package com.gongsibao.crm.service.action.task.allocation.manual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.gongsibao.utils.NumberUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 分配：保存日志
 */
public class ActionManualAllocationRecordLog implements IAction {

    // 1.保存流转日志
    INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);

    @Override
    public void execute(ActionContext ctx) {

        List<NCustomerTask> taskList = (List<NCustomerTask>) ctx.getItem();
        Map<String, Object> status = ctx.getStatus();
        Integer supplierId = NumberUtils.toInt(status.get("toSupplier"));
        Integer departId = NumberUtils.toInt(status.get("toDepartmentId"));
        Integer ownerId = NumberUtils.toInt(status.get("toUserId"));
        Integer alloCount = NumberUtils.toInt(status.get("alloCount"));

        boolean isNotify = (boolean) status.get("isNotify");

        //1.保存流转日志
        addOperationLog(taskList, supplierId, departId, ownerId);
        // 2.通知日志
        if (alloCount > 1) {
            if (!isNotify) {
                batchAllocation(taskList, alloCount, supplierId, departId, ownerId);
            }
        } else {
            allocation(taskList.get(0), supplierId, departId, ownerId);
        }
    }


    //保存流转日志
    private void addOperationLog(List<NCustomerTask> taskList, Integer supplierId, Integer departId, Integer ownerId) {
        List<NCustomerOperationLog> logList = new ArrayList<>();
        for (NCustomerTask task : taskList) {
            NCustomerOperationLog changeLog = new NCustomerOperationLog();
            {
                changeLog.toNew();
                changeLog.setTaskId(task.getId());
                changeLog.setChangeType(ChangeType.ALLOCATION);
                changeLog.setCustomerId(task.getCustomerId());
                changeLog.setFormDepartmentId(task.getDepartmentId());
                changeLog.setFormSupplierId(task.getSupplierId());
                changeLog.setToUserId(ownerId);
                changeLog.setSupplierId(supplierId);
                changeLog.setDepartmentId(departId);
            }
        }
        changeService.saves(logList);
    }

    /**
     * 单商机分配
     *
     * @param task
     */
    private void allocation(NCustomerTask task, Integer toSupplier, Integer toDepartmentId, Integer toUserId) {
        String getContact = NCustomerContact.handleContact(task.getCustomer());
        String copyWriter = null;

        ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
        List<NCustomerTask> taskList = Arrays.asList(task);
        //业务员为空，通知服务商管理员或部门主管
        if (toUserId == 0) {
            String.format("【分配提醒】您好，1个新商机待您分配，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时分配跟进",
                    task.getName(), task.getCustomer().getRealName(), getContact);

            Integer leaderId = salesmanService.getLeaderId(toSupplier, toDepartmentId);
            sendNotify(taskList, copyWriter, toSupplier, toDepartmentId, leaderId);
        } else {
            //通知业务员文案
            copyWriter = String.format("【分配提醒】您好，1个新商机分配给您，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
                    task.getName(), task.getCustomer().getRealName(), getContact);
            sendNotify(taskList, copyWriter, toSupplier, toDepartmentId, toUserId);
            //通知业务员的一二级领导
            SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(toUserId);
            String leaderCopyWriter = String.format("【分配提醒】您好，1个新商机分配给【%s】，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时安排跟进",
                    organization.getEmployeeName(), task.getName(), task.getCustomer().getRealName(), getContact);

            if (organization.getDirectLeaderId() != null) {
                sendNotify(taskList, leaderCopyWriter, toSupplier, toDepartmentId, organization.getDirectLeaderId());
            }
            if (organization.getSuperiorLeaderId() != null) {
                sendNotify(taskList, leaderCopyWriter, toSupplier, toDepartmentId, organization.getSuperiorLeaderId());
            }
        }
    }

    /**
     * 批量分配
     *
     * @param taskList
     */
    private void batchAllocation(List<NCustomerTask> taskList, int alloCount, Integer toSupplier, Integer toDepartmentId, Integer toUserId) {
        ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
        //业务员为空，通知服务商管理员或部门主管
        if (toUserId == 0) {
            String copyWriter = String.format("【批量分配提醒】您好，%s个新商机待您分配，请及时分配跟进", alloCount);
            Integer leaderId = salesmanService.getLeaderId(toSupplier, toDepartmentId);
            sendNotify(taskList, copyWriter, toSupplier, toDepartmentId, leaderId);
        } else {
            SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(toUserId);

            //通知业务员文案
            String copyWriter = String.format("【批量分配提醒】您好，【%s】分配%s个商机给您，请及时跟进",
                    organization.getDirectLeaderName(), alloCount);
            sendNotify(taskList, copyWriter, toSupplier, toDepartmentId, toUserId);
            //通知业务员的一二级领导

            String leaderCopyWriter = String.format("【批量分配提醒】您好，【%s】分配%s个商机给【%s】，请及时安排跟进",
                    organization.getDirectLeaderName(), alloCount, organization.getEmployeeName());

            if (organization.getDirectLeaderId() != null) {
                sendNotify(taskList, leaderCopyWriter, toSupplier, toDepartmentId, organization.getDirectLeaderId());
            }
            if (organization.getSuperiorLeaderId() != null) {
                sendNotify(taskList, leaderCopyWriter, toSupplier, toDepartmentId, organization.getSuperiorLeaderId());
            }
        }


    }

    /**
     * 发送通知
     *
     * @param taskList   商机实体
     * @param copyWriter 通知文案
     * @param receivedId 接收人
     */
    private void sendNotify(List<NCustomerTask> taskList, String copyWriter, Integer toSupplier, Integer toDepartmentId, Integer receivedId) {
        INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
        List<NCustomerTaskNotify> notifyList = new ArrayList<>();
        for (NCustomerTask task : taskList) {
            NCustomerTaskNotify notify = new NCustomerTaskNotify();
            {
                notify.toNew();
                notify.setTaskId(task.getId());
                notify.setContent(copyWriter);
                notify.setCustomerId(task.getCustomerId());
                notify.setSupplierId(toSupplier);
                notify.setDepartmentId(toDepartmentId);
                notify.setReceivedId(receivedId);
            }
            notifyList.add(notify);
        }
        notifyService.saves(notifyList);
    }
}
