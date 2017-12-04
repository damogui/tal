package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.uc.dic.SupplerAuditStatus;

@Table(name="uc_suppler_audit_log",header="供应商审核日志")
public class SupplerAuditLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4539380875797903886L;
	@Column(name="user_id")
    private Integer userId;
	
    @Reference(foreignKey="userId")
    private User user;
	
    @Column(name="status_id",header="供应商审核状态 1、初次申请 2 等待审核 3审核驳回 4 审核通过 5信息修改等待审核 6信息修改审核驳回")
    private SupplerAuditStatus statusId;

    @Column(name="remark",header="备注")
    private String remark;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SupplerAuditStatus getStatusId() {
		return statusId;
	}
	public void setStatusId(SupplerAuditStatus statusId) {
		this.statusId = statusId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}