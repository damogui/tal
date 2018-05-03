package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyName;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.igirl.ic.base.IcCompanyNameService;
import com.gongsibao.igirl.ic.base.IcExcelBaseInfoService;
import com.gongsibao.utils.SupplierSessionManager;
import org.apache.http.cookie.Cookie;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.persistence.session.SessionManager;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelBaseInfoService extends GsbPersistableService<ExcelBaseInfo> implements IcExcelBaseInfoService {
    public ExcelBaseInfoService() {
        super();
        this.type = ExcelBaseInfo.class;
    }

    @Override
    public ExcelBaseInfo save(ExcelBaseInfo entity) {
        EntityState state = entity.getEntityState();
        if (state.equals(EntityState.New)) {
            INCustomerTaskService customerTaskService = ServiceFactory.create(INCustomerTaskService.class);
            INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
            String  mobile = entity.getCustomerMobile();
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
                IDictService dictService = ServiceFactory.create(IDictService.class);
                customer.setCustomerSourceId(entity.getSource().getValue());
                customer.setCustomerSource(dictService.byId(entity.getSource().getValue()));
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
                task.setSourceId(entity.getSource().getValue());
                task.setSourceOther(customer.getCustomerSourceOther());
                task.setConsultWayId(entity.getConsultWay().getValue());
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
        }
        entity.setSupplier(SupplierSessionManager.getSupplier());
        entity.setSupplierId(SupplierSessionManager.getSupplierId());
        entity.setDepartment(SupplierSessionManager.getDepartment());
        entity.setDepartmentId(SupplierSessionManager.getDepartmentId());
        List<CompanyName> companyNames = entity.getCompanyNames();
        if (companyNames!=null&&!companyNames.isEmpty()){
            IcCompanyNameService companyNameService = ServiceFactory.create(IcCompanyNameService.class);
            for (CompanyName cn:companyNames){
                String name = companyNameService.getName(cn);
                cn.setName(name);
            }
        }
        return super.save(entity);
    }
}
