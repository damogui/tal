package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="prod_income_city",header="分成 城市关联表")
public class IncomeCity extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9055637265947363792L;
	@Column(name="income_id",header="分成Id")
    private Integer incomeId;
	
	@Reference(foreignKey="incomeId",header="分成")
	private Income income;
	
    @Column(name="city_id",header="城市Id")
    private Integer cityId;
    
	@Reference(foreignKey="cityId",header="城市")
	private Dict city;

	public Integer getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Integer incomeId) {
		this.incomeId = incomeId;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}
}