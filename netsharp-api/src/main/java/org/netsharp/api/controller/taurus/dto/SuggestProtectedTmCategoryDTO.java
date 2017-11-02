package org.netsharp.api.controller.taurus.dto;

/**   
 * @ClassName:  SuggestProtectedTmCategoryDTO   
 * @Description:TODO 建议保户商标大类
 * @author: 韩伟
 * @date:   2017年10月30日 下午8:52:34   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class SuggestProtectedTmCategoryDTO {

	private String intclsName;
	
	private String name;
	
	private String categoryId;

	public String getIntclsName() {
		return intclsName;
	}

	public void setIntclsName(String intclsName) {
		this.intclsName = intclsName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
