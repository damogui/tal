package com.gongsibao.entity.cms;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_guest_voice")
public class GuestVoice extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1390638669938323116L;
	private String name;
    @Column(name="guest_name")
    private String guestName;
    @Column(name="guest_port")
    private String guestPort;
    @Column(name="guest_evaluation")
    private String guestEvaluation;
    private String img;
    private Integer sort;
    private Integer spider;
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
    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public String getGuestPort() {
        return guestPort;
    }
    public void setGuestPort(String guestPort) {
        this.guestPort = guestPort;
    }
    public String getGuestEvaluation() {
        return guestEvaluation;
    }
    public void setGuestEvaluation(String guestEvaluation) {
        this.guestEvaluation = guestEvaluation;
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
    public Integer getSpider() {
        return spider;
    }
    public void setSpider(Integer spider) {
        this.spider = spider;
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