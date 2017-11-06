package com.gongsibao.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.netsharp.json.JsonTimeStampDeserializer;
import org.netsharp.json.JsonTimeStampSerializer;



/**
 * @author hw 用户
 */
public class UserDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3048125236780764887L;

	private String token;

	/**
	 * 手机号码
	 */
	private String phoneNumber;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 余额
	 */
	private BigDecimal balance = BigDecimal.ZERO;

	/**
	 * 会员等级
	 */
	private String level = "";

	/**
	 * 大客户
	 */
	private boolean isBigCustomer = false;

	/**
	 * 注册日期
	 */

	@JsonDeserialize(using = JsonTimeStampDeserializer.class)
	@JsonSerialize(using = JsonTimeStampSerializer.class)
	private Date registerTime;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isBigCustomer() {
		return isBigCustomer;
	}

	public void setBigCustomer(boolean isBigCustomer) {
		this.isBigCustomer = isBigCustomer;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
