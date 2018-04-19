package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.persistence.session.SessionManager;

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
        return super.save(entity);
    }
}
