package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw 银行账户匹配
 */
public class BankAccountMatching implements IMatching {

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		if (acquisitionDemand.getHasBankAccount().equals(sellingDemand.getHasBankAccount())) {

			return 100;
		}
		return 0;
	}

	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("银行帐号");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = acquisitionDemand.getHasBankAccount()==true?"有":"无";
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		String sellingDemandText = sellingDemand.getHasBankAccount()==true?"有":"无";
		item.setSellingDemandText(sellingDemandText);
		return item;
	}
}
