package com.gongsibao.ma.service.matching;

import java.util.List;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.SellingDemandTurnoverDetail;
import com.gongsibao.entity.ma.dic.TurnoverGrade;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 流水匹配
 */
public class TurnoverGradeMatching implements IMatching{

	@Override
	public int matching(AcquisitionDemand acquisitionDemand, SellingDemand sellingDemand) {
		
		//不限流水
		if(acquisitionDemand.getTurnoverGrade() == TurnoverGrade.UNLIMITED){
			
			return 100;
		}else {
			
			List<SellingDemandTurnoverDetail> turnoverDetails = sellingDemand.getTurnoverDetails();
			for(SellingDemandTurnoverDetail turnoverDetail :turnoverDetails){
				
				//只要有一项符合就是100分
				if(acquisitionDemand.getTurnoverGrade() == turnoverDetail.getTurnoverGrade()){
					
					return 100;
				}
			}
		}
		 
		return 0;
	}


	@Override
	public ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand) {

		ComparisonModelItem item = new ComparisonModelItem();
		item.setTitle("流水");
		
		int matchingRate = matching(acquisitionDemand, sellingDemand);
		item.setMatchingRate(matchingRate);
		item.setAcquisitionDemandText( "不限");
		//要求
		if(acquisitionDemand.getTurnoverGrade() != null){
			
			String acquisitionDemandText = acquisitionDemand.getTurnoverGrade().getText();
			item.setAcquisitionDemandText(acquisitionDemandText);
		}
		
		String sellingDemandText = "";
		List<SellingDemandTurnoverDetail> turnoverDetails = sellingDemand.getTurnoverDetails();
		for(SellingDemandTurnoverDetail turnoverDetail :turnoverDetails){
			

			sellingDemandText += turnoverDetail.getTurnoverGrade().getText()+"、";
		}
		item.setSellingDemandText(sellingDemandText);
		
		return item;
	}
}
