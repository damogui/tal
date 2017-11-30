package com.gongsibao.api.conroller.order.manager;


import javax.ws.rs.Path;


/**
 * Created by lianghongpeng on 2016/4/21.
 */

@Path("/api/order")
public class OrderController {
//    private static Logger log = Logger.getLogger(OrderController.class);
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private SoContractService soContractService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    private UcAccountService ucAccountService;
//
//    @Autowired
//    private ProdProductService prodProductService;
//
//    @Autowired
//    private ProdServiceService prodServiceService;
//
//    @Autowired
//    private BdDictService bdDictService;
//
//    @Autowired
//    private ProdPriceService prodPriceService;
//
//    @Autowired
//    private SoOrderDiscountService soOrderDiscountService;
//
//    @Autowired
//    private BdSyncService bdSyncService;
//
//    @Autowired
//    private ProdPriceAuditService prodPriceAuditService;
//
//    @Autowired
//    private BdPreferentialCodeService bdPreferentialCodeService;
//
//    @Autowired
//    private BdPreferentialDataMapService bdPreferentialDataMapService;
//    @Autowired
//    private CrmCompanyIntentionService crmCompanyIntentionService;
//
//    private static final int DISCOUNT_TYPE = 3092;
//
//    private static final int PENDING_AUDIT = 1051;
//
//    private static final int BEE_HIVE = 3045;
//
//    private static final int PENDING = 3021;
//
//    private static final int PENDING_PAYMENT = 3011;
//
//    private static final int GONG_SI_BAO = 32101;
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
//     * 全部订单列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
//        List<Integer> ids = ucUserService.getUserPkid(userId);
//        setupParameters(map, request);
//        map.put("myUserIds", ids);
//
//        Pager<OrderList> pager = soOrderService.pageOrderListByProperties(map, Integer.valueOf(page), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /***
//     * 导出excel
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping({"/list/export"})
//    public ResponseData exportOrderList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
////        String page = request.getParameter("currentPage");
////        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
////        if (StringUtils.isBlank(page)) {
////            page = "0";
////        }
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
//        List<Integer> ids = ucUserService.getUserPkid(userId);
//
//        map.put("myUserIds", ids);
//
//        Date begin = null;
//        Date end = null;
//        String beginTime = request.getParameter("beginTime");
//
//        if (!StringUtils.isBlank(beginTime)) {
//            begin = DateUtils.strToDateTime(beginTime + " 00:00:00");
//        }
//
//        //下单时间 结束
//        String endTime = request.getParameter("endTime");
//        if (!StringUtils.isBlank(endTime)) {
//            end = DateUtils.strToDateTime(endTime + " 23:59:59");
//        }
//
//        long rs = DateUtils.checkTimeDis(begin, end, 365);
//
//        if (rs < 0) {
//            data.setCode(-1);
//            data.setMsg("请选择下单开始结束日期，一年以内");
//            return data;
//        }
//
//        setupParameters(map, request);
//        map.put("currentUserId", loginUser.getUcUser().getPkid());
//        String filePath = soOrderService.exportOrderList(map, 0, 0);
//        FileUtils.downLoacl(request, response, filePath, "全部订单.csv");
//        FileUtils.removeLocal(new File(filePath));
//        return data;
//    }
//
//
//    /**
//     * 获取我的 合同 列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/contract/list", method = RequestMethod.GET)
//    public ResponseData getContractList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int page = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        int from = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("from")));
//
//        Map<String, Object> map = new HashMap<>();
//        setupParameters(map, request);
//        if (from != 101) {
//            map.put("userId", loginUser.getUcUser().getPkid());
//        }
//        addParameter(request, "auditStatusId", Integer.class, map);
//
//        String orderPkidStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//        if (StringUtils.isNotBlank(orderPkidStr)) {
//            map.put("orderId", SecurityUtils.rc4Decrypt(orderPkidStr));
//        }
//
//        Pager<ContractList> pager = soContractService.pageContractListByProperties(map, page, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 获取我的改价 列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/priceChange/list", method = RequestMethod.GET)
//    public ResponseData getPriceChangeList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Map<String, Object> map = new HashMap<>();
//        String auditStatusId = request.getParameter("auditStatusId");
//        if (!StringUtils.isBlank(page)) {
//            map.put("changePriceAuditStatusId", auditStatusId);
//        }
//        setupParameters(map, request);
//        map.put("userId", loginUser.getUcUser().getPkid());
//        map.put("isChangePrice", 1);
//        Pager<OrderList> pager = soOrderService.pageOrderListByProperties(map, Integer.valueOf(page), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 获取订单详细
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    public ResponseData getInfo(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Integer pkidStr = DecryptPkIdStr(request.getParameter("pkidStr"));
//        if (pkidStr == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//        OrderList orderList = soOrderService.findOrderListById(pkidStr);
//        data.setData(orderList);
//        return data;
//    }
//
//    /**
//     * 获取订单详细
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/orderProduct/list", method = RequestMethod.GET)
//    public ResponseData getOrderProductList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int page = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Map<String, Object> map = new HashMap<>();
//        if (null != request.getParameter("orderPkidStr")) {
//            Integer orderPkidStr = DecryptPkIdStr(request.getParameter("orderPkidStr"));
//            map.put("orderId", orderPkidStr);
//        }
//        Pager<OrderProdList> pager = soOrderProdService.pageOrderProdListByProperties(map, Integer.valueOf(page), pageSize);
//
//        if (CollectionUtils.isNotEmpty(pager.getList())) {
//            for (OrderProdList orderProdList : pager.getList()) {
//                orderProdList.setOrderProdIdStr(orderProdList.getPkidStr());
//            }
//        }
//
//        data.setData(pager);
//        return data;
//    }
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        String no = request.getParameter("no");
//        if (StringUtils.isNotBlank(no)) {
//            map.put("no", no);
//        }
//        //产品名称
//        addParameter(request, "productName", String.class, map);
//        //订单状态
//        addParameter(request, "state", Integer.class, map);
//        //订单类型，1订单，2合同
//        addParameter(request, "type", Integer.class, map);
//        //是否分期付款， 1是，2否
//        addParameter(request, "isInstallment", Integer.class, map);
//        //业务员姓名
//        addParameter(request, "realName", String.class, map);
//        //是否开发票
//        addParameter(request, "isInvoice", Integer.class, map);
//        //下单人姓名
//        addParameter(request, "accountName", String.class, map);
//        //下单人电话
//        addParameter(request, "accountMobile", String.class, map);
//        //下单时间-开始
//        String beginTime = request.getParameter("beginTime");
//        if (!StringUtils.isBlank(beginTime)) {
//            map.put("beginTime", beginTime + " 00:00:00");
//        }
//        //下单时间 结束
//        String endTime = request.getParameter("endTime");
//        if (!StringUtils.isBlank(endTime)) {
//            map.put("endTime", endTime + " 23:59:59");
//        }
//
//        //回款时间-开始
//        String payTimeBegin = request.getParameter("payTimeBegin");
//        if (!StringUtils.isBlank(payTimeBegin)) {
//            map.put("payTimeBegin", payTimeBegin + " 00:00:00");
//        }
//        //回款时间-结束
//        String payTimeEnd = request.getParameter("payTimeEnd");
//        if (!StringUtils.isBlank(payTimeEnd)) {
//            map.put("payTimeEnd", payTimeEnd + " 23:59:59");
//        }
//
//        //下单方式
//        addParameter(request, "sourceType", Integer.class, map);
//        //是否删除
//        addParameter(request, "isDelete", Integer.class, map);
//
//        int productTypeId = NumberUtils.toInt(request.getParameter("productTypeId"));
//        if (productTypeId > 0) {
//            map.put("productTypeId", productTypeId);
//        }
//
//        int platformSource = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("platformSource")));
//        if (platformSource > 0) {
//            map.put("platformSource", platformSource);
//        }
//
//        String handleName = StringUtils.trimToEmpty(request.getParameter("handleName"));
//        if (StringUtils.isNotBlank(handleName)) {
//            map.put("handleName", handleName);
//        }
//
//        String applyNo = StringUtils.trimToEmpty(request.getParameter("applyNo"));
//        if (StringUtils.isNotBlank(applyNo)) {
//            map.put("applyNo", applyNo);
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            map.put("companyName", companyName);
//        }
//
//        String customerAddUserName = StringUtils.trimToEmpty(request.getParameter("customerAddUserName"));
//        if (StringUtils.isNotBlank(customerAddUserName)) {
//            map.put("customerAddUserName", customerAddUserName);
//        }
//
//        int organizationId = NumberUtils.toInt(request.getParameter("organizationId"));
//        if (organizationId > 0) {
//            map.put("organizationId", organizationId);
//        }
//    }
//
//    //对参数进行非空判断，然后添加至map中
//    private void addParameter(HttpServletRequest request, String parameterName, Class c, Map<String, Object> map) {
//        if (null != request.getParameter(parameterName) && !request.getParameter(parameterName).equals("")) {
//            if (c == Integer.class) {
//                map.put(parameterName, Integer.valueOf(request.getParameter(parameterName)));
//            } else {
//                map.put(parameterName, request.getParameter(parameterName));
//            }
//        }
//    }
//
//    /**
//     * 订单池-订单分配
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/myOrder/assignApply")
//    public ResponseData assignApply(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String[] orderProdIdStrArr = request.getParameter("orderProdPkidStr").split(",");
//
//        List<Integer> orderProdIds = new ArrayList<>();
//        for (String orderIdStr : orderProdIdStrArr) {
//            int orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderIdStr));
//            if (orderId == 0) {
//                continue;
//            }
//            orderProdIds.add(orderId);
//        }
//
//        int userId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("userIdStr")));
//        if (userId == 0) {
//            throw new IllegalArgumentException("userId[" + request.getParameter("userIdStr") + "]");
//        }
//
////        // 当前用户下属业务员ids
////        Integer ucid = user.getUcUser().getPkid();
////        List<Integer> businessUserIds = ucUserService.getUserPkid(ucid, RoleTag.ROLE_YWY);
////        // 检验是否为业务员
////        if (!businessUserIds.contains(userId)) {
////            data.setCode(-1);
////            data.setMsg("分配失败，当前业务员不在您的岗位下");
////            return data;
////        }
//
//        soOrderService.editAssignApply(orderProdIds, userId, user.getUcUser().getPkid());
//
//        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//        if (isSend && !user.getRoleTags().contains(RoleTag.ROLE_GLY)) {
//            UcUser assignUser = ucUserService.findById(userId);
//            try {
//                if (null != assignUser && StringUtils.isNotBlank(assignUser.getEmail())) {
//                    soOrderProdService.sendAssignEmail(orderProdIds, assignUser.getEmail());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            try {
//                if (null != assignUser && StringUtils.isNotBlank(assignUser.getMobilePhone()) && orderProdIds.size() == 1) {
//                    soOrderProdService.sendAssignSms(orderProdIds.get(0), assignUser.getMobilePhone());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public ResponseData delOrder(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String orderIdStr = request.getParameter("orderPkidStr");
//        if (null != orderIdStr) {
//            Integer orderId = DecryptPkIdStr(request.getParameter("orderPkidStr"));
//            SoOrder order = soOrderService.findById(orderId);
//            if (order.getPaidPrice() == 0 && DateUtils.getDistinceDay(order.getAddTime(), new Date()) >= 3) {
//                data.setMsg("操作成功");
//                order.setIsDelete(1);
//                soOrderService.update(order);
//            } else {
//                data.setMsg("状态错误");
//            }
//        } else {
//            data.setMsg("参数错误");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
//    public ResponseData recOrder(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String orderIdStr = request.getParameter("orderPkidStr");
//        if (null != orderIdStr) {
//            Integer orderId = DecryptPkIdStr(request.getParameter("orderPkidStr"));
//            SoOrder order = soOrderService.findById(orderId);
//            if (order.getPaidPrice() == 0 && DateUtils.getDistinceDay(order.getAddTime(), new Date()) >= 3) {
//                data.setMsg("操作成功");
//                order.setIsDelete(0);
//                soOrderService.update(order);
//            } else {
//                data.setMsg("状态错误");
//            }
//        } else {
//            data.setMsg("参数错误");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public ResponseData addOrder(HttpServletRequest request, LoginUser loginUser, @RequestBody String soOrderJson) {
//
//        SoOrder soOrder = JsonUtils.jsonToObject(soOrderJson, SoOrder.class);
//
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        //产品必选项是否选中
////        if (!checkMustServiceItem(soOrder.getProdList())) {
////            data.setCode(200);
////            data.setMsg("必选服务未选中");
////            return data;
////        }
//
//        //通过手机号获取用户信息
//        UcAccount account = ucAccountService.findByMobile(soOrder.getAccountMobile());
//
//        if (account == null) {
//            data.setMsg("客户信息错误");
//            return data;
//        }
//
//        if (soOrder.getSourceTypeId() == null) {
//            data.setMsg("下单来源未知");
//            return data;
//        }
//
//        if (soOrder.getAccountType() == 0) {
//            data.setMsg("新老客户选项不能为空");
//            return data;
//        }
//
//        //总价
//        int totalPrice = 0;
//        Integer payablePrice = 0;
//
//        Date dt = new Date();
//
////        String prodName = "";
//        Map<String, Integer> prodNameMap = new HashMap<>();
//        Map<Integer, Integer> discountIdPriceMap = new HashMap<>();
//        Map<Integer, Integer> productIdDiscountIdMaps = new HashMap<>();
//        List<Integer> allProductIds = new ArrayList<>();
//        List<Integer> allCityIds = new ArrayList<>();
//        List<BdPreferentialCodeWeb> discounts = new ArrayList<>();
//
//        //region 优惠券
//        if (StringUtils.isNotBlank(soOrder.getOrderDiscount())) {
//            discounts = bdPreferentialCodeService.discountList(soOrder.getOrderDiscount());
//            if (CollectionUtils.isEmpty(discounts)) {
//                data.setMsg("所选择的优惠券不可用");
//                return data;
//            }
//
//            for (BdPreferentialCodeWeb item : discounts) {
//                Map<String, Object> params = new HashMap<>();
//                params.put("preferential_id", item.getPreferentialId());
//                params.put("type", 1);
//                List<Integer> dataIds = bdPreferentialDataMapService.getDiscountDataIdsById(params);
//                if (CollectionUtils.isNotEmpty(dataIds)) {
//                    for (Integer productId : dataIds) {
//                        productIdDiscountIdMaps.put(productId, item.getPreferentialId());
//                    }
//                }
//            }
//        }
//
//        //通过产品服务定价ID查找原价
//        if (CollectionUtils.isNotEmpty(soOrder.getProdList())) {
//            for (SoOrderProd soOrderProd : soOrder.getProdList()) {
//                if (soOrderProd.getQuantity() > 200) {
//                    data.setMsg("购买产品数量不能超过200");
//                    return data;
//                }
//
//                //产品总价
//                int prodprice = 0;
//                int price = 0;
//                //查询prod信息
//                for (SoOrderProdItem soOrderProdItem : soOrderProd.getItemList()) {
//                    //查询产品定价相关信息
//                    ProdPrice prodPrice = prodPriceService.findById(Integer.valueOf(SecurityUtils.rc4Decrypt(soOrderProdItem.getPriceIdStr())));
//                    if (prodPrice == null) {
//                        data.setMsg("没有当前定价服务 id:" + soOrderProdItem.getPriceIdStr());
//
//                        return data;
//                    }
//                    prodprice += prodPrice.getPrice();
//                    price += soOrderProdItem.getPrice();
//
//                    soOrderProdItem.setPriceId(prodPrice.getPkid());
//                    //产品服务项
//                    ProdService prodService = prodServiceService.findById(prodPrice.getServiceId());
//                    if (prodService == null) {
//                        data.setMsg("没有当前定价服务下的产品服务 id:" + soOrderProdItem.getPriceIdStr());
//
//                        return data;
//                    }
//
//                    soOrderProdItem.setPriceOriginal(prodPrice.getPrice()); //订单原价
//                    soOrderProdItem.setUnitName(bdDictService.queryDictName(202, prodService.getUnitId()));
//
//                    //特性+服务名称
//                    String propertServicename;
//                    if (prodService.getPropertyId() == 0)
//                        propertServicename = bdDictService.queryDictName(207, prodService.getTypeId());
//                    else
//                        propertServicename = bdDictService.queryDictName(207, prodService.getPropertyId()) + bdDictService.queryDictName(203, prodService.getTypeId());
//                    soOrderProdItem.setServiceName(propertServicename); //产品服务名称
//                    soOrderProdItem.setPrice(soOrderProdItem.getPrice());
//                    soOrderProdItem.setPriceRefund(0);
//                }
//
//                ProdProduct prodProduct = prodProductService.findById(Integer.valueOf(SecurityUtils.rc4Decrypt(soOrderProd.getProductIdStr())));
//
//                if (prodProduct == null) {
//                    data.setMsg("没有产品信息 ID:" + soOrderProd.getProductIdStr());
//                    return data;
//                }
//
//                if (MapUtils.getIntValue(prodNameMap, soOrderProd.getProductName()) == 0) {
//                    prodNameMap.put(soOrderProd.getProductName(), soOrderProd.getQuantity());
//                } else {
//                    prodNameMap.replace(soOrderProd.getProductName(), MapUtils.getIntValue(prodNameMap, soOrderProd.getProductName()) + soOrderProd.getQuantity());
//                }
//
//                //产品名称
////                if (StringUtils.isBlank(prodName))
////                    prodName = prodProduct.getName();
////                else
////                    prodName += "," + prodProduct.getName();
//
//                if(prodprice == 0){
//                    log.info("----------------------- PROD_PRICE == 0 -------------------------");
//                    log.info("----------------------- PROD_PRICE == 0 -------------------------");
//                    log.info("----------------------- PROD_PRICE == 0 -------------------------");
//                }
//
//                soOrderProd.setNo("");
//                soOrderProd.setProductId(prodProduct.getPkid());
//                soOrderProd.setProductName(prodProduct.getName());
//                soOrderProd.setCityId(Integer.valueOf(SecurityUtils.rc4Decrypt(soOrderProd.getCityIdStr())));
//                soOrderProd.setProcessStatusId(0);
//                soOrderProd.setAuditStatusId(PENDING_AUDIT);
//                soOrderProd.setPrice(price);
//                soOrderProd.setIsComplaint(0);
//                soOrderProd.setPriceOriginal(prodprice);
//                soOrderProd.setIsRefund(0);
//                soOrderProd.setProcessedDays(0);
//                soOrderProd.setNeedDays(0);
//                soOrderProd.setTimeoutDays(0);
//                soOrderProd.setIsAssign(0);
//
//                totalPrice += prodprice * soOrderProd.getQuantity();
//                payablePrice += price * soOrderProd.getQuantity();
//
//                allProductIds.add(prodProduct.getPkid());
//                if (!allCityIds.contains(soOrderProd.getCityId())) {
//                    allCityIds.add(soOrderProd.getCityId());
//                }
//                if (MapUtils.isNotEmpty(productIdDiscountIdMaps)) {
//                    int discountId = MapUtils.getIntValue(productIdDiscountIdMaps, soOrderProd.getProductId());
//
//                    if (discountId != 0) {
//                        int disprice = MapUtils.getIntValue(discountIdPriceMap, discountId);
//                        if (MapUtils.getIntValue(discountIdPriceMap, discountId) != 0) {
//                            discountIdPriceMap.replace(discountId, soOrderProd.getPrice() + disprice);
//                        } else {
//                            discountIdPriceMap.put(discountId, soOrderProd.getPrice());
//                        }
//                    }
//                }
//            }
//
//            //是否改价
//            if (totalPrice != payablePrice) {
//                soOrder.setIsChangePrice(1);
//                soOrder.setChangePriceAuditStatusId(PENDING_AUDIT); //改价申请状态
//            } else {
//                soOrder.setIsChangePrice(0);
//                soOrder.setChangePriceAuditStatusId(0); //改价申请状态
//            }
//
//            //订单编号
//            soOrder.setType(1);
//            soOrder.setAccountId(account.getPkid());
//            soOrder.setAccountMobile(account.getMobilePhone());
//            soOrder.setAccountName(account.getRealName());
//            soOrder.setTotalPrice(totalPrice);
//            //region 优惠券
//            if (CollectionUtils.isNotEmpty(discounts)) {
//
//                int preferential = 0;
//                int accountOrderCount = soOrderService.countByAccountId(account.getPkid());
//                List<SoOrderDiscount> soOrderdiscountList = new ArrayList<>();
//                for (BdPreferentialCodeWeb item : discounts) {
//                    int res = soOrderDiscountService.checkDiscount(item, discountIdPriceMap.get(item.getPreferentialId()), account.getPkid(), accountOrderCount, allProductIds, allCityIds, discounts.size(), data, false);
//                    //region .NET
//                    //                    if (item.getIsDisabled() == 0) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]已经被禁用");
////                        return data;
////                    }
////
////                    if (item.getStatus() == IS_USED) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]已经被使用");
////                        return data;
////                    }
////
////                    if (item.getStatus() == UN_ACTIVATE) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]还没有被激活");
////                        return data;
////                    }
////
////                    if (item.getAccountId().compareTo(account.getPkid()) != 0) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]不属于你");
////                        return data;
////                    }
////
////                    if (payablePrice.compareTo(item.getAmountlimit()) == -1) { //满减产品,遍历prodlist  满足优惠券产品的,金额上加
////
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]必须满" + item.getAmountlimit() + "才能使用");
////                        return data;
////                    }
////
////                    if (item.getIsOverlay() == 0 && (discounts.size() > 1 || false)) { //新建订单不存在order_id之前使用过优惠券,条件为false,这里留标记,对以后需求添加逻辑
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]不能叠加使用");
////                        return data;
////                    }
////
////                    if (item.getIsFirstOrderUse() == 1) {
////                        List<SoOrder> soOrderList = soOrderService.findByAccountId(account.getPkid());
////                        if (soOrderList.size() > 0) {
////                            data.setCode(-1);
////                            data.setMsg("优惠券[" + item.getNo() + "]必须首单才能使用");
////                            return data;
////                        }
////                    }
////
////                    if (item.getStartDate().compareTo(dt) == 1) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]的开始日期是" + item.getStartDate().toString() + "还没有到使用期,禁止使用");
////                        return data;
////                    }
////
////                    if (item.getEndDate().compareTo(dt) == -1) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.getNo() + "]的结束日期是" + item.getEndDate().toString() + "已过期,禁止使用");
////                        return data;
////                    }
////
////                    if (CollectionUtils.isNotEmpty(soOrder.getProdList())) {//prod 和 city in 查询
////                        for (SoOrderProd soOrderProd : soOrder.getProdList()) {
////                            Map<String, Object> params = new HashMap<>();
////                            params.put("preferential_id", item.getPreferentialId());
////                            params.put("type", 1);
////                            params.put("data_id", soOrderProd.getProductId());
////
////                            if(bdPreferentialDataMapService.findDiscountByProperties(params) == 0){
////                                data.setMsg("优惠券[" + item.getNo() + "]不支持产品" + soOrderProd.getProductName());
////                                return data;
////                            }
////
////                            params.replace("type", 0);
////                            params.replace("data_id", soOrderProd.getCityId());
////
////                            if(bdPreferentialDataMapService.findDiscountByProperties(params) == 0){
////                                BdDict dict = bdDictService.findById(soOrderProd.getCityId());
////                                data.setMsg("优惠券[" + item.getNo() + "]不支持区域" + dict.getName());
////                                return data;
////                            }
////                        }
////                    }
//                    //endregion
//                    if (res != 1) {
//                        data.setCode(res);
//                        return data;
//                    }
//
//                    SoOrderDiscount soOrderdiscount = new SoOrderDiscount();
//                    preferential += item.getPreferentialAmount();
//
//                    soOrderdiscount.setAddTime(dt);
//                    soOrderdiscount.setPreferentialId(item.getPreferentialId());
//                    soOrderdiscount.setAmount(item.getPreferentialAmount());
//                    soOrderdiscount.setRemark(StringUtils.trimToEmpty(item.getRemark()));
//                    soOrderdiscount.setNo(StringUtils.trimToEmpty(item.getNo()));
//                    soOrderdiscount.setTypeId(DISCOUNT_TYPE);
//                    //soOrderdiscount.setSqlId(StringUtils.trimToEmpty(item.get("BCID")));
//
//                    soOrderdiscountList.add(soOrderdiscount);
//                }
//
//                payablePrice = payablePrice - preferential;
//                soOrder.setSoOrderDiscountList(soOrderdiscountList);
//                //region .NET 优惠券
////                /*List<Map<String, Object>> discounts = soOrderDiscountService.discountList(soOrder.getOrderDiscount().substring(0, soOrder.getOrderDiscount().length() - 1));
////                if (discounts.size() == 0) {
////                    data.setCode(-1);
////                    data.setMsg("没有可用优惠券");
////
////                    return data;
////                }
////                BdSync bdsync = bdSyncService.findByMPkidAndTableName(account.getPkid(), "uc_account");
////                if (bdsync == null) {
////                    data.setCode(-1);
////                    data.setMsg("没有找到对应的客户sqlID");
////
////                    return data;
////                }
////                int preferential = 0;
////                List<SoOrderDiscount> soOrderdiscountList = new ArrayList<>();
////
////                for (Map<String, Object> item : discounts) {
////                    SoOrderDiscount soOrderdiscount = new SoOrderDiscount();
////
////                    if (NumberUtils.toInt(item.get("IsDisabled")) == 1) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]已经被禁用");
////                        return data;
////                    }
////
////                    if (NumberUtils.toInt(item.get("IsUse")) == 1) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]已经被使用");
////                        return data;
////                    }
////
////                    if (!StringUtils.isNotBlank(StringUtils.trimToEmpty(item.get("Account_ID")))) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]还没有被激活");
////                        return data;
////                    }
////
////                    if (!StringUtils.trimToEmpty(item.get("Account_ID")).equals(bdsync.getsId())) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]不属于你");
////                        return data;
////                    }
////
////                    BigDecimal amountLimit = new BigDecimal(item.get("AmountLimit").toString());
////                    if (BigDecimal.valueOf(payablePrice).compareTo(amountLimit) == -1) {
////
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]必须满" + amountLimit + "才能使用");
////                        return data;
////                    }
////
////                    if (NumberUtils.toInt(item.get("OverlayType")) == 0 && (discounts.size() > 1 || false)) { //新建订单不存在order_id之前使用过优惠券,条件为false,这里留标记,对以后需求添加逻辑
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]不能叠加使用");
////                        return data;
////                    }
////
////                    if (NumberUtils.toInt(item.get("FirstOrderUse")) == 1) {
////                        List<SoOrder> soOrderList = soOrderService.findByAccountId(account.getPkid());
////                        if (soOrderList.size() > 0) {
////                            data.setCode(-1);
////                            data.setMsg("优惠券[" + item.get("NO") + "]必须首单才能使用");
////                            return data;
////                        }
////                    }
////
////                    if (DateUtils.strToDate(item.get("StartDate").toString()).compareTo(dt) == 1) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]的开始日期是" + item.get("StartDate").toString() + "还没有到使用期,禁止使用");
////                        return data;
////                    }
////
////                    if (DateUtils.strToDate(item.get("EndDate").toString()).compareTo(dt) == -1) {
////                        data.setCode(-1);
////                        data.setMsg("优惠券[" + item.get("NO") + "]的结束日期是" + item.get("EndDate").toString() + "已过期,禁止使用");
////                        return data;
////                    }
////
////                    if (soOrder.getProdList().size() != 0) {
////                        for (SoOrderProd soOrderProd : soOrder.getProdList()) {
////                            BdSync productGuid = bdSyncService.findByMPkidAndTableName(soOrderProd.getProductId(), "prod_product");
////                            if (productGuid == null) {
////                                data.setCode(-1);
////                                data.setMsg("没有找到对应的产品sqlID");
////
////                                return data;
////                            }
////                            BdSync cityGuid = bdSyncService.findByMPkidAndTableName(soOrderProd.getCityId(), "bd_dict");
////                            if (cityGuid == null) {
////                                data.setCode(-1);
////                                data.setMsg("没有找到对应的城市sqlID");
////
////                                return data;
////                            }
////
////                            if (soOrderDiscountService.findByProductId(productGuid.getsId(), StringUtils.trimToEmpty(item.get("ID"))).size() == 0) {
////                                data.setCode(-1);
////                                data.setMsg("优惠券[" + item.get("NO") + "]不支持产品" + soOrderProd.getProductName());
////                                return data;
////                            }
////
////                            if (soOrderDiscountService.findByCityId(cityGuid.getsId(), StringUtils.trimToEmpty(item.get("ID"))).size() == 0) {
////                                BdDict cityDict = bdDictService.findById(soOrderProd.getCityId());
////                                data.setCode(-1);
////                                data.setMsg("优惠券[" + item.get("NO") + "]不支持区域" + cityDict.getName());
////                                return data;
////                            }
////                        }
////                    }
////
////                    preferential += (int) NumberUtils.toDouble(StringUtils.trimToEmpty(item.get("Preferential"))) * 100;
////
////                    soOrderdiscount.setAddTime(dt);
////                    soOrderdiscount.setAmount((int) NumberUtils.toDouble(StringUtils.trimToEmpty(item.get("Preferential"))) * 100);
////                    soOrderdiscount.setRemark(StringUtils.trimToEmpty(item.get("Remark")));
////                    soOrderdiscount.setNo(StringUtils.trimToEmpty(item.get("NO")));
////                    soOrderdiscount.setTypeId(3029);
////                    soOrderdiscount.setSqlId(StringUtils.trimToEmpty(item.get("BCID")));
////
////                    soOrderdiscountList.add(soOrderdiscount);
////                }
////
////                payablePrice = payablePrice - preferential;
////                soOrder.setSoOrderDiscountList(soOrderdiscountList);*/
//                //endregion
//            }
//            //endregion
//
//            soOrder.setPayablePrice(payablePrice);
//            soOrder.setPaidPrice(0);
//
//            soOrder.setPayStatusId(PENDING_PAYMENT); //待付款
//            soOrder.setProcessStatusId(PENDING); // 待处理
//            soOrder.setRefundStatusId(0); //退款
//            //soOrder.setSourceTypeId(BEE_HIVE); //来源类型 后台
//            soOrder.setIsInstallment(0);  //分期
//            soOrder.setInstallmentMode("");  //分期形式,存储金额
//            soOrder.setInstallmentAuditStatusId(0);
//
//            soOrder.setIsCarryOver(0);
//            soOrder.setIsInvoice(0); //是否开发票
//            soOrder.setDescription("");
//            soOrder.setIsPackage(0);
//            soOrder.setPackageId(0);
//            soOrder.setAddTime(dt);
//            soOrder.setAddUserId(loginUser.getUcUser().getPkid());
//
//            StringBuffer name = new StringBuffer();
//            for (Map.Entry<String, Integer> entry : prodNameMap.entrySet()) {
//                name.append(entry.getKey()).append("*").append(entry.getValue()).append(",");
//            }
//
//            soOrder.setProdName(StringUtils.isNotBlank(name) ? name.substring(0, name.length() - 1) : "");
//            soOrder.setIsDelete(0);
////            soOrder.setPlatformSource(GONG_SI_BAO);
//        } else {
//            data.setMsg("没有添加产品");
//            return data;
//        }
//
//        try {
//            soOrderService.insertOrders(soOrder, loginUser.getUcUser().getPkid());
//            data.setCode(200);
//            data.setData("操作成功");
//        } catch (AuditException e) {
//            data.setMsg(e.getMessage());
//        }
//        return data;
//    }
//
//    public boolean checkMustServiceItem(List<SoOrderProd> list) {
//        for (SoOrderProd soOrderProd : list) {
//            soOrderProd.setCityId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(soOrderProd.getCityIdStr())));
//            soOrderProd.setProductId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(soOrderProd.getProductIdStr())));
//
//            Map<String, Object> propertiesMap1 = new HashMap<>();
//            propertiesMap1.put("product_id", soOrderProd.getProductId());
//            //查询出当前产品下所有服务项
//            List<ProdService> prodServiceList = prodServiceService.listByProperties(propertiesMap1);
//            //创建必选项服务项集合
//            List<ProdPrice> mustServiceItemList = new ArrayList<>();
//            for (ProdService prodService : prodServiceList) {
//                Map<String, Object> propertiesMap2 = new HashMap<>();
//                propertiesMap2.put("service_id", prodService.getPkid());
//                propertiesMap2.put("city_id", soOrderProd.getCityId());
//                propertiesMap2.put("is_must", 1);
//                //查询出当必选服务项且价格且审核状态通过
//                List<ProdPrice> prodPriceList = prodPriceService.listByProperties(propertiesMap2);
//                for (ProdPrice prodPrice : prodPriceList) {
//                    ProdPriceAudit prodPriceAudit = prodPriceAuditService.findById(prodPrice.getPriceAuditId());
//                    if (prodPriceAudit.getAuditStatusId() == 1052) {
//                        mustServiceItemList.add(prodPrice);
//                    }
//                }
//            }
//            if (CollectionUtils.isNotEmpty(mustServiceItemList)) {
//                //有非必选项,遍历价格id开始检查,必选项必须全部选择才为true
//                List<SoOrderProdItem> soOrderProdItemList = soOrderProd.getItemList();
//                //订单中的价格Id集合(包含了必选项及非必项)
//                List<Integer> soOrderProdItemPriceIdList = new ArrayList<>();
//                for (SoOrderProdItem soOrderProdItem : soOrderProdItemList) {
//                    soOrderProdItemPriceIdList.add(soOrderProdItem.getPriceId());
//                }
//                //必选项目中价格Id集合
//                List<Integer> mustServiceItemPriceIdList = new ArrayList<>();
//                for (ProdPrice prodPrice : mustServiceItemList) {
//                    mustServiceItemPriceIdList.add(prodPrice.getPkid());
//                }
//                if (!soOrderProdItemPriceIdList.containsAll(mustServiceItemPriceIdList)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    @RequestMapping("/prodPrice/list")
//    public ResponseData getProdPriceInfo(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        String productIdStr = StringUtils.trimToEmpty(request.getParameter("productIdStr"));
//        if (StringUtils.isBlank(productIdStr)) {
//            data.setMsg("产品ID不能为空");
//            data.setCode(-1);
//            return data;
//        }
//
//        String cityIdStr = StringUtils.trimToEmpty(request.getParameter("cityIdStr"));
//        if (StringUtils.isBlank(cityIdStr)) {
//            data.setMsg("城市ID不能为空");
//            data.setCode(-1);
//            return data;
//        }
//        Integer productId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(productIdStr));
//        Integer cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(cityIdStr));
//        List<ProdPrice> prodPriceList = prodPriceService.findByCityIdAndProductId(cityId, productId);
//        data.setData(prodPriceList);
//
//        return data;
//    }
//
//    @RequestMapping("/cancelOrderCompanyCognate")
//    public ResponseData cancelOrderCompanyCognate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        SoOrder order = soOrderService.findById(pkid);
//        if (order == null) {
//            data.setCode(-1);
//            data.setMsg("该订单不存在");
//            return data;
//        }
//        soOrderService.cancelOrderCompanyCognate(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/addCompanyCognateOrderCustom")
//    private ResponseData addCompanyCognateOrderCustom(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//
//        String companyName = StringUtils.trimToEmpty(mapParam.get("companyName"));
//        if (StringUtils.isBlank(companyName)) {
//            data.setCode(-1);
//            data.setMsg("企业名称不能为空");
//            return data;
//        }
//        List<CrmCompanyIntention> companyList = crmCompanyIntentionService.findByCompanyName(companyName);
//        int companyId = companyList.isEmpty() ? 0 : companyList.get(0).getPkid();
//        Integer rescompanyId = soOrderService.addCompanyCognateOrderCustom(pkid, companyName, companyId, user.getUcUser().getAddUserId());
//        if (rescompanyId.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该订单不存在");
//            return data;
//        }
//        if (rescompanyId.equals(-2)) {
//            data.setCode(-1);
//            data.setMsg("该企业名称已经存在");
//            return data;
//        }
//        if (rescompanyId.equals(-3)) {
//            data.setCode(-1);
//            data.setMsg("该订单已经存在企业信息了");
//            return data;
//        }
//        if (rescompanyId.equals(-4)) {
//            data.setCode(-1);
//            data.setMsg("该企业信息不存在");
//            return data;
//        }
//        Map<String, Object> resMap = new HashMap<>();
//        resMap.put("companyId", SecurityUtils.rc4Encrypt(rescompanyId));
//        data.setMsg("操作成功");
//        data.setData(resMap);
//        return data;
//    }
//
//    @RequestMapping(value = "/editAccountType", method = RequestMethod.POST)
//    public ResponseData editAccountType(@RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        int accountType = NumberUtils.toInt(mapParam.get("accountType"));
//        soOrderService.updateAccountType(pkid, accountType);
//        return data;
//    }

}
