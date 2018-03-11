package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.crm.service.action.task.transfer.ProcessNoticeEnum;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.DateUtils;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

@Service
public class NCustomerTaskService extends SupplierPersistableService<NCustomerTask> implements INCustomerTaskService {

    public NCustomerTaskService() {
        super();
        this.type = NCustomerTask.class;
    }

    @Override
    public int updateInspectionState(Integer taskId, Integer selectValue, String getNote) {
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("n_crm_customer_task");
            updateSql.set("inspection_state", selectValue);
            updateSql.set("memoto", getNote);
            updateSql.where("id=" + taskId);
        }
        String cmdText = updateSql.toSQL();
        return this.pm.executeNonQuery(cmdText, null);
    }

    @Override
    public int taskRelease(Integer taskId) {
        String cmdText = "UPDATE n_crm_customer_task SET owner_id = NULL where id=" + taskId;
        return this.pm.executeNonQuery(cmdText, null);
    }

    @Override
    public NCustomerTask save(NCustomerTask entity) {

        //System.err.println("状态：" + entity.getEntityState());
        //这里有个BUG,修改状态时，entityState为New
        //暂时先强制设置，hw 2018-02-06
        if (entity.getId() != null && !entity.equals(0)) {

            entity.setEntityState(EntityState.Persist);
        }

        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/save");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        entity = (NCustomerTask) ctx.getItem();
        return entity;
    }

    @Override
    public NCustomerTask byId(Object id) {

        String selectFields = getSelectFullFields();
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects(selectFields);
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }

        NCustomerTask entity = this.queryFirst(oql);
        return entity;
    }

    private String getSelectFullFields() {

        StringBuilder builder = new StringBuilder();
        builder.append("NCustomerTask.*,");
        builder.append("NCustomerTask.customer.*,");
        builder.append("NCustomerTask.source.{id,name},");
        builder.append("NCustomerTask.consultWay.{id,name},");
        builder.append("NCustomerTask.supplier.*,");
        builder.append("NCustomerTask.department.*,");
        builder.append("NCustomerTask.owner.*,");
        builder.append("NCustomerTask.products.*,");
        builder.append("NCustomerTask.products.productCategory1.{id,name},");
        builder.append("NCustomerTask.products.productCategory2.{id,name},");
        builder.append("NCustomerTask.products.product.{id,name},");
        builder.append("NCustomerTask.products.province.{id,name},");
        builder.append("NCustomerTask.products.city.{id,name},");
        builder.append("NCustomerTask.products.county.{id,name},");
        builder.append("NCustomerTask.follows.*,");
        builder.append("NCustomerTask.notifys.*,");
        builder.append("NCustomerTask.changes.*,");
        builder.append("NCustomerTask.changes.formUser.{id,name},");
        builder.append("NCustomerTask.changes.toUser.{id,name},");
        builder.append("NCustomerTask.inspections.*,");

        return builder.toString();
    }

    @Override
    public Boolean batchTransfer(String[] taskIdArray, Integer supplierId, Integer departmentId, Integer toUserId) {
        //任务批量转移
        Map<ProcessNoticeEnum, Map<Integer, Integer>> noticeMap = transgerNotice(taskIdArray, supplierId, departmentId, toUserId);
        boolean isNotify = false;
        for (String taskId : taskIdArray) {

            this.transfer(Integer.valueOf(taskId), supplierId, departmentId, toUserId, noticeMap, isNotify);
            isNotify = true;
        }
        return true;
    }

    @Override
    public Boolean transfer(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId, Map<ProcessNoticeEnum, Map<Integer, Integer>> noticeMap, boolean isNotify) {
        //任务转移
        Map<String, Object> setMap = new HashMap<String, Object>();
        NCustomerTask entity = this.byId(taskId);

        setMap.put("noticeMap", noticeMap);
        //2.批量转移是否已经发送通知
        setMap.put("isNotify", isNotify);
        
        setMap.put("formUserId", entity.getOwnerId());
        setMap.put("formDepartmentId", entity.getDepartmentId());
        setMap.put("formSupplier", entity.getSupplierId());
        
        entity.setSupplierId(supplierId);
        entity.setDepartmentId(departmentId);
        entity.setOwnerId(toUserId);
        ActionManager action = new ActionManager();
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/transfer");
            ctx.setItem(entity);
            ctx.setStatus(setMap);
        }
        action.execute(ctx);
        return true;
    }

    /**
     * 生成转移类型，以及统计类型的Id和对应的数量
     *
     * @param taskIdArray
     * @param supplierId
     * @param departmentId
     * @param toUserId
     * @return
     */
    private Map<ProcessNoticeEnum, Map<Integer, Integer>> transgerNotice(String[] taskIdArray, Integer supplierId, Integer departmentId, Integer toUserId) {
        Map<ProcessNoticeEnum, Map<Integer, Integer>> reusMap = new HashMap<>();
        Map<Integer, Integer> ownerMap = new HashMap<>();
        Map<Integer, Integer> seasMap = new HashMap<>();
        for (String taskId : taskIdArray) {
            NCustomerTask entity = this.byId(taskId);
            if (entity.getOwnerId() != null) {
                //业务员转移
                if (ownerMap.containsKey(entity.getOwnerId())) {
                    ownerMap.put(entity.getOwnerId(), ownerMap.get(entity.getOwnerId()).intValue() + 1);
                } else {
                    ownerMap.put(entity.getOwnerId(), 1);
                }
                if (toUserId != null) {
                    reusMap.put(ProcessNoticeEnum.salesmanTosalesman, ownerMap);
                } else if (toUserId == null && departmentId != null) {
                    reusMap.put(ProcessNoticeEnum.salesmanToseas, ownerMap);
                } else if (toUserId == null && departmentId == null && supplierId != null) {
                    reusMap.put(ProcessNoticeEnum.salesmanToseas, ownerMap);
                }
            } else if (entity.getOwnerId() == null && entity.getDepartmentId() != null) {
                //部门转移
                if (seasMap.containsKey(entity.getDepartmentId())) {
                    seasMap.put(entity.getDepartmentId(), seasMap.get(entity.getDepartmentId()).intValue() + 1);
                } else {
                    seasMap.put(entity.getDepartmentId(), 1);
                }
                if (toUserId != null) {
                    reusMap.put(ProcessNoticeEnum.seasTosalesman, seasMap);
                } else if (toUserId == null && departmentId != null) {
                    reusMap.put(ProcessNoticeEnum.seasToseas, seasMap);
                } else if (toUserId == null && departmentId == null && supplierId != null) {
                    reusMap.put(ProcessNoticeEnum.salesmanToseas, seasMap);
                }
            }
        }
        return reusMap;
    }


    /**
     * 抽查异常
     *
     * @param taskId  任务Id
     * @param state   1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
     * @param content
     * @param type    1-"抽查",2-"处理"
     * @return
     */
    @Override
    public Boolean abnormal(Integer taskId, Integer state, String content, Integer type) {
        // 抽查异常
        NCustomerTask entity = this.byId(taskId);
        entity.setLastInspectionContent(content);
        Map<String, Object> setMap = new HashMap<String, Object>();
        setMap.put("state", state);
        setMap.put("type", type);
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/abnormal");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
            ctx.setStatus(setMap);
        }

        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public Boolean batchAllocation(String[] taskIdArray, Integer supplierId, Integer departmentId, Integer toUserId) {
        //任务批量分配
        int taskIdCount = taskIdArray.length;
        boolean isNotify = false;
        for (String taskId : taskIdArray) {
            this.allocation(Integer.valueOf(taskId), supplierId, departmentId, toUserId, taskIdCount, isNotify);
            isNotify = true;
        }
        return true;
    }

    @Override
    public Boolean allocation(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId, int alloCount, boolean isNotify) {
        //任务分配
        Map<String, Object> setMap = new HashMap<String, Object>();
        NCustomerTask entity = this.byId(taskId);
        setMap.put("formDepartmentId", entity.getDepartmentId());
        setMap.put("formSupplier", entity.getSupplierId());
        //区别批量分配
        setMap.put("alloCount", alloCount);
        //批量分配是否已经发送通知
        setMap.put("isNotify", isNotify);
        entity.setSupplierId(supplierId);
        entity.setDepartmentId(departmentId);
        entity.setOwnerId(toUserId);
        ActionManager action = new ActionManager();
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/allocation/manual");
            ctx.setItem(entity);
            ctx.setStatus(setMap);
        }
        action.execute(ctx);
        return true;
    }

    @Override
    public Boolean follow(NCustomerTaskFoolow taskFoolow, Integer originalQualityId) {
        //任务跟进
        Map<String, Object> setMap = new HashMap<String, Object>();
        setMap.put("originalQualityId", originalQualityId);
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/follow");
            ctx.setItem(taskFoolow);
            ctx.setStatus(setMap);
            ctx.setState(taskFoolow.getEntityState());
        }

        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public Boolean batchRegain(String[] taskIdArray, String content) {

        //任务批量收回
        for (String taskId : taskIdArray) {

            this.regain(Integer.valueOf(taskId), content);
        }
        return true;
    }

    @Override
    public Boolean regain(Integer taskId, String content) {
        //任务收回
        ActionManager action = new ActionManager();
        Map<String, Object> setMap = new HashMap<String, Object>();
        setMap.put("content", content);
        
        NCustomerTask entity = this.byId(taskId);
        
        setMap.put("formUserId", entity.getOwnerId());
        setMap.put("formDepartmentId", entity.getDepartmentId());
        setMap.put("formSupplier", entity.getSupplierId());
        
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/regain");
            ctx.setItem(entity);
            ctx.setStatus(setMap);
        }
        action.execute(ctx);
        return true;
    }

    @Override
    public Boolean rollback(Integer taskId, String content) {
        // 任务回退
        NCustomerTask entity = this.byId(taskId);
        Map<String, Object> setMap = new HashMap<String, Object>();
        setMap.put("content", content);

        setMap.put("formUserId", entity.getOwnerId());
        setMap.put("formDepartmentId", entity.getDepartmentId());
        setMap.put("formSupplier", entity.getSupplierId());
        
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/rollback");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
            ctx.setStatus(setMap);
        }

        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public int autoAllot(Integer taskId) {
        // 自动分配
        NCustomerTask entity = this.byId(taskId);
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/task/allocation/auto");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }

        ActionManager action = new ActionManager();
        action.execute(ctx);
        return 0;
    }

    @Override
    public Map<Integer, Integer> getTaskCountByEmployeeIdList(List<Integer> employeeIdList, Integer type) {
        String employeeIdStr = StringManager.join(",", employeeIdList);
        Map<Integer, Integer> resMap = new HashMap<Integer, Integer>();
        List<String> whereList = new ArrayList<String>();
        whereList.add(" owner_id in(" + employeeIdStr + ") ");
        Date date = new Date();
        if (type.equals(0)) {// 当日已分配数
            // 时间格式：2008-08-08
            String dateString = DateUtils.formatDate(date);
            String dateStart = dateString + " 00:00:00";
            String dateEnd = dateString + " 23:59:59";
            whereList.add(" last_allocation_time >= '" + dateStart + "' ");
            whereList.add(" last_allocation_time <= '" + dateEnd + "' ");
        }
        if (type.equals(1)) {// 当周已分配数
            // 时间格式：2008-08-08
            String dateStart = DateUtils.formatDate(DateUtils.getMondayOfWeek(date)) + " 00:00:00";
            String dateEnd = DateUtils.formatDate(DateUtils.getSundayOfWeek(date)) + " 23:59:59";
            whereList.add(" last_allocation_time >= '" + dateStart + "' ");
            whereList.add(" last_allocation_time <= '" + dateEnd + "' ");
        }
        if (type.equals(2)) {// XAB类已分配客户数
            whereList.add(" intention_category in(1,2,5) ");
        }
        Oql oql = new Oql();
        {
            oql.setType(NCustomerTask.class);
            oql.setSelects("*");
            oql.setFilter(StringManager.join(" and ", whereList));
        }

        List<NCustomerTask> reslist = this.pm.queryList(oql);
        for (Integer employeeId : employeeIdList) {
            int count = 0;
            for (NCustomerTask nCustomerTask : reslist) {
                if (nCustomerTask.getOwnerId().equals(employeeId)) {
                    count = count + 1;
                }
            }
            resMap.put(employeeId, count);
        }
        return resMap;
    }

    public NCustomerTask newInstance() {
        NCustomerTask entity = super.newInstance();
        Supplier supplier = SupplierSessionManager.getSupplier();
        if (supplier != null) {
            entity.setSupplierId(supplier.getId());
            entity.setSupplier(supplier);
        }

        SupplierDepartment department = SupplierSessionManager.getDepartment();
        if (department != null) {
            entity.setDepartmentId(department.getId());
            entity.setDepartment(department);
        }

        // 业务员处理,只有是业务员的才有
        Integer ownerId = SupplierSessionManager.getSalesmanEmployeeId();
        if (ownerId != null) {
            entity.setAllocationType(NAllocationType.MANUAL);
            entity.setOwnerId(ownerId);
            entity.setOwner(UserPermissionManager.getUserPermission().getEmployee());
        }
        return entity;
    }

    @Override
    public Map<Integer, Integer> getAssignmentCountBySeas() {
        Map<Integer, Integer> resMap = new HashMap<Integer, Integer>();
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("NCustomerTask.*,NCustomerTask.customer.*,");
            oql.setFilter("allocationState = ?");
            oql.getParameters().add("@allocationState", AllocationState.WAIT.getValue(), Types.INTEGER);
        }
        List<NCustomerTask> reslist = this.pm.queryList(oql);
        ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
        for (NCustomerTask item : reslist) {
            Integer leaderId = salesmanService.getLeaderId(item.getSupplierId(), item.getDepartmentId());
            SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(leaderId);

            //统计部门，及部门下的待分配任务的数量
            if (orgaForm.getEmployeeId() != null) {
                if (resMap.containsKey(orgaForm.getEmployeeId())) {
                    resMap.put(orgaForm.getEmployeeId(), resMap.get(leaderId).intValue() + 1);
                } else {
                    resMap.put(orgaForm.getEmployeeId(), 1);
                }
            }

            //统计服务商，及服务商下的待分配任务的数量
            if (orgaForm.getAdminId() != null) {
                if (resMap.containsKey(orgaForm.getAdminId())) {
                    resMap.put(orgaForm.getAdminId(), resMap.get(orgaForm.getAdminId()).intValue() + 1);
                } else {
                    resMap.put(orgaForm.getAdminId(), 1);
                }
            }
        }
        return resMap;
    }

    @Override
    public List<NCustomerTask> getUnFoolowList(Date time) {
        String getTime = DateUtils.formatDate(time, "yyyy-MM-dd");
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("NCustomerTask.*,NCustomerTask.customer.*,");
            oql.setFilter("next_foolow_time = ?");
            oql.getParameters().add("@next_foolow_time", getTime, Types.DATE);
        }
        List<NCustomerTask> taskList = this.pm.queryList(oql);
        return taskList;
    }

    @Override
    public List<NCustomerTask> getTimeOutList(Date time) {
        String getTime = DateUtils.formatDate(time, "yyyy-MM-dd");
        List<String> whereList = new ArrayList<String>();
        whereList.add(" next_foolow_time < '" + getTime + "'");
        whereList.add(" foolowStatus = 3");
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("NCustomerTask.*,NCustomerTask.customer.*,");
            oql.setFilter(StringManager.join(" and ", whereList));
        }
        List<NCustomerTask> taskList = this.pm.queryList(oql);
        return taskList;
    }

    @Override
    public List<NCustomerTask> getNoStartList(Date time) {
        String getTime = DateUtils.formatDate(time, "yyyy-MM-dd HH:mm:ss");
        List<String> whereList = new ArrayList<String>();
        whereList.add(" distribut =1");
        whereList.add(" foolow_status = 6");
        whereList.add(" owner_id is not NULL");
        whereList.add(" last_allocation_time >= '" + getTime + "' ");
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("NCustomerTask.*,NCustomerTask.customer.*,");
            oql.setFilter(StringManager.join(" and ", whereList));
        }
        List<NCustomerTask> taskList = this.pm.queryList(oql);
        return taskList;
    }

    @Override
    public int updateAllocationState(NCustomerTask task) {

        // 验证非空
        if (task == null || NumberUtils.toInt(task.getId()) == 0) {
            throw new BusinessException("该任务不存在！");
        }
        //服务商id
        Integer supplierId = NumberUtils.toInt(task.getSupplierId());
        //部门id
        Integer departmentId = NumberUtils.toInt(task.getDepartmentId());
        //业务员id
        Integer ownerId = NumberUtils.toInt(task.getOwnerId());

        // 当没有分配到人，也没有分配到部门，则不改变状态
        if (supplierId.equals(0) && departmentId.equals(0) && ownerId.equals(0)) {
            return 0;
        }

        //获取当前登录人信息
        UserPermission permission = UserPermissionManager.getUserPermission();

        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("n_crm_customer_task");
            updateSql.set("allocation_state", AllocationState.WAIT.getValue());
            // 跟进状态改为【未启动】
            if (task.getFoolowStatus() == null)
                updateSql.set("foolow_status", CustomerFollowStatus.UNSTART.getValue());
            // 跟新最后分配时间
            updateSql.set("last_allocation_time", DateUtils.getDateStr(new Date()));
            // 最后分配人Id（机器分配，默认写0）
            updateSql.set("last_allocation_user_id", task.getAllocationType().equals(NAllocationType.MANUAL) ? permission.getEmployee().getId() : 0);
            updateSql.where("id=?");
        }

        // 状态改为【待分配】
        task.setAllocationState(AllocationState.WAIT);
        // 修改人,自动分配时默认是0
        task.setLastAllocationUserId(task.getAllocationType().equals(NAllocationType.MANUAL) ? permission.getEmployee().getId() : 0);

        if (task.getFoolowStatus() == null) {
            task.setFoolowStatus(CustomerFollowStatus.UNSTART);
        }
        task.setLastAllocationTime(new Date());

        if (NumberUtils.toInt(task.getSupplierId()) != 0) {
            // 状态改为【已分配-服务商】
            updateSql.set("allocation_state", AllocationState.ALLOCATED_Supplier.getValue());
            task.setAllocationState(AllocationState.ALLOCATED_Supplier);
            //是否被分配过(只修改一次，过滤未分配)
            updateSql.set("distribut", true);
            task.setDistribut(true);
        }

        if (NumberUtils.toInt(task.getDepartmentId()) != 0) {
            // 状态改为【已分配-部门】
            updateSql.set("allocation_state", AllocationState.ALLOCATED_Department.getValue());
            task.setAllocationState(AllocationState.ALLOCATED_Department);
            //是否被分配过(只修改一次，过滤未分配)
            updateSql.set("distribut", true);
            task.setDistribut(true);
        }

        if (NumberUtils.toInt(task.getOwnerId()) != 0) {
            // 状态改为【已分配-业务员】
            updateSql.set("allocation_state", AllocationState.ALLOCATED.getValue());
            task.setAllocationState(AllocationState.ALLOCATED);
            //是否被分配过(只修改一次，过滤未分配)
            updateSql.set("distribut", true);
            task.setDistribut(true);
        }

        String cmdText = updateSql.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", task.getId(), Types.INTEGER);
        int res = this.pm.executeNonQuery(cmdText, qps);
        return res;
    }
}