package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_company_investment_map")
public class CompanyInvestmentMap extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1942000154181691847L;
	@Column(header="type")
    private Integer type;
    @Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="yj_company_investment_id",header="YjCompanyInvestmentId")
    private Integer yjCompanyInvestmentId;

    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public Integer getYjCompanyInvestmentId() {
        return yjCompanyInvestmentId;
    }
    public void setYjCompanyInvestmentId(Integer yjCompanyInvestmentId) {
        this.yjCompanyInvestmentId = yjCompanyInvestmentId;
    }
}