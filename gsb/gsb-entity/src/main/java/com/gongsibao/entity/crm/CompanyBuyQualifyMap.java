package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

/**   
 * @ClassName:  CompanyBuyQualifyMap   
 * @Description:TODO 没有数据
 * @author: 韩伟
 * @date:   2018年1月9日 上午11:10:59   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
@Table(name="crm_company_buy_qualify_map",header="我要收购公司--企业资质关联表")
public class CompanyBuyQualifyMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3545208649260473051L;
	@Column(name="buy_id",header="")
    private Integer buyId;
	
    @Column(name="qualify_id",header="")
    private Integer qualifyId;

    public Integer getBuyId() {
        return buyId;
    }
    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }
    public Integer getQualifyId() {
        return qualifyId;
    }
    public void setQualifyId(Integer qualifyId) {
        this.qualifyId = qualifyId;
    }
}