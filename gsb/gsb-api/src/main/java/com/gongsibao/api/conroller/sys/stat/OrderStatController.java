package com.gongsibao.api.conroller.sys.stat;












//
//  转移到在 ScreenOrderController 类
//













//package com.gongsibao.sys.controllers.stat.order;
//
//import com.gongsibao.common.util.DateUtils;
//import com.gongsibao.common.util.NumberUtils;
//import com.gongsibao.common.util.StringUtils;
//import com.gongsibao.common.util.page.ResponseData;
//import com.gongsibao.module.order.soorder.service.SoOrderService;
//import com.gongsibao.module.order.soorderprod.entity.SoOrderProd;
//import com.gongsibao.module.order.soorderprod.service.SoOrderProdService;
//import com.gongsibao.module.product.prodproduct.entity.ProdProduct;
//import com.gongsibao.module.product.prodproduct.service.ProdProductService;
//import com.gongsibao.module.product.prodworkflownode.service.ProdWorkflowNodeService;
//import com.gongsibao.module.sys.bddict.entity.BdDict;
//import com.gongsibao.module.sys.bddict.service.BdDictService;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.*;
//
///**
// * Created by ys on 2016/3/28.
// */
//@RestController
//@RequestMapping("/stat/order/quantity")
//public class OrderStatController {
//
//    private static Logger log = Logger.getLogger(OrderStatController.class);
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private ProdProductService prodProductService;
//
//    @Autowired
//    private BdDictService bdDictService;
//
//    @Autowired
//    private ProdWorkflowNodeService prodWorkflowNodeService;
//
//    /**
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/day")
//    public ResponseData quantityDay(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        Map<Integer, BdDict> dictMap = bdDictService.findOneLevelMapByType(201);
//        if(MapUtils.isEmpty(dictMap)) {
//            data.setCode(-1);
//            data.setMsg("产品类别为空");
//            return data;
//        }
//        Map<Integer, String> result = new HashMap<>();
//        for(Integer key :  dictMap.keySet()) {
//            if(dictMap.get(key) != null) {
//                result.put(key, dictMap.get(key).getName()+"#0#0");
//            }
//        }
//
//        Date now = new Date();
//        // 查询订单ID集合
//        List<Integer> orderIds = soOrderService.getIdsByTime(DateUtils.dateStr(now) + " 00:00:00", DateUtils.dateStr(now) + " 23:59:59");
//        if(CollectionUtils.isEmpty(orderIds)) {
//            data.setCode(-1);
//            data.setMsg("订单ID为空");
//            data.setData(result.values());
//            return data;
//        }
//        Map<Integer, SoOrderProd> orderProdMap = soOrderProdService.queryMapByOrderIds(orderIds);
//        if(MapUtils.isEmpty(orderProdMap)) {
//            data.setCode(-1);
//            data.setMsg("产品订单集合为空");
//            data.setData(result.values());
//            return data;
//        }
//        Set<Integer> prodIds = new HashSet<>();
//        for(Integer key : orderProdMap.keySet()) {
//            if(orderProdMap.get(key) != null) {
//                prodIds.add(orderProdMap.get(key).getProductId());
//            }
//        }
//        Map<Integer, ProdProduct> prodMap = prodProductService.findMapByIds(new ArrayList<>(prodIds));
//        if(MapUtils.isEmpty(prodMap)) {
//            data.setCode(-1);
//            data.setMsg("产品集合为空");
//            data.setData(result.values());
//            return data;
//        }
//        //产品订单ID集合查询应付金额MAP
//        Map<Integer, Integer> payablePriceMap = soOrderProdService.findPayablePrice(orderProdMap.keySet());
//        if(MapUtils.isEmpty(payablePriceMap)) {
//            data.setCode(-1);
//            data.setMsg("产品订单应付金额集合为空");
//            data.setData(result.values());
//            return data;
//        }
//
//        Integer typeId = null;
//        Set<Integer> typeIds = new HashSet<>();
//        for(Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            if(NumberUtils.toInt(typeId) > 0) {
//                typeIds.add(typeId);
//            }
//        }
//        Map<Integer, Integer> topParentIdMap = bdDictService.findTopParentIdMap(201, typeIds);
//
//        Integer topParentId = null;
//        String value = null;
//        int quantity = 0;
//        int sales = 0;
//        for(Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            topParentId = topParentIdMap.get(typeId);
//            if(NumberUtils.toInt(topParentId) == 0) {
//                continue;
//            }
//
//            value = result.get(topParentId);
//            if(StringUtils.isBlank(value)) {
//                continue;
//            }
//            quantity = NumberUtils.toInt(value.substring(value.indexOf("#")+1,value.lastIndexOf("#")));
//            quantity++;
//            sales = NumberUtils.toInt(value.substring(value.lastIndexOf("#")+1,value.length()));
//            sales += NumberUtils.toInt(payablePriceMap.get(key));
//            value = value.substring(0, value.indexOf("#"))+"#"+quantity+"#"+sales;
//            result.put(topParentId, value);
//        }
//
//        data.setData(sort(result));
//        return data;
//    }
//
//    @RequestMapping("/month")
//    public ResponseData quantityMonth(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        Map<Integer, BdDict> dictMap = bdDictService.findOneLevelMapByType(201);
//        if(MapUtils.isEmpty(dictMap)) {
//            data.setCode(-1);
//            data.setMsg("产品类别为空");
//            return data;
//        }
//        Map<Integer, String> result = new HashMap<>();
//        for(Integer key :  dictMap.keySet()) {
//            if(dictMap.get(key) != null) {
//                result.put(key, dictMap.get(key).getName()+"#0#0");
//            }
//        }
//
//        Date now = new Date();
//        // 查询订单ID集合
//        List<Integer> orderIds = soOrderService.getIdsByTime(DateUtils.dateStr(DateUtils.getMonthBegin(now)) + " 00:00:00", DateUtils.dateStr(DateUtils.getMonthEnd(now)) + " 23:59:59");
//        if(CollectionUtils.isEmpty(orderIds)) {
//            data.setCode(-1);
//            data.setMsg("订单ID为空");
//            data.setData(result.values());
//            return data;
//        }
//        Map<Integer, SoOrderProd> orderProdMap = soOrderProdService.queryMapByOrderIds(orderIds);
//        if(MapUtils.isEmpty(orderProdMap)) {
//            data.setCode(-1);
//            data.setMsg("产品订单集合为空");
//            data.setData(result.values());
//            return data;
//        }
//        Set<Integer> prodIds = new HashSet<>();
//        for(Integer key : orderProdMap.keySet()) {
//            if(orderProdMap.get(key) != null) {
//                prodIds.add(orderProdMap.get(key).getProductId());
//            }
//        }
//        Map<Integer, ProdProduct> prodMap = prodProductService.findMapByIds(new ArrayList<>(prodIds));
//        if(MapUtils.isEmpty(prodMap)) {
//            data.setCode(-1);
//            data.setMsg("产品集合为空");
//            data.setData(result.values());
//            return data;
//        }
//        //产品订单ID集合查询应付金额MAP
//        Map<Integer, Integer> payablePriceMap = soOrderProdService.findPayablePrice(orderProdMap.keySet());
//        if(MapUtils.isEmpty(payablePriceMap)) {
//            data.setCode(-1);
//            data.setMsg("产品订单应付金额集合为空");
//            data.setData(result.values());
//            return data;
//        }
//
//        Integer typeId = null;
//        Set<Integer> typeIds = new HashSet<>();
//        for(Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            if(NumberUtils.toInt(typeId) > 0) {
//                typeIds.add(typeId);
//            }
//        }
//        Map<Integer, Integer> topParentIdMap = bdDictService.findTopParentIdMap(201, typeIds);
//
//        Integer topParentId = null;
//        String value = null;
//        int quantity = 0;
//        int sales = 0;
//        for(Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            topParentId = topParentIdMap.get(typeId);
//            if(NumberUtils.toInt(topParentId) == 0) {
//                continue;
//            }
//
//            value = result.get(topParentId);
//            if(StringUtils.isBlank(value)) {
//                continue;
//            }
//            quantity = NumberUtils.toInt(value.substring(value.indexOf("#")+1,value.lastIndexOf("#")));
//            quantity++;
//            sales = NumberUtils.toInt(value.substring(value.lastIndexOf("#")+1,value.length()));
//            sales += NumberUtils.toInt(payablePriceMap.get(key));
//            value = value.substring(0, value.indexOf("#"))+"#"+quantity+"#"+sales;
//            result.put(topParentId, value);
//        }
//
//        data.setData(sort(result));
//        return data;
//    }
//
//    @RequestMapping("/all")
//    public ResponseData quantityAll(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        Map<Integer, BdDict> dictMap = bdDictService.findOneLevelMapByType(201);
//        if(MapUtils.isEmpty(dictMap)) {
//            data.setCode(-1);
//            data.setMsg("产品类别为空");
//            return data;
//        }
//        Map<Integer, String> result = new HashMap<>();
//        for(Integer key :  dictMap.keySet()) {
//            if(dictMap.get(key) != null) {
//                result.put(key, dictMap.get(key).getName()+"#0#0");
//            }
//        }
//
//        List<Integer> nodeIds = prodWorkflowNodeService.getIdsByProcess(Arrays.asList(2062, 2064));
//        if(CollectionUtils.isEmpty(nodeIds)) {
//            data.setCode(-1);
//            data.setMsg("节点ID集合为空");
//            data.setData(result.values());
//            return data;
//        }
//
//        Map<Integer, SoOrderProd> orderProdMap = soOrderProdService.queryMapByProcessStatusIds(nodeIds);
//        if(MapUtils.isEmpty(orderProdMap)) {
//            data.setCode(-1);
//            data.setMsg("产品订单集合为空");
//            data.setData(result.values());
//            return data;
//        }
//        Set<Integer> prodIds = new HashSet<>();
//        for(Integer key : orderProdMap.keySet()) {
//            if(orderProdMap.get(key) != null) {
//                prodIds.add(orderProdMap.get(key).getProductId());
//            }
//        }
//        Map<Integer, ProdProduct> prodMap = prodProductService.findMapByIds(new ArrayList<>(prodIds));
//        if(MapUtils.isEmpty(prodMap)) {
//            data.setCode(-1);
//            data.setMsg("产品集合为空");
//            data.setData(result.values());
//            return data;
//        }
//
//        Integer typeId = null;
//        Set<Integer> typeIds = new HashSet<>();
//        for(Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            if(NumberUtils.toInt(typeId) > 0) {
//                typeIds.add(typeId);
//            }
//        }
//        Map<Integer, Integer> topParentIdMap = bdDictService.findTopParentIdMap(201, typeIds);
//
//        Integer topParentId = null;
//        String value = null;
//        int quantity = 0;
//        for(Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            topParentId = topParentIdMap.get(typeId);
//            if(NumberUtils.toInt(topParentId) == 0) {
//                continue;
//            }
//
//            value = result.get(topParentId);
//            if(StringUtils.isBlank(value)) {
//                continue;
//            }
//            quantity = NumberUtils.toInt(value.substring(value.indexOf("#")+1,value.lastIndexOf("#")));
//            quantity++;
//            value = value.substring(0, value.indexOf("#"))+"#"+quantity+"#0";
//            result.put(topParentId, value);
//        }
//
//        data.setData(sort(result));
//        return data;
//    }
//
//    private List<String> sort(Map<Integer, String> map) {
//        List<String> result = new ArrayList<>();
//        if(MapUtils.isEmpty(map)) {
//            return result;
//        }
//
//        List<Map.Entry<Integer,String>> list =
//                new ArrayList<>(map.entrySet());
//
//        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
//            public int compare(Map.Entry<Integer, String> o1,
//                               Map.Entry<Integer, String> o2) {
//                return (NumberUtils.toInt(o2.getValue().substring(o2.getValue().indexOf("#")+1,o2.getValue().lastIndexOf("#")))
//                        - NumberUtils.toInt(o1.getValue().substring(o1.getValue().indexOf("#")+1,o1.getValue().lastIndexOf("#"))));
//            }
//        });
//
//        for(Map.Entry<Integer, String> o : list) {
//            result.add(o.getValue());
//            log.info("==========key=========="+o.getKey()+"==========value=========="+o.getValue());
//        }
//        return result;
//    }
//
//
//}
