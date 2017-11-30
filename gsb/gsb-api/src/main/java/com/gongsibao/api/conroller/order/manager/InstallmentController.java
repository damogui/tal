package com.gongsibao.api.conroller.order.manager;

import javax.ws.rs.Path;

/**
 * 分期
 * Created by wk on 2016/4/23.
 */
@Path("/api")
public class InstallmentController {
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    @RequestMapping("/myOrder/installment/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"), 1);
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 0);
//
//        Map<String, Object> paramMap = getRequestParams(request);
//        paramMap.put("isInstallment", 1);
//        paramMap.put("userId", user.getUcUser().getPkid());
//        Pager<OrderList> pager = soOrderService.listInstallment(paramMap, currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//    @RequestMapping("/myOrder/installment/export")
//    public ResponseData export(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        Map<String, Object> paramMap = getRequestParams(request);
//        paramMap.put("isInstallment", 1);
//        paramMap.put("currentUserId", user.getUcUser().getPkid());
//        String filePath = soOrderService.exportInstallment(paramMap);
//        FileUtils.downLoacl(request, response, filePath, "我的订单-分期订单列表.csv");
//        FileUtils.removeLocal(new File(filePath));
//
//        return data;
//    }
//
//    @RequestMapping("/myOrder/installment/apply")
//    public ResponseData apply(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//
//        String[] itemArr = StringUtils.trimToEmpty(request.getParameter("installmentItem")).split(",");
//
//        List<Integer> moneys = new ArrayList<>();
//        for (String item : itemArr) {
//            int itemMoney = NumberUtils.doubleRoundInt(item);
//            if (itemMoney == 0) {
//                data.setCode(-1);
//                data.setMsg("不允许分期金额为0元");
//                return data;
//            }
//            moneys.add(itemMoney);
//        }
//
//        if (CollectionUtils.isEmpty(moneys)) {
//            data.setCode(-1);
//            data.setMsg("请填写分期款项");
//            return data;
//        }
//
//        if (moneys.size() == 1) {
//            data.setCode(-1);
//            data.setMsg("分期申请至少填写两期");
//            return data;
//        }
//
//        SoOrder order = new SoOrder();
//        order.setPkid(pkid);
//        order.setIsInstallment(1);
//        order.setInstallmentMode(StringUtils.join(moneys, "|"));
//        order.setInstallmentAuditStatusId(AuditStatusUtils.TO_AUDIT);
//        data.setCode(-1);
//        try {
//            int flag = soOrderService.addInstallment(order, user.getUcUser().getPkid());
//            if (flag == -1) {
//                data.setMsg("订单不存在");
//            } else if (flag == -2) {
//                data.setMsg("合同订单不允许分期");
//            } else if (flag == -3) {
//                data.setMsg("订单已有付款，不允许分期");
//            } else if (flag == -4) {
//                data.setMsg("订单正在审核收款，不允许分期");
//            } else if (flag == -5) {
//                data.setMsg("该订单已经分期");
//            } else if (flag == -6) {
//                data.setMsg("分期金额超出订单金额");
//            } else if (flag == -1002) {
//                data.setMsg("订单正在改价，不允许分期");
//            } else if (flag == 0) {
//                data.setMsg("分期失败");
//            } else {
//                data.setCode(200);
//            }
//        } catch (AuditException e) {
//            data.setMsg(e.getMessage());
//        }
//        return data;
//    }
//
//    @RequestMapping("/auditOrder/installment/process")
//    public ResponseData process(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1047);
//        data.setData(list);
//        return data;
//    }
//
//    @RequestMapping("/auditOrder/installment/toaudit/list")
//    public ResponseData toauditList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.TO_AUDIT);
//    }
//
//    @RequestMapping("/auditOrder/installment/pass/list")
//    public ResponseData passList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_PASS);
//    }
//
//    @RequestMapping("/auditOrder/installment/reject/list")
//    public ResponseData rejectList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/auditOrder/installment/all/list")
//    public ResponseData auditAllList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user,
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/auditOrder/installment/toaudit/export")
//    public ResponseData exportToauditList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "审核订单-待审核列表", AuditStatusUtils.TO_AUDIT);
//    }
//
//    @RequestMapping("/auditOrder/installment/pass/export")
//    public ResponseData exportPassList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "审核订单-审核通过列表", AuditStatusUtils.AUDIT_PASS);
//    }
//
//    @RequestMapping("/auditOrder/installment/reject/export")
//    public ResponseData exportRejectList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "审核订单-审核驳回列表", AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/auditOrder/installment/all/export")
//    public ResponseData exportAuditAllList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        return exportAuditList(request, response, user, "审核订单-全部列表",
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//
//    @RequestMapping("/auditOrder/installment/audit/nums")
//    public ResponseData auditNums(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> auditNums = bdAuditLogService.getAuditNums(user.getUcUser().getPkid(), 1047);
//        data.setData(auditNums);
//        return data;
//    }
//
//    /**
//     * 查询分期审核列表
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
//        paramMap.put("typeId", 1047);
//        paramMap.put("auditUserId", user.getUcUser().getPkid());
//
//        if (ArrayUtils.isNotEmpty(auditTypes)) {
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//
//        Pager<OrderList> pager = soOrderService.listAuditInstallment(paramMap, currentPage, pageSize);
//
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 导出分期审核列表
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    private ResponseData exportAuditList(HttpServletRequest request, HttpServletResponse response, LoginUser user, String fileName, Integer... auditTypes) {
//        ResponseData data = new ResponseData();
//
//        Map<String, Object> paramMap = getRequestParams(request);
//        paramMap.put("typeId", 1047);
//        paramMap.put("auditUserId", user.getUcUser().getPkid());
//
//        if (ArrayUtils.isNotEmpty(auditTypes)) {
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//
//        String filePath = soOrderService.exportAuditInstallment(paramMap);
//
//        FileUtils.downLoacl(request, response, filePath, fileName + ".csv");
//        FileUtils.removeLocal(new File(filePath));
//        return data;
//    }
//
//
//    // 审核
//    @RequestMapping("/auditOrder/installment/audit/pass")
//    public ResponseData auditPass(HttpServletRequest request, LoginUser user, @RequestBody Map<String, Object> passReq) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(passReq.get("orderPkidStr"))));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//        String remark = StringUtils.trimToEmpty(passReq.get("remark"));
//        try {
//            int rs = soOrderService.editInstallmentPass(pkid, user.getUcUser().getPkid(), remark);
//            if (rs == -1) {
//                data.setMsg("订单不存在");
//                return data;
//            } else if (rs == -2) {
//                data.setMsg("审核任务不存在");
//                return data;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("审核失败");
//            return data;
//        }
//
//        data.setCode(200);
//        return data;
//    }
//
//    @RequestMapping("/auditOrder/installment/audit/reject")
//    public ResponseData auditReject(HttpServletRequest request, LoginUser user, @RequestBody Map<String, Object> rejectReq) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(rejectReq.get("orderPkidStr"))));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//
//        String remark = StringUtils.trimToEmpty(rejectReq.get("remark"));
//        try {
//            int rs = soOrderService.editInstallmentReject(pkid, user.getUcUser().getPkid(), remark);
//            if (rs == -1) {
//                data.setMsg("订单不存在");
//
//            } else if (rs == -2) {
//                data.setMsg("审核任务不存在");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("审核失败");
//        }
//
//        data.setCode(200);
//        return data;
//    }
//
//    /**
//     * 获取列表参数
//     *
//     * @param request
//     * @return
//     */
//    private Map<String, Object> getRequestParams(HttpServletRequest request) {
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        int auditStatusId = NumberUtils.toInt(request.getParameter("auditStatusId"), -1);
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
//        if (auditStatusId > -1) {
//            paramMap.put("installmentAuditStatusId", auditStatusId);
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            paramMap.put("companyName", companyName);
//        }
//
//        String businessName = StringUtils.trimToEmpty(request.getParameter("businessName"));
//        if (StringUtils.isNotBlank(businessName)) {
//            paramMap.put("businessName", businessName);
//        }
//
//        return paramMap;
//    }
}
