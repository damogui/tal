package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;
import org.netsharp.util.NumUtil;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.product.WorkflowFile;
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.product.base.IWorkflowNodeService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.utils.pcc.PccHelper;

@Service
public class OrderProdService extends PersistableService<OrderProd> implements IOrderProdService {

	public OrderProdService() {
		super();
		this.type = OrderProd.class;
	}

	@Override
	public Map<Integer, String> getProductCityNameByOrderIds(List<Integer> orderIdList) {
		Map<Integer, String> resMap = new HashMap<Integer, String>();
		String orderIds = StringManager.join(",", orderIdList);
		if (StringManager.isNullOrEmpty(orderIds)) {
			return resMap;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `getbddictfullname`(od.`city_id`) 'cityName',od.product_name 'productName',od.order_id 'orderId' FROM so_order_prod od ");
		sql.append("JOIN  so_order oi ON od.order_id = oi.pkid ");
		sql.append("WHERE od.order_id IN (" + orderIds + ") ");

		DataTable executeTable = this.pm.executeTable(sql.toString(), null);

		List<Map<String, Object>> valueMapList = executeTable.getValueMapList();

		for (IRow row : executeTable) {
			Integer orderId = row.getInteger("orderId");

			List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> map : valueMapList) {
				Integer oid = NumUtil.parseInteger(map.get("orderId") == null ? "0" : map.get("orderId").toString());
				if (orderId.equals(oid)) {
					tempList.add(map);
				}
			}

			String prodNames = "";
			if (resMap.get(orderId) == null) {
				for (Map<String, Object> tempMap : tempList) {
					String prodName = String.valueOf(tempMap.get("productName") == null ? "" : tempMap.get("productName"));
					String cityName = String.valueOf(tempMap.get("cityName") == null ? "" : tempMap.get("cityName"));
					if (StringManager.isNullOrEmpty(prodName) || StringManager.isNullOrEmpty(cityName))
						continue;
					// 已经放入的产品名称
					if (prodNames.indexOf(prodName) > -1)
						continue;
					int count = 0;
					for (Map<String, Object> map : tempList) {
						String tempProdName = String.valueOf(map.get("productName") == null ? "" : map.get("productName"));
						String tempCityName = String.valueOf(map.get("cityName") == null ? "" : map.get("cityName"));
						if (tempProdName.equals(prodName) && tempCityName.equals(tempCityName)) {
							count = count + 1;
						}
					}

					prodNames += prodName + "*" + count + "|" + cityName + ",";
					resMap.put(orderId, prodNames.substring(0, prodNames.length() - 1));
				}
				resMap.put(orderId, prodNames.substring(0, prodNames.length() - 1));
			}
		}

		return resMap;
	}

	@Override
	public List<Integer> getIdsByOrderIds(List<Integer> orderIdList) {
		List<Integer> idList = new ArrayList<Integer>();
		String orderIds = StringManager.join(",", orderIdList);
		StringBuffer sql = new StringBuffer("SELECT pkid from so_order_prod where order_id in (" + orderIds + ")");
		DataTable executeTable = this.pm.executeTable(sql.toString(), null);
		for (Row row : executeTable) {
			idList.add(row.getInteger("pkid"));
		}
		return idList;
	}

	@Override
	public int updateAssignByIds(Integer isAssign, List<Integer> pkidList) {

		if (CollectionUtils.isEmpty(pkidList)) {
			return 0;
		}

		String idsString = StringManager.join(",", pkidList);

		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order_prod");
			updateBuilder.set("is_assign", isAssign);
			updateBuilder.where("pkid in(" + idsString + ")");
		}
		String sql = updateBuilder.toSQL();
		return this.pm.executeNonQuery(sql, null);
	}

	@Override
	public List<OrderProd> queryByOrderId(Integer orderId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrderProd.*,OrderProd.owner.{id,name},OrderProd.items.{id,serviceName}");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}

		List<OrderProd> list = this.queryList(oql);
		for (OrderProd prod : list) {

			if (StringManager.isNullOrEmpty(prod.getCityName())) {
				
				String cityName = PccHelper.getFullName(prod.getCityId());
				this.updateCityName(prod.getId(),cityName);
				prod.setCityName(cityName);
			}
		}
		return list;
	}

	/**
	 * @Title: updateCityName
	 * @Description: TODO(更新城市名称，因为线上下单没的冗余，所以先加这个方法，后面统一后再去掉)
	 * @param: @param cityId
	 * @return: void
	 * @throws
	 */
	private void updateCityName(Integer orderProdId,String cityName) {

		String sql = "UPDATE so_order_prod prod set prod.city_name = ? where pkid =?";
		QueryParameters qps = new QueryParameters();
		qps.add("cityName", cityName, Types.VARCHAR);
		qps.add("orderProdId", orderProdId, Types.INTEGER);
		this.pm.executeNonQuery(sql, qps);
	}

	@Override
	public boolean updateSettleStatus(List<Integer> orderProdIds, SettleStatus settleStatus) {
		if (null == orderProdIds || orderProdIds.isEmpty()) {
			return false;
		}

		if (null == settleStatus) {
			return false;
		}
		UpdateBuilder build = UpdateBuilder.getInstance();
		build.update(MtableManager.getMtable(this.type).getTableName());
		build.set("settle_status", settleStatus.getValue());
		build.where("pkid IN ( " + StringManager.join(",", orderProdIds) + " ) ");
		return this.pm.executeNonQuery(build.toSQL(), null) > 0;
	}

	@Override
	public boolean updateIsComplaint(Integer orderProdId) {
		String sql = "update `so_order_prod` set `is_complaint` = 1 where pkid = ?";
		QueryParameters qps = new QueryParameters();
		qps.add("pkid", orderProdId, Types.INTEGER);
		return this.pm.executeNonQuery(sql, qps) > 0;
	}

	@Override
	public Boolean editApplyNo(Integer orderProdId, String applyNo) {

		String sql = "update `so_order_prod` set `apply_no` = ? where pkid = ?";
		QueryParameters qps = new QueryParameters();
		qps.add("applyNo", applyNo, Types.VARCHAR);
		qps.add("pkid", orderProdId, Types.INTEGER);
		return this.pm.executeNonQuery(sql, qps) > 0;
	}

	public Boolean updateStatus(Integer orderProdId, Integer processStatusId, AuditStatusType auditStatus) {

		String sql = "update `so_order_prod` set `process_status_id` = ?, `audit_status_id` = ? where pkid = ?";
		QueryParameters qps = new QueryParameters();
		qps.add("processStatusId", processStatusId, Types.INTEGER);
		qps.add("auditStatus", auditStatus.getValue(), Types.INTEGER);
		qps.add("pkid", orderProdId, Types.INTEGER);
		return this.pm.executeNonQuery(sql, qps) > 0;
	}

	@Override
	public Boolean editHandleName(Integer orderProdId, String handleName) {

		String sql = "update `so_order_prod` set `handle_name` = ? where pkid = ?";
		QueryParameters qps = new QueryParameters();
		qps.add("handleName", handleName, Types.VARCHAR);
		qps.add("pkid", orderProdId, Types.INTEGER);
		return this.pm.executeNonQuery(sql, qps) > 0;
	}

	@Override
	public Integer getProcessStatusId(Integer orderProdId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,processStatusId");
			oql.setFilter("id=?");
			oql.getParameters().add("id", orderProdId, Types.INTEGER);
		}

		OrderProd entity = this.queryFirst(oql);
		if (entity != null) {

			return entity.getProcessStatusId();
		}
		return 0;
	}

	@Override
	public Map<Integer, Integer> getProcessStatusIdByOrderProdIds(List<Integer> orderProdIdList) {

		String orderProdIds = StringManager.join(",", orderProdIdList);

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,processStatusId");
			oql.setFilter("id in (" + orderProdIds + ")");
		}
		Map<Integer, Integer> resMap = new HashMap<>();
		List<OrderProd> orderProds = this.pm.queryList(oql);
		for (OrderProd orderProd : orderProds) {
			resMap.put(orderProd.getId(), orderProd.getProcessStatusId());
		}
		return resMap;
	}

	@Override
	public List<WorkflowNode> getWorkflowNodeList(Integer orderProdId) {

		OrderProd orderProd = this.byId(orderProdId);

		Integer version = orderProd.getVersion();
		IWorkflowNodeService workflowNodeService = ServiceFactory.create(IWorkflowNodeService.class);
		if (version == null) {

			version = workflowNodeService.getWorkflowNodeMaxVersion(orderProd.getProductId(), orderProd.getCityId());
		}

		if (version == null) {

			throw new BusinessException("交付流程模版未设置，请联系管理！");
		}

		return workflowNodeService.queryWorkflowNodeList(orderProd.getProductId(), orderProd.getCityId(), version);
	}

	@Override
	public List<WorkflowFile> queryWorkflowFileList(Integer orderProdId) {

		return null;
	}

	@Override
	public Boolean cancelRelevanceCompany(Integer orderProdId) {

		String sql = "update `so_order_prod` set `company_id` = 0 where pkid = ?";
		QueryParameters qps = new QueryParameters();
		qps.add("pkid", orderProdId, Types.INTEGER);
		return this.pm.executeNonQuery(sql, qps) > 0;
	}

	@Override
	public Boolean addRelevanceCompany(Integer orderProdId, Integer companyId) {

		String sql = "update `so_order_prod` set `company_id` =? where pkid = ?";
		QueryParameters qps = new QueryParameters();
		qps.add("companyId", companyId, Types.INTEGER);
		qps.add("pkid", orderProdId, Types.INTEGER);
		return this.pm.executeNonQuery(sql, qps) > 0;
	}

	@Override
	public Boolean updateOrderDetail(Integer orderProdId, String handleName, Integer companyId) {
		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order_prod");
			updateBuilder.set("begin_option", 1);
			updateBuilder.set("handle_name", handleName);
			if (!companyId.equals(0)) {
				updateBuilder.set("company_id", companyId);
			}
			updateBuilder.where("pkid =" + orderProdId);
		}
		String sql = updateBuilder.toSQL();
		return this.pm.executeNonQuery(sql, null) > 0;
	}
}