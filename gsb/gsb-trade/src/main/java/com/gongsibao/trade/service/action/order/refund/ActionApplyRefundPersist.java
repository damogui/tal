package com.gongsibao.trade.service.action.order.refund;

import com.gongsibao.entity.trade.NU8BankSoPayMap;
import com.gongsibao.trade.base.INU8BankSoPayMapService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.dic.RefundWayType;
import com.gongsibao.trade.base.INDepRefundService;
import com.gongsibao.trade.base.IRefundService;

public class ActionApplyRefundPersist implements IAction {

    INU8BankSoPayMapService nU8BankSoPayMapService = ServiceFactory.create(INU8BankSoPayMapService.class);

    @Override
    public void execute(ActionContext ctx) {
        Refund refund = (Refund) ctx.getItem();
        //1.添加退款
        IRefundService refundService = ServiceFactory.create(IRefundService.class);
        refund.toNew();
        refund.setWayType(RefundWayType.Dsh);
        //目前给默认值
        refund.setNo("");
        refund.setCost(0);
        refund = refundService.save(refund);
        ctx.setItem(refund);
        //保存退款表和u8的中间表
        saveU8BankSoPayMap(refund);

        //2.添加退款业绩
        INDepRefundService depRefundService = ServiceFactory.create(INDepRefundService.class);
        for (NDepRefund item : refund.getDepRefunds()) {
            NDepRefund entity = new NDepRefund();
            entity.toNew();
            entity.setOrderId(refund.getOrderId());
            entity.setAmount(item.getAmount());
            entity.setRefundId(item.getRefundId());
            entity.setSupplierId(item.getSupplierId());
            entity.setDepartmentId(item.getDepartmentId());
            entity.setSalesmanId(item.getSalesmanId());
            depRefundService.save(entity);
        }
    }

    //保存退款表和u8的中间表
    private void saveU8BankSoPayMap(Refund refund) {

        NU8BankSoPayMap nU8BankSoPayMap = new NU8BankSoPayMap();
        {
            nU8BankSoPayMap.setPayId(refund.getId());
            nU8BankSoPayMap.setSetOfBooksId(refund.getSetOfBooksId());
            nU8BankSoPayMap.setType(1);//类别（0：支付 1：退款）
            nU8BankSoPayMap.setU8BankId(refund.getU8BankId());
            nU8BankSoPayMap.setPrice(refund.getAmount());
            nU8BankSoPayMap.toNew();
        }
        nU8BankSoPayMapService.save(nU8BankSoPayMap);
    }
}
