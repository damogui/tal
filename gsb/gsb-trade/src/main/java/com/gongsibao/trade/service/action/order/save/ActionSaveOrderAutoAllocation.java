package com.gongsibao.trade.service.action.order.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

/**   
 * @ClassName:  ActionSaveOrderAllocation   
 * @Description:TODO 处理明细订单的业务员分配
 * 执行顺序：9
 * @author: 韩伟
 * @date:   2018年3月2日 下午5:11:28   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionSaveOrderAutoAllocation implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		//当前用户是业务员，将所有明细订单分配给当前业务员
		
		
	}

}

//
//if (userId != 0) {
//    if (CollectionUtils.isNotEmpty(orderProdIds)) {
//        List<UcRole> ucRoleList = ucRoleService.findByUserPkid(userId);
//        List<String> roleTags = ucRoleList.stream().map(UcRole::getTag).collect(Collectors.toList());
//        if (CollectionUtils.isNotEmpty(roleTags) && roleTags.contains(RoleTag.ROLE_YWY)) {
//            editAssignApply(orderProdIds, userId, userId);
//        }
//    }
//}
//
//@Override
//public int editAssignApply(Collection<Integer> orderProdIds, Integer applyUserId, Integer operateUserId) {
//    for (Integer orderId : orderProdIds) {
//        editOrderAssign(applyUserId, orderId);
//    }
//
//    log.info("assign_batch_order: user[" + operateUserId + "] assign batch orderProdIds[" + StringUtils.join(orderProdIds, ",") + "] to user[" + applyUserId + "]");
//    return 1;
//}
//
///**
// * 订单分配
// *
// * @param applyUserId
// * @param orderProdId
// */
//@Override
//public int editOrderAssign(Integer applyUserId, Integer orderProdId) {
//    int type = 3061;// 业务员type
//
//    SoOrderProdUserMap prodUserMap = new SoOrderProdUserMap();
//    prodUserMap.setUserId(applyUserId);
//    prodUserMap.setStatusId(3141);
//    prodUserMap.setTypeId(type);
//    prodUserMap.setOrderProdId(orderProdId);
//
//    // 历史业务员改为曾经负责
//    soOrderProdUserMapDao.updateStatusByOrderProdId(orderProdId, type, 3142, 3141);
//
//    // 插入关联关系
//    soOrderProdUserMapDao.insert(prodUserMap);
//
//    // 更新产品状态
//    soOrderProdDao.updateAssignByIds(1, orderProdId);
//    return 1;
//}