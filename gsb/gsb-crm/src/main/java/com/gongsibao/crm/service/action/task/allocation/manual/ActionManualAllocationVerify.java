package com.gongsibao.crm.service.action.task.allocation.manual;

import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.core.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author hw
 * 分配校验
 */
public class ActionManualAllocationVerify implements IAction {

    @Override
    public void execute(ActionContext ctx) {

        List<NCustomerTask> taskList = (List<NCustomerTask>) ctx.getItem();
        Map<String, Object> status = ctx.getStatus();
        Integer supplierId = NumberUtils.toInt(status.get("toSupplier"));
        Integer departId = NumberUtils.toInt(status.get("toDepartmentId"));
        Integer ownerId = NumberUtils.toInt(status.get("toUserId"));

        if (CollectionUtils.isEmpty(taskList)) {
            throw new BusinessException("该商机不存在");
        }

        for (NCustomerTask taskEntity : taskList) {
            //1.根据选择的服务商、部门是否为空，判断分配状态
            if (supplierId != null && ownerId == null && departId == null) {
                taskEntity.setAllocationState(AllocationState.ALLOCATED_Supplier);
            }
            if (departId != null && ownerId == null) {
                taskEntity.setAllocationState(AllocationState.ALLOCATED_Department);
            }
            //2.服务商和部门如果不选择，此时根据业务员Id,获取相应的服务商和部门
            if (ownerId != null) {
                taskEntity.setAllocationState(AllocationState.ALLOCATED);
                SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(ownerId);

                if (departId == null) {

                    taskEntity.setDepartmentId(organization.getDepartmentId());
                }
                if (supplierId == null) {

                    taskEntity.setSupplierId(organization.getSupplierId());
                }
            }
        }
    }
}