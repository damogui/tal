package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.uc.dic.BdDictType;

@Table(name="uc_organization_bd_dict_map",header="")
public class OrganizationBdDictMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6762842238477044010L;
	@Column(name="organization_id",header="组织机构序号")
    private Integer organizationId;
	
    @Reference(foreignKey="organizationId")
    private Organization organization;
    
    @Column(name="dict_id")
    private Integer dictId;
    
    @Reference(foreignKey="dictId")
    private Dict dict;
    
    @Column(name="type",header="关系类型，1服务产品分类、2服务地区")
    private BdDictType type;

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
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	public BdDictType getType() {
		return type;
	}
	public void setType(BdDictType type) {
		this.type = type;
	}
}