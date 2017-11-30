package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  EntChangeRecord   
 * @Description:TODO 企业变更记录
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:09:20   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class EntChangeRecord implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3459285877291273272L;

	/**   
	 * @Fields name : TODO(变更后)   
	 */   
	private String changeAfter;
	
	/**   
	 * @Fields name : TODO(变更前)   
	 */   
	private String changeBefore;
	
	/**   
	 * @Fields name : TODO(变更日期)   
	 */   
	private String changeData;
	
	/**   
	 * @Fields name : TODO(变更事项)   
	 */   
	private String changeEvent;

	public String getChangeAfter() {
		return changeAfter;
	}

	public void setChangeAfter(String changeAfter) {
		this.changeAfter = changeAfter;
	}

	public String getChangeBefore() {
		return changeBefore;
	}

	public void setChangeBefore(String changeBefore) {
		this.changeBefore = changeBefore;
	}

	public String getChangeData() {
		return changeData;
	}

	public void setChangeData(String changeData) {
		this.changeData = changeData;
	}

	public String getChangeEvent() {
		return changeEvent;
	}

	public void setChangeEvent(String changeEvent) {
		this.changeEvent = changeEvent;
	}
}
