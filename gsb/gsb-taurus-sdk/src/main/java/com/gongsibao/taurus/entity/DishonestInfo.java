package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  DishonestInfo   
 * @Description:TODO 失信人信息
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:10:46   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class DishonestInfo implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3683987369450317029L;

	/**   
	 * @Fields name : TODO(案号)   
	 */   
	private String casecode;
	
	/**   
	 * @Fields name : TODO(立案时间)   
	 */   
	private String regDt;
	
	/**   
	 * @Fields name : TODO(履行情况)   
	 */   
	private String performance;
	
	/**   
	 * @Fields name : TODO(serial)   
	 */   
	private String serial;
	 
	/**   
	 * @Fields name : TODO(执行法院)   
	 */   
	private String courtname;
	
	/**   
	 * @Fields name : TODO(生效法律文书确定的义务)   
	 */   
	private String duty;
	
	/**   
	 * @Fields name : TODO(公布时间)   
	 */   
	private String publishDt;
	
	/**   
	 * @Fields name : TODO(执行依据文号)   
	 */   
	private String gistid;
 
	public String getCasecode() {
		return casecode;
	}

	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getCourtname() {
		return courtname;
	}

	public void setCourtname(String courtname) {
		this.courtname = courtname;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPublishDt() {
		return publishDt;
	}

	public void setPublishDt(String publishDt) {
		this.publishDt = publishDt;
	}

	public String getGistid() {
		return gistid;
	}

	public void setGistid(String gistid) {
		this.gistid = gistid;
	}
}
