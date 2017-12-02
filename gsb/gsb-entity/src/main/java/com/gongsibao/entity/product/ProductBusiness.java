package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_product_business",header="产品归属事业部")
public class ProductBusiness extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3966471676233300389L;
	@Column(name="product_id",header="产品id")
    private Integer productId;
	
    @Column(name="business_id",header="归属事业部 108 1081通用, 1082 公司宝, 1083 知产 1084 基础")
    private Integer businessId = 0;

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
}