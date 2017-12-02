package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_role")
public class Role extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5596388151147746516L;
	
    @Column(name="name",header="角色名称")
	private String name;
	
    @Column(name="tag",header="角色标志(拼音首字母)")
    private String tag;
    
    @Column(name="description",header="描述信息")
    private String description;
    
    @Column(name="sort",header="排序")
    private Double sort = 1D;
    
    @Column(name="is_enabled",header="是否启用 0否 1是")
    private Boolean enabled = true;
    
    @Column(name="remark",header="说明")
    private String remark;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}