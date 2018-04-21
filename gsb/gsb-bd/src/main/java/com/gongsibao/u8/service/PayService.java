package com.gongsibao.u8.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.u8.base.IOrderPayMapService;
import com.gongsibao.u8.base.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.u8.base.IPayService;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

	public PayService() {
		super();
		this.type = Pay.class;
	}

	@Override
	public Boolean changeReceiptStatus(int payId, PayReceiptStatus receiptStatus) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_pay");
			updateSql.set("receipt_status", receiptStatus.getValue());
			updateSql.where("pkid=" + payId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}

	@Override
	public int updateAuditStatus(int payId, int auditStatusId, int oldStatusId, int successStatusId) {
		String oldStatusIdWhereString = oldStatusId == 0 ? "" : " AND offline_audit_status_id=" + oldStatusId + "";
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_pay");
			updateSql.set("offline_audit_status_id", auditStatusId);
			updateSql.where("pkid=" + payId + "" + oldStatusIdWhereString + "");
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public Pay getById(int payId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", payId, Types.INTEGER);
		}
		return this.pm.queryFirst(oql);
	}

	@Override
	public Integer addPay(Pay soPay, Integer orderId, String uploadPayVoucher) {
		Integer payId = this.save(soPay).getId();
		logger.info("payId save："+payId);
		IOrderPayMapService soOrderPayMapService=ServiceFactory.create(IOrderPayMapService.class);
		OrderPayMap soOrderPayMap = new OrderPayMap();
		soOrderPayMap.setOrderId(orderId);
		soOrderPayMap.setPayId(payId);
		soOrderPayMapService.save(soOrderPayMap);
		IFileService bdFileService=ServiceFactory.create(IFileService.class);
		if (StringUtils.isNotEmpty(uploadPayVoucher)) {
			File bdFile = new File();
			bdFile.setName("网站用户线下支付的付款凭证图片");
			bdFile.setFormId(payId);
			bdFile.setTabName("so_pay");
			bdFile.setUrl(uploadPayVoucher);
			bdFile.setCreateTime(new Date());
			bdFile.setCreatorId(soPay.getOfflineAddUserId());
			bdFileService.save(bdFile);
		}

		if (soPay.getPayWayType().getValue() == 3102) {
			int typeId = 1045;
			List<AuditLog> auditLogList = new ArrayList<>();
			AuditLog bdAuditLog0 = new AuditLog();
			bdAuditLog0.setType(AuditLogType.getItem(typeId));
			bdAuditLog0.setFormId(payId);
			bdAuditLog0.setStatus(AuditLogStatusType.AUDITPASS);
			bdAuditLog0.setCreatorId(0);
			bdAuditLog0.setLevel(0);
			bdAuditLog0.setRemark("会员线下支付");
			auditLogList.add(bdAuditLog0);
			IUserService ucUserService= ServiceFactory.create(IUserService.class);
			IAuditLogService auditLogService= ServiceFactory.create(IAuditLogService.class);
//			List<Integer> userList = ucUserService.findByRoleTag(RoleTag.ROLE_STKZY);
//			userList = ucUserService.findIdsByEnabled(userList, 1);
//			userList.removeAll(ucUserService.findByRoleTag(RoleTag.ROLE_GLY));
//			if (CollectionUtils.isNotEmpty(userList)) {
//				for (Integer auditUserId : userList) {
//					AuditLog bdAuditLog1 = new AuditLog();
//					bdAuditLog1.setType(AuditLogType.getItem(typeId));
//					bdAuditLog1.setFormId(payId);
//					bdAuditLog1.setStatus(AuditLogStatusType.TOAUDIT);
//					bdAuditLog1.setCreatorId(auditUserId);
//					bdAuditLog1.setLevel(1);
//					bdAuditLog1.setRemark("财务审核");
//					auditLogList.add(bdAuditLog1);
//				}
//			}
			auditLogService.saves(auditLogList);
		}

		return payId;
	}
}
