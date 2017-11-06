package com.gongsibao.api.dto;

/**
 * @author hw
 * 车型
 */
public class CarTypeDTO extends BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1299939714421167429L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 上级ID
	 */
	private Long parentId;
	
	/**
	 * 全称
	 */
	private String pathName;
	
	/**
	 * 级别 - 品牌:1，车系:2，车型:3
	 */
	private Integer level;
	
	/**
	 * 图片地址
	 */
	private String imgUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
