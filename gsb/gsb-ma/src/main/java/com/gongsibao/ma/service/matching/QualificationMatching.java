package com.gongsibao.ma.service.matching;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.DemandQualificationDetail;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw 企业资质匹配
 */
public class QualificationMatching implements IMatching {
 
	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		// 需求资质
		List<DemandQualificationDetail> requireList = acquisitionDemand.getQualificationDetails();
		if (requireList.size() == 0) {

			return 100;
		}

		// 具备资质
		List<DemandQualificationDetail> haveList = sellingDemand.getQualificationDetails();
		if (haveList.size() == 0) {

			return 0;
		}

		// 需求数量
		Integer requireCount = requireList.size();
		Integer haveCount = 0;
		for (DemandQualificationDetail qualification : haveList) {

			boolean isHave = isAgreeable(requireList, qualification);
			if (isHave) {
				haveCount++;
			}
		}

		BigDecimal matchingRate = new BigDecimal(haveCount)
										.divide(new BigDecimal(requireCount), 2, RoundingMode.HALF_UP)
										.multiply(new BigDecimal(100));
		
		return matchingRate.toBigInteger().intValue();
	}

	private boolean isAgreeable(List<DemandQualificationDetail> requireList, DemandQualificationDetail haveQualification) {

		for (DemandQualificationDetail qualification : requireList) {

			if (qualification.getEnterpriseQualification() == haveQualification.getEnterpriseQualification()) {

				return true;
			}
		}
		return false;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("企业资质");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = "";
		List<DemandQualificationDetail> requireList = acquisitionDemand.getQualificationDetails();
		for (DemandQualificationDetail qualification : requireList) {
			
			acquisitionDemandText+=qualification.getEnterpriseQualification().getText()+"、";
		}

		if(StringManager.isNullOrEmpty(acquisitionDemandText)){
			
			acquisitionDemandText = "不限";
		}
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		
		
		
		String sellingDemandText = "";
		List<DemandQualificationDetail> haveList = sellingDemand.getQualificationDetails();
		for (DemandQualificationDetail qualification : haveList) {
			sellingDemandText+=qualification.getEnterpriseQualification().getText()+"、";
		}
		
		item.setSellingDemandText(sellingDemandText);
		
		return item;
	}

}
