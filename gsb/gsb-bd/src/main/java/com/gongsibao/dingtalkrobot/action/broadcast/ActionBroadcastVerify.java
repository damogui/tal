package com.gongsibao.dingtalkrobot.action.broadcast;

import com.gongsibao.utils.DingTalkRobotUtils;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.u8.base.IOrderProdService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.util.StringManager;

import java.util.*;

public class ActionBroadcastVerify implements IAction {
    private ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);
    private IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
    private ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
    private IOrderProdService soOrderProdService = ServiceFactory.create(IOrderProdService.class);

    //1、校验
    @Override
    public void execute(ActionContext ctx) {
        Integer orderId = (Integer) ctx.getItem();
        if (NumberUtils.toInt(orderId) <= 0) return;
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("isSend", true);
        String ywyMobile = null;
        String atMobile = null;
        String atName = null;
        List<String> ywyNames = new ArrayList<>();
        if (StringUtils.isBlank(DingTalkRobotUtils.getGsbFollowToken())) {
            statusMap.put("isSend", false);
            return;// -2
        }
        SoOrder soOrder = soOrderService.getByOrderId(orderId);
        if (soOrder == null) {
            statusMap.put("isSend", false);
            return;
        }//-3
        Employee salesman = employeeService.byId(soOrder.getOwnerId());
        if (salesman == null || salesman.getDisabled()) {
            statusMap.put("isSend", false);
            return;//-6
        }
        //订单服务商
        Supplier supplier = supplierService.getById(NumberUtils.toInt(soOrder.getSupplierId()));
        if (supplier == null) {
            statusMap.put("isSend", false);
            return;//服务商存在-11
        }
        if (!supplier.getType().equals(SupplierType.SELFSUPPORT)) {
            statusMap.put("isSend", false);
            return;//非自营的不播报-12
        }
        Employee boss = employeeService.byPhone(supplier.getMobilePhone());
        if (boss == null || boss.getDisabled()) {
            statusMap.put("isSend", false);
            return;//服务商大领导不存在 -13
        }
        //屏蔽曹玉玺
        if (soOrder.getAccountId() == 95608) return;//-10
        if (orderId == 0) {
            statusMap.put("isSend", false);
            return;//-2
        }
        List<OrderProd> prodList = soOrderProdService.getByOrderId(orderId);
        if (CollectionUtils.isEmpty(prodList)) {
            statusMap.put("isSend", false);
            return;//-1
        }
        String prodName = getProductName(prodList);
        if (StringManager.isNullOrEmpty(prodName)) {
            statusMap.put("isSend", false);
            return;//-5
        }
        if (StringUtils.isBlank(ywyMobile)) {
            ywyMobile = "\"" + salesman.getMobile() + "\",\"" + boss.getMobile() + "\"";
            atMobile = "@" + salesman.getMobile() + " @" + boss.getMobile();
            atName = "@" + salesman.getName() + " @" + boss.getName();
            ywyNames.add(salesman.getName());
            ywyNames.add(boss.getName());
        } else {
            if (atMobile.indexOf(salesman.getMobile()) == -1) {
                ywyMobile += "," + "\"" + salesman.getMobile() + "\"";
                atMobile += " @" + salesman.getMobile();
                atName += " @" + salesman.getName();
                ywyNames.add(salesman.getName());
            }
            if (atMobile.indexOf(salesman.getMobile()) == -1) {
                ywyMobile += "," + "\"" + salesman.getMobile() + "\",\"" + boss.getMobile() + "\"";
                atMobile += " @" + salesman.getMobile() + "@" + boss.getMobile();
                atName += " @" + salesman.getName();
                ywyNames.add(salesman.getName());
                ywyNames.add(boss.getName());
            }
        }
        if (StringManager.isNullOrEmpty(ywyMobile)) {
            statusMap.put("isSend", false);
            return;//-6
        }
        if (StringManager.isNullOrEmpty(atMobile)) {
            statusMap.put("isSend", false);
            return;//-14
        }

        statusMap.put("ywyMobile", ywyMobile);
        statusMap.put("atMobile", atMobile);
        statusMap.put("atName", atName);
        statusMap.put("ywyNames", ywyNames);
        statusMap.put("accountId", soOrder.getAccountId());
        statusMap.put("prodName", prodName);
        ctx.setStatus(statusMap);
    }

    //region 私有方法
    private String getProductName(List<OrderProd> prodList) {
        String prodName = "";
        for (OrderProd orderProd : prodList) {
            prodName = prodName + orderProd.getProductName() + ",";
        }
        if (prodName.endsWith(",")) {
            prodName = StringManager.substring(prodName, 0, prodName.length() - 1);
        }
        return prodName;
    }
    //endregion

}
