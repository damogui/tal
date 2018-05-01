//package com.gongsibao.trade.base;
//
//import com.gongsibao.entity.bd.dic.AuditLogType;
//import org.netsharp.base.IPersistableService;
//import org.netsharp.core.annotations.Transaction;
//
//import com.gongsibao.entity.bd.AuditLog;
//
//import java.util.List;
//
//public interface IAuditService extends IPersistableService<AuditLog> {
//
//    //审核通过
//    @Transaction
//    Boolean auditApproved(Integer auditId, String remark);
//
//    //审核驳回
//    @Transaction
//    Boolean auditRejected(Integer auditId, String remark);
//
//    /*
//    * 根据类别id和formid获取审批记录集合
//    * */
//    List<AuditLog> getByTypeIdFormId(AuditLogType auditLogType, Integer formId);
//
///**
// * 根据主键获取审核实体
// * @param id
// * @return
// */
//    AuditLog getById(Integer id);
//}
