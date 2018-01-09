package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

/**   
 * @ClassName:  CompanyBuyIndustryMap   
 * @Description:TODO 没有数据
 * @author: 韩伟
 * @date:   2018年1月9日 上午11:10:40   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
@Table(name="crm_company_buy_industry_map" ,header="我要收购公司--行业特点关联表")
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