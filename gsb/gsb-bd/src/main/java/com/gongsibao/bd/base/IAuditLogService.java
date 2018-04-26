package com.gongsibao.bd.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

public interface IAuditLogService extends IPersistableService<AuditLog> {

    int updateStatus(Integer id, Integer status, Integer oldStatus, String remark);

    int updateStatusByFidTidLev(Integer formId, Integer typeId, Integer level, Integer status, String levelLogic, Integer exceptId);

    List<AuditLog> queryByFormId(Integer orderId, AuditLogType type);

    /**
     * @throws
     * @Title: queryUnPassCountByFormId
     * @Description: TODO(根据订单Id获取审核未通过的数量)
     * @param: @param orderIds
     * @param: @return
     * @return: int
     */
    int queryUnPassCountByFormId(List<Integer> orderIdList);


    /**
     * @throws
     * @Title: createAuditLog
     * @Description: TODO(1.根据类型不同构建不同数量的记录 2.addUserId为提交审核人Id 3.需要返回集合 ， 用作发送通知)
     * @param: @param type
     * @param: @param formId
     * @param: @param addUserId
     * @param: @return
     * @return: List<AuditLog>
     */
    List<AuditLog> createAuditLog(AuditLogType type, Integer formId, Integer addUserId);

    /*根据表单id 类型typeId和等级 查询出来下一级审核的人*/
    List<AuditLog> getNextLevelUserIds(Integer fromId, int typeId, int level);
}