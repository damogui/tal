package com.gongsibao.entity.cms;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product",header="cms产品表")
public class Product extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO()   
	 */   
	private static final long serialVersionUID = -1099819795496453198L;
	@Column(name="product_id",header="产品序号")
    private Integer productId;
	
    @Column(name="package_id",header="套餐序号")
    private Integer packageId;
    
    @Column(name="cooperation_company_id",header="合作公司")
    private Integer cooperationCompanyId;
    
    @Column(name="showprice",header="显示价")
    private String showprice;
    
    @Column(name="prod_name",header="商品或套餐名称(显示名称)")
    private String prodName;
    
    @Column(name="summary",header="摘要")
    private String summary;
    
    @Column(name="app_pord_img_url_id",header="手机产品图id")
    private Integer appPordImgUrlId;
    
    @Column(name="un_app_pord_img_url_id",header="未选中手机产品图id")
    private Integer unAppPordImgUrlId;
    
    @Column(name="prod_icon_img_url_id",header="产品小图标id")
    private Integer prodIconImgUrlId;
    
    @Column(name="package_prod_icon_url_id",header="套餐里面产品的icon小图id")
    private Integer packageProdIconUrlId;
    
    @Column(name="status",header="状态（0：待发布，1：已发布）")
    private Integer status;
    
    @Column(name="php_id",header="phpid")
    private Integer phpId;
    
    @Column(name="sort",header="排序编号")
    private Double sort;
    
    @Column(name="is_show",header="是否显示（0：不显示，1：显示）")
    private Integer isShow;
    
    @Column(name="is_hot",header="是否是热门推广（0：不是，1：是）")
    private Integer isHot;
    
    @Column(name="last_update_time",header="最后修改时间")
    private Date lastUpdateTime;
    
    @Column(name="attribute_category",header="性类别（0：单品；1：聚合；2：无层级聚合；3：套餐）")
    private Integer attributeCategory;
    
    @Column(name="first_aggregation_name",header="一级聚合分类名称'")
    private String firstAggregationName;
    
    @Column(name="second_aggregation_name",header="二级聚合分类名称")
    private String secondAggregationName;
    
    @Column(name="price_description",header="价格描述")
    private String priceDescription;
    
    @Column(name="promotional_copy",header="一句话卖点(推广文案)")
    private String promotionalCopy;
    
    @Column(name="service_area_description",header="服务地区文案")
    private String serviceAreaDescription;
    
    @Column(name="regist_address_description",header="注册地址文案")
    private String registAddressDescription;
    
    @Column(name="service_period_description",header="服务期文案")
    private String servicePeriodDescription;
    
    @Column(name="buy_count_description",header="购买数量的文案")
    private String buyCountDescription;

    @Column(name="remark",header="备注")
    private String remark;


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

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}