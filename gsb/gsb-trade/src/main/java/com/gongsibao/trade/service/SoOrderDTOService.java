package com.gongsibao.trade.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.dic.OrderPlatformSourceType;
import com.gongsibao.entity.trade.dic.OrderRefundStatusName;
import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.trade.base.ISoOrderDTOService;

@Service
public class SoOrderDTOService extends PersistableService<SoOrderDTO> implements ISoOrderDTOService {

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

			dto.setId(row.getInteger("id"));
			dto.setOrderNo(row.getString("orderNo"));
			dto.setChannelOrderNo(row.getString("channelOrderNo"));
			dto.setCompanyName(row.getString("companyName"));
			dto.setRefundStatus(OrderRefundStatusName.getItem(row.getInteger("refundStatusId")));
			dto.setInstallment(row.getBoolean("isInstallment"));
			dto.setOperator(row.getString("operator"));
			dto.setAccountId(row.getInteger("accountId"));
			dto.setAccountName(row.getString("accountName"));
			dto.setAccountMobile(row.getString("accountMobile"));
			dto.setCustomerName(row.getString("customerName"));
			dto.setPlatformSource(OrderPlatformSourceType.getItem(row.getInteger("platformSource")));
			dto.setTotalPrice(getDivRes(totalPrice, 100));
			dto.setPaidPrice(getDivRes(paidPrice, 100));
			dto.setPayablePrice(getDivRes(payablePrice, 100));
			dto.setAddTime(row.getDate("addTime"));
			dto.setPayTime(row.getDate("payTime"));
			orderIdList.add(dto.getId());
			reslis.add(dto);
		}

		return reslis;
	}

	// type：0查询sql 1获取页码sql
	private StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
		StringBuffer sql = new StringBuffer();

		if (type == 0) {
			sql.append("SELECT DISTINCT oi.`pkid` 'id',oi.`no` 'orderNo',oi.channel_order_no 'channelOrderNo',oi.`pay_time` 'payTime', ");
			sql.append("oi.pay_status_id 'payStatusId',oi.process_status_id 'processStatusId', ");
			sql.append("(CASE WHEN cri.company_name!='' THEN cri.company_name WHEN cri.`full_name`!='' THEN cri.`full_name` WHEN cri.`name`!='' THEN cri.`name`  ");
			sql.append("ELSE GROUP_CONCAT(DISTINCT(CASE WHEN cri1.company_name!='' THEN cri1.company_name WHEN cri1.`full_name`!='' THEN cri1.`full_name` ELSE cri.`name` END) SEPARATOR ',') END) 'companyName', ");
			sql.append("oi.refund_status_id 'refundStatusId',oi.total_price 'totalPrice',oi.payable_price 'payablePrice',oi.paid_price 'paidPrice',oi.is_installment 'isInstallment', ");
			sql.append("GROUP_CONCAT(DISTINCT u.real_name SEPARATOR ',') 'operator',oi.account_id 'accountId',oi.account_name 'accountName',oi.account_mobile 'accountMobile',c.real_name 'customerName', ");
			sql.append("oi.platform_source 'platformSource',oi.add_time 'addTime' ");
		} else {
			sql.append("SELECT COUNT(DISTINCT oi.`pkid`) 'rount' ");
		}

		sql.append("FROM so_order oi ");
		sql.append("JOIN so_order_prod od ON oi.`pkid`=od.`order_id` ");
		sql.append("JOIN uc_account a ON a.`pkid` = oi.`account_id` ");
		sql.append("LEFT JOIN crm_customer c ON c.account_id = a.`pkid`");
		sql.append("LEFT JOIN crm_company_intention cri ON cri.pkid = oi.`company_id` ");
		sql.append("LEFT JOIN crm_company_intention cri1 ON cri1.pkid = od.`company_id` ");
		sql.append("LEFT JOIN so_order_prod_user_map odum ON odum.`order_prod_id` = od.`pkid` AND type_id = 3061 AND status_id = 3141 ");
		sql.append("LEFT JOIN uc_user u ON u.`pkid`=odum.`user_id`");
		sql.append("WHERE 1=1 ");

		// 订单号
		if (!StringManager.isNullOrEmpty(mapFilters.get("orderNo"))) {
			sql.append("AND oi.no LIKE " + mapFilters.get("orderNo") + " ");
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
			/*
			 * if(){//参考蜂巢逻辑
			 * 
			 * }
			 */
		}

		// 下单人手机号
		if (!StringManager.isNullOrEmpty(mapFilters.get("accountMobile"))) {
			sql.append("AND oi.account_mobile LIKE " + mapFilters.get("accountMobile") + " ");
		}

		// 产品名称
		if (!StringManager.isNullOrEmpty(mapFilters.get("productName"))) {
			sql.append("AND od.product_name LIKE " + mapFilters.get("productName") + " ");
		}

		// 关联企业
		if (!StringManager.isNullOrEmpty(mapFilters.get("companyName"))) {
			sql.append("AND (cri.account_mobile LIKE " + mapFilters.get("companyName") + ") ");
		}

		// 订单来源
		if (!StringManager.isNullOrEmpty(mapFilters.get("accountMobile"))) {
			sql.append("AND oi.account_mobile LIKE " + mapFilters.get("accountMobile") + " ");
		}

		if (type == 0) {
			sql.append("GROUP BY oi.`pkid` ");
			sql.append("ORDER BY oi.add_time DESC ");
			sql.append("LIMIT " + startIndex + ", " + pageSize + " ");
		}

		return sql;
	}

	// 获取查询的总条数
	private int getqueryListCount(String sql) {
		int count = 0;
		Object rcount = this.pm.executeScalar(sql.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}

	private double getDivRes(int a, int b) {

		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format((float) a / b));
	}

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
					if (mapKeyString.equals("addTime") && splitString.equals(">=")) {
						mapKeyString = "startAddTime";
					}
					if (mapKeyString.equals("addTime") && splitString.equals("<=")) {
						mapKeyString = "endAddTime";
					}
					map.put(mapKeyString, mapValString);
				}
				return map;
			}
		}
		return map;
	}

}
