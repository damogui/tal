package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  Judgment   
 * @Description:TODO 法院判决
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:08:33   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class Judgment implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7487818858050275795L;

	/**
	 * @Fields name : TODO(发布时间)
	 */
	private String judgetime;
	
	/**
	 * @Fields name : TODO(判决书)
	 */
	private String title;
	
	/**
	 * @Fields name : TODO(执行法院)
	 */
	private String court;
	
	/**
	 * @Fields name : TODO(案件编号)
	 */
	private String caseno;
	
	/**
	 * @Fields name : TODO(原告被告)
	 */
	private String party;

	public String getJudgetime() {
		return judgetime;
	}

	public void setJudgetime(String judgetime) {
		this.judgetime = judgetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getCaseno() {
		return caseno;
	}

	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
}
