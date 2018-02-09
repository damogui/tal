package com.gongsibao.panda.operation.workspace.supplier.data;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.crm.base.*;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.*;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.utils.NumberUtils;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IRoleGroupService;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
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


// 备注：渠道商 状态不导入新库
public class ImportOldDataToNewData {
    INCustomerTaskQualityService serviceQuality = ServiceFactory.create (INCustomerTaskQualityService.class);//客户质量

    @Test
    public void run() {

        int num1 = handleCustomerOld ();//Customer 里面赋值处理


    }


    /*有分享记录生成任务  NCustomerTaskService  客户任务*/
    private int handleCustomerTaskShare() {
        ICustomerShareService serviceCustomerShare = ServiceFactory.create (ICustomerShareService.class);//有分享
        INCustomerTaskService serviceNCustomerTask = ServiceFactory.create (INCustomerTaskService.class);//任务

        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        int totalCustomerPage = serviceCustomerShare.queryCount (oql1) / pageSize + 1;
        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setPaging (new Paging (i, pageSize));
            List<CustomerShare> customerShareList = serviceCustomerShare.queryList (oql2);

            for (CustomerShare item : customerShareList
                    ) {
                NCustomerTask nCustomerTask = new NCustomerTask ();


                nCustomerTask.toNew ();//新增
                serviceNCustomerTask.save (nCustomerTask);
                totalCountExce += 1;
            }


        }

        return 1;
    }

    /*客户任务 无分享记录 NCustomerTaskService  客户任务*/
    private int handleCustomerTaskNoShare() {

        ICustomerService serviceCustomer = ServiceFactory.create (ICustomerService.class);//客户
        INCustomerTaskService serviceNCustomerTask = ServiceFactory.create (INCustomerTaskService.class);//任务

        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        int totalCustomerPage = serviceCustomer.queryCount (oql1) / pageSize + 1;
        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setPaging (new Paging (i, pageSize));
            List<Customer> customerList = serviceCustomer.queryList (oql2);

            for (Customer item : customerList
                    ) {
                NCustomerTask nCustomerTask = new NCustomerTask ();
                nCustomerTask.setCustomerId (item.getId ());
                nCustomerTask.setTaskType (TaskCustomerType.OLD);//全部都是老客户  有订单的是老客户
                nCustomerTask.setName ("");//  根据意向产品拼出来  内资公司注册-北京市-北京市-朝阳区


//                nCustomerTask.setSupplierId(item.get());
//                nCustomerTask.setDepartmentId(item.get());//回写
                nCustomerTask.setOwnerId (item.getFollowUserId ());  //跟进人id  有分享记录的分享给给谁id
                nCustomerTask.setLastAllocationTime (item.getLastFollowTime ());
                nCustomerTask.setLastAllocationUserId (item.getFollowUserId ());

                QualityInfo qualityInfo = getQualityInfoByCode (item.getFollowStatus ());//根据旧的跟进状态获取新的
//                nCustomer.setIntentionCategory(qualityInfo.getBigCategory());//质量分类
//                nCustomer.setQualityId(qualityInfo.getSmallCategory());/
                nCustomerTask.setFoolowStatus (getNewFoolowStatusByOld (item.getFollowStatus ()));
                if (item.getFollowUserId () == 0) {
                    nCustomerTask.setFoolowStatus (CustomerFollowStatus.UNALLOCATION);//未分配

                }
                nCustomerTask.setIntentionCategory (qualityInfo.getBigCategory ());//大客户质量
                nCustomerTask.setQualityId (qualityInfo.getSmallCategory ());//小客户质量
                nCustomerTask.setLastFollowTime (item.getLastFollowTime ());
                nCustomerTask.setLastFoolowUserId (item.getFollowUserId ());
                //nCustomerTask.setLastContent(item.get());//空
//                nCustomerTask.setNextFoolowTime();//
//                nCustomerTask.setInspectionState(item.get());//
//                nCustomerTask.setLastInspectionUserId(item.get());
//                nCustomerTask.setLastInspectionTime(item.get());
//                nCustomerTask.setLastInspectionContent(item.get());
                nCustomerTask.setRemark (item.getRemark ());
                nCustomerTask.setSmsRemark (item.getSmsRemark ());
                //nCustomerTask.setCostSupplierId(item.get()); //回写
//                nCustomerTask.setCosted(item.get());
                nCustomerTask.setDistribut (item.getFollowUserId () > 0 ? true : false);//有ownerid是分配过

                nCustomerTask.setAllocationType (NAllocationType.AUTO);
                nCustomerTask.setAllocationState (item.getFollowUserId () > 0 ? AllocationState.ALLOCATED : AllocationState.WAIT);//有ownerid是分配过

                //nCustomerTask.setAllocationDispositon(item.get());//是否自营  回写
                nCustomerTask.setSourceId (item.getCustomerSourceId ());
                nCustomerTask.setSourceOther (item.getCustomerSourceOther ());
                nCustomerTask.setConsultWayId (item.getConsultWay ().getValue ());//判空
                nCustomerTask.setConsultWayOther (item.getConsultWayOther ());
                nCustomerTask.setQualityProgress (TaskQualityProgress.INVARIABILITY);
                nCustomerTask.setCreatorId (item.getCreatorId ());
                nCustomerTask.setCreator (item.getCreator ());
                nCustomerTask.setCreateTime (item.getCreateTime ());
                nCustomerTask.setUpdatorId (item.getUpdatorId ());
                nCustomerTask.setUpdator (item.getUpdator ());
                nCustomerTask.setUpdateTime (item.getUpdateTime ());


                nCustomerTask.toNew ();//新增
                serviceNCustomerTask.save (nCustomerTask);
                totalCountExce += 1;
            }


        }


        return 1;
    }

    /*旧跟进状态转成新的跟进状态*/
    private CustomerFollowStatus getNewFoolowStatusByOld(FollowStatus followStatus) {
//        根据是否有跟进人判断是不是未分配
//        初步跟进-跟进中
//        意向签单-跟进中
//        已签单-已签单
//        无效客户-无法签单
//        流失客户-无法签单
//        潜在客户-跟进中
        switch (followStatus.getValue ()) {
//            case 4011:
//
//                return  CustomerFollowStatus.UNFollow;

            case 4012:
                return CustomerFollowStatus.FOLLOWING;

            case 4013:
                return CustomerFollowStatus.FOLLOWING;

            case 4014:
                return CustomerFollowStatus.SIGNED;


            case 4015:
                return CustomerFollowStatus.DEFEATED;

            case 4016:
                return CustomerFollowStatus.DEFEATED;

//            case 4017:
//                return  CustomerFollowStatus.UNSTART;//不处理4017的

            case 4020:
                return CustomerFollowStatus.FOLLOWING;

            default:

        }
        return CustomerFollowStatus.UNSTART;
    }


    /*处理顾客表旧数据*/
    private int handleCustomerOld() {

           /*客户表 crm_customer->n_crm_customer*/
        ICustomerService serviceCustomer = ServiceFactory.create (ICustomerService.class);
        INCustomerService serviceNewCustomer = ServiceFactory.create (INCustomerService.class);//客户

        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次

        StringBuilder filterBuilder = new StringBuilder ();
        filterBuilder.append (" follow_status <>4017");//过滤掉招商渠道的
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        oql1.setFilter (filterBuilder.toString ());
        int totalCustomerPage = serviceCustomer.queryCount (oql1) / pageSize + 1;

        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql1.setFilter (filterBuilder.toString ());
            oql2.setPaging (new Paging (i, pageSize));
            List<Customer> customerList = serviceCustomer.queryList (oql2);

            for (Customer item : customerList
                    ) {
                NCustomer nCustomer = new NCustomer ();
                nCustomer.setId (item.getId ());
                nCustomer.setAccountId (item.getAccountId ());
                nCustomer.setRealName (item.getRealName ());
                nCustomer.setSex (item.getSex ());
                nCustomer.setMobile (item.getMobile ());

                //是否会员 根据订单id是否大于0判断是不是会员
                nCustomer.setIsMember (item.getAccountId () > 0 ? true : false);

                nCustomer.setTelephone (item.getTelephone ());
                nCustomer.setEmail (item.getEmail ());
                nCustomer.setQq (item.getQq ());
                nCustomer.setWeixin (item.getWeixin ());
                nCustomer.setBirdthday (item.getBirdthday ());
                nCustomer.setAddr (item.getAddr ());


                nCustomer.setProvinceId (item.getfProvinceId ());
                nCustomer.setCityId (item.getCityId ());
                nCustomer.setCountyId (item.getfCountyId ());
                nCustomer.setUnvalidRemark (item.getUnvalidRemark ());
                nCustomer.setMaybeRemark (item.getMaybeRemark ());
                nCustomer.setCustomerSourceOther (item.getCustomerSourceOther ());
                nCustomer.setIntroducerUserId (item.getIntroducerUserId ());
                nCustomer.setConsultWay (item.getConsultWay ());
                nCustomer.setConsultWayOther (item.getConsultWayOther ());
                nCustomer.setImportant (item.getImportant ());
                nCustomer.setInvalid (item.getInvalid ());
                nCustomer.setIntroducerId (item.getIntroducerId ());
                nCustomer.setRemark (item.getRemark ());

                nCustomer.setAllocationType (item.getAllocationType ());
                nCustomer.setSupplierId (0);//回写供应商id和部门id
                nCustomer.setDepartmentId (0);
                nCustomer.setSwtCustomerId (item.getSwtCustomerId ());
                nCustomer.setSwtServiceId (item.getSwtServiceId ());
//                if (item.getFollowStatus().getValue() == 4017) {
//
//                    nCustomer.setCrmSourceType(1);//招商渠道 区分
//                }
                QualityInfo qualityInfo = getQualityInfoByCode (item.getFollowStatus ());
                nCustomer.setIntentionCategory (qualityInfo.getBigCategory ());//质量分类
                nCustomer.setQualityId (qualityInfo.getSmallCategory ());//
                nCustomer.setLastFollowTime (item.getLastFollowTime ());
                nCustomer.setLastFoolowUserId (item.getFollowUserId ());
                nCustomer.setLastContent ("");
                // nCustomer.setNextFoolowTime(new Date());//下次跟进时间
                nCustomer.setCustomerSourceId (item.getCustomerSourceId ());

                nCustomer.setCreatorId (item.getCreatorId ());
                nCustomer.setCreator (item.getCreator ());
                nCustomer.setCreateTime (item.getCreateTime ());
                nCustomer.setUpdatorId (item.getUpdatorId ());
                nCustomer.setUpdator (item.getUpdator ());
                nCustomer.setUpdateTime (item.getUpdateTime ());
                //任务
                List<NCustomerTask> listTask = getTasksByCustomerId (item);
                nCustomer.setTasks (listTask);//任务里面进行意向产品
                nCustomer.setTaskCount (listTask.size ());//0任务数量回写
                //意向产品
                // nCustomer.setProducts(getProductsByCustomerId(item));
                //日志
                nCustomer.setFollows (getFollowsByCustomer (nCustomer, item.getFollowStatus ()));
                //顾客关联企业
                nCustomer.setCompanys (getCompanysByCustomer (item));
                nCustomer.toNew ();
                serviceNewCustomer.save (nCustomer);
                totalCountExce += 1;
            }


        }

        return totalCountExce;
    }

    /*获取关联企业*/
    private List<NCustomerCompany> getCompanysByCustomer(Customer customer) {
        ICustomerCompanyMapService serviceCustomerCompanyMap = ServiceFactory.create (ICustomerCompanyMapService.class);//日志服务
        List<NCustomerCompany> listNCustomerCompany = new ArrayList<> ();

        Oql oql = new Oql ();
        oql.setFilter (" customer_id=" + customer.getId ());
        List<CustomerCompanyMap> listOld = serviceCustomerCompanyMap.queryList (oql);
        for (CustomerCompanyMap item : listOld
                ) {
            NCustomerCompany ncus = new NCustomerCompany ();

            ncus.setId (item.getId ());
            ncus.setCustomerId (customer.getId ());
            ncus.setCompanyId (item.getCompanyId ());
            //ncus.setSupplierId(item.getS());
            // ncus.setDepartmentId(item.getD());
            ncus.setCreatorId (item.getCreatorId ());
            ncus.setCreator (item.getCreator ());
            ncus.setCreateTime (item.getCreateTime ());
            ncus.setUpdatorId (item.getUpdatorId ());
            ncus.setUpdator (item.getUpdator ());
            ncus.setUpdateTime (item.getUpdateTime ());
            ncus.toNew ();
            listNCustomerCompany.add (ncus);
        }
        return listNCustomerCompany;
    }

    /*获取跟进日志*/
    private List<NCustomerTaskFoolow> getFollowsByCustomer(NCustomer customer, FollowStatus oldFollowStatus) {
        ICustomerFollowService serviceCustomerFollow = ServiceFactory.create (ICustomerFollowService.class);//日志服务
        List<NCustomerTaskFoolow> list = new ArrayList<> ();
        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次

        StringBuilder filterBuilder = new StringBuilder ();
        filterBuilder.append (" customer_id=" + customer.getId ());//过滤掉招商渠道的
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        oql1.setFilter (filterBuilder.toString ());
        int totalCustomerPage = serviceCustomerFollow.queryCount (oql1) / pageSize + 1;

        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql1.setFilter (filterBuilder.toString ());
            oql2.setPaging (new Paging (i, pageSize));
            List<CustomerFollow> customerFollowList = serviceCustomerFollow.queryList (oql2);

            for (CustomerFollow item : customerFollowList
                    ) {
                NCustomerTaskFoolow nCustomerTaskFoolow = new NCustomerTaskFoolow ();

//                nCustomerTaskFoolow.setId();
//        nCustomerTaskFoolow.setCustomerId(item.getId());
//        nCustomerTaskFoolow.setTaskType(item.getAccountId() > 0 ? TaskCustomerType.OLD : TaskCustomerType.NEW);//全部都是老客户  有订单的是老客户
                nCustomerTaskFoolow.setId (item.getId ());
                nCustomerTaskFoolow.setCustomerId (customer.getId ());
                List<NCustomerTask> listTask = customer.getTasks ();
                if (listTask != null && listTask.size () > 0) {

                    nCustomerTaskFoolow.setTaskId (listTask.get (0).getId ());
                }
                QualityInfo qualityInfo = getQualityInfoByCode (oldFollowStatus);//通过旧的顾客的跟进状态获取质量
                nCustomerTaskFoolow.setQualityCategory (qualityInfo.getBigCategory ());
                nCustomerTaskFoolow.setQualityId (qualityInfo.getSmallCategory ());
                nCustomerTaskFoolow.setQualityProgress (TaskQualityProgress.INVARIABILITY);
                //nCustomerTaskFoolow.setNextFoolowTime(item.get());
                nCustomerTaskFoolow.setContent (item.getContent ());
                //nCustomerTaskFoolow.setSigningAmount(item.get);
                //nCustomerTaskFoolow.setReturnedAmount(item.get());
                //nCustomerTaskFoolow.setSupplierId(item.get());
                //nCustomerTaskFoolow.setDepartmentId(item.get());
                nCustomerTaskFoolow.setCreatorId (item.getCreatorId ());
                nCustomerTaskFoolow.setCreator (item.getCreator ());
                nCustomerTaskFoolow.setCreateTime (item.getCreateTime ());
                nCustomerTaskFoolow.setUpdatorId (item.getUpdatorId ());
                nCustomerTaskFoolow.setUpdator (item.getUpdator ());
                nCustomerTaskFoolow.setUpdateTime (item.getUpdateTime ());
                nCustomerTaskFoolow.toNew ();
            }


        }

        return list;
    }


    /*根据客户id获取相关任务*/
    private List<NCustomerTask> getTasksByCustomerId(Customer item) {

        List<NCustomerTask> list = new ArrayList<> ();
        NCustomerTask nCustomerTask = new NCustomerTask ();
        nCustomerTask.setCustomerId (item.getId ());
        nCustomerTask.setTaskType (item.getAccountId () > 0 ? TaskCustomerType.OLD : TaskCustomerType.NEW);//全部都是老客户  有订单的是老客户
        String productName = "";//产品名称
        if (item.getProdDetails () != null && item.getProdDetails ().size () > 0) {
            CustomerProdMap customerProdMap = item.getProdDetails ().get (0);//都会有意向产品
            productName = customerProdMap.getProduct ().getName ();
        }
        String areaName = getProvinceCityAndCountry (item.getCityId ());

        if (StringManager.isNullOrEmpty (productName)) {
            productName = "无意向产品";
        }
        String taskName = String.format ("%s-%s", productName, areaName);


        nCustomerTask.setName (taskName);//  根据意向产品拼出来  内资公司注册-北京市-北京市-朝阳区


//                nCustomerTask.setSupplierId(item.get());
//                nCustomerTask.setDepartmentId(item.get());//回写
        nCustomerTask.setOwnerId (item.getFollowUserId ());  //跟进人id  有分享记录的分享给给谁id
        nCustomerTask.setLastAllocationTime (item.getLastFollowTime ());
        nCustomerTask.setLastAllocationUserId (item.getFollowUserId ());

        QualityInfo qualityInfo = getQualityInfoByCode (item.getFollowStatus ());//根据旧的跟进状态获取新的
//                nCustomer.setIntentionCategory(qualityInfo.getBigCategory());//质量分类
//                nCustomer.setQualityId(qualityInfo.getSmallCategory());/
        nCustomerTask.setFoolowStatus (getNewFoolowStatusByOld (item.getFollowStatus ()));
        if (item.getFollowUserId () == 0) {
            nCustomerTask.setFoolowStatus (CustomerFollowStatus.UNALLOCATION);//未分配
        }
        nCustomerTask.setIntentionCategory (qualityInfo.getBigCategory ());//大客户质量
        nCustomerTask.setQualityId (qualityInfo.getSmallCategory ());//小客户质量
        nCustomerTask.setLastFollowTime (item.getLastFollowTime ());
        nCustomerTask.setLastFoolowUserId (item.getFollowUserId ());
        //nCustomerTask.setLastContent(item.get());//空
//                nCustomerTask.setNextFoolowTime();//
//                nCustomerTask.setInspectionState(item.get());//
//                nCustomerTask.setLastInspectionUserId(item.get());
//                nCustomerTask.setLastInspectionTime(item.get());
//                nCustomerTask.setLastInspectionContent(item.get());
        nCustomerTask.setRemark (item.getRemark ());
        nCustomerTask.setSmsRemark (item.getSmsRemark ());
        //nCustomerTask.setCostSupplierId(item.get()); //回写
//                nCustomerTask.setCosted(item.get());
        nCustomerTask.setDistribut (item.getFollowUserId () > 0 ? true : false);//有ownerid是分配过

        nCustomerTask.setAllocationType (NAllocationType.AUTO);
        nCustomerTask.setAllocationState (item.getFollowUserId () > 0 ? AllocationState.ALLOCATED : AllocationState.WAIT);//有ownerid是分配过

        //nCustomerTask.setAllocationDispositon(item.get());//是否自营  回写
        nCustomerTask.setSourceId (item.getCustomerSourceId ());
        nCustomerTask.setSourceOther (item.getCustomerSourceOther ());
        nCustomerTask.setConsultWayId (item.getConsultWay ().getValue ());//判空
        nCustomerTask.setConsultWayOther (item.getConsultWayOther ());
        nCustomerTask.setQualityProgress (TaskQualityProgress.INVARIABILITY);
        nCustomerTask.setCreatorId (item.getCreatorId ());
        nCustomerTask.setCreator (item.getCreator ());
        nCustomerTask.setCreateTime (item.getCreateTime ());
        nCustomerTask.setUpdatorId (item.getUpdatorId ());
        nCustomerTask.setUpdator (item.getUpdator ());
        nCustomerTask.setUpdateTime (item.getUpdateTime ());

        nCustomerTask.toNew ();//新增

        nCustomerTask.setProducts (getProductsByTask (nCustomerTask));//设置意向产品集合
        list.add (nCustomerTask);
        //当客户有分享的时候再添加任务
        ICustomerShareService serviceCustomerShare = ServiceFactory.create (ICustomerShareService.class);
        Oql oql = new Oql ();
        oql.setFilter ("customer_id=" + item.getId ());
        List<CustomerShare> listShare = serviceCustomerShare.queryList (oql);

        for (CustomerShare share : listShare
                ) {
            NCustomerTask nCustomerTask2 = nCustomerTask;
            nCustomerTask2.setOwnerId (share.getShareUserId ());
            nCustomerTask2.setCreatorId (share.getCreatorId ());
            nCustomerTask2.setUpdatorId (share.getUpdatorId ());
            nCustomerTask2.setUpdator (share.getUpdator ());
            nCustomerTask2.setCreator (share.getCreator ());
            nCustomerTask2.setUpdateTime (share.getUpdateTime ());
            nCustomerTask2.setCreateTime (share.getCreateTime ());
            nCustomerTask2.toNew ();
            list.add (nCustomerTask2);
        }


        return list;

    }

    /*根据任务获取意向产品赋值*/
    private List<NCustomerProduct> getProductsByTask(NCustomerTask nCustomerTask) {
        ICustomerProdMapService serviceCustomerProdMap = ServiceFactory.create (ICustomerProdMapService.class);
        INCustomerProductService serviceNewCustomerProdMap = ServiceFactory.create (INCustomerProductService.class);//意向产品
        List<NCustomerProduct> nCustomerProdMapList = new ArrayList<> ();
        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        oql1.setFilter (" customer_id=" + nCustomerTask.getCustomerId ());//只弄一条
        int totalCustomerPage = serviceCustomerProdMap.queryCount (oql1) / pageSize + 1;
        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setFilter (" customer_id=" + nCustomerTask.getCustomerId ());//只弄一条
            oql2.setPaging (new Paging (i, pageSize));
            List<CustomerProdMap> customerProdMapList = serviceCustomerProdMap.queryList (oql2);

            for (CustomerProdMap item : customerProdMapList
                    ) {
                NCustomerProduct nCustomerProduct = new NCustomerProduct ();
                nCustomerProduct.setId (item.getId ());
                //nCustomerProduct.setSupplierId(0);//回写
                nCustomerProduct.setCustomerId (item.getCustomerId ());
                nCustomerProduct.setTaskId (nCustomerTask.getId ());//任务
                nCustomerProduct.setProductCategoryId1 (1);//分类  产品表对应typeid 二级分类 一级分类从字典表取值
                //nCustomerProduct.setProductCategory1(item.get());
                nCustomerProduct.setProductCategoryId2 (1);
                //nCustomerProduct.setProductCategory2(item.get());
                nCustomerProduct.setProductId (item.getProductId ());
                nCustomerProduct.setProvinceId (item.getdProvinceId ());
                nCustomerProduct.setCityId (item.getCityId ());
                nCustomerProduct.setCountyId (item.getdCountyId ());
                nCustomerProduct.setCreatorId (item.getCreatorId ());
                nCustomerProduct.setCreator (item.getCreator ());
                nCustomerProduct.setCreateTime (item.getCreateTime ());
                nCustomerProduct.setUpdatorId (item.getUpdatorId ());
                nCustomerProduct.setUpdator (item.getUpdator ());
                nCustomerProduct.setUpdateTime (item.getUpdateTime ());
                nCustomerProduct.toNew ();//新增
                nCustomerProdMapList.add (nCustomerProduct);
            }


        }

        return nCustomerProdMapList;
    }

    /*意向产品数据处理  批量暂时不用*/
    private int handleCustomerProdMap() {
        ICustomerProdMapService serviceCustomerProdMap = ServiceFactory.create (ICustomerProdMapService.class);
        INCustomerProductService serviceNewCustomerProdMap = ServiceFactory.create (INCustomerProductService.class);//意向产品
        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        int totalCustomerPage = serviceCustomerProdMap.queryCount (oql1) / pageSize + 1;
        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setPaging (new Paging (i, pageSize));
            List<CustomerProdMap> customerProdMapList = serviceCustomerProdMap.queryList (oql2);

            for (CustomerProdMap item : customerProdMapList
                    ) {
                NCustomerProduct nCustomerProduct = new NCustomerProduct ();
                nCustomerProduct.setId (item.getId ());
                //nCustomerProduct.setSupplierId(0);//回写
                nCustomerProduct.setCustomerId (item.getCustomerId ());
                //nCustomerProduct.setTaskId();//任务
                nCustomerProduct.setProductCategoryId1 (1);//分类  产品表对应typeid 二级分类 一级分类从字典表取值
                //nCustomerProduct.setProductCategory1(item.get());
                nCustomerProduct.setProductCategoryId2 (1);
                //nCustomerProduct.setProductCategory2(item.get());
                nCustomerProduct.setProductId (item.getProductId ());
                nCustomerProduct.setProvinceId (item.getdProvinceId ());
                nCustomerProduct.setCityId (item.getCityId ());
                nCustomerProduct.setCountyId (item.getdCountyId ());
                nCustomerProduct.setCreatorId (item.getCreatorId ());
                nCustomerProduct.setCreator (item.getCreator ());
                nCustomerProduct.setCreateTime (item.getCreateTime ());
                nCustomerProduct.setUpdatorId (item.getUpdatorId ());
                nCustomerProduct.setUpdator (item.getUpdator ());
                nCustomerProduct.setUpdateTime (item.getUpdateTime ());

                nCustomerProduct.toNew ();//新增
                serviceNewCustomerProdMap.save (nCustomerProduct);
                totalCountExce += 1;
            }


        }
        return totalCountExce;
    }


    /*获取地区名称根据旧的cityId*/
    private String getProvinceCityAndCountry(Integer cityId) {
        String areaName = "";
        String pName = "";//省
        String cityName = "";//市
        String countryName = "";//区县

        if (cityId <= 0) {

            return areaName;
        }
        IDictService serviceDict = ServiceFactory.create (IDictService.class);
        Dict dict1 = serviceDict.byId (cityId);
        Dict dict2 = new Dict ();
        Dict dict3 = new Dict ();
        if (dict1 == null) {
            return areaName;

        }
        //分情况 有父级id  无父级Id
        if (dict1.getParentId () > 0) {
            //有父级id：区或者城市
            List<Dict> list2 = serviceDict.byParentId (cityId);
            if (list2 != null && list2.size () > 0) {//城市
                dict2 = list2.get (0);
                cityName = dict1.getName ();
                countryName = dict2.getName ();//县
                List<Dict> list3 = serviceDict.byParentId (dict1.getParentId ());
                if (list3 != null && list3.size () > 0) {
                    dict3 = list3.get (0);
                    pName = dict3.getName ();//省
                }

            } else {//区县
                countryName = dict1.getName ();
                dict2 = serviceDict.byId (dict1.getParentId ());
                if (dict2 != null) {
                    cityName = dict2.getName ();
                    dict3 = serviceDict.byId (dict2.getParentId ());
                    if (dict3 != null) {
                        pName = dict3.getName ();
                    }
                }


            }


        } else {
            pName = dict1.getName ();
            List<Dict> list2 = serviceDict.byParentId (cityId);
            if (list2 != null && list2.size () > 0) {
                dict2 = list2.get (0);
            }

            if (dict2 != null) {
                cityName = dict2.getName ();
                List<Dict> list3 = serviceDict.byParentId (dict2.getId ());
                if (list3 != null && list3.size () > 0) {
                    dict3 = list3.get (0);
                    countryName = dict3.getName ();
                }


            }


        }

        return String.format ("%s-%s-%s", pName, cityName, countryName);


    }

    /*根据之前的跟进状态判断客户质量*/
    private QualityInfo getQualityInfoByCode(FollowStatus followStatus) {

        String smallCode = "";
        QualityInfo quaInfo = new QualityInfo ();
        Oql oql = new Oql ();
        //filterBuilder.append("province_id`=?");
        oql.setFilter (" code=?");
        QueryParameters qps = new QueryParameters ();

        switch (followStatus.getValue ()) {
            case 4011:
                smallCode = "X";

            case 4012:
                smallCode = "A4";

            case 4013:
                smallCode = "A2";

            case 4014:
                smallCode = "S";

            case 4015:
                smallCode = "D2";

            case 4016:
                smallCode = "C4";

//            case 4017:
//                smallCode = "A4";  //不需要管

            case 4020:
                smallCode = "B1";

            default:


        }
        qps.add ("@code", smallCode, Types.VARCHAR);
        oql.setParameters (qps);

        NCustomerTaskQuality nQuality = serviceQuality.queryFirst (oql);
        if (nQuality != null) {
            quaInfo.setBigCategory (nQuality.getIntentionCategory ());
            quaInfo.setSmallCategory (nQuality.getId ());
        } else {

            quaInfo.setBigCategory (QualityCategory.B);
            quaInfo.setSmallCategory (8);
        }

        return quaInfo;

    }


}


/*质量小分类*/
class QualityInfo {
    /*大分类*/
    private QualityCategory bigCategory;
    /*小分类*/
    private int smallCategory;


    public int getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(int smallCategory) {
        this.smallCategory = smallCategory;
    }

    public QualityCategory getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(QualityCategory bigCategory) {
        this.bigCategory = bigCategory;
    }
}
