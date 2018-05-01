package com.gongsibao.bd.service.auditLog;

/**
 * @author hw
 * 相关审核的上下文
 * <p>
 * 其它具体内容根据实际情况增加
 */
public class AuditContext {

    /**
     * 审核状态
     */
    private AuditState state;

    /**
     * 驳回原因
     */
    private String remark;

    /**
     * 审核记录Id
     */
    private Integer auditLogId;
    
    /**   
     * @Fields otherInfo : TODO(扩展信息)   
     */   
    private Object otherInfo;

    public AuditState getState() {
        return state;
    }

    public void setState(AuditState state) {
        this.state = state;
    }

    public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }

    public Integer getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Integer auditLogId) {
        this.auditLogId = auditLogId;
    }


    public Object getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Object otherInfo) {
        this.otherInfo = otherInfo;
    }
}
