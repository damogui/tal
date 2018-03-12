package com.gongsibao.crm.service.action.task.allocation.auto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.NumberUtils;

/**
 * @author zhangchao 写入跟进记录
 */
public class ActionAutoAllocationRecordLog implements IAction {

    // 流转日志
    INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
    // 通知日志
    INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
    // 登录人
    IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);

    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);


    @Override
    public void execute(ActionContext ctx) {
        NCustomerTask entity = (NCustomerTask) ctx.getItem();
        Map<String, Object> statusMap = ctx.getStatus();
        Integer FormUserId = (Integer) statusMap.get("formUserId");
        // 添加日志
        addRecord(entity, FormUserId);
    }

    // 添加日志
    private void addRecord(NCustomerTask entity, Integer FormUserId) {

        // 业务员id
        Integer receiveUserId = entity.getOwnerId();
        // 领导id
        Integer receiveleaderUserId = 0;

        if (entity.getAllocationType().equals(NAllocationType.AUTO) && entity.getOwnerId() != null && !entity.getOwnerId().equals(0)) {
            // 添加流转日志
            addNCustomerChange(entity, FormUserId, getChangelog(1, entity, FormUserId));
            // 添加通知日志
            addNCustomerTaskNotify(entity, receiveUserId, getNotifyLog(1, entity, 0, 1));
            // 领导id
            receiveleaderUserId = getLeaderId(entity.getSupplierId(), entity.getDepartmentId());
            if (receiveleaderUserId != 0) {
                // 添加通知日志
                addNCustomerTaskNotify(entity, receiveleaderUserId, getNotifyLog(1, entity, 1, 1));
            }
        }

        // 当是分配方式是【手动】时，则通知售前负责人
        if (entity.getAllocationType().equals(NAllocationType.MANUAL)) {
            // 售前负责人
            Salesman preSeller = getPreSeller();
            receiveleaderUserId = preSeller == null ? 0 : preSeller.getEmployeeId();
            // 添加通知日志(通知售前负责人)
            addNCustomerTaskNotify(entity, receiveUserId, getNotifyLog(3, entity, 1, 3));
            // TODO 给所有的售前发通知
        }
        // 当是分配方式是【半自动】、分配到部门公海时，
        if (entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
            if (entity.getSupplierId() != null && !entity.getSupplierId().equals(0)) {
                // 领导id
                receiveleaderUserId = getLeaderId(entity.getSupplierId(), entity.getDepartmentId());
                // 添加通知日志
                addNCustomerTaskNotify(entity, receiveleaderUserId, getNotifyLog(2, entity, 1, 2));
            }
        }
    }

    private void addNCustomerChange(NCustomerTask entity, Integer FormUserId, String content) {
        if (StringManager.isNullOrEmpty(content))
            return;
        if (NumberUtils.toInt(entity.getOwnerId()) == 0)
            return;

        //来自业务员信息
        Salesman salesman = null;
        if (NumberUtils.toInt(FormUserId) != 0) {
            salesman = salesmanService.byEmployeeId(FormUserId);
        }
        // 1.保存流转日志
        NCustomerOperationLog changeEntity = new NCustomerOperationLog();
        changeEntity.toNew();// 标示下类型，有多种
        changeEntity.setFormUserId(FormUserId);
        changeEntity.setFormDepartmentId(salesman == null ? null : salesman.getDepartmentId());
        changeEntity.setFormSupplierId(salesman == null ? null : salesman.getSupplierId());
        changeEntity.setToUserId(entity.getOwnerId());
        changeEntity.setChangeType(ChangeType.ALLOCATION);
        changeEntity.setTaskId(entity.getId());
        changeEntity.setSupplierId(entity.getSupplierId());
        changeEntity.setDepartmentId(entity.getDepartmentId());
        changeEntity.setCustomerId(entity.getCustomerId());
        changeEntity.setContent(content);
        changeService.save(changeEntity);
    }

    // 添加【通知日志】
    private void addNCustomerTaskNotify(NCustomerTask entity, Integer receiveUserId, String content) {
        if (StringManager.isNullOrEmpty(content))
            return;
        if (NumberUtils.toInt(receiveUserId) == 0)
            return;
        NCustomerTaskNotify notifyEntity = new NCustomerTaskNotify();
        notifyEntity.toNew();
        notifyEntity.setCustomerId(entity.getCustomerId());
        notifyEntity.setTaskId(entity.getId());
        notifyEntity.setType(NotifyType.SYSTEM);
        notifyEntity.setSupplierId(entity.getSupplierId());
        notifyEntity.setDepartmentId(entity.getDepartmentId());
        notifyEntity.setReceivedId(receiveUserId);
        notifyEntity.setContent(content);
        notifyService.save(notifyEntity);
    }

    // 获取领导id
    private Integer getLeaderId(Integer supplierId, Integer departmentId) {
        Integer resId = 0;
        List<Salesman> tempList = getListBydepartmentId(supplierId, departmentId);
        if (CollectionUtils.isNotEmpty(tempList)) {
            resId = tempList.get(0).getEmployeeId();
        }
        return resId;
    }

    // 获取【流转日志】文案：1转移
    private String getChangelog(Integer type, NCustomerTask entity, Integer FormUserId) {
        String res = "";
        switch (type) {
            case 1:
                if (entity.getCustomer() == null)
                    return "";
                String customerName = StringUtils.trimToEmpty(entity.getCustomer().getRealName());
                String contract = getContractStr(entity);
                String zyString = "";
                if (!FormUserId.equals(0)) {
                    Employee employee = employeeService.byId(FormUserId);
                    if (employee != null)
                        zyString = "从【" + employee.getName() + "】";
                }
                res = "【转移提醒】您好，【" + entity.getCreator() + "】" + zyString + "转移给您1个任务，任务名称【" + StringUtils.trimToEmpty(entity.getName()) + "】，" + "客户名称【" + customerName + "】，" + "客户联系方式【" + contract + "】，请及时跟进";
                break;
            default:
                res = "";
                break;
        }
        return res;
    }

    // 获取联系方式
    private String getContractStr(NCustomerTask entity) {
        String contract = !StringManager.isNullOrEmpty(entity.getCustomer().getMobile()) ? entity.getCustomer().getMobile() : !StringManager.isNullOrEmpty(entity.getCustomer().getTelephone()) ? entity.getCustomer().getTelephone() : !StringManager.isNullOrEmpty(entity.getCustomer().getWeixin()) ? entity.getCustomer().getWeixin() : !StringManager.isNullOrEmpty(entity.getCustomer().getQq()) ? entity.getCustomer().getQq() : "";
        if (!StringManager.isNullOrEmpty(entity.getCustomer().getMobile())) {
            contract = contract.substring(0, 3) + "****" + contract.substring(7);
        } else {
            StringBuffer xxStr = new StringBuffer();
            int xxcount = (contract.length()) - 4 <= 0 ? 0 : contract.length();
            for (int i = 0; i < xxcount; i++) {
                xxStr.append("*");
            }
            contract = contract.substring(0, 2) + xxStr + contract.substring(contract.length() - 2);
        }
        return contract;
    }

    /**
     * 通知
     *
     * @param type           1分配成功业务员通知 2分配成功部门（公海）通知 3分配失败提醒(提醒售前和售前负责人)
     * @param entity
     * @param isLeader       0不是领导 1是领导
     * @param allocationType 1自动分配 2半自动分配 3手动分配
     * @return
     */
    private String getNotifyLog(Integer type, NCustomerTask entity, Integer isLeader, Integer allocationType) {
        String res = "";
        if (entity.getCustomer() == null)
            return "";
        String contract = getContractStr(entity);
        String taskName = StringUtils.trimToEmpty(entity.getName());
        String customeName = StringUtils.trimToEmpty(entity.getCustomer().getRealName());
        switch (type) {
            case 1: // 分配成功业务员通知
                if (isLeader.equals(0) && allocationType.equals(0)) {
                    res = "【分配提醒】您好，1个新任务分配给您，任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时跟进";
                }
                if (isLeader.equals(1) && allocationType.equals(0)) {
                    res = "【分配提醒】您好，1个新任务分配给您，任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时安排跟进";
                }

                if (isLeader.equals(0) && allocationType.equals(1)) {
                    res = "【分配提醒】您好，1个新任务分配给您，任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时跟进";
                }
                if (isLeader.equals(1) && allocationType.equals(1)) {
                    res = "【分配提醒】您好，1个新任务分配给您，任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时安排跟进";
                }
                break;
            case 2: // 分配成功部门（公海）通知
                if (isLeader.equals(1) && allocationType.equals(2)) {
                    res = "【分配提醒】您好，1个新任务待您分配，" + "任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时分配跟进";
                }
                break;
            case 3: // 分配失败提醒
                if (isLeader.equals(0) && allocationType.equals(3)) {
                    res = "【分配失败提醒】您好，您创建的1个新任务分配失败，任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时处理";
                }
                if (isLeader.equals(1) && allocationType.equals(3)) {
                    res = "【分配失败提醒】您好，" + "【" + entity.getCreator() + "】创建的1个新任务分配失败，" + "任务名称【" + taskName + "】，" + "客户名称【" + customeName + "】，" + "客户联系方式【" + contract + "】，请及时安排处理";
                }
                break;
            default:
                break;
        }
        return res;
    }

    // 获取售前负责人【暂时默认写死【刘立丹】】
    private Salesman getPreSeller() {
        Oql oql = new Oql();
        {
            oql.setType(Salesman.class);
            oql.setSelects("*");
            oql.setFilter("employee.name='刘立丹'");
        }
        IPersister<Salesman> salesrPm = PersisterFactory.create();
        return salesrPm.queryFirst(oql);
    }

    // 根据服务商id和部门id获取业务员【默认部门只有一个领导】
    private List<Salesman> getListBydepartmentId(Integer supplierId, Integer departmentId) {

        List<String> whereStrList = new ArrayList<String>();

        whereStrList.add(" is_leader = 1 ");// 是否主管：是

        if (supplierId != null && !supplierId.equals(0)) {
            whereStrList.add(" supplier_id = " + supplierId + " ");
        }
        if (departmentId != null && !departmentId.equals(0)) {
            whereStrList.add(" department_id = " + departmentId + " ");
        }
        String whereString = StringManager.join(" and ", whereStrList);
        Oql oql = new Oql();
        {
            oql.setType(Salesman.class);
            oql.setSelects("*");
            oql.setFilter(whereString);
        }
        IPersister<Salesman> salesrPm = PersisterFactory.create();
        return salesrPm.queryList(oql);
    }

}
