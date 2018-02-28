package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="prod_service",header="产品服务")
public class ProductService extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -806091553711905396L;

	@Column(name="product_id",header="产品序号")
    private Integer productId;
	
    @Column(name="unit_id",header="单位序号，type=4")
    private Integer unitId;
    
	@Reference(foreignKey="unitId",header="单位")
	private Dict unit;
    
    @Column(name="property_id",header="特性序号，type=207")
    private Integer propertyId;
    
	@Reference(foreignKey="propertyId",header="特性")
	private Dict property;
    
    @Column(name="type_id",header="分类序号，type=5")
    private Integer typeId;
    
	@Reference(foreignKey="typeId",header="分类")
	private Dict type;
    
    @Column(name="description",header="描述信息")
    private String description;
    
    @Column(name="sort",header="排序")
    private Double sort = 1D;
    
    @Column(name="has_stock",header="是否有库存量，默认否")
    private Boolean hasStock = false;
    
    @Column(name="is_enabled",header="是否启用")
    private Boolean enabled =true;

    @Column(name="remark",header="说明")
    private String remark;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Dict getUnit() {
		return unit;
	}

	public void setUnit(Dict unit) {
		this.unit = unit;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Dict getProperty() {
		return property;
	}

	public void setProperty(Dict property) {
		this.property = property;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Dict getType() {
		return type;
	}

	public void setType(Dict type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Boolean getHasStock() {
		return hasStock;
	}

	public void setHasStock(Boolean hasStock) {
		this.hasStock = hasStock;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}