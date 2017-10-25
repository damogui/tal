package org.netsharp.api.controller.order.supply;

import javax.ws.rs.Path;

/**
 * Created by ys on 2016/7/3.
 */

@Path("/api/supply/account")
public class SupplyAccountController {
//
//    Log log = LogFactory.getLog(SupplyAccountController.class);
//
//    @Autowired
//    private UcSupplerAccountService ucSupplerAccountService;
//
//    @Autowired
//    private UcSupplerAccountLogService ucSupplerAccountLogService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @RequestMapping(value = "/accountList", method = RequestMethod.GET)
//    public ResponseData accountList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//
//        // 类型 0充值1消费 空为全部
//        String type = StringUtils.trimToEmpty(request.getParameter("type"));
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//        Integer userId = loginUser.getUcUser().getPkid();
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("uc_user_id", userId);
//        if(NumberUtils.toInt(type, -1) >= 0) {
//            condition.put("type", NumberUtils.toInt(type, -1));
//        }
//
//        //当前用户所属的组织机构节点
////        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
////        condition.put("ids", ids);
//
//        Pager<UcSupplerAccountLog> pager = ucSupplerAccountLogService.pageByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        UcSupplerAccount ucSupplerAccount = ucSupplerAccountService.findByUserId(userId);
//        if(ucSupplerAccount == null) {
//            pager.getExtend().put("balance", 0);
//        } else {
//            pager.getExtend().put("balance", NumberUtils.toInt(ucSupplerAccount.getPrice()));
//        }
//
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData saveInfo(@RequestBody Map<String, Object> param, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setCode(500);
//
//        int ucUserId = loginUser.getUcUser().getPkid();
//
//        if (ucUserId == 0) {
//            data.setMsg("参数缺少ucUserIdStr");
//            return data;
//        }
//
//        String price = StringUtils.trimToEmpty(param.get("price"));
//        String accountName = StringUtils.trimToEmpty(param.get("accountName"));
//        String accountNo = StringUtils.trimToEmpty(param.get("accountNo"));
//        String remark = StringUtils.trimToEmpty(param.get("remark"));
//        String voucher = StringUtils.trimToEmpty(param.get("voucher"));
//
//        if (NumberUtils.toInt(price) == 0) {
//            data.setMsg("金额不能为0");
//            return data;
//        }
//
//        UcSupplerAccountLog ucSupplerAccountLog = new UcSupplerAccountLog();
//        ucSupplerAccountLog.setUcUserId(ucUserId);
//        ucSupplerAccountLog.setPrice(NumberUtils.toInt(price));
//        ucSupplerAccountLog.setType(0);
//        ucSupplerAccountLog.setAccountName(accountName);
//        ucSupplerAccountLog.setAccountNo(accountNo);
//        ucSupplerAccountLog.setRemark(remark);
//        ucSupplerAccountLog.setVoucher(voucher);
//        ucSupplerAccountLog.setAuditStatus(0);
//        ucSupplerAccountLog.setAuditRemark("");
//        ucSupplerAccountLog.setPaymentTime(new Date());
//        ucSupplerAccountLog.setAddUserId(ucUserId);
//        ucSupplerAccountLog.setAddTime(new Date());
//        ucSupplerAccountLog.setUpUserId(ucUserId);
//        ucSupplerAccountLog.setUpTime(new Date());
//
//        //当前用户所属的组织机构节点
//        List<Integer> ids = ucUserService.getUserOrganizationIds(ucUserId);
//        ucSupplerAccountLog.setOrganizationId(CollectionUtils.isEmpty(ids) ? 0 : NumberUtils.toInt(ids.get(0)));
//
//        ucSupplerAccountLogService.insert(ucSupplerAccountLog);
//        data.setCode(200);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping(value = "/auditAccountList", method = RequestMethod.GET)
//    public ResponseData auditAccountList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//
//        // 审核状态0待审核1审核通过2审核驳回  全部为空
//        String auditStatus = StringUtils.trimToEmpty(request.getParameter("auditStatus"));
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//        Integer userId = loginUser.getUcUser().getPkid();
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("type", 0);
//        if(NumberUtils.toInt(auditStatus, -1) >= 0) {
//            condition.put("auditStatus", NumberUtils.toInt(auditStatus, -1));
//        }
//
//        //当前用户所属的组织机构节点
////        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
////        condition.put("ids", ids);
//
//        Pager<UcSupplerAccountLog> pager = ucSupplerAccountLogService.pageAuditByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    public ResponseData auditInfo(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        String pkidStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        int pkid = NumberUtils.toInt(pkidStr);
//
//        ResponseData data = new ResponseData();
//        UcSupplerAccountLog supplerAccountLog = ucSupplerAccountLogService.findById(pkid);
//        data.setData(supplerAccountLog);
//        return data;
//    }
//
//    @RequestMapping(value = "/audit", method = RequestMethod.POST)
//    public ResponseData audit(@RequestBody Map<String, Object> param, HttpServletResponse response, LoginUser loginUser) {
//
//        String pkidStr = StringUtils.trimToEmpty(param.get("pkidStr"));
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        int pkid = NumberUtils.toInt(pkidStr);
//
//        // 审核状态0待审核1审核通过2审核驳回
//        String auditStatus = StringUtils.trimToEmpty(param.get("auditStatus"));
//        String paymentTime = StringUtils.trimToEmpty(param.get("paymentTime"));
//        String auditRemark = StringUtils.trimToEmpty(param.get("auditRemark"));
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//        if(NumberUtils.toInt(auditStatus) != 1 && NumberUtils.toInt(auditStatus) != 2) {
//            data.setMsg("审核错误");
//            return data;
//        }
//        if(NumberUtils.toInt(auditStatus) == 2 && StringUtils.isBlank(auditRemark)) {
//            data.setMsg("驳回原因必填");
//            return data;
//        }
//
//        UcSupplerAccountLog supplerAccountLog = ucSupplerAccountLogService.findById(pkid);
//        supplerAccountLog.setAuditStatus(NumberUtils.toInt(auditStatus));
//        supplerAccountLog.setAuditRemark(auditRemark);
//        supplerAccountLog.setPaymentTime(DateUtils.strToDateTime(paymentTime));
//        supplerAccountLog.setUpUserId(loginUser.getUcUser().getPkid());
//        supplerAccountLog.setUpTime(new Date());
//
//        int result = ucSupplerAccountLogService.update(supplerAccountLog);
//        if (result <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }



}
