package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_base_areatwo",header="县级地区",orderBy="code asc")
public class AreaTwo extends Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6388786836663106011L;


	@Column(name="code",header="编码分组")
    private String code;

    @Column(name="name",header="地区名字")
    private String name;

    @Column(name="level",header="层次")
    private int level=2;

    @Column(name="areaone_id",header="省级ID")
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}