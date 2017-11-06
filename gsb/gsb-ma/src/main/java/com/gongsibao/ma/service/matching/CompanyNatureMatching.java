package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 公司性质匹配
 */
public class CompanyNatureMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		if(acquisitionDemand.getCompanyNature() == sellingDemand.getCompanyNature()){
			
			return 100;
		}
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("公司性质");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		if(acquisitionDemand.getCompanyNature() != null){
			
			String acquisitionDemandText = acquisitionDemand.getCompanyNature().getText();
			item.setAcquisitionDemandText(acquisitionDemandText);
		}
		
		if(sellingDemand.getCompanyNature() != null){
			
			String sellingDemandText = sellingDemand.getCompanyNature().getText();
			item.setSellingDemandText(sellingDemandText);
		}
		
		return item;
	}
}
