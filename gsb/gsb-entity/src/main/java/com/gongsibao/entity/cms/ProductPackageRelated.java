package com.gongsibao.entity.cms;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product_package_related",header="")
public class ProductPackageRelated extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7034823526769842310L;
	@Column(name="cms_product_id",header="")
    private Integer cmsProductId;
    @Column(name="package_id",header="")
    private Integer packageId;
    @Column(name="product_name",header="")
    private String productName;
    @Column(name="package_name",header="")
    private String packageName;
    private Integer sort;
    @Column(name="add_user_id",header="")
    private Integer addUserId;
    private String remark;
    @Column(name="add_time",header="")
    private Date addTime;

    public Integer getCmsProductId() {
        return cmsProductId;
    }
    public void setCmsProductId(Integer cmsProductId) {
        this.cmsProductId = cmsProductId;
    }
    public Integer getPackageId() {
        return packageId;
    }
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}