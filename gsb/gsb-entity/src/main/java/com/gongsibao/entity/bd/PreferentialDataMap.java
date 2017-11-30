package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_preferential_data_map")
public class PreferentialDataMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1639661333298312070L;
	@Column(name="preferential_id",header="PreferentialId")
    private Integer preferentialId;
    @Column(name="data_id",header="DataId")
    private Integer dataId;
    @Column(header="name")
    private String name;
    @Column(header="type")
    private Integer type;

    public Integer getPreferentialId() {
        return preferentialId;
    }
    public void setPreferentialId(Integer preferentialId) {
        this.preferentialId = preferentialId;
    }
    public Integer getDataId() {
        return dataId;
    }
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
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