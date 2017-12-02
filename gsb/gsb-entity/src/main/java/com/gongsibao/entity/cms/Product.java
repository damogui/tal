package com.gongsibao.entity.cms;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product",header="")
public class Product extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鍙橀噺琛ㄧず浠�涔�)   
	 */   
	private static final long serialVersionUID = -1099819795496453198L;
	@Column(name="product_id",header="")
    private Integer productId;
    @Column(name="package_id",header="")
    private Integer packageId;
    
    @Column(name="cooperation_company_id",header="合作公司")
    private Integer cooperationCompanyId;
    
    private String showprice;
    @Column(name="prod_name",header="")
    private String prodName;
    private String summary;
    @Column(name="app_pord_img_url_id",header="")
    private Integer appPordImgUrlId;
    @Column(name="un_app_pord_img_url_id",header="")
    private Integer unAppPordImgUrlId;
    @Column(name="prod_icon_img_url_id",header="")
    private Integer prodIconImgUrlId;
    @Column(name="package_prod_icon_url_id",header="")
    private Integer packageProdIconUrlId;
    private Integer status;
    @Column(name="php_id",header="")
    private Integer phpId;
    private Double sort;
    @Column(name="is_show",header="")
    private Integer isShow;
    @Column(name="is_hot",header="")
    private Integer isHot;
    @Column(name="last_update_time",header="")
    private Date lastUpdateTime;
    @Column(name="attribute_category",header="")
    private Integer attributeCategory;
    @Column(name="first_aggregation_name",header="")
    private String firstAggregationName;
    @Column(name="second_aggregation_name",header="")
    private String secondAggregationName;
    @Column(name="price_description",header="")
    private String priceDescription;
    @Column(name="promotional_copy",header="")
    private String promotionalCopy;
    @Column(name="service_area_description",header="")
    private String serviceAreaDescription;
    @Column(name="regist_address_description",header="")
    private String registAddressDescription;
    @Column(name="service_period_description",header="")
    private String servicePeriodDescription;
    @Column(name="buy_count_description",header="")
    private String buyCountDescription;
    @Column(name="add_user_id",header="")
    private Integer addUserId;
    private String remark;
    @Column(name="add_time",header="")
    private Date addTime;

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getPackageId() {
        return packageId;
    }
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
    
    public Integer getCooperationCompanyId() {
		return cooperationCompanyId;
	}
	public void setCooperationCompanyId(Integer cooperationCompanyId) {
		this.cooperationCompanyId = cooperationCompanyId;
	}
	public String getShowprice() {
        return showprice;
    }
    public void setShowprice(String showprice) {
        this.showprice = showprice;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Integer getAppPordImgUrlId() {
        return appPordImgUrlId;
    }
    public void setAppPordImgUrlId(Integer appPordImgUrlId) {
        this.appPordImgUrlId = appPordImgUrlId;
    }
    public Integer getUnAppPordImgUrlId() {
        return unAppPordImgUrlId;
    }
    public void setUnAppPordImgUrlId(Integer unAppPordImgUrlId) {
        this.unAppPordImgUrlId = unAppPordImgUrlId;
    }
    public Integer getProdIconImgUrlId() {
        return prodIconImgUrlId;
    }
    public void setProdIconImgUrlId(Integer prodIconImgUrlId) {
        this.prodIconImgUrlId = prodIconImgUrlId;
    }
    public Integer getPackageProdIconUrlId() {
        return packageProdIconUrlId;
    }
    public void setPackageProdIconUrlId(Integer packageProdIconUrlId) {
        this.packageProdIconUrlId = packageProdIconUrlId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getPhpId() {
        return phpId;
    }
    public void setPhpId(Integer phpId) {
        this.phpId = phpId;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public Integer getIsShow() {
        return isShow;
    }
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
    public Integer getIsHot() {
        return isHot;
    }
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    public Integer getAttributeCategory() {
        return attributeCategory;
    }
    public void setAttributeCategory(Integer attributeCategory) {
        this.attributeCategory = attributeCategory;
    }
    public String getFirstAggregationName() {
        return firstAggregationName;
    }
    public void setFirstAggregationName(String firstAggregationName) {
        this.firstAggregationName = firstAggregationName;
    }
    public String getSecondAggregationName() {
        return secondAggregationName;
    }
    public void setSecondAggregationName(String secondAggregationName) {
        this.secondAggregationName = secondAggregationName;
    }
    public String getPriceDescription() {
        return priceDescription;
    }
    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }
    public String getPromotionalCopy() {
        return promotionalCopy;
    }
    public void setPromotionalCopy(String promotionalCopy) {
        this.promotionalCopy = promotionalCopy;
    }
    public String getServiceAreaDescription() {
        return serviceAreaDescription;
    }
    public void setServiceAreaDescription(String serviceAreaDescription) {
        this.serviceAreaDescription = serviceAreaDescription;
    }
    public String getRegistAddressDescription() {
        return registAddressDescription;
    }
    public void setRegistAddressDescription(String registAddressDescription) {
        this.registAddressDescription = registAddressDescription;
    }
    public String getServicePeriodDescription() {
        return servicePeriodDescription;
    }
    public void setServicePeriodDescription(String servicePeriodDescription) {
        this.servicePeriodDescription = servicePeriodDescription;
    }
    public String getBuyCountDescription() {
        return buyCountDescription;
    }
    public void setBuyCountDescription(String buyCountDescription) {
        this.buyCountDescription = buyCountDescription;
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