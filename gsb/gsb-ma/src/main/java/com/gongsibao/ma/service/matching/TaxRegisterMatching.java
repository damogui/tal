package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 国地税报到 匹配
 */
public class TaxRegisterMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		if(acquisitionDemand.getTaxRegister().equals(sellingDemand.getTaxRegister())){
			
			return 100;
		}
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {


		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("国地税报到");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = acquisitionDemand.getTaxRegister()==true?"是":"否";
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		String sellingDemandText = sellingDemand.getTaxRegister()==true?"是":"否";
		item.setSellingDemandText(sellingDemandText);
		return item;
	}
}
