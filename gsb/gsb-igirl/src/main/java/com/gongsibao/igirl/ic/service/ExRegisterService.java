package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SupplierSessionManager;
import org.junit.Test;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExRegisterService extends GsbPersistableService<IcExRegisterCase> implements IcExRegisterService{
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    public ExRegisterService() {
        super();
        this.type = IcExRegisterCase.class;
    }

    @Override
    public IcExRegisterCase save(IcExRegisterCase entity) {
        INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
        INCustomerTaskService customerTaskService = ServiceFactory.create(INCustomerTaskService.class);
        String mobile = entity.getCustomerMobile();
        NCustomer customer = customerService.getByMobile(mobile);
        if (customer==null){
            customer = new NCustomer();
            customer.setRealName(entity.getCustomerName());
            customer.setSex(Sex.SECRECY);
            customer.setMobile(entity.getCustomerMobile());
            customer.setIsMember(false);
            customer.setTelephone("");
            customer.setEmail("");
            customer.setQq("");
            customer.setWeixin("");
            customer.setImportant(Important.COMMON);
            customer.setRemark("");
            customer.setAllocationType(AllocationType.NATURAL);
            customer.setSupplierId(SupplierSessionManager.getSupplierId());
            customer.setSupplier(SupplierSessionManager.getSupplier());
            customer.setDepartmentId(SupplierSessionManager.getDepartmentId());
            customer.setDepartment(SupplierSessionManager.getDepartment());
            customer.setProducts(new ArrayList<>());
            customer.setCompanys(new ArrayList<>());
            customer.setFollows(new ArrayList<>());
            customer.setNotifys(new ArrayList<>());
            customer.setChanges(new ArrayList<>());
            customer.setCreator(SessionManager.getUserName());
            customer.setCreatorId(SessionManager.getUserId());
            customer.setCreateTime(new Date());
            customer.setEntityState(EntityState.New);
            customer = customerService.save(customer);
            List<NCustomerTask> tasks = new ArrayList();
            NCustomerTask task = new NCustomerTask();
            task.setDepartmentId(SupplierSessionManager.getDepartmentId());
            task.setDepartment(SupplierSessionManager.getDepartment());
            task.setSupplierId(SupplierSessionManager.getSupplierId());
            task.setSupplier(SupplierSessionManager.getSupplier());
            task.setFoolowStatus(CustomerFollowStatus.UNSTART);
            task.setLastAllocationUserId(SessionManager.getUserId());
            task.setLastAllocationTime(new Date());
            task.setIntentionCategory(QualityCategory.X);
            task.setCreatorId(SessionManager.getUserId());
            task.setCreator(SessionManager.getUserName());
            task.setCreateTime(new Date());
            task.setTaskType(TaskCustomerType.NEW);
            task.setOwnerId(SessionManager.getUserId());
            task.setInspectionState(TaskInspectionState.UNINSPECTION);
            task.setCosted(false);
            task.setAllocationType(NAllocationType.MANUAL);
            task.setAllocationDispositon(SupplierType.UNLIMITED);
            task.setSourceId(4184);
            task.setSourceOther("");
            task.setConsultWayId(4211);
            task.setConsultWayOther("");
            task.setProcessingState(1);
            task.setAllocationState(AllocationState.ALLOCATED);
            task.setRemark("");
            task.setSmsRemark("");
            task.setDistribut(true);
            task.setQualityProgress(TaskQualityProgress.INVARIABILITY);
            task.setCustomer(customer);
            task.setCustomerId(customer.getId());
            task.setProducts(new ArrayList<>());
            task.setEntityState(EntityState.New);
            task=customerTaskService.save(task);
            tasks.add(task);
            customer.setTasks(tasks);
            customer.setTaskCount(tasks.size());
            customer = customerService.save(customer);
        }
        entity.setCustomer(customer);
        entity.setCustomerId(customer.getId());
        entity.setSupplier(SupplierSessionManager.getSupplier());
        entity.setSupplierId(SupplierSessionManager.getSupplierId());
        entity.setDepartment(SupplierSessionManager.getDepartment());
        entity.setDepartmentId(SupplierSessionManager.getDepartmentId());
        entity.setOwner(SessionManager.getUserName());
        entity.setOwnerId(SessionManager.getUserId());
        return super.save(entity);
    }

    @Override
    public Integer updateState(String id, Integer state) {
        String sql = "update ic_ex_register_case set corp_reg_statue = ? where id = ?";
        Oql oql = new Oql();
        oql.getParameters().add("corp_reg_statue",state, Types.INTEGER);
        oql.getParameters().add("id",id,Types.INTEGER);
        return this.pm.executeNonQuery(sql,oql.getParameters());
    }

    @Override
    public List<IcExRegisterCase> getIcCaseByType(ApprovalType wait) {
        Oql oql = new Oql();
        oql.setType(IcExRegisterCase.class);
        oql.setSelects("*");
        oql.setFilter("approvalType=?");
        oql.getParameters().add("approvalType",wait.getValue(),Types.INTEGER);
        return this.pm.queryList(oql);
    }

    @Override
    public IcExRegisterCase updateIcCase(String name, Integer state) {
        Oql oql = new Oql();
        oql.setType(IcExRegisterCase.class);
        oql.setSelects("*");
        oql.setFilter("approvalName=?");
        oql.getParameters().add("approvalName",name,Types.VARCHAR);
        IcExRegisterCase icCase = this.pm.queryFirst(oql);
        if (icCase!=null){
            icCase.setCorpRegStatue(CorpRegStatue.getItem(state));
            icCase.toPersist();
            super.save(icCase);
        }
        return null;
    }

    @Override
    public IcExRegisterCase updateOwner(Integer id, Integer toUserId) {
        IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
        Oql oql = new Oql();
        oql.setSelects("IcExRegisterCase.*");
        oql.setType(IcExRegisterCase.class);
        oql.setFilter("id=?");
        oql.getParameters().add("id",id,Types.INTEGER);
        IcExRegisterCase icCase =this.queryFirst(oql);
        if (icCase!=null){
            icCase.setOwnerId(toUserId);
            Employee employee = employeeService.byId(toUserId);
            icCase.setOwner(employee.getName());
            icCase.toPersist();
            icCase = super.save(icCase);
        }
        return icCase;
    }

    /*查找公司名*/
    @Override
    public IcExRegisterCase findCom(String approvalName){
        Oql oql = new Oql();
        oql.setSelects("IcExRegisterCase.*");
        oql.setType(IcExRegisterCase.class);
        oql.setFilter("approvalName=?");
        oql.getParameters().add("approvalName",approvalName,Types.VARCHAR);
        IcExRegisterCase icCase =this.queryFirst(oql);
        return icCase;
    }
}
