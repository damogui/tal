package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/**
 * 
*  报销补助明细实体  
* 项目名称：gsb-entity   
* 类名称：SubsidyRecord   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午2:51:35   
* @version
 */

@Table(name="cw_subsidy_record",header="报销补助明细")
public class SubsidyRecord extends BizEntity{

	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 2341409435968446219L;

	@Column(name="expense_id",header="报销id")
	private String expenseId;
	
	@Column(name="subsidy_type",header="补贴类型")
	private String subsidyType;
	
	@Column(name="subsidy_day",header="补贴天数 ")
	private Integer subsidyDay;
	
	@Column(name = "subsidy_amount", header = "补贴总金额")
	private Integer subsidyAmount;
	
	@Column(name = "subsidy_standard", header = "补贴标准")
	private Integer subsidyStandard;
	
	
}
