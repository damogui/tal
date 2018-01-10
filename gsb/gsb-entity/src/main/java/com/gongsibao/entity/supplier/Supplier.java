package com.gongsibao.entity.supplier;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.crm.dic.NotifiedType;

@Table(name="sp_supplier",header="服务商")
public class Supplier extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9055417222309634407L;

	@Column(name = "service_name", header = "服务商名称")
	private String serviceName;

	@Column(name = "address", header = "地址")
	private String address;

	@Column(name = "mobile_phone", header = "手机号（账号）")
	private String mobilePhone;

	@Column(name = "is_proprietary", header = "是否自营  0否, 1是")
	private Boolean isProprietary = true;

	@Column(name = "customer_pool_number", header = "客户池数量")
	private Integer customerPoolNumber;

	@Column(name = "is_push_report", header = "是否推送报表  0否, 1是")
	private Boolean isPushReport = true;
	
	@Column(name = "message_notified_type", header = "消息通知类型 ")
	private NotifiedType messageNotifiedType=NotifiedType.Wx;
	
	@Column(name = "is_auto_assign", header = "是否推自动分配  0否, 1是")
	private Boolean isAutoAssign = true;
	
	@Column(name = "is_auto_release", header = "是否推自动释放  0否, 1是")
	private Boolean isAutoRelease = true;
	
	@Column(name = "no_follow_days", header = "未跟进天数释放")
	private Integer noFollowDays;
	
	@Column(name = "is_enable_depart", header = "是否启用部门  0否, 1是")
	private Boolean isEnableDepart = true;

	@Column(name = "depart_level", header = "部门级次")
	private Integer departLevel;

	
	@Subs(foreignKey="supplierId",header="服务范围",subType=SupplierServiceScope.class)
	private List<SupplierServiceScope> serviceScopes;
}
