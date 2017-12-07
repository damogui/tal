//package com.gongsibao.reporting;
//
//
//import com.gongsibao.entity.trade.OrderProd;
//import com.gongsibao.entity.trade.OrderProdOrganizationMap;
//import com.gongsibao.entity.trade.OrderProdUserMap;
//import org.junit.Test;
//import org.netsharp.core.Oql;
//import org.netsharp.core.Paging;
//import org.netsharp.persistence.IPersister;
//import org.netsharp.persistence.PersisterFactory;
//import org.netsharp.util.StringManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SalesOrderTest {
//
//    @Test
//    public void ttttt(){
//
//        IPersister<OrderProd> pm = PersisterFactory.create();
//
//        Oql oql = new Oql();
//        {
//            oql.setType(OrderProd.class);
//            oql.setSelects("*,soOrder.*,product.*");
//            oql.setPaging(new Paging(1,10));
//            oql.setOrderby("id desc");
//        }
//
//        List<OrderProd> items = pm.queryList(oql);
//
//        for(OrderProd item : items){
////            System.out.println(item.getProductName());
////            System.out.println(item.getPrice());
//            System.out.println(item.getId());
//            System.out.println(item.getProduct().getName());
//        }
//
//    }
//
//    @Test
//    public  void salesTT(){
//
//
//        IPersister<OrderProdUserMap> pm = PersisterFactory.create();
//
//        Oql oql = new Oql();
//        {
//            oql.setType(OrderProdUserMap.class);
//            oql.setSelects("*,user.*,orderProd.*,type.*,status.*,orderProd.soOrder.*");
//            oql.setPaging(new Paging(1,10));
//            oql.setFilter("typeId=3061 and statusId= 3141");
//            oql.setOrderby("id desc");
//        }
//
//        List<OrderProdUserMap> items = pm.queryList(oql);
//
//        for(OrderProdUserMap item : items){
//            System.out.println(item.getUser().getName());
//            System.out.println(item.getStatus().getName());
//            System.out.println(item.getType().getName());
//            System.out.println(item.getOrderProd().getProductName());
//            System.out.println(item.getOrderProd().getSoOrder().getNo());
//        }
//    }
//
//    @Test
//    public  void salesTT3333(){
//
//
//        IPersister<OrderProdOrganizationMap> pm = PersisterFactory.create();
//
//        Oql oql = new Oql();
//        {
//            oql.setType(OrderProdOrganizationMap.class);
//            oql.setSelects("*,organization.*,orderProd.*,orderProd.soOrder.*");
//            oql.setPaging(new Paging(1,10));
////            oql.setFilter("typeId=3061 and statusId= 3141");
//            oql.setOrderby("id desc");
//        }
//
//        List<OrderProdOrganizationMap> items = pm.queryList(oql);
//
//        for(OrderProdOrganizationMap item : items){
//            System.out.println(item.getOrganization().getId());
//            System.out.println(item.getOrganization().getName());
//            System.out.println(item.getOrderProd().getProductName());
//            System.out.println(item.getOrderProd().getSoOrder().getNo());
//        }
//    }
//
//    @Test
//    public void kongxiaomeng(){
//
//        //北分基础133
//        List<Integer> allids = new ArrayList<Integer>();
//        List<Integer> pids = new ArrayList<Integer>();
//        {
//            pids.add(133);
//        }
//        OrganiztionHelper.getOrganizationIds(allids,pids);
//
//        System.out.println(StringManager.join(",",allids));
//
//        //首先查询组织机构所属的人员id
//        //查询组织机构所属的订单id
//        //查询订单列表
//    }
//
//
//}
