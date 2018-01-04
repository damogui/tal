package com.gongsibao.trade.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderPlatformSourceType;
import com.gongsibao.entity.trade.dic.OrderProcessStatusType;
import com.gongsibao.entity.trade.dic.OrderProdTraceOperatorType;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatusType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.entity.trade.dic.OrderRefundStatusType;
import com.gongsibao.entity.trade.dic.OrderStatusType;
import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.trade.base.ICompanyIntentionService;
import com.gongsibao.trade.base.ICustomerService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.ISoOrderDTOService;
import com.gongsibao.trade.service.constant.OrderConstant;

@Service
public class SoOrderDTOService extends PersistableService<SoOrderDTO> implements ISoOrderDTOService {

	IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

	IOrderService orderService = ServiceFactory.create(IOrderService.class);

	IOrderProdTraceService orderProdTraceService = ServiceFactory.create(IOrderProdTraceService.class);

	IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);

	ICompanyIntentionService companyIntentionService = ServiceFactory.create(ICompanyIntentionService.class);

	ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

	public SoOrderDTOService() {
		super();
		this.type = SoOrderDTO.class;
	}

	@Override
	public List<SoOrderDTO> queryList(Oql oql) {

		String filterString = oql.getFilter();
		HashMap<String, String> mapFilters = new HashMap();
		try {
			mapFilters = getMapFilters(filterString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();

		// 查询sql
		StringBuffer sql = getsql(0, mapFilters, startIndex, paging.getPageSize());
		// 获取总条数sql
		StringBuffer rcountsql = getsql(1, mapFilters, startIndex, paging.getPageSize());

		// 获取总条数
		paging.setTotalCount(getqueryListCount(rcountsql.toString()));
		oql.setPaging(paging);

		// 订单id集合
		List<Integer> orderIdList = new ArrayList();

		DataTable dataTable = this.pm.executeTable(sql.toString(), null);
		List<SoOrderDTO> reslis = new ArrayList<>();

		for (IRow row : dataTable) {
			SoOrderDTO dto = new SoOrderDTO();

			int totalPrice = row.getInteger("totalPrice");// 订单原价
			int payablePrice = row.getInteger("payablePrice");// 订单应付价
			int paidPrice = row.getInteger("paidPrice");// 订单已付价
			int id = row.getInteger("id");

			dto.setId(id);
			dto.setOrderNo(row.getString("orderNo"));
			dto.setChannelOrderNo(row.getString("channelOrderNo"));
			//dto.setCompanyName(row.getString("companyName"));
			dto.setRefundStatus(OrderRefundStatusType.getItem(row.getInteger("refundStatusId")));
			dto.setInstallment(row.getBoolean("isInstallment"));
			//dto.setOperator(row.getString("operator"));
			dto.setAccountId(row.getInteger("accountId"));
			dto.setAccountName(row.getString("accountName"));
			dto.setAccountMobile(row.getString("accountMobile"));
			//dto.setCustomerName(row.getString("customerName"));
			dto.setPlatformSource(OrderPlatformSourceType.getItem(row.getInteger("platformSource")));
			dto.setProcessStatusId(OrderProcessStatusType.getItem(row.getInteger("processStatusId")));
			dto.setTotalPrice(getDivRes(totalPrice, 100));
			dto.setPaidPrice(getDivRes(paidPrice, 100));
			dto.setPayablePrice(getDivRes(payablePrice, 100));
			dto.setAddTime(row.getDate("addTime"));
			dto.setPayTime(row.getDate("payTime"));
			orderIdList.add(id);
			reslis.add(dto);
		}

		// 根据订单id 获取产品名称
		Map<Integer, String> productNameMap = orderProdService.getProductCityNameByOrderIds(orderIdList);
		// 批量转移客户的最后一条跟进记录
		Map<Integer, String> lastInfoMap = orderProdTraceService.getLastInfoByOrderIdType(orderIdList, OrderProdTraceType.Ghywy.getValue());
		// 获取最后的【曾经跟进】的业务员
		Map<Integer, String> lastOperatorMap = orderProdUserMapService.getLastOperatorByOrderIdsStatusType(orderIdList, OrderProdUserMapType.Ywy.getValue(), OrderProdUserMapStatusType.Cjfz.getValue());
		// 获取最后的【正在跟进】的业务员
		Map<Integer, String> operatorMap = orderProdUserMapService.getLastOperatorByOrderIdsStatusType(orderIdList, OrderProdUserMapType.Ywy.getValue(), OrderProdUserMapStatusType.Zzfz.getValue());
		// 根据订单id获取该订单对应的公司名称
		Map<Integer, String> companyByOrderIdListMap = companyIntentionService.getCompanyByOrderIdList(orderIdList);
		// 根据订单id获取该订单对应的客户名称
		Map<Integer, String> customerNameByOrderIdListMap = customerService.getCustomerNameByOrderIdList(orderIdList);

		for (SoOrderDTO o : reslis) {
			// 产品名称
			o.setProductName(productNameMap.get(o.getId()));
			// 最后一天【批量跟进的跟进记录】
			o.setOperationTraceInfo(lastInfoMap.get(o.getId()));
			// 获取最后的【曾经跟进】的业务员
			o.setOldOperator(lastOperatorMap.get(o.getId()));
			// 获取最后的【正在跟进】的业务员
			o.setOperator(operatorMap.get(o.getId()));
			// 获取公司名称
			o.setCompanyName(companyByOrderIdListMap.get(o.getId()));
			// 获取客户名称
			o.setCustomerName(customerNameByOrderIdListMap.get(o.getId()));
			// 订单状态
			if (o.getPaidPrice() == 0 && getDistinceDay(o.getAddTime(), new Date()) < OrderConstant.ORDER_UNVALID_DAY) {
				o.setOrderStatus(OrderStatusType.getItem(1));
			}
			if (Objects.equals(o.getPaidPrice(), o.getPayablePrice())) {
				o.setOrderStatus(OrderStatusType.getItem(2));
			}
			if (!Objects.equals(o.getPaidPrice(), o.getPayablePrice()) && o.getPaidPrice() > 0) {
				o.setOrderStatus(OrderStatusType.getItem(3));
			}
			if (o.getPaidPrice() == 0 && getDistinceDay(o.getAddTime(), new Date()) >= OrderConstant.ORDER_UNVALID_DAY) {
				o.setOrderStatus(OrderStatusType.getItem(5));
			}
			if (o.getProcessStatusId() == OrderProcessStatusType.Ywc) {
				o.setOrderStatus(OrderStatusType.getItem(4));
			}
		}

		return reslis;
	}

	@Override
	public SoOrderDTO byId(Object id) {
		SoOrderDTO orderdto = new SoOrderDTO();
		SoOrder order = orderService.byId(id);
		orderdto.setOrderNo(order.getNo());
		return orderdto;
	}

	// type：0查询sql 1获取页码sql
	protected StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
		StringBuffer sql = new StringBuffer();

		if (type == 0) {
			sql.append("SELECT DISTINCT oi.`pkid` 'id',oi.`no` 'orderNo',oi.channel_order_no 'channelOrderNo',oi.`pay_time` 'payTime', ");
			sql.append("oi.pay_status_id 'payStatusId',oi.process_status_id 'processStatusId', ");
			// sql.append("(CASE WHEN cri.company_name!='' THEN cri.company_name WHEN cri.`full_name`!='' THEN cri.`full_name` WHEN cri.`name`!='' THEN cri.`name`  ");
			// sql.append("ELSE GROUP_CONCAT(DISTINCT(CASE WHEN cri1.company_name!='' THEN cri1.company_name WHEN cri1.`full_name`!='' THEN cri1.`full_name` ELSE cri.`name` END) SEPARATOR ',') END) 'companyName', ");
			sql.append("oi.refund_status_id 'refundStatusId',oi.total_price 'totalPrice',oi.payable_price 'payablePrice',oi.paid_price 'paidPrice',oi.is_installment 'isInstallment', ");
			// sql.append("GROUP_CONCAT(DISTINCT u.real_name SEPARATOR ',') 'operator',oi.account_id 'accountId',oi.account_name 'accountName',oi.account_mobile 'accountMobile',c.real_name 'customerName', ");
			// sql.append("oi.account_id 'accountId',oi.account_name 'accountName',oi.account_mobile 'accountMobile',c.real_name 'customerName', ");
			sql.append("oi.account_id 'accountId',oi.account_name 'accountName',oi.account_mobile 'accountMobile', ");
			sql.append("oi.platform_source 'platformSource',oi.add_time 'addTime' ");
		} else {
			sql.append("SELECT COUNT(DISTINCT oi.`pkid`) 'rount' ");
		}

		sql.append("FROM so_order oi ");
		sql.append("JOIN so_order_prod od ON oi.`pkid`=od.`order_id` ");
		//当查询的条件没有牵连到子表时，则不用join表，这样可以提高查询效率
		// 下单人
		if (!StringManager.isNullOrEmpty(mapFilters.get("customerName"))) {
			sql.append("JOIN uc_account a ON a.`pkid` = oi.`account_id` ");
			sql.append("LEFT JOIN crm_customer c ON c.account_id = a.`pkid`");
		}

		// 企业名称
		if (!StringManager.isNullOrEmpty(mapFilters.get("companyName"))) {
			sql.append("LEFT JOIN crm_company_intention cri ON cri.pkid = oi.`company_id` ");
			sql.append("LEFT JOIN crm_company_intention cri1 ON cri1.pkid = od.`company_id` ");
		}

		// 业务员
		if (!StringManager.isNullOrEmpty(mapFilters.get("operator"))) {
			sql.append("LEFT JOIN so_order_prod_user_map odum ON odum.`order_prod_id` = od.`pkid` AND odum.type_id = " + OrderProdUserMapType.Ywy.getValue() + "  AND odum.status_id = " + OrderProdUserMapStatusType.Zzfz.getValue() + "  ");
			sql.append("LEFT JOIN uc_user u ON u.`pkid`=odum.`user_id`");
		}

		// 原业务员
		if (!StringManager.isNullOrEmpty(mapFilters.get("oldOperator"))) {
			sql.append("LEFT JOIN (SELECT * FROM so_order_prod_user_map WHERE pkid IN(SELECT MAX(pkid) FROM so_order_prod_user_map WHERE status_id=" + OrderProdUserMapStatusType.Cjfz.getValue() + " AND type_id = " + OrderProdUserMapType.Ywy.getValue() + " GROUP BY order_prod_id)) odum1 ON odum1.`order_prod_id` = od.`pkid` ");
			sql.append("LEFT JOIN uc_user u1 ON u1.`pkid`=odum1.`user_id` ");
		}

		// 批量转移业务员操作记录
		if (!StringManager.isNullOrEmpty(mapFilters.get("operationTraceInfo"))) {
			sql.append("LEFT JOIN (SELECT * FROM so_order_prod_trace WHERE pkid IN(SELECT MAX(pkid) FROM so_order_prod_trace GROUP BY order_prod_id)) odt ON odt.order_prod_id = od.pkid AND odt.`type_id`= " + OrderProdTraceType.Ghywy.getValue() + " ");
		}

		sql.append("WHERE 1=1 ");

		// 订单号
		if (!StringManager.isNullOrEmpty(mapFilters.get("orderNo"))) {
			sql.append("AND oi.no LIKE " + mapFilters.get("orderNo") + " ");
		}

		// 原业务员
		if (!StringManager.isNullOrEmpty(mapFilters.get("oldOperator"))) {
			sql.append("AND u1.real_name LIKE " + mapFilters.get("oldOperator") + " ");
		}

		// 批量转移业务员操作记录
		if (!StringManager.isNullOrEmpty(mapFilters.get("operationTraceInfo"))) {
			sql.append("AND odt.`info` LIKE " + mapFilters.get("operationTraceInfo") + "  ");
		}

		// 渠道订单编号
		if (!StringManager.isNullOrEmpty(mapFilters.get("channelOrderNo"))) {
			sql.append("AND oi.channel_order_no LIKE " + mapFilters.get("channelOrderNo") + " ");
		}

		// 业务员
		if (!StringManager.isNullOrEmpty(mapFilters.get("operator"))) {
			sql.append("AND u.real_name LIKE " + mapFilters.get("operator") + " ");
		}

		// 下单人
		if (!StringManager.isNullOrEmpty(mapFilters.get("customerName"))) {
			sql.append("AND c.real_name LIKE " + mapFilters.get("customerName") + " ");
		}

		// 下单人手机号
		if (!StringManager.isNullOrEmpty(mapFilters.get("accountMobile"))) {
			sql.append("AND oi.account_mobile LIKE " + mapFilters.get("accountMobile") + " ");
		}

		// 订单状态
		if (!StringManager.isNullOrEmpty(mapFilters.get("orderStatus"))) {
			String state = mapFilters.get("orderStatus");
			// 订单状态 1等待付款、2已付全款、3已付部分款、4办理完成、5失效订单
			// if (state == OrderStatusType.Ddfk) {
			if (state.indexOf("'1'") > -1) {
				sql.append(" AND oi.paid_price = 0 AND TIMESTAMPDIFF(HOUR, oi.add_time, NOW()) < " + OrderConstant.ORDER_UNVALID_HOUR + " ");
			} else if (state.indexOf("'2'") > -1) {
				sql.append(" AND oi.paid_price = oi.payable_price ");
			} else if (state.indexOf("'3'") > -1) {
				sql.append(" AND oi.paid_price != oi.payable_price AND oi.paid_price > 0 ");
			} else if (state.indexOf("'4'") > -1) {
				sql.append(" AND oi.process_status_id = " + OrderProcessStatusType.Ywc.getValue() + " ");
			} else if (state.indexOf("'5'") > -1) {
				sql.append(" AND oi.paid_price = 0 AND TIMESTAMPDIFF(HOUR, oi.add_time, NOW()) >= " + OrderConstant.ORDER_UNVALID_HOUR + " ");
			}
		}

		// 产品名称
		if (!StringManager.isNullOrEmpty(mapFilters.get("productName"))) {
			sql.append("AND od.product_name LIKE " + mapFilters.get("productName") + " ");
		}

		// 关联企业
		if (!StringManager.isNullOrEmpty(mapFilters.get("companyName"))) {
			sql.append("AND (cri.company_name LIKE " + mapFilters.get("companyName") + " OR cri.full_name LIKE " + mapFilters.get("companyName") + " OR cri.name LIKE " + mapFilters.get("companyName") + " OR cri1.company_name LIKE " + mapFilters.get("companyName") + " OR cri1.full_name LIKE " + mapFilters.get("companyName") + " OR cri1.name LIKE " + mapFilters.get("companyName") + " ) ");
		}

		// 订单来源
		if (!StringManager.isNullOrEmpty(mapFilters.get("platformSource"))) {
			sql.append("AND oi.platform_source in " + mapFilters.get("platformSource") + " ");
		}

		// 是否分期
		if (!StringManager.isNullOrEmpty(mapFilters.get("isInstallment"))) {
			sql.append("AND oi.is_installment = " + mapFilters.get("isInstallment") + " ");
		}

		// 开始订单创建日期
		if (!StringManager.isNullOrEmpty(mapFilters.get("startAddTime"))) {
			sql.append("AND oi.add_time >= " + mapFilters.get("startAddTime") + " ");
		}

		// 结束订单创建日期
		if (!StringManager.isNullOrEmpty(mapFilters.get("endAddTime"))) {
			sql.append("AND oi.add_time <= " + mapFilters.get("endAddTime") + " ");
		}

		// 开始回款日期
		if (!StringManager.isNullOrEmpty(mapFilters.get("startPayTime"))) {
			sql.append("AND oi.pay_time >= " + mapFilters.get("startPayTime") + " ");
		}

		// 结束回款日期
		if (!StringManager.isNullOrEmpty(mapFilters.get("endPayTime"))) {
			sql.append("AND oi.pay_time <= " + mapFilters.get("endPayTime") + " ");
		}
		// 分页时，不用排序分组
		if (type == 0) {
			sql.append("GROUP BY oi.`pkid` ");
			sql.append("ORDER BY oi.add_time DESC ");
			sql.append("LIMIT " + startIndex + ", " + pageSize + " ");
		}

		return sql;
	}

	// 获取查询的总条数
	protected int getqueryListCount(String sql) {
		int count = 0;
		Object rcount = this.pm.executeScalar(sql.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}

	// 两个数相除
	protected double getDivRes(int a, int b) {

		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format((float) a / b));
	}

	// 根据前段传过来的参数，得到Map
	protected HashMap<String, String> getMapFilters(String filter) throws UnsupportedEncodingException {

		HashMap<String, String> map = new HashMap<String, String>();

		if (!StringManager.isNullOrEmpty(filter)) {

			filter = filter.replace("%", "|");
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace("|", "%");
			String[] ss = filter.split(" AND ");
			if (ss.length > 0) {

				for (String s : ss) {
					String splitString = "=";
					if (s.contains("LIKE")) {
						splitString = "LIKE";
					}
					if (s.contains("in")) {
						splitString = "in";
					}
					if (s.contains(">=")) {
						splitString = ">=";
					}
					if (s.contains("<=")) {
						splitString = "<=";
					}
					String[] a = s.split(splitString);
					String mapKeyString = a[0].trim();
					String mapValString = a[1].trim();
					// 下单时间
					if (mapKeyString.equals("addTime") && splitString.equals(">=")) {
						mapKeyString = "startAddTime";
					}
					if (mapKeyString.equals("addTime") && splitString.equals("<=")) {
						mapKeyString = "endAddTime";
					}
					// 订单回款时间
					if (mapKeyString.equals("payTime") && splitString.equals(">=")) {
						mapKeyString = "startPayTime";
					}
					if (mapKeyString.equals("payTime") && splitString.equals("<=")) {
						mapKeyString = "endPayTime";
					}
					map.put(mapKeyString, mapValString);
				}
				return map;
			}
		}
		return map;
	}

	/**
	 * 计算两个日期相差的天数
	 *
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	protected long getDistinceDay(Date beforeDate, Date afterDate) {
		long dayCount = 0;
		dayCount = (afterDate.getTime() - beforeDate.getTime()) / (24 * 60 * 60 * 1000);
		return dayCount;
	}

}
