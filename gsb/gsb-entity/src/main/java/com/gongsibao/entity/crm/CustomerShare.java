package com.gongsibao.entity.crm;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_customer_share")
public class CustomerShare extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2152429777856544213L;
	@Column(name="customer_id")
    private Integer customerId;
    @Column(name="follow_user_id")
    private Integer followUserId;
    @Column(name="share_user_id")
    private Integer shareUserId;
    @Column(name="share_time")
    private Date shareTime;
    @Column(name="is_bbk")
    private String isBbk;

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getFollowUserId() {
        return followUserId;
    }
    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }
    public Integer getShareUserId() {
        return shareUserId;
    }
    public void setShareUserId(Integer shareUserId) {
        this.shareUserId = shareUserId;
    }
    public Date getShareTime() {
        return shareTime;
    }
    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
}