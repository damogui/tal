package com.gongsibao.entity.product;

import java.math.BigDecimal;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.product.dic.IncomeType;
import com.gongsibao.entity.uc.Organization;

@Table(name="prod_income",header="分成表")
public class Income extends BaseEntity {

	  
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3264580870868344594L;
	@Column(name="product_id",header="产品id")
    private Integer productId;
	
	@Reference(foreignKey="productId",header="产品")
	private Product product;
	
    @Column(name="organization_id",header="组织机构id")
    private Integer organizationId;
    
	@Reference(foreignKey="organizationId",header="组织机构")
	private Organization organization;
    
    @Column(name="income_type",header="分成方式: 0 比例分成, 1 定额分成")
    private IncomeType incomeType = IncomeType.IncomeType_1;
    
    @Column(name="income",header="比例/定额")
    private BigDecimal income = BigDecimal.ZERO;
    
    @Column(name="is_enabled",header="是否启用 0否 1是")
    private Boolean enabled = false;

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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public IncomeType getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}