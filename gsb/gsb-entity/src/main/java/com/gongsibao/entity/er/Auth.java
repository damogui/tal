package com.gongsibao.entity.er;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_auth")
public class Auth extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8793245235464215363L;
	@Column(name="pid",header="pid")
    private Integer pid;
    @Column(name="name",header="name")
    private String name;
    @Column(name="sref",header="sref")
    private String sref;
    @Column(name="icon",header="icon")
    private String icon;
    @Column(name="is_menu",header="IsMenu")
    private Integer isMenu;
    @Column(name="sort",header="sort")
    private Double sort;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="is_confing_show",header="IsConfingShow")
    private Integer isConfingShow;

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSref() {
        return sref;
    }
    public void setSref(String sref) {
        this.sref = sref;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getIsMenu() {
        return isMenu;
    }
    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Integer getIsConfingShow() {
        return isConfingShow;
    }
    public void setIsConfingShow(Integer isConfingShow) {
        this.isConfingShow = isConfingShow;
    }
}