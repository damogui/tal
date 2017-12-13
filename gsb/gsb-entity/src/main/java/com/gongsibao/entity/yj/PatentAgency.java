package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_patent_agency")
public class PatentAgency extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6840152327194621234L;
	@Column(name="yj_patent_id",header="YjPatentId")
    private Integer yjPatentId;
    @Column(header="queryname")
    private String queryname;
    @Column(header="name")
    private String name;
    @Column(header="code")
    private String code;
    @Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;

    public Integer getYjPatentId() {
        return yjPatentId;
    }
    public void setYjPatentId(Integer yjPatentId) {
        this.yjPatentId = yjPatentId;
    }
    public String getQueryname() {
        return queryname;
    }
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
}