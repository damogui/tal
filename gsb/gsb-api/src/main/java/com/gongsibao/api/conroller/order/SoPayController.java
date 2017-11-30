package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;


@Path("/sopay")
public class SoPayController {
//
//    @Autowired
//    private SoPayService soPayService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoPay soPay) {
//        ResponseData data = new ResponseData();
//        soPayService.insert(soPay);
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
//        soPayService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoPay soPay) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soPay.setPkid(pkid);
//        soPayService.update(soPay);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<SoPay> pager = soPayService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        SoPay soPay = soPayService.findById(pkid);
//        data.setData(soPay);
//        return data;
//    }
//
//    @RequestMapping("/getlist")
//    public ResponseData getList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Map<String, Object> mapParam = new HashMap<>();
//
//        //region 获取参数
//        /*编号*/
//        int no = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        /*订单编号*/
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderNo"));
//        /*下单人名称*/
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        /*下单人手机号*/
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        /*支付状态*/
//        int payStatus = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("payStatus")));
//        /*付款审核状态*/
//        int auditStatus = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("auditStatus")));
//        /*付款方式*/
//        int payWayType = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("payWayType")));
//        /*银行名称*/
//        String bankName = StringUtils.trimToEmpty(request.getParameter("bankName"));
//
//        if (no > 0) {
//            mapParam.put("no",no);
//        }
//        if (payStatus > 0) {
//            mapParam.put("payStatus",payStatus);
//        }
//        if (auditStatus > 0) {
//            mapParam.put("auditStatus",auditStatus);
//        }
//        if (payWayType > 0) {
//            mapParam.put("payWayType",payWayType);
//        }
//        if (StringUtils.isNotBlank(orderNo)) {
//            mapParam.put("orderNo",orderNo);
//        }
//        if (StringUtils.isNotBlank(accountName)) {
//            mapParam.put("accountName",accountName);
//        }
//        if (StringUtils.isNotBlank(accountMobile)) {
//            mapParam.put("accountMobile",accountMobile);
//        }
//        if (StringUtils.isNotBlank(bankName)) {
//            mapParam.put("bankName",bankName);
//        }
//        // endregion
//
//        Pager<PayOrder> list = soPayService.getList(mapParam, currentPage, pageSize);
//        data.setData(list);
//        return data;
//    }

}