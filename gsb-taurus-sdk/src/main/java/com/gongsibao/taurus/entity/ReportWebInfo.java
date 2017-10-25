package com.gongsibao.taurus.entity;

/**   
 * @ClassName:  ReportWebInfo   
 * @Description:TODO 网站信息
 * @author: 韩伟
 * @date:   2017年10月20日 下午3:27:16   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class ReportWebInfo implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8031539386034936781L;

	
	/**   
	 * @Fields webType : 网站类型  
	 */   
	private String webType; 
	
	/**   
	 * @Fields website : 网址
	 */   
	private String website; 
	
	/**   
	 * @Fields name : 名称
	 */   
	private String name;

	public String getWebType() {
		return webType;
	}

	public void setWebType(String webType) {
		this.webType = webType;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
