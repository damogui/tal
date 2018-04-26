package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.BreachType;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.u8.base.ISoOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractPersist implements IAction {

    IContractService contractService = ServiceFactory.create(IContractService.class);

    ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

    IFileService fileService = ServiceFactory.create(IFileService.class);

    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();
        //初始状态为【待审核】
        contract.setAuditStatusId(AuditStatusType.Dsh);
        SoOrder order = soOrderService.getOrderWithOrderProdsByOrderId(contract.getOrderId());
        //合同业绩额
        Integer realAmount = 0;
        Integer achievementAmount = 0;
        for (OrderProd orderProd : order.getProducts()) {
            achievementAmount = achievementAmount + orderProd.getPrice();
        }
        contract.setAchievementAmount(achievementAmount);
        //当有材料撰写情况
        if (contract.getHasDataFee().equals(BreachType.YOU)) {
            //合同金额=订单金额+材料撰写费
            realAmount = contract.getDataFee() + order.getPayablePrice();
            contract.setRealAmount(realAmount);
        }
        //分配合同跟进人
        contract.setSupplierId(order.getSupplierId());
        contract.setDepartmentId(order.getDepartmentId());
        contract.setSalesmanId(order.getOwnerId());
        contract = contractService.save(contract);
        //附件的保存
        saveFiles(contract);
        //更新合同
        ctx.setItem(contract);
    }

    //region 私有方法
    //附件的保存
    private void saveFiles(Contract contract) {
        for (File f : contract.getFiles()) {
            f.setFormId(contract.getId());
        }
        if (CollectionUtils.isNotEmpty(contract.getFiles())) {
            fileService.saves(contract.getFiles());
        }
    }
    // endregion

}
