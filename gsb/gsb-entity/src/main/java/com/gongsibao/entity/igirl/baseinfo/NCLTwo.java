package com.gongsibao.entity.igirl.baseinfo;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Table(name="ig_base_ncltwo",header="商标小类")
public class NCLTwo extends Entity {

    @Column(name="code",header="二级编码/分组")
    private String code;


    @Column(name="third_code",header="小类编码")
    private String thirdCode;
	
    @Column(name="name",header="分类标题")
    private String name;

    @Column(name="nclone_id",header="商标大类ID")
    private Integer nclOneId = -1;

    @Reference(foreignKey="nclOneId",header="商标大类")
    private NCLOne nclOne;


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

    public Integer getNclOneId() {
        return nclOneId;
    }

    public void setNclOneId(Integer nclOneId) {
        this.nclOneId = nclOneId;
    }

    public NCLOne getNclOne() {
        return nclOne;
    }

    public void setNclOne(NCLOne nclOne) {
        this.nclOne = nclOne;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }
}