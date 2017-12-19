package com.gongsibao.entity.franchisee;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="bd_franchisee_linkman",header="联系人")
public class FranchiseeLinkman extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1062577570290854602L;

	@Column(name="franchisee_id")
    private Integer franchiseeId;
	
	@JsonIgnore
    @Reference(foreignKey="franchiseeId")
    private Franchisee franchisee;
	
    @Column(name="name",header="姓名")
    private String name;
    
    @Column(name="mobile",header="手机号")
    private String mobile;
    
    @Column(name="post",header="职务")
    private String post;
    
    @Column(name="weixin",header="微信")
    private String weixin;
    
    @Column(name="qq",header="qq")
    private String qq;
    
    @Column(name="tel",header="座机")
    private String tel;
    
    @Column(name="main",header="主联系人")
    private Boolean main = false;

	public Integer getFranchiseeId() {
		return franchiseeId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
