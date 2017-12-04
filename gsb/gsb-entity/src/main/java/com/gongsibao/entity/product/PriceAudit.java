package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_price_audit",header="定价审核")
public class PriceAudit extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2919907714282312596L;

	@Column(name="product_id",header="产品序号")
    private Integer productId;
	
    @Column(name="organization_id",header="代理商序号")
    private Integer organizationId;
    
    @Column(name="audit_status_id",header="审核状态序号，type=105")
    private Integer auditStatusId;
    
    @Column(name="audit_status_type",header="1041产品定价申请审核、1048产品改价申请审核")
    private Integer auditStatusType;
    
    @Column(name="remark",header="说明")
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}