package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product_template_bd_dict_map",header="")
public class ProductTemplateBdDictMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9066252837501569111L;
	@Column(name="product_id",header="")
    private Integer productId;
	
    @Column(name="city_id",header="")
    private Integer cityId;
    
    @Column(name="template_id",header="")
    private Integer templateId;
    
    @Column(name="remark",header="")
    private String remark;

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}