package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_base_nationality",header="国籍",orderBy="code asc")
public class Nationality extends Entity {

    /**
	 * 
	 */

	@Column(name="code",header="编码")
    private String code;

    @Column(name="name",header="国家名字")
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