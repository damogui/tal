package com.gongsibao.entity.uc;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_organization_map",header="员工组织机构")
public class UserOrganizationMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -581614742153635532L;
	@Column(name="user_id")
    private Integer userId;
	
    @JsonIgnore
    @Reference(foreignKey="userId")
    private User user;
	
    @Column(name="organization_id")
    private Integer organizationId;
    
    @Reference(foreignKey="organizationId")
    private Organization organization;
    
    @Column(name="add_time")
    private Timestamp addTime;
    @Column(name="add_user_id")
    private Integer addUserId;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}