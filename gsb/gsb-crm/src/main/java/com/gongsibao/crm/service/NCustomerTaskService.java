package com.gongsibao.crm.service;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.utils.DateUtils;
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
		builder.append("NCustomerTask.inspections.*,");
		
		return builder.toString();
	}
	

	@Override
	public Boolean batchTransfer(String[] taskIdArray, Integer supplierId, Integer departmentId, Integer toUserId) {

		for (String taskId : taskIdArray) {

			this.transfer(Integer.valueOf(taskId), supplierId, departmentId, toUserId);
		}
		return true;
		
	}

	@Override
	public Boolean transfer(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId) {
		Map<String, Object> setMap = new HashMap<String, Object>();
		NCustomerTask entity = this.byId(taskId);
		setMap.put("formUserId", entity.getOwnerId());
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
	 * 抽查异常
	 * 
	 * @param taskId 	任务Id
	 * @param state 1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
	 * @param content
	 * @param type	1-"抽查",2-"处理"
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

		for (String taskId : taskIdArray) {

			this.allocation(Integer.valueOf(taskId), supplierId, departmentId, toUserId);
		}
		return true;
	}

	@Override
	public Boolean allocation(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId) {

		Map<String, Object> setMap = new HashMap<String, Object>();
		NCustomerTask entity = this.byId(taskId);
		setMap.put("formUserId", entity.getOwnerId());
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
	public Boolean follow(Integer taskId, Integer qualityId,String nextTime,BigDecimal amount,String content) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		NCustomerTask entity = this.byId(taskId);
		// 任务跟进
		try {
			Date setFollowTimeDate = sdf.parse(nextTime);
			entity.setQualityId(qualityId);
			setMap.put("lastFollowTime", entity.getNextFoolowTime() == null ? new Date() : entity.getNextFoolowTime());	
			entity.setNextFoolowTime(nextTime == null ? new Date() : setFollowTimeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setLastContent(content);
		
		setMap.put("amount", amount == null ? 0 : amount);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/follow");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
			ctx.setStatus(setMap);
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}
	
	@Override
	public Boolean batchRegain(String[] taskIdArray, String content) {

		// 任务收回
		for (String taskId : taskIdArray) {

			this.regain(Integer.valueOf(taskId), content);
		}
		return true;
	}

	@Override
	public Boolean regain(Integer taskId, String content) {

		ActionManager action = new ActionManager();
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("content", content);

		NCustomerTask entity = this.byId(taskId);
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
		if(supplier != null){
			entity.setSupplierId(supplier.getId());
			entity.setSupplier(supplier);
		}
		
		SupplierDepartment department = SupplierSessionManager.getDepartment();
		if(department != null){
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
}