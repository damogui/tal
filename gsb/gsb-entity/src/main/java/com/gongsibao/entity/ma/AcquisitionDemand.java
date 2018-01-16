package com.gongsibao.entity.ma;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.ma.dic.AcquisitionDemandTaxMode;
import com.gongsibao.entity.ma.dic.ProfitType;
import com.gongsibao.entity.ma.dic.TurnoverGrade;
@BizCode(bizType="AD")
@Table(name="ma_acquisition_demand", header="收购需求")
public class AcquisitionDemand extends Demand {
	
	private static final long serialVersionUID = -5364556052309205002L;

	
	//--------------------
	// 意向信息
	//--------------------	
	@Column(name="regist_date_begin",header="成立日期开始")
	private Date registDateBegin;
	
	@Column(name="regist_date_end",header="成立日期结束")
	private Date registDateEnd;
	
	
	@Column(name="turnover_grade",header="流水")
	private TurnoverGrade turnoverGrade = TurnoverGrade.UNLIMITED;

	@Column(name="profit_type",header="企业盈利")
	private ProfitType profitType = ProfitType.UNLIMITED;
	
	@Column(name="tax_mode",header="纳税人")
	private AcquisitionDemandTaxMode taxMode = AcquisitionDemandTaxMode.UNLIMITED;
	
	@Subs(foreignKey="acquisitionDemandId",header="意向区域",subType=AcquisitionDemandPccDetail.class)
	private List<AcquisitionDemandPccDetail> pcdDetails;
	
	@Subs(foreignKey="acquisitionDemandId",header="意向行业特点",subType=AcquisitionDemandIndustryFeatureDetail.class)
	private List<AcquisitionDemandIndustryFeatureDetail> industryFeatureDetails;
	
	@Subs(foreignKey="acquisitionDemandId",header="匹配明细",subType=AcquisitionDemandMatchingDetail.class)
	private List<AcquisitionDemandMatchingDetail> matchingDetails;
	

	public Date getRegistDateBegin() {
		return registDateBegin;
	}

	public void setRegistDateBegin(Date registDateBegin) {
		this.registDateBegin = registDateBegin;
	}

	public Date getRegistDateEnd() {
		return registDateEnd;
	}

	public void setRegistDateEnd(Date registDateEnd) {
		this.registDateEnd = registDateEnd;
	}

	public TurnoverGrade getTurnoverGrade() {
		return turnoverGrade;
	}

	public void setTurnoverGrade(TurnoverGrade turnoverGrade) {
		this.turnoverGrade = turnoverGrade;
	}

	public ProfitType getProfitType() {
		return profitType;
	}

	public void setProfitType(ProfitType profitType) {
		this.profitType = profitType;
	}

	public List<AcquisitionDemandPccDetail> getPcdDetails() {
		return pcdDetails;
	}

	public void setPcdDetails(List<AcquisitionDemandPccDetail> pcdDetails) {
		this.pcdDetails = pcdDetails;
	}

	public List<AcquisitionDemandIndustryFeatureDetail> getIndustryFeatureDetails() {
		return industryFeatureDetails;
	}

	public void setIndustryFeatureDetails(
			List<AcquisitionDemandIndustryFeatureDetail> industryFeatureDetails) {
		this.industryFeatureDetails = industryFeatureDetails;
	}

	public List<AcquisitionDemandMatchingDetail> getMatchingDetails() {
		return matchingDetails;
	}

	public void setMatchingDetails(
			List<AcquisitionDemandMatchingDetail> matchingDetails) {
		this.matchingDetails = matchingDetails;
	}

	public AcquisitionDemandTaxMode getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(AcquisitionDemandTaxMode taxMode) {
		this.taxMode = taxMode;
	}
}
