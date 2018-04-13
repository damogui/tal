package com.gongsibao.u8.service;

import com.gongsibao.entity.u8.U8Department;
import com.gongsibao.u8.base.IU8DepartmentService;
import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;

/**
 * Created by zhangchao on 2018/4/12.
 */
@Service
public class U8DepartmentService extends PersistableService<U8Department> implements IU8DepartmentService {

    public U8DepartmentService() {
        super();
        this.type = U8Department.class;
    }

    @Override
    public U8Department getByEmployeeId(Integer employeeId) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("salesman_id = ?");
            oql.getParameters().add("salesmanId", employeeId, Types.INTEGER);
        }

        U8Department u8Department = this.pm.queryFirst(oql);

        return u8Department;
    }

    @Override
    public U8Department save(U8Department entity) {
        if (entity.getEntityState().equals(EntityState.New)) {
            U8Department u8Department = getByEmployeeId(entity.getSalesmanId());
            if (u8Department != null) {
                throw new BusinessException("该业务员已经配置了u8部门编码，且编码为：【" + u8Department.getCode() + "】，禁止保存");
            }
        }
        return super.save(entity);
    }


}
