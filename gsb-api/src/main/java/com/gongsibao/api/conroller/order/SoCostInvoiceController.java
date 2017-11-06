package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;

@Path("/api/socostinvoice")
public class SoCostInvoiceController {
//
//    @Autowired
//    private SoCostInvoiceService soCostInvoiceService;
//
//    @Autowired
//    private SoOrderProdCostService soOrderProdCostService;
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData add(HttpServletRequest request, LoginUser loginUser, @RequestBody String req) {
//        ResponseData data = new ResponseData();
//
//        SoCostInvoice invoice = JsonUtils.jsonToObject(req, SoCostInvoice.class);
//        invoice.setAddUser(loginUser.getUcUser().getPkid());
//        invoice.setStatus(0);
//        soCostInvoiceService.save(invoice);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping(value = "/info")
//    public ResponseData info(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int invoiceId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (invoiceId == 0) {
//            data.setMsg("参数错误，pkidStr = " + request.getParameter("pkidStr"));
//            return data;
//        }
//        data.setCode(200);
//        data.setData(soCostInvoiceService.findInfoById(invoiceId));
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        //客户名称
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        //审核编号
//        String no = StringUtils.trimToEmpty(request.getParameter("no"));
//        //申请人
//        String applicantName = StringUtils.trimToEmpty(request.getParameter("applicantName"));
//        //审核提交开始时间
//        String beginTime = request.getParameter("beginTime");
//        //审核提交结束时间
//        String endTime = request.getParameter("endTime");
//        //审核状态(0待审核, 1通过, 2驳回)
//        int status = NumberUtils.toInt(request.getParameter("status"), -1);
//        Map<String, Object> condition = new HashMap<>();
//        if (StringUtils.isNotBlank(accountName)) {
//            condition.put("accountName", accountName);
//        }
//        if (StringUtils.isNotBlank(no)) {
//            condition.put("no", no);
//        }
//        if (StringUtils.isNotBlank(applicantName)) {
//            condition.put("applicantName", applicantName);
//        }
//        if (StringUtils.isNotBlank(beginTime)) {
//            condition.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(endTime)) {
//            condition.put("endTime", endTime + " 23:59:59");
//        }
//        if (status > -1) {
//            condition.put("status", status);
//        }
//        Pager<SoCostInvoice> pageByProperties = soCostInvoiceService.getPageByProperties(condition, currentPage, pageSize);
//        data.setData(pageByProperties);
//        return data;
//    }
//
//    @RequestMapping("/saveVerify")
//    public ResponseData saveVerify(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> paramMap) {
//        ResponseData data = new ResponseData();
//        //审核类型（1：通过，2：不通过）
//        Integer type = NumberUtils.toInt(paramMap.get("status"));
//        //主键
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(paramMap.get("pkidStr")));
//        //审核意见
//        String auditRemark = StringUtils.trimToEmpty(paramMap.get("auditRemark"));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        if (type != 1 && type != 2) {
//            data.setCode(-1);
//            data.setMsg("type参数错误");
//            return data;
//        }
//        if (type == 2 && StringUtils.isBlank(auditRemark)) {
//            data.setCode(-1);
//            data.setMsg("当审核不通过时，审核意见不能为空！");
//            return data;
//        }
//        Integer res = soCostInvoiceService.saveVerify(pkid, type, auditRemark, user.getUcUser().getPkid());
//        if (res.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该发票审核信息不存在！");
//            return data;
//        }
//        if (res.equals(-2)) {
//            data.setCode(-1);
//            data.setMsg("该发票审核信息审核状态不正确！");
//            return data;
//        }
//        data.setData(SecurityUtils.rc4Encrypt(res));
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
//        SoCostInvoice costInvoice = new SoCostInvoice();
//        costInvoice.setCostInfoList(costInfoList);
//        data.setCode(200);
//        data.setData(costInvoice);
//        return data;
//    }


}