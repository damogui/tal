package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  Tm   
 * @Description:TODO 商标
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:12:36   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class Tm implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -81645783403246227L;


	/**
	 * @Fields name : TODO(id)
	 */
	private int id;
	

	/**
	 * @Fields name : TODO(登记号)
	 */
	private String regNo;
	

	/**
	 * @Fields name : TODO(商标分类)
	 */
	private String classify;
	

	/**
	 * @Fields name : TODO(商标名称)
	 */
	private String tmName;
	

	/**
	 * @Fields name : TODO(商标所有者)
	 */
	private String applicantCn;
	 
	 
	/**
	 * @Fields name : TODO( 状态)
	 */
	private String status;
	 
	
	/**   
	 * @Fields agent : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String agent;
	
	/**   
	 * @Fields color : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String color;
	
	/**   
	 * @Fields announcemenIssue : TODO(用一句话描述这个变量表示什么)   
	 */   
	private int announcemenIssue;
	
	/**   
	 * @Fields announcementDate : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String announcementDate;
	
	/**   
	 * @Fields regDate : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String regDate;
	
	/**   
	 * @Fields appDate : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String appDate;
	
	/**   
	 * @Fields applicantEn : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String applicantEn;
	
	/**   
	 * @Fields applicantOther1 : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String applicantOther1;
	
	/**   
	 * @Fields intCls : TODO(用一句话描述这个变量表示什么)   
	 */   
	private int intCls;
	
	/**   
	 * @Fields applicantOther2 : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String applicantOther2;
	
	/**   
	 * @Fields yxqrq : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String yxqrq;
	
	/**   
	 * @Fields privateDateStart : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String privateDateStart;
	
	/**   
	 * @Fields gjzcrq : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String gjzcrq;
	
	/**   
	 * @Fields hqzdrq : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String hqzdrq;
	
	/**   
	 * @Fields category : TODO(用一句话描述这个变量表示什么)   
	 */   
	private int category;
	
	/**   
	 * @Fields regIssue : TODO(用一句话描述这个变量表示什么)   
	 */   
	private int regIssue;
	
	/**   
	 * @Fields addressEn : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String addressEn;
	
	/**   
	 * @Fields privateDateEnd : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String privateDateEnd;
	
	/**   
	 * @Fields addressCn : TODO(用一句话描述这个变量表示什么)   
	 */   
	private String addressCn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getApplicantCn() {
		return applicantCn;
	}

	public void setApplicantCn(String applicantCn) {
		this.applicantCn = applicantCn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAnnouncemenIssue() {
		return announcemenIssue;
	}

	public void setAnnouncemenIssue(int announcemenIssue) {
		this.announcemenIssue = announcemenIssue;
	}

	public String getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getApplicantEn() {
		return applicantEn;
	}

	public void setApplicantEn(String applicantEn) {
		this.applicantEn = applicantEn;
	}

	public String getApplicantOther1() {
		return applicantOther1;
	}

	public void setApplicantOther1(String applicantOther1) {
		this.applicantOther1 = applicantOther1;
	}

	public int getIntCls() {
		return intCls;
	}

	public void setIntCls(int intCls) {
		this.intCls = intCls;
	}

	public String getApplicantOther2() {
		return applicantOther2;
	}

	public void setApplicantOther2(String applicantOther2) {
		this.applicantOther2 = applicantOther2;
	}

	public String getYxqrq() {
		return yxqrq;
	}

	public void setYxqrq(String yxqrq) {
		this.yxqrq = yxqrq;
	}

	public String getPrivateDateStart() {
		return privateDateStart;
	}

	public void setPrivateDateStart(String privateDateStart) {
		this.privateDateStart = privateDateStart;
	}

	public String getGjzcrq() {
		return gjzcrq;
	}

	public void setGjzcrq(String gjzcrq) {
		this.gjzcrq = gjzcrq;
	}

	public String getHqzdrq() {
		return hqzdrq;
	}

	public void setHqzdrq(String hqzdrq) {
		this.hqzdrq = hqzdrq;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getRegIssue() {
		return regIssue;
	}

	public void setRegIssue(int regIssue) {
		this.regIssue = regIssue;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public String getPrivateDateEnd() {
		return privateDateEnd;
	}

	public void setPrivateDateEnd(String privateDateEnd) {
		this.privateDateEnd = privateDateEnd;
	}

	public String getAddressCn() {
		return addressCn;
	}

	public void setAddressCn(String addressCn) {
		this.addressCn = addressCn;
	}
}
