package com.gongsibao.api.conroller.order.manager;

import javax.ws.rs.Path;

/**
 * 退单列表查看模块
 * Created by jko on 2016/4/23.
 */
@Path("/api/refundApplyList")
public class RefundApplyListController {
//
//    @Autowired
//    private SoRefundService soRefundService;
//
//    @Autowired
//    private SoRefundItemService soRefundItemService;
//
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @RequestMapping("/getRefundList")
//    public ResponseData getRefundList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int page = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Map<String, Object> map = new HashMap<>();
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        String prodName = StringUtils.trimToEmpty(request.getParameter("prodName"));
//        String payStatusId = StringUtils.trimToEmpty(request.getParameter("payStatusId"));
//        String processStatusId = StringUtils.trimToEmpty(request.getParameter("processStatusId"));
//        String isInstallment = StringUtils.trimToEmpty(request.getParameter("isInstallment"));
//        String isInvoice = StringUtils.trimToEmpty(request.getParameter("isInvoice"));
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        String refundStatusId = StringUtils.trimToEmpty(request.getParameter("refundStatusId"));
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        String auditStatusId = StringUtils.trimToEmpty(request.getParameter("auditStatusId"));
//        map.put("addUserId", user.getUcUser().getPkid());
//
//        if (!StringUtils.isBlank(no)) {
//            map.put("no", no);
//        }
//        if (!StringUtils.isBlank(prodName)) {
//            map.put("prodName", prodName);
//        }
//        if (!StringUtils.isBlank(auditStatusId)) {
//            map.put("auditStatusId", auditStatusId);
//        }
//        if (!StringUtils.isBlank(payStatusId)) {
//            map.put("payStatusId", payStatusId);
//        }
//        if (!StringUtils.isBlank(processStatusId)) {
//            map.put("processStatusId", processStatusId);
//        }
//        if (!StringUtils.isBlank(isInstallment)) {
//            map.put("isInstallment", isInstallment);
//        }
//        if (!StringUtils.isBlank(isInvoice)) {
//            map.put("isInvoice", isInvoice);
//        }
//        if (!StringUtils.isBlank(accountName)) {
//            map.put("accountName", accountName);
//        }
//        if (!StringUtils.isBlank(refundStatusId)) {
//            map.put("refundStatusId", refundStatusId);
//        }
//        if (!StringUtils.isBlank(accountMobile)) {
//            map.put("accountMobile", accountMobile);
//        }
//        if (!StringUtils.isBlank(beginTime)) {
//            map.put("beginTime", beginTime);
//        }
//        if (!StringUtils.isBlank(endTime)) {
//            map.put("endTime", endTime);
//        }
//        Integer statusId = 1051;
//      /*  List<SoOrder> soOrderList = soRefundService.getRefundViewList(1046, 1051, 2, map); //测试用例*/
//        Pager<SoOrder> pager = soRefundService.getRefundViewList(map, page, pageSize);
//        if (null == pager) {
//            data.setCode(-1);
//            data.setMsg("请求错误");
//        } else {
//            data.setData(pager);
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
//            data.setMsg("请求错误");
//        } else {
//            data.setData(soOrder);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getRefundInfo")
//    public ResponseData getRefund(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        SoRefund soRefund = soRefundService.findById(pkid);
//        if (null == soRefund) {
//            data.setCode(-1);
//            data.setMsg("请求错误");
//        } else {
//            data.setData(soRefund);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getRefundProdList")
//    public ResponseData getRefundProdList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData responseData = new ResponseData();
//        int refundId = org.apache.commons.lang3.math.NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        List<SoRefundItem> list = soRefundItemService.getListByRefundIds(refundId);
//        if (null == list) {
//            responseData.setCode(-1);
//            responseData.setMsg("请求错误");
//        } else {
//            responseData.setData(list);
//        }
//        return responseData;
//    }
//
//    @RequestMapping("/getAuditFlow")
//    public ResponseData getAuditFlow(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));//退单id 【so_refund】
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1046);
//        /*List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(1, 1046);*///测试用例
//        data.setData(list);
//        return data;
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
///*     Map<String, Object> properties = new HashMap<>();
//        properties.put("form_id", 2);
//        properties.put("type_id", 104);*/
//        Pager<BdAuditLog> pager = bdAuditLogService.pageByProperties(properties, NumberUtils.toInt(page), NumberUtils.toInt(pageSize));
//        data.setData(pager);
//        data.setMsg("操作成功");
//        return data;
//    }


}
