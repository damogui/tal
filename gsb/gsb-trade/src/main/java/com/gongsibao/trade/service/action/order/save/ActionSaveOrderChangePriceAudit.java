package com.gongsibao.trade.service.action.order.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.trade.SoOrder;

/**   
 * @ClassName:  ActionSaveOrderChangePriceAudit   
 * @Description:TODO 改价进行审核
 * 执行顺序：8
 * @author: 韩伟
 * @date:   2018年3月2日 下午5:11:39   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionSaveOrderChangePriceAudit implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		SoOrder soOrder = (SoOrder) ctx.getItem();
//		if (soOrder.getIsChangePrice()) {
//			
//		}
	}

}
//if (soOrder.getIsChangePrice() == 1) {
//    // 审核节点查询
//    List<List<BdAuditLog>> logs = new ArrayList<>();
//    addChangePriceAuditNode(logs, orderId, currentUserId, soOrder.getPlatformSource() == 32105);
//
//    // 插入
//    List<BdAuditLog> auditLogs = new ArrayList<>();
//    for (List<BdAuditLog> auditLog : logs) {
//        auditLogs.addAll(auditLog);
//    }
//    if (CollectionUtils.isNotEmpty(auditLogs)) {
//        bdAuditLogService.insertBatch(auditLogs);
//    }
//}

//private void addChangePriceAuditNode(List<List<BdAuditLog>> list, Integer orderPkId, Integer currentUserId, boolean isSp) throws AuditException {
//    int typeId = 1042; //改价类型
//    List<Integer> glyIds = ucUserService.findByRoleTag(RoleTag.ROLE_GLY);
//
//    SoOrder order = findById(orderPkId);
//    if (null == order) {
//        return;
//    }
//
//    int level = 0;
//
//    BdAuditLog addLog = new BdAuditLog();
//    addLog.setAddUserId(currentUserId);
//    addLog.setFormId(orderPkId);
//    addLog.setTypeId(typeId);
//    addLog.setContent("提交改价申请");
//    addLog.setRemark("提交改价申请");
//    addLog.setLevel(level);
//    addLog.setStatusId(AuditStatusUtils.AUDIT_PASS);   // 通过
//
//    // 提交申请人
//    list.add(new ArrayList<BdAuditLog>() {{
//        add(addLog);
//    }});
//
//    level++;
//
//    // 调用组织结构接口-查分公司总经理
//    List<Integer> compManagerIdList = null;
//
//    if (isSp) {
//        compManagerIdList = ucUserService.findByRoleTag(RoleTag.ROLE_GYSGLY);
//    } else {
//        compManagerIdList = ucUserService.getBranchBoss(currentUserId);
//    }
//    compManagerIdList.removeAll(glyIds);
//    if (CollectionUtils.isEmpty(compManagerIdList)) {
//        if (isSp) {
//            throw new AuditException("对不起，请联系公司宝，为您设置供应商管理员");
//        } else {
//            throw new AuditException("对不起，改价审核，找不到您的分公司总经理，请联系管理员设置后再下单");
//        }
//    }
//
//    List<BdAuditLog> managerLogs = new ArrayList<>();
//    for (Integer managerId : compManagerIdList) {
//        BdAuditLog mLog = new BdAuditLog();
//        mLog.setAddUserId(managerId);
//        mLog.setFormId(orderPkId);
//        mLog.setTypeId(typeId);
//        mLog.setContent(isSp ? "供应商管理员审核" : "分总审核");
//        mLog.setRemark("");
//        mLog.setLevel(level);
//        mLog.setStatusId(AuditStatusUtils.TO_AUDIT);   // 1052审核中
//        managerLogs.add(mLog);
//    }
//    list.add(managerLogs);
//}