package com.gongsibao.entity.crm;

import com.gongsibao.entity.BaseEntity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Supplier;

@Table(name = "crm_customer_prod_map", header = "客户意向产品")
public class NCustomerProduct extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)//n_crm_customer_product_map
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@Column(name = "supplier_id", header = "分配服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "分配服务商")
	private Supplier supplier;

	@JsonIgnore
	@Reference(foreignKey = "customerId", header = "客户")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId;

	@JsonIgnore
	@Reference(foreignKey = "taskId", header = "客户商机")
	private NCustomerTask task;

	@Column(name = "task_id", header = "客户商机Id")
	private Integer taskId;

	@Column(name = "product_category_id_1")
	private Integer productCategoryId1;

	@Reference(foreignKey = "productCategoryId1",  header = "产品一级分类")
	private Dict productCategory1;

	@Column(name = "product_category_id_2")
	private Integer productCategoryId2;

	@Reference(foreignKey = "productCategoryId2", header = "产品二级分类")
	private Dict productCategory2;

	@Column(name = "product_id")
	private Integer productId;

	@Reference(foreignKey = "productId", header = "产品")
	private Product product;

	@Column(name = "d_province_id")
	private Integer provinceId;

	@Reference(foreignKey = "provinceId", header = "省份")
	private Dict province;
	
    @Column(name="city_id")
    private Integer oldCityId = 0;

	@Column(name = "d_city_id")
	private Integer cityId;

	@Reference(foreignKey = "cityId", header = "城市")
	private Dict city;

	@Column(name = "d_county_id")
	private Integer countyId;

	@Reference(foreignKey = "countyId",header = "区/县")
	private Dict county;
	
	public Integer getOldCityId() {
		return oldCityId;
	}

	public void setOldCityId(Integer oldCityId) {
		this.oldCityId = oldCityId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public NCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(NCustomer customer) {
		this.customer = customer;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public NCustomerTask getTask() {
		return task;
	}

	public void setTask(NCustomerTask task) {
		this.task = task;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getProductCategoryId1() {
		return productCategoryId1;
	}

	public void setProductCategoryId1(Integer productCategoryId1) {
		this.productCategoryId1 = productCategoryId1;
	}

	public Dict getProductCategory1() {
		return productCategory1;
	}

	public void setProductCategory1(Dict productCategory1) {
		this.productCategory1 = productCategory1;
	}

	public Integer getProductCategoryId2() {
		return productCategoryId2;
	}

	public void setProductCategoryId2(Integer productCategoryId2) {
		this.productCategoryId2 = productCategoryId2;
	}

	public Dict getProductCategory2() {
		return productCategory2;
	}

	public void setProductCategory2(Dict productCategory2) {
		this.productCategory2 = productCategory2;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Dict getProvince() {
		return province;
	}

	public void setProvince(Dict province) {
		this.province = province;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Dict getCounty() {
		return county;
	}

	public void setCounty(Dict county) {
		this.county = county;
	}
}
