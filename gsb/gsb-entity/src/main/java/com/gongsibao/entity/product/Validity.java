package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

//@Table(name="prod_validity",header="")
public class Validity extends Persistable {
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -349839270614525661L;
	@Column(name="int_",header="")
    private Integer int_;
	
    @Column(name="product_id",header="产品id")
    private Integer productId;
    
    @Column(name="validity",header="有效期（年） 0表示为无限期")
    private Integer validity;

    @Column(name="recommend",size=500,header="推荐理由")
    private String recommend;

    public Integer getInt() {
        return int_;
    }
    public void setInt(Integer int_) {
        this.int_ = int_;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getValidity() {
        return validity;
    }
    public void setValidity(Integer validity) {
        this.validity = validity;
    }
	public Integer getInt_() {
		return int_;
	}
	public void setInt_(Integer int_) {
		this.int_ = int_;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
    
    
}