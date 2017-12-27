package com.gongsibao.entity.yj;

import java.sql.Timestamp;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_company_change_record")
public class CompanyChangeRecord extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1977512923224870878L;
	
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="project_name",header="ProjectName")
    private String projectName;
    @Column(name="before_content",header="BeforeContent")
    private String beforeContent;
    @Column(name="after_content",header="AfterContent")
    private String afterContent;
    @Column(name="change_date",header="ChangeDate")
    private Timestamp changeDate;

    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getBeforeContent() {
        return beforeContent;
    }
    public void setBeforeContent(String beforeContent) {
        this.beforeContent = beforeContent;
    }
    public String getAfterContent() {
        return afterContent;
    }
    public void setAfterContent(String afterContent) {
        this.afterContent = afterContent;
    }
    public Timestamp getChangeDate() {
        return changeDate;
    }
    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }
}