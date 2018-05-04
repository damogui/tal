package com.gongsibao.entity.cw;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/**
 * 
*  报销行程记录实体  
* 项目名称：gsb-entity   
* 类名称：StrokeRecord   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午2:51:35   
* @version
 */

@Table(name="cw_trip_record",header="报销行程记录")
public class TripRecord extends BizEntity{

	private static final long serialVersionUID = 2341409435968446219L;

	@Column(name="expense_id",header="报销单id")
	private Integer expenseId;
	
	@Column(name="origin",header="出发地")
	private String origin;
	
	@Column(name="destination",header="目的地 ")
	private String destination;
	
	@Column(name = "start_time", header = "开始时间")
	private Date startTime;
	
	@Column(name = "end_time", header = "结束时间")
	private Date endTime;

	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
