package com.gongsibao.entity.er;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order_task_auditor")
public class OrderTaskAuditor extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1583786711039788370L;
	@Column(name="order_task_id",header="OrderTaskId")
    private Integer orderTaskId;
    @Column(name="user_id",header="UserId")
    private Integer userId;

    public Integer getOrderTaskId() {
        return orderTaskId;
    }
    public void setOrderTaskId(Integer orderTaskId) {
        this.orderTaskId = orderTaskId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}