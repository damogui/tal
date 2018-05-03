package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/**
 * 
*  抄送记录表  
* 项目名称：gsb-entity   
* 类名称：CarbonCopy   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月21日 下午5:31:46   
* @version
 */
@Table(name="cw_carbon_copy",header="抄送记录表  ")
public class CarbonCopy extends BizEntity{
	
	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 1396819395194478910L;

	@Column(name="form_id",header="单据id")
	private Integer formId ;
	
	@Column(name="form_type",header="单据类型")
	private Integer formType;
	
	@Column(name="copy_user_id",header="抄送人id")
	private double copyUserId;

	@Column(name = "is_read", header = "是否已读")
	private String isRead;
	
	@Column(name = "status", header = "状态 1:待审核 ，2：审核中 ，3：已通过")
	private Integer status;
	
}
