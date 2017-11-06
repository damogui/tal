package com.gongsibao.entity.product;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_price_audit")
public class PriceAudit extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2919907714282312596L;
	@Column(name="product_id")
    private Integer productId;
    @Column(name="organization_id")
    private Integer organizationId;
    @Column(name="audit_status_id")
    private Integer auditStatusId;
    @Column(name="audit_status_type")
    private Integer auditStatusType;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user_id")
    private Integer addUserId;
    private String remark;

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    public Integer getAuditStatusId() {
        return auditStatusId;
    }
    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
    }
    public Integer getAuditStatusType() {
        return auditStatusType;
    }
    public void setAuditStatusType(Integer auditStatusType) {
        this.auditStatusType = auditStatusType;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}