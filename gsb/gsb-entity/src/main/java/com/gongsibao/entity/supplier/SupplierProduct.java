package com.gongsibao.entity.supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Product;

@Table(name="sp_supplier_product",header="服务商服务商品")
public class SupplierProduct extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6865133330856235904L;

	@Column(name="supplier_id",header="服务商主键")
    private Integer supplierId;
	
	@JsonIgnore
    @Reference(foreignKey="supplierId")
    private Supplier supplier;
	
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
    
	@Column(name = "province_id")
	private Integer nProvinceId;

	@Reference(foreignKey = "nProvinceId", header = "省份")
	private Dict nProvince;

	@Column(name = "city_id")
	private Integer nCityId;

	@Reference(foreignKey = "nCityId", header = "城市")
	private Dict nCity;

	@Column(name = "county_id")
	private Integer nCountyId;

	@Reference(foreignKey = "nCountyId",header = "区/县")
	private Dict nCounty;


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

	public Integer getnProvinceId() {
		return nProvinceId;
	}

	public void setnProvinceId(Integer nProvinceId) {
		this.nProvinceId = nProvinceId;
	}

	public Dict getnProvince() {
		return nProvince;
	}

	public void setnProvince(Dict nProvince) {
		this.nProvince = nProvince;
	}

	public Integer getnCityId() {
		return nCityId;
	}

	public void setnCityId(Integer nCityId) {
		this.nCityId = nCityId;
	}

	public Dict getnCity() {
		return nCity;
	}

	public void setnCity(Dict nCity) {
		this.nCity = nCity;
	}

	public Integer getnCountyId() {
		return nCountyId;
	}

	public void setnCountyId(Integer nCountyId) {
		this.nCountyId = nCountyId;
	}

	public Dict getnCounty() {
		return nCounty;
	}

	public void setnCounty(Dict nCounty) {
		this.nCounty = nCounty;
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
}
