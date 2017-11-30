package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_account_dingtalk_file")
public class AccountDingtalkFile extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4437525361302235641L;
	@Column(name="file_id")
    private Integer fileId;
    @Column(name="account_id")
    private Integer accountId;
    @Column(name="trademark_id")
    private Integer trademarkId;
    @Column(name="int_cls")
    private Integer intCls;
    private String name;
    @Column(name="company_id")
    private Integer companyId;
    private Integer type;
    private Integer category;
    @Column(name="add_time")
    private Date addTime;

    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public Integer getTrademarkId() {
        return trademarkId;
    }
    public void setTrademarkId(Integer trademarkId) {
        this.trademarkId = trademarkId;
    }
    public Integer getIntCls() {
        return intCls;
    }
    public void setIntCls(Integer intCls) {
        this.intCls = intCls;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getCategory() {
        return category;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}