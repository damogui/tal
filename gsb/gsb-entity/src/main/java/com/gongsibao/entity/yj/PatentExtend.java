package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_patent_extend")
public class PatentExtend extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6133405498961008370L;
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="yj_patent_id",header="YjPatentId")
    private Integer yjPatentId;
    @Column(header="name")
    private String name;
    @Column(header="type")
    private Integer type;

    public Integer getYjPatentId() {
        return yjPatentId;
    }
    public void setYjPatentId(Integer yjPatentId) {
        this.yjPatentId = yjPatentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
}