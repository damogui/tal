package com.gongsibao.entity.product;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_package",header="产品包")
public class ProductPackage extends BaseEntity {

	private static final long serialVersionUID = 2084809012877696348L;
	
	@Column(name="name",header="名称")
	private String name;
	
	@Exclusive
	@Column(name="content",header="套餐内容")
    private String content;
	
	@Exclusive
	@Column(name="desc",header="描述")
    private String desc;
	
	@Column(name="sort",header="排序")
    private Integer sort;
    
    @Column(name="is_enabled",header="是否启用")
    private Boolean enabled = true;
    
	@Subs(foreignKey="packageId",header="套餐产品明细",subType=PackageProductMap.class)
	private List<PackageProductMap> maps;

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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<PackageProductMap> getMaps() {
		return maps;
	}
	public void setMaps(List<PackageProductMap> maps) {
		this.maps = maps;
	}
}