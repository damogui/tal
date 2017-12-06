package com.gongsibao.entity.uc;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="uc_user_business")
public class UserBusiness extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 829072585086601250L;
	

    @JsonIgnore
    @Reference(foreignKey="userId")
    private User user;
    
	@Column(name="user_id")
    private Integer userId;
	
	@Reference(foreignKey="businessId",header="归属事业部：108")
	private Dict business;
	
    @Column(name="business_id")
    private Integer businessId;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

	public Dict getBusiness() {
		return business;
	}
	public void setBusiness(Dict business) {
		this.business = business;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}