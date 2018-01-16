package com.gongsibao.entity.supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.bd.Dict;

@Table(name="sp_service_product",header="服务商服务商品")
public class SupplierServiceProduct extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6865133330856235904L;

	@Column(name="supplier_id",header="服务商主键")
    private Integer supplierId;
	
	@JsonIgnore
    @Reference(foreignKey="supplierId")
    private Supplier supplier;
	
    @Column(name="product_id")
    private Integer productId;
    
    @Reference(foreignKey="productId",header="产品")
    private Dict product;


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

	public Dict getProduct() {
		return product;
	}

	public void setProduct(Dict product) {
		this.product = product;
	}
}
