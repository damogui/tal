package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_company_industry")
public class CompanyIndustry extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1457109975792968579L;
	
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(header="industry")
    private String industry;
    @Column(name="sub_industry",header="SubIndustry")
    private String subIndustry;

    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getSubIndustry() {
        return subIndustry;
    }
    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }
}