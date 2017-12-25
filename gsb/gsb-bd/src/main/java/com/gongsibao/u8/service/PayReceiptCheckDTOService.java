package com.gongsibao.u8.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.dic.PayReceiptStatus;
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

		String filterString = oql.getFilter();
		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM ( ");
		sqlBuffer.append("SELECT p.`pkid` as id, oi.`pkid` orderId, oi.`no` orderNo,oi.`payable_price` 'payablePrice',oi.`paid_price` 'paidPrice', p.`receipt_no` 'receiptNo',p.`receipt_status` 'receiptStatus',p.`amount`, ");
		sqlBuffer.append("book.`name` 'bookName',book.`id` 'bookId',ub.`name` 'bankName',ub.`id` 'bankId',oi.`add_time` 'addTime', ");
		sqlBuffer.append("p.confirm_time 'returnTime' ");
		sqlBuffer.append("FROM so_pay p ");
		sqlBuffer.append("JOIN so_order_pay_map opm ON p.`pkid`=opm.`pay_id` ");
		sqlBuffer.append("JOIN so_order oi ON oi.`pkid`=opm.`order_id` ");
		sqlBuffer.append("JOIN u8_bank_so_pay_map uopm ON uopm.`pay_id`=p.`pkid` AND uopm.`type`=0 ");
		sqlBuffer.append("JOIN u8_bank ub ON ub.id=uopm.`u8_bank_id` ");
		sqlBuffer.append("JOIN u8_set_of_books book ON book.`id`=ub.`set_of_books_id` ");
		sqlBuffer.append("WHERE p.`success_status_id`=3123 AND offline_audit_status_id=1054 ");
		sqlBuffer.append("ORDER BY p.`pkid`  DESC )t  ");
		sqlBuffer.append(filterString == null ? "" : "WHERE " + filterString);// 拼接sql语句的where条件
		paging.setTotalCount(getqueryListCount(sqlBuffer.toString()));
		sqlBuffer.append("LIMIT " + startIndex + ", " + paging.getPageSize() + " ");

		oql.setPaging(paging);

		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<PayReceiptCheckDTO> reslis = new ArrayList<>();
		for (IRow row : dataTable) {
			PayReceiptCheckDTO dto = new PayReceiptCheckDTO();
			Integer id = row.getInteger("id");// 支付id
			Integer orderId = row.getInteger("orderId");// 订单id
			String orderNo = row.getString("orderNo");// 订单编号
			Integer payablePrice = row.getInteger("payablePrice");// 订单应付价
			Integer paidPrice = row.getInteger("paidPrice");// 订单已付价
			String receiptNo = row.getString("receiptNo");// 支付回单号
			Integer receiptStatus = row.getInteger("receiptStatus");// 支付回单状态
			Integer amount = row.getInteger("amount");// 支付金额
			String bookName = row.getString("bookName");// 账套名称
			String bankName = row.getString("bankName");// 支付方式
			dto.setId(id);
			dto.setOrderNo(orderNo);
			dto.setOrderId(orderId);
			dto.setPaidPrice(paidPrice == null ? 0 : getDivRes(paidPrice, 100));
			dto.setPayablePrice(payablePrice == null ? 0 : getDivRes(payablePrice, 100));
			dto.setReceiptNo(receiptNo);
			dto.setReceiptStatus(PayReceiptStatus.values()[receiptStatus]);
			dto.setAmount(amount == null ? 0 : getDivRes(amount, 100));
			dto.setBookName(bookName);
			dto.setBankName(bankName);
			Date addTime = row.getDate("addTime");// 订单生成时间
			Date returnTime = row.getDate("returnTime");// 回款时间
			dto.setAddTime(addTime);
			dto.setReturnTime(returnTime);
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

}
