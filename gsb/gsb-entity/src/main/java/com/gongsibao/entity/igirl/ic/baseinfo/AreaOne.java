package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.Entity;

@Table(name="ig_base_area_one",header="省市大类",orderBy="code asc")
public class AreaOne extends Entity {
	/**
	 * 
	 */
	@Column(name="code",header="编码")
    private String code;
	
    @Column(name="name",header="标题")
    private String name;


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
}