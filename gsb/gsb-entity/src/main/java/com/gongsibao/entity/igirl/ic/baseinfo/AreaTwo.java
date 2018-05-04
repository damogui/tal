package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_base_area_two",header="县级地区",orderBy="code asc")
public class AreaTwo extends Entity {

    /**
	 * 
	 */

	@Column(name="code",header="编码分组")
    private String code;

    @Column(name="name",header="地区名字")
    private String name;

    @Column(name="level",header="层次")
    private Integer level=2;

    @Column(name="parent_id",header="上级代码")
    private Integer parentId;

    @Column(name="area_one_id",header="省级ID")
    private Integer areaOneId = -1;

    @Reference(foreignKey="areaOneId",header="省级ID")
    private AreaOne areaOne;


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


    public Integer getAreaOneId() {
        return areaOneId;
    }

    public void setAreaOneId(Integer areaOneId) {
        this.areaOneId = areaOneId;
    }

    public AreaOne getAreaOne() {
        return areaOne;
    }

    public void setAreaOne(AreaOne areaOne) {
        this.areaOne = areaOne;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}