package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_base_chapter_one",header="注册刻章公司登记区",orderBy="code asc")
public class ChapterOne extends Entity {

    @Column(name="code",header="编码")
    private String code;

    @Column(name="name",header="地区名称")
    private String name;

    @Column(name="first_code",header="一级代码")
    private String firstCode;

    @Column(name="sec_code",header="二级代码")
    private String secCode;

    @Column(name="first_name",header="一级名称")
    private String firstName;

    @Column(name="sec_name",header="二级名称")
    private String secName;


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

    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }
}