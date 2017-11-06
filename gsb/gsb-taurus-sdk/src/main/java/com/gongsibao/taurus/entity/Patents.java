package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  Patents   
 * @Description:TODO 专利信息
 * @author: 韩伟 
 * @date:   2017年10月19日 上午11:11:33   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class Patents implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5837862005684272400L;

	/**
	 * @Fields name : TODO(专利类型)
	 */
	private String patType;
	
	/**
	 * @Fields name : TODO(专利名称)
	 */
	private String patentName;
	
	/**
	 * @Fields name : TODO(公布日期)
	 */
	private String applicationPublishTime;
	
	/**
	 * @Fields name : TODO(公司ID)
	 */
	private String entid;

	public String getPatType() {
		return patType;
	}

	public void setPatType(String patType) {
		this.patType = patType;
	}

	public String getPatentName() {
		return patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}

	public String getApplicationPublishTime() {
		return applicationPublishTime;
	}

	public void setApplicationPublishTime(String applicationPublishTime) {
		this.applicationPublishTime = applicationPublishTime;
	}

	public String getEntid() {
		return entid;
	}

	public void setEntid(String entid) {
		this.entid = entid;
	}
}
