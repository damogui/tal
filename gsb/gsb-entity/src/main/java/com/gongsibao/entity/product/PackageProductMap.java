package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_package_product_map",header="城市定价关联表")
public class PackageProductMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6292565921773409119L;
	@Column(name="package_id",header="套餐id")
    private Integer packageId;
	
	@Reference(foreignKey="packageId",header="套餐")
	private ProductPackage productPackage;
	
    @Column(name="product_id",header="产品id")
    private Integer productId;
    
	@Reference(foreignKey="productId",header="产品")
	private Product product;
    
    @Column(name="sort",header="排序")
    private Integer sort;

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public ProductPackage getProductPackage() {
		return productPackage;
	}

	public void setProductPackage(ProductPackage productPackage) {
		this.productPackage = productPackage;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}