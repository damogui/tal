package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.utils.DateUtils;

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

		return builder.toString();
	}

	@Override
	public int taskTransfer(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("supplier_id", supplierId);
			updateSql.set("department_id", departmentId);
			updateSql.set("owner_id", toUserId);
			updateSql.where("id=" + taskId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public Boolean transfer(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId) {
		// 任务转移
		String[] getTaskIdStrings = taskIds.split("_");
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("supplierId", supplierId);
		setMap.put("departmentId", departmentId);
		setMap.put("toUserId", toUserId);
		setMap.put("taskIds", taskIds);
		for (String item : getTaskIdStrings) {
			NCustomerTask entity = this.byId(Integer.valueOf(item));
			setMap.put("formUserId" + item, entity.getOwnerId());
			setMap.put("customerId" + item, entity.getCustomerId());
		}
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/transfer");
			ctx.setStatus(setMap);
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

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
	public Boolean allocation(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId, Integer allocationType) {

		String[] getTaskIdStrings = taskIds.split("_");
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("supplierId", supplierId);
		setMap.put("departmentId", departmentId);
		setMap.put("toUserId", toUserId);
		setMap.put("allocationType", allocationType);
		setMap.put("taskIds", taskIds);
		for (String item : getTaskIdStrings) {
			// 任务分配
			NCustomerTask entity = this.byId(Integer.valueOf(item));
			setMap.put("formUserId" + item, entity.getOwnerId());
			setMap.put("customerId" + item, entity.getCustomerId());
		}

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/allocation");
			ctx.setStatus(setMap);
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	@Override
	public Boolean follow(NCustomerTaskFoolow taskFoolow) {

		// 任务跟进
		NCustomerTask entity = this.byId(taskFoolow.getTaskId());
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/follow");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	@Override
	public Boolean regain(String taskIds, String content) {
		// 任务收回
		String[] getTaskIds = taskIds.split("_");
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("content", content);
		setMap.put("taskIds", taskIds);

		for (String item : getTaskIds) {
			NCustomerTask entity = this.byId(Integer.valueOf(item));
			setMap.put(item, entity);
		}
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/regain");
			ctx.setStatus(setMap);
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	@Override
	public Boolean release(Integer taskId) {

		// 任务释放
		NCustomerTask entity = this.byId(taskId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/release");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
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
			ctx.setPath("gsb/crm/customer/task/autoAllot");
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
}