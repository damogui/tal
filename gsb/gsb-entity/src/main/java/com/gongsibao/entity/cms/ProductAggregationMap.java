package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product_aggregation_map",header="")
public class ProductAggregationMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8853783739888558067L;
	
	@Column(name="cms_product_aggregation_id",header="")
    private Integer cmsProductAggregationId;
	
    @Column(name="cms_product_id",header="")
    private Integer cmsProductId;
    
    @Column(name="prod_product_id",header="")
    private Integer prodProductId;
    
    @Column(name="product_name",header="")
    private String productName;

    public Integer getCmsProductAggregationId() {
        return cmsProductAggregationId;
    }
    public void setCmsProductAggregationId(Integer cmsProductAggregationId) {
        this.cmsProductAggregationId = cmsProductAggregationId;
    }
    public Integer getCmsProductId() {
        return cmsProductId;
    }
    public void setCmsProductId(Integer cmsProductId) {
        this.cmsProductId = cmsProductId;
    }
    public Integer getProdProductId() {
        return prodProductId;
    }
    public void setProdProductId(Integer prodProductId) {
        this.prodProductId = prodProductId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}