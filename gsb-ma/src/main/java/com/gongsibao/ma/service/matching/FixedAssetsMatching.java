package com.gongsibao.ma.service.matching;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.DemandFixedAssets;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 固定资产匹配
 */
public class FixedAssetsMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		// 需求资质
		List<DemandFixedAssets> requireList = acquisitionDemand.getFixedAssetss();
		if (requireList.size() == 0) {

			return 100;
		}

		// 具备资质
		List<DemandFixedAssets> haveList = sellingDemand.getFixedAssetss();
		if (haveList.size() == 0) {

			return 0;
		}

		// 需求数量
		Integer requireCount = requireList.size();
		Integer haveCount = 0;
		for (DemandFixedAssets fixedAssets : haveList) {

			boolean isHave = isAgreeable(requireList, fixedAssets);
			if (isHave) {
				haveCount++;
			}
		}

		BigDecimal matchingRate = new BigDecimal(haveCount)
										.divide(new BigDecimal(requireCount), 2, RoundingMode.HALF_UP)
										.multiply(new BigDecimal(100));
		
		return matchingRate.toBigInteger().intValue();
	}

	private boolean isAgreeable(List<DemandFixedAssets> requireList, DemandFixedAssets haveFixedAssets) {

		for (DemandFixedAssets fixedAssets : requireList) {

			if (fixedAssets.getFixedAssets() == haveFixedAssets.getFixedAssets()) {

				return true;
			}
		}
		return false;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("固定资产");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		String acquisitionDemandText = "";
		List<DemandFixedAssets> requireList = acquisitionDemand.getFixedAssetss();
		for (DemandFixedAssets fixedAssets : requireList) {
			
			acquisitionDemandText+=fixedAssets.getFixedAssets().getText()+"、";
		}
		if(StringManager.isNullOrEmpty(acquisitionDemandText)){
			
			acquisitionDemandText = "不限";
		}
		item.setAcquisitionDemandText(acquisitionDemandText);
		
		String sellingDemandText = "";
		List<DemandFixedAssets> haveList = sellingDemand.getFixedAssetss();
		for (DemandFixedAssets fixedAssets : haveList) {
			sellingDemandText+=fixedAssets.getFixedAssets().getText()+"、";
		}
		item.setSellingDemandText(sellingDemandText);
		return item;
	}
}
