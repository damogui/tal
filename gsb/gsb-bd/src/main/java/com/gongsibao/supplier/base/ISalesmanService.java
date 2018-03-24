package com.gongsibao.supplier.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.supplier.Salesman;

public interface ISalesmanService extends IPersistableService<Salesman> {

    /**
     * @throws
     * @Title: getSupplierId
     * @Description: TODO(根据当前登录人获取服务商Id)
     * @param: @param employeeId
     * @param: @return
     * @return: Integer
     */
    Integer getSupplierId(Integer employeeId);

    /**
     * @throws
     * @Title: getDepartmentId
     * @Description: TODO(根据当前登录人获取服务商下对应部门Id)
     * @param: @param employeeId
     * @param: @return
     * @return: Integer
     */
    Integer getDepartmentId(Integer employeeId);

    /**
     * @throws
     * @Title: getDepartmentId
     * @Description: TODO(根据当前登录人获取对应部门Id和子部门Id集合)
     * @param: @param employeeId
     * @param: @return
     * @return: Integer
     */
    List<Integer> getDepartmentIdList(Integer employeeId);

    /**
     * @throws
     * @Title: byEmployeeId
     * @Description: TODO(根据employeeId获取)
     * @param: @param employeeId
     * @param: @return
     * @return: Salesman
     */
    Salesman byEmployeeId(Integer employeeId);

    /**
     * @throws
     * @Title: getEmployeeId
     * @Description: TODO(根据salesmanId获取employeeId)
     * @param: @param salesmanId
     * @param: @return
     * @return: Integer
     */
    Integer getEmployeeId(Integer salesmanId);

    /**
     * @throws
     * @Title: setDisabled
     * @Description: TODO(设置停用/启用状态)
     * @param: @param salesmanId
     * @param: @param state
     * @param: @return
     * @return: boolean
     */
    @Transaction
    boolean setDisabled(Integer salesmanId, boolean state);

    /**
     * @throws
     * @Title: setReceiving
     * @Description: TODO(设置接单状态)
     * @param: @param salesmanId
     * @param: @param state
     * @param: @return
     * @return: boolean
     */
    @Transaction
    boolean setReceiving(Integer salesmanId, boolean state);

    /**
     * 根据服务商id获取员工集合
     *
     * @param supplierId
     * @return
     */
    List<Salesman> getBySupplierId(Integer supplierId);

    /**
     * 根据部门Id获取员工集合
     *
     * @param supplierId
     * @return
     */
    List<Salesman> getByDepartmentId(Integer departmentId);

    /**
     * @throws
     * @Title: hasEmployeeId
     * @Description: TODO(是否存在employeeId)
     * @param: @param employeeId
     * @param: @return
     * @return: Boolean
     */
    Boolean hasEmployeeId(Integer employeeId);

    /**
     * @throws
     * @Title: getDirectLeader
     * @Description: TODO(获取直属领导)
     * @param: @param salesmanId
     * @param: @return
     * @return: Salesman
     */
    Salesman getDirectLeader(Integer salesmanId);

    /**
     * @throws
     * @Title: getDirectLeader
     * @Description: TODO(获取隔级领导)
     * @param: @param salesmanId
     * @param: @return
     * @return: Salesman
     */
    Salesman getSuperiorLeader(Integer salesmanId);

    /**
     * 获取服务商管理员或者部门领导
     *
     * @param supplierId   服务商id
     * @param departmentId 部门id
     * @return 领导id
     */
    Integer getLeaderId(Integer supplierId, Integer departmentId);

    //根据角色Code获取该角色下所有的employeeId集合
    List<Integer> getEmployeeIdListByRoleCodes(List<String> roleCodes);

}
