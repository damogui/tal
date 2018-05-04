package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.entity.igirl.ic.ex.dict.OperatorType;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.tm.dict.ConfigType;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.igirl.ic.base.IcExLogService;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import com.gongsibao.igirl.tm.base.IGirlConfigService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.DateManage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExRegisterService extends GsbPersistableService<IcExRegisterCase> implements IcExRegisterService{
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    IcExLogService serviceLog = ServiceFactory.create(IcExLogService.class);
    INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
    public ExRegisterService() {
        super();
        this.type = IcExRegisterCase.class;
    }

    @Override
    public IcExRegisterCase save(IcExRegisterCase entity) {
        EntityState state = entity.getEntityState();
        if (state.equals(EntityState.New)){
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
            entity.setSupplier(SupplierSessionManager.getSupplier());
            entity.setSupplierId(SupplierSessionManager.getSupplierId());
            entity.setDepartment(SupplierSessionManager.getDepartment());
            entity.setDepartmentId(SupplierSessionManager.getDepartmentId());
            entity.setOwner(SessionManager.getUserName());
            entity.setOwnerId(SessionManager.getUserId());
            entity.setOperator(SessionManager.getUserName());
            entity.setOperatorId(SessionManager.getUserId());
            entity.setCollector(SessionManager.getUserName());
            entity.setCollectorId(SessionManager.getUserId());
        }else if (state.equals(EntityState.Persist)){
            IcExLogService logService = ServiceFactory.create(IcExLogService.class);
            Integer id = entity.getId();
            IcExRegisterCase icCase = this.byId(id);
            CorpRegStatue newState = entity.getCorpRegStatue();
            CorpRegStatue oldState = icCase.getCorpRegStatue();
            if (!newState.equals(oldState)){
                IcExLog log = new IcExLog();
                log.setCompanyName(icCase.getApprovalName());
                log.setCorpRegStatue(newState);
                log.setTitle("手动日志");
                Integer userId = SessionManager.getUserId();
                IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
                Employee employee = employeeService.byId(userId);
                StringBuffer sb = new StringBuffer("");
                sb.append(employee.getName()).append("-").append(employee.getMobile()).append("-").append("更新工商状态");
                log.setContent(sb.toString());
                log.setCreateTime(new Date());
                log.setExcId(id);
                log.toNew();
                logService.save(log);
            }
        }
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
    public List<IcExRegisterCase> getIcCaseByType(ApprovalType wait, OperatorType operator) {
        Oql oql = new Oql();
        oql.setType(IcExRegisterCase.class);
        oql.setSelects("*");
        oql.setFilter("approvalType=?");
        oql.getParameters().add("approvalType",wait.getValue(),Types.INTEGER);
        if (operator!=null){
            oql.setFilter("operator=?");
            oql.getParameters().add("operator",operator.getValue(),Types.INTEGER);
        }
        return this.pm.queryList(oql);
    }

    @Override
    public IcExRegisterCase updateIcCase(String name, Integer state) {
        CorpRegStatue statue = CorpRegStatue.getItem(state);
        Oql oql = new Oql();
        oql.setType(IcExRegisterCase.class);
        oql.setSelects("*");
        oql.setFilter("approvalName=?");
        oql.getParameters().add("approvalName",name,Types.VARCHAR);
        IcExRegisterCase icCase = this.pm.queryFirst(oql);
        if (icCase!=null&&!icCase.getCorpRegStatue().equals(statue)){
            icCase.setCorpRegStatue(statue);
            icCase.toPersist();
            return super.save(icCase);
        }
        return null;
    }

    @Override
    public IcExRegisterCase updateOwner(Integer id, Integer toUserId,Integer type) {
        IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
        Oql oql = new Oql();
        oql.setSelects("IcExRegisterCase.*");
        oql.setType(IcExRegisterCase.class);
        oql.setFilter("id=?");
        oql.getParameters().add("id",id,Types.INTEGER);
        IcExRegisterCase icCase =this.queryFirst(oql);
        if (icCase!=null){
            Employee employee = employeeService.byId(toUserId);
            if (type==0){
                icCase.setOwnerId(employee.getId());
                icCase.setOwner(employee.getName());
            }else if (type==1){
                icCase.setOperatorId(employee.getId());
                icCase.setOperator(employee.getName());
            }else{
                icCase.setCollectorId(employee.getId());
                icCase.setCollector(employee.getName());
            }
            icCase.toPersist();
            icCase = super.save(icCase);
        }
        return icCase;
    }

    /**
     * 查找公司名*/
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

    @Override
    public String fetchQrCodeUrl(String url,String casecode,Integer id) {
        // TODO Auto-generated method stub
        IGirlConfigService girlConf=ServiceFactory.create(IGirlConfigService.class);
        Oql oql=new Oql();{
            oql.setType(IGirlConfig.class);
            oql.setSelects("IGirlConfig.*");
            oql.setFilter("configType=? or configType=?");
            oql.getParameters().add("configType", ConfigType.IGIRL_QR_URL.getValue(),Types.INTEGER);
            oql.getParameters().add("configType",ConfigType.IGIRL_MOBILE_TESTURL.getValue(),Types.INTEGER);
        }
        List<IGirlConfig> configs=girlConf.queryList(oql);
        String qcurl="";
        if(configs.size()==1) {
            qcurl="{qrServiceUrl}/qc?detailLink=|{currentDomain}/gsb/igirl/mobile/main.html#/?spid="+SupplierSessionManager.getSupplierId()+"&casecode="+casecode+"&source=iccase"+"&excid="+id;
            qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", url);
            try {
                qcurl=qcurl.split("\\|")[0]+ URLEncoder.encode(qcurl.split("\\|")[1],"UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(configs.size()==2) {
            qcurl="{qrServiceUrl}/qc?detailLink=|{currentDomain}/gsb/igirl/mobile/main.html#/?spid="+SupplierSessionManager.getSupplierId()+"&casecode="+casecode+"&source=iccase"+"&excid="+id;
            qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", configs.get(1).getConfigValue());
            try {
                qcurl=qcurl.split("\\|")[0]+URLEncoder.encode(qcurl.split("\\|")[1],"UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return qcurl;
    }

    @Override
    public String findMoblie(String customerMobile) {
        NCustomer customer = customerService.getByMobile(customerMobile);
        if (customer!=null){
            return customer.getRealName();
        }else{
            return null;
        }
    }

    /*工商状态通过手机号和公司名找到数据*/
    @Override
    public IcExRegisterCase fetchInfoByCode(String code) {
        Oql oql = new Oql();
        {
            oql.setType(IcExRegisterCase.class);
            oql.setSelects("IcExRegisterCase.*,IcExRegisterCase.customer.*");
            oql.setFilter("code=?");
            oql.getParameters().add("code", code, Types.VARCHAR);
        }
        IcExRegisterCase tcs = service.queryFirst(oql);
        return tcs;
    }
}
