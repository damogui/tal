package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="ig_base_corp_lic_type",header="证照类型",orderBy="code asc")
public class EntLicType extends Entity {

    /**
	 * 
	 */

	@Column(name="code",header="编码")
    private String code;

    @Column(name="name",header="执照名称")
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