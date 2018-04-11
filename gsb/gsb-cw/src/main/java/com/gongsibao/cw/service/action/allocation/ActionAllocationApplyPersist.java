package com.gongsibao.cw.service.action.allocation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.IAllocationService;
import com.gongsibao.entity.cw.Allocation;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.cw.dict.FinanceDict.AuditStatus;

public class ActionAllocationApplyPersist implements IAction {
	//付款服务
	IAllocationService allocationService = ServiceFactory.create(IAllocationService.class);


	
    @Override
    public void execute(ActionContext ctx) {
    
    	 Allocation allocation = (Allocation) ctx.getItem();
    	 allocation.toNew();
    	 //审核状态
    	 allocation.setStatus(AuditStatus.Status_1);
    	 //审核步骤
    	 allocation.setAuditStep(1);
    	 
    	 UserPermission up = UserPermissionManager.getUserPermission();
    	 allocation.setDepartmentId(up.getDepartmentId());
    	 
    	 allocation.setCode(getCode());
    	 Allocation temp = allocationService.save(allocation);
    	 ctx.setItem(temp);
         
    }
    
    //生成调拨单据号
    private static String getCode(){
    	Date dt=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long temp=Math.round(Math.random()*8999+1000);
    	String code = "DB"+format.format(dt)+temp;
        return code;
    } 
}
