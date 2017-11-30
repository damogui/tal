package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.dic.AcquisitionDemandTaxMode;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 纳税人匹配
 */
public class TaxModeMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		if(acquisitionDemand.getTaxMode() == AcquisitionDemandTaxMode.UNLIMITED
				|| acquisitionDemand.getTaxMode().getValue() ==  sellingDemand.getTaxMode().getValue()){
			
			return 100;
		}
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("纳税人");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		item.setAcquisitionDemandText( "不限");
		if(acquisitionDemand.getTaxMode() != null){

			String acquisitionDemandText = acquisitionDemand.getTaxMode().getText();
			item.setAcquisitionDemandText(acquisitionDemandText);
		}
		
		if(sellingDemand.getTaxMode() != null){

			String sellingDemandText = sellingDemand.getTaxMode().getText();
			item.setSellingDemandText(sellingDemandText);
		}
		return item;
	}
}
