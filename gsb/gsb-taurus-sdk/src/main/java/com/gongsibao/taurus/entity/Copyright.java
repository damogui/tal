package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  Copyright   
 * @Description:TODO 软件著作权
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:11:54   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class Copyright implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4724340497398337064L;

	
	/**   
	 * @Fields name : TODO(登记日期)   
	 */   
	private String regDt;
	
	/**   
	 * @Fields name : TODO(名称)   
	 */   
	private String fullName;
	
	/**   
	 * @Fields name : TODO(登记号)   
	 */   
	private String regnum;
	
	/**   
	 * @Fields name : TODO(首次发表时间)   
	 */   
	private String publishDt;
	
	/**   
	 * @Fields name : TODO(版本)   
	 */   
	private String version;
	
	/**   
	 * @Fields name : TODO(登记类别)   
	 */   
	private String catnum;

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRegnum() {
		return regnum;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public String getPublishDt() {
		return publishDt;
	}

	public void setPublishDt(String publishDt) {
		this.publishDt = publishDt;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCatnum() {
		return catnum;
	}

	public void setCatnum(String catnum) {
		this.catnum = catnum;
	}
}
