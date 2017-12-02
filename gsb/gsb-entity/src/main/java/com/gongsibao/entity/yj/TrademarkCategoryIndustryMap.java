package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="yj_trademark_category_industry_map")
public class TrademarkCategoryIndustryMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7034084149424740520L;
	@Column(name="trademark_category_id",header="TrademarkCategoryId")
    private Integer trademarkCategoryId;
    @Column(header="type")
    private Integer type;
    @Column(name="industry_id",header="IndustryId")
    private Integer industryId;

    public Integer getTrademarkCategoryId() {
        return trademarkCategoryId;
    }
    public void setTrademarkCategoryId(Integer trademarkCategoryId) {
        this.trademarkCategoryId = trademarkCategoryId;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getIndustryId() {
        return industryId;
    }
    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }
}