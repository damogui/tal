package com.gongsibao.u8.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.dic.OrderIsManualVoucher;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.u8.base.IManualVoucherOrderDTOService;

@Service
public class ManualVoucherOrderDTOService extends PersistableService<ManualVoucherOrderDTO> implements IManualVoucherOrderDTOService {
	
    public ManualVoucherOrderDTOService(){
        super();
        this.type=ManualVoucherOrderDTO.class;
    }

	@Override
	public List<ManualVoucherOrderDTO> queryList(Oql oql) {

	    String filterString =	oql.getFilter();
		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo()-1)*paging.getPageSize();
		StringBuffer sqlBuffer =new StringBuffer();
		sqlBuffer.append("SELECT * FROM ( ");
		sqlBuffer.append("SELECT (SELECT uc_user.`real_name` FROM so_order  ");
		sqlBuffer.append("JOIN (SELECT MIN(so_order_prod.pkid) 'pkid', so_order_prod.order_id FROM so_order_prod GROUP BY order_id) so_order_prod1 ON so_order.`pkid`= so_order_prod1.`order_id`  ");
		sqlBuffer.append("JOIN so_order_prod_user_map ON so_order_prod_user_map.`order_prod_id`=so_order_prod1.pkid AND `so_order_prod_user_map`.`type_id`=3061 AND `so_order_prod_user_map`.`status_id`=3141  ");
		sqlBuffer.append("JOIN uc_user ON uc_user.`pkid` = so_order_prod_user_map.`user_id` WHERE so_order.`pkid`= oi.pkid) 'operator', ");
		sqlBuffer.append("(CASE WHEN (cri1.`pkid` IS NOT NULL AND cri1.`company_name`!='' ) THEN cri1.`company_name`  ");
		sqlBuffer.append("WHEN (c.pkid IS NULL) THEN (CASE WHEN a.real_name='' THEN a.name ELSE a.real_name END) ");
		sqlBuffer.append("WHEN (ccm.pkid IS NULL OR cri.company_name='') THEN (CASE WHEN c.real_name='' THEN c.`mobile` ELSE c.real_name END) ELSE cri.company_name END) 'custName', ");
		sqlBuffer.append("oi.pkid 'id',oi.no 'orderNo',oi.payable_price 'payablePrice',oi.paid_price 'paidPrice', ");
		sqlBuffer.append("oi.manual_voucher_status 'manualVoucherStatus',oi.is_manual_voucher 'isManualVoucher', ");
		sqlBuffer.append("(SELECT (CASE so_pay.`pay_way_type_id` WHEN 3101 THEN so_pay.`confirm_time` ELSE bd_audit_log.`add_time` END) FROM so_order  ");		
		sqlBuffer.append("JOIN (SELECT order_id,MIN(pay_id) 'pay_id' FROM so_order_pay_map GROUP BY order_id) opm ON opm.`order_id`=so_order.`pkid` ");
		sqlBuffer.append("JOIN so_pay ON opm.`pay_id`=so_pay.`pkid` ");
		sqlBuffer.append("LEFT JOIN bd_audit_log ON bd_audit_log.`form_id`=opm.pay_id AND type_id = 1045 AND status_id = 1054 ");
		sqlBuffer.append("WHERE so_pay.success_status_id=3123 AND offline_audit_status_id=1054 AND so_order.`pkid`=oi.pkid ");
		sqlBuffer.append("ORDER BY bd_audit_log.LEVEL LIMIT 1) 'returnTime',  ");
		sqlBuffer.append("oi.add_time 'addTime' ");
		sqlBuffer.append("FROM so_order oi  ");
		sqlBuffer.append("JOIN uc_account a ON a.pkid = oi.account_id  ");
		sqlBuffer.append("LEFT JOIN crm_customer c ON c.account_id = oi.account_id AND c.is_invalid = 0  ");
		sqlBuffer.append("LEFT JOIN (SELECT MIN(pkid) 'pkid',customer_id,company_id FROM crm_customer_company_map GROUP BY customer_id) ccm ON ccm.customer_id = c.pkid ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri ON cri.pkid = ccm.company_id ");		
		sqlBuffer.append("LEFT JOIN crm_company_intention cri1 ON oi.`company_id` = cri1.`pkid` ");
		sqlBuffer.append("WHERE oi.is_manual_voucher!=0 AND oi.paid_price>0)t ");	
		sqlBuffer.append(filterString==null?"":"WHERE "+filterString);//拼接sql语句的where条件
		sqlBuffer.append("ORDER BY t.id DESC ");
		sqlBuffer.append(" LIMIT "+startIndex+", "+paging.getPageSize()+" ");
		
		paging.setTotalCount(getqueryListCount());
		oql.setPaging(paging);
		
		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<ManualVoucherOrderDTO> reslis = new ArrayList<>();
		for (IRow row : dataTable) {
			ManualVoucherOrderDTO dto = new ManualVoucherOrderDTO();	
			
			Integer payablePrice = row.getInteger("payablePrice");// 订单应付价
			Integer paidPrice = row.getInteger("paidPrice");// 订单已付价	
			dto.setId(row.getInteger("id"));
			dto.setOrderNo(row.getString("orderNo"));			
			dto.setCustName(row.getString("custName"));
			dto.setIsManualVoucher(OrderIsManualVoucher.values()[row.getInteger("isManualVoucher")]);
			dto.setManualVoucherStatus(OrderManualVoucherStatus.values()[row.getInteger("manualVoucherStatus")] );
			dto.setOperator(row.getString("operator"));
			dto.setPaidPrice(paidPrice==null?0:getDivRes(paidPrice,100));
			dto.setPayablePrice(payablePrice==null?0:getDivRes(payablePrice,100));
     		dto.setAddTime(row.getDate("addTime"));
			dto.setReturnTime(row.getDate("returnTime"));
			
			reslis.add(dto);
		}
		
		return reslis;
	}
	
	//获取查询的总条数
	private int getqueryListCount(){
		int count=0;
		StringBuffer sqlBuffer =new StringBuffer();
		sqlBuffer.append("SELECT  COUNT(*) 'rcount' FROM so_order oi WHERE oi.is_manual_voucher!=0 AND oi.paid_price>0 ");
		Object	rcount = this.pm.executeScalar(sqlBuffer.toString(), null);	
		count = Integer.parseInt(rcount.toString());
		return count;
	}
	
	
	
	private double getDivRes(int a,int b){
		DecimalFormat df=new DecimalFormat("0.00");		
		return Double.parseDouble(df.format((float)a/b));
	}
}
