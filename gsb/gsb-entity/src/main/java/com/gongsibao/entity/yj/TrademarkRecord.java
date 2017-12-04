package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_trademark_record")
public class TrademarkRecord extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3691263087949139353L;
	@Column(name="account_id",header="AccountId")
    private Integer accountId;
    @Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="account_name",header="AccountName")
    private String accountName;
    @Column(name="company_name",header="CompanyName")
    private String companyName;
    @Column(name="image_urls",header="ImageUrls")
    private String imageUrls;
    @Column(header="name")
    private String name;
    @Column(name="add_time",header="AddTime")
    private Timestamp addTime;

    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getImageUrls() {
        return imageUrls;
    }
    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
}