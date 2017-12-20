package com.gongsibao.u8.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PaySuccessStatus;
import com.gongsibao.entity.trade.dto.ReceivablesAuditDTO;
import com.gongsibao.u8.base.IPayService;
import com.gongsibao.u8.base.IReceivablesAuditDTOService;

@Service
public class ReceivablesAuditDTOService extends PersistableService<ReceivablesAuditDTO> implements IReceivablesAuditDTOService {

	public ReceivablesAuditDTOService() {
		super();
		this.type = ReceivablesAuditDTO.class;
	}

	@Override
	public List<ReceivablesAuditDTO> queryList(Oql oql) {

		String filterString = oql.getFilter();
		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM ( ");
		sqlBuffer.append("SELECT bal.pkid 'id',p.pkid 'payId', ");
		sqlBuffer.append("(SELECT GROUP_CONCAT(so_order.`no` SEPARATOR ',') FROM so_order JOIN so_order_pay_map ON so_order_pay_map.`order_id`=so_order.`pkid` WHERE so_order_pay_map.`pay_id`=p.pkid) 'orderNos',  ");
		sqlBuffer.append("p.amount,GROUP_CONCAT(book.name SEPARATOR ',') 'bookName',GROUP_CONCAT(ub.name SEPARATOR ',') 'bankName',d.name 'auditStatusName',d.pkid 'auditStatusId', ");
		sqlBuffer.append("p.offline_payer_name 'offlinePayerName',p.offline_bank_no 'offlineBankNo',u.real_name 'applyUserName',bal.add_time 'addTime' ");
		sqlBuffer.append("FROM bd_audit_log bal  ");
		sqlBuffer.append("JOIN so_pay p ON bal.form_id = p.pkid AND bal.type_id=1045 ");
		sqlBuffer.append("JOIN bd_dict d ON d.pkid = bal.status_id AND d.type=105 ");
		sqlBuffer.append("JOIN u8_bank_so_pay_map uspm ON uspm.pay_id = p.pkid AND uspm.type=0 ");
		sqlBuffer.append("JOIN u8_set_of_books book ON book.id = uspm.set_of_books_id ");
		sqlBuffer.append("JOIN u8_bank ub ON ub.id = uspm.u8_bank_id ");
		sqlBuffer.append("JOIN uc_user u ON u.pkid = p.offline_add_user_id ");
		sqlBuffer.append("GROUP BY p.pkid,bal.pkid  ");
		sqlBuffer.append("ORDER BY bal.`pkid` DESC  ");
		sqlBuffer.append("LIMIT " + startIndex + ", " + paging.getPageSize() + ")t  ");
		sqlBuffer.append(filterString == null ? "" : "WHERE " + filterString);// 拼接sql语句的where条件

		paging.setTotalCount(getqueryListCount());
		oql.setPaging(paging);

		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<ReceivablesAuditDTO> reslis = new ArrayList<>();
		for (IRow row : dataTable) {
			ReceivablesAuditDTO dto = new ReceivablesAuditDTO();
			Integer id = row.getInteger("id");// 支付id
			Integer amount = row.getInteger("amount");// 支付金额
			String bookName = row.getString("bookName");// 账套名称
			String bankName = row.getString("bankName");// 支付方式
			dto.setId(id);
			dto.setAmount(amount == null ? 0 : getDivRes(amount, 100));
			dto.setApplyUserName(row.getString("applyUserName"));
			dto.setAuditStatusId(row.getInteger("auditStatusId"));
			dto.setBookName(bookName);
			dto.setBankName(bankName);
			dto.setOrderNos(row.getString("orderNos"));
			dto.setOfflineBankNo(row.getString("offlineBankNo"));
			dto.setOfflinePayerName(row.getString("offlinePayerName"));
			dto.setAuditStatusName(row.getString("auditStatusName"));
			dto.setPayId(row.getInteger("payId"));
			dto.setAddTime(row.getDate("addTime"));
			reslis.add(dto);
		}

		return reslis;
	}

	// 获取查询的总条数
	private int getqueryListCount() {
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT COUNT(bal.`pkid`) 'rcount' FROM bd_audit_log bal ");
		sqlBuffer.append("JOIN so_pay p ON bal.form_id = p.pkid AND bal.type_id=1045  ");
		sqlBuffer.append("JOIN bd_dict d ON d.pkid = bal.status_id AND d.type=105  ");
		sqlBuffer.append("JOIN u8_bank_so_pay_map uspm ON uspm.pay_id = p.pkid AND uspm.type=0  ");
		sqlBuffer.append("JOIN u8_set_of_books book ON book.id = uspm.set_of_books_id ");
		sqlBuffer.append("JOIN u8_bank ub ON ub.id = uspm.u8_bank_id ");
		sqlBuffer.append("JOIN uc_user u ON u.pkid = p.offline_add_user_id ");
		Object rcount = this.pm.executeScalar(sqlBuffer.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}

	private double getDivRes(int a, int b) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format((float) a / b));
	}

	@Override
	public Map<String, Object> getOrderInfoListByPayId(Integer payId) {
		Map<String, Object> resMap = new HashMap<>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT oi.no 'orderNo',opm.order_price 'orderPrice',opm.offline_installment_type 'installmentType',oi.`add_time` 'addTime', ");
		sqlBuffer.append("d.`name` 'platformSourceName',oi.`payable_price` 'payablePrice',oi.`payable_price`, ");
		sqlBuffer.append("CONCAT(oi.`account_name`,'-',oi.`account_id`) 'accountName',oi.`account_mobile` 'accountMobile', ");
		sqlBuffer.append("(CASE WHEN cri.`pkid` IS NOT NULL THEN CONCAT((CASE WHEN cri.`company_name`!='' THEN cri.`company_name` ELSE cri.`name` END),'-',cri.pkid) ELSE  ");
		sqlBuffer.append("(CASE WHEN cri1.pkid IS NULL THEN '无' ELSE CONCAT((CASE WHEN cri1.`company_name`!='' THEN cri1.`company_name` ELSE cri1.`name` END),'-',cri1.pkid) END) END) 'companyName' FROM so_order oi ");
		sqlBuffer.append("JOIN so_order_pay_map opm ON opm.`order_id`=oi.`pkid` ");
		sqlBuffer.append("JOIN bd_dict d ON d.`pkid`=oi.platform_source ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri ON cri.`pkid`=oi.`company_id` ");
		sqlBuffer.append("LEFT JOIN crm_customer c ON c.`account_id`= oi.`account_id` ");
		sqlBuffer.append("LEFT JOIN crm_customer_company_map ccm ON ccm.`customer_id`=c.`pkid` ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri1 ON cri1.`pkid`=ccm.`company_id` ");
		sqlBuffer.append("WHERE opm.`pay_id`=" + payId);

		DataTable dataTable = this.pm.executeTable(sqlBuffer.toString(), null);
		List<Map<String, Object>> orderInfoList = new ArrayList<>();
		for (IRow row : dataTable) {
			Map<String, Object> orderInfoMap = new HashMap();
			Integer orderPrice = row.getInteger("orderPrice");
			Integer payablePrice = row.getInteger("payablePrice");
			Integer paidPrice = row.getInteger("paidPrice");
			orderInfoMap.put("orderNo", row.getString("orderNo"));
			orderInfoMap.put("orderPrice", orderPrice == null ? 0 : getDivRes(orderPrice, 100));
			orderInfoMap.put("payablePrice", payablePrice == null ? 0 : getDivRes(payablePrice, 100));
			orderInfoMap.put("paidPrice", paidPrice == null ? 0 : getDivRes(paidPrice, 100));
			orderInfoMap.put("installmentTypeName", row.getString("installmentTypeName"));
			orderInfoMap.put("addTime", row.getDate("addTime"));
			orderInfoMap.put("platformSourceName", row.getString("platformSourceName"));
			orderInfoMap.put("accountMobile", row.getString("accountMobile"));
			orderInfoMap.put("accountName", row.getString("accountName"));
			orderInfoMap.put("companyName", row.getString("companyName"));
		}
		resMap.put("orderInfoList", orderInfoList);
		IPayService payService = ServiceFactory.create(IPayService.class);
		Pay pay = payService.byId(payId);
		resMap.put("payInfo", pay);
		return resMap;
	}

	//收款审核写到一半，发现要把之前的审核逻辑搬过来，工作量太大，所以暂时不挪过来了（20171218）
	@Override
	public Integer payAudit(Integer payId, Integer auditId, Integer auditStatusId, String remark, String confirmTime) {
		IAuditLogService auditLogService = ServiceFactory.create(IAuditLogService.class);
		IPayService payService = ServiceFactory.create(IPayService.class);
		int typeId = 1045;
		Oql auditLogOql = new Oql();
		{
			auditLogOql.setType(AuditLog.class);
			auditLogOql.setSelects("*");
			// type_id:审核类型序号，type=104，1041产品定价申请审核、1042订单改价申请审核、1043合同申请审核、1044发票申请审核、1045收款申请审核、1046退单申请审核、1047分期申请审核、1048产品改价申请审核
			// status_id:审核状态序号，type=105，1051 待审核、1052 审核中、1053 驳回审核、1054
			// 审核通过、1055排队、1056关闭
			auditLogOql.setFilter("form_id=" + payId + " AND type_id=" + typeId + " AND add_user_id={userId} AND status_id=1051");
		}
		List<AuditLog> auditLogList = auditLogService.queryList(auditLogOql);
		
		if (CollectionUtils.isEmpty(auditLogList)) {
			// 审核任务不存在
			return -2;
		}

		AuditLog auditLog = auditLogList.get(0);
		Integer level = auditLog.getLevel();
		Integer nextLevel = auditLog.getLevel() + 1;
		// 审核通过
		if (auditStatusId == AuditLogStatusType.AUDITPASS.getValue()) {
			// 将本条审核记录的审核状态改为【审核通过】
			auditLogService.updateStatus(auditId, auditStatusId, AuditLogStatusType.TOAUDIT.getValue(), remark);
			// 将与本条审核记录属于同一水平的记录的状态，更新为【关闭】，这样就不会出现重复审核的情况
			auditLogService.updateStatusByFidTidLev(payId, typeId, level, AuditLogStatusType.Close.getValue(), "=", auditId);
			// 将下一级的审核记录修改为【待审核】,当没有影响行数时，则说明这是最高一级别的审核了
			int rows = auditLogService.updateStatusByFidTidLev(payId, typeId, nextLevel, AuditLogStatusType.TOAUDIT.getValue(), "=", 0);
			if (rows == 0) {
				//跟新支付记录的状态
				payService.updateAuditStatus(payId, auditStatusId, AuditLogStatusType.TOAUDIT.getValue(), PaySuccessStatus.Success.getValue());
				
				
				
				
			}
		} else {

		}

		return 1;

	}

}
