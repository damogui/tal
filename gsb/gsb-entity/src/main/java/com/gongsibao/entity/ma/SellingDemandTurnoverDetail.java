package com.gongsibao.entity.ma;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.TurnoverGrade;

@Table(name="ma_selling_demand_turnover_detail",header="出售需求流水明细")
public class SellingDemandTurnoverDetail extends Entity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1921651580206443558L;

	@JsonIgnore
    @Reference(foreignKey="sellingDemandId")
    private SellingDemand sellingDemand;
    
	@Column(name="selling_demand_id",header="销售需求")
	private Integer sellingDemandId;
	
	@Column(name="begin_date",header="开始时间")
	private Date beginDate;
	
	@Column(name="end_date",header="结束时间")
	private Date endDate;
	
	@Column(name="turnover_grade",header="流水区间")
	private TurnoverGrade turnoverGrade;
	
	public SellingDemand getSellingDemand() {
		return sellingDemand;
	}

	public void setSellingDemand(SellingDemand sellingDemand) {
		this.sellingDemand = sellingDemand;
	}

	public Integer getSellingDemandId() {
		return sellingDemandId;
	}

	public void setSellingDemandId(Integer sellingDemandId) {
		this.sellingDemandId = sellingDemandId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public TurnoverGrade getTurnoverGrade() {
		return turnoverGrade;
	}

	public void setTurnoverGrade(TurnoverGrade turnoverGrade) {
		this.turnoverGrade = turnoverGrade;
	}
	
	
}
