package com.gongsibao.crm.service.action.task.autoAllot;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanProduct;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.supplier.base.ISalesmanService;

/**
 * @author zhangchao 分配逻辑
 */
public class ActionCustomerTaskAllot implements IAction {

	// 任务服务对象
	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
	IPersister<NCustomerTask> taskPm = PersisterFactory.create();

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask entity = (NCustomerTask) ctx.getItem();

		Integer ownerId = 0;

		List<String> salesmanSqlWhereList = new ArrayList<String>();
		salesmanSqlWhereList.add(" disabled=0 ");// 没有停用的
		salesmanSqlWhereList.add(" receiving=1 ");// 是否接单
		// 当有市场投放时，优先有市场投放的部门
		if (entity.getCosted()) {
			salesmanSqlWhereList.add(" supplier_id=" + entity.getCostSupplierId() + " ");
		} else {
			// 当有跟进【服务商时】，将分配组织机构范围缩小至跟进部门
			if (!entity.getSupplierId().equals(0)) {
				salesmanSqlWhereList.add(" supplier_id=" + entity.getSupplierId() + " ");
			}
		}
		// 新客户时(【新客户】或【全部】标签的组织机构)
		if (entity.getTaskType().equals(TaskCustomerType.NEW)) {
			salesmanSqlWhereList.add(" (customer_type=" + TaskCustomerType.NEW.getValue() + " or customer_type=" + TaskCustomerType.All.getValue() + ") ");
		}
		// 老客户时(【老客户】或【全部】标签的组织机构)
		if (entity.getTaskType().equals(TaskCustomerType.OLD)) {
			salesmanSqlWhereList.add(" (customer_type=" + TaskCustomerType.OLD.getValue() + " or customer_type=" + TaskCustomerType.All.getValue() + ") ");
		}
		// 平台：自营时
		if (entity.getAllocationDispositon().equals(SupplierType.SELFSUPPORT)) {
			salesmanSqlWhereList.add(" (allocation_dispositon=" + SupplierType.SELFSUPPORT.getValue() + " or allocation_dispositon=" + SupplierType.UNLIMITED.getValue() + ") ");
		}
		// 平台：平台时
		if (entity.getAllocationDispositon().equals(SupplierType.PLATFORM)) {
			salesmanSqlWhereList.add(" (allocation_dispositon=" + SupplierType.PLATFORM.getValue() + " or allocation_dispositon=" + SupplierType.UNLIMITED.getValue() + ") ");
		}

		// 查询业务员的条件的字符串
		String whereString = StringManager.join(" and ", salesmanSqlWhereList);

		Oql salesmanOql = new Oql();
		{
			salesmanOql.setType(Salesman.class);
			salesmanOql.setSelects("Salesman.*,Salesman.products.*");
			salesmanOql.setFilter(whereString);
		}

		// 查询出符合条件的业务员
		List<Salesman> salesmanList = salesmanService.queryList(salesmanOql);
		// 符合服务范围的业务员
		List<Salesman> taskSalesmanProducts = new ArrayList<Salesman>();
		// 最后的结果集合
		List<Salesman> resSalesmanList = new ArrayList<Salesman>();

		// 该任务的所有的服务范围
		List<NCustomerProduct> taskProducts = entity.getProducts();
		// 筛选出满足服务范围的业务员
		for (Salesman s : salesmanList) {
			for (NCustomerProduct nCustomerProduct : taskProducts) {
				List<SalesmanProduct> salesmanProducts = s.getProducts();
				for (SalesmanProduct salesmanProduct : salesmanProducts) {
					if (salesmanProduct.getProductId().equals(nCustomerProduct.getProductId()) && // 产品
							salesmanProduct.getProvinceId().equals(nCustomerProduct.getProvinceId()) && // 省
							salesmanProduct.getCityId().equals(nCustomerProduct.getCityId()) && // 市
							salesmanProduct.getCountyId().equals(nCustomerProduct.getCountyId())) {// 区
						// 防止重复
						if (!taskSalesmanProducts.contains(s)) {
							taskSalesmanProducts.add(s);
						}
					}
				}
			}
		}

		// 无符合意向产品/地区的组织机构
		if (CollectionUtils.isEmpty(taskSalesmanProducts)) {
			// 无市场投放
			if (!entity.getCosted()) {
				// 将分配方式选中【手动分配】
				updateTaskAllocationType(entity.getId(), NAllocationType.MANUAL);
				// TODO:提醒售前客服负责人进行手动分配，日志信息
				return;
			} else {
				// 将分配方式选中【半自动分配】
				updateTaskAllocationType(entity.getId(), NAllocationType.SemiAutomatic);
				// 分配至目标部门的【公海】,此时的跟进服务商修改成【有市场投放的部门（服务商）】
				updateTaskOwnerId(entity.getId(), 0, entity.getCostSupplierId());
				// TODO:提醒部门负责人进行任务分配，日志信息

				return;
			}
		}

		// 分配方式:半自动分配时（分配到跟进服务商即可）
		if (entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
			// 分配至目标服务商的【公海】,直接就是剩下业务员所在的服务商（如果有市场投放，则都是该有市场投放部门的人，如果没有市场投放则就在剩下业务员所在部门随便挑一个）
			updateTaskOwnerId(entity.getId(), 0, taskSalesmanProducts.get(0).getSupplierId());
			// TODO:提醒部门负责人进行任务分配，日志信息
			return;
		}

		// 分配方式:自动分配时
		if (entity.getAllocationType().equals(NAllocationType.AUTO)) {
			List<Integer> employeeIdList = new ArrayList<Integer>();
			for (Salesman s : taskSalesmanProducts) {
				employeeIdList.add(s.getEmployeeId());
			}
			// 业务员的日已分配数量
			Map<Integer, Integer> dayMap = nCustomerTaskService.getTaskCountByEmployeeIdList(employeeIdList, 0);
			// 业务员的周已分配数量
			Map<Integer, Integer> weekMap = nCustomerTaskService.getTaskCountByEmployeeIdList(employeeIdList, 1);
			// 业务员的XAB类已分配数量
			Map<Integer, Integer> abxMap = nCustomerTaskService.getTaskCountByEmployeeIdList(employeeIdList, 2);

			for (Salesman salesman : taskSalesmanProducts) {
				// 是否接受自动分配
				if (!salesman.getIsaccpetauto())
					continue;
				int dayCount = dayMap.get(salesman.getEmployeeId());
				int weekCount = weekMap.get(salesman.getEmployeeId());
				int abxCount = abxMap.get(salesman.getEmployeeId());
				// 日分配上线
				if (salesman.getDaymax() < dayCount)
					continue;
				// 周分配上线
				if (salesman.getWeekmax() < weekCount)
					continue;
				// XAB类任务上限
				if (salesman.getXabmax() < abxCount)
					continue;
				salesman.setDayAllocatedCount(dayCount);
				// 取一个后跳出循环（需求说：选取随机一人进行分配）
				resSalesmanList.add(salesman);

			}
			// 当有符合条件【业务员】时，才更新
			if (CollectionUtils.isNotEmpty(resSalesmanList)) {
				// 找到今日已分配数量最少的人，然后选取随机一人进行分配
				resSalesmanList.sort(new Comparator<Salesman>() {
					// 按照今日分配量，升序排序
					@Override
					public int compare(Salesman s1, Salesman s2) {
						return s1.getDayAllocatedCount().compareTo(s2.getDayAllocatedCount());
					}
				});
				ownerId = resSalesmanList.get(0).getEmployeeId();
				// 跟新业务员
				updateTaskOwnerId(entity.getId(), ownerId, entity.getSupplierId());
			}
		}

	}

	private void updateTaskOwnerId(Integer taskId, Integer ownerId, Integer supplierId) {
		// 跟新业务员
		// IPersister<NCustomerTask> taskPm = PersisterFactory.create();

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("owner_id", ownerId);
			updateSql.set("supplier_id", supplierId);
			updateSql.where("id=?");
		}
		String cmdText = updateSql.toSQL();

		QueryParameters qps = new QueryParameters();
		qps.add("id", taskId, Types.INTEGER);

		taskPm.executeNonQuery(cmdText, qps);
	}

	// 修改任务的【分配方式】
	private void updateTaskAllocationType(Integer taskId, NAllocationType allocationType) {
		// 跟新【分配方式】
		// IPersister<NCustomerTask> taskPm = PersisterFactory.create();

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("allocation_type", allocationType.getValue());
			updateSql.where("id=?");
		}
		String cmdText = updateSql.toSQL();

		QueryParameters qps = new QueryParameters();
		qps.add("id", taskId, Types.INTEGER);

		taskPm.executeNonQuery(cmdText, qps);
	}
}
