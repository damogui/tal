package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import org.apache.commons.lang.StringUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdOrganizationMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.OrderPayStatusType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.product.base.IProductService;
import com.gongsibao.trade.base.IOrderProdOrganizationMapService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;

/**
 * Created by yxb on 2018/4/10.
 */
public class SalesmanOrderDetailListPart extends AdvancedListPart {
    IProductService productService = ServiceFactory.create(IProductService.class);
    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
    IOrderProdOrganizationMapService organizationService = ServiceFactory.create(IOrderProdOrganizationMapService.class);
    IOrderProdUserMapService proUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);
    IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);

    @Override
    public String getFilterByParameter(FilterParameter parameter) {
        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单明细编号、订单编号、下单人、下单人电话、明细订单关联公司、订单关联公司)
        String keyword = StringUtils.trimToEmpty(parameter.getValue1().toString());
        if (parameter.getKey().equals("keyword")) {
            filters.add("orderProd.pkid = '" + keyword + "'");
            String companyWhere = "company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )";
            filters.add("orderProd." + companyWhere);
            filters.add(getOrderwhere(keyword, companyWhere));
            return "(" + StringManager.join(" or ", filters) + ")";
        }
        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderProd.*,");
        sb.append("soOrder.*,");
        sb.append("soOrder.owner.{id,name},");
        sb.append("processStatus.{pkid,name},");
        sb.append("companyIntention.{pkid,companyName},");
        sb.append("soOrder.companyIntention.{pkid,companyName},");
        sb.append("soOrder.customer.{id,realName}");
        oql.setSelects(sb.toString());
        oql.setOrderby("pkid DESC");
        List<OrderProd> orderProdList = (List<OrderProd>) super.doQuery(oql);
        List<Integer> orderProdIdList = getOrderProdIdList(orderProdList);
        //设置操作员
        setOperator(orderProdIdList, orderProdList);
        //设置操作组
        setOperationGroup(orderProdIdList, orderProdList);
        return orderProdList;
    }


    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            OrderProd soOrderPro = ((OrderProd) list.get(i));
            //设置操作员
            ob2.get(i).put("operator", soOrderPro.getOperator());
            //设置操作组
            ob2.get(i).put("operationsGroup", soOrderPro.getOperationsGroup());
        }
        return json;
    }

    /**
     * 根据产品Id获取是否有办理名称
     *
     * @param productId
     * @return
     */
    public Boolean isHandle(Integer productId) {
        Oql oql = new Oql();
        {
            oql.setType(Product.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", productId, Types.INTEGER);
        }
        Product entity = productService.queryFirst(oql);
        return entity.getIsHandle() == null ? false : entity.getIsHandle();
    }

    /**
     * 根据订单获取关联公司
     *
     * @param productId
     * @return
     */
    public String getCompaniesName(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("SoOrder.companyId,SoOrder.companyIntention.companyName");
            oql.setFilter("id=?");
            oql.getParameters().add("id", orderId, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);
        return entity.getCompanyIntention() == null ? "" : entity.getCompanyIntention().getCompanyName();
    }

    /**
     * 开始操作前置条件：
     * 1.全额：订单余额=订单金额
     * 2.分期：订单余额＞0 && 已付金额＋结转转入额≥一期款
     * 订单余额 = paidPrice+carryIntoAmount-refundPrice-carryAmount
     *
     * @param orderId
     * @return -1：订单信息为空、0：不满足全款、1：不满足分期、2：都满足、3：分期审核中
     */
    public Integer meetBegOption(Integer orderId) {
        Integer retuValue = 2;
        SoOrder orderEntity = orderService.getByOrderId(orderId);
        if (orderEntity != null) {
            //获取订单余额
            Integer balance = NumberUtils.toInt(orderEntity.getPaidPrice()) + NumberUtils.toInt(orderEntity.getCarryIntoAmount()) - NumberUtils.toInt(orderEntity.getRefundPrice()) - NumberUtils.toInt(orderEntity.getCarryAmount());
            //根据是否支付全款payStatus
            if (orderEntity.getPayStatus().equals(OrderPayStatusType.Yfk)) {
                if (!balance.equals(orderEntity.getPayablePrice())) {
                    retuValue = 0;
                }
            } else {
                AuditStatusType orderCurrentStatusType = orderEntity.getInstallmentAuditStatusId();
                if (orderCurrentStatusType.equals(AuditStatusType.Shtg)) {
                    //获取一期款
                    Integer firstPhase = Integer.valueOf(StringManager.isNullOrEmpty(orderEntity.getInstallmentMode()) ? "0" : orderEntity.getInstallmentMode().split("\\|")[0]);
                    //获取现有金额（已付金额＋结转转入额）
                    Integer existAmount = NumberUtils.toInt(orderEntity.getPaidPrice()) + NumberUtils.toInt(orderEntity.getCarryIntoAmount());
                    if (balance <= 0 || existAmount < firstPhase) {
                        retuValue = 1;
                    }
                } else {
                    retuValue = orderCurrentStatusType.equals(AuditStatusType.Bhsh)||orderCurrentStatusType.equals(AuditStatusType.wu) ? 0 : 3;
                }
            }
        } else {
            retuValue = -1;
        }
        return retuValue;
    }

    /**
     * 订单明细操作-办理名称、操作公司
     * OrderProdOrganizationMap 表中添加一条数据
     *
     * @param orderProdId 订单产品Id
     * @param supplierId  服务商Id
     * @param handleName  办理名称
     * @param companyId   关联公司Id或者0
     * @return
     */
    public Boolean begOption(Integer orderProdId, Integer supplierId, String handleName, Integer companyId) {
        //1.修改订单明细
        orderProdService.updateOrderDetail(orderProdId, handleName, companyId);
        //2.添加数据OrderProdOrganizationMap
        OrderProdOrganizationMap organizateMap = new OrderProdOrganizationMap();
        {
            organizateMap.toNew();
            organizateMap.setOrderProdId(orderProdId);
            organizateMap.setOrganizationId(supplierId);
            organizateMap.setSupplierId(supplierId);
        }
        organizationService.save(organizateMap);

        return true;
    }

    /**
     * 订单明细操作-变更服务商
     *
     * @param orderProdId 订单产品Id
     * @param supplierId  服务商Id
     * @return
     */
    public Boolean operateGroup(Integer orderProdId, Integer supplierId) {
        return organizationService.updateOrganizationMap(orderProdId, supplierId);
    }

    //region 私有方法
    private List<Integer> getOrderProdIdList(List<OrderProd> resList) {
        List<Integer> orderProdIdList = new ArrayList<>();
        for (OrderProd orderProd : resList) {
            orderProdIdList.add(orderProd.getId());
        }
        return orderProdIdList;
    }

    //设置操作员
    private void setOperator(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, String> operatorMap = orderProdUserMapService.getOrderUserByIds(orderProdIdList, OrderProdUserMapType.Czy.getValue(), OrderProdUserMapStatus.Zzfz.getValue());
        for (OrderProd orderProd : resList) {
            orderProd.setOperator(operatorMap.get(orderProd.getId()));
        }
    }

    //设置操作组
    private void setOperationGroup(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, String> OperationGroupMap = organizationService.getOrderOperationGroup(orderProdIdList);
        for (OrderProd orderProd : resList) {
            orderProd.setOperationsGroup(OperationGroupMap.get(orderProd.getId()));
        }
    }

    private String getOrderwhere(String keyword, String companyWhere) {
        String res = "";
        List<String> orderWhere = new ArrayList<>();
        orderWhere.add("no = '" + keyword + "'");
        orderWhere.add("account_mobile = '" + keyword + "'");
        orderWhere.add("customer_id in (select pkid from crm_customer where real_name like '%" + keyword + "%')");
        orderWhere.add(companyWhere);
        res = "orderProd.order_id in(select pkid from so_order where (" + StringManager.join(" or ", orderWhere) + ") )";
        return res;
    }
    //endregion

}
