package com.gongsibao.entity.product;

import java.math.BigDecimal;
import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_income")
public class Income extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3264580870868344594L;
	@Column(name="product_id")
    private Integer productId;
    @Column(name="organization_id")
    private Integer organizationId;
    @Column(name="income_type")
    private Integer incomeType;
    private BigDecimal income;
    @Column(name="is_enabled")
    private Integer isEnabled;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user_id")
    private Integer addUserId;

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
    public Integer getIncomeType() {
        return incomeType;
    }
    public void setIncomeType(Integer incomeType) {
        this.incomeType = incomeType;
    }
    public BigDecimal getIncome() {
        return income;
    }
    public void setIncome(BigDecimal income) {
        this.income = income;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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
}