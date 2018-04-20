package com.gongsibao.cw.service.action.loan;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict.AuditStatus;

public class ActionLoanApplyPersist implements IAction {
	//借款服务
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	//明细服务
	ICostDetailService costDetailService =  ServiceFactory.create(ICostDetailService.class);
	//附件服务
	IFileService fileService = ServiceFactory.create(IFileService.class);
	
	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class); 
	
    @Override
    public void execute(ActionContext ctx) {
    	 Loan loan = (Loan) ctx.getItem();
    	 if(loan.getId()==null){
    		 loan.toNew(); 
    	 }else{
    		 loan.toPersist();
    	 }
    	 //审核状态
    	 loan.setStatus(AuditStatus.Status_1);
    	 //审核步骤
    	 loan.setAuditStep(1);
    	 loan.setCode(getLoanCode());
    	 //创建人 所属部门
    	 UserPermission up = UserPermissionManager.getUserPermission();
    	 Organization organization = organizationService.getMainDepartment(SessionManager.getUserId());
    	 loan.setDepartmentId(up.getEmployee().getDepartmentId());
    	 loan.setDepartmentName(organization.getName());
    	 loan.setArrearsAmount(loan.getAmount());
    	 Loan temp = loanService.save(loan);
    	 ctx.setItem(temp);
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
