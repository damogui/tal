package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.uc.Organization;

@Table(name="prod_income_organization_child",header="分成下辖组织机构id")
public class IncomeOrganizationChild extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6347590501493573390L;
	@Column(name="income_id",header="")
    private Integer incomeId;
	
	@Reference(foreignKey="incomeId",header="分成")
	private Income income;
	
    @Column(name="organization_id",header="")
    private Integer organizationId;
    
	@Reference(foreignKey="organizationId",header="组织机构")
	private Organization organization;

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
	public Income getIncome() {
		return income;
	}
	public void setIncome(Income income) {
		this.income = income;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
    
    
}