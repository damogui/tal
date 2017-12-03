package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product_template",header="")
public class ProductTemplate extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 321978296688381712L;
	private String name;
    @Column(name="is_default",header="")
    private Integer isDefault;
    private String content;
    @Column(name="add_user_id",header="")
    private Integer addUserId;
    @Column(name="product_id",header="")
    private Integer productId;
    @Column(name="cms_product_id",header="")
    private Integer cmsProductId;
    private String title;
    private String keyword;
    @Column(name="page_description",header="")
    private String pageDescription;
    @Column(name="product_name",header="")
    private String productName;
    private String remark;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getCmsProductId() {
        return cmsProductId;
    }
    public void setCmsProductId(Integer cmsProductId) {
        this.cmsProductId = cmsProductId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getPageDescription() {
        return pageDescription;
    }
    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}