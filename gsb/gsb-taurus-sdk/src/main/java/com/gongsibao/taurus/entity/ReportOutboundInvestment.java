package com.gongsibao.taurus.entity;

/**   
 * @ClassName:  ReportOutboundInvestment   
 * @Description:TODO 对外投资 
 * @author: 韩伟
 * @date:   2017年10月20日 下午3:26:40   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class ReportOutboundInvestment implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4006056844295484930L;

	
	/**   
	 * @Fields outcompanyName : 对外投资企业名称
	 */   
	private String outcompanyName; 
	
	/**   
	 * @Fields creditCode : 统一信用代码
	 */   
	private String creditCode; 
	
	/**   
	 * @Fields regNum : 注册号
	 */   
	private String regNum;

	public String getOutcompanyName() {
		return outcompanyName;
	}

	public void setOutcompanyName(String outcompanyName) {
		this.outcompanyName = outcompanyName;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	} 
}
