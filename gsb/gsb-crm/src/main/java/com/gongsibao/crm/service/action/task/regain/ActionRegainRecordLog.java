package com.gongsibao.crm.service.action.task.regain;

import java.util.Map;

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
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 收回：记录日志
 */
public class ActionRegainRecordLog implements IAction {

    @Override
    public void execute(ActionContext ctx) {

        NCustomerTask task = (NCustomerTask) ctx.getItem();
        Map<String, Object> getMap = ctx.getStatus();
        String content = getMap.get("content").toString();
        Integer currentOwner = Integer.valueOf(ctx.getStatus().get("formUserId") == null ? "0" : ctx.getStatus().get("formUserId").toString());
        SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(currentOwner);

        // 1.保存流转日志
        INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
        NCustomerOperationLog changeLog = new NCustomerOperationLog();
        {
            changeLog.toNew();
            //changeLog.setFormUserId(currentOwner);
            changeLog.setContent(content);
            changeLog.setChangeType(ChangeType.RECYCLE);
            changeLog.setTaskId(task.getId());
            changeLog.setFormUserId((Integer) getMap.get("formUserId"));
            changeLog.setFormDepartmentId((Integer) getMap.get("formDepartmentId"));
            changeLog.setFormSupplierId((Integer) getMap.get("formSupplier"));

            changeLog.setSupplierId(organization.getSupplierId());
            changeLog.setDepartmentId(organization.getDepartmentId());
            changeLog.setCustomerId(task.getCustomerId());
            changeService.save(changeLog);
        }

        //2.保存通知日志
        String getContact = NCustomerContact.handleContact(task.getCustomer());
        String copyWriter = String.format("【收回提醒】您好，【%s】收回【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，收回原因为【%s】，请知悉",
                organization.getEmployeeName(), organization.getSalessmanName(), task.getName(), task.getCustomer().getRealName(), getContact, content);

        //通知业务员
        notify(task, organization, copyWriter, currentOwner);
        //通知直属领导
        if (organization.getDirectLeaderId() != null) {
            notify(task, organization, copyWriter, organization.getDirectLeaderId());
        }
        //通知服务商管理员
        if (organization.getAdminId() != null) {
            notify(task, organization, copyWriter, organization.getAdminId());
        }
    }

    /**
     * 发送通知
     *
     * @param task         任务实体
     * @param organization 业务员组织机构
     * @param copyWriter   通知文案
     * @param receivedId   接收人
     */
    private void notify(NCustomerTask task, SalesmanOrganization organization, String copyWriter, Integer receivedId) {
        INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
        NCustomerTaskNotify notify = new NCustomerTaskNotify();
        {
            notify.toNew();
            notify.setTaskId(task.getId());
            notify.setContent(copyWriter);
            notify.setType(NotifyType.WEIXIN);
            notify.setCustomerId(task.getCustomerId());
            notify.setSupplierId(organization.getSupplierId());
            notify.setDepartmentId(organization.getDepartmentId());
            notify.setReceivedId(receivedId);
            notifyService.save(notify);
        }
    }
}
