package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_sell_industry_map")
public class CompanySellIndustryMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8272541292887956686L;
	@Column(name="sell_id")
    private Integer sellId;
    @Column(name="industry_id")
    private Integer industryId;

    public Integer getSellId() {
        return sellId;
    }
    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }
    public Integer getIndustryId() {
        return industryId;
    }
    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }
}