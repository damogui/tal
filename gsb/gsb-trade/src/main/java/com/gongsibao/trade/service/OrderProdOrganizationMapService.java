package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdOrganizationMap;
import com.gongsibao.trade.base.IOrderProdOrganizationMapService;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.utils.NumberUtils;

@Service
public class OrderProdOrganizationMapService extends PersistableService<OrderProdOrganizationMap> implements IOrderProdOrganizationMapService {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);

    IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);

    public OrderProdOrganizationMapService() {
        super();
        this.type = OrderProdOrganizationMap.class;
    }

    @Override
    public Boolean updateOrganizationMap(Integer orderProdId, Integer supplierId) {
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order_prod_organization_map");
            updateBuilder.set("supplier_id", supplierId);
            updateBuilder.where("order_prod_id =" + orderProdId);
        }
        String sql = updateBuilder.toSQL();
        return this.pm.executeNonQuery(sql, null) > 0;
    }

    //根据订单编号查询操作组及操作员
    @Override
    public List<OrderProdOrganizationMap> getListByOrderNo(String orderNo, int startIndex, int pageSize) {
        List<OrderProdOrganizationMap> resList = new ArrayList<>();
        if (StringManager.isNullOrEmpty(orderNo)) {
            return resList;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT od.pkid 'orderProdId',sp.name 'supplierName', ");
        sql.append("em.name 'operator',od.product_name 'productName',cri.company_name 'companyName',od.city_name 'cityName' FROM so_order_prod od ");
        sql.append("JOIN so_order oi ON oi.pkid = od.order_id ");
        sql.append("LEFT JOIN crm_company_intention cri ON cri.pkid = od.company_id ");
        sql.append("LEFT JOIN so_order_prod_organization_map opom ON opom.order_prod_id=od.pkid ");
        sql.append("LEFT JOIN sp_supplier sp ON sp.id = opom.supplier_id ");
        sql.append("LEFT JOIN so_order_prod_user_map opum ON opum.order_prod_id = od.pkid AND opum.type_id = 3063 ");
        sql.append("LEFT JOIN sp_salesman sm ON sm.employee_id = opum.user_id ");
        sql.append("LEFT JOIN sys_permission_employee em ON em.id = sm.employee_id ");
        sql.append("WHERE oi.no='" + orderNo + "' ");
        sql.append("ORDER BY od.pkid ASC ");
        sql.append("LIMIT " + startIndex + ", " + pageSize + " ");
        DataTable rows = this.pm.executeTable(sql.toString(), null);
        for (IRow row : rows) {
            Integer orderProdId = NumberUtils.toInt(row.getInteger("orderProdId"));
            String supplierName = row.getString("supplierName");
            String operator = row.getString("operator");
            String productName = row.getString("productName");
            String companyName = row.getString("companyName");
            String cityName = row.getString("cityName");
            OrderProdOrganizationMap map = new OrderProdOrganizationMap();
            OrderProd orderProd = setOrderProdInfo(orderProdId, productName, companyName, cityName);
            map.setOrderProd(orderProd);
            map.setOrderProdId(orderProdId);
            map.setSupplierName(StringManager.isNullOrEmpty(supplierName) ? "无" : supplierName);
            map.setOperator(operator);
            resList.add(map);
        }
        return resList;
    }


    private OrderProd setOrderProdInfo(Integer orderProdId, String productName, String companyName, String cityName) {
        OrderProd orderProd = new OrderProd();
        orderProd.setId(orderProdId);
        orderProd.setProductName(productName);
        orderProd.setCityName(cityName);
        CompanyIntention companyIntention = new CompanyIntention();
        companyIntention.setCompanyName(companyName);
        orderProd.setCompanyIntention(companyIntention);
        return orderProd;
    }


    @Override
    public List<OrderProdOrganizationMap> getListByOrderProdId(Integer orderProdId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("OrderProdOrganizationMap.supplierId,OrderProdOrganizationMap.supplier.name");
            oql.setFilter("order_prod_id=?");
            oql.getParameters().add("order_prod_id", orderProdId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public Integer getCountByOrderNo(String orderNo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(od.pkid) 'rcount' FROM so_order_prod od ");
        sql.append("JOIN so_order oi ON oi.pkid = od.order_id ");
        sql.append("WHERE oi.no='" + orderNo + "' ");
        int count = NumberUtils.toInt(this.pm.executeScalar(sql.toString(), null));
        return count;
    }

    @Override
    public Map<Integer, String> getOrderOperationGroup(List<Integer> orderProdIdList) {
        Map<Integer, String> resMap = new HashMap<>();
        List<OrderProdOrganizationMap> orderProdMapList = getOrderProdIdList(orderProdIdList);
        for (OrderProdOrganizationMap map : orderProdMapList) {
            String organizationName = resMap.get(map.getOrderProdId());
            if (!StringManager.isNullOrEmpty(organizationName)) {
                resMap.put(map.getOrderProdId(), organizationName + "," + map.getSupplier().getName());
            } else {
                resMap.put(map.getOrderProdId(), map.getSupplier() != null ? map.getSupplier().getName() : "");
            }
        }
        return resMap;
    }

    @Override
    public List<OrderProdOrganizationMap> getOrderProdIdList(List<Integer> orderProdIdList) {
        List<OrderProdOrganizationMap> resList = new ArrayList<>();
        if (CollectionUtils.isEmpty(orderProdIdList)) {
            return resList;
        }
        String orderProdIds = StringManager.join(",", orderProdIdList);
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("orderProdOrganizationMap.*,supplier.{id,name}");
            oql.setFilter("order_prod_id in (" + orderProdIds + ")");
            oql.setOrderby("pkid DESC");
        }
        resList = this.pm.queryList(oql);
        return resList;
    }
}