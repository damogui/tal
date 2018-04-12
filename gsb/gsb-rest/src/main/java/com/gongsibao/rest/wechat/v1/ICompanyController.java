//package com.gongsibao.rest.wechat;
//
//import com.gongsibao.bd.base.IBdServiceProductService;
//import com.gongsibao.bd.base.IBdServiceService;
//import com.gongsibao.cms.base.IProductService;
//import com.gongsibao.entity.Result;
//import com.gongsibao.entity.bd.BdService;
//import com.gongsibao.entity.bd.BdServiceProduct;
//import com.gongsibao.entity.cms.Product;
//import com.gongsibao.entity.dict.ResponseStatus;
//import org.apache.commons.collections.CollectionUtils;
//import org.netsharp.communication.ServiceFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/wx/icompany/company")
//public class ICompanyController {
//
//
//    /**
//     * 服务列表
//     *
//     * @return
//     */
//    @RequestMapping(value = "/lstService", method = RequestMethod.GET)
//    public Result lstService() {
//        IBdServiceService bdServiceService = ServiceFactory.create(IBdServiceService.class);
//        Result data = new Result();
//        try {
//            List<BdService> list = bdServiceService.findServiceList();
//            if (CollectionUtils.isEmpty(list)) {
//                data.setList(list);
//                return data;
//            }
//            // 记录serviceIds
//            List<Integer> serviceIds = new ArrayList<>();
//            for (BdService bdService : list) {
//                serviceIds.add(bdService.getPkid().intValue());
//            }
//            // 查询商品
//            Map<Integer, List<BdServiceProduct>> serviceProductMap = findProductMapByServiceIds(serviceIds);
//            Iterator<BdService> serviceIterator = list.iterator();
//            while (serviceIterator.hasNext()) {
//                BdService bdService = serviceIterator.next();
//                int serviceId = bdService.getPkid().intValue();
//                List<BdServiceProduct> productList = serviceProductMap.get(serviceId);
//                if (CollectionUtils.isEmpty(productList)) {
//                    serviceIterator.remove();
//                    continue;
//                }
//                bdService.setProductList(productList);
//            }
//            data.setList(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setStatus(ResponseStatus.FAILED);
//            data.setMsg("您的网络不稳定，请稍后再试。");
//        }
//        return data;
//    }
//
//    /**
//     * 调用查询上线产品通过 gsb-cms
//     *
//     * @param serviceIds
//     * @return
//     */
//    public Map<Integer, List<BdServiceProduct>> findProductMapByServiceIds(Collection<Integer> serviceIds) {
//        IBdServiceProductService productService = ServiceFactory.create(IBdServiceProductService.class);
//        IProductService cmsProductService = ServiceFactory.create(IProductService.class);
//        Map<Integer, List<BdServiceProduct>> result = new HashMap<>();
//        if (CollectionUtils.isEmpty(serviceIds)) {
//            return result;
//        }
//
//        List<BdServiceProduct> productList = productService.findByServiceIds(serviceIds);
//        if (CollectionUtils.isEmpty(productList)) {
//            return result;
//        }
//
//        Map<Integer, BdServiceProduct> serviceProductMap = new HashMap<>();
//        for (BdServiceProduct bdServiceProduct : productList) {
//            serviceProductMap.put(bdServiceProduct.getProductId(), bdServiceProduct);
//        }
//        // 查询线上产品id
//        List<Product> cmsProductList = cmsProductService.getOnlineByProductIds(serviceProductMap.keySet());
//
//        if (CollectionUtils.isEmpty(cmsProductList)) {
//            return result;
//        }
//
//        for (Product cmsProduct : cmsProductList) {
//            BdServiceProduct bdServiceProduct = serviceProductMap.get(cmsProduct.getProductId());
//            // 取cms产品名称
//            bdServiceProduct.setName(cmsProduct.getProdName());
//
//            int serviceId = bdServiceProduct.getServiceId();
//            List<BdServiceProduct> list = result.get(serviceId);
//
//            if (null == list) {
//                list = new ArrayList<>();
//                result.put(serviceId, list);
//            }
//            list.add(bdServiceProduct);
//        }
//        return result;
//    }
//}