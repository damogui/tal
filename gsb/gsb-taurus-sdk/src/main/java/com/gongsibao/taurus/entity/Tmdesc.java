package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  Tmdesc   
 * @Description:TODO 商标详情
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:13:59   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class Tmdesc implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2676444456865857866L;

	
	/**   
	 * @Fields id : TODO( 主键)   
	 */   
	private int id;
	
	/**   
	 * @Fields reg_no : TODO(注册号)   
	 */
	 
	private String regNo; 
	
	/**   
	 * @Fields int_cls : TODO(国际分类)   
	 */   
	private String intCls; 
	
	/**   
	 * @Fields tm_name : TODO(商标名)   
	 */   
	private String tmName; 
	
	/**   
	 * @Fields app_date : TODO(申请日期)   
	 */   
	private String appDate; 
	
	/**   
	 * @Fields application_cn : TODO(申请人中文)   
	 */   
	private String applicationCn; 
	
	/**   
	 * @Fields address_cn : TODO(申请人地址中文)   
	 */   
	private String addressCn; 
	
	/**   
	 * @Fields application_other_1 : TODO(共有申请人 1)   
	 */   
	private String applicationOther1; 
	
	/**   
	 * @Fields application_other_2 : TODO(共有申请人 2)   
	 */   
	private String applicationOther2;
	
	/**   
	 * @Fields application_en : TODO(申请人英文)   
	 */   
	private String applicationEn; 
	
	/**   
	 * @Fields address_en : TODO(申请人地址英文)   
	 */   
	private String addressEn; 
	
	/**   
	 * @Fields announcement_issue : TODO(初审公告期号)   
	 */   
	private String announcementIssue; 
	
	/**   
	 * @Fields announcement_date : TODO(初审公告日期)   
	 */   
	private String announcementDate; 
	
	/**   
	 * @Fields reg_issue : TODO(注册公告期号)   
	 */   
	private String regIssue; 
	
	/**   
	 * @Fields reg_date : TODO(注册公告日期)   
	 */   
	private String regDate; 
	
	/**   
	 * @Fields private_date_start : TODO(专用权期限开始日期)   
	 */   
	private String privateDateStart; 
	
	/**   
	 * @Fields private_date_end : TODO(专用权期限结束日期)   
	 */   
	private String privateDateEnd; 
	
	/**   
	 * @Fields agent : TODO(代理人名称)   
	 */   
	private String agent; 
	
	/**   
	 * @Fields category : TODO(商标类型)   
	 */   
	private String category; 
	
	/**   
	 * @Fields hqzdrq : TODO(后期指定日期)   
	 */   
	private String hqzdrq; 
	
	/**   
	 * @Fields gjzcrq : TODO(国际注册日期)   
	 */   
	private String gjzcrq; 
	
	/**   
	 * @Fields yxqrq : TODO(优先权日期)   
	 */   
	private String yxqrq; 
	
	/**   
	 * @Fields color : TODO(指定颜色)   
	 */   
	private String color; 
	
	/**   
	 * @Fields status : TODO(商标状态)   
	 */   
	private String status;

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

	public String getIntCls() {
		return intCls;
	}

	public void setIntCls(String intCls) {
		this.intCls = intCls;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getApplicationCn() {
		return applicationCn;
	}

	public void setApplicationCn(String applicationCn) {
		this.applicationCn = applicationCn;
	}

	public String getAddressCn() {
		return addressCn;
	}

	public void setAddressCn(String addressCn) {
		this.addressCn = addressCn;
	}

	public String getApplicationOther1() {
		return applicationOther1;
	}

	public void setApplicationOther1(String applicationOther1) {
		this.applicationOther1 = applicationOther1;
	}

	public String getApplicationOther2() {
		return applicationOther2;
	}

	public void setApplicationOther2(String applicationOther2) {
		this.applicationOther2 = applicationOther2;
	}

	public String getApplicationEn() {
		return applicationEn;
	}

	public void setApplicationEn(String applicationEn) {
		this.applicationEn = applicationEn;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public String getAnnouncementIssue() {
		return announcementIssue;
	}

	public void setAnnouncementIssue(String announcementIssue) {
		this.announcementIssue = announcementIssue;
	}

	public String getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String getRegIssue() {
		return regIssue;
	}

	public void setRegIssue(String regIssue) {
		this.regIssue = regIssue;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPrivateDateStart() {
		return privateDateStart;
	}

	public void setPrivateDateStart(String privateDateStart) {
		this.privateDateStart = privateDateStart;
	}

	public String getPrivateDateEnd() {
		return privateDateEnd;
	}

	public void setPrivateDateEnd(String privateDateEnd) {
		this.privateDateEnd = privateDateEnd;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHqzdrq() {
		return hqzdrq;
	}

	public void setHqzdrq(String hqzdrq) {
		this.hqzdrq = hqzdrq;
	}

	public String getGjzcrq() {
		return gjzcrq;
	}

	public void setGjzcrq(String gjzcrq) {
		this.gjzcrq = gjzcrq;
	}

	public String getYxqrq() {
		return yxqrq;
	}

	public void setYxqrq(String yxqrq) {
		this.yxqrq = yxqrq;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
