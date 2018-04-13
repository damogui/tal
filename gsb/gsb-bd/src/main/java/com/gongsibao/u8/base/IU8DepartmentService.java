package com.gongsibao.u8.base;

import com.gongsibao.entity.u8.U8Department;
import org.netsharp.base.IPersistableService;

/**
 * Created by zhangchao on 2018/4/12.
 */
public interface IU8DepartmentService extends IPersistableService<U8Department> {

    U8Department getByEmployeeId(Integer employeeId);
}
