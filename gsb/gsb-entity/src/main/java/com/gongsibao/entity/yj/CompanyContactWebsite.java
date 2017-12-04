package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="yj_company_contact_website")
public class CompanyContactWebsite extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4062438982918457454L;
	@Column(name="yj_company_contactinfo_id",header="YjCompanyContactinfoId")
    private Integer yjCompanyContactinfoId;
    @Column(header="name")
    private String name;
    @Column(header="url")
    private String url;

    public Integer getYjCompanyContactinfoId() {
        return yjCompanyContactinfoId;
    }
    public void setYjCompanyContactinfoId(Integer yjCompanyContactinfoId) {
        this.yjCompanyContactinfoId = yjCompanyContactinfoId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}