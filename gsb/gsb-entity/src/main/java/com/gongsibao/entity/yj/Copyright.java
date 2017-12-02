package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_copyright")
public class Copyright extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8527237767718261173L;
	@Column(name="company_id",header="CompanyId")
    private Integer companyId;
    @Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(header="owner")
    private String owner;
    @Column(header="category")
    private String category;
    @Column(header="name")
    private String name;
    @Column(header="type")
    private Integer type;
    @Column(name="short_name",header="ShortName")
    private String shortName;
    @Column(name="register_no",header="RegisterNo")
    private String registerNo;
    @Column(name="register_date",header="RegisterDate")
    private Timestamp registerDate;
    @Column(name="publish_date",header="PublishDate")
    private Timestamp publishDate;
    @Column(name="finish_date",header="FinishDate")
    private Timestamp finishDate;
    @Column(name="version_no",header="VersionNo")
    private String versionNo;
    @Column(name="register_aper_date",header="RegisterAperDate")
    private Timestamp registerAperDate;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getRegisterNo() {
        return registerNo;
    }
    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }
    public Timestamp getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }
    public Timestamp getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }
    public Timestamp getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }
    public String getVersionNo() {
        return versionNo;
    }
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
    public Timestamp getRegisterAperDate() {
        return registerAperDate;
    }
    public void setRegisterAperDate(Timestamp registerAperDate) {
        this.registerAperDate = registerAperDate;
    }
}