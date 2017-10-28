//package com.gongsibao.entity.taurus;
//
//import java.util.Date;
//
//import org.netsharp.core.annotations.Auto;
//import org.netsharp.core.annotations.Column;
//import org.netsharp.core.annotations.Id;
//
//import com.gongsibao.entity.BaseEntity;
//
//public class UserWallet extends BaseEntity{
//
//	/**   
//	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
//	 */   
//	private static final long serialVersionUID = 2938825025113244574L;
//
//	
//	@Id
//	@Auto
//	@Column(name="pkid",header="id")
//	private Integer id;
//	
//	@Column(name="user_id",header="用户Id")
//	private Integer userId;
//	
//	@Column(name="price",header="金额")
//	private Integer price;
//	
//	@Column(name="add_time",header="创建时间")
//    private Date createTime;
//    
//	@Column(name = "creator", header = "创建人名称")
//	private String creator;
//	
//    @Column(name="add_user_id",header="添加人序号")
//    private Integer creatorId = 0;
//    
//    @Column(name="upd_user_id",header="修改人序号")
//    private Integer updatorId = 0;
//    
//    @Column(name="upd_time",header="修改时间")
//    private Date updateTime;
//
//	@Column(name = "updator", header = "更新人名称")
//	private String updator;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
//	public Integer getPrice() {
//		return price;
//	}
//
//	public void setPrice(Integer price) {
//		this.price = price;
//	}
//
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	public String getCreator() {
//		return creator;
//	}
//
//	public void setCreator(String creator) {
//		this.creator = creator;
//	}
//
//	public Integer getCreatorId() {
//		return creatorId;
//	}
//
//	public void setCreatorId(Integer creatorId) {
//		this.creatorId = creatorId;
//	}
//
//	public Integer getUpdatorId() {
//		return updatorId;
//	}
//
//	public void setUpdatorId(Integer updatorId) {
//		this.updatorId = updatorId;
//	}
//
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public String getUpdator() {
//		return updator;
//	}
//
//	public void setUpdator(String updator) {
//		this.updator = updator;
//	}
//}
