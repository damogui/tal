package com.gongsibao.api.conroller.order.supply;

import javax.ws.rs.Path;

/**
 * Created by duan on 2016/7/3.
 */
@Path("/api/admin/supply/user")
public class SupplyUserAdminController {
//	
//    Log log = LogFactory.getLog(SupplyUserAdminController.class);
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private SupplerService supplerService;
//
//    @Autowired
//    private SupplerAuditLogService supplerAuditLogService;
//
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    public ResponseData info(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        data.setData(supplerService.getSupplerInfo(pkid));
//        return data;
//    }
//
//
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter(" ")));
//
//        Map<String, Object> condition = getParamCondition(request);
//
//        Pager<Suppler> pager = supplerService.listByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//
//    @RequestMapping(value = "/auditList/waiting", method = RequestMethod.GET)
//    public ResponseData waiting(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter(" ")));
//
//        Map<String, Object> condition = getParamCondition(request);
//        condition.put("supplyStatuses", Arrays.asList(2, 5));
//
//        Pager<Suppler> pager = supplerService.listByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//    @RequestMapping(value = "/auditList/pass", method = RequestMethod.GET)
//    public ResponseData listPass(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter(" ")));
//
//        Map<String, Object> condition = getParamCondition(request);
//        condition.put("supplyStatuses", Arrays.asList(4));
//
//        Pager<Suppler> pager = supplerService.listByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//
//    @RequestMapping(value = "/auditList/reject", method = RequestMethod.GET)
//    public ResponseData listReject(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter(" ")));
//
//        Map<String, Object> condition = getParamCondition(request);
//        condition.put("supplyStatuses", Arrays.asList(3, 6));
//
//        Pager<Suppler> pager = supplerService.listByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//
//    @RequestMapping(value = "/audit/pass", method = RequestMethod.POST)
//    public ResponseData pass(LoginUser loginUser, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("pkidStr")));
//        String orgName = StringUtils.trimToEmpty(param.get("orgName"));
//        int orgId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("orgIdStr")));
//        int orgPid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("orgPidStr")));
//
//        String remark = StringUtils.trimToEmpty(param.get("remark"));
//
//        if (pkid == 0) {
//            data.setMsg("参数 pkidStr[" + param.get("pkidStr") + "] error");
//            return data;
//        }
//
//        int rs = supplerService.editPass(pkid, remark, orgId, orgName, orgPid, loginUser.getUcUser().getPkid());
//        if (rs > 0) {
//            data.setCode(200);
//        } else if(rs == -1) {
//            data.setMsg("供应商不存在");
//        } else if(rs == -2) {
//            data.setMsg("请填写组织名称");
//        } else if(rs == -3) {
//            data.setMsg("请选择上级组织");
//        } else if(rs == -11) {
//            data.setMsg("该供应商已审核，请不要重复审核");
//        } else {
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/audit/reject", method = RequestMethod.POST)
//    public ResponseData reject(LoginUser loginUser, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("pkidStr")));
//        String remark = StringUtils.trimToEmpty(param.get("remark"));
//
//        if (pkid == 0) {
//            data.setMsg("参数 pkidStr[" + param.get("pkidStr") + "] error");
//            return data;
//        }
//
//        if (StringUtils.isEmpty(remark)) {
//            data.setMsg("请填写驳回原因");
//            return data;
//        }
//        int rs = supplerService.editReject(pkid, remark, loginUser.getUcUser().getPkid());
//        if (rs > 0) {
//            data.setCode(200);
//        } else if(rs == -11) {
//            data.setMsg("该供应商已审核，请不要重复审核");
//        } else {
//            data.setMsg("审核失败");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/audit/logs", method = RequestMethod.GET)
//    public ResponseData logs(LoginUser loginUser, HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setMsg("参数 pkidStr[" + request.getParameter("pkidStr") + "] error");
//            return data;
//        }
//
//        List<SupplerAuditLog> auditLogs = supplerAuditLogService.findAuditLogs(pkid);
//        data.setData(auditLogs);
//        return data;
//    }
//
//    private Map<String, Object> getParamCondition(HttpServletRequest request) {
//        Map<String, Object> condition = new HashMap<>();
//        int supplyStatus = NumberUtils.toInt(request.getParameter("supplyStatus"), 0);
//        String mobilePhone = StringUtils.trimToEmpty(request.getParameter("mobilePhone"));
//        String landLine = StringUtils.trimToEmpty(request.getParameter("landLine"));
//        String qq = StringUtils.trimToEmpty(request.getParameter("qq"));
//        String weixin = StringUtils.trimToEmpty(request.getParameter("weixin"));
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        String idCard = StringUtils.trimToEmpty(request.getParameter("idCard"));
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        int supplyType = NumberUtils.toInt(request.getParameter("supplyType"));
//        condition.put("supplyType", supplyType == 0 ? 1 : supplyType);
//        if (supplyStatus > 0) {
//            condition.put("supplyStatus", supplyStatus);
//        }
//        if (StringUtils.isNotBlank(mobilePhone)) {
//            condition.put("mobilePhone", mobilePhone);
//        }
//
//        if (StringUtils.isNotBlank(landLine)) {
//            condition.put("landLine", landLine);
//        }
//        if (StringUtils.isNotBlank(qq)) {
//            condition.put("qq", qq);
//        }
//        if (StringUtils.isNotBlank(weixin)) {
//            condition.put("weixin", weixin);
//        }
//        if (StringUtils.isNotBlank(companyName)) {
//            condition.put("companyName", companyName);
//        }
//        if (StringUtils.isNotBlank(idCard)) {
//            condition.put("idCard", idCard);
//        }
//        if (StringUtils.isNotBlank(name)) {
//            condition.put("name", name);
//        }
//        return condition;
//    }
}
