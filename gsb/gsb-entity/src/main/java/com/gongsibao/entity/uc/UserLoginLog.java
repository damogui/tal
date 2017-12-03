package com.gongsibao.entity.uc;


import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_login_log",header="员工登录日志")
public class UserLoginLog extends BaseEntity {
	
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6287652606232491739L;
	
	@Column(name="ip",header="ip")
	private String ip;
	
    @Column(name="ip_addr",header="ip对应地址信息")
    private String ipAddr;
    
    @Column(name="user_id")
    private Integer userId;
	
    @Reference(foreignKey="userId")
    private User user;
    
    @Column(name="user_agent")
    private String userAgent;

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}