package com.gongsibao.ma.service.matching;

import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.AcquisitionDemandIndustryFeatureDetail;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw 行业特点匹配
 */
public class IndustryFeatureMatching implements IMatching {

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		List<AcquisitionDemandIndustryFeatureDetail> industryFeatureList = acquisitionDemand.getIndustryFeatureDetails();
		if(industryFeatureList.size() == 0){
			
			return 100;
		}
		
		if(sellingDemand.getCompanyFeature() == null){
			
			return 0;
		}
		
		for (AcquisitionDemandIndustryFeatureDetail industryFeature : industryFeatureList) {

			// 只要符合一项就是100分
			if (industryFeature.getIndustryFeature() == sellingDemand.getCompanyFeature()) {

				return 100;
			}
		}
		
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("行业特点");
		
		int matchingRate = matching(acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = "";
		List<AcquisitionDemandIndustryFeatureDetail> industryFeatureList = acquisitionDemand.getIndustryFeatureDetails();
		for (AcquisitionDemandIndustryFeatureDetail industryFeature : industryFeatureList) {

			acquisitionDemandText += industryFeature.getIndustryFeature().getText()+"、";
		}
		
		if(StringManager.isNullOrEmpty(acquisitionDemandText)){
			
			acquisitionDemandText = "不限";
		}
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		if(sellingDemand.getCompanyFeature() != null){
			
			String sellingDemandText = sellingDemand.getCompanyFeature().getText();
			item.setSellingDemandText(sellingDemandText);
		}
		
		return item;
	}
}
