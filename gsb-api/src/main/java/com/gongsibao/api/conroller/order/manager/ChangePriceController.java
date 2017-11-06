package com.gongsibao.api.conroller.order.manager;

import javax.ws.rs.Path;

/**
 * Created by wp on 16-4-27.
 */

@Path("/api")
public class ChangePriceController {
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    private ResponseData auditList(HttpServletRequest request, LoginUser loginUser, Integer... auditTypes){
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"), 1);
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 0);
//
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("typeId", 1042);
//        paramMap.put("auditUserId",loginUser.getUcUser().getPkid());
//
//        if(ArrayUtils.isNotEmpty(auditTypes)){
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//
//        if(StringUtils.isNotBlank(productName)){
//            paramMap.put("productName", productName);
//        }
//
//        if(StringUtils.isNotBlank(no)){
//            paramMap.put("no",no);
//        }
//
//        if(StringUtils.isNotBlank(accountName)){
//            paramMap.put("accountName", accountName);
//        }
//
//        if(StringUtils.isNotBlank(accountMobile)){
//            paramMap.put("accountMobile",accountMobile);
//        }
//
//        if(StringUtils.isNotBlank(beginTime)){
//            paramMap.put("beginTime", beginTime + " 00:00:00");
//        }
//
//        if(StringUtils.isNotBlank(endTime)){
//            paramMap.put("endTime", endTime + " 23:59:59");
//        }
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            paramMap.put("companyName", companyName);
//        }
//
//        String businessName = StringUtils.trimToEmpty(request.getParameter("businessName"));
//        if(StringUtils.isNoneBlank(businessName)){
//            paramMap.put("businessName", businessName);
//        }
//
//        Pager<OrderList> pager = soOrderService.listAuditChangePrice(paramMap,currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//    /**
//     * 待审核
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/changePrice/toaudit/list")
//    public ResponseData toauditList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.TO_AUDIT);
//    }
//
//    /**
//     * 审核通过
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/changePrice/pass/list")
//    public ResponseData passList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_PASS);
//    }
//
//    /**
//     * 审核驳回
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/changePrice/reject/list")
//    public ResponseData rejectList(HttpServletRequest request,  LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    /**
//     * 全部审核
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/changePrice/all/list")
//    public ResponseData auditAllList(HttpServletRequest request,  LoginUser user) {
//        return auditList(request, user,
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    /**
//     * 各审核状态数量
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/changePrice/audit/nums")
//    public ResponseData auditNums(HttpServletRequest request,  LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> auditNums = bdAuditLogService.getAuditNums(user.getUcUser().getPkid(), 1042);
//        data.setData(auditNums);
//        return data;
//    }
//
//    /**
//     * 改价订单-浮层
//     * @param request
//     * @return
//     */
//    @RequestMapping("/changePrice/info")
//    public ResponseData changePriceInfo(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        List<SoOrderProdItem> soOrderProdItems = soOrderService.listChangePriceInfo(pkid);
//        data.setData(soOrderProdItems);
//
//        return data;
//    }
//
//    /**
//     * 改价订单-审核通过
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping(value = "/changePrice/audit/pass", method = RequestMethod.POST)
//    public ResponseData auditPass(HttpServletRequest request, LoginUser user,@RequestBody Map<String, Object> param){
//        ResponseData data = new ResponseData();
//
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(param.get("orderPkidStr"))));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//
//        String remark = StringUtils.trimToEmpty(param.get("remark"));
//
//        try {
//            int rs = soOrderService.editChangePricePass(pkid, user.getUcUser().getPkid(), remark);
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
//     * 改价订单-审核不通过
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping(value = "/changePrice/audit/reject", method = RequestMethod.POST)
//    public ResponseData auditReject(HttpServletRequest request, LoginUser user,@RequestBody Map<String, Object> param){
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(param.get("orderPkidStr"))));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//
//        String remark = StringUtils.trimToEmpty(param.get("remark"));
//        try {
//            int rs = soOrderService.editChangePriceReject(pkid, user.getUcUser().getPkid(), remark);
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
//
//    }
//
//    @RequestMapping("/changePrice/audit/process")
//    public ResponseData process(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1042);
//        data.setData(list);
//        return data;
//    }
}
