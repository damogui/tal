package com.gongsibao.ma.service.matching;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModel;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw 匹配工厂
 */
public class MatchingHandler {

	public static int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		List<IMatching> handlerList = getHandlerList();

		int totalMatchingRate = 0;// 匹配总百分比
		int matchingItemCount = handlerList.size();// 参与匹配项目数量
		for (IMatching handler : handlerList) {

			totalMatchingRate += handler.matching(acquisitionDemand, sellingDemand);
		}

		int matchingRate = totalMatchingRate / matchingItemCount;//只取整数
		return matchingRate;
	}
	
	public static ComparisonModel getComparisonModelItems(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {

		List<IMatching> handlerList = getHandlerList();
		ComparisonModel model = new ComparisonModel();{
			
			//公司名称
			model.setCompanyName(sellingDemand.getCompanyName());
		}
		
		for (IMatching handler : handlerList) {

			ComparisonModelItem item = handler.getComparisonModelItem(acquisitionDemand, sellingDemand);
			model.getItems().add(item);
		}
		
		return model;
		
	}
	
	private static List<IMatching> getHandlerList(){
		
		List<IMatching> handlerList = new ArrayList<IMatching>();
		handlerList.add(new CompanyTypeMatching());
		handlerList.add(new CompanyNatureMatching());
		handlerList.add(new IndustryFeatureMatching());
		handlerList.add(new DistrictMatching());
		handlerList.add(new RegistDateMatching());
		handlerList.add(new TurnoverGradeMatching());
		handlerList.add(new TaxModeMatching());
		handlerList.add(new BankAccountMatching());
		handlerList.add(new TaxStatusMatching());
		handlerList.add(new TaxRegisterMatching());
		handlerList.add(new ProfitTypeMatching());
		handlerList.add(new IntangibleAssetsMatching());
		handlerList.add(new FixedAssetsMatching());
		handlerList.add(new QualificationMatching());
		return handlerList;
	}
}
