package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_package",header="产品包")
public class ProductPackage extends BaseEntity {

	private static final long serialVersionUID = 2084809012877696348L;
	
	@Column(name="name",header="名称")
	private String name;
	
	@Exclusive//这个字段可能是mysql关键字，查询就会报错
	@Column(name="desc",header="标题（显示名称）")
    private String desc;
	
	@Column(name="sort",header="拍下")
    private Integer sort;
    
    @Column(name="is_enabled",header="是否启用")
    private Boolean enabled = true;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

    
}