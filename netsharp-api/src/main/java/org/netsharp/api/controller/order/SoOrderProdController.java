package org.netsharp.api.controller.order;


import javax.ws.rs.Path;


@Path("/soorderprod")
public class SoOrderProdController {
//
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//    @Autowired
//    private CrmCompanyIntentionService crmCompanyIntentionService;
//
//    @Autowired
//    private SoOrderProdTraceService soOrderProdTraceService;
//
//    @Autowired
//    private SoOrderProdTraceFileService soOrderProdTraceFileService;
//
//    @Autowired
//    private ErOrderService erOrderService;
//
//    private String UUID = "94b9b544-6bd3-4b0f-816d-8c7fa107bffa";
//
//    private String SPIDER_URL = "http://www.gongsibao.com/spider/cmsapi/getSpiderNewsInfo.api";
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProd soOrderProd) {
//        ResponseData data = new ResponseData();
//        soOrderProdService.insert(soOrderProd);
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
//        soOrderProdService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProd soOrderProd) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderProd.setPkid(pkid);
//        soOrderProdService.update(soOrderProd);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list_demo"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<SoOrderProd> pager = soOrderProdService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        SoOrderProd soOrderProd = soOrderProdService.findById(orderProdId);
//        ResponseData data = new ResponseData();
//        data.setData(soOrderProd);
//        return data;
//    }
//
//    @RequestMapping("/getDetail")
//    public ResponseData getDetailById(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        Map<String, Object> result = soOrderProdService.getDetailById(orderProdId);
//
//        List<String> roleTags = loginUser.getRoleTags();
//        result.put("isYWY", CollectionUtils.isEmpty(roleTags) ? 1 : (roleTags.contains(RoleTag.ROLE_DDCZ) ? 0 : 1));
//        ResponseData data = new ResponseData();
//        data.setData(result);
//        return data;
//    }
//
//    @RequestMapping("/saveCompany")
//    public ResponseData saveCompany(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 产品订单ID加密串
//        int orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderProdIdStr")));
//        String companyName = StringUtils.trimToEmpty(request.getParameter("orderProdCompanyName"));
//
//        soOrderProdService.saveCompanyName(orderProdId, companyName, loginUser.getUcUser().getPkid());
//        return new ResponseData();
//    }
//
//    @RequestMapping("/orderProdAudit")
//    public ResponseData gotoOrderProdAudit(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        SoOrder result = soOrderProdService.getOrderProdAuditById(orderProdId);
//        ResponseData data = new ResponseData();
//        data.setData(result);
//        return data;
//    }
//
//    @RequestMapping("/apply")
//    public ResponseData apply(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        ResponseData data = new ResponseData();
//
//        // 根据产品订单ID验证必须上传的材料是否都上传了
//        String needUploadFileNames = soOrderProdTraceFileService.getNeedUploadFileNames(orderProdId);
//        if (StringUtils.isNotBlank(needUploadFileNames)) {
//            data.setCode(-2);
//            data.setMsg("提交审核失败！您需要再次上传审核不通过的材料：" + needUploadFileNames + "。");
//            return data;
//        }
//
//        SoOrderProd orderProd = soOrderProdService.findById(orderProdId);
//        if (orderProd == null) {
//            data.setCode(-1);
//            data.setMsg("提交审核失败！");
//            return data;
//        }
//        /** 105 审核状态：1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过 */
//        if (orderProd.getAuditStatusId().compareTo(1051) == 0 || orderProd.getAuditStatusId().compareTo(1053) == 0) {
//            int result = soOrderProdService.updateAuditStatus(orderProdId, 1052, orderProd.getAuditStatusId());
//            if (result <= 0) {
//                data.setCode(-1);
//                data.setMsg("提交审核失败！");
//            }
//        } else {
//            data.setCode(-1);
//            data.setMsg("提交审核失败！");
//        }
//        return data;
//    }
//
//    @RequestMapping("/audit")
//    public ResponseData audit(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser, @RequestBody String json) {
////        Map<String, Object> mmpp = new HashMap<>();
////        mmpp.put("orderProdIdStr", "1XU~");
////        mmpp.put("content", "身份证过期");
////        List<Map<String, Object>> lst1 = new ArrayList<>();
////        Map<String, Object> m1 = new HashMap<>();
////        m1.put("pkidStr","1nI~");
////        m1.put("auditStatusId",1054);
////        lst1.add(m1);
//////        Map<String, Object> m2 = new HashMap<>();
//////        m2.put("pkidStr","2");
//////        m2.put("auditStatusId",1053);
//////        lst1.add(m2);
////        mmpp.put("traceFilesJson", lst1);
////        String json = JsonUtils.objectToJson(mmpp);
//
//        // 获取参数JSON串
//        //String orderProdAuditJson = StringUtils.trimToEmpty(request.getParameter("orderProdAuditJson"));
//        //String json = "{orderProdIdStr: \"1XU~\", traceFilesJson: [{pkidStr: \"1nI~\", auditStatusId: 1054}]}"
//
//        ResponseData data = new ResponseData();
//        if (StringUtils.isBlank(json)) {
//            data.setCode(-1);
//            data.setMsg("参数为空");
//            return data;
//        }
//        Map<String, Object> map = (Map<String, Object>) JsonUtils.jsonToObject(json, Map.class);
//        if (MapUtils.isEmpty(map)) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<Map<String, Object>> lstTraceFile = (List<Map<String, Object>>) map.get("traceFilesJson");
//        if (CollectionUtils.isEmpty(lstTraceFile)) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty((String) map.get("orderProdIdStr"));
//        String content = StringUtils.trimToEmpty((String) map.get("content"));
//
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        // 材料审核通过的ID集合
//        List<Integer> passTraceFileIds = new ArrayList<>();
//        // 材料审核不通过的ID集合
//        List<Integer> failTraceFileIds = new ArrayList<>();
//        String traceFileIdStr = null;
//        int traceFileStatus = 0;
//        for (Map<String, Object> item : lstTraceFile) {
//            traceFileIdStr = StringUtils.trimToEmpty((String) item.get("pkidStr"));
//            traceFileIdStr = SecurityUtils.rc4Decrypt(traceFileIdStr);
//            traceFileStatus = NumberUtils.toInt(item.get("auditStatusId"));
//
//            /** 105 审核状态：1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过 */
//            if (traceFileStatus == 1054) {
//                passTraceFileIds.add(NumberUtils.toInt(traceFileIdStr));
//            } else if (traceFileStatus == 1053) {
//                failTraceFileIds.add(NumberUtils.toInt(traceFileIdStr));
//            }
//        }
//
//        if (CollectionUtils.isNotEmpty(failTraceFileIds) && StringUtils.isBlank(content)) {
//            data.setCode(-1);
//            data.setMsg("审核不通过理由必填!");
//            return data;
//        }
//
//        Integer userId = loginUser.getUcUser().getPkid();// 登录人ID
//        int result = soOrderProdService.updateOrderProdAudit(orderProdId, passTraceFileIds, failTraceFileIds, content, userId);
//        if (result <= 0) {
//            data.setCode(-1);
//            data.setMsg("审核失败！");
//        }
//        return data;
//    }
//
//    @RequestMapping({"/monitorList"})
//    public ResponseData monitorList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 类型
//        String listType = StringUtils.trimToEmpty(request.getParameter("type"));
//        // 组织名称
//        String organizationName = StringUtils.trimToEmpty(request.getParameter("organizationName"));
//        // 订单编号
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderNo"));
//        //订单明细号
//        Integer orderProdNo = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("orderProdNo")));
//        // 产品名称
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        // 公司名称
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        // 状态
//        String statusName = StringUtils.trimToEmpty(request.getParameter("statusName"));
//        // 地区ID
//        String cityIdStr = StringUtils.trimToEmpty(request.getParameter("cityIdStr"));
//        cityIdStr = SecurityUtils.rc4Decrypt(cityIdStr);
//        int cityId = NumberUtils.toInt(cityIdStr);
//        // 业务员名称
//        String realName = StringUtils.trimToEmpty(request.getParameter("realName"));
//        // 下单时间
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("userId", loginUser.getUcUser().getPkid());// 当前登陆者
//        int type = NumberUtils.toInt(listType);
//        if (type > 0) {
//            condition.put("type", type);
//            if (type != 3) {
//                condition.put("isAudit", 1);// 需要用3063过滤数据
//            }
//        }
//        if (StringUtils.isNotBlank(organizationName)) {
//            condition.put("organizationName", organizationName);
//        }
//        if (StringUtils.isNotBlank(orderNo)) {
//            condition.put("orderNo", orderNo);
//        }
//        if (!orderProdNo.equals(0)) {
//            condition.put("orderProdNo", orderProdNo + "%");
//        }
//        if (StringUtils.isNotBlank(productName)) {
//            condition.put("productName", productName);
//        }
//        //产品订单状态
//        if (StringUtils.isNotBlank(statusName)) {
//            condition.put("statusName", "%" + statusName + "%");
//        }
//        //公司名称
//        if (StringUtils.isNotBlank(companyName)) {
//            condition.put("companyName", companyName);
//        }
//        if (cityId > 0) {
//            condition.put("cityId", cityId);
//        }
//        if (StringUtils.isNotBlank(realName)) {
//            condition.put("realName", realName);
//        }
//        if (StringUtils.isNotBlank(beginTime)) {
//            condition.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(endTime)) {
//            condition.put("endTime", endTime + " 23:59:59");
//        }
//
//        String handleName = StringUtils.trimToEmpty(request.getParameter("handleName"));
//        if (StringUtils.isNotBlank(handleName)) {
//            condition.put("handleName", handleName);
//        }
//
//        String applyNo = StringUtils.trimToEmpty(request.getParameter("applyNo"));
//        if (StringUtils.isNotBlank(applyNo)) {
//            condition.put("applyNo", applyNo);
//        }
//
//        //跟踪记录
//        String traceBeginTime = StringUtils.trimToEmpty(request.getParameter("tracebegintime"));
//        String traceEndTime = StringUtils.trimToEmpty(request.getParameter("traceendtime"));
//        String traceContent = StringUtils.trimToEmpty(request.getParameter("tracecontent"));
//
//        if (StringUtils.isNoneBlank(traceBeginTime)) {
//            condition.put("tracebegintime", traceBeginTime + " 00:00:00");
//        }
//
//        if (StringUtils.isNoneBlank(traceEndTime)) {
//            condition.put("traceendtime", traceEndTime + " 23:59:59");
//        }
//
//        if (StringUtils.isNoneBlank(traceContent)) {
//            condition.put("tracecontent", traceContent);
//        }
//        //操作人员名
//        String operator = StringUtils.trimToEmpty(request.getParameter("operator"));
//        //操作人员名称
//        if (StringUtils.isNotBlank(operator)) {
//            condition.put("operator", operator);
//        }
//
//        Pager<OrderProdMonitorList> pager = soOrderProdService.findOrderProdMonitorListByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 认领产品订单
//     */
//    @RequestMapping("claim")
//    public ResponseData claimOrderProd(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("OrderProdIdStr"));
//
//        Integer orderProdId = getIntegerIdFromEncryptStr(orderProdIdStr);
//        if (orderProdId <= 0) {
//            data.setCode(400);
//            data.setMsg("产品订单Id无效");
//            return data;
//        }
//
//        if (soOrderProdService.addClaimOrderProd(orderProdId, loginUser)) {
//            data.setCode(200);
//            data.setMsg("操作成功");
//        } else {
//            data.setCode(200);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    /**
//     * 操作订单池列表（只有操作人员）
//     * <p>
//     * statusStr产品处理状态编码 0:未处理，1: 正在处理，2:正常结束, 8:异常结束
//     * 审核状态码 0：未审核状态，1，审核中，2，审核通过，4，审核不通过
//     */
//    @RequestMapping("/list")
//    public ResponseData getOrderProdList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//
//        ResponseData data = new ResponseData();
//        data.setCode(200);
//        data.setMsg("操作成功");
//
//        Map<String, Object> map = getQueryProdRowsParas(request);
//
//        //查询当前登录人组织ID集合
//        List<Integer> orgIds = ucUserService.getUserOrganizationIds(loginUser.getUcUser().getPkid());
//        if (CollectionUtils.isEmpty(orgIds)) {
///*            map.put("orgIds", new ArrayList<Integer>() {{
//                add(0);
//            }});*/
//            data.setData(new Pager<>(0, 0));
//            return data;
//        } else {
//            map.put("orgIds", orgIds);
//        }
//        //System.out.println("ids is:"+StringUtils.join(orgIds, ","));
//
//        //查询当前登录人所在部门所有人员ID集合
//        List<Integer> userIds = ucUserService.getUserPkid(loginUser.getUcUser().getPkid());
//        if (CollectionUtils.isNotEmpty(userIds)) {
//            map.put("userIds", userIds);
//        }
//
//        String sort = StringUtils.trimToEmpty(request.getParameter("sort"));
//        if (StringUtils.isNotBlank(sort)) {
//            map.put("sort", sort);
//        }
//
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        Pager<OrderProdRow> pager = soOrderProdService.pageOrderProdRowsByProperties(map, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize), loginUser);
//
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * @操作产品订单列表计数器 statusStr产品处理状态编码 0:未处理，1: 正在处理，2:正常结束, 8:异常结束
//     * 审核状态码 0：未审核状态，1，审核中，2，审核通过，4，审核不通过
//     */
//    @RequestMapping("count")
//    public ResponseData getOrderProdCount(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        //查询当前人员的
//        List<Integer> orgIds = ucUserService.getUserOrganizationIds(loginUser.getUcUser().getPkid());
//        //String orgIdsStr = "";
//        if (CollectionUtils.isEmpty(orgIds)) {
//            data.setCode(-1);
//            data.setMsg("当前登录人所在组织为空");
//            data.setData(0);
//            return data;
//        } else {
//            //orgIdsStr = orgIds.stream().map(Object::toString).collect(Collectors.joining(","));
//        }
//
//        String statusStr = StringUtils.trimToEmpty(request.getParameter("statusStr"));
//        if (StringUtils.isNotBlank(statusStr)
//                && (statusStr.equalsIgnoreCase("00")
//                || statusStr.equalsIgnoreCase("10")
//                || statusStr.equalsIgnoreCase("24"))) {
//
//            Map<String, Object> condition = new HashMap<>();
//            condition.put("processStatusId", statusStr.charAt(0));
//            condition.put("auditStatusId", statusStr.charAt(1));
//            condition.put("orgIds", orgIds);
//            //查询当前登录人所在部门所有人员ID集合
//            List<Integer> userIds = ucUserService.getUserPkid(loginUser.getUcUser().getPkid());
//            if (CollectionUtils.isNotEmpty(userIds)) {
//                condition.put("userIds", userIds);
//            }
//
//            Integer count = soOrderProdService.getOrderProdCount(condition);
//
//            data.setCode(200);
//            data.setMsg("操作成功");
//            data.setData(count);
//            return data;
//        }
//        data.setCode(400);
//        data.setMsg("请指定参数");
//        return data;
//    }
//
//    /**
//     * statusStr产品处理状态编码 0:未处理，1: 正在处理，2:正常结束, 8:异常结束
//     * 审核状态码 0：未审核状态，1，审核中，2，审核通过，4，审核不通过
//     */
//    private Map<String, Object> getQueryProdRowsParas(HttpServletRequest request) {
//
//        //业务人员名
//        String userNameStr = StringUtils.trimToEmpty(request.getParameter("userName"));
//        //操作人员名
//        String operator = StringUtils.trimToEmpty(request.getParameter("operator"));
//        // 产品名称
//        String prodName = StringUtils.trimToEmpty(request.getParameter("prodName"));
//        // 公司名称
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        // 订单编号
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderNo"));
//        //订单状态
//        String statusStr = StringUtils.trimToEmpty(request.getParameter("statusStr"));
//        // 地区ID
//        String cityAreaIdStr = StringUtils.trimToEmpty(request.getParameter("cityIdStr"));
//        //开始时间
//        String beginTimeStr = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        //结束时间
//        String endTimeStr = StringUtils.trimToEmpty(request.getParameter("endTime"));
//
//        //跟踪记录
//        String traceBeginTime = StringUtils.trimToEmpty(request.getParameter("tracebegintime"));
//        String traceEndTime = StringUtils.trimToEmpty(request.getParameter("traceendtime"));
//        String traceContent = StringUtils.trimToEmpty(request.getParameter("tracecontent"));
//        //订单明细号
//        Integer orderProdNo = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("orderProdNo")));
//        // 状态
//        String statusName = StringUtils.trimToEmpty(request.getParameter("statusName"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        //业务人员名称
//        if (StringUtils.isNotBlank(userNameStr)) {
//            condition.put("userName", userNameStr + "%");
//        }
//        //操作人员名称
//        if (StringUtils.isNotBlank(operator)) {
//            condition.put("operator", operator + "%");
//        }
//        //产品名称
//        if (StringUtils.isNotBlank(prodName)) {
//            condition.put("prodName", prodName + "%");
//        }
//        //状态
//        if (StringUtils.isNotBlank(statusName)) {
//            condition.put("statusName", "%" + statusName + "%");
//        }
//        //公司名称
//        if (StringUtils.isNotBlank(companyName)) {
//            condition.put("companyName", companyName);
//        }
//        //产品编码
//        if (StringUtils.isNotBlank(orderNo)) {
//            condition.put("orderNo", orderNo + "%");
//        }
//        //城市ID
//        if (StringUtils.isNotBlank(cityAreaIdStr)) {
//            Integer cityAreaId = getIntegerIdFromEncryptStr(cityAreaIdStr);
//            condition.put("cityId", cityAreaId);
//        }
//        //产品状态和审核状态
//        if (StringUtils.isNotBlank(statusStr) && statusStr.length() == 2) {
//            condition.put("processStatusId", statusStr.charAt(0));
//            condition.put("auditStatusId", statusStr.charAt(1));
//        }
//        //开始时间
//        if (StringUtils.isNotBlank(beginTimeStr)) {
//            condition.put("beginTime", beginTimeStr + " 00:00:00");
//        }
//        //结束时间
//        if (StringUtils.isNotBlank(endTimeStr)) {
//            condition.put("endTime", endTimeStr + " 23:59:59");
//        }
//
//        String handleName = StringUtils.trimToEmpty(request.getParameter("handleName"));
//        if (StringUtils.isNotBlank(handleName)) {
//            condition.put("handleName", handleName);
//        }
//        String applyNo = StringUtils.trimToEmpty(request.getParameter("applyNo"));
//        if (StringUtils.isNotBlank(applyNo)) {
//            condition.put("applyNo", applyNo);
//        }
//        if (StringUtils.isNoneBlank(traceBeginTime)) {
//            condition.put("tracebegintime", traceBeginTime + " 00:00:00");
//        }
//
//        if (StringUtils.isNoneBlank(traceEndTime)) {
//            condition.put("traceendtime", traceEndTime + " 23:59:59");
//        }
//
//        if (StringUtils.isNoneBlank(traceContent)) {
//            condition.put("tracecontent", traceContent);
//        }
//        if (!orderProdNo.equals(0)) {
//            condition.put("orderProdNo", orderProdNo + "%");
//        }
//        return condition;
//    }
//
//    private Integer getIntegerIdFromEncryptStr(String encryKeyStr) {
//        if (StringUtils.isNotBlank(encryKeyStr)) {
//            encryKeyStr = SecurityUtils.rc4Decrypt(encryKeyStr);
//        }
//        return NumberUtils.toInt(encryKeyStr, -1);
//    }
//
//    @RequestMapping("/orderauditlist")
//    public ResponseData orderauditlist(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        //页码
//        Integer currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        //分页大小
//        Integer pagerSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//
//        Map<String, Object> map = new HashMap<>();
////        map.put("isAudit", 1);// 订单审核列表需要用3063过滤数据
//        map.put("userId", loginUser.getUcUser().getPkid());// 当前登陆者
//
//        //审核状态
//        String auditStatusId = StringUtils.trimToEmpty(request.getParameter("auditStatusId"));
//        if (StringUtils.isNotBlank(auditStatusId)) {
//            map.put("auditStatusId", auditStatusId);
//        }
//        //订单号
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderId"));
//        if (StringUtils.isNotBlank(orderNo)) {
//            map.put("orderNo", orderNo);
//        }
//        //订单明细号
//        Integer orderProdNo = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("orderProdNo")));
//        if (!orderProdNo.equals(0)) {
//            map.put("orderProdNo", orderProdNo + "%");
//        }
//        // 状态
//        String statusName = StringUtils.trimToEmpty(request.getParameter("statusName"));
//        if (StringUtils.isNotBlank(statusName)) {
//            map.put("statusName", "%" + statusName + "%");
//        }
//        //产品名称
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        if (StringUtils.isNotBlank(productName)) {
//            map.put("productName", productName);
//        }
//        //业务员
//        String realName = StringUtils.trimToEmpty(request.getParameter("userName"));
//        if (StringUtils.isNotBlank(realName)) {
//            map.put("realName", realName);
//        }
//        //组织
//        String organizationName = StringUtils.trimToEmpty(request.getParameter("orgnizationName"));
//        if (StringUtils.isNotBlank(organizationName)) {
//            map.put("organizationName", organizationName);
//        }
//        //公司名
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            map.put("companyName", companyName);
//        }
//
//        String handleName = StringUtils.trimToEmpty(request.getParameter("handleName"));
//        if (StringUtils.isNotBlank(handleName)) {
//            map.put("handleName", handleName);
//        }
//
//        String applyNo = StringUtils.trimToEmpty(request.getParameter("applyNo"));
//        if (StringUtils.isNotBlank(applyNo)) {
//            map.put("applyNo", applyNo);
//        }
//
//        //跟踪记录
//        String traceBeginTime = StringUtils.trimToEmpty(request.getParameter("tracebegintime"));
//        String traceEndTime = StringUtils.trimToEmpty(request.getParameter("traceendtime"));
//        String traceContent = StringUtils.trimToEmpty(request.getParameter("tracecontent"));
//
//        if (StringUtils.isNoneBlank(traceBeginTime)) {
//            map.put("tracebegintime", traceBeginTime + " 00:00:00");
//        }
//
//        if (StringUtils.isNoneBlank(traceEndTime)) {
//            map.put("traceendtime", traceEndTime + " 23:59:59");
//        }
//
//        if (StringUtils.isNoneBlank(traceContent)) {
//            map.put("tracecontent", traceContent);
//        }
//        //操作人员名
//        String operator = StringUtils.trimToEmpty(request.getParameter("operator"));
//        //操作人员名称
//        if (StringUtils.isNotBlank(operator)) {
//            map.put("operator", operator);
//        }
//
//        int ywyOrganizationId = NumberUtils.toInt(request.getParameter("ywyOrganizationId"));
//        if (ywyOrganizationId > 0) {
//            map.put("ywyOrganizationId", ywyOrganizationId);
//        }
//
//        int czyOrganizationId = NumberUtils.toInt(request.getParameter("czyOrganizationId"));
//        if (czyOrganizationId > 0) {
//            map.put("czyOrganizationId", czyOrganizationId);
//        }
//
//        Pager<OrderProdMonitorList> pager = soOrderProdService.findOrderAuditListByProperties(map, currentPage, pagerSize);
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("myorderlist")
//    public ResponseData getMyOrderProdList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        HashMap<String, Object> map = new HashMap<>();
//        //页码
//        Integer currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        //分页大小
//        Integer pagerSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//
//        //审核状态
//        String auditStatusId = StringUtils.trimToEmpty(request.getParameter("auditStatusId"));
//        if (StringUtils.isNotBlank(auditStatusId)) {
//            map.put("auditStatusId", auditStatusId);
//        }
//        //业务人员对订单的状态  3141正在负责  3142曾经负责
//        String statusId = StringUtils.trimToEmpty(request.getParameter("statusId"));
//        if (StringUtils.isNotBlank(statusId)) {
//            map.put("statusId", statusId);
//        }
//        //公司名
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        if (StringUtils.isNotBlank(companyName)) {
//            map.put("companyName", companyName);
//        }
//        //联系人
//        String accountName = StringUtils.trimToEmpty(request.getParameter("accountName"));
//        if (StringUtils.isNotBlank(accountName)) {
//            map.put("accountName", accountName + "%");
//        }
//        //联系电话
//        String accountMobile = StringUtils.trimToEmpty(request.getParameter("accountMobile"));
//        if (StringUtils.isNotBlank(accountMobile)) {
//            map.put("accountMobile", accountMobile + "%");
//        }
//        //订单号
//        String orderId = StringUtils.trimToEmpty(request.getParameter("orderId"));
//        if (StringUtils.isNotBlank(orderId)) {
//            map.put("orderId", orderId + "%");
//        }
//        //产品名称
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        if (StringUtils.isNotBlank(productName)) {
//            map.put("productName", productName + "%");
//        }
//        // 状态
//        String statusName = StringUtils.trimToEmpty(request.getParameter("statusName"));
//        if (StringUtils.isNotBlank(statusName)) {
//            map.put("statusName", "%" + statusName + "%");
//        }
//        //业务员名称
//        String ywyName = StringUtils.trimToEmpty(request.getParameter("ywyName"));
//        if (StringUtils.isNotBlank(ywyName)) {
//            map.put("ywyName", "%" + ywyName + "%");
//        }
//        //地区
//        String cityId = StringUtils.trimToEmpty(request.getParameter("cityId"));
//        if (StringUtils.isNotBlank(cityId)) {
//            map.put("cityId", cityId);
//        }
//        //开始时间
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        if (StringUtils.isNotBlank(beginTime)) {
//            map.put("beginTime", beginTime + " 00:00:00");
//        }
//        //结束时间
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//        if (StringUtils.isNotBlank(endTime)) {
//            map.put("endTime", endTime + " 23:59:59");
//        }
//        //权限orgId
//        String userId = loginUser.getUcUser().getPkid().toString();
//        if (userId != null) {
//            map.put("userId", userId);
//        }
//
//        map.put("sort", NumberUtils.toInt(request.getParameter("sort")));
//
//        String handleName = StringUtils.trimToEmpty(request.getParameter("handleName"));
//        if (StringUtils.isNotBlank(handleName)) {
//            map.put("handleName", handleName);
//        }
//
//        String applyNo = StringUtils.trimToEmpty(request.getParameter("applyNo"));
//        if (StringUtils.isNotBlank(applyNo)) {
//            map.put("applyNo", applyNo);
//        }
//
//        //跟踪记录
//        String traceBeginTime = StringUtils.trimToEmpty(request.getParameter("tracebegintime"));
//        String traceEndTime = StringUtils.trimToEmpty(request.getParameter("traceendtime"));
//        String traceContent = StringUtils.trimToEmpty(request.getParameter("tracecontent"));
//
//        if (StringUtils.isNoneBlank(traceBeginTime)) {
//            map.put("tracebegintime", traceBeginTime + " 00:00:00");
//        }
//
//        if (StringUtils.isNoneBlank(traceEndTime)) {
//            map.put("traceendtime", traceEndTime + " 23:59:59");
//        }
//
//        if (StringUtils.isNoneBlank(traceContent)) {
//            map.put("tracecontent", traceContent);
//        }
//        //订单明细号
//        Integer orderProdNo = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("orderProdNo")));
//        if (!orderProdNo.equals(0)) {
//            map.put("orderProdNo", orderProdNo + "%");
//        }
//
//        //操作人员名
//        String operator = StringUtils.trimToEmpty(request.getParameter("operator"));
//        //操作人员名称
//        if (StringUtils.isNotBlank(operator)) {
//            map.put("operator", operator + "%");
//        }
//
//        Pager<OrderProdRow> pager = soOrderProdService.findMyOrderListByProperties(map, currentPage, pagerSize);
//        data.setData(pager);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("myorderlistnuminfo")
//    public ResponseData myorderlistnuminfo(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = new HashMap<>();
//        //审核状态  默认查询所有状态
//        String auditStatusId = StringUtils.trimToEmpty(request.getParameter("auditStatusId"));
//        List<Integer> auditStatusIdList = new ArrayList<>();
//        if (StringUtils.isNotBlank(auditStatusId)) {
//            String[] auditStatusIdStrs = auditStatusId.split(",");
//            for (String string : auditStatusIdStrs) {
//                auditStatusIdList.add(NumberUtils.toInt(string));
//            }
//        } else {
//            auditStatusIdList.add(1051);
//            auditStatusIdList.add(1052);
//            auditStatusIdList.add(1053);
//            auditStatusIdList.add(1054);
//        }
//        map.put("auditStatusId", auditStatusIdList);
//        //业务人员对订单的状态  3141正在负责  3142曾经负责
//        String statusId = StringUtils.trimToEmpty(request.getParameter("statusId"));
//        List<Integer> statusIdList = new ArrayList<>();
//        if (StringUtils.isNotBlank(statusId)) {
//            String[] auditStatusIdStrs = statusId.split(",");
//            for (String string : auditStatusIdStrs) {
//                statusIdList.add(NumberUtils.toInt(string));
//            }
//        } else {
//            statusIdList.add(3141);
//            statusIdList.add(3142);
//        }
//        map.put("statusId", statusIdList);
//        //权限orgId
//        Integer userId = loginUser.getUcUser().getPkid();
//        if (userId != null) {
//            map.put("userId", userId);
//        }
//        Map<Integer, Object> resulstMap = soOrderProdService.findMyOrderNumInfoByProperties(map);
//        data.setData(resulstMap);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/addCompanyCognateOrderProdCustom")
//    public ResponseData addCompanyCognateOrderProdCustom(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//
//        String companyName = StringUtils.trimToEmpty(mapParam.get("companyName"));
//        if (StringUtils.isBlank(companyName)) {
//            data.setCode(-1);
//            data.setMsg("企业名称不能为空");
//            return data;
//        }
//        List<CrmCompanyIntention> companyList = crmCompanyIntentionService.findByCompanyName2(companyName);
//        int companyId = companyList.isEmpty() ? 0 : companyList.get(0).getPkid();
//        Integer rescompanyId = soOrderProdService.addCompanyCognateOrderProdCustom(pkid, companyName, companyId, user.getUcUser().getAddUserId());
//        if (rescompanyId.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该产品订单不存在");
//            return data;
//        }
//        if (rescompanyId.equals(-2)) {
//            data.setCode(-1);
//            data.setMsg("该企业名称已经存在");
//            return data;
//        }
//        if (rescompanyId.equals(-3)) {
//            data.setCode(-1);
//            data.setMsg("该产品订单已经存在企业信息了");
//            return data;
//        }
//        if (rescompanyId.equals(-4)) {
//            data.setCode(-1);
//            data.setMsg("该企业信息不存在");
//            return data;
//        }
//
//        new Thread() {
//            @Override
//            public void run() {
//                erOrderService.updateCompanyName(pkid, companyName);
//            }
//        }.start();
//
//        Map<String, Object> resMap = new HashMap<>();
//        resMap.put("companyId", SecurityUtils.rc4Encrypt(rescompanyId));
//        data.setMsg("操作成功");
//        data.setData(resMap);
//        return data;
//    }
//
//    @RequestMapping("/cancelOrderProdCompanyCognate")
//    public ResponseData cancelOrderProdCompanyCognate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        SoOrderProd orderProd = soOrderProdService.findById(pkid);
//        if (orderProd == null) {
//            data.setCode(-1);
//            data.setMsg("该产品订单不存在");
//            return data;
//        }
//        soOrderProdService.cancelOrderProdCompanyCognate(pkid);
//
//        new Thread() {
//            @Override
//            public void run() {
//                erOrderService.updateCompanyName(pkid, "");
//            }
//        }.start();
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping(value = "/updateHandleName", method = RequestMethod.POST)
//    public ResponseData updateHandleName(LoginUser loginUser, @RequestBody Map<String, Object> request) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.get("pkidStr")));
//        if (pkid == 0) {
//            data.setMsg("参数错误 pkidStr[" + request.get("pkidStr") + "]");
//            return data;
//        }
//
//        String handleName = StringUtils.trimToEmpty(request.get("handleName"));
//        if (StringUtils.isBlank(handleName)) {
//            return data;
//        }
//
//        try {
//            int rs = soOrderProdService.updateHandleName(pkid, handleName, loginUser.getUcUser().getPkid());
//            data.setCode(200);
//            data.setData(rs);
//            data.setMsg("操作成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/updateApplyNo", method = RequestMethod.POST)
//    public ResponseData updateApplyNo(LoginUser loginUser, @RequestBody Map<String, Object> request) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.get("pkidStr")));
//        if (pkid == 0) {
//            data.setMsg("参数错误 pkidStr[" + request.get("pkidStr") + "]");
//            return data;
//        }
//
//        String applyNo = StringUtils.trimToEmpty(request.get("applyNo"));
//        if (StringUtils.isBlank(applyNo)) {
//            return data;
//        }
//
//        try {
//            int rs = soOrderProdService.updateApplyNo(pkid, applyNo, loginUser.getUcUser().getPkid());
//            data.setCode(200);
//            data.setData(rs);
//            data.setMsg("操作成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping("/autoprogress")
//    public ResponseData getAutoProgressInfo(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-2);
//        //orderIdStr
//        String orderIdStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//        if (StringUtils.isBlank(orderIdStr)) {
//            data.setMsg("订单ID不能为空");
//            return data;
//        }
//        Map<String, String> condition = new HashMap<>();
//        condition.put("pkidStr", orderIdStr);
//        condition.put("status", "1");
//        condition.put("uuid", UUID);
//
//        String result = null;
//        try {
//            result = HttpClientUtil.httpGet(SPIDER_URL, condition,"utf-8");
//
//            ResponseData res = JsonUtils.jsonToObject(result,ResponseData.class);
//
//            if(res == null || res.getCode() != 200){
//                data.setMsg("数据获取中，请稍后再试...");
//                return data;
//            }
//
//
//        }catch (Exception e){
//            data.setMsg("抓取信息有误");
//            return data;
//        }
//
//        return JsonUtils.jsonToObject(result,ResponseData.class);
//
//    }
}