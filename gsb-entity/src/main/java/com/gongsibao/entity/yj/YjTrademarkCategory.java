package com.gongsibao.entity.yj;

import java.util.List;

import com.gongsibao.entity.BaseEntity;

public class YjTrademarkCategory extends BaseEntity{


    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2136493636640928548L;

	/**
     * 父级id
     */
    private Integer pid;

    /*父级id（加密）*/
    private String pidStr;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /*别名*/
    private String alias;

    /*层级(1：大类，2：小类，3：商品)*/
    private Integer level;

    /*描述*/
    private String description;

    /*图片url*/
    private String url;

    /*排序编号*/
    private Integer sotr;

    /*未选中样式的图片*/
    private String unOnUrl;

    /*pc端显示的图片*/
    private String pcUrl;

    /*子集*/
    private List<YjTrademarkCategory> childrens;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPidStr() {
		return pidStr;
	}

	public void setPidStr(String pidStr) {
		this.pidStr = pidStr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public Integer getSotr() {
		return sotr;
	}

	public void setSotr(Integer sotr) {
		this.sotr = sotr;
	}

	public String getUnOnUrl() {
		return unOnUrl;
	}

	public void setUnOnUrl(String unOnUrl) {
		this.unOnUrl = unOnUrl;
	}

	public String getPcUrl() {
		return pcUrl;
	}

	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}

	public List<YjTrademarkCategory> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<YjTrademarkCategory> childrens) {
		this.childrens = childrens;
	}
}
