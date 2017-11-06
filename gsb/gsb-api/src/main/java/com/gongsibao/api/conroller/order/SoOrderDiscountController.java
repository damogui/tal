package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;


@Path("/soorderdiscount")
public class SoOrderDiscountController {
//
//    @Autowired
//    private SoOrderDiscountService soOrderDiscountService;
//
//    @Autowired
//    private BdSyncService bdSyncService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderDiscount soOrderDiscount) {
//        ResponseData data = new ResponseData();
//        soOrderDiscountService.insert(soOrderDiscount);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderDiscountService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderDiscount soOrderDiscount) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderDiscount.setPkid(pkid);
//        soOrderDiscountService.update(soOrderDiscount);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<SoOrderDiscount> pager = soOrderDiscountService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        SoOrderDiscount soOrderDiscount = soOrderDiscountService.findById(pkid);
//        data.setData(soOrderDiscount);
//        return data;
//    }
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
//        String accountId = SecurityUtils.rc4Decrypt(request.getParameter("accountPkidStr"));
//        int pkid = Integer.valueOf(accountId);
//
//        String productIds = request.getParameter("productPkidsStr");
//        String[] productIdList = productIds.split(",");
//        List<String> productGuids = new ArrayList<>();
//        for (String id : productIdList) {
//            int productId = Integer.valueOf(SecurityUtils.rc4Decrypt(id));
//            String productGuid = bdSyncService.findByMPkidAndTableName(productId, "prod_product").getsId();
//            if(StringUtils.isNotBlank(productGuid)) {
//                productGuids.add(productGuid);
//            }
//        }
//
//        String cityIds = request.getParameter("cityPkidStr");
//        String[] cityIdList = cityIds.split(",");
//        List<String> cityGuids = new ArrayList<>();
//        for(String id : cityIdList){
//            int cityId = Integer.valueOf(SecurityUtils.rc4Decrypt(id));
//            String cityGuid = bdSyncService.findByMPkidAndTableName(cityId, "bd_dict").getsId();
//
//            if(StringUtils.isNotBlank(cityGuid)){
//                cityGuids.add(cityGuid);
//            }
//        }
//        String no = request.getParameter("no");
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("accountId", pkid);
//        if (!productGuids.isEmpty()) {
//            paramMap.put("productIds", Arrays.asList(productGuids));
//        }
//        if (!cityGuids.isEmpty()) {
//            paramMap.put("cityIds", Arrays.asList(cityGuids));
//        }
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