package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  WorksCopyright   
 * @Description:TODO 作品著作权
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:12:51   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class WorksCopyright implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7207111454584688472L;


	/**
	 * @Fields name : TODO(名称)
	 */
	private String name;
	 
	/**
	 * @Fields name : TODO(登记号)
	 */
	private String regNum;
	

	/**
	 * @Fields name : TODO(类型)
	 */
	private String type;
	

	/**
	 * @Fields name : TODO(登记日期)
	 */
	private String regTime;
	

	/**
	 * @Fields name : TODO(完成日期)
	 */
	private String finishTime;
	

	/**
	 * @Fields name : TODO(首次发布日期)
	 */
	private String firstPublishTime;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRegNum() {
		return regNum;
	}


	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getRegTime() {
		return regTime;
	}


	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}


	public String getFinishTime() {
		return finishTime;
	}


	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}


	public String getFirstPublishTime() {
		return firstPublishTime;
	}


	public void setFirstPublishTime(String firstPublishTime) {
		this.firstPublishTime = firstPublishTime;
	}
}
