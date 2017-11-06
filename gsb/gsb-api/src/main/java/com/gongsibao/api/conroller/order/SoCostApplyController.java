package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;

@Path("/api/socostapply")
public class SoCostApplyController {
//
//    @Autowired
//    private SoCostApplyService soCostApplyService;
//
//    @Autowired
//    private SoOrderProdCostService soOrderProdCostService;
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData add(HttpServletRequest request, LoginUser loginUser, @RequestBody String req) {
//        ResponseData data = new ResponseData();
//
//        SoCostApply apply = JsonUtils.jsonToObject(req, SoCostApply.class);
//        apply.setAddUser(loginUser.getUcUser().getPkid());
//        apply.setStatus(0);
//        soCostApplyService.save(apply);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping(value = "/info")
//    public ResponseData info(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int applyId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (applyId == 0) {
//            data.setMsg("参数错误，pkidStr = " + request.getParameter("pkidStr"));
//            return data;
//        }
//        data.setCode(200);
//        data.setData(soCostApplyService.findInfoById(applyId));
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        //页码
//        Integer currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        //分页大小
//        Integer pagerSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//
//        Map<String, Object> map = new HashMap<>();
//        // 收款单位名称
//        String payeeName = StringUtils.trimToEmpty(request.getParameter("payeeName"));
//        if (StringUtils.isNotBlank(payeeName)) {
//            map.put("payeeName", payeeName);
//        }
//        // 申请编号
//        String pkid = StringUtils.trimToEmpty(request.getParameter("pkid"));
//        if (StringUtils.isNotBlank(pkid)) {
//            map.put("pkid", NumberUtils.toInt(pkid));
//        }
//        // 状态
//        String status = StringUtils.trimToEmpty(request.getParameter("status"));
//        if(NumberUtils.toInt(status, -1) >= 0) {
//            map.put("status", NumberUtils.toInt(status, -1));
//        }
//        // 申请人
//        String applyUser = StringUtils.trimToEmpty(request.getParameter("applyUser"));
//        if (StringUtils.isNotBlank(applyUser)) {
//            map.put("applyUser", applyUser);
//        }
//        // 提交时间
//        String applybegintime = StringUtils.trimToEmpty(request.getParameter("applybegintime"));
//        String applyendtime = StringUtils.trimToEmpty(request.getParameter("applyendtime"));
//
//        if (StringUtils.isNoneBlank(applybegintime)) {
//            map.put("applybegintime", applybegintime + " 00:00:00");
//        }
//
//        if (StringUtils.isNoneBlank(applyendtime)) {
//            map.put("applyendtime", applyendtime + " 23:59:59");
//        }
//
//        Pager<SoCostApply> pager = soCostApplyService.pageByProperties(map, currentPage, pagerSize);
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 审核请款申请
//     */
//    @RequestMapping("/audit")
//    public ResponseData audit(@RequestBody Map<String, Object> request, HttpServletResponse response
//            , LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String pkidStr = StringUtils.trimToEmpty(request.get("pkidStr"));
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = NumberUtils.toInt(pkidStr);
//
//        String auditRemark = StringUtils.trimToEmpty(request.get("auditRemark"));
//        String status = StringUtils.trimToEmpty(request.get("status"));
//        if (NumberUtils.toInt(status, -1) != 1 && NumberUtils.toInt(status, -1) != 2) {
//            data.setCode(-1);
//            data.setMsg("status error");
//            return data;
//        }
//        if (NumberUtils.toInt(status, -1) == 2 && StringUtils.isBlank(auditRemark)) {
//            data.setCode(-1);
//            data.setMsg("auditRemark error");
//            return data;
//        }
//
//        int result = soCostApplyService.updateAuditStatus(pkid, loginUser.getUcUser().getPkid(), auditRemark, NumberUtils.toInt(status, -1), 0);
//
//        if (result > 0) {
//            data.setMsg("操作成功");
//        } else {
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping("/preview")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        List<Integer> costIds = StringUtils.idsToList(request.getParameter("costIds"));
//        if (CollectionUtils.isEmpty(costIds)) {
//            data.setMsg("请先选择成本");
//            return data;
//        }
//
//        List<CostInfo> costInfoList = soOrderProdCostService.findCostInfoByCostIds(costIds);
//        SoCostApply costApply = new SoCostApply();
//        costApply.setCostInfoList(costInfoList);
//        data.setCode(200);
//        data.setData(costApply);
//        return data;
//    }
}