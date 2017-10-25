package org.netsharp.api.controller.sys;


import javax.ws.rs.Path;

/**
 * Created by jko on 2016/4/21.
 */
@Path("/api/bdAuditLog")
public class BdAuditLogController {
//
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    /**
//     * 根据ID得到审核日志记录跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
///*        String pkIdStr = request.getParameter("pkIdStr");
//        String idStr = SecurityUtils.rc4Decrypt(pkIdStr);;
//        Integer id = Integer.valueOf(idStr);*/
//        BdAuditLog bdAuditLog = bdAuditLogService.findById(43);
//        data.setData(bdAuditLog);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 根据参数得到列表信息跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping({"/getList"})
//    public ResponseData getList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String formId = StringUtils.trimToEmpty(request.getParameter("formId"));
//        String typeId = StringUtils.trimToEmpty(request.getParameter("typeId"));
//        String statusfId = StringUtils.trimToEmpty(request.getParameter("statusfId"));
//        String addUserId = StringUtils.trimToEmpty(request.getParameter("addUserId"));
//        String level = StringUtils.trimToEmpty(request.getParameter("level"));
//        Map<String, Object> properties = new HashMap<>();
//        if (!StringUtils.isBlank(formId)) {
//            properties.put("form_id", formId);
//        }
//        if (!StringUtils.isBlank(typeId)) {
//            properties.put("type_id", typeId);
//        }
//        if (!StringUtils.isBlank(statusfId)) {
//            properties.put("status_id", statusfId);
//        }
//        if (!StringUtils.isBlank(addUserId)) {
//            properties.put("add_user_id", addUserId);
//        }
//        if (!StringUtils.isBlank(level)) {
//            properties.put("level", level);
//        }
//        List<BdAuditLog> list = bdAuditLogService.listByProperties(properties);
//        data.setData(list);
//        return data;
//    }
//
//    /**
//     * 单条插入跳转
//     *
//     * @param request
//     * @param response
//     * @param bdAuditLog
//     * @return ResponseData
//     */
//    @RequestMapping(value = "/insert")
//    public ResponseData insert(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdAuditLog bdAuditLog, LoginUser user) {
//        ResponseData data = new ResponseData();
//        bdAuditLog.setFormId(2);
//        bdAuditLog.setContent("审批内容");
//        bdAuditLog.setAddUserId(1);
//        bdAuditLog.setRemark("说明");
//        bdAuditLog.setLevel(5);
//        bdAuditLog.setStatusId(105);
//        bdAuditLog.setTypeId(104);
//        bdAuditLogService.insert(bdAuditLog);
//        data.setMsg("操作成功");
//        data.setData(bdAuditLog);
//        return data;
//    }
//
//    /**
//     * 批量插入跳转
//     *
//     * @param request
//     * @param response
//     * @param bdAuditLog
//     * @return ResponseData
//     */
//    @RequestMapping(value = "/batchInsert")
//    public ResponseData batchInsert(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdAuditLog bdAuditLog) {
//        ResponseData data = new ResponseData();
//        List<BdAuditLog> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            BdAuditLog bdAuditLog1 = new BdAuditLog();
//            bdAuditLog1.setFormId(1);
//            bdAuditLog1.setContent("内容");
//            bdAuditLog1.setAddUserId(2);
//            bdAuditLog1.setRemark("2222");
//            bdAuditLog1.setLevel(6);
//            bdAuditLog1.setStatusId(9);
//            bdAuditLog1.setTypeId(15);
//            list.add(bdAuditLog1);
//        }
//        bdAuditLogService.insertBatch(list);
//        data.setMsg("操作成功");
//        data.setData(list);
//        return data;
//    }
//
//    /**
//     * 更新审核日志记录跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping(value = "/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdAuditLog bdAuditLog) {
//        ResponseData data = new ResponseData();
///*        BdAuditLog bdAuditLog = new BdAuditLog();
//        bdAuditLog.setPkid(2);
//        bdAuditLog.setTypeId(105);
//        bdAuditLog.setLevel(10);
//        bdAuditLog.setStatusId(11);
//        bdAuditLog.setRemark("修改说明");
//        bdAuditLog.setAddUserId(3);
//        bdAuditLog.setContent("修改内容");
//        bdAuditLog.setFormId(12);*/
//        bdAuditLogService.update(bdAuditLog);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        bdAuditLogService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update1")
//    public ResponseData update1(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdAuditLog bdAuditLog) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        bdAuditLog.setPkid(pkid);
//        bdAuditLogService.update(bdAuditLog);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        String pageSize = request.getParameter("pageSize");
//        if (StringUtils.isBlank(pageSize)) {
//            pageSize = "0";
//        }
//        String formId = StringUtils.trimToEmpty(request.getParameter("formId"));
//        String typeId = StringUtils.trimToEmpty(request.getParameter("typeId"));
//        String statusId = StringUtils.trimToEmpty(request.getParameter("statusId"));
//        String addUserId = StringUtils.trimToEmpty(request.getParameter("addUserId"));
//        String level = StringUtils.trimToEmpty(request.getParameter("level"));
//        Map<String, Object> properties = new HashMap<>();
//        if (!StringUtils.isBlank(formId)) {
//            properties.put("form_id", formId);
//        }
//        if (!StringUtils.isBlank(typeId)) {
//            properties.put("type_id", typeId);
//        }
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
///*        Map<String, Object> properties = new HashMap<>();
//        properties.put("form_id", 2);
//        properties.put("type_id", 104);*/
//        Pager<BdAuditLog> pager = bdAuditLogService.pageByProperties(properties, NumberUtils.toInt(page), NumberUtils.toInt(pageSize));
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 查各类型审核记录
//     *
//     * @param request
//     * @return ResponseData
//     */
//    @RequestMapping("/list/{typeName:[(dingjia)|(gaijia)|(hetong)|(fapiao)|(shoukuan)|(tuidan)|(fenqi)]+}")
//    public ResponseData auditList(HttpServletRequest request, @PathVariable String typeName) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//
//        int formId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("formIdStr")));
//        if (formId == 0) {
//            formId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("formId")));
//        }
//
//        int typeId = getTypeId(typeName);
//
//        Map<String, Object> properties = new HashMap<>();
//
//        properties.put("status_id", new ArrayList<Integer>() {{
////            审核状态序号，type=105，1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过、1055排队、1056关闭
//            add(1051);
//            add(1052);
//            add(1053);
//            add(1054);
//        }});
//        if (formId > 0) {
//            properties.put("form_id", formId);
//        }
//        if (typeId > 0) {
//            properties.put("type_id", typeId);
//        }
//
//        Pager<BdAuditLog> pager = bdAuditLogService.pageByProperties(properties, currentPage, pageSize);
//
//        data.setData(pager);
//        return data;
//    }
//
//    private int getTypeId(String typeName) {
//        if (StringUtils.isBlank(typeName)) {
//            return 0;
//        }
////        type=104，1041产品定价申请审核、1042订单改价申请审核、1043合同申请审核、1044发票申请审核、1045收款申请审核、1046退单申请审核、1047分期申请审核、1048产品改价申请审核
//        if ("dingjia".equals(typeName)) {
//            return 1041;
//        } else if ("gaijia".equals(typeName)) {
//            return 1042;
//        } else if ("hetong".equals(typeName)) {
//            return 1043;
//        } else if ("fapiao".equals(typeName)) {
//            return 1044;
//        } else if ("shoukuan".equals(typeName)) {
//            return 1045;
//        } else if ("tuidan".equals(typeName)) {
//            return 1046;
//        } else if ("fenqi".equals(typeName)) {
//            return 1047;
//        }
//        return 0;
//    }
}
