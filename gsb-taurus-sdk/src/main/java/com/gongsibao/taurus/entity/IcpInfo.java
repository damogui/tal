package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  IcpInfo   
 * @Description:TODO ICP 信息
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:11:14   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class IcpInfo implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -305327615058431309L;

	/**
	 * @Fields name : TODO(网站地址)
	 */
	private String webSite;
	
	/**
	 * @Fields name : TODO(审核时间)
	 */
	private String recordAuditDate;
	
	/**
	 * @Fields name : TODO( 网站备案/许可证号)
	 */
	private String webRecord;
	
	/**
	 * @Fields name : TODO(网站名称)
	 */
	private String webName;
	
	/**
	 * @Fields name : TODO(域名)
	 */
	private String webDomain;

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getRecordAuditDate() {
		return recordAuditDate;
	}

	public void setRecordAuditDate(String recordAuditDate) {
		this.recordAuditDate = recordAuditDate;
	}

	public String getWebRecord() {
		return webRecord;
	}

	public void setWebRecord(String webRecord) {
		this.webRecord = webRecord;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebDomain() {
		return webDomain;
	}

	public void setWebDomain(String webDomain) {
		this.webDomain = webDomain;
	}
}
