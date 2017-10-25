package org.netsharp.api.controller.order.manager;

import javax.ws.rs.Path;

/**
 * Created by lianghongpeng on 2016/4/21.
 */

@Path("/api/orderProd/")
public class OrderProductController {
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private SmsService smsService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    private TemplateService templateService;
//
//    @Autowired
//    private EmailService emailService;
//
//    /**
//     * 订单产品 开始操作
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/operation/begin", method = RequestMethod.POST)
//    public ResponseData postOperationBegin(@RequestBody Operation operation, HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int orderProdPkid = Integer.valueOf(SecurityUtils.rc4Decrypt(operation.getOrderProdPkidStr()));
//        int organizationPkid = Integer.valueOf(SecurityUtils.rc4Decrypt(operation.getOrganizationPkidStr()));
//        Integer rs = soOrderProdService.updateBeginOperation(orderProdPkid, operation.getHandleName(), operation.getApplyNo(), organizationPkid, operation.getIsUseSoOrderCompany(), loginUser.getUcUser().getPkid());
//        data.setCode(-1);
//        if (rs == 1) {
//            data.setCode(200);
//            new Thread(){
//                @Override
//                public void run() {
//                    soOrderProdService.saveOrderProd2Er(orderProdPkid, loginUser.getUcUser().getPkid());
//                }
//            }.start();
//        } else if (rs == -1) {
//            data.setMsg("此产品在此地区未设置流程方案，请联系产品管理员");
//        } else if (rs == -2) {
//            data.setMsg("订单未付款，不允许开始操作");
//        } else if (rs == -3) {
//            data.setMsg("请填写办理名称");
//        } else if (rs == -4) {
//            data.setMsg("请填写申请号");
//        }
//        return data;
//    }
//
//    /**
//     * 订单产品 更换操作
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/operation/saveOrderProd2Er")
//    public ResponseData saveOrderProd2Er(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        List<Integer> ids = StringUtils.idsToList(request.getParameter("saveOrderProd2Erid"));
//        for (Integer id : ids) {
//            try {
//                soOrderProdService.saveOrderProd2Er(id, 0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
//
//
//    /**
//     * 订单产品 更换操作
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/operation/change", method = RequestMethod.POST)
//    public ResponseData postOperationChange(@RequestBody Operation operation, HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int orderProdPkid = Integer.valueOf(SecurityUtils.rc4Decrypt(operation.getOrderProdPkidStr()));
//        int organizationPkid = Integer.valueOf(SecurityUtils.rc4Decrypt(operation.getOrganizationPkidStr()));
//        soOrderProdService.updateChangeOperation(orderProdPkid, organizationPkid);
//        return data;
//    }
//
//    /**
//     * 订单产品 退回操作
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "operation/return", method = RequestMethod.POST)
//    public ResponseData postOperationReturn(@RequestBody Operation operation, HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String pkidStr = operation.getOrderProdPkidStr();
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        int userId = loginUser.getUcUser().getPkid();
//        soOrderProdService.updateReturnProduct(pkid, userId);
//        return data;
//    }
//
//    /**
//     * 获取订单池列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/pool/list", method = RequestMethod.GET)
//    public ResponseData getPoolList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int page = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Map<String, Object> map = new HashMap<>();
//        setupParameters(map, request);
//        Pager<OrderProdList> pager = soOrderProdService.pageOrderProdListByProperties(map, Integer.valueOf(page), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping(value = "/pool/assignAll", method = RequestMethod.GET)
//    public ResponseData assignAll(LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        List<String> roleTags = user.getRoleTags();
//        if (roleTags.contains(RoleTag.ROLE_SQKF) || roleTags.contains(RoleTag.ROLE_KFZG)) {
//            soOrderProdService.editAssignAll();
//        }
//        return data;
//    }
//
//    /**
//     * 订单池-订单分配
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/pool/assignApply")
//    public ResponseData assignApply(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderProdPkidStr")));
//        if (orderProdId == 0) {
//            throw new IllegalArgumentException("orderProdId[" + request.getParameter("orderProdPkidStr") + "]");
//        }
//        int userId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("userIdStr")));
//        if (userId == 0) {
//            throw new IllegalArgumentException("userId[" + request.getParameter("userIdStr") + "]");
//        }
//
//        soOrderService.editAssignApply(orderProdId, userId, user.getUcUser().getPkid());
//
//        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//        if (isSend && !user.getRoleTags().contains(RoleTag.ROLE_GLY)) {
//            UcUser assignUser = ucUserService.findById(userId);
//            try {
//                if (null != assignUser && StringUtils.isNotBlank(assignUser.getMobilePhone())) {
//                    soOrderProdService.sendAssignSms(orderProdId, assignUser.getMobilePhone());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            try {
//                if (null != assignUser && StringUtils.isNotBlank(assignUser.getEmail())) {
//                    soOrderProdService.sendAssignEmail(Arrays.asList(orderProdId), assignUser.getEmail());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
//
//    /**
//     * 订单明细列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/detail/list", method = RequestMethod.GET)
//    public ResponseData getOrderProductList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Integer orderPkid = DecryptPkIdStr(request.getParameter("orderPkidStr"));
//        if (orderPkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<OrderProdList> orderProdList = soOrderProdService.findOrderProdListByOrderId(orderPkid);
//        data.setData(orderProdList);
//        return data;
//    }
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        String orderNo = request.getParameter("orderNo");
//        if (!StringUtils.isBlank(orderNo)) {
//            map.put("orderNo", orderNo);
//        }
//        String orderProdNo = request.getParameter("orderProdNo");
//        if (!StringUtils.isBlank(orderProdNo)) {
//            map.put("orderProdNo", orderProdNo);
//        }
//        //是否分配
//        String isAssign = request.getParameter("isAssign");
//        addParameter("isAssign", isAssign, map);
//        //产品名称
//        String productName = request.getParameter("productName");
//        addParameter("productName", productName, map);
//        //城市id
//        String cityIdStr = request.getParameter("cityId");
//        if (StringUtils.isNotBlank(cityIdStr)) {
//            Integer cityId = DecryptPkIdStr(cityIdStr);
//            addParameter("cityId", cityId + "", map);
//        }
//        //订单状态
//        String state = request.getParameter("state");
//        addParameter("state", state, map);
//        //业务员姓名
//        String realName = request.getParameter("realName");
//        addParameter("realName", realName, map);
//        //下单人姓名
//        String accountName = request.getParameter("accountName");
//        addParameter("accountName", accountName, map);
//        //下单人电话
//        String accountMobile = request.getParameter("accountMobile");
//        addParameter("accountMobile", accountMobile, map);
//        //下单时间-开始
//        String beginTime = request.getParameter("beginTime");
//        if (!StringUtils.isBlank(beginTime)) {
//            addParameter("beginTime", beginTime, map);
//        }
//        //下单时间 结束
//        String endTime = request.getParameter("endTime");
//        if (!StringUtils.isBlank(endTime)) {
//            addParameter("endTime", endTime, map);
//        }
//
//        int platformSource = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("platformSource")));
//        if (platformSource > 0) {
//            map.put("platformSource", platformSource);
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            map.put("companyName", companyName);
//        }
//        String applyNo = StringUtils.trimToEmpty(request.getParameter("applyNo"));
//        if (StringUtils.isNotBlank(applyNo)) {
//            map.put("applyNo", applyNo);
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
//        int tab = NumberUtils.toInt(request.getParameter("tab"));
//        if (tab == 3) {
//            map.put("tab", tab);
//        }
//    }
//
//    //对参数进行非空判断，然后添加至map中
//    private void addParameter(String parameterName, String parameter, Map<String, Object> map) {
//        if (!StringUtils.isBlank(parameter)) {
//            map.put(parameterName, parameter);
//        }
//    }
//
//    /**
//     * 解析 pkIdStr 2 pkId
//     *
//     * @param pkIdStr
//     * @return
//     */
//    private Integer DecryptPkIdStr(String pkIdStr) {
//        String str = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkid = Integer.valueOf(str);
//        return pkid;
//    }
}
