package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_recommend_package",header="")
public class RecommendPackage extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -382128716314442525L;
	
	@Column(name="name",header="")
	private String name;
	
	@Column(name="description",header="")
    private String description;
    
	@Column(name="url",header="")
    private String url;
    
	@Column(name="img",header="")
    private String img;
    
    @Column(name="img_focus",header="")
    private String imgFocus;
    
    @Column(name="sort",header="")
    private Integer sort;
    
    @Column(name="status",header="")
    private Integer status;
    
    @Column(name="add_user",header="")
    private Integer addUser;

    @Column(name="upd_user",header="")
    private Integer updUser;

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
    public String getImgFocus() {
        return imgFocus;
    }
    public void setImgFocus(String imgFocus) {
        this.imgFocus = imgFocus;
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