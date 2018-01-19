package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.service.EmployeeService;
import org.netsharp.util.StringManager;

@Service
public class SalesmanService extends SupplierPersistableService<Salesman> implements ISalesmanService {

    public SalesmanService() {
        super();
        this.type = Salesman.class;
    }

    @Override
    public Integer getSupplierId(Integer employeeId) {

        Salesman entity = byEmployeeId(employeeId);
        if (entity != null) {

            return entity.getSupplierId();
        }
        return null;
    }

    //处理增删改的逻辑
    @Override
    public Salesman save(Salesman entity) {

        EmployeeService employeeService = new EmployeeService();//用户表实体
        Employee employee = new Employee();
        employee.setName(entity.getName());
        employee.setMobile(entity.getMobile());
        employee.setBankNo(entity.getBankNo());
        employee.setEntryDate(entity.getEntryDate());
        employee.setQuitDate(entity.getQuitDate());
        Employee saveEmploy;
        EntityState state = entity.getEntityState();

        if (state == EntityState.Deleted) {

            //删除删两个
            return super.save(entity);//继承父类的保存

        } else if (state.equals(EntityState.New)) {//新增
            employee.toNew();
            //更新登录用户表
            saveEmploy = employeeService.save(employee);//会处理异常直接弹窗
            entity.setEmployeeId(saveEmploy.getId());
            if (saveEmploy.getId() > 0) {
                return super.save(entity);//继承父类的保存
            } else {
                return entity;
            }


        } else {//修改

            employee.toPersist();//修改
            employee.setId(entity.getEmployeeId());//传递用户id
            //更新登录用户表
            saveEmploy = employeeService.save(employee);//会处理异常直接弹窗
            entity.setEmployeeId(saveEmploy.getId());
            return super.save(entity);//继承父类的保存


        }

    }


    //重写进行复制
    @Override
    public List<Salesman> queryList(Oql oql) {
        List<Salesman> list = super.queryList(oql);

        for (Salesman salesman : list
                ) {
            Employee employee = salesman.getEmployee();
            if (employee != null) {
                salesman.setName(employee.getName());
                salesman.setMobile(employee.getMobile());
                salesman.setBankNo(employee.getBankNo());
                salesman.setEntryDate(employee.getEntryDate());
                salesman.setQuitDate(employee.getQuitDate());
                //停用的话两张表必须保持一致
            }
        }


        return list;
    }

    //进行重写赋值
    @Override
    public Salesman byId(Object id) {
        Salesman salesman = super.byId(id);
        Employee employee = salesman.getEmployee();
        if (employee != null) {
            salesman.setName(employee.getName());
            salesman.setMobile(employee.getMobile());
            salesman.setBankNo(employee.getBankNo());
            salesman.setEntryDate(employee.getEntryDate());
            salesman.setQuitDate(employee.getQuitDate());
        }

        return salesman;
    }

    @Override
    public Integer getDepartmentId(Integer employeeId) {

        Salesman entity = byEmployeeId(employeeId);
        if (entity != null) {

            return entity.getDepartmentId();
        }
        return null;
    }

    @Override
    public Salesman byEmployeeId(Integer employeeId) {

        Oql oql = new Oql();
        {
            oql.setType(type);
            oql.setSelects("*");
            oql.setFilter("employeeId=?");
            oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
        }
        Salesman entity = this.queryFirst(oql);
        return entity;
    }
}