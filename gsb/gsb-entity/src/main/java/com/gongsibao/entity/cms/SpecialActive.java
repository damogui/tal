package com.gongsibao.entity.cms;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_special_active",header="")
public class SpecialActive extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8092706452181831443L;
	
	@Column(name="name",header="")
	private String name;
	
	@Column(name="description",header="")
    private String description;
    
    @Column(name="active_time",header="")
    private Date activeTime;
    
    @Column(name="url",header="")
    private String url;
    
    @Column(name="img",header="")
    private String img;
    
    @Column(name="img_big",header="")
    private String imgBig;
    
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
    public Date getActiveTime() {
        return activeTime;
    }
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
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
    public String getImgBig() {
        return imgBig;
    }
    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
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