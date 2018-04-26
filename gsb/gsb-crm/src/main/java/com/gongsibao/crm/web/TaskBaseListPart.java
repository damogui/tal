package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskInspection;

public class TaskBaseListPart extends AdvancedListPart {

	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
	ICustomerCompanyMapService customerCompanyMapService = ServiceFactory.create(ICustomerCompanyMapService.class);
	INCustomerTaskInspectionService taskInspectService = ServiceFactory.create(INCustomerTaskInspectionService.class);
	
	public String getFilterByParameter(FilterParameter parameter) {
		if (parameter.getKey().equals("keyword")) {
			// 这里全匹配
			ArrayList<String> filters = new ArrayList<String>();
			String keyword = parameter.getValue1().toString();
			filters.add("pkid='" + keyword + "'");
			filters.add("name like '%" + keyword + "%'");
			filters.add("id='" + keyword + "'");
			filters.add("real_name like '%" + keyword + "%'");
			filters.add("mobile='" + keyword + "'");
			filters.add("telephone='" + keyword + "'");
			filters.add("qq='" + keyword + "'");
			filters.add("weixin='" + keyword + "'");
			filters.add("pkid in (select customer_id from crm_customer_company_map where company_id in(select pkid from crm_company_intention where (name like '%" + keyword
					+ "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' ) ))");
			return "customer_id in ( select pkid from crm_customer where (" + StringManager.join(" or ", filters) + "))";
		} else if (parameter.getKey().equals("unFollowDayCount")) {

			// 未跟进天数：当前时间-上次跟进时间
			return String.format("(datediff(now(),last_follow_time)>%s and (datediff(now(),last_follow_time) )<%s)", parameter.getValue1(), parameter.getValue2());
		}
		return parameter.getFilter();
	}

	@Override
	public List<?> doQuery(Oql oql) {
		StringBuilder sb = new StringBuilder();
		sb.append("NCustomerTask.*,");
		sb.append("customer.*,");
		sb.append("owner.{id,name},");
		sb.append("quality.{id,name},");
		sb.append("source.{pkid,name},");
		sb.append("lastFoolowUser.{id,name},");
		sb.append("lastFoolowUser.{id,name}");
		oql.setSelects(sb.toString());
		oql.setOrderby("create_time DESC");
		List<NCustomerTask> taskList = (List<NCustomerTask>) super.doQuery(oql);
		List<Integer> customerIdList = getCustomerIdList(taskList);
		setCompanyName(taskList, customerIdList);
		return taskList;

	}

	protected Object serialize(List<?> list, Oql oql) {
		
		HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
		ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
		for (int i = 0; i < ob2.size(); i++) {
			
			NCustomerTask task = ((NCustomerTask) list.get(i));
			if (task != null && task.getCustomer() != null) {

				ob2.get(i).put("customer_companyName", task.getCustomer().getCompanyName());
			}
		}
		return json;
	}

	/**
	 * 商机分配
	 *
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean allocation(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId) {

		String[] taskIdArray = taskIds.split("_");
		return taskService.batchAllocation(taskIdArray, supplierId, departmentId, toUserId);
	}

	/**
	 * 商机收回
	 *
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean regain(String taskIds, String getNote, Integer isPlatform) {

		String[] taskIdArray = taskIds.split("_");
		return taskService.batchRegain(taskIdArray, getNote, isPlatform);
	}

	/**
	 * 商机退回
	 *
	 * @param taskId
	 * @param getNote
	 * @return
	 */
	public boolean rollback(Integer taskId, String getNote) {

		return taskService.rollback(taskId, getNote);
	}

	/**
	 * 商机转移
	 *
	 * @param taskId
	 * @param supplierId
	 * @param departmentId
	 * @param toUserId
	 * @return
	 */
	public boolean transfer(String taskIds, Integer supplierId, Integer departmentId, Integer toUserId) {

		String[] taskIdArray = taskIds.split("_");
		return taskService.batchTransfer(taskIdArray, supplierId, departmentId, toUserId);
	}

	/**
	 * 开通会员功能
	 *
	 * @param customerId
	 * @return
	 */
	public boolean openMember(Integer customerId, Boolean isSendSms) {

		INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
		return customerService.openMember(customerId, isSendSms);
	}

	/**
	 * 抽查异常处理
	 *
	 * @param taskId
	 *            商机Id
	 * @param state
	 *            1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
	 * @param content
	 *            处理内容
	 * @param type
	 *            1-"抽查",2-"处理"
	 * @return
	 */
	public boolean abnormal(Integer taskId, Integer state, String content, Integer type) {
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		return taskService.abnormal(taskId, state, content, type);
	}
	/**
     * 查看-抽查异常
     * @param taskId
     * @return
     */
    public String showAbnormal(Integer taskId) {
        String inspecteContent = "";
    	NCustomerTaskInspection taskInspcete =  taskInspectService.getByTaskId(taskId);
    	if(taskInspcete != null){
    		inspecteContent = taskInspcete.getContent();
    	}
        return inspecteContent;
    }
	/**
	 * @throws
	 * @Title: recordLookLog
	 * @Description: TODO(记录查看联系方式日志)
	 * @param: @param customerId
	 * @param: @param typeName
	 * @param: @return
	 * @return: boolean
	 */
	public boolean recordLookLog(Integer customerId, String typeName) {

		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		return changeService.recordLookLog(customerId, typeName);
	}

	// region 私有方法
	private void setCompanyName(List<NCustomerTask> taskList, List<Integer> customerIdList) {

		Map<Integer, String> companyNameByCustomerIdMap = customerCompanyMapService.getCompanyNameByCustomerIdList(customerIdList);
		for (NCustomerTask task : taskList) {

			NCustomer customer = task.getCustomer();
			if (customer != null) {

				customer.setCompanyName(companyNameByCustomerIdMap.get(customer.getId()));
			}
		}
	}

	// 获取客户id集合
	private List<Integer> getCustomerIdList(List<NCustomerTask> taskList) {

		List<Integer> customerIdList = new ArrayList<>();
		for (NCustomerTask task : taskList) {

			if (!customerIdList.contains(task.getCustomerId())) {

				customerIdList.add(task.getCustomerId());
			}
		}
		return customerIdList;
	}
	// endregion
}
