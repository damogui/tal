package com.gongsibao.entity.cms;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "cms_bottomnav", header = "")
public class Bottomnav extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 3920385162116885953L;
	@Column(name = "bottom_category", header = "")
	private Integer bottomCategory;

	@Column(name = "name", header = "")
	private String name;

	@Column(name = "url", header = "")
	private String url;

	@Column(name = "img", header = "")
	private String img;

	@Column(name = "spider", header = "")
	private Integer spider;

	@Column(name = "sort", header = "")
	private Integer sort;

	@Column(name = "status", header = "")
	private Integer status;

	@Column(name = "add_time", header = "")
	private Date addTime;

	@Column(name = "add_user", header = "")
	private Integer addUser;

	@Column(name = "upd_time", header = "")
	private Date updTime;

	@Column(name = "upd_user", header = "")
	private Integer updUser;

	public Integer getBottomCategory() {
		return bottomCategory;
	}

	public void setBottomCategory(Integer bottomCategory) {
		this.bottomCategory = bottomCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getSpider() {
		return spider;
	}

	public void setSpider(Integer spider) {
		this.spider = spider;
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