package com.gongsibao.u8.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.dic.OrderIsManualVoucherType;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.u8.base.IManualVoucherOrderDTOService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.u8.base.IUserService;

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
		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM ( ");
		sqlBuffer.append("SELECT oi.pkid 'id',oi.no 'orderNo',oi.payable_price 'payablePrice',oi.paid_price 'paidPrice', ");
		sqlBuffer.append("oi.manual_voucher_status 'manualVoucherStatus',oi.is_manual_voucher 'isManualVoucher', ");
		sqlBuffer.append("oi.pay_time 'returnTime',  ");
		sqlBuffer.append("oi.add_time 'addTime' ");
		sqlBuffer.append("FROM so_order oi  ");
		sqlBuffer.append("JOIN uc_account a ON a.pkid = oi.account_id  ");
		sqlBuffer.append("LEFT JOIN crm_customer c ON c.account_id = oi.account_id AND c.is_invalid = 0  ");
		sqlBuffer.append("LEFT JOIN (SELECT MIN(pkid) 'pkid',customer_id,company_id FROM crm_customer_company_map GROUP BY customer_id) ccm ON ccm.customer_id = c.pkid ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri ON cri.pkid = ccm.company_id ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri1 ON oi.`company_id` = cri1.`pkid` ");
		sqlBuffer.append("WHERE oi.is_manual_voucher!=0 AND oi.paid_price>0 ");
		sqlBuffer.append("ORDER BY oi.pkid DESC )t ");
		sqlBuffer.append(filterString == null ? "" : " WHERE " + filterString);// 拼接sql语句的where条件
		paging.setTotalCount(getqueryListCount(sqlBuffer.toString()));
		sqlBuffer.append("LIMIT " + startIndex + ", " + paging.getPageSize() + " ");
		oql.setPaging(paging);
		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<ManualVoucherOrderDTO> reslis = new ArrayList<>();

		List<Integer> orderIdList = new ArrayList();

		for (IRow row : dataTable) {
			ManualVoucherOrderDTO dto = new ManualVoucherOrderDTO();
			Integer payablePrice = row.getInteger("payablePrice");// 订单应付价
			Integer paidPrice = row.getInteger("paidPrice");// 订单已付价
			dto.setId(row.getInteger("id"));
			dto.setOrderNo(row.getString("orderNo"));
			dto.setCustName(row.getString("custName"));
			dto.setIsManualVoucher(OrderIsManualVoucherType.values()[row.getInteger("isManualVoucher")]);
			dto.setManualVoucherStatus(OrderManualVoucherStatus.values()[row.getInteger("manualVoucherStatus")]);
			dto.setOperator(row.getString("operator"));
			dto.setPaidPrice(paidPrice == null ? 0 : getDivRes(paidPrice, 100));
			dto.setPayablePrice(payablePrice == null ? 0 : getDivRes(payablePrice, 100));
			dto.setAddTime(row.getDate("addTime"));
			dto.setReturnTime(row.getDate("returnTime"));
			orderIdList.add(dto.getId());
			reslis.add(dto);
		}

		
		IUserService userService = ServiceFactory.create(IUserService.class);
		//根据订单id集合获取订单操作员
		Map<Integer, String> operatorByOrderIdMap = userService.getOperatorByOrderIds(orderIdList);

		ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);
		//根据订单id集合获取订单客户名称
		Map<Integer, String> custNameByOrderIdList = soOrderService.getCustNameByOrderIdList(orderIdList);

		for (ManualVoucherOrderDTO dto : reslis) {
			dto.setOperator(operatorByOrderIdMap.get(dto.getId()));
			dto.setCustName(custNameByOrderIdList.get(dto.getId()));
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
}
