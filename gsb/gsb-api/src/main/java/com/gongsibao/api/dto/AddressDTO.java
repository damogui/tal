package com.gongsibao.api.dto;


/**
 * @author hw
 * 地址
 */
public class AddressDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1916390368356928903L;

	/**
	 * 用户Id
	 */
	private Long userId;

	/**
	 * 手机号码
	 */
	private String phoneNumber = "";

	/**
	 * 姓名
	 */
	private String name = "";

	/**
	 * 地址
	 */
	private String address = "";

	/**
	 * 默认的
	 */
	private Boolean defaulted = false;

	/**
	 * 是否无效
	 */
	private Boolean invalid = false;
	
	/**
	 * 省
	 */
	private String province;

	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 区/县
	 */
	private String county;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getDefaulted() {
		return defaulted;
	}

	public void setDefaulted(Boolean defaulted) {
		this.defaulted = defaulted;
	}

	public Boolean getInvalid() {
		return invalid;
	}

	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
}
