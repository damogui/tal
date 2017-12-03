package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_audit_log")
public class AuditLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8815735808036860870L;
	@Column(name="type_id",header="TypeId")
    private Integer typeId;
    @Column(name="form_id",header="FormId")
    private Integer formId;
    @Column(name="status_id",header="StatusId")
    private Integer statusId;
    @Column(header="content")
    private String content;

    @Column(header="remark")
    private String remark;
    @Column(header="level")
    private Integer level;

    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getFormId() {
        return formId;
    }
    public void setFormId(Integer formId) {
        this.formId = formId;
    }
    public Integer getStatusId() {
        return statusId;
    }
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
}