package com.gongsibao.panda.operation.workspace.supplier.data;

import com.gongsibao.crm.base.ICustomerProdMapService;
import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.NCustomer;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.organization.base.IRoleGroupService;

import java.util.List;

/**
 * Created by win on 2018/2/8.
 */
/*从旧表导入新表*/
    /*旧表*/
/* crm_customer （客户）  crm_customer_prod_map（意向产品）   crm_customer_share（分享记录）  crm_customer_follow（沟通日志） crm_customer_company_map（顾客关联企业） */
/*新表*/
/* n_crm_customer（客户）   n_crm_customer_task（客户任务） n_crm_customer_product_map（意向产品）  n_crm_task_foolow（跟进日志）  n_crm_customer_company_map（顾客关联企业）*/
    /*Service*/
/*old  CustomerService  客户   CustomerProdMapService 意向产品  CustomerShareService  分享记录 CustomerFollowService 沟通日志  CustomerCompanyMapService  顾客关联企业*/
/*new    NCustomerService  客户    NCustomerTaskService  客户任务   NCustomerProductService 意向产品   NCustomerTaskFoolowService  跟进日志  NCustomerCompanyService  顾客关联企业*/

public class ImportOldDataToNewData {
    /*客户表 crm_customer->n_crm_customer*/
    ICustomerService serviceCustomer = ServiceFactory.create(ICustomerService.class);
    INCustomerService serviceNewCustomer = ServiceFactory.create(INCustomerService.class);//客户

    ICustomerProdMapService serviceCustomerProdMap = ServiceFactory.create(ICustomerProdMapService.class);
    INCustomerProductService serviceNewCustomerProdMap = ServiceFactory.create(INCustomerProductService.class);//意向产品

    @Test
    public void run() {

        int pageSize = 100;//每100条进行处理一次
        Oql oql1 = new Oql() {
        };
        oql1.setOrderby(" upd_time ");
        int totalCustomerPage = serviceCustomer.queryCount(oql1) / pageSize + 1;

        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql() {
            };
            oql2.setOrderby(" upd_time ");
            oql2.setPaging(new Paging(i, pageSize));
            List<Customer> customerList = serviceCustomer.queryList(oql2);

            for (Customer item:customerList
                 ) {
                NCustomer nCustomer=new NCustomer();
                nCustomer.setId(item.getId());
                nCustomer.setAccountId(item.getAccountId());
                nCustomer.setRealName(item.getRealName());
                nCustomer.setSex(item.getSex());
                nCustomer.setMobile(item.getMobile());
                nCustomer.setTelephone(item.getTelephone());
                nCustomer.setEmail(item.getEmail());
//                nCustomer.setQq(item.get());
//                nCustomer.setWeixin(item.get());
//                nCustomer.setBirdthday(item.get());
//                nCustomer.setAddr(item.get());
//
//                nCustomer.setLastFoolowUserId(item.get());
//                nCustomer.setProvinceId(item.getfProvinceId());
//                nCustomer.setCityId(item.getCityId());
//                nCustomer.setCountyId(item.getfCountyId());
//                nCustomer.setUnvalidRemark(item.get());
//                nCustomer.setMaybeRemark(item.get());
//                nCustomer.setCustomerSourceOther(item.get());
//                nCustomer.setIntroducerUserId(item.get());
//                nCustomer.setConsultWay(item.get());
//                nCustomer.setConsultWayOther(item.get());
//                nCustomer.setImportant(item.get());
//                nCustomer.setInvalid(item.get());
//                nCustomer.getIntroducerId(item.get());
//                nCustomer.setRemark(item.get());
////                nCustomer.set(item.get());//Is_BBK
//                nCustomer.setAllocationType(item.get());
//                nCustomer.setSupplierId(item.get());
//                nCustomer.setDepartmentId(item.get());
//                nCustomer.setSwtCustomerId(item.get());
//                nCustomer.setSwtServiceId(item.get());
//                nCustomer.set(item.get());
//                nCustomer.set(item.get());
//                nCustomer.set(item.get());
//                nCustomer.set(item.get());




            }


        }


    }


}
