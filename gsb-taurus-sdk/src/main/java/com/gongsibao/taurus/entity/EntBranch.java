package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  EntBranch   
 * @Description:TODO 分支机构
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:09:42   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class EntBranch implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2695759747277774656L;
	
	
	
	/**   
	 * @Fields name : TODO(案号)   
	 */   
	private String Id;
	
	/**   
	 * @Fields name : TODO(分支机构名称)   
	 */   
	private String name;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
