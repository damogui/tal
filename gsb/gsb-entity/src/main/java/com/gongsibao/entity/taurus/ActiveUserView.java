package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="jnz_activeUser_view",isView=true)
public class ActiveUserView extends Persistable{
	
	/**
	 * 金牛座用户活跃度
	 */
	private static final long serialVersionUID = 1L;
	
	/*id必须存在否者运行失败*/
	@Id
	@Auto
	private Integer id;
	private Long days;
	private Long count;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
