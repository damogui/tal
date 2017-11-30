package com.gongsibao.taurus.entity;

import java.util.List;


/**   
 * @ClassName:  Entshareholder   
 * @Description:TODO 股东信息
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:07:36   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class EntShareholder implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4944752976958047394L;

	/**   
	 * @Fields name : TODO( 实缴出资（金额/时间）)   
	 */   
	private List<EntShareholderPaidIn> paidIn;
	
	/**   
	 * @Fields name : TODO(股东名称)   
	 */   
	private String shareholdersName;
	
	/**   
	 * @Fields name : TODO(认缴出资（金额/时间）)   
	 */   
	private List<EntShareholderSubcribe> subcribe;

	public List<EntShareholderPaidIn> getPaidIn() {
		return paidIn;
	}

	public void setPaidIn(List<EntShareholderPaidIn> paidIn) {
		this.paidIn = paidIn;
	}

	public String getShareholdersName() {
		return shareholdersName;
	}

	public void setShareholdersName(String shareholdersName) {
		this.shareholdersName = shareholdersName;
	}

	public List<EntShareholderSubcribe> getSubcribe() {
		return subcribe;
	}

	public void setSubcribe(List<EntShareholderSubcribe> subcribe) {
		this.subcribe = subcribe;
	}
}
