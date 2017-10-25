package com.gongsibao.entity.taurus;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;

import com.gongsibao.entity.BaseEntity;

public class UserWalletLog extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1129870239055750903L;

	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	
	@Column(name="user_id",header="用户Id")
	private Integer userId;
	
	@Column(name="price",header="金额")
	private Integer price;
	
	@Column(name="type",header="类型0充值1消费")
	private Integer type;
	
	@Column(name = "company_name", header = "消费公司名称")
	private String companyName;
	
	@Column(name = "company_id", header = "消费公司ID")
	private String company_Id;
	
	@Column(name = "remark",size=200, header = "说明")
	private String remark;
	
	@Column(name="sign_status",header="消费公司是否标记:0关闭没有标记1打开已标记")
	private Integer signStatus;
	
	@Column(name="add_time",header="创建时间")
    private Date createTime;
    
	@Column(name = "creator", header = "创建人名称")
	private String creator;
	
    @Column(name="add_user_id",header="添加人序号")
    private Integer creatorId = 0;
    
    @Column(name="upd_user_id",header="修改人序号")
    private Integer updatorId = 0;
    
    @Column(name="upd_time",header="修改时间")
    private Date updateTime;

	@Column(name = "updator", header = "更新人名称")
	private String updator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompany_Id() {
		return company_Id;
	}

	public void setCompany_Id(String company_Id) {
		this.company_Id = company_Id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
}
