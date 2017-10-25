package org.netsharp.api.controller.order.manager;

import javax.ws.rs.Path;

/**
 * 退单详情功能模块
 * Created by jko on 2016/4/22.
 */

@Path("/api/orderDetails")
public class OrderDetailsController {
//
//    @Autowired
//    private SoRefundService soRefundService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private SoRefundItemService soRefundItemService;
//
//    @RequestMapping("/getRefundList")
//    public ResponseData getRefundList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
////        int orderId = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("orderId"))); 一行代码能写出各种驴唇不对马嘴
//        int orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        List<SoRefund> detailsList = soRefundService.findDetailsListByIds(orderId);
//        if (null == detailsList) {
//            data.setCode(-1);
//            data.setMsg("请求失败");
//        } else {
//            // 注意 此处配合页面统一管理
//            for (SoRefund soRefund : detailsList) {
//                SoRefundPkid soRefundPkid = new SoRefundPkid();
//                soRefundPkid.setPkid(soRefund.getPkid());
//
//                soRefund.setPkid(soRefund.getOrderId());
//                soRefund.setSoRefund(soRefundPkid);
//            }
//            data.setData(detailsList);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getOrderInfo")
//    public ResponseData getOrderInfo(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = com.gongsibao.common.util.NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        SoOrder soOrder = soOrderService.findById(pkid);
//        if (null == soOrder) {
//            data.setCode(-1);
//            data.setMsg("请求失败");
//        } else {
//            data.setData(soOrder);
//        }
//        return data;
//    }
//
//    @RequestMapping("/getRefund")
//    public ResponseData getRefund(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        SoRefund soRefund = soRefundService.findById(pkid);
//        if (null == soRefund) {
//            data.setCode(-1);
//            data.setMsg("请求失败");
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
//            responseData.setMsg("请求失败");
//        } else {
//            responseData.setData(list);
//        }
//        return responseData;
//    }
//
//    @RequestMapping("/getAuditFlow")
//    public ResponseData getAuditFlow(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = com.gongsibao.common.util.NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1046);
//        if (null == list) {
//            data.setCode(-1);
//            data.setMsg("请求失败");
//        } else {
//            data.setData(list);
//        }
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
//        int formId = com.gongsibao.common.util.NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkIdStr"))));
//        if (formId == 0) {
//            throw new IllegalArgumentException("formId [" + request.getParameter("formId") + "]");
//        }
//
//        int typeId = com.gongsibao.common.util.NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("typeId"))));
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
//        Pager<BdAuditLog> pager = bdAuditLogService.pageByProperties(properties, com.gongsibao.common.util.NumberUtils.toInt(page), com.gongsibao.common.util.NumberUtils.toInt(pageSize));
//        if (null == pager) {
//            data.setCode(-1);
//            data.setMsg("请求失败");
//        } else {
//            data.setData(pager);
//            data.setMsg("操作成功");
//        }
//        return data;
//    }


}
