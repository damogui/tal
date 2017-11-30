package com.gongsibao.api.conroller.order.manager;

import javax.ws.rs.Path;

/**
 * Created by lianghongpeng on 2016/4/21.
 */

@Path("/api/myOrder")
public class MyOrderController {
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private SoPayService soPayService;
//
//    @Autowired
//    private SoOrderDiscountService soOrderDiscountService;
//
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData getList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        String page = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        Map<String, Object> map = new HashMap<>();
//        setupParameters(map, request);
//        map.put("userId", loginUser.getUcUser().getPkid());
//        map.put("orderProdUserStatus", 3141); // 正在负责
//        Pager<OrderList> pager = soOrderService.pageOrderListByProperties(map, NumberUtils.toInt(page), pageSize);
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 我的订单明细
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//
//    @RequestMapping(value = "/orderProd/list", method = RequestMethod.GET)
//    public ResponseData getOrderProductList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int page = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//
//        Map<String, Object> map = new HashMap<>();
//        setupParameters(map, request);
//        map.put("userMapUserId", loginUser.getUcUser().getPkid()); // 当前用户id
//        map.put("userMapTypeId", 3061); // 类型业务员
//        map.put("userMapStatusId", 3141); // 状态正在负责
//
//        Pager<OrderProdList> pager = soOrderProdService.pageOrderProdListByProperties(map, page, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        if (StringUtils.isNotBlank(no)) {
//            map.put("no", no);
//        }
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderNo"));
//        if (StringUtils.isNotBlank(orderNo)) {
//            map.put("orderNo", orderNo);
//        }
//        String orderProdNo = StringUtils.trimToEmpty(request.getParameter("orderProdNo"));
//        if (StringUtils.isNotBlank(orderProdNo)) {
//            map.put("orderProdNo", orderProdNo);
//        }
//        //产品名称
//        addParameter(request, "productName", String.class, map);
//        //城市id
//        addParameter(request, "cityId", String.class, map);
//        //是否退单
//        addParameter(request, "isRefund", Integer.class, map);
//        //订单状态
//        addParameter(request, "state", Integer.class, map);
//        //订单类型，1订单，2合同
//        addParameter(request, "type", Integer.class, map);
//        //是否分期付款， 1是，2否
//        addParameter(request, "isInstallment", Integer.class, map);
//        //是否开发票
//        addParameter(request, "isInvoice", Integer.class, map);
//        //下单人姓名
//        addParameter(request, "accountName", String.class, map);
//        //下单人电话
//        addParameter(request, "accountMobile", String.class, map);
//        //付款状态
//        addParameter(request, "payStatusId", Integer.class, map);
//        //付款审核状态
//        addParameter(request, "payAuditStatusId", Integer.class, map);
//        //下单时间-开始
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        if (StringUtils.isNotBlank(beginTime)) {
//            map.put("beginTime", beginTime + " 00:00:00");
//        }
//        //下单时间 结束
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        if (StringUtils.isNotBlank(endTime)) {
//            map.put("endTime", endTime + " 23:59:59");
//        }
//
//        //回款时间-开始
//        String payTimeBegin = StringUtils.trimToEmpty(request.getParameter("payTimeBegin"));
//        if (!StringUtils.isBlank(payTimeBegin)) {
//            map.put("payTimeBegin", payTimeBegin + " 00:00:00");
//        }
//        //回款时间-结束
//        String payTimeEnd = StringUtils.trimToEmpty(request.getParameter("payTimeEnd"));
//        if (!StringUtils.isBlank(payTimeEnd)) {
//            map.put("payTimeEnd", payTimeEnd + " 23:59:59");
//        }
//
//        //下单方式
//        addParameter(request, "sourceType", Integer.class, map);
//
//        //订单操作状态
////        addParameter(request, "processStatusId", Integer.class, map);
//        int processStatusId = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("processStatusId")));
//        if (processStatusId > 0) {
//            map.put("processStatusType", processStatusId);
//        }
//
//        int isDelete = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("isDelete")), -1);
//        if (isDelete > -1) {
//            map.put("isDelete", isDelete);
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
//        //跟踪记录
//        String traceBeginTime = StringUtils.trimToEmpty(request.getParameter("tracebegintime"));
//        String traceEndTime = StringUtils.trimToEmpty(request.getParameter("traceendtime"));
//        String traceContent = StringUtils.trimToEmpty(request.getParameter("tracecontent"));
//
//        if (StringUtils.isNoneBlank(traceBeginTime)) {
//            map.put("tracebegintime", traceBeginTime + " 00:00:00");
//        }
//
//        if (StringUtils.isNoneBlank(traceEndTime)) {
//            map.put("traceendtime", traceEndTime + " 23:59:59");
//        }
//
//        if (StringUtils.isNoneBlank(traceContent)) {
//            map.put("tracecontent", traceContent);
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
//
//
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
//    @RequestMapping(value = "/list/pay", method = RequestMethod.GET)
//    public ResponseData getPayAuditList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        Map<String, Object> map = new HashMap<>();
//        setupParameters(map, request);
//        String auditStatusId = StringUtils.trimToEmpty(request.getParameter("auditStatusId"));
//        if (StringUtils.isNotBlank(auditStatusId)) {
//            map.put("payAuditStatusId", auditStatusId);
//        }
//        map.put("offlineAddUserId", loginUser.getUcUser().getPkid());
//        Pager<PayAudit> pager = soPayService.getMyPayList(map, currentPage, pageSize);
//
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/changePrice")
//    public ResponseData getChangePriceByOrderId(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("orderPkidStr"));
//        Integer pkId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(pkIdStr));
//        SoOrder soOrder = soOrderService.findChangePriceById(pkId);
//        if (soOrder != null) {
//            if (soOrder.getIsChangePrice() == 1) {
//                List<SoOrderDiscount> soOrderDiscounts = soOrderDiscountService.findByOrderId(pkId);
//                if (soOrderDiscounts != null) {
//                    Integer discountsPrice = 0;
//                    for (SoOrderDiscount item : soOrderDiscounts) {
//                        discountsPrice += item.getAmount();
//                    }
//
//                    soOrder.setAfterChangePrice(soOrder.getPayablePrice() + discountsPrice);
//                    soOrder.setMargin(soOrder.getTotalPrice() - soOrder.getAfterChangePrice());
//                }
//
//                data.setData(soOrder);
//            }
//        } else {
//            data.setMsg("没有改价记录");
//            data.setCode(200);
//        }
//
//        return data;
//    }
//
//    @RequestMapping("/discount")
//    public ResponseData getDiscountByOrderId(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("orderPkidStr"));
//        Integer pkId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(pkIdStr));
//        List<SoOrderDiscount> soOrderDiscountList = soOrderDiscountService.findByOrderId(pkId);
//
//        data.setData(soOrderDiscountList);
//
//        return data;
//    }
}
