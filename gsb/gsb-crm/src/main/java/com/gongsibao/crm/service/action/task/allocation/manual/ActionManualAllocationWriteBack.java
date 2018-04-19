package com.gongsibao.crm.service.action.task.allocation.manual;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gongsibao.utils.NumberUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 分配回写
 */
public class ActionManualAllocationWriteBack implements IAction {

    @Override
    public void execute(ActionContext ctx) {

        List<NCustomerTask> taskList = (List<NCustomerTask>) ctx.getItem();
        Map<String, Object> status = ctx.getStatus();
        Integer supplierId = NumberUtils.toInt(status.get("toSupplier"));
        Integer departId = NumberUtils.toInt(status.get("toDepartmentId"));
        Integer ownerId = NumberUtils.toInt(status.get("toUserId"));
        for (NCustomerTask task : taskList) {
            UpdateBuilder updateSql = UpdateBuilder.getInstance();
            {
                updateSql.update("n_crm_customer_task");
                updateSql.set("supplier_id", supplierId);
                updateSql.set("department_id", departId);
                updateSql.set("owner_id", ownerId);
                updateSql.set("allocation_state", task.getAllocationState().getValue());
                updateSql.set("last_allocation_time", new Date());
                updateSql.set("allocation_type", NAllocationType.MANUAL.getValue());
                updateSql.set("distribut", true);
                updateSql.where("id =" + task.getId());
            }
            String cmdText = updateSql.toSQL();
            IPersister<NCustomerTask> pm = PersisterFactory.create();
            pm.executeNonQuery(cmdText, null);
        }

    }

}