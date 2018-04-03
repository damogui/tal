package com.gongsibao.entity.igirl.ic.baseinfo;
import com.gongsibao.entity.gardian.baseinfo.ProvidesEnv;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Table(name="ig_base_areaone",header="省市大类",orderBy="code asc")
public class AreaOne extends Entity {
	/**
	 * 
	 */
//	private static final long serialVersionUID = -7045611896623763333L;

	@Column(name="code",header="编码")
    private String code;
	
    @Column(name="name",header="标题")
    private String name;

//    @Subs(foreignKey="areaOneId",header="地市id",subType=AreaTwo.class)

    @Exclusive
    @JsonIgnore
    private List<AreaTwo> areaTwos = new ArrayList<AreaTwo>();


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

    public List<AreaTwo> getAreaTwos() {
        return areaTwos;
    }

    public void setAreaTwos(List<AreaTwo> areaTwos) {
        this.areaTwos = areaTwos;
    }
}