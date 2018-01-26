package com.gongsibao.crm.service;

import java.sql.Types;

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
	public Boolean transfer(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId) {
		
		//任务转移
		NCustomerTask entity = this.byId(taskId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/transfer");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
		
	}

	@Override
	public Boolean abnormal(Integer taskId) {

		//抽查异常
		NCustomerTask entity = this.byId(taskId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/abnormal");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	@Override
	public Boolean allocation(Integer taskId, Integer supplierId, Integer departmentId, Integer toUserId) {

		//任务分配
		NCustomerTask entity = this.byId(taskId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/allocation");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
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
	public Boolean regain(Integer taskId) {

		//任务收回
		NCustomerTask entity = this.byId(taskId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/regain");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
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
	public Boolean rollback(Integer taskId) {
		
		//任务回退
		NCustomerTask entity = this.byId(taskId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/task/rollback");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}
}