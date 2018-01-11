package com.gongsibao.u8.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.trade.dic.PayForOrderCountType;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.entity.trade.dic.PaySuccessStatus;
import com.gongsibao.entity.trade.dto.PayReceiptCheckDTO;
import com.gongsibao.u8.base.IPayReceiptCheckDTOService;

@Service
public class PayReceiptCheckDTOService extends PersistableService<PayReceiptCheckDTO> implements IPayReceiptCheckDTOService {

	public PayReceiptCheckDTOService() {
		super();
		this.type = PayReceiptCheckDTO.class;
	}

	@Override
	public List<PayReceiptCheckDTO> queryList(Oql oql) {

		/*
		 * String filterString = oql.getFilter(); Paging paging =
		 * oql.getPaging(); int startIndex = (paging.getPageNo() - 1) *
		 * paging.getPageSize(); StringBuffer sql = new StringBuffer();
		 * sql.append("SELECT * FROM ( "); sql.append(
		 * "SELECT p.`pkid` as id, oi.`pkid` orderId, oi.`no` orderNo,oi.`payable_price` 'payablePrice',oi.`paid_price` 'paidPrice', p.`receipt_no` 'receiptNo',p.`receipt_status` 'receiptStatus',p.`amount`, "
		 * ); sql.append(
		 * "book.`name` 'bookName',book.`id` 'bookId',ub.`name` 'bankName',ub.`id` 'bankId',oi.`add_time` 'addTime', "
		 * ); sql.append("p.confirm_time 'returnTime' ");
		 * sql.append("FROM so_pay p ");
		 * sql.append("JOIN so_order_pay_map opm ON p.`pkid`=opm.`pay_id` ");
		 * sql.append("JOIN so_order oi ON oi.`pkid`=opm.`order_id` ");
		 * sql.append(
		 * "JOIN u8_bank_so_pay_map uopm ON uopm.`pay_id`=p.`pkid` AND uopm.`type`=0 "
		 * ); sql.append("JOIN u8_bank ub ON ub.id=uopm.`u8_bank_id` ");
		 * sql.append
		 * ("JOIN u8_set_of_books book ON book.`id`=ub.`set_of_books_id` ");
		 * sql.append(
		 * "WHERE p.`success_status_id`=3123 AND offline_audit_status_id=1054 "
		 * ); sql.append("ORDER BY p.`pkid`  DESC )t  ");
		 * sql.append(filterString == null ? "" : "WHERE " + filterString);//
		 * 拼接sql语句的where条件
		 * paging.setTotalCount(getqueryListCount1(sql.toString()));
		 * sql.append("LIMIT " + startIndex + ", " + paging.getPageSize() +
		 * " "); oql.setPaging(paging);
		 */

		String filterString = oql.getFilter();
		HashMap<String, String> mapFilters = new HashMap<String, String>();
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

		DataTable dataTable = this.pm.executeTable(sql.toString(), null);
		List<PayReceiptCheckDTO> reslis = new ArrayList<>();
		for (IRow row : dataTable) {
			PayReceiptCheckDTO dto = new PayReceiptCheckDTO();
			Integer id = row.getInteger("id");// 支付id
			String orderId = row.getString("orderId");// 订单id
			String orderNo = row.getString("orderNo");// 订单编号
			String payablePriceStr = row.getString("payablePrice");// 订单应付价
			String paidPriceStr = row.getString("paidPrice");// 订单已付价
			String receiptNo = row.getString("receiptNo");// 支付回单号
			Integer receiptStatus = row.getInteger("receiptStatus");// 支付回单状态
			Integer amount = row.getInteger("amount");// 支付金额
			String bookName = row.getString("bookName");// 账套名称
			String bankName = row.getString("bankName");// 支付方式
			Integer payForOrderCount = row.getInteger("payForOrderCount");// 支付金额
			dto.setId(id);
			dto.setOrderNo(orderNo);
			dto.setOrderIdStr(orderId);
			dto.setPaidPriceStr(paidPriceStr);
			dto.setPayablePriceStr(payablePriceStr);
			dto.setReceiptNo(receiptNo);
			dto.setReceiptStatus(PayReceiptStatus.getItem(receiptStatus));
			dto.setPayForOrderCount(PayForOrderCountType.getItem(payForOrderCount));
			dto.setAmount(amount == null ? 0 : getDivRes(amount, 100));
			dto.setBookName(bookName);
			dto.setBankName(bankName);
			String addTimeStr = row.getString("addTime");// 订单生成时间
			Date returnTime = row.getDate("returnTime");// 回款时间
			dto.setAddTimeStr(addTimeStr);
			dto.setReturnTime(returnTime);
			reslis.add(dto);
		}

		return reslis;
	}

	// type：0查询sql 1获取页码sql
	protected StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
		StringBuffer sql = new StringBuffer();

		if (type == 0) {
			sql.append("SELECT p.`pkid` 'id',  ");
			sql.append("(CASE WHEN p.pay_for_order_count=0 THEN oi.`pkid` ELSE GROUP_CONCAT(oi.`pkid` SEPARATOR ',') END) 'orderId',  ");
			sql.append("(CASE WHEN p.pay_for_order_count=0 THEN oi.`no` ELSE GROUP_CONCAT(oi.`no` SEPARATOR ',') END) 'orderNo', ");
			sql.append("(CASE WHEN p.pay_for_order_count=0 THEN TRUNCATE(oi.`payable_price`/100,2) ELSE GROUP_CONCAT(CONCAT(oi.no,':',TRUNCATE(oi.`payable_price`/100,2)) SEPARATOR ',') END) 'payablePrice', ");
			sql.append("(CASE WHEN p.pay_for_order_count=0 THEN TRUNCATE(oi.`paid_price`/100,2) ELSE GROUP_CONCAT(CONCAT(oi.no,':',TRUNCATE(oi.`paid_price`/100,2)) SEPARATOR ',') END) 'payablePrice', ");
			sql.append("p.`pay_for_order_count` 'payForOrderCount', ");
			sql.append("p.`receipt_no` 'receiptNo',p.`receipt_status` 'receiptStatus',p.`amount`, ");
			sql.append("book.`name` 'bookName',book.`id` 'bookId',ub.`name` 'bankName',ub.`id` 'bankId', ");
			sql.append("(CASE WHEN p.pay_for_order_count=0 THEN oi.`add_time` ELSE GROUP_CONCAT(CONCAT(oi.no,':',oi.`add_time`) SEPARATOR ',') END) 'addTime', ");
			sql.append("p.confirm_time 'returnTime' ");
		} else {
			sql.append("SELECT COUNT(DISTINCT p.`pkid`) 'rcount' ");
		}
		sql.append("FROM so_pay p ");
		sql.append("JOIN so_order_pay_map opm ON p.`pkid`=opm.`pay_id` ");
		sql.append("JOIN so_order oi ON oi.`pkid`=opm.`order_id` ");
		sql.append("JOIN u8_bank_so_pay_map uopm ON uopm.`pay_id`=p.`pkid` AND uopm.`type`=0 ");
		sql.append("JOIN u8_bank ub ON ub.id=uopm.`u8_bank_id` ");
		sql.append("JOIN u8_set_of_books book ON book.`id`=ub.`set_of_books_id` ");
		sql.append("WHERE p.`success_status_id`= " + PaySuccessStatus.Success.getValue() + " AND offline_audit_status_id= " + AuditLogStatusType.AUDITPASS.getValue() + " ");

		// 订单号
		if (!StringManager.isNullOrEmpty(mapFilters.get("orderNo"))) {
			sql.append("AND oi.no LIKE " + mapFilters.get("orderNo") + " ");
		}

		// 回单编号
		if (!StringManager.isNullOrEmpty(mapFilters.get("receiptNo"))) {
			sql.append("AND p.real_name LIKE " + mapFilters.get("receiptNo") + " ");
		}

		// 支付编号
		if (!StringManager.isNullOrEmpty(mapFilters.get("id"))) {
			sql.append("AND p.`pkid` LIKE " + mapFilters.get("id") + "  ");
		}

		// 回单处理状态（0：未完成 1已完成：）payForOrderCount
		if (!StringManager.isNullOrEmpty(mapFilters.get("receiptStatus"))) {
			sql.append("AND p.receipt_status IN " + mapFilters.get("receiptStatus") + " ");
		}

		// 支付订单数量（0:一笔单单 1:一笔多单）
		if (!StringManager.isNullOrEmpty(mapFilters.get("payForOrderCount"))) {
			sql.append("AND p.pay_for_order_count  IN " + mapFilters.get("payForOrderCount") + " ");
		}

		// 账套id
		if (!StringManager.isNullOrEmpty(mapFilters.get("bookId"))) {
			sql.append("AND book.id = " + mapFilters.get("bookId") + " ");
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
		if (!StringManager.isNullOrEmpty(mapFilters.get("startReturnTime"))) {
			sql.append("AND p.confirm_time >= " + mapFilters.get("startReturnTime") + " ");
		}

		// 结束回款日期
		if (!StringManager.isNullOrEmpty(mapFilters.get("endReturnTime"))) {
			sql.append("AND p.confirm_time <= " + mapFilters.get("endReturnTime") + " ");
		}
		// 分页时，不用排序分组
		if (type == 0) {
			sql.append("GROUP BY p.`pkid` ");
			sql.append("ORDER BY p.`pkid` DESC ");
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
					if (mapKeyString.equals("returnTime") && splitString.equals(">=")) {
						mapKeyString = "startReturnTime";
					}
					if (mapKeyString.equals("returnTime") && splitString.equals("<=")) {
						mapKeyString = "endReturnTime";
					}
					map.put(mapKeyString, mapValString);
				}
				return map;
			}
		}
		return map;
	}

	// 获取查询的总条数

	private int getqueryListCount1(String sql) {
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();

		sqlBuffer.append("SELECT COUNT(*) rcount FROM( ");
		sqlBuffer.append(sql);
		sqlBuffer.append(" )tt ");

		Object rcount = this.pm.executeScalar(sqlBuffer.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}

	private double getDivRes(int a, int b) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format((float) a / b));
	}

}
