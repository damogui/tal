package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

@Service
public class NCustomerTaskService extends SupplierPersistableService<NCustomerTask> implements INCustomerTaskService {
	public NCustomerTaskService() {
		super();
		this.type = NCustomerTask.class;
	}

	@Override
	public int updateInspectionState(Integer taskId, Integer selectValue,
			String getNote) {
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
		String cmdText = "UPDATE n_crm_customer_task SET owner_id = NULL where id="+taskId;
		return this.pm.executeNonQuery(cmdText, null);
	}
	@Override
	public NCustomerTask save(NCustomerTask entity){
		
		entity = super.save(entity);
		
		//这里可能2次查询，需要优化
		entity = this.byId(entity.getId());
		return entity;
	}

	@Override
	public NCustomerTask byId(Object id){
		
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
	public int taskTransfer(Integer taskId,Integer supplierId,Integer departmentId,Integer toUserId) {
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
		//任务转移
		String [] getTaskIdStrings = taskIds.split("_");
		Map<String,Object> setMap = new HashMap<String,Object>();
		setMap.put("supplierId", supplierId);
		setMap.put("departmentId", departmentId);
		setMap.put("toUserId", toUserId);
		setMap.put("taskIds", taskIds);
		for (String item : getTaskIdStrings) {
			NCustomerTask entity = this.byId(Integer.valueOf(item));
			setMap.put("formUserId"+item, entity.getOwnerId());
			setMap.put("customerId"+item, entity.getCustomerId());
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
	public Boolean abnormal(Integer taskId,Integer state,String content,Integer type) {

		//抽查异常
		NCustomerTask entity = this.byId(taskId);
		entity.setLastInspectionContent(content);
		Map<String,Object> setMap = new HashMap<String,Object>();
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
	public Boolean allocation(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId,Integer allocationType) {

		String [] getTaskIdStrings = taskIds.split("_");
		Map<String,Object> setMap = new HashMap<String,Object>();
		setMap.put("supplierId", supplierId);
		setMap.put("departmentId", departmentId);
		setMap.put("toUserId", toUserId);
		setMap.put("allocationType", allocationType);
		setMap.put("taskIds", taskIds);
		for (String item : getTaskIdStrings) {
			//任务分配
			NCustomerTask entity = this.byId(Integer.valueOf(item));
			setMap.put("formUserId"+item, entity.getOwnerId());
			setMap.put("customerId"+item, entity.getCustomerId());
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
		
		//任务跟进
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
	public Boolean regain(String taskIds,String content) {
		//任务收回
		String [] getTaskIds = taskIds.split("_");
		Map<String,Object> setMap = new HashMap<String,Object>();
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

		//任务释放
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
	public Boolean rollback(Integer taskId,String content) {
		//任务回退
		NCustomerTask entity = this.byId(taskId);
		Map<String,Object> setMap = new HashMap<String,Object>();
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
}