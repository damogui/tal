package com.gongsibao.trade.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gongsibao.trade.base.IPayReceiptCheckDTOService;

@Service
public class PayReceiptCheckDTOService extends PersistableService<PayReceiptCheckDTO> implements IPayReceiptCheckDTOService {
	
    public PayReceiptCheckDTOService(){
        super();
        this.type=PayReceiptCheckDTO.class;
    }

	@Override
	public List<PayReceiptCheckDTO> queryList(Oql oql) {

		Paging paging = oql.getPaging();
		int startIndex = (paging.pageNo-1)*paging.pageSize;
		StringBuffer sqlBuffer =new StringBuffer();
		sqlBuffer.append("SELECT p.`pkid` as id, oi.`pkid` orderId, oi.`no` orderNo,oi.`payable_price` 'payablePrice',oi.`paid_price` 'paidPrice', p.`receipt_no` 'receiptNo',p.`receipt_status` 'receiptStatus',p.`amount`, ");
		sqlBuffer.append("book.`name` 'bookName',ub.`name` 'bankName',oi.`add_time` 'addTime', ");
		sqlBuffer.append("(CASE p.pay_way_type_id WHEN 3101 THEN p.confirm_time WHEN 3102 THEN (SELECT add_time FROM bd_audit_log WHERE type_id = 1045 AND form_id = p.`pkid` AND status_id = 1054 ORDER BY LEVEL DESC LIMIT 1) ELSE NULL END) 'returnTime' ");
		sqlBuffer.append("FROM so_pay p ");
		sqlBuffer.append("JOIN so_order_pay_map opm ON p.`pkid`=opm.`pay_id` ");
		sqlBuffer.append("JOIN so_order oi ON oi.`pkid`=opm.`order_id` ");
		sqlBuffer.append("JOIN u8_bank_so_pay_map uopm ON uopm.`pay_id`=p.`pkid` AND uopm.`type`=0 ");
		sqlBuffer.append("JOIN u8_bank ub ON ub.id=uopm.`u8_bank_id` ");
		sqlBuffer.append("JOIN u8_set_of_books book ON book.`id`=ub.`set_of_books_id` ");
		sqlBuffer.append("WHERE p.`success_status_id`=3123 AND offline_audit_status_id=1054 ");
		sqlBuffer.append("LIMIT "+startIndex+", "+paging.pageSize+" ");
		
		paging.totalCount=getqueryListCount();
		oql.setPaging(paging);
		
		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<PayReceiptCheckDTO> reslis = new ArrayList<>();
		for (IRow row : dataTable) {
			PayReceiptCheckDTO dto = new PayReceiptCheckDTO();			
			Integer id = getInteger(row.getString("id"));//支付id
			Integer orderId = getInteger(row.getString("orderId"));//订单id
			String orderNo = getString(row.getString("orderNo"));// 订单编号
			Integer payablePrice = getInteger(row.getString("payablePrice"));// 订单应付价
			Integer paidPrice = getInteger(row.getString("paidPrice"));// 订单已付价
			String receiptNo = getString(row.getString("receiptNo"));// 支付回单号
			Integer receiptStatus = getInteger(row.getString("receiptStatus"));// 支付回单状态
			Integer amount = getInteger(row.getString("amount"));// 支付金额
			String bookName = getString(row.getString("bookName"));// 账套名称
			String bankName = getString(row.getString("bankName"));//支付方式
			
			dto.setId(id);
			dto.setOrderNo(orderNo);
			dto.setOrderId(orderId);
			dto.setPaidPrice(paidPrice);
			dto.setPayablePrice(payablePrice);
			dto.setReceiptNo(receiptNo);
			dto.setReceiptStatus(PayReceiptStatus.values()[receiptStatus]);
			dto.setAmount(amount);
			dto.setBookName(bookName);
			dto.setBankName(bankName);
			
			try {
				SimpleDateFormat sdf =new SimpleDateFormat();
				Date addTime = sdf.parse(row.getString("addTime"));// 总客户数量
				Date returnTime = sdf.parse(row.getString("returnTime"));// 总客户数量
				dto.setAddTime(addTime);
				dto.setReturnTime(returnTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reslis.add(dto);			
		}
		
		return reslis;
	}
	
	
	private int getqueryListCount(){
		int count=0;
		StringBuffer sqlBuffer =new StringBuffer();
		sqlBuffer.append("SELECT COUNT(p.pkid) 'rcount' FROM so_pay p ");
		sqlBuffer.append("JOIN so_order_pay_map opm ON p.`pkid`=opm.`pay_id` ");
		sqlBuffer.append("JOIN so_order oi ON oi.`pkid`=opm.`order_id` ");
		sqlBuffer.append("JOIN u8_bank_so_pay_map uopm ON uopm.`pay_id`=p.`pkid` AND uopm.`type`=0 ");
		sqlBuffer.append("JOIN u8_bank ub ON ub.id=uopm.`u8_bank_id` ");
		sqlBuffer.append("JOIN u8_set_of_books book ON book.`id`=ub.`set_of_books_id` ");
		sqlBuffer.append("WHERE p.`success_status_id`=3123 AND offline_audit_status_id=1054 ");
		count =(int)	this.pm.executeScalar(sqlBuffer.toString(), null);		
		return count;
	}
	
	
	
	
	
	
	private Integer getInteger(String val) {
		return val==null?0:Integer.parseInt(val);
	}
	
	private String getString(String val) {
		return val==null?"":val;
	}
    
    
}
