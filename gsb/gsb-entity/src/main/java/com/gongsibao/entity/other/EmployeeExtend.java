package com.gongsibao.entity.other;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.other.dic.AbilityType;
import com.gongsibao.entity.other.dic.LoginValid;
import com.gongsibao.entity.other.dic.PriorityType;
import com.gongsibao.entity.other.dic.SupplyStatus;
import com.gongsibao.entity.other.dic.SupplyType;
import com.gongsibao.entity.uc.Organization;

@Table(name="sys_permission_employee",header="员工信息")
public class EmployeeExtend extends Employee{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6945415109443942499L;

    @Column(name="ability_id",header="服务能力： 107 1071金牌，1072银牌，1073铜牌，1074普通")
    private AbilityType abilityType;
    
    @Column(name="priority_id",header="分配优先级 109 1091金牌，1092银牌，1073铜牌，1094普通")
    private PriorityType priorityType;
    
    @Column(name="office_id",header="分公司id")
    private Integer officeId;
    
    @Reference(foreignKey="officeId")
    private Organization office;
    
    @Column(name="head_thumb_file_id",header="头像图片序号")
    private Integer headThumbFileId;
    
    @Column(name="user_type_id",header="用户类型序号，type=2")
    private Integer userTypeId = 10;
    
    @Column(name="is_bbk",header="是否八百客")
    private String isBbk="0";

    @Column(name="is_accept_order",header="是否接单 0不接单 1接单")
    private Boolean isAcceptOrder = false;
    
    @Column(name="supply_status",header="供应商审核状态 1、初次申请 2 等待审核 3审核驳回 4 审核通过 5审核通过后修改-等待审核, 6信息修改审核驳回")
    private SupplyStatus supplyStatus;
    
    @Column(name="supply_reject_reason",header="供应商审核驳回原因")
    private String supplyRejectReason;
    
    @Column(name="supply_type",header="供应商类型 0 无, 1 cp, 2 sp")
    private SupplyType supplyType;
    
    @Column(name="login_valid",header="登录验证 1ukey, 2短信, 3都验证")
    private LoginValid loginValid;
    
    @Column(name="ukey_pid",size=20,header="ukey的pid")
    private String ukeyPid;
    
    @Column(name="pub_key",size=100,header="公钥")
    private String pubKey;
    
    @Subs(subType=EmployeeExtendCity.class,foreignKey="employeeId",header="影响城市")
    private List<EmployeeExtendCity> citys;
    
    @Subs(subType=EmployeeExtendBusiness.class,foreignKey="employeeId",header="归属事业部")
    private List<EmployeeExtendBusiness> business;
    
    
}

//uc_user_organization_map

///uc_user_role_map

//createResourceNodeVoucher(EmployeeCity.class.getName(), "影响城市", EmployeeCity.class.getSimpleName(), IEmployeeCityService.class.getName(), node1.getId());
