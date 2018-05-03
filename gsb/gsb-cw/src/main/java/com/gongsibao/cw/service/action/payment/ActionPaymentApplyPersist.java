package com.gongsibao.cw.service.action.payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.entity.Employee;
import org.netsharp.wx.ea.base.IEaMessageService;

import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.cw.dict.FinanceDict.AuditStatus;

public class ActionPaymentApplyPersist implements IAction {
	//付款服务
	IPaymentService paymentService = ServiceFactory.create(IPaymentService.class);
	
    @Override
    public void execute(ActionContext ctx) {
    
    	 Payment payment = (Payment) ctx.getItem();
    	 payment.toNew();
    	 //审核状态
    	 payment.setStatus(AuditStatus.Status_1);
    	 //审核步骤
    	 payment.setAuditStep(1);
    	 payment.setCode(getLoanCode());
    	 Payment temp = paymentService.save(payment);
    	 ctx.setItem(temp);
    }
    
    //生成借款单据
    private static String getLoanCode(){
    	Date dt=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long temp=Math.round(Math.random()*8999+1000);
    	String code = "FK"+format.format(dt)+temp;
        return code;
    } 
    
	
}
