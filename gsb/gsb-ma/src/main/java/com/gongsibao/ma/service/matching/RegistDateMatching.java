package com.gongsibao.ma.service.matching;


import java.util.Date;

import org.netsharp.util.DateManage;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 成立日期匹配
 */
public class RegistDateMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		Date beginDate = acquisitionDemand.getRegistDateBegin();
		Date endDate = acquisitionDemand.getRegistDateEnd();
		Date registDate = sellingDemand.getRegistDate();
		//没有成立时间要求
		if( beginDate == null && endDate == null){
			
			return 100;
		}
		
		if(registDate == null){
			
			return 0;
		}
		
		//成立开始时间不为空，成立结束时间为空
		if(beginDate != null && endDate == null){
			
			if(beginDate.getTime() > registDate.getTime()){
				
				return 100;
			}
		}
		
		if(beginDate == null && endDate != null){
			
			if(registDate.getTime() > endDate.getTime()){
				
				return 100;
			}
		}
		
		if(beginDate != null && endDate != null){
			
			if(registDate.getTime() > endDate.getTime()
				&& registDate.getTime() < beginDate.getTime()){
				
				return 100;
			}
		}
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("成立年限");
		
		int matchingRate = matching( acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		
		Date beginDate = acquisitionDemand.getRegistDateBegin();
		Date endDate = acquisitionDemand.getRegistDateEnd();
		Date registDate = sellingDemand.getRegistDate();
		

		String acquisitionDemandText = "";
		if(beginDate == null && endDate == null){
			
			acquisitionDemandText = "不限";
		}else{

			if(beginDate != null){

				acquisitionDemandText += DateManage.toString(beginDate,"yyyy年MM月dd");
			}
			
			acquisitionDemandText+=" 至 ";
			if(endDate != null){

				acquisitionDemandText += DateManage.toString(endDate,"yyyy年MM月dd");
			}
		}

		item.setAcquisitionDemandText(acquisitionDemandText);
		
		
		if(registDate != null){

			String sellingDemandText = DateManage.toString(registDate,"yyyy年MM月dd");
			item.setSellingDemandText(sellingDemandText);
		}
		return item;
	}
}
