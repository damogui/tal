package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_accept_log")
public class UserAcceptLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1408535943976722927L;
	@Column(name="is_accept_orde")
    private Integer isAcceptOrde;
    @Column(name="user_id")
    private Integer userId;
    @Column(name="operator_id")
    private Integer operatorId;
    @Column(name="add_time")
    private Date addTime;

    public Integer getIsAcceptOrde() {
        return isAcceptOrde;
    }
    public void setIsAcceptOrde(Integer isAcceptOrde) {
        this.isAcceptOrde = isAcceptOrde;
    }
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
}