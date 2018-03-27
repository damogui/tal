package com.gongsibao.crm.service.action.task.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 保存商机：新增状态，如果是立即分配，则执行分配
 */
public class ActionSaveTaskAllocation implements IAction {

    //客户服务
    INCustomerService nCustomerService = ServiceFactory.create(INCustomerService.class);
    //商机服务
    INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);

    @Override
    public void execute(ActionContext ctx) {

        NCustomerTask task = (NCustomerTask) ctx.getItem();
        NAllocationType allocationType = task.getAllocationType();
        //当为【手动分配】时，修改商机的分配状态
        if(allocationType.equals(NAllocationType.MANUAL)){
            nCustomerTaskService.updateAllocationState(task);
        }

        // 分配状态
        AllocationState allocationState = task.getAllocationState();
        if (allocationState == AllocationState.ALLOCATED || allocationState == AllocationState.NOALLOCATED) {

            //分配状态为【不分配】、【已分配】时不执行分配
            return;
        }

        if (allocationType == NAllocationType.AUTO || allocationType == NAllocationType.SemiAutomatic) {
            if (!task.getCustomerId().equals(0)) {
                NCustomer customer = nCustomerService.getById(task.getCustomerId());
                task.setCustomer(customer);
            }
            // 自动分配，半自动分配，立即分配时调用
            ActionContext autoCtx = new ActionContext();
            {
                autoCtx.setPath("gsb/crm/task/allocation/auto");
                autoCtx.setItem(task);
                autoCtx.setState(task.getEntityState());
            }

            ActionManager action = new ActionManager();
            action.execute(autoCtx);
        }
    }
}
