package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 公司类型匹配
 */
public class CompanyTypeMatching implements IMatching {

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		if(acquisitionDemand.getCompanyType() == sellingDemand.getCompanyType()){
			
			return 100;
		}
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("公司类型");
		
		int matchingRate = matching(acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		if(acquisitionDemand.getCompanyType() != null){
			
			String acquisitionDemandText = acquisitionDemand.getCompanyType().getText();
			item.setAcquisitionDemandText(acquisitionDemandText);
		}
		
		if(sellingDemand.getCompanyType() != null){
			
			String sellingDemandText = sellingDemand.getCompanyType().getText();
			item.setSellingDemandText(sellingDemandText);
		}
		
		return item;
	}

}
