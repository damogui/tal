package com.gongsibao.cw.service.action.expense;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.TripRecord;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dict.FinanceDict.AuditStatus;

public class ActionExpenseApplyPersist implements IAction {
	//报销服务
	IExpenseService expenseService = ServiceFactory.create(IExpenseService.class);
   
	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class); 
	
	@Override
    public void execute(ActionContext ctx) {
    
    	 Expense expense = (Expense) ctx.getItem();
    	 if(expense.getId() == null){
    		 expense.toNew();
    	 }else{
    		 expense.toPersist();
    	 }
    	 //审核状态
    	 expense.setStatus(AuditStatus.Status_1);
    	 //审核步骤
    	 expense.setAuditStep(1);
    	//创建人 所属部门
    	 UserPermission up = UserPermissionManager.getUserPermission();
    	 expense.setDepartmentId(up.getEmployee().getDepartmentId());
    	 Organization organization = organizationService.getMainDepartment(SessionManager.getUserId());
    	 expense.setDepartmentName(organization.getName());
    	 expense.setCode(getExpenseCode());
    	 expense.setBankId(FinanceDict.getBankId(expense.getSetOfBooksId()));
    	 Expense temp = expenseService.save(expense);
    	 ctx.setItem(temp);
    }
    
    //生成借款单据
    private static String getExpenseCode(){
    	Date dt=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long temp=Math.round(Math.random()*8999+1000);
    	String code = "BX"+format.format(dt)+temp;
        return code;
    } 
}
