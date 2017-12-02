package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_accept_log")
public class UserAcceptLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1408535943976722927L;
	@Column(name="is_accept_orde",header="是否接单 0不接单 1接单")
    private Boolean isAcceptOrde;
	
    @Column(name="user_id")
    private Integer userId;
    
    @Reference(foreignKey="userId")
    private User user;
    
    @Column(name="operator_id")
    private Integer operatorId;
    
    @Reference(foreignKey="operatorId")
    private User operator;
    
    @Column(name="add_time")
    private Date addTime;


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
	public Boolean getIsAcceptOrde() {
		return isAcceptOrde;
	}
	public void setIsAcceptOrde(Boolean isAcceptOrde) {
		this.isAcceptOrde = isAcceptOrde;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getOperator() {
		return operator;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
    
}