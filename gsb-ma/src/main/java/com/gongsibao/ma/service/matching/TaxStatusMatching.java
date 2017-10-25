package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 报税状态（正常/非正常）匹配
 */
public class TaxStatusMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		if(acquisitionDemand.getTaxStatus().equals(sellingDemand.getTaxStatus())){
			
			return 100;
		}
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {


		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("报税状态");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = acquisitionDemand.getTaxStatus()==true?"正常":"非正常";
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		String sellingDemandText = sellingDemand.getTaxStatus()==true?"正常":"非正常";
		item.setSellingDemandText(sellingDemandText);
		return item;
	}
}
