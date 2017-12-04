package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_buy_qualify_map",header="")
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