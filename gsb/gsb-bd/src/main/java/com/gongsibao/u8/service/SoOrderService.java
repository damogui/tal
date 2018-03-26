package com.gongsibao.u8.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;

@Service
public class SoOrderService extends PersistableService<SoOrder> implements ISoOrderService {

    //业务员接口服务
    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);

    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
    public SoOrderService() {
        super();
        this.type = SoOrder.class;
    }

    @Override
    public Boolean updateManuaVoucherStatus(Integer orderId, OrderManualVoucherStatus status) {

        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order");
            updateBuilder.set("manual_voucher_status", status.getValue());
            updateBuilder.where("pkid=" + orderId);
        }

        String cmdText = updateBuilder.toSQL();
        return this.pm.executeNonQuery(cmdText, null) > 0;
    }

    @Override
    public Map<Integer, String> getCustNameByOrderIdList(List<Integer> orderIdList) {

        Map<Integer, String> map = new HashMap<Integer, String>();
        String orderIds = StringManager.join(",", orderIdList);

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT oi.pkid 'orderId', ");
        sqlBuffer.append("(CASE WHEN (cri1.`pkid` IS NOT NULL AND cri1.`company_name`!='' ) THEN cri1.`company_name`   ");
        sqlBuffer.append("WHEN (c.pkid IS NULL) THEN (CASE WHEN a.real_name='' THEN a.name ELSE a.real_name END) ");
        sqlBuffer.append("WHEN (ccm.pkid IS NULL OR cri.company_name='') THEN (CASE WHEN c.real_name='' THEN c.`mobile` ELSE c.real_name END) ELSE cri.company_name END) 'custName' FROM so_order oi  ");
        sqlBuffer.append("JOIN uc_account a ON a.pkid = oi.account_id ");
        sqlBuffer.append("LEFT JOIN crm_customer c ON c.account_id = a.pkid ");
        sqlBuffer.append("LEFT JOIN crm_customer_company_map ccm ON ccm.customer_id = c.pkid ");
        sqlBuffer.append("LEFT JOIN crm_company_intention cri ON cri.pkid = ccm.company_id ");
        sqlBuffer.append("LEFT JOIN crm_company_intention cri1 ON cri1.pkid = oi.company_id ");
        sqlBuffer.append("WHERE oi.pkid IN(" + orderIds + ") ");

        DataTable executeTable = this.pm.executeTable(sqlBuffer.toString(), null);

        for (IRow row : executeTable) {
            map.put(row.getInteger("orderId"), row.getString("custName"));
        }
        return map;
    }

    //转移/分配（支持批量转移/分配）
    @Override
    public void orderTran(List<Integer> orderIdList, Integer toUserId) {

        //订单id集合
        String orderIds = StringManager.join(",", orderIdList);

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("pkid in (" + orderIds + ")");
            oql.setOrderby("add_time Desc");
        }
        List<SoOrder> soOrderList = this.pm.queryList(oql);
        //转移的目标业务员
        Salesman toUser = salesmanService.byEmployeeId(toUserId);
        //根据订单id集合获取，对应的业务员信息
        Map<Integer, Salesman> salesmanMap = getSalesmanMapByOrderList(soOrderList);

        for (SoOrder order : soOrderList) {
            Map<String, Object> setMap = new HashMap<String, Object>();
            setMap.put("toUser", toUser);//转移的目标业务员
            setMap.put("formUser", salesmanMap.get(order.getId()));//转移的来自业务员
            ActionContext ctx = new ActionContext();
            {
                ctx.setPath("gsb/crm/order/transform");
                ctx.setItem(order);
                ctx.setState(order.getEntityState());
                ctx.setStatus(setMap);
            }
            ActionManager action = new ActionManager();
            action.execute(ctx);
        }
    }

    /*根据订单id集合获取，对应的业务员信息*/
    @Override
    public Map<Integer, Salesman> getSalesmanMapByOrderIdList(List<Integer> orderIdList) {

        if (CollectionUtils.isEmpty(orderIdList)) {
            throw new BusinessException("订单id集合不能为空");
        }

        //订单id集合
        String orderIds = StringManager.join(",", orderIdList);

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("pkid in (" + orderIds + ")");
            oql.setOrderby("add_time Desc");
        }

        List<SoOrder> soOrderList = this.pm.queryList(oql);

        Map<Integer, Salesman> res = getSalesmanMapByOrderList(soOrderList);

        return res;
    }


    //region 私有方法
    /*
    * 根据订单集合获取，对应的业务员信息
    * */
    private Map<Integer, Salesman> getSalesmanMapByOrderList(List<SoOrder> soOrderList) {

        Map<Integer, Salesman> res = new HashMap<>();

        if (CollectionUtils.isEmpty(soOrderList)) {
            return res;
        }

        List<Integer> employeeIdList = new ArrayList<>();
        for (SoOrder order : soOrderList) {
            if (NumberUtils.toInt(order.getOwnerId()) != 0) {
                employeeIdList.add(order.getOwnerId());
            }
        }

        if (CollectionUtils.isEmpty(employeeIdList)) {
            return res;
        }

        String employeeIds = StringManager.join(",", employeeIdList);

        Oql saleManOql = new Oql();
        {
            saleManOql.setType(Salesman.class);
            saleManOql.setSelects("Salesman.*,Salesman.supplier.{id,name},Salesman.department.{id,name},Salesman.employee.{id,name}");
            saleManOql.setFilter("employee_id in (" + employeeIds + ")");
        }

        List<Salesman> salesmenlist = salesmanService.queryList(saleManOql);

        for (SoOrder order : soOrderList) {
            for (Salesman sale : salesmenlist) {
                if (NumberUtils.toInt(order.getOwnerId()) != 0 && NumberUtils.toInt(order.getOwnerId()) == NumberUtils.toInt(sale.getEmployeeId())) {
                    res.put(order.getId(), sale);
                }
            }
        }
        return res;
    }
    // endregion

	@Override
	public SoOrder getByOrderId(Integer orderId) {
		Oql oql = new Oql();
        {
        	oql.setType(this.type);
        	oql.setSelects("*");
        	oql.setFilter("pkid =" + orderId);
        }
       SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}

    @Override
    public SoOrder getOrderWithOrderProdsByOrderId(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("soOrder.*,products.*");
            oql.setFilter("pkid =" + orderId);
        }
        SoOrder entity = orderService.queryFirst(oql);
        return entity;
    }

	@Override
	public SoOrder getByOrderNo(String orderNo) {
		Oql oql = new Oql();
        {
        	oql.setType(this.type);
        	oql.setSelects("*");
        	oql.setFilter("no =" + orderNo);
        }
       SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}


}
