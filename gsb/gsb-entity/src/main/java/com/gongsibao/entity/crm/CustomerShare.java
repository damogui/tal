package com.gongsibao.entity.crm;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_customer_share",header="分享记录")
public class CustomerShare extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2152429777856544213L;
	@Column(name="customer_id",header="")
    private Integer customerId;
    @Column(name="follow_user_id",header="")
    private Integer followUserId;
    @Column(name="share_user_id",header="")
    private Integer shareUserId;
    @Column(name="share_time",header="")
    private Date shareTime;
    @Column(name="is_bbk",header="")
    private String isBbk ="0";

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

    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
}