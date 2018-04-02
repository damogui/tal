package com.gongsibao.cw.service.action.payment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dict.FinanceDict.AuditStatus;

public class ActionPaymentApplyPersist implements IAction {
//	//借款服务
//	ILoanService loanService = ServiceFactory.create(ILoanService.class);
//	//明细服务

	
    @Override
    public void execute(ActionContext ctx) {
    
//    	 Loan loan = (Loan) ctx.getItem();
//    	 loan.toNew();
//    	 //审核状态
//    	 loan.setStatus(AuditStatus.Status_1);
//    	 //审核步骤
//    	 loan.setAuditStep(1);
//    	 loan.setCode(getLoanCode());
//    	 Loan temp = loanService.save(loan);
//    	 ctx.setItem(temp);
         
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
