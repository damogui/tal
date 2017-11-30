package com.gongsibao.api.conroller.order.manager;

import javax.ws.rs.Path;

/**
 * 付款
 * Created by wk on 2016/4/27.
 */

@Path("/api/order")
public class PayController {
//
//    @Autowired
//    private SoPayService soPayService;
//
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    @RequestMapping(value = "/pay/list", method = RequestMethod.GET)
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String orderPkIdStr = StringUtils.trimToEmpty(request.getParameter("orderPkidStr"));
//        int orderPkId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderPkIdStr));
//
//        List<SoPay> soPays = soPayService.findByOrderId(orderPkId);
//        if (null == soPays) {
//            soPays = new ArrayList<>();
//        }
//
//        for (SoPay soPay : soPays) {
//            soPay.setOrderPkidStr(SecurityUtils.rc4Encrypt(orderPkId));
//        }
//        data.setData(soPays);
//        return data;
//    }
//
//    @RequestMapping(value = "/pay", method = RequestMethod.POST)
//    public ResponseData insertPay(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        SoPay soPay = new SoPay();
//
//        Map<String, Object> reqMap = JsonUtils.jsonToMap(json, String.class, Object.class);
//
//        soPay.setPayWayTypeId(3102);        // 线下支付
//        soPay.setOfflineWayTypeId(NumberUtils.toInt(reqMap.get("offlineWayTypeId")));
//        soPay.setOfflineInstallmentType(NumberUtils.toInt(reqMap.get("offlineInstallmentTypeId")));
//        soPay.setOfflinePayerName(StringUtils.trimToEmpty(reqMap.get("offlinePayerName")));
//        soPay.setOfflineBankNo(StringUtils.trimToEmpty(reqMap.get("offlineBankNo")));
//        soPay.setAmount(NumberUtils.toInt(reqMap.get("amount")));
//        soPay.setOfflineRemark(StringUtils.trimToEmpty(reqMap.get("offlineRemark")));
//        soPay.setOnlineTradeNo(StringUtils.trimToEmpty(reqMap.get("onlineTradeNo")));
//        soPay.setOfflineAuditStatusId(AuditStatusUtils.TO_AUDIT);
//        soPay.setOfflineAddUserId(loginUser.getUcUser().getPkid());
//
//        if (StringUtils.isBlank(soPay.getOfflinePayerName())) {
//            data.setMsg("请填写付款名称");
//            return data;
//        }
//
//        String orderPkidStr = StringUtils.trimToEmpty(reqMap.get("orderPkidStr"));
//        int orderPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderPkidStr));
//        String vouchers = StringUtils.trimToEmpty(reqMap.get("vouchers"));
//        String[] vouchersArray = vouchers.split("\\|");
//        List<Integer> voucherList = new ArrayList<>();
//        for (String voucher : vouchersArray) {
//            voucherList.add(NumberUtils.toInt(SecurityUtils.rc4Decrypt(voucher)));
//        }
//
//        if (CollectionUtils.isEmpty(voucherList)) {
//            data.setMsg("请上传付款凭证");
//            return data;
//        }
//
//        try {
//            int result = soPayService.insertPay(soPay, orderPkid, voucherList, loginUser.getUcUser().getPkid());
//            if (result == 1) {
//                data.setCode(200);
//                data.setMsg("操作成功");
//            } else if (result == -1) {
//                data.setMsg("提交失败，订单不存在");
//            } else if (result == -2) {
//                data.setMsg("提交失败，有其他收款正在审核");
//            } else if (result == -3) {
//                data.setMsg("提交失败，收款总数超出订单金额");
//            } else if (result == -4) {
//                data.setMsg("提交失败，订单正在分期审核中");
//            } else if (result == -1001) {
//                data.setMsg("提交失败，订单不存在");
//            } else if (result == -1002) {
//                data.setMsg("提交失败，订单正在改价");
//            } else if (result == -2001) {
//                data.setMsg("提交失败，合同不存在");
//            } else if (result == -2002) {
//                data.setMsg("提交失败，合同未审核通过");
//            }
//        } catch (AuditException e) {
//            data.setMsg(e.getMessage());
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "pay/auditflow", method = RequestMethod.GET)
//    public ResponseData auditflow(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("payPkidStr")));
//
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1045);
//        data.setData(list);
//        return data;
//    }
//
//    @RequestMapping(value = "pay/paytype", method = RequestMethod.GET)
//    public ResponseData paytype(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String orderPkidStr = StringUtils.trimToEmpty(request.getParameter("orderPkidStr"));
//        int orderPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderPkidStr));
//
//        List<PayType> payType = soPayService.getPayType(orderPkid);
//        data.setData(payType);
//        return data;
//    }
//
//    @RequestMapping(value = "/pay/info", method = RequestMethod.GET)
//    public ResponseData findPayById(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String payPkidStr = StringUtils.trimToEmpty(request.getParameter("payPkidStr"));
//        int payPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(payPkidStr));
//
//        SoPay soPay = soPayService.findInfoById(payPkid);
//        data.setData(soPay);
//        return data;
//    }
//
//    @RequestMapping(value = "pay/audit", method = RequestMethod.POST)
//    public ResponseData audit(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, @RequestBody Map<String, Object> map) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        int payPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(map.get("payPkidStr")));
//        int auditStatusId = NumberUtils.toInt(map.get("auditStatusId"));
//        String remark = StringUtils.trimToEmpty(map.get("remark"));
//        String confirmTime = StringUtils.trimToEmpty(map.get("confirmTime"));
//
//        try {
//            int rs = soPayService.updateAudit(payPkid, auditStatusId, remark, loginUser.getUcUser().getPkid(),confirmTime);
//            if (rs == -1) {
//                data.setMsg("收款凭证不存在");
//            } else if (rs == -2) {
//                data.setMsg("审核任务不存在");
//            } else {
//                data.setCode(200);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("审核失败");
//        }
//
//        return data;
//    }
//
//    @RequestMapping("/pay/process")
//    public ResponseData process(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("payPkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1045);
//        data.setData(list);
//        return data;
//    }
//
//    @RequestMapping("/auditPay/toaudit/list")
//    public ResponseData toauditList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.TO_AUDIT);
//    }
//
//    @RequestMapping("/auditPay/pass/list")
//    public ResponseData passList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_PASS);
//    }
//
//    @RequestMapping("/auditPay/reject/list")
//    public ResponseData rejectList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/auditPay/all/list")
//    public ResponseData auditAllList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user,
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/auditPay/nums")
//    public ResponseData auditNums(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> auditNums = bdAuditLogService.getAuditNums(user.getUcUser().getPkid(), 1045);
//        data.setData(auditNums);
//        return data;
//    }
//
//    @RequestMapping("/auditPay/toaudit/export")
//    public ResponseData exportToauditList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "收款审核-待审核列表", AuditStatusUtils.TO_AUDIT);
//    }
//
//    @RequestMapping("/auditPay/pass/export")
//    public ResponseData exportPassList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "收款审核-审核通过列表", AuditStatusUtils.AUDIT_PASS);
//    }
//
//    @RequestMapping("/auditPay/reject/export")
//    public ResponseData exportRejectList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "收款审核-审核驳回列表", AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/auditPay/all/export")
//    public ResponseData exportAuditAllList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "收款审核-全部列表",
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    /**
//     * 查询审核列表
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    private ResponseData auditList(HttpServletRequest request, LoginUser user, Integer... auditTypes) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"), 1);
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 0);
//
//        Map<String, Object> paramMap = getRequestParams(request);
//        paramMap.put("typeId", 1045);
//        paramMap.put("auditUserId", user.getUcUser().getPkid());
//
//        if (ArrayUtils.isNotEmpty(auditTypes)) {
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//
//        Pager<PayAudit> pager = soPayService.getAuditList(paramMap, currentPage, pageSize);
//
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 导出审核列表
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    private ResponseData exportAuditList(HttpServletRequest request, HttpServletResponse response, LoginUser user, String fileName, Integer... auditTypes) {
//        ResponseData data = new ResponseData();
//
//        Map<String, Object> paramMap = getRequestParams(request);
//        paramMap.put("typeId", 1045);
//        paramMap.put("auditUserId", user.getUcUser().getPkid());
//
//        if (ArrayUtils.isNotEmpty(auditTypes)) {
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//
//        String filePath = soPayService.exportAuditList(paramMap);
//
//        FileUtils.downLoacl(request, response, filePath, fileName + ".csv");
//        FileUtils.removeLocal(new File(filePath));
//        return data;
//    }
//
//    private Map<String, Object> getRequestParams(HttpServletRequest request) {
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        String businessName = StringUtils.trimToEmpty(request.getParameter("businessName"));
//        String type = StringUtils.trimToEmpty(request.getParameter("type"));
//        int state = NumberUtils.toInt(request.getParameter("state"), -1);
//        int installmentAuditStatusId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("installmentAuditStatusId")), -1);
//
//        Map<String, Object> paramMap = new HashMap<>();
//
//        // 订单编号
//        if (StringUtils.isNotBlank(no)) {
//            paramMap.put("no", no);
//        }
//
//        if (StringUtils.isNotBlank(productName)) {
//            paramMap.put("productName", productName);
//        }
//
//        if (StringUtils.isNotBlank(accountName)) {
//            paramMap.put("accountName", accountName);
//        }
//        if (StringUtils.isNotBlank(accountMobile)) {
//            paramMap.put("accountMobile", accountMobile);
//        }
//        if (StringUtils.isNotBlank(beginTime)) {
//            paramMap.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(endTime)) {
//            paramMap.put("endTime", endTime + " 23:59:59");
//        }
//
//        if (installmentAuditStatusId > -1) {
//            paramMap.put("installmentAuditStatusId", installmentAuditStatusId);
//        }
//
//        if (state > -1) {
//            paramMap.put("state", state);
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            paramMap.put("companyName", companyName);
//        }
//
//        if (StringUtils.isNotBlank(businessName)) {
//            paramMap.put("businessName", businessName);
//        }
//        if (StringUtils.isNotBlank(type)) {
//            paramMap.put("type", type);
//        }
//
//        return paramMap;
//    }
}