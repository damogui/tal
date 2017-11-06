package com.gongsibao.api.conroller.taurus.dto;

import java.util.List;

/**   
 * @ClassName:  TrademarkInfoDTO   
 * @Description:TODO 商标信息
 * @author: 韩伟
 * @date:   2017年10月30日 下午8:13:41   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class TmInfoDTO {

	/**   
	 * @Fields potentialDemandAmount : TODO(潜在金额)   
	 */   
	private Long potentialDemandAmount = 0L;
	
	/**   
	 * @Fields attentionDegree : TODO(重视度)   
	 */   
	private String attentionDegree = "不重视";
	
	/**   
	 * @Fields chanceCount : TODO(机会数量)   
	 */   
	private int chanceCount = 0;
	
	/**   
	 * @Fields haveCount : TODO(已有数量)   
	 */   
	private int haveCount = 0;
	
	/**   
	 * @Fields categoryList : TODO(商标分类)   
	 */   
	private List<SuggestProtectedTmCategoryDTO> suggestList;

	/**   
	 * @Fields protectedList : TODO(著作权)   
	 */   
	private List<HaveProtectedTmCategoryDTO> haveList;

	public Long getPotentialDemandAmount() {
		return potentialDemandAmount;
	}

	public void setPotentialDemandAmount(Long potentialDemandAmount) {
		this.potentialDemandAmount = potentialDemandAmount;
	}

	public String getAttentionDegree() {
		return attentionDegree;
	}

	public void setAttentionDegree(String attentionDegree) {
		this.attentionDegree = attentionDegree;
	}

	public int getChanceCount() {
		return chanceCount;
	}

	public void setChanceCount(int chanceCount) {
		this.chanceCount = chanceCount;
	}

	public int getHaveCount() {
		return haveCount;
	}

	public void setHaveCount(int haveCount) {
		this.haveCount = haveCount;
	}

	public List<SuggestProtectedTmCategoryDTO> getSuggestList() {
		return suggestList;
	}

	public void setSuggestList(List<SuggestProtectedTmCategoryDTO> suggestList) {
		this.suggestList = suggestList;
	}

	public List<HaveProtectedTmCategoryDTO> getHaveList() {
		return haveList;
	}

	public void setHaveList(List<HaveProtectedTmCategoryDTO> haveList) {
		this.haveList = haveList;
	}
}
