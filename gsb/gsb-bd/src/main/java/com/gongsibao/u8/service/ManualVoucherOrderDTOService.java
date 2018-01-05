package com.gongsibao.u8.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.dic.OrderIsManualVoucherType;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.u8.base.IManualVoucherOrderDTOService;

@Service
public class ManualVoucherOrderDTOService extends PersistableService<ManualVoucherOrderDTO> implements IManualVoucherOrderDTOService {

	public ManualVoucherOrderDTOService() {
		super();
		this.type = ManualVoucherOrderDTO.class;
	}

	//
	@Override
	public List<ManualVoucherOrderDTO> queryList(Oql oql) {

		String filterString = oql.getFilter();
		// filterString = StringManager.isNullOrEmpty(filterString) ? "" :
		// filterString.replace("%", "");
		HashMap<String, String> mapFilters = new HashMap<String, String>();
		try {
			mapFilters = getMapFilters(filterString);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM ( ");
		sqlBuffer.append("SELECT (SELECT uc_user.`real_name` FROM so_order ");
		sqlBuffer.append("JOIN (SELECT MIN(so_order_prod.pkid) 'pkid', so_order_prod.order_id FROM so_order_prod GROUP BY order_id) so_order_prod1 ON so_order.`pkid`= so_order_prod1.`order_id`  ");
		sqlBuffer.append("JOIN so_order_prod_user_map ON so_order_prod_user_map.`order_prod_id`=so_order_prod1.pkid AND `so_order_prod_user_map`.`type_id`=3061 AND `so_order_prod_user_map`.`status_id`=3141   ");
		sqlBuffer.append("JOIN uc_user ON uc_user.`pkid` = so_order_prod_user_map.`user_id` WHERE so_order.`pkid`= oi.pkid) 'operator', ");
		sqlBuffer.append("(SELECT (CASE WHEN (cri1.`pkid` IS NOT NULL AND cri1.`company_name`!='' ) THEN cri1.`company_name`   ");
		sqlBuffer.append("WHEN (crm_customer.pkid IS NULL) THEN (CASE WHEN uc_account.real_name='' THEN uc_account.name ELSE uc_account.real_name END)  ");
		sqlBuffer.append("WHEN (crm_customer_company_map.pkid IS NULL OR crm_company_intention.company_name='') THEN  ");
		sqlBuffer.append("(CASE WHEN crm_customer.real_name='' THEN crm_customer.`mobile` ELSE crm_customer.real_name END) ELSE crm_company_intention.company_name END) 'custName'  ");
		sqlBuffer.append("FROM so_order  ");
		sqlBuffer.append("JOIN uc_account  ON uc_account.pkid = so_order.account_id  ");
		sqlBuffer.append("LEFT JOIN crm_customer ON crm_customer.account_id = uc_account.pkid  ");
		sqlBuffer.append("LEFT JOIN crm_customer_company_map ON customer_id = crm_customer.pkid  ");
		sqlBuffer.append("LEFT JOIN crm_company_intention ON crm_company_intention.pkid = crm_customer_company_map.company_id  ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri1 ON cri1.pkid = so_order.company_id  ");
		sqlBuffer.append("WHERE so_order.pkid = oi.`pkid` LIMIT 1) 'custName',  ");
		sqlBuffer.append("oi.pkid 'id',oi.no 'orderNo',oi.payable_price 'payablePrice',oi.paid_price 'paidPrice',  ");
		sqlBuffer.append("oi.manual_voucher_status 'manualVoucherStatus',oi.is_manual_voucher 'isManualVoucher',  ");
		sqlBuffer.append("(SELECT (CASE so_pay.`pay_way_type_id` WHEN 3101 THEN so_pay.`confirm_time` ELSE bd_audit_log.`add_time` END) FROM so_order   ");
		sqlBuffer.append("JOIN (SELECT order_id,MIN(pay_id) 'pay_id' FROM so_order_pay_map GROUP BY order_id) opm ON opm.`order_id`=so_order.`pkid`  ");
		sqlBuffer.append("JOIN so_pay ON opm.`pay_id`=so_pay.`pkid`  ");
		sqlBuffer.append("LEFT JOIN bd_audit_log ON bd_audit_log.`form_id`=opm.pay_id AND type_id = 1045 AND status_id = 1054  ");
		sqlBuffer.append("WHERE so_pay.success_status_id=3123 AND offline_audit_status_id=1054 AND so_order.`pkid`=oi.pkid  ");
		sqlBuffer.append("ORDER BY bd_audit_log.LEVEL LIMIT 1) 'returnTime',  ");
		sqlBuffer.append("oi.add_time 'addTime'  ");
		sqlBuffer.append("FROM so_order oi  ");
		sqlBuffer.append("WHERE oi.is_manual_voucher!=0 AND oi.paid_price>0  ");
		// 订单号
		if (!StringManager.isNullOrEmpty(mapFilters.get("orderNo"))) {
			sqlBuffer.append("AND oi.no LIKE " + mapFilters.get("orderNo") + " ");
		}
		// 手动原因
		if (!StringManager.isNullOrEmpty(mapFilters.get("isManualVoucher"))) {
			sqlBuffer.append("AND oi.is_manual_voucher in " + mapFilters.get("isManualVoucher") + " ");
		}
		// 凭证状态
		if (!StringManager.isNullOrEmpty(mapFilters.get("manualVoucherStatus"))) {
			sqlBuffer.append("AND oi.manual_voucher_status in " + mapFilters.get("manualVoucherStatus") + " ");
		}
		// 订单创建开始时间
		if (!StringManager.isNullOrEmpty(mapFilters.get("startAddTime"))) {
			sqlBuffer.append("AND oi.add_time >= " + mapFilters.get("startAddTime") + " ");
		}
		// 订单创建结束时间
		if (!StringManager.isNullOrEmpty(mapFilters.get("endAddTime"))) {
			sqlBuffer.append("AND oi.add_time <= " + mapFilters.get("endAddTime") + " ");
		}
		sqlBuffer.append("ORDER BY oi.pkid DESC )t ");
		sqlBuffer.append(StringManager.isNullOrEmpty(filterString) ? "" : " WHERE " + filterString);// 拼接sql语句的where条件
		paging.setTotalCount(getqueryListCount(sqlBuffer.toString()));
		sqlBuffer.append("LIMIT " + startIndex + ", " + paging.getPageSize() + " ");
		oql.setPaging(paging);
		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<ManualVoucherOrderDTO> reslis = new ArrayList<>();

		List<Integer> orderIdList = new ArrayList<Integer>();

		for (IRow row : dataTable) {
			ManualVoucherOrderDTO dto = new ManualVoucherOrderDTO();
			Integer payablePrice = row.getInteger("payablePrice");// 订单应付价
			Integer paidPrice = row.getInteger("paidPrice");// 订单已付价
			dto.setId(row.getInteger("id"));
			dto.setOrderNo(row.getString("orderNo"));
			dto.setCustName(row.getString("custName"));
			dto.setIsManualVoucher(OrderIsManualVoucherType.getItem(row.getInteger("isManualVoucher")));
			dto.setManualVoucherStatus(OrderManualVoucherStatus.getItem(row.getInteger("manualVoucherStatus")));
			dto.setOperator(row.getString("operator"));
			dto.setPaidPrice(paidPrice == null ? 0 : getDivRes(paidPrice, 100));
			dto.setPayablePrice(payablePrice == null ? 0 : getDivRes(payablePrice, 100));
			dto.setAddTime(row.getDate("addTime"));
			dto.setReturnTime(row.getDate("returnTime"));
			orderIdList.add(dto.getId());
			reslis.add(dto);
		}

		return reslis;
	}

	// 获取查询的总条数
	private int getqueryListCount(String sql) {
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
