package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_buy_industry_map" ,header="")
public class CompanyBuyIndustryMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3552661194504173014L;
	@Column(name="buy_id",header="")
    private Integer buyId;
	
    @Column(name="industry_id",header="")
    private Integer industryId;

    public Integer getBuyId() {
        return buyId;
    }
    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }
    public Integer getIndustryId() {
        return industryId;
    }
    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }
}