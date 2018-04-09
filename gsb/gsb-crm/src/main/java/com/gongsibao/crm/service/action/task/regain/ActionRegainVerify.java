package com.gongsibao.crm.service.action.task.regain;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw
 *         收回校验
 */
public class ActionRegainVerify implements IAction {


    ICustomerServiceConfigService customerServiceConfigService = ServiceFactory.create(ICustomerServiceConfigService.class);

    IPersister<RoleGroup> pmRole = PersisterFactory.create();

    @Override
    public void execute(ActionContext ctx) {
    	
        Map<String, Object> setMap = ctx.getStatus();
        NCustomerTask taskEntity = (NCustomerTask) ctx.getItem();
        //yxb收回级别：1.业务员：当前商机的ownerId等于当前登录人，退回到业务员当前的部门公海、2.部门负责人或平台（售前）：当前商机的ownerId所在部门的上级部门不为空退回上级部门公海，上级部门为空是平台公海
        if (taskEntity.getOwnerId() != null && taskEntity.getOwnerId().equals(SessionManager.getUserId())) {
            setMap.put("ownerId", SessionManager.getUserId());
            taskEntity.setOwnerId(null);
        } else {
            Integer currentOwner = taskEntity.getOwnerId() != null ? taskEntity.getOwnerId() : SessionManager.getUserId();
            setMap.put("ownerId", currentOwner);
            SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(currentOwner);
            ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
            Integer currentDepartmentSupId = departmentService.getSupDepartmentId(organization.getDepartmentId());
            //当前部门的上级为空，进入平台公海。否则进入上级部门公海
            if (currentDepartmentSupId == null) {
                taskEntity.setSupplierId(null);
                taskEntity.setDepartmentId(null);
            } else {
                taskEntity.setDepartmentId(currentDepartmentSupId);
            }
        }
        
        
        
        /*zhaochao
         * //服务商id
        int supplierId = NumberUtils.toInt(taskEntity.getSupplierId());
        //部门id
        int departmentId = NumberUtils.toInt(taskEntity.getDepartmentId());
        //业务员id
        int ownerId = NumberUtils.toInt(taskEntity.getOwnerId());

        if (supplierId == 0 && departmentId == 0 && ownerId == 0) {
        	
            throw new BusinessException("当前商机已经是大公海商机了，禁止收回！");
        }
        //当前登录人
        int userId = SessionManager.getUserId();
        //当不是售前时，判断是不是服务商管理员或部门负责人
        DataTable roleRow = getRoleMapList(userId);
        if (roleRow == null) {
        	
            throw new BusinessException("当前登录人无权限收回！");
        }
        
        if (roleRow != null) {
        	
            StringBuffer flag = new StringBuffer();
            //当前登录人的部门id
            Integer currentDepartmentId = 0;
            
            //当前登录人的服务商id
            Integer currentSupplierId = 0;
            
            for (IRow role : roleRow) {
            	
                //管理员:Supplier_Admin、部门负责人:Supplier_Leader、业务员:Supplier_Salesman
                String roleCode = role.getString("roleCode");
                //管理员
                if (roleCode.equals("Supplier_Admin")) {
                    flag.append("s");
                }
                //部门负责人
                if (roleCode.equals("Supplier_Leader")) {
                    flag.append("d");
                }
                currentDepartmentId = NumberUtils.toInt(role.get("departmentId"));
                currentSupplierId = NumberUtils.toInt(role.get("supplierId"));
            }
            
            //当该登录人既不是售前也是不服务商管理也不是部门负责人时
            if (StringManager.isNullOrEmpty(flag.toString())) {
            	
                throw new BusinessException("当前登录人无权限，收回商机");
            }
            //当该登录人是：【部门负责人】时
            if (flag.indexOf("d") > -1) {
            	
                if (supplierId != 0 && departmentId != 0 && ownerId == 0) {
                	
                    throw new BusinessException("该商机已经是到该部门公海了，禁止收回");
                }
                //退出等级：1：退回到大公海（即:服务商id,部门id,业务员id都设置为空）、2：退回到服务商（（即:服务商id不为空））、3：退回到部门（（即:服务商id,部门id不为空））
                setMap.put("backLevel", 3);
                taskEntity.setOwnerId(null);
                taskEntity.setDepartmentId(currentDepartmentId);
                taskEntity.setSupplierId(currentSupplierId);
                taskEntity.setAllocationState(AllocationState.ALLOCATED_Department);//已分配-部门
            }
            //当该登录人是：服务商【管理员】时
            if (flag.indexOf("s") > -1) {
            	
                if (supplierId != 0 && departmentId == 0 && ownerId == 0) {
                	
                    throw new BusinessException("该商机已经是到该服务商公海了，禁止收回");
                }
                //退出等级：1：退回到大公海（即:服务商id,部门id,业务员id都设置为空）、2：退回到服务商（（即:服务商id不为空））、3：退回到部门（（即:服务商id,部门id不为空））
                setMap.put("backLevel", 2);
                taskEntity.setOwnerId(null);
                taskEntity.setDepartmentId(null);
                taskEntity.setSupplierId(currentSupplierId);
                taskEntity.setAllocationState(AllocationState.ALLOCATED_Supplier);//已分配-服务商
            }
        } else {
            //当前登录人是否是售前
            ServiceType serviceType = customerServiceConfigService.getTypeByEmployeeId(userId);
            if (serviceType != null && ServiceType.CUSTOMER_SERVICES.equals(serviceType)) {
            	
                //退出等级：1：退回到大公海（即:服务商id,部门id,业务员id都设置为空）、2：退回到服务商（（即:服务商id不为空））、3：退回到部门（（即:服务商id,部门id不为空））
                setMap.put("backLevel", 1);
                taskEntity.setSupplierId(null);
                taskEntity.setDepartmentId(null);
                taskEntity.setOwnerId(null);
                taskEntity.setAllocationState(AllocationState.WAIT);//待分配
            } else {
            	
                //当是售前时，则将
                throw new BusinessException("当前商机已经是大公海商机了，禁止收回！");
            }
        }*/
        
    }

    /*private DataTable getRoleMapList(int userId) {
        // 角色
       
        StringBuffer sqlRole = new StringBuffer();
        sqlRole.append("SELECT pr.name 'roleName',pr.code 'roleCode',em.`name` 'employeeName',sa.`employee_id` 'employeeId', ");
        sqlRole.append("sa.`id` 'salesmanId',sa.`department_id` 'departmentId',sa.`supplier_id` 'supplierId' FROM `sp_salesman_role` rm ");
        sqlRole.append("JOIN sp_salesman sa ON sa.`id` = rm.salesman_id ");
        sqlRole.append("JOIN sys_permission_employee em ON em.id = sa.`employee_id` ");
        sqlRole.append("JOIN sys_permission_role pr ON pr.id = rm.role_id ");
        sqlRole.append("WHERE em.id = ? ");
        QueryParameters qsRole = new QueryParameters();
        qsRole.add("@employee_id", userId, Types.INTEGER);
        DataTable roleList = pmRole.executeTable(sqlRole.toString(), qsRole);
        return roleList;
    }*/
}
