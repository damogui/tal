package com.gongsibao.report.service.perfrmance.salesman;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.report.utils.DateUtils;

public class PerfrmanceSalesmanDayService extends AbstractPerfrmanceSalesmanService {

	@Override
	public void doExecute() {

		Date date = context.getDate();
		String strDate = DateUtils.formatDate(date);
		
		List<UserOrganizationMap> mapList = context.getMapList();
		int i=0;
		for (UserOrganizationMap map : mapList) {
			Integer salesmanId = map.getUserId();
			Integer organizationId = map.getOrganizationId();
			PerformanceStatistics entity = create(context.getDate(), salesmanId, organizationId);
			Map<String, Integer> getOrderMap = orderRelated(strDate,salesmanId);
			
			entity.setReceivableAmount(getOrderMap.get("payablePriceCount"));
			entity.setPaidAmount(getOrderMap.get("payCount"));
			entity.setRefundAmount(getOrderMap.get("refundCount"));
			entity.setNetReceivables(getOrderMap.get("netPayablePrice"));
			entity.setNetPaidAmount(getOrderMap.get("netGetAmout"));
			entity.setProductCount(getOrderMap.get("salesCount"));
			entity.setOrderCount(getOrderMap.get("orderCount"));	
						
			this.getStatisticsService().save(entity);
			System.out.println(++i);
		}
		System.out.println("ok...... ");
	}

	/**
	 * 返回订单相关（应收金额、退款额、销售量、订单量、）
	 * @param date 当天时间
	 * @param userId 用户Id
	 * @return 应收金额、退款额、销售量、订单量
	 */
	private Map<String, Integer> orderRelated(String date, Integer userId) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		//应收金额
		Integer payablePriceCount = new Integer(0);
		//退款额
		Integer refundAmount = new Integer(0);
		//实收金额
		Integer payAmount = new Integer(0);
		
		//销售量
		int salesCount=0;
		//订单量
		int orderCount=0;
		//订单Id集合
		String orderList = "";
		//线上支付
		StringBuilder onLinePay=new StringBuilder();
		onLinePay.append("SELECT o.pkid 'orderId',");
		onLinePay.append("o.payable_price 'payablePrice',");
		onLinePay.append("o.paid_price 'paidPrice',");
		onLinePay.append("o.is_installment 'isInstallMent',");
		onLinePay.append("o.installment_mode 'installMentMode'");
		onLinePay.append(" from(SELECT userMap.user_id,pr.order_id,pr.product_id");
		onLinePay.append(" from so_order_prod_user_map userMap");
		onLinePay.append(" LEFT JOIN so_order_prod pr");
		onLinePay.append(" on userMap.order_prod_id=pr.pkid");
		onLinePay.append(" where userMap.type_id=3061");
		onLinePay.append(" and userMap.status_id=3141");
		onLinePay.append(" and userMap.user_id="+userId);
		onLinePay.append(") as one");
		onLinePay.append(" LEFT JOIN so_order o");
		onLinePay.append(" on o.pkid=one.order_id");
		onLinePay.append(" LEFT JOIN so_order_pay_map op");
		onLinePay.append(" on o.pkid=op.order_id");
		onLinePay.append(" LEFT JOIN so_pay p");
		onLinePay.append(" on op.pay_id=p.pkid");
		onLinePay.append(" where p.pay_way_type_id <> 3102");
		onLinePay.append(" and p.confirm_time LIKE CONCAT('%','"+date+"','%')");
		onLinePay.append(" GROUP BY o.pkid");
		DataTable onLineDt = this.pm.executeTable(onLinePay.toString(), null);
		for (IRow row : onLineDt) {
			Integer orderId = Integer.parseInt(row.getString("orderId"));
			Integer paidPrice = Integer.parseInt(row.getString("paidPrice"));
			Integer getRefundAmout = refundAmount(orderId);
			//如果退款金额大于支付金额改支付不计在内
			if(getRefundAmout.intValue()>=paidPrice.intValue()){
				continue;
			}
			Integer payablePrice = Integer.parseInt(row.getString("payablePrice"));			
			//0:全款、1：分期
			Integer isInstallMent = Integer.parseInt(row.getString("isInstallMent"));
			String installMentMode = row.getString("installMentMode");
			if(isInstallMent.equals(0)){
				if(paidPrice.intValue()>=payablePrice.intValue()){
					orderCount+=1;
					payablePriceCount = payablePriceCount.intValue() + payablePrice.intValue();
					orderList += orderId + ",";
				}
			}else{
				String [] getStr= installMentMode.split("\\|");
				if(paidPrice.intValue()>=Integer.parseInt(getStr[0])){
					orderCount+=1;
					payablePriceCount = payablePriceCount.intValue() + payablePrice.intValue();
					orderList += orderId + ",";
				}
			}
			refundAmount=refundAmount.intValue() + getRefundAmout.intValue();
		}
		//线下支付
		StringBuilder offLinePay=new StringBuilder();
		offLinePay.append("SELECT * from (SELECT o.pkid 'orderId',");
		offLinePay.append("o.payable_price 'payablePrice',");
		offLinePay.append("o.paid_price 'paidPrice',");
		offLinePay.append("o.is_installment 'isInstallMent',");
		offLinePay.append("o.installment_mode 'installMentMode',");
		offLinePay.append("userMap.user_id");
		offLinePay.append(" from (SELECT op.order_id 'orderId'");
		offLinePay.append(" from (SELECT form_id,add_time");
		offLinePay.append(" from bd_audit_log");
		offLinePay.append(" where pkid in(SELECT MAX(pkid) from bd_audit_log");
		offLinePay.append(" where type_id=1045 and status_id=1054 GROUP BY form_id)");
		offLinePay.append(" and add_time LIKE CONCAT('%','"+date+"','%')) log");
		offLinePay.append(" LEFT JOIN so_pay p");
		offLinePay.append(" ON log.form_id=p.pkid");
		offLinePay.append(" LEFT JOIN so_order_pay_map op");
		offLinePay.append(" on p.pkid= op.pay_id");
		offLinePay.append(" WHERE p.pay_way_type_id = 3102) as one");
		offLinePay.append(" LEFT JOIN so_order o");
		offLinePay.append(" ON one.orderId=o.pkid");
		offLinePay.append(" LEFT JOIN so_order_prod pr");
		offLinePay.append(" on one.orderId=pr.order_id");
		offLinePay.append(" LEFT JOIN so_order_prod_user_map userMap");
		offLinePay.append(" on pr.pkid = userMap.order_prod_id");
		offLinePay.append(" where userMap.type_id=3061");
		offLinePay.append(" and userMap.status_id=3141");
		offLinePay.append(" GROUP BY o.pkid )as two");
		offLinePay.append(" WHERE two.user_id="+userId);
		
		DataTable offLineDt = this.pm.executeTable(offLinePay.toString(), null);
		for (IRow row : offLineDt) {
			Integer orderId = Integer.parseInt(row.getString("orderId"));
			Integer paidPrice = Integer.parseInt(row.getString("paidPrice"));
			Integer getRefundAmout = refundAmount(orderId);
			//如果退款金额大于支付金额改支付不计在内
			if(getRefundAmout.intValue()>=paidPrice.intValue()){
				continue;
			}
			Integer payablePrice = Integer.parseInt(row.getString("payablePrice"));
			
			
			//0:全款、1：分期
			String isInstallMent = row.getString("isInstallMent");
			String installMentMode = row.getString("installMentMode");
			
			if(isInstallMent.equals("0")||isInstallMent.equals("false")){
				if(paidPrice.intValue()>=payablePrice.intValue()){
					orderCount+=1;
					payablePriceCount = payablePriceCount.intValue() + payablePrice.intValue();
					orderList += orderId + ",";
				}
			}else{
				String [] getStr= installMentMode.split("\\|");
				if(paidPrice.intValue()>=Integer.parseInt(getStr[0])){
					orderCount+=1;
					payablePriceCount = payablePriceCount.intValue() + payablePrice.intValue();
					orderList += orderId + ",";
				}
			}
			refundAmount = refundAmount.intValue() + getRefundAmout.intValue();
		}
		if(!"".equals(orderList)){
			//销售量
			salesCount = salesAmount(orderList);
			//实收金额
			payAmount = payAmount(orderList);
		}
		resultMap.put("payablePriceCount", payablePriceCount);
		resultMap.put("payCount", payAmount);
		resultMap.put("refundCount", refundAmount);
		resultMap.put("netPayablePrice", payablePriceCount.intValue()-refundAmount.intValue());
		resultMap.put("netGetAmout", payAmount.intValue()-refundAmount.intValue());
		resultMap.put("salesCount", salesCount);
		resultMap.put("orderCount", orderCount);
		return resultMap;
	}
	/***
	 * 根据订单Id获取退款额
	 * @param orderId 订单Id
	 * @return
	 */
	private Integer refundAmount(Integer orderId) {
		Integer getRefund = new Integer(0);
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(amount) as 'amout'");
		strSql.append(" from so_refund");
		strSql.append(" where order_id =" + orderId);
		strSql.append(" GROUP BY order_id");
		DataTable refundDt = this.pm.executeTable(strSql.toString(), null);
		for (IRow row : refundDt) {
			getRefund = Integer.parseInt(row.getString("amout"));
		}
		return getRefund;
	}
	/***
	 * 根据订单Id的集合，获取销售量
	 * @param orderList 订单Id集合
	 * @return
	 */
	private Integer salesAmount(String orderList) {
		Integer getSales = new Integer(0);
		//销售量
		StringBuilder salesStr=new StringBuilder();
		salesStr.append("SELECT count(distinct pr.pkid) as 'count'");
		salesStr.append(" from so_order_prod pr"); 
		salesStr.append(" where pr.order_id IN("+ orderList.substring(0,orderList.length()-1) +")");
		DataTable salesDt = this.pm.executeTable(salesStr.toString(), null);
		for (IRow row : salesDt) {
			getSales = Integer.parseInt(row.getString("count"));
		}		
		return getSales;
	}
	/***
	 * 根据订单Id的集合，获取实收金额
	 * @param orderList 订单Id集合
	 * @return
	 */
	private Integer payAmount(String orderList) {
		Integer getPay = new Integer(0);
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT SUM(amount) as 'amout' from so_order_pay_map map");
		strSql.append(" LEFT JOIN so_pay p");
		strSql.append(" on map.pay_id = p.pkid");
		strSql.append(" where map.order_id in("+ orderList.substring(0,orderList.length()-1) +")");
		DataTable refundDt = this.pm.executeTable(strSql.toString(), null);
		for (IRow row : refundDt) {
			getPay = Integer.parseInt(row.getString("amout"));
		}
		return getPay;
	}
	
	private PerformanceStatistics create(Date date, Integer salesmanId, Integer departmentId) {
		PerformanceStatistics entity = new PerformanceStatistics();
		entity.toNew();
		entity.setDepartmentId(departmentId);
		entity.setSalesmanId(salesmanId);
		entity.setDateType(ReportDateType.DAY);
		entity.setOrganizationType(ReportOrganizationType.SALESMAN);
		entity.setWeek(this.context.getWeek());
		entity.setSeason(this.context.getSeason());
		entity.setMonth(this.context.getMonth());
		entity.setYear(this.context.getYear());
		entity.setDay(this.context.getDay());
		entity.setDate(this.context.getDate());

		return entity;
	}

	@Override
	public void before() {

		AbstractPerfrmanceService netService = new PerfrmanceSalesmanWeekService();
		netService.setContext(context);
		this.setNextService(netService);
	}

	@Override
	protected ReportDateType getReportDateType() {

		return ReportDateType.DAY;
	}
}
