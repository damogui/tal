package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_organization_bd_dict_map")
public class OrganizationBdDictMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6762842238477044010L;
	@Column(name="organization_id")
    private Integer organizationId;
    @Column(name="dict_id")
    private Integer dictId;
    private Integer type;

    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    public Integer getDictId() {
        return dictId;
    }
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
}