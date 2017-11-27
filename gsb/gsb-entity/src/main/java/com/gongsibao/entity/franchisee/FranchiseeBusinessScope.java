package com.gongsibao.entity.franchisee;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.franchisee.dic.BusinessScope;

@Table(name="bd_franchisee_business_scope",header="经营范围")
public class FranchiseeBusinessScope extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7647465505472047429L;

	@Column(name="franchisee_id")
    private Integer franchiseeId;
	
	@JsonIgnore
    @Reference(foreignKey="franchiseeId")
    private Franchisee franchisee;
	
    @Column(name="scope",header="经营范围")
    private BusinessScope scope;
    
//	@Column(name="scope_id")
//	private Integer scopeId;
//	
//	@Reference(foreignKey="scopeId",header="经营范围:type=201")
//	private Dict scope;

	public Integer getFranchiseeId() {
		return franchiseeId;
	}

	public void setScope(BusinessScope scope) {
		this.scope = scope;
	}

	public void setFranchiseeId(Integer franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Franchisee getFranchisee() {
		return franchisee;
	}

	public void setFranchisee(Franchisee franchisee) {
		this.franchisee = franchisee;
	}

	public BusinessScope getScope() {
		return scope;
	}
}
