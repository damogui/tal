package com.gongsibao.taurus.entity;

import java.util.Date;


/**   
 * @ClassName:  ReportWebInfo   
 * @Description:TODO 商标流程
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:13:40   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class Tmflow implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7707580128471802594L;
	
	/**   
	 * @Fields id : TODO( 主键)   
	 */   
	private int tmId;
	
	/**   
	 * @Fields id : TODO( 主键)   
	 */   
	private int _alibabaRdsRowId_;
	
	/**   
	 * @Fields id : TODO( 主键)   
	 */   
	private Date lcDate;
	
	/**   
	 * @Fields id : TODO( 主键)   
	 */   
	private String category;

	public int getTmId() {
		return tmId;
	}

	public void setTmId(int tmId) {
		this.tmId = tmId;
	}

	public int get_alibabaRdsRowId_() {
		return _alibabaRdsRowId_;
	}

	public void set_alibabaRdsRowId_(int _alibabaRdsRowId_) {
		this._alibabaRdsRowId_ = _alibabaRdsRowId_;
	}

	public Date getLcDate() {
		return lcDate;
	}

	public void setLcDate(Date lcDate) {
		this.lcDate = lcDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	//"tmId":18088745,"_alibabaRdsRowId_":114802558,"lcDate":"2016-01-20 00:00:00","category":"101"
	
}
