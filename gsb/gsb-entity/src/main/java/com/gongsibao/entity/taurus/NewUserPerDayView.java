package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="jnz_newUserPerDay_view",isView=true)
public class NewUserPerDayView extends Persistable{
	
	/**
	 * 金牛座每日新增客户数量
	 */
	private static final long serialVersionUID = 1L;
	
	/*id必须存在否者运行失败*/
	@Id
	@Auto
	private Integer id;
	private String dates;
	private Long userCount;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public Long getUserCount() {
		return userCount;
	}
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}
	
}
