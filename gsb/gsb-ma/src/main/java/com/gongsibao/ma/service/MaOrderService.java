package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.MtableManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.entity.ma.dic.OrderAuditState;
import com.gongsibao.ma.base.IMaOrderService;

@Service
public class MaOrderService extends PersistableService<MaOrder> implements IMaOrderService {

    public MaOrderService(){
        super();
        this.type=MaOrder.class;
    }

	@Override
	public boolean leaderPass(Integer orderId) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("group_leader_audit_state",OrderAuditState.VERIFIED.getValue());
			updateBuilder.set("vp_audit_state", OrderAuditState.UNAUDIT.getValue());
			updateBuilder.where("id =" +orderId);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}

	@Override
	public boolean leaderNoPass(Integer orderId) {
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("group_leader_audit_state",OrderAuditState.AUDIT_FAILED.getValue());
			updateBuilder.where("id =" +orderId);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}

	@Override
	public boolean vpPass(Integer orderId) {
		
		//如果上传了【订金收据】，则将出售需求出售状态
//		MaOrder entity =  this.byId(orderId);
//		if(!StringManager.isNullOrEmpty(entity.getDepositReceipt())){
//
//			ISellingDemandService sellingDemandService = ServiceFactory.create(ISellingDemandService.class);
//			sellingDemandService.updateSelingStatus(entity.getSellingDemandId(),SelingStatus.RESERVE);
//		}

		//尾款收据 finalPaymentReceipt
		
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("vp_audit_state",OrderAuditState.VERIFIED.getValue());
			updateBuilder.where("id =" +orderId);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}

	@Override
	public boolean vpNoPass(Integer orderId) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("vp_audit_state",OrderAuditState.AUDIT_FAILED.getValue());
			updateBuilder.where("id =" +orderId);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}

	@Override
	public boolean submitAudit(Integer orderId) {
		
		//验证
		MaOrder entity =  this.byId(orderId);
		if(entity.getGroupLeaderAuditState() != null){
			
			throw new BusinessException("已经提交过，不要重复提交！");
		}

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("group_leader_audit_state",OrderAuditState.UNAUDIT.getValue());
			updateBuilder.set("vp_audit_state",null);
			updateBuilder.where("id =" +orderId);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}
}