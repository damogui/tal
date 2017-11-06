package com.gongsibao.entity.cms;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_special_active")
public class SpecialActive extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8092706452181831443L;
	private String name;
    private String description;
    @Column(name="active_time")
    private Date activeTime;
    private String url;
    private String img;
    @Column(name="img_big")
    private String imgBig;
    private Integer status;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user")
    private Integer addUser;
    @Column(name="upd_time")
    private Date updTime;
    @Column(name="upd_user")
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
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUser() {
        return addUser;
    }
    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Integer getUpdUser() {
        return updUser;
    }
    public void setUpdUser(Integer updUser) {
        this.updUser = updUser;
    }
}