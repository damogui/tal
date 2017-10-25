package org.netsharp.api.controller.order.manager;

import javax.ws.rs.Path;

/**
 * 退单审核模块
 * Created by jko on 2016/4/23.
 */
@Path("/api/refundAudit")
public class RefundAuditController {
//
//    @Autowired
//    private SoRefundService soRefundService;
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//    @Autowired
//    private SoRefundItemService soRefundItemService;
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @RequestMapping("/getRefundList")
//    private ResponseData auditList(HttpServletRequest request, LoginUser loginUser, Integer... auditTypes) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"), 1);
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 0);
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        String prodName = StringUtils.trimToEmpty(request.getParameter("prodName"));
//        String state = StringUtils.trimToEmpty(request.getParameter("state"));
//        String type = StringUtils.trimToEmpty(request.getParameter("type"));
//        String isInstallment = StringUtils.trimToEmpty(request.getParameter("isInstallment"));
//        String isInvoice = StringUtils.trimToEmpty(request.getParameter("isInvoice"));
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        String refundStatusId = StringUtils.trimToEmpty(request.getParameter("refundStatusId"));
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        String statusId = StringUtils.trimToEmpty(request.getParameter("statusId"));
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("typeId", 1046);
//        paramMap.put("auditUserId", loginUser.getUcUser().getPkid());
//        if (ArrayUtils.isNotEmpty(auditTypes)) {
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//        if (StringUtils.isNotBlank(no)) {
//            paramMap.put("no", no);
//        }
//        if (StringUtils.isNotBlank(prodName)) {
//            paramMap.put("prodName", prodName);
//        }
//        if (StringUtils.isNotBlank(state)) {
//            paramMap.put("state", state);
//        }
//        if (StringUtils.isNoneBlank(statusId)) {
//            paramMap.put("logStatusId", statusId);
//        }
//        if (StringUtils.isNotBlank(type)) {
//            paramMap.put("type", type);
//        }
//        if (StringUtils.isNotBlank(isInstallment)) {
//            paramMap.put("isInstallment", isInstallment);
//        }
//        if (StringUtils.isNotBlank(isInvoice)) {
//            paramMap.put("isInvoice", isInvoice);
//        }
//        if (StringUtils.isNotBlank(accountName)) {
//            paramMap.put("accountName", accountName);
//        }
//        if (StringUtils.isNotBlank(accountMobile)) {
//            paramMap.put("accountMobile", accountMobile);
//        }
//        if (StringUtils.isNotBlank(refundStatusId)) {
//            paramMap.put("refundStatusId", refundStatusId);
//        }
//        if (StringUtils.isNotBlank(beginTime)) {
//            paramMap.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(endTime)) {
//            paramMap.put("endTime", endTime + " 23:59:59");
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            paramMap.put("companyName", companyName);
//        }
//        String businessName = StringUtils.trimToEmpty(request.getParameter("businessName"));
//        if (StringUtils.isNotBlank(businessName)) {
//            paramMap.put("businessName", businessName);
//        }
//
//        Pager<SoRefund> pager = soRefundService.pageRefundAuditByProperties(paramMap, currentPage, pageSize);
//        if (null == pager) {
//            data.setCode(-1);
//            data.setMsg("Null Pointer");
//        } else {
//            data.setData(pager);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getToAuditList")
//    public ResponseData getToAuditList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.TO_AUDIT);
//    }
//
//    @RequestMapping("/getPassList")
//    public ResponseData getPassList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.TO_AUDIT);
//    }
//
//    @RequestMapping("/getRejectList")
//    public ResponseData getRejectList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/getAuditAllList")
//    public ResponseData getAuditAllList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user,
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    @RequestMapping("/getRefundStatus")
//    public ResponseData getRefundStatus(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> auditNums = bdAuditLogService.getAuditNums(user.getUcUser().getPkid(), 1046);
//        if (null == auditNums) {
//            data.setCode(-1);
//            data.setMsg("Null Pointer");
//        } else {
//            data.setData(auditNums);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getOrderInfo")
//    public ResponseData getOrderInfo(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        SoOrder soOrder = soOrderService.findById(pkid);
//        if (null == soOrder) {
//            data.setCode(-1);
//            data.setMsg("Null Pointer");
//        } else {
//            data.setData(soOrder);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getRefundInfo")
//    public ResponseData getRefund(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        //通过传入ID查询记录
//        int pkId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        /*SoRefund soRefund = soRefundService.findById(1);*/ //测试用例
//        SoRefund soRefund = soRefundService.findById(pkId);
//        if (null == soRefund) {
//            data.setCode(-1);
//            data.setMsg("Null Pointer");
//        } else {
//            data.setData(soRefund);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getRefundProdList")
//    public ResponseData getRefundProdList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData responseData = new ResponseData();
//        int refundId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        List<SoRefundItem> list = soRefundItemService.getListByRefundIds(refundId);
//        if (null == list) {
//            responseData.setCode(-1);
//            responseData.setMsg("Null Pointer");
//        } else {
//            responseData.setData(list);
//        }
//        return responseData;
//    }
//
//    @RequestMapping("/getAuditRecordList")
//    public ResponseData getAuditRecordList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        String pageSize = request.getParameter("pageSize");
//        if (StringUtils.isBlank(pageSize)) {
//            pageSize = "0";
//        }
//        int formId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkIdStr"))));
//        if (formId == 0) {
//            throw new IllegalArgumentException("formId [" + request.getParameter("formId") + "]");
//        }
//
//        int typeId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("typeId"))));
//        if (typeId == 0) {
//            throw new IncompleteArgumentException("typeId [" + request.getParameter("typeId") + "]");
//        }
//        String statusId = StringUtils.trimToEmpty(request.getParameter("statusId"));
//        String addUserId = StringUtils.trimToEmpty(request.getParameter("addUserId"));
//        String level = StringUtils.trimToEmpty(request.getParameter("level"));
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("form_id", 2);
//        properties.put("type_id", 104);
//
//        if (!StringUtils.isBlank(statusId)) {
//            properties.put("status_id", statusId);
//        }
//        if (!StringUtils.isBlank(addUserId)) {
//            properties.put("add_user_id", addUserId);
//        }
//        if (!StringUtils.isBlank(level)) {
//            properties.put("level", level);
//        }
//        //测试用例
//    /* Map<String, Object> properties = new HashMap<>();
//        properties.put("form_id", 2);
//        properties.put("type_id", 104);*/
//        Pager<BdAuditLog> pager = bdAuditLogService.pageByProperties(properties, NumberUtils.toInt(page), NumberUtils.toInt(pageSize));
//        if (null == pager) {
//            data.setCode(-1);
//            data.setMsg("Null Pointer");
//        } else {
//            data.setData(pager);
//            data.setMsg("操作成功");
//        }
//        return data;
//    }
//
//
//    @RequestMapping("/process")
//    public ResponseData getAuditProcess(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1046);
//        if (null == list) {
//            data.setCode(-1);
//            data.setMsg("Null Pointer");
//        } else {
//            data.setData(list);
//        }
//        return data;
//    }
//
//    @RequestMapping("/pass")
//    public ResponseData refundAuditPass(HttpServletResponse response, LoginUser user, @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = (Map<String, Object>) JsonUtils.jsonToObject(json, Map.class);
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(map.get("pkIdStr")));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//        String remark = StringUtils.trimToEmpty(map.get("remark"));
//        try {
//            int rs = soRefundService.editRefundPass(pkid, user.getUcUser().getPkid(), remark, map);
//            if (rs == -1) {
//                data.setMsg("退款单不存在");
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
//    @RequestMapping("/reject")
//    public ResponseData refundAuditNoPass(HttpServletResponse response, LoginUser user, @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = (Map<String, Object>) JsonUtils.jsonToObject(json, Map.class);
//
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(map.get("pkIdStr")));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//        String remark = StringUtils.trimToEmpty(map.get("remark"));
//        try {
//            int rs = soRefundService.editRefundReject(pkid, user.getUcUser().getPkid(), remark, map);
//            if (rs == -1) {
//                data.setMsg("退款单不存在");
//
//            } else if (rs == -2) {
//                data.setMsg("审核任务不存在");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("审核失败");
//        }
//        data.setCode(200);
//        return data;
//    }

}
