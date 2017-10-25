package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/api/income/settle")
public class ProdIncomeSettleController {
//
//    @Autowired
//    private ProdIncomeSettleService prodIncomeSettleService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private SmsService smsService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Map<String, Object> map = new HashMap<>();
//
//        List<Integer> ids = ucUserService.getUserPkid(loginUser.getUcUser().getPkid());
//        map.put("myUserIds", ids);
//        setParam(request, map);
//
//        Pager<OrderProdList> pager = soOrderProdService.findSettleList(map, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/green")
//    public ResponseData green(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int orderProdId = NumberUtils.toInt(request.getParameter("orderProdId"));
//
//        OrderProdList greenSettle = soOrderProdService.findGreenSettle(orderProdId);
//        if (null == greenSettle) {
//            data.setCode(-1);
//            data.setMsg("未找到您输入的明细号，请确认后重新输入");
//            return data;
//        }
//        greenSettle.setDeleteBtn(1);
//        data.setData(greenSettle);
//        return data;
//    }
//
//    private void setParam(HttpServletRequest request, Map<String, Object> map) {
//        int settleId = NumberUtils.toInt(request.getParameter("settleId"));
//        if (settleId > 0) {
//            map.put("settleId", settleId);
//        }
//
//        String addUserName = StringUtils.trimToEmpty(request.getParameter("addUserName"));
//        if (settleId > 0) {
//            map.put("addUserName", addUserName);
//        }
//
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderNo"));
//        if (StringUtils.isNotBlank(orderNo)) {
//            map.put("orderNo", orderNo);
//        }
//        String orderProdNo = StringUtils.trimToEmpty(request.getParameter("orderProdNo"));
//        if (StringUtils.isNotBlank(orderProdNo)) {
//            map.put("orderProdNo", orderProdNo);
//        }
//
//        String realName = StringUtils.trimToEmpty(request.getParameter("realName"));
//        if (StringUtils.isNotBlank(realName)) {
//            map.put("realName", realName);
//        }
//
//        int organizationId = NumberUtils.toInt(request.getParameter("organizationId"));
//        if (organizationId > 0) {
//            map.put("organizationId", organizationId);
//        }
//
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        if (StringUtils.isNotBlank(accountName)) {
//            map.put("accountName", accountName);
//        }
//
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        if (StringUtils.isNotBlank(accountMobile)) {
//            map.put("accountMobile", accountMobile);
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            map.put("companyName", companyName);
//        }
//
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        if (StringUtils.isNotBlank(productName)) {
//            map.put("productName", productName);
//        }
//
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        if (StringUtils.isNotBlank(beginTime)) {
//            map.put("beginTime", beginTime + " 00:00:00");
//        }
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        if (StringUtils.isNotBlank(endTime)) {
//            map.put("endTime", endTime + " 23:59:59");
//        }
//
//        int auditStatus = NumberUtils.toInt(request.getParameter("auditStatus"), -1);
//        if (auditStatus > -1) {
//            map.put("auditStatus", auditStatus);
//        }
//
//        int settleStatus = NumberUtils.toInt(request.getParameter("settleStatus"), -1);
//        if (settleStatus > -1) {
//            map.put("settleStatus", settleStatus);
//        }
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, @RequestBody String json, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        ProdIncomeSettle settle = JsonUtils.jsonToObject(json, ProdIncomeSettle.class);
//        settle.setAddUserId(loginUser.getUcUser().getPkid());
//
//        List<ProdIncomeSettleOrderprod> prodSettleList = settle.getProdSettleList();
//        if (CollectionUtils.isEmpty(prodSettleList)) {
//            data.setCode(-1);
//            data.setMsg("请先到列表勾选明细订单并填写结算金额");
//            return data;
//        }
//        data = prodIncomeSettleService.insert(settle);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//
//        ProdIncomeSettle settle = prodIncomeSettleService.findInfoById(pkid);
//        data.setData(settle);
//        return data;
//    }

//
//    @RequestMapping("/applyList")
//    public ResponseData applyList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Map<String, Object> map = new HashMap<>();
//        setParam(request, map);
//
//        Pager<ProdIncomeSettle> pager = prodIncomeSettleService.findByCondition(map, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping(value = "/pass", method = RequestMethod.POST)
//    public ResponseData pass(@RequestBody String json, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        ProdSettleAuditParam param = JsonUtils.jsonToObject(json, ProdSettleAuditParam.class);
//
//        param.setAuditStatus(1054);
//        param.setOperatorId(loginUser.getUcUser().getPkid());
//
//        int rows = prodIncomeSettleService.editAudit(param);
//        if (rows > 0) {
//            try {
//                ProdIncomeSettle settle = prodIncomeSettleService.findById(param.getPkid());
//                if (null != settle) {
//                    UcUser ucUser = ucUserService.findById(settle.getAddUserId());
//                    if (null != ucUser && RegexUtils.isPhone(ucUser.getMobilePhone())) {
//                        String smsContent = "您提交的打款申请【" + param.getPkid() + "】已由【" + loginUser.getUcUser().getRealName() + "】审核通过";
//                        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//                        if (isSend) {
//                            smsService.send(2, ucUser.getMobilePhone(), smsContent);
//                        } else {
//                            data.setMsg(smsContent);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/reject", method = RequestMethod.POST)
//    public ResponseData reject(@RequestBody String json, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        ProdSettleAuditParam param = JsonUtils.jsonToObject(json, ProdSettleAuditParam.class);
//
//        param.setAuditStatus(1053);
//        param.setOperatorId(loginUser.getUcUser().getPkid());
//
//        int rows = prodIncomeSettleService.editAudit(param);
//
//        if (rows > 0) {
//            try {
//                ProdIncomeSettle settle = prodIncomeSettleService.findById(param.getPkid());
//                if (null != settle) {
//                    UcUser ucUser = ucUserService.findById(settle.getAddUserId());
//                    if (null != ucUser && RegexUtils.isPhone(ucUser.getMobilePhone())) {
//                        String smsContent = "您提交的打款申请【" + param.getPkid() + "】已由【" + loginUser.getUcUser().getRealName() + "】驳回";
//                        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//                        if (isSend) {
//                            smsService.send(2, ucUser.getMobilePhone(), smsContent);
//                        } else {
//                            data.setMsg(smsContent);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return data;
//    }
//
//
//    @RequestMapping(value = "/bankInfo")
//    public ResponseData bankIn	fo(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        List<Integer> pkids = StringUtils.idsToList(request.getParameter("orderProdIds"));
//        if (CollectionUtils.isEmpty(pkids)) {
//            return data;
//        }
//
//        Map<String, Object> result = soOrderProdService.findSupplyBankInfo(pkids);
//        data.setData(result);
//        return data;
//    }

}