package com.gongsibao.crm.service.action.task.save;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.utils.NumberUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.EntityState;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.service.NCustomerService;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 新增商机：保存
 */
public class ActionSaveTaskPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		EntityState state = task.getEntityState();

		// 如果商机名称为空，则自动生成（默认取客户意向产品、意向地区，支持手动填写/修改）
		// if (StringManager.isNullOrEmpty(task.getName())) {

		createTaskName(task);// 每次取第最后一个意向产品命名
		// }

		// 新增状态下，如果是市场投放则自动代入费用部门
		if (state == EntityState.New && task.getCosted()) {

			task.setCostSupplierId(task.getSupplierId());
		}

		NAllocationType allocationType = task.getAllocationType();
		if (allocationType == NAllocationType.MANUAL) {

			// 【手动分配】 时设置分配状态为【已经分配】
			// task.setAllocationState(AllocationState.ALLOCATED);

			if (state == EntityState.New) {

				// 新增状态：设置最后分配时间，最后分配人
				task.setLastAllocationTime(new Date());
				task.setLastAllocationUserId(SessionManager.getUserId());
				task.setFoolowStatus(CustomerFollowStatus.UNSTART);
			}
		}
		// 当该商机有：【市场投放费用】时，则将该商机的市场投放服务商更新为跟进服务商
		if (task.getCosted() && NumberUtils.toInt(task.getCostSupplierId()) == 0) {
			task.setCostSupplierId(task.getSupplierId());
		}

		this.relevanceProductCustomerId(task);

		@SuppressWarnings("unchecked")
		IPersistableService<NCustomerTask> service = (IPersistableService<NCustomerTask>) ReflectManager.newInstance(NCustomerService.class.getSuperclass());
		task = service.save(task);
		ctx.setItem(task);
	}

	private void createTaskName(NCustomerTask task) {

		List<String> ss = new ArrayList<String>();
		List<NCustomerProduct> productList = task.getProducts();
		if (productList != null && productList.size() > 0) {

			//int lastIndex = productList.size() - 1;//这里要区分
			NCustomerProduct nCustomerProduct = productList.get(0);
			if (nCustomerProduct.getProduct() != null) {

				ss.add(nCustomerProduct.getProduct().getName());
			}

			List<String> countyList = new ArrayList<String>();
			if (nCustomerProduct.getProvinceId() != null && nCustomerProduct.getProvince() != null) {

				countyList.add(nCustomerProduct.getProvince().getName());
				nCustomerProduct.setOldCityId(nCustomerProduct.getProvinceId());
			}

			if (nCustomerProduct.getCityId() != null && nCustomerProduct.getCity() != null) {

				countyList.add(nCustomerProduct.getCity().getName());
				nCustomerProduct.setOldCityId(nCustomerProduct.getCityId());
			}

			if (nCustomerProduct.getCountyId() != null && nCustomerProduct.getCounty() != null) {

				countyList.add(nCustomerProduct.getCounty().getName());
				nCustomerProduct.setOldCityId(nCustomerProduct.getCountyId());
			}

			if (countyList.size() > 0) {

				String countyName = "(" + StringManager.join(",", countyList) + ")";
				ss.add(countyName);
			}
			String name = StringManager.join(" - ", ss);
			task.setName(name);
		}
	}

	/**
	 * @Title: relevanceProductCustomerId
	 * @Description: TODO(关联意向产品的customerId)
	 * @param: @param task
	 * @return: void
	 * @throws
	 */
	private void relevanceProductCustomerId(NCustomerTask task) {

		List<NCustomerProduct> productList = task.getProducts();
		for (NCustomerProduct product : productList) {

			product.setCustomerId(task.getCustomerId());
		}
	}
}
