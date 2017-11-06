package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_validity_scope")
public class ValidityScope extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7875207552993256353L;
	@Column(name="validity_id")
    private Integer validityId;
    @Column(name="scope_name")
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