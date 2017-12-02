package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_validity_scope",header="")
public class ValidityScope extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7875207552993256353L;
	@Column(name="validity_id",header="产品有效期id")
    private Integer validityId;
	
    @Column(name="scope_name",header="经营范围关键字")
    private String scopeName;

    public Integer getValidityId() {
        return validityId;
    }
    public void setValidityId(Integer validityId) {
        this.validityId = validityId;
    }
    public String getScopeName() {
        return scopeName;
    }
    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
} 