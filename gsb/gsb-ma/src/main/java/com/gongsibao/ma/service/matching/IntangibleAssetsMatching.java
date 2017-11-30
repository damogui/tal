package com.gongsibao.ma.service.matching;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.DemandIntangibleAssets;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 无形资产 匹配
 */
public class IntangibleAssetsMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		// 需求资质
		List<DemandIntangibleAssets> requireList = acquisitionDemand.getIntangibleAssetss();
		if (requireList.size() == 0) {

			return 100;
		}

		// 具备资质
		List<DemandIntangibleAssets> haveList = sellingDemand.getIntangibleAssetss();
		if (haveList == null || haveList.size() == 0) {

			return 0;
		}

		// 需求数量
		Integer requireCount = requireList.size();
		Integer haveCount = 0;
		for (DemandIntangibleAssets intangibleAssets : haveList) {

			boolean isHave = isAgreeable(requireList, intangibleAssets);
			if (isHave) {
				haveCount++;
			}
		}

		BigDecimal matchingRate = new BigDecimal(haveCount)
										.divide(new BigDecimal(requireCount), 2, RoundingMode.HALF_UP)
										.multiply(new BigDecimal(100));
		
		return matchingRate.toBigInteger().intValue();
	}

	private boolean isAgreeable(List<DemandIntangibleAssets> requireList, DemandIntangibleAssets haveIntangibleAssets) {

		for (DemandIntangibleAssets intangibleAssets : requireList) {

			if (intangibleAssets.getIntangibleAssets() == haveIntangibleAssets.getIntangibleAssets()) {

				return true;
			}
		}
		return false;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("无形资产");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = "";
		List<DemandIntangibleAssets> requireList = acquisitionDemand.getIntangibleAssetss();
		for (DemandIntangibleAssets intangibleAssets : requireList) {
			
			acquisitionDemandText+=intangibleAssets.getIntangibleAssets().getText()+"、";
		}
		if(StringManager.isNullOrEmpty(acquisitionDemandText)){
			
			acquisitionDemandText = "不限";
		}
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		String sellingDemandText = "";
		List<DemandIntangibleAssets> haveList = sellingDemand.getIntangibleAssetss();
		for (DemandIntangibleAssets intangibleAssets : haveList) {
			sellingDemandText+=intangibleAssets.getIntangibleAssets().getText()+"、";
		}
		
		item.setSellingDemandText(sellingDemandText);
		
		 
		return item;
	}
}
