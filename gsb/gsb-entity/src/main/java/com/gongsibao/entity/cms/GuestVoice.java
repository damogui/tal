package com.gongsibao.entity.cms;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_guest_voice",header="")
public class GuestVoice extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1390638669938323116L;
	
	@Column(name="name",header="")
	private String name;
	
    @Column(name="guest_name",header="")
    private String guestName;
    
    @Column(name="guest_port",header="")
    private String guestPort;
    
    @Column(name="guest_evaluation",header="")
    private String guestEvaluation;
    
    @Column(name="img",header="")
    private String img;
    
    @Column(name="sort",header="")
    private Integer sort;
    
    @Column(name="spider",header="")
    private Integer spider;
    
    @Column(name="status",header="")
    private Integer status;
    
    @Column(name="add_time",header="")
    private Date addTime;
    
    @Column(name="add_user",header="")
    private Integer addUser;
    
    @Column(name="upd_time",header="")
    private Date updTime;
    
    @Column(name="upd_user",header="")
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