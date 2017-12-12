package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_trademark_category")
public class TrademarkCategory extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2178160278556130435L;
	@Column(header="pid")
    private Integer pid;
    @Column(header="code")
    private String code;
    @Column(header="sort")
    private Integer sort;
    @Column(header="name")
    private String name;
    @Column(header="url")
    private String url;
    @Column(name="un_on_url",header="UnOnUrl")
    private String unOnUrl;
    @Column(name="pc_url",header="PcUrl")
    private String pcUrl;
    @Column(header="alias")
    private String alias;
    @Column(header="level")
    private Integer level;
    @Column(header="description")
    private String description;

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
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
}