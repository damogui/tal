package com.gongsibao.cw.service.action.loan;

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

public class ActionLoanApplyPersist implements IAction {
	//借款服务
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	//明细服务
	ICostDetailService costDetailService =  ServiceFactory.create(ICostDetailService.class);
	//附件服务
	IFileService fileService = ServiceFactory.create(IFileService.class);
	
    @Override
    public void execute(ActionContext ctx) {
    
    	 Loan loan = (Loan) ctx.getItem();
    	 loan.toNew();
    	 //审核状态
    	 loan.setStatus(AuditStatus.Status_1);
    	 //审核步骤
    	 loan.setAuditStep(1);
    	 loan.setCode(getLoanCode());
    	 Loan temp = loanService.save(loan);
    	 ctx.setItem(temp);
         if (temp != null && temp.getId() != null) {
        	 
        	 List<CostDetail> costDetailList = loan.getCostDetailItem();
        	 for(CostDetail item : costDetailList){
        		 item.toNew();
        		 item.setFormId(temp.getId());
        		 costDetailService.save(item);
        	 }
        	 //保存附件
        	 List<File> fileList = loan.getFiles();
        	 if(fileList != null && fileList.size() > 0 ){
        		 for(File fileItem : fileList){
        			 fileItem.toNew();
        			 fileItem.setCreateTime(loan.getCreateTime());
        			 fileItem.setCreator(loan.getCreator());
        			 fileItem.setFormId(temp.getId());
        			 fileService.save(fileItem);
        		 }
        	 }
         }
    }
    
    //生成借款单据
    private static String getLoanCode(){
    	Date dt=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long temp=Math.round(Math.random()*8999+1000);
    	String code = "JK"+format.format(dt)+temp;
        return code;
    } 
}
