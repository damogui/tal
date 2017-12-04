package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_recommend_service",header="")
public class RecommendService extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4179515733823349839L;
	@Column(name="category_id",header="")
    private Integer categoryId;
	
	@Column(name="name",header="")
    private String name;
    
	@Column(name="description",header="")
    private String description;
    
	@Column(name="price",header="")
    private String price;
    
    @Column(name="price_type",header="")
    private Integer priceType;
    
    @Column(name="url",header="")
    private String url;
    
    @Column(name="img",header="")
    private String img;
    
    @Column(name="sort",header="")
    private Integer sort;
    
    @Column(name="status",header="")
    private Integer status;

    @Column(name="add_user",header="")
    private Integer addUser;

    @Column(name="upd_user",header="")
    private Integer updUser;

    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public Integer getPriceType() {
        return priceType;
    }
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAddUser() {
        return addUser;
    }
    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    public Integer getUpdUser() {
        return updUser;
    }
    public void setUpdUser(Integer updUser) {
        this.updUser = updUser;
    }
}