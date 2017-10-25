package com.gongsibao.ma.service.matching;

import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.AcquisitionDemandPccDetail;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw 区域匹配
 */
public class DistrictMatching implements IMatching {

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		List<AcquisitionDemandPccDetail> pccList = acquisitionDemand.getPcdDetails();

		// 无要求
		if (pccList.size() == 0) {

			return 100;
		}

		// 无地区信息
		if (sellingDemand.getProvinceId() == null && sellingDemand.getCityId() == null && sellingDemand.getCountyId() == null) {

			return 0;
		}

		for (AcquisitionDemandPccDetail pcc : pccList) {

			// 1.只有省份要求,并且省份相等
			if (pcc.getProvinceId() != null && pcc.getCityId() == null && pcc.getCountyId() == null && pcc.getProvinceId().equals(sellingDemand.getProvinceId())) {

				return 100;
			}

			// 2.只有省份，城市要求,并相等
			if (pcc.getProvinceId() != null && pcc.getCityId() != null && pcc.getCountyId() == null && pcc.getProvinceId().equals(sellingDemand.getProvinceId())
					&& pcc.getCityId().equals(sellingDemand.getCityId())) {

				return 100;
			}

			// 3.省份，城市，区/县 要求,并相等
			if (pcc.getProvinceId() != null && pcc.getCityId() != null && pcc.getCountyId() != null && pcc.getProvinceId().equals(sellingDemand.getProvinceId())
					&& pcc.getCityId().equals(sellingDemand.getCityId()) && pcc.getCountyId().equals(sellingDemand.getCountyId())) {

				return 100;
			}

			// 4.省份，城市，区/县 要求,只有省份相同，
			if (pcc.getProvinceId() != null && pcc.getCityId() != null && pcc.getCountyId() != null && pcc.getProvinceId().equals(sellingDemand.getProvinceId())
					&& !pcc.getCityId().equals(sellingDemand.getCityId()) && !pcc.getCountyId().equals(sellingDemand.getCountyId())) {

				return 33;
			}

			// 5.省份，城市，区/县 要求,只有省份，城市相同，
			if (pcc.getProvinceId() != null && pcc.getCityId() != null && pcc.getCountyId() != null && pcc.getProvinceId().equals(sellingDemand.getProvinceId())
					&& pcc.getCityId().equals(sellingDemand.getCityId()) && !pcc.getCountyId().equals(sellingDemand.getCountyId())) {

				return 66;
			}
		}

		return 0;
	}

	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("区域");

		int matchingRate = matching(acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);

		String acquisitionDemandText = "";
		List<AcquisitionDemandPccDetail> pccList = acquisitionDemand.getPcdDetails();
		for (AcquisitionDemandPccDetail pcc : pccList) {

			String text = "";
			if (sellingDemand.getProvinceId() != null) {

				text += pcc.getProvince().getName() + " ";
			}

			if (sellingDemand.getCityId() != null) {

				text += pcc.getCity().getName() + " ";
			}

			if (sellingDemand.getCountyId() != null) {

				text += pcc.getCounty().getName() + " ";
			}

			acquisitionDemandText += text + "、";
		}
		
		if(StringManager.isNullOrEmpty(acquisitionDemandText)){
			
			acquisitionDemandText = "不限";
		}
		item.setAcquisitionDemandText(acquisitionDemandText);

		String sellingDemandText = "";
		if (sellingDemand.getProvinceId() != null) {

			sellingDemandText += sellingDemand.getProvince().getName() + " ";
		}

		if (sellingDemand.getCityId() != null) {

			sellingDemandText += sellingDemand.getCity().getName() + " ";
		}

		if (sellingDemand.getCountyId() != null) {

			sellingDemandText += sellingDemand.getCounty().getName() + " ";
		}
		item.setSellingDemandText(sellingDemandText);

		return item;
	}
}
