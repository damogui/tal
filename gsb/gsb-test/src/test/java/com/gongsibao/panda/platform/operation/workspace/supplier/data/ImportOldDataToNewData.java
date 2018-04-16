package com.gongsibao.panda.platform.operation.workspace.supplier.data;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.crm.base.*;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.*;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.IImportNCustomerService;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity.ImNCustomer;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity.ImNCustomerCompany;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity.ImNCustomerTaskFoolow;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.IMNCustomerTaskService;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.ImNCustomerTaskFoolowService;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.tools.TimeUtils;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;

import jodd.typeconverter.Convert;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.organization.base.IRoleGroupService;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by win on 2018/2/8.
 */
/*从旧表导入新表*/
    /*旧表*/
/* crm_customer  Customer（客户）  crm_customer_prod_map CustomerProdMap（意向产品）   crm_customer_share（分享记录）  crm_customer_follow CustomerFollow（沟通日志） crm_customer_company_map CustomerCompanyMap（顾客关联企业） */
/*新表*/
/* n_crm_customer NCustomer（客户 用旧表）   n_crm_customer_task NCustomerTask（客户商机---- 新表）
 n_crm_customer_product_map   NCustomerProduct（意向产品 用旧表）  n_crm_task_foolow NCustomerTaskFoolow（跟进日志----- 新表）  n_crm_customer_company_map NCustomerCompany（顾客关联企业 用旧表）*/
    /*Service*/
/*old  CustomerService  客户   CustomerProdMapService 意向产品  CustomerShareService  分享记录 CustomerFollowService 沟通日志  CustomerCompanyMapService  顾客关联企业*/
/*new    NCustomerService  客户    NCustomerTaskService  客户商机   NCustomerProductService 意向产品   NCustomerTaskFoolowService  跟进日志  NCustomerCompanyService  顾客关联企业*/

//生产库合计:278593
// 备注：渠道商 状态不导入新库
public class ImportOldDataToNewData {
    INCustomerTaskQualityService serviceQuality = ServiceFactory.create (INCustomerTaskQualityService.class);//客户质量
    IPersister<NCustomer> nCustomerService = PersisterFactory.create ();//进行执行sql
    IMNCustomerTaskService nCustomerTaskService = ServiceFactory.create (IMNCustomerTaskService.class);//任务保存需要
//    ImNCustomerTaskFoolowService nCustomerTaskFoolowService = ServiceFactory.create (ImNCustomerTaskFoolowService.class);//任务跟进

    IPersister<NCustomerProduct> nCustomerProductService = PersisterFactory.create ();//意向产品


//    INCustomerService nCustomerService2 = ServiceFactory.create (INCustomerService.class);//任务保存需要

    @Test
    public void run() {
/*第一步*/

        int num1 = handleCustomerOld ();
        String msg = String.format ("处理数据共%s条,时间%s", num1,TimeUtils.getDateFormat (new Date()));
        System.out.println (msg);


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
        IImportNCustomerService serviceNewCustomer = ServiceFactory.create (IImportNCustomerService.class);//新客户

        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次

        StringBuilder filterBuilder = new StringBuilder ();
        StringBuilder filterBuilder2 = new StringBuilder ();
        //读取最大的id，然后根据节点插入
        String sql = "SELECT  IFNULL(MIN(pkid),0) id  FROM   crm_customer  WHERE  is_member IS NULL and follow_status <>4017"; //"SELECT  IFNULL(MAX(id),0) id  FROM n_crm_customer";//导入从pkid 1开始 每次续导从 省id为null的开始
        IPersister<ImNCustomer> pm = PersisterFactory.create ();
        int idMax =Convert.toInteger (pm.executeScalar (sql, null));


        filterBuilder.append (" follow_status <>4017 and is_member IS NULL and pkid>=" + idMax);//过滤掉招商渠道的和进行续导
        filterBuilder2.append (" follow_status <>4017  and pkid>=" + idMax);//过滤掉招商渠道的和进行续导
        if (idMax==0){

            return 0 ;//代表已经处理完毕
        }
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        oql1.setFilter (filterBuilder.toString ());
        int countData = serviceCustomer.queryCount (oql1);
        int totalCustomerPage = 0;
        if ((countData % pageSize) == 0) {//判断是不是有剩余
            totalCustomerPage = countData / pageSize;

        } else {

            totalCustomerPage = countData / pageSize + 1;
        }


        for (int i = 1; i < totalCustomerPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid asc ");
            oql2.setFilter (filterBuilder2.toString ());
            oql2.setPaging (new Paging (i, pageSize));
            oql2.setType (Customer.class);
            StringBuilder sb = new StringBuilder ();
            sb.append ("Customer.*");
//            sb.append ("Customer.*,");
//            sb.append ("Customer.prodDetails.*,");
//            sb.append ("Customer.prodDetails.product.{id,name}");

            oql2.setSelects (sb.toString ());//设置要查询的列


            List<Customer> customerList = serviceCustomer.queryList (oql2);

            for (Customer item : customerList
                    ) {
                ImNCustomer nCustomer = new ImNCustomer ();
                //是否会员 根据订单id是否大于0判断是不是会员
                nCustomer.setId (item.getId ());
                nCustomer.setIsMember (item.getAccountId () > 0 ? true : false);
                //需要处理省市县的id进行配对
                ProvinceCityAndCountry provinceCityAndCountry = getProvinceCityAndCountry (item.getCityId ());
                if (provinceCityAndCountry != null) {
                    nCustomer.setProvinceId (provinceCityAndCountry.getProvinceId ());
                    nCustomer.setCityId (provinceCityAndCountry.getCityId ());
                    nCustomer.setCountyId (provinceCityAndCountry.getCountryId ());

                }
                QualityInfo qualityInfo = getQualityInfoByCode (item.getFollowStatus ());
                nCustomer.setIntentionCategory (qualityInfo.getBigCategory ());//质量分类
                nCustomer.setQualityId (qualityInfo.getSmallCategory ());//

                nCustomer.setLastFoolowUserId (item.getFollowUserId ());
                nCustomer.setLastContent ("");//可以考虑回写
                // nCustomer.setNextFoolowTime(new Date());//下次跟进时间
                nCustomer.setCustomerSourceId (item.getCustomerSourceId ());//需要注意
                //商机
                List<NCustomerTask> listTask = getTasksByCustomerId (item);//直接进行保存
                //通过Customer保存
                // nCustomer.setTasks (listTask);//商机里面进行意向产品
                nCustomer.setTaskCount (listTask.size ());//0商机数量回写
                //意向产品
                // nCustomer.setProducts(getProductsByCustomerId(item));
                //跟进日志，流转日志暂时不考虑
                // nCustomer.setFollows (getFollowsByCustomer (item, listTask));//item.getFollowStatus ()
                
                
                /**
                 * 同步customer的getFollowsByCustomer方法，有点慢先不执行。
                 * 等customer,执行完成后，直接从crm_customer_follow导入至n_crm_task_foolow，然后再通过sql语句拿到task_id相关信息进行更新。
                 * 50分钟只执行了1000条。
                 * 
                 */
                //getFollowsByCustomer (item, listTask);//直接手动保存不通过持久化
                
                //顾客关联企业
                // nCustomer.setCompanys (getCompanysByCustomer (item));//用旧表
                //nCustomer.toPersist ();//变成修改
                try {
                    //serviceNewCustomer.save (nCustomer);//进行手动更新通过sql
                    saveNCustomer (nCustomer);//通过sql进行保存

                } catch (Exception e) {
                    System.out.println (e.getMessage ());

                }
                totalCountExce += 1;
                System.out.println (String.format ("已经处理%s条,时间%s,客户id:%s", totalCountExce,TimeUtils.getDateFormat (new Date()),nCustomer.getId ()));


            }


        }

        return totalCountExce;
    }

    /*进行保存要添加的字段*/
    private int saveNCustomer(ImNCustomer nCustomer) {

        //更新字段  随后更新  `supplier_id`=, `department_id`=, `last_content`=%s, , nCustomer.getLastContent ()
        String sql = String.format ("UPDATE crm_customer SET is_member=%s,f_province_id=%s,f_city_id=%s,f_county_id=%s,`allocation_type`=%s, `intention_category`=%s, `quality_id`=%s, `last_foolow_user_id`=%s,  `next_foolow_time`=%s, `customer_source_id`=%s, `task_count`=%s  WHERE pkid=%s", nCustomer.getIsMember () == true ? 1 : 0, nCustomer.getProvinceId (), nCustomer.getCityId (), nCustomer.getCountyId (), nCustomer.getAllocationType ().getValue (), nCustomer.getIntentionCategory ().getValue (), nCustomer.getQualityId (), nCustomer.getLastFoolowUserId (), nCustomer.getNextFoolowTime (), nCustomer.getCustomerSourceId (), nCustomer.getTaskCount (), nCustomer.getId ());

        int num = nCustomerService.executeNonQuery (sql, null);
//        NCustomer nCustomerSave = nCustomerService2.byId (nCustomer.getId ());
//        nCustomerSave.toPersist ();//更新
//        nCustomerSave.setTasks (nCustomer.getTasks ());
//        nCustomerService2.save (nCustomerSave);
        return num;
    }

    /*获取关联企业*/
    private List<ImNCustomerCompany> getCompanysByCustomer(Customer customer) {
        ICustomerCompanyMapService serviceCustomerCompanyMap = ServiceFactory.create (ICustomerCompanyMapService.class);//日志服务
        List<ImNCustomerCompany> listNCustomerCompany = new ArrayList<> ();

        Oql oql = new Oql ();
        oql.setFilter (" customer_id=" + customer.getId ());
        oql.setType (CustomerCompanyMap.class);
        oql.setSelects ("CustomerCompanyMap.*");
        List<CustomerCompanyMap> listOld = serviceCustomerCompanyMap.queryList (oql);
        for (CustomerCompanyMap item : listOld
                ) {
            ImNCustomerCompany ncus = new ImNCustomerCompany ();

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

    /*获取跟进日志转换为新的跟进*/
    private List<ImNCustomerTaskFoolow> getFollowsByCustomer(Customer customer, List<NCustomerTask> tasks) {
        ICustomerFollowService serviceCustomerFollow = ServiceFactory.create (ICustomerFollowService.class);//日志服务
        List<ImNCustomerTaskFoolow> listImNCustomerTaskFoolow = new ArrayList<> ();
        // List<NCustomerTaskFoolow> listNCustomerTaskFoolow = new ArrayList<> ();
        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次

        StringBuilder filterBuilder = new StringBuilder ();
        filterBuilder.append (" customer_id=" + customer.getId ());//过滤掉招商渠道的
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        oql1.setFilter (filterBuilder.toString ());

        int countData = serviceCustomerFollow.queryCount (oql1) / pageSize + 1;
        int totalPage=0;
        if ((countData % pageSize) == 0){//刚好整除
            totalPage = countData / pageSize;
        }else{
            totalPage = countData / pageSize + 1;
        }

        for (int i = 1; i < totalPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setFilter (filterBuilder.toString ());
            oql2.setPaging (new Paging (i, pageSize));
            oql2.setType (CustomerFollow.class);
            oql2.setSelects ("CustomerFollow.*");
            List<CustomerFollow> customerFollowList = serviceCustomerFollow.queryList (oql2);

            for (CustomerFollow item : customerFollowList
                    ) {
                ImNCustomerTaskFoolow nCustomerTaskFoolow = new ImNCustomerTaskFoolow ();//把自增去掉

//                nCustomerTaskFoolow.setId();
//        nCustomerTaskFoolow.setCustomerId(item.getId());
//        nCustomerTaskFoolow.setTaskType(item.getAccountId() > 0 ? TaskCustomerType.OLD : TaskCustomerType.NEW);//全部都是老客户  有订单的是老客户
                nCustomerTaskFoolow.setId (item.getId ());
                nCustomerTaskFoolow.setCustomerId (customer.getId ());
                List<NCustomerTask> listTask = tasks;//直接传值过来
                if (listTask != null && listTask.size () > 0) {

                    nCustomerTaskFoolow.setTaskId (listTask.get (0).getId ());
                }
                QualityInfo qualityInfo = getQualityInfoByCode (customer.getFollowStatus ());//通过旧的顾客的跟进状态获取质量
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
                
                if(StringManager.isNullOrEmpty(item.getCreator ())){
                	item.setCreator("");
                }
                nCustomerTaskFoolow.setCreator (item.getCreator ());
                nCustomerTaskFoolow.setCreateTime (item.getCreateTime ());
                nCustomerTaskFoolow.setUpdatorId (item.getUpdatorId ());
                
                if(StringManager.isNullOrEmpty(item.getUpdator ())){
                	item.setUpdator("");
                }
                nCustomerTaskFoolow.setUpdator (item.getUpdator ());
                
                if(item.getUpdateTime () == null){
                	
                	item.setUpdateTime(item.getCreateTime());
                }
                nCustomerTaskFoolow.setUpdateTime (item.getUpdateTime ());
                nCustomerTaskFoolow.toNew ();
                String sql = String.format ("INSERT INTO n_crm_task_foolow(creator_id,creator,create_time,updator_id,updator,update_time,customer_id,task_id,quality_category,quality_id,quality_progress,next_foolow_time,content,signing_amount,returned_amount) VALUES(%s,'%s','%s',%s,'%s','%s',%s,%s,%s,%s,%s,%s,?,%s,%s); ",
                		
                		nCustomerTaskFoolow.getCreatorId (),
                		nCustomerTaskFoolow.getCreator (), 
                		TimeUtils.getDateFormat (nCustomerTaskFoolow.getCreateTime ()),
                		nCustomerTaskFoolow.getUpdatorId (),
                		nCustomerTaskFoolow.getUpdator (), 
                		nCustomerTaskFoolow.getUpdateTime (), 
                		nCustomerTaskFoolow.getCustomerId (), 
                		nCustomerTaskFoolow.getTaskId (), 
                		nCustomerTaskFoolow.getQualityCategory ().getValue (), 
                		nCustomerTaskFoolow.getQualityId (), 
                		nCustomerTaskFoolow.getQualityProgress ().getValue (), 
                		nCustomerTaskFoolow.getNextFoolowTime (), 
                		nCustomerTaskFoolow.getSigningAmount (), 
                		nCustomerTaskFoolow.getReturnedAmount ());//使用自增id nCustomerTaskFoolow.getContent ().re
                QueryParameters qps = new QueryParameters ();
                qps.add ("@content",nCustomerTaskFoolow.getContent (),Types.VARCHAR);
                Integer numTask = nCustomerService.executeNonQuery (sql, qps);


                listImNCustomerTaskFoolow.add (nCustomerTaskFoolow);
            }


        }
//添加完之后进行保存
        //nCustomerTaskFoolowService.saves (listImNCustomerTaskFoolow);

        return listImNCustomerTaskFoolow;
    }


    /*根据客户id获取相关商机*/
    private List<NCustomerTask> getTasksByCustomerId(Customer item) {

        List<NCustomerTask> list = new ArrayList<> ();
        NCustomerTask nCustomerTask = new NCustomerTask ();
        nCustomerTask.setCustomerId (item.getId ());
        nCustomerTask.setTaskType (item.getAccountId () > 0 ? TaskCustomerType.OLD : TaskCustomerType.NEW);//全部都是老客户  有订单的是老客户
        String productName = "";//产品名称
        ProvinceCityAndCountry provinceCityAndCountry = new ProvinceCityAndCountry ();
        String areaName = "";
        if (item.getProdDetails () != null && item.getProdDetails ().size () > 0) {
            CustomerProdMap customerProdMap = item.getProdDetails ().get (0);//都会有意向产品
            productName = customerProdMap.getProduct ().getName ();
            provinceCityAndCountry = getProvinceCityAndCountry (customerProdMap.getCityId ());//从意向产品中读取
            if (provinceCityAndCountry != null) {

                areaName = provinceCityAndCountry.getAreaName ();//从实体中读取拼接的地区的名称
            }

        }


        if (StringManager.isNullOrEmpty (productName)) {
            productName = "";//无意向产品就是空
        }
        String taskName = "";
        if (StringManager.isNullOrEmpty (productName)) {

            taskName = "";//无意向产品没地区

        } else {
            if (provinceCityAndCountry==null||provinceCityAndCountry.getProvinceId () == 0) {
                taskName = productName;
            } else {
                taskName = String.format ("%s-%s", productName, areaName);

            }


        }


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
        //判断是否订单已经支付，支付的话  为已签单
        //ISoOrderService  soOrderService = ServiceFactory.create (ISoOrderService.class);
        IPersister<SoOrder> pm = PersisterFactory.create ();

        String sql = "SELECT  IFNULL(MAX(paid_price),0)  FROM   so_order  WHERE  paid_price>0  AND  account_id=?";
        QueryParameters qps = new QueryParameters ();

        qps.add ("@account_id", item.getAccountId (), Types.INTEGER);
        int payNum = Convert.toInteger (pm.executeScalar (sql, qps));
        if (payNum > 0) {

            nCustomerTask.setFoolowStatus (CustomerFollowStatus.SIGNED);//已经签订订单
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
        if (item.getConsultWay () != null) {
            nCustomerTask.setConsultWayId (item.getConsultWay ().getValue ());//判空
        }

        nCustomerTask.setConsultWayOther (item.getConsultWayOther ());
        nCustomerTask.setQualityProgress (TaskQualityProgress.INVARIABILITY);
        nCustomerTask.setCreatorId (item.getCreatorId ());
        nCustomerTask.setCreator (item.getCreator ());
        nCustomerTask.setCreateTime (item.getCreateTime ());
        nCustomerTask.setUpdatorId (item.getUpdatorId ());
        nCustomerTask.setUpdator (item.getUpdator ());
        nCustomerTask.setUpdateTime (item.getUpdateTime ());

        nCustomerTask.toNew ();//新增
        nCustomerTask = nCustomerTaskService.save (nCustomerTask);
        getProductsByTask (nCustomerTask);//直接去保存
        //nCustomerTask.setProducts ();//设置意向产品集合

        list.add (nCustomerTask);
        //当客户有分享的时候再添加商机
        ICustomerShareService serviceCustomerShare = ServiceFactory.create (ICustomerShareService.class);
        Oql oql = new Oql ();
        oql.setFilter ("customer_id=" + item.getId ());
        oql.setType (CustomerShare.class);
        oql.setSelects ("CustomerShare.*");
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
            if (payNum > 0) {

                nCustomerTask2.setFoolowStatus (CustomerFollowStatus.SIGNED);//已经签订订单
            }
            nCustomerTask2.toNew ();
            nCustomerTaskService.save (nCustomerTask2);
            list.add (nCustomerTask2);
        }

        //nCustomerTaskService.saves (list);//直接保存然后返回获取数量
        return list;

    }

    /*根据商机获取意向产品赋值 NCustomerProduct  还用CustomerProdMap*/
    private List<NCustomerProduct> getProductsByTask(NCustomerTask nCustomerTask) {
        ICustomerProdMapService serviceCustomerProdMap = ServiceFactory.create (ICustomerProdMapService.class);
//        INCustomerProductService serviceNewCustomerProdMap = ServiceFactory.create (INCustomerProductService.class);//意向产品
        List<NCustomerProduct> nCustomerProdMapList = new ArrayList<> ();
        int totalCountExce = 0;//插入条数
        int pageSize = 100;//每100条进行处理一次
        Oql oql1 = new Oql () {
        };
        oql1.setOrderby (" pkid ");
        oql1.setFilter (" customer_id=" + nCustomerTask.getCustomerId ());//只弄一条
        int countData = serviceCustomerProdMap.queryCount (oql1) / pageSize + 1;
        int totalPage=0;

        if ((countData % pageSize) == 0) {//判断是不是有剩余
            totalPage = countData / pageSize;

        } else {

            totalPage = countData / pageSize + 1;
        }

        for (int i = 1; i < totalPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setFilter (" customer_id=" + nCustomerTask.getCustomerId ());//只弄一条
            oql2.setPaging (new Paging (i, pageSize));
            oql2.setType (CustomerProdMap.class);
            oql2.setSelects ("CustomerProdMap.*");
            List<CustomerProdMap> customerProdMapList = serviceCustomerProdMap.queryList (oql2);

            for (CustomerProdMap item : customerProdMapList
                    ) {
                NCustomerProduct nCustomerProduct = new NCustomerProduct ();
                nCustomerProduct.setTaskId (nCustomerTask.getId ());//商机
                nCustomerProduct.setProductCategoryId1 (1);//分类  产品表对应typeid 二级分类 一级分类从字典表取值
                nCustomerProduct.setProductCategoryId2 (1);
                ProvinceCityAndCountry provinceCityAndCountry = getProvinceCityAndCountry (item.getCityId ());
                if (provinceCityAndCountry != null) {
                    nCustomerProduct.setProvinceId (provinceCityAndCountry.getProvinceId ());

                    nCustomerProduct.setCityId (provinceCityAndCountry.getCityId ());
                    nCustomerProduct.setCountyId (provinceCityAndCountry.getCountryId ());

                }
                //更新下字段  setTaskId
                String sql = String.format ("UPDATE `crm_customer_prod_map` SET `d_province_id` =%s, `d_city_id` = %s, `d_county_id` = %s,  `task_id` = %s WHERE `pkid` = %s ;", nCustomerProduct.getProvinceId (), nCustomerProduct.getCityId (), nCustomerProduct.getCountyId (), nCustomerProduct.getTaskId (), item.getId ());
                nCustomerProductService.executeNonQuery (sql, null);
//                nCustomerProdMapList.add (nCustomerProduct);//通过sql更新

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
        int countData = serviceCustomerProdMap.queryCount (oql1) / pageSize + 1;
        int totalPage = 0;
        if ((countData % pageSize) == 0) {//判断是不是有剩余
            totalPage = countData / pageSize;

        } else {

            totalPage = countData / pageSize + 1;
        }

        for (int i = 1; i < totalPage + 1; i++) {
            Oql oql2 = new Oql () {
            };
            oql2.setOrderby (" pkid ");
            oql2.setPaging (new Paging (i, pageSize));
            oql2.setType (CustomerProdMap.class);
            oql2.setSelects ("CustomerProdMap.*");
            List<CustomerProdMap> customerProdMapList = serviceCustomerProdMap.queryList (oql2);

            for (CustomerProdMap item : customerProdMapList
                    ) {
                NCustomerProduct nCustomerProduct = new NCustomerProduct ();
                nCustomerProduct.setId (item.getId ());
                //nCustomerProduct.setSupplierId(0);//回写
                nCustomerProduct.setCustomerId (item.getCustomerId ());
                //nCustomerProduct.setTaskId();//商机
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
    private ProvinceCityAndCountry getProvinceCityAndCountry(Integer cityId) {
        ProvinceCityAndCountry provinceCityAndCountry = new ProvinceCityAndCountry ();

        if (cityId <= 0) {

            return null;
        }
        IDictService serviceDict = ServiceFactory.create (IDictService.class);
        Dict dict1 = serviceDict.byId (cityId);
        Dict dict2 = new Dict ();
        Dict dict3 = new Dict ();
        if (dict1 == null) {
            return provinceCityAndCountry;

        }
        //分情况 有父级id  无父级Id
        if (dict1.getParentId () > 0) {
            //有父级id：区或者城市
            List<Dict> list2 = serviceDict.byParentId (cityId);
            if (list2 != null && list2.size () > 0) {//城市
                dict2 = list2.get (0);
                provinceCityAndCountry.setCityName (dict1.getName ());
                provinceCityAndCountry.setCityId (dict1.getId ());

                provinceCityAndCountry.setCountryName (dict2.getName ());
                provinceCityAndCountry.setCountryId (dict2.getId ());
//                countryName = dict2.getName ();//县
                List<Dict> list3 = serviceDict.byParentId (dict1.getParentId ());
                if (list3 != null && list3.size () > 0) {
                    dict3 = list3.get (0);
                    // pName = dict3.getName ();//省
                    provinceCityAndCountry.setProvinceName (dict3.getName ());
                    provinceCityAndCountry.setProvinceId (dict3.getId ());
                }

            } else {//区县
                provinceCityAndCountry.setCountryName (dict1.getName ());
                provinceCityAndCountry.setCountryId (dict1.getId ());
                //countryName = dict1.getName ();
                dict2 = serviceDict.byId (dict1.getParentId ());
                if (dict2 != null) {
                    //cityName = dict2.getName ();
                    provinceCityAndCountry.setCityName (dict2.getName ());
                    provinceCityAndCountry.setCityId (dict2.getId ());
                    dict3 = serviceDict.byId (dict2.getParentId ());
                    if (dict3 != null) {
                        //pName = dict3.getName ();
                        provinceCityAndCountry.setProvinceName (dict3.getName ());
                        provinceCityAndCountry.setProvinceId (dict3.getId ());
                    }
                }


            }


        } else {
            //pName = dict1.getName ();
            provinceCityAndCountry.setProvinceName (dict1.getName ());
            provinceCityAndCountry.setProvinceId (dict1.getId ());
            List<Dict> list2 = serviceDict.byParentId (cityId);
            if (list2 != null && list2.size () > 0) {
                dict2 = list2.get (0);
                //cityName = dict2.getName ();
                provinceCityAndCountry.setCityName (dict2.getName ());
                provinceCityAndCountry.setCityId (dict2.getId ());
                List<Dict> list3 = serviceDict.byParentId (dict2.getId ());
                if (list3 != null && list3.size () > 0) {
                    dict3 = list3.get (0);
                    //countryName = dict3.getName ();

                    provinceCityAndCountry.setCountryName (dict3.getName ());
                    provinceCityAndCountry.setCountryId (dict3.getId ());
                }

            }


        }

        provinceCityAndCountry.setAreaName (String.format ("%s-%s-%s", provinceCityAndCountry.getProvinceName (), provinceCityAndCountry.getCityName (), provinceCityAndCountry.getCountryName ()));

        return provinceCityAndCountry;


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
                break;

            case 4012:
                smallCode = "A4";
                break;
            case 4013:
                smallCode = "A2";
                break;
            case 4014:
                smallCode = "S";
                break;
            case 4015:
                smallCode = "D2";
                break;
            case 4016:
                smallCode = "C4";
                break;
//            case 4017:
//                smallCode = "A4";  //不需要管

            case 4020:
                smallCode = "B1";
                break;
            default:
                break;


        }
        qps.add ("@code", smallCode, Types.VARCHAR);
        oql.setParameters (qps);
        oql.setSelects ("NCustomerTaskQuality.*");
        oql.setType (NCustomerTaskQuality.class);
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

//省市县实体
class ProvinceCityAndCountry {

    private int provinceId;
    private String provinceName;
    private int cityId;
    private String cityName;
    private int countryId;
    private String countryName;
    /*拼写的地区名称*/
    private String areaName;


    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
