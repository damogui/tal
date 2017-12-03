package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_file")
public class File extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5654996031271694764L;
	@Column(name="tab_name",header="TabName")
    private String tabName;
    @Column(name="form_id",header="FormId")
    private Integer formId;
    @Column(header="name")
    private String name;
    @Column(header="url")
    private String url;

    public String getTabName() {
        return tabName;
    }
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
    public Integer getFormId() {
        return formId;
    }
    public void setFormId(Integer formId) {
        this.formId = formId;
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

}