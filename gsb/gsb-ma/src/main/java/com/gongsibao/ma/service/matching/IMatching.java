package com.gongsibao.ma.service.matching;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.dto.ComparisonModelItem;

/**
 * @author hw
 * 匹配度计算接口
 */
public interface IMatching {

	/**
	 * 匹配
	 * @param acquisitionDemand 收购需求
	 * @param sellingDemand 出售需求
	 * @return 匹配度
	 */
	int matching(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand);
	
	/**   
	 * @Title: getMatchingPropertyZhName   
	 * @Description: TODO(获取匹配结果项)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	ComparisonModelItem getComparisonModelItem(AcquisitionDemand acquisitionDemand,SellingDemand sellingDemand);
}
