package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.dic.ProfitType;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw 企业盈利匹配
 */
public class ProfitTypeMatching implements IMatching {

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		if ((acquisitionDemand.getProfitType() == ProfitType.UNLIMITED) 
				|| (acquisitionDemand.getProfitType() == ProfitType.YES && sellingDemand.getProfitable())
				|| (acquisitionDemand.getProfitType() == ProfitType.NO && !sellingDemand.getProfitable())) {

			return 100;
		}

		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("企业盈利");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		item.setAcquisitionDemandText( "不限");
		if(acquisitionDemand.getProfitType() != null){

			String acquisitionDemandText = acquisitionDemand.getProfitType().getText();
			item.setAcquisitionDemandText(acquisitionDemandText);
		}
		
		String sellingDemandText = sellingDemand.getProfitable()==true?"是":"否";
		item.setSellingDemandText(sellingDemandText);
		return item;
	}

}
