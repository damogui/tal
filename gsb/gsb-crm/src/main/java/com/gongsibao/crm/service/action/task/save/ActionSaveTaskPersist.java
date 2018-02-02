package com.gongsibao.crm.service.action.task.save;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 新增任务：保存
 */
public class ActionSaveTaskPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		EntityState state = task.getEntityState();
		NAllocationType allocationType = task.getAllocationType();
		if (allocationType == NAllocationType.MANUAL) {

			// 【手动分配】 时设置分配状态为【已经分配】
			task.setAllocationState(AllocationState.ALLOCATED);
			if (state == EntityState.New) {

				// 新增状态：设置最后分配时间，最后分配人
				task.setLastAllocationTime(new Date());
				task.setLastAllocationUserId(SessionManager.getUserId());
				task.setFoolowStatus(CustomerFollowStatus.UNSTART);
				
				// 如果任务名称为空，则自动生成（默认取客户意向产品、意向地区，支持手动填写/修改）
				if (StringManager.isNullOrEmpty(task.getName())) {

					List<String> ss = new ArrayList<String>();
					List<NCustomerProduct> productList = task.getProducts();
					if (productList != null && productList.size() > 0) {

						NCustomerProduct nCustomerProduct = productList.get(0);
						if (nCustomerProduct.getProduct() != null) {

							ss.add(nCustomerProduct.getProduct().getName());
						}

						List<String> countyList = new ArrayList<String>();
						if (nCustomerProduct.getProvince() != null) {

							countyList.add(nCustomerProduct.getProvince().getName());
						}

						if (nCustomerProduct.getCity() != null) {

							countyList.add(nCustomerProduct.getCity().getName());
						}

						if (nCustomerProduct.getCounty() != null) {

							countyList.add(nCustomerProduct.getCounty().getName());
						}
						
						String countyName = "("+StringManager.join( ",", countyList)+")";
						ss.add(countyName);
						String name = StringManager.join( " - ", ss);
						task.setName(name);
					}
				}
			}
		}

		@SuppressWarnings("unchecked")
		IPersistableService<NCustomerTask> service = (IPersistableService<NCustomerTask>) ReflectManager.newInstance(NCustomerService.class.getSuperclass());
		task = service.save(task);
		ctx.setItem(task);
	}
}
