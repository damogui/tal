package com.gongsibao.entity.crm.home;

import org.netsharp.core.annotations.Table;

/**
 * 部门门户统计
 * @author Administrator
 *
 */
@Table(isView=true, name = "")
public class HomeDepartmentEntity extends BaseHomeSupplierEntity{

	/**
	 * 公海
	 */
	private Integer highSeasCount;

	public Integer getHighSeasCount() {
		return highSeasCount;
	}

	public void setHighSeasCount(Integer highSeasCount) {
		this.highSeasCount = highSeasCount;
	}
}
