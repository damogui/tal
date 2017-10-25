package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_income_organization")
public class IncomeOrganization extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6802465817051318646L;
	@Column(name="income_id")
    private Integer incomeId;
    @Column(name="organization_id")
    private Integer organizationId;

    public Integer getIncomeId() {
        return incomeId;
    }
    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
}