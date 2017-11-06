package com.gongsibao.api.conroller.order.manager;

/**
 * Created by oupeng on 16/5/17.
 */

import javax.ws.rs.Path;


@Path("/api/orderdiscount")
public class OrderDiscountController {
//
//    @Autowired
//    private SoOrderDiscountService soOrderDiscountService;
//
//    @Autowired
//    private BdSyncService bdSyncService;
//
//    /**
//     * 根据产品,地区,输入编号,查询优惠券
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/code/list")
//    public ResponseData getDiscount(HttpServletRequest request, LoginUser user){
//        ResponseData data = new ResponseData();
////        String accountId = SecurityUtils.rc4Decrypt(request.getParameter("accountPkidStr"));
////        int pkid = Integer.valueOf(accountId);
////        paramMap.put("accountId", pkid);
//        Map<String, Object> paramMap = new HashMap<>();
//
//        String productIds = request.getParameter("productPkidsStr");
//        if(StringUtils.isNotBlank(productIds)){
//            String[] productIdList = productIds.split(",");
//            List<String> productGuids = new ArrayList<>();
//            for (String id : productIdList) {
//                int productId = Integer.valueOf(SecurityUtils.rc4Decrypt(id));
//                String productGuid = bdSyncService.findByMPkidAndTableName(productId, "prod_product").getsId();
//                if(StringUtils.isNotBlank(productGuid)) {
//                    productGuids.add(productGuid);
//                }
//            }
//
//            paramMap.put("productIds", Arrays.asList(productGuids));
//        }
//
//
//        String cityIds = request.getParameter("cityPkidStr");
//        if(StringUtils.isNotBlank(cityIds)){
//            String[] cityIdList = cityIds.split(",");
//            List<String> cityGuids = new ArrayList<>();
//            for(String id : cityIdList){
//                int cityId = Integer.valueOf(SecurityUtils.rc4Decrypt(id));
//                String cityGuid = bdSyncService.findByMPkidAndTableName(cityId, "bd_dict").getsId();
//
//                if(StringUtils.isNotBlank(cityGuid)){
//                    cityGuids.add(cityGuid);
//                }
//            }
//
//            paramMap.put("cityIds", Arrays.asList(cityGuids));
//        }
//
//        String no = request.getParameter("no");
//
//        if (StringUtils.isNotBlank(no)) {
//            paramMap.put("no", no.trim());
//        }
//
//        List<Map<String, Object>> codes = soOrderDiscountService.findByCityIdAndProductIdAndNo(paramMap);
//        data.setData(codes);
//
//        return data;
//    }
}
