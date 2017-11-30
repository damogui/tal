package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_income_city")
public class IncomeCity extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9055637265947363792L;
	@Column(name="income_id")
    private Integer incomeId;
    @Column(name="city_id")
    private Integer cityId;

    public Integer getIncomeId() {
        return incomeId;
    }
    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}