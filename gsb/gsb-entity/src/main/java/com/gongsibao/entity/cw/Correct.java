package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.BizEntity;
/**
 * 
* 单据冲正表（报销冲正借款，还款冲正借款）   
* 项目名称：gsb-entity   
* 类名称：Correct   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月30日 下午3:14:14   
* @version
 */
public class Correct extends BizEntity{

	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = -7015513482674958861L;

	@Column(name="loan_id",header="借款id")
	private Integer loanId ;
	
	@Column(name="form_id",header="报销id 或者 还款id")
	private Integer formId ;
	
	@Column(name="form_type",header="1：报销，2：还款")
	private Integer formType ;
	
}
