package org.netsharp.api.controller.order.supply;


import javax.ws.rs.Path;


/**
 * Created by duan on 2016/4/21.
 */

@Path("/api/supply/order")
public class SupplyOrderController {
//
//    @Autowired
//    private SupplyOrderService supplyOrderService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    private ProdWorkflowNodeService prodWorkflowNodeService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private SupplyProdService supplyProdService;
//
//    @Autowired
//    private UcSupplerAccountService ucSupplerAccountService;
//
//
//    /**
//     * 解析 pkIdStr 2 pkId
//     *
//     * @param pkIdStr
//     * @return
//     */
//    private Integer DecryptPkIdStr(String pkIdStr) {
//        String str = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkid = NumberUtils.toInt(str);
//        return pkid;
//    }
//
//    /***
//     * 供应商订单数
//     */
//    @RequestMapping(value = "/num", method = RequestMethod.GET)
//    public ResponseData num(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> numbs = new HashMap<>();
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
////        List<Integer> ids = ucUserService.getUserPkid(userId);
//        map.put("ids", Arrays.asList(userId));
//        int totalNum = supplyOrderService.getNumByProperties(map);
//        map.put("orderType", 1);
//        int newNum = supplyOrderService.getNumByProperties(map);
//        map.put("orderType", 2);
//        int servingNum = supplyOrderService.getNumByProperties(map);
//        map.put("orderType", 3);
//        int finishNum = supplyOrderService.getNumByProperties(map);
//        map.put("orderType", 4);
//        int endNum = supplyOrderService.getNumByProperties(map);
//        map.put("orderType", 6);
//        int empowermentNum = supplyOrderService.getNumByProperties(map);
//        numbs.put("totalNum", totalNum);
//        numbs.put("newNum", newNum);
//        numbs.put("servingNum", servingNum);
//        numbs.put("finishNum", finishNum);
//        numbs.put("endNum", endNum);
//        numbs.put("empowermentNum", empowermentNum);
//        data.setData(numbs);
//        return data;
//    }
//
//    /***
//     * 供应商订单列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData orderList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
//        //List<Integer> ids = ucUserService.getUserPkid(userId);
//        List<Integer> ids = new ArrayList<>();
//        ids.add(userId);
//        map.put("ids", ids);
//        setupParameters(map, request);
//        Pager<SupplyOrderList> pager = supplyOrderService.pageSupplyOrderListByProperties(map, Integer.valueOf(page), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 供应商订单记录
//     */
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    public ResponseData info(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                             @RequestParam("orderProdIdStr") String orderProdIdStr) {
//        ResponseData data = new ResponseData();
//        int isFileTrace = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("isFileTrace")));
//        Integer orderProdId = DecryptPkIdStr(orderProdIdStr);
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<OrderProdTrace> pager = supplyOrderService.getOrderProdTraceList(orderProdId, isFileTrace == 1, Integer.valueOf(page), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /***
//     * 订单信息
//     */
//    @RequestMapping(value = "orderInfo", method = RequestMethod.GET)
//    public ResponseData orderInfo(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                                  @RequestParam("orderProdIdStr") String orderProdIdStr) {
//        ResponseData data = new ResponseData();
//        Integer orderProdId = DecryptPkIdStr(orderProdIdStr);
//        SupplyOrderInfo supplyOrderInfo = supplyOrderService.getSupplyOrderInfo(orderProdId);
//        data.setData(supplyOrderInfo);
//        return data;
//    }
//
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        //产品ID
//        String productIdStr = request.getParameter("productIdStr");
//        if (StringUtils.isNoneBlank(productIdStr)) {
//            map.put("productId", DecryptPkIdStr(productIdStr));
//        }
//        //公司名称
//        ParameterUtils.addParameter(request, "companyName", String.class, map);
//        //客户名称
//        ParameterUtils.addParameter(request, "customerName", String.class, map);
//        //订单类型
//        ParameterUtils.addParameter(request, "orderType", Integer.class, map);
//        //订单号
//        ParameterUtils.addParameter(request, "no", String.class, map);
//        //pkid
//        ParameterUtils.addParameter(request, "orderId", Integer.class, map);
//    }
//
//    /***
//     * 赋能产品
//     */
//    @RequestMapping(value = "empowerment", method = RequestMethod.GET)
//    public ResponseData empowerment(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                                  @RequestParam("orderProdIdStr") String orderProdIdStr) {
//        ResponseData data = new ResponseData();
//        Integer orderProdId = DecryptPkIdStr(orderProdIdStr);
//
//        Integer userId = loginUser.getUcUser().getPkid();
//
//        Map<String, Object> map = new HashMap<>();
//
//        //当前用户所属的组织机构节点
//        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
//        map.put("ids", ids);
//
//        SoOrderProd orderProd = soOrderProdService.findById(orderProdId);
//        if(orderProd != null) {
//            map.put("productId", orderProd.getProductId());
//            map.put("cityId", orderProd.getCityId());
//        }
//        Pager<DetailProdList> detailProdLists = supplyProdService.getDetailProdList(map, 0, Integer.MAX_VALUE);
//        detailProdLists.getExtend().put("orderProd", orderProd);
//        List<DetailProdList> list = detailProdLists.getList();
//        if(CollectionUtils.isNotEmpty(list)) {
//            int count = 0;
//            for(DetailProdList item : list) {
//                count += NumberUtils.toInt(item.getPlatformPrice());
//            }
//            detailProdLists.getExtend().put("count", count);
//        }
//        data.setData(detailProdLists);
//        return data;
//    }
//
//    /***
//     * 支付赋能产品
//     */
//    @RequestMapping(value = "pay", method = RequestMethod.GET)
//    public ResponseData pay(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                            @RequestParam("orderProdIdStr") String orderProdIdStr, @RequestParam("organizationPkidStr") String organizationPkidStr) {
//        ResponseData data = new ResponseData();
//        Integer orderProdId = DecryptPkIdStr(orderProdIdStr);
//        String price = StringUtils.trimToEmpty(request.getParameter("price"));
//        String addUserId = StringUtils.trimToEmpty(request.getParameter("addUserId"));
//
//        if(NumberUtils.toInt(price, -1) <= 0) {
//            data.setMsg("支付总金额不能为空");
//            data.setCode(-1);
//            return data;
//        }
//
//        Integer userId = loginUser.getUcUser().getPkid();
//        UcSupplerAccount supplerAccount = ucSupplerAccountService.findByUserId(userId);
//        if(supplerAccount == null || NumberUtils.toInt(supplerAccount.getPrice(), -1) <= 0 || NumberUtils.toInt(supplerAccount.getPrice(), -1) < NumberUtils.toInt(price, -1)) {
//            data.setMsg("账户余额不足");
//            data.setCode(-1);
//            return data;
//        }
//
//        //当前用户所属的组织机构节点
//        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
//
//        int result = supplyOrderService.editPay(organizationPkidStr, orderProdId, NumberUtils.toInt(addUserId), NumberUtils.toInt(price), userId, ids);
//        if(result > 0) {
//            data.setMsg("操作成功");
//        } else if (result == -1) {
//            data.setCode(-1);
//            data.setMsg("此产品在此地区未设置流程方案，请联系产品管理员");
//        } else if (result == -2) {
//            data.setCode(-1);
//            data.setMsg("订单未付款，不允许开始操作");
//        } else if (result == -3) {
//            data.setCode(-1);
//            data.setMsg("请填写办理名称");
//        } else if (result == -4) {
//            data.setCode(-1);
//            data.setMsg("请填写申请号");
//        } else {
//            data.setMsg("操作失败");
//            data.setCode(-1);
//        }
//        return data;
//    }

}
