package com.gongsibao.crm.service.action.task.allocation.auto;

import java.sql.Types;
import java.util.Date;

import com.gongsibao.utils.NumberUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.utils.DateUtils;

/**
 * @author zhangchao 任务的分配状态的回写
 */
public class ActionAutoAllocationUpdateTaskStatus implements IAction {

    INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);

    @Override
    public void execute(ActionContext ctx) {
        NCustomerTask entity = (NCustomerTask) ctx.getItem();

        // 验证非空
        if (entity == null) {
            throw new BusinessException("该任务不存在！");
        }
        //服务商id
        Integer supplierId = NumberUtils.toInt(entity.getSupplierId());
        //部门id
        Integer departmentId = NumberUtils.toInt(entity.getDepartmentId());
        //业务员id
        Integer ownerId = NumberUtils.toInt(entity.getOwnerId());

        // 当没有分配到人，也没有分配到部门，则不改变状态
        if (supplierId.equals(0) && departmentId.equals(0) && ownerId.equals(0)) {
            return;
        }

        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("n_crm_customer_task");
            updateSql.set("allocation_state", AllocationState.WAIT.getValue());
            // 跟进状态改为【未启动】
            updateSql.set("foolow_status", CustomerFollowStatus.UNSTART.getValue());
            // 跟新最后分配时间
            updateSql.set("last_allocation_time", DateUtils.getDateStr(new Date()));
            // 最后分配人Id（机器分配，默认写0）
            updateSql.set("last_allocation_user_id", 0);
            updateSql.where("id=?");
        }

        // 状态改为【待分配】
        entity.setAllocationState(AllocationState.WAIT);
        // 最后分配人Id（机器分配，默认写0）
        entity.setLastAllocationUserId(0);
        entity.setFoolowStatus(CustomerFollowStatus.UNSTART);
        entity.setLastAllocationTime(new Date());

        if (!supplierId.equals(0) && departmentId.equals(0) && ownerId.equals(0) && entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
            // 状态改为【已分配-服务商】
            updateSql.set("allocation_state", AllocationState.ALLOCATED_Supplier.getValue());
            entity.setAllocationState(AllocationState.ALLOCATED_Supplier);
            //是否被分配过(只修改一次，过滤未分配)
            updateSql.set("distribut", true);
            entity.setDistribut(true);
        }

        if (!supplierId.equals(0) && !departmentId.equals(0) && ownerId.equals(0) && entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
            // 状态改为【已分配-部门】
            updateSql.set("allocation_state", AllocationState.ALLOCATED_Department.getValue());
            entity.setAllocationState(AllocationState.ALLOCATED_Department);
            //是否被分配过(只修改一次，过滤未分配)
            updateSql.set("distribut", true);
            entity.setDistribut(true);
        }

        if (!ownerId.equals(0) && entity.getAllocationType().equals(NAllocationType.AUTO)) {
            // 状态改为【已分配-业务员】
            updateSql.set("allocation_state", AllocationState.ALLOCATED.getValue());
            entity.setAllocationState(AllocationState.ALLOCATED);
            //是否被分配过(只修改一次，过滤未分配)
            updateSql.set("distribut", true);
            entity.setDistribut(true);
        }
        String cmdText = updateSql.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", entity.getId(), Types.INTEGER);
        IPersister<NCustomerTask> taskPm = PersisterFactory.create();
        taskPm.executeNonQuery(cmdText, qps);
    }
}
