package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="jnz_userConsStatistic_view",isView=true)
public class UserConsStatisticView extends Persistable{

	/**
	 * 金牛座用户消费统计
	 */
	private static final long serialVersionUID = 1L;
	
	/*id必须存在否者运行失败*/
	@Id
	@Auto
	private Integer id;
	private Long buyUserCount;
	private Long buyTimes;
	private String dates;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getBuyUserCount() {
		return buyUserCount;
	}
	public void setBuyUserCount(Long buyUserCount) {
		this.buyUserCount = buyUserCount;
	}
	public Long getBuyTimes() {
		return buyTimes;
	}
	public void setBuyTimes(Long buyTimes) {
		this.buyTimes = buyTimes;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	

}
