package org.netsharp.api.controller.order;


import javax.ws.rs.Path;


@Path("/soorderprodusermap")
public class SoOrderProdUserMapController {
//
//    @Autowired
//    private SoOrderProdUserMapService soOrderProdUserMapService;
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private SmsService smsService;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private SoOrderProdTraceService soOrderProdTraceService;
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private CrmCompanyIntentionService crmCompanyIntentionService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdUserMap soOrderProdUserMap) {
//        ResponseData data = new ResponseData();
//        soOrderProdUserMapService.insert(soOrderProdUserMap);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdUserMap soOrderProdUserMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderProdUserMap.setPkid(pkid);
//        soOrderProdUserMapService.update(soOrderProdUserMap);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/status")
//    public ResponseData updateStatus(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        // 订单项和用户关系ID加密字符串
//        String pkidStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = NumberUtils.toInt(pkidStr, -1);
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        SoOrderProd orderProd = soOrderProdService.findById(orderProdId);
//        if (orderProd == null) {
//            data.setCode(-1);
//            data.setMsg("产品订单不存在");
//            return data;
//        }
//        /** 105 审核状态：1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过 */
//        if (orderProd.getAuditStatusId().compareTo(1054) != 0) {
//            Map<String, Object> condition = new HashMap<>();
//            condition.put("status_id", 3141);
//            condition.put("type_id", 3063);
//            condition.put("order_prod_id", orderProdId);
//            int count = soOrderProdUserMapService.countByProperties(condition);
//            if (count <= 1) {
//                data.setCode(-1);
//                data.setMsg("产品订单至少需要一个人负责");
//                return data;
//            }
//        }
//
//        // 负责人列表(操作列我已完成操作) 正在负责改成曾经负责 3141正在负责、3142曾经负责
//        int result = soOrderProdUserMapService.updateStatus(pkid, 3142, 3141);
//        if (result <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("userId", loginUser.getUcUser().getPkid());// 登陆人ID
//        condition.put("type_id", 3063);
//        if (orderProdId > 0) {
//            condition.put("order_prod_id", orderProdId);
//        }
//
//        Pager<SoOrderProdUserMap> pager = soOrderProdUserMapService.pageByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
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
//        SoOrderProdUserMap soOrderProdUserMap = soOrderProdUserMapService.findById(pkid);
//        data.setData(soOrderProdUserMap);
//        return data;
//    }
//
//    @RequestMapping("/sendMsg")
//    public ResponseData sendMsg(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//        // 负责人ID加密串
//        String userIdStr = StringUtils.trimToEmpty(request.getParameter("userIdStr"));
//        userIdStr = SecurityUtils.rc4Decrypt(userIdStr);
//        Integer userId = NumberUtils.toInt(userIdStr);
//        // 是否发送短信
//        String isSendSms = StringUtils.trimToEmpty(request.getParameter("isSendSms"));
//        // 提醒内容
//        String info = StringUtils.trimToEmpty(request.getParameter("info"));
//
//        // 发送站内信 TODO
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        if (StringUtils.isBlank(info)) {
//            data.setMsg("提醒内容不能为空");
//            data.setCode(-1);
//            return data;
//        }
//
//        SoOrderProd soOrderProd = soOrderProdService.findById(orderProdId);
//        if (soOrderProd == null) {
//            data.setCode(-1);
//            data.setMsg("产品订单不存在");
//            return data;
//        }
//        SoOrderProdTrace soOrderProdTrace = new SoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        /** 315 订单项记录类型：3151 更改状态、3152 备注、3153 上传材料、3154 提示客户、3155 快递、3156 帐号密码、3157 标记投诉、3158 提醒负责人 */
//        soOrderProdTrace.setTypeId(3158);
//        soOrderProdTrace.setInfo(info);
//        soOrderProdTrace.setIsSendSms(NumberUtils.toInt(isSendSms));
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(loginUser.getUcUser().getPkid());
//        soOrderProdTrace.setAddTime(new Date());
//        soOrderProdTrace.setRemark("");
//        soOrderProdTrace.setExpressContent("");
//        soOrderProdTrace.setExpressTo("");
//        soOrderProdTrace.setExpressCompanyName("");
//        soOrderProdTrace.setExpressNo("");
//        soOrderProdTrace.setProcessdDays(0);
//        soOrderProdTrace.setTimeoutDays(0);
//        // 插入订单项跟进记录
//        int key = soOrderProdTraceService.insert(soOrderProdTrace);
//        if (key <= 0) {
//            data.setCode(-1);
//            data.setMsg("订单项跟进记录插入失败");
//            return data;
//        }
//
//        if (NumberUtils.toInt(isSendSms) == 1) {
//            // 根据userId查询手机号 以及短信内容  TODO
//            UcUser user = ucUserService.findById(userId);
//            if (user == null) {
//                data.setCode(-1);
//                data.setMsg("用户为空");
//            } else {
//                SoOrder order = soOrderService.findById(soOrderProd.getOrderId());
//                String no = "";
//                if (order != null) {
//                    no = order.getNo();
//                }
//                // 【公司宝】XXX，您好！XXX给您发了一条订单（订单号：125461）提醒：XXXXXXXXXX。
//                String content = "【公司宝】" + user.getRealName() + "，您好！" + loginUser.getUcUser().getRealName() + "给您发了一条订单（订单号：" + no + "）提醒：" + info;
//                // Integer appId, String mobilePhone, String content  TODO  appId:具体哪个应用的ID
//                SmsResponse result = smsService.send(2, user.getMobilePhone(), content);
//                if (result == null || !result.getIsSuccessful()) {
//                    data.setCode(-1);
//                    data.setMsg("发短信失败");
//                }
//            }
//        }
//        return data;
//    }
//
//    @RequestMapping({"/follow"})
//    public ResponseData follow(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        if (orderProdId <= 0) {
//            data.setCode(-1);
//            data.setMsg("产品订单ID错误");
//            return data;
//        }
//
//        SoOrderProdUserMap soOrderProdUserMap = new SoOrderProdUserMap();
//        soOrderProdUserMap.setOrderProdId(orderProdId);
//        soOrderProdUserMap.setUserId(loginUser.getUcUser().getPkid());
//        // 关系类型序号，type=306，3061业务、3062客服（关注）、3063操作
//        soOrderProdUserMap.setTypeId(3062);
//        soOrderProdUserMap.setStatusId(0);
//        soOrderProdUserMap.setAddTime(new Date());
//
//        Integer id = soOrderProdUserMapService.insert(soOrderProdUserMap);
//        if (id == null || id <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping({"/cancelFollow"})
//    public ResponseData cancelFollow(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        if (orderProdId <= 0) {
//            data.setCode(-1);
//            data.setMsg("产品订单ID错误");
//            return data;
//        }
//
//        // 关系类型序号，type=306，3061业务、3062客服（关注）、3063操作
//        int result = soOrderProdUserMapService.deleteInfo(loginUser.getUcUser().getPkid(), orderProdId, 3062);
//        if (result <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    @RequestMapping({"/addResponsibility"})
//    public ResponseData addResponsibility(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 获取所要添加的用户ID加密串集合
//        String userIdStrs = StringUtils.trimToEmpty(request.getParameter("userIdStrs"));
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        if (orderProdId <= 0) {
//            data.setCode(-1);
//            data.setMsg("产品订单ID错误");
//            return data;
//        }
//        if (StringUtils.isBlank(userIdStrs)) {
//            data.setCode(-1);
//            data.setMsg("用户ID为空");
//            return data;
//        }
//
//        String[] idStrs = StringUtils.split(userIdStrs, ",");
//
//        int userId = 0;
//        String useSIdStr = null;
//        List<SoOrderProdUserMap> list = new ArrayList<>();
//        List<Integer> userIdList = new ArrayList<>();
//        for (int i = 0; i < idStrs.length; i++) {
//            useSIdStr = SecurityUtils.rc4Decrypt(idStrs[i]);
//            userId = NumberUtils.toInt(useSIdStr);
//            if (userId <= 0) {
//                continue;
//            }
//            userIdList.add(userId);
//            SoOrderProdUserMap soOrderProdUserMap = new SoOrderProdUserMap();
//            soOrderProdUserMap.setOrderProdId(orderProdId);
//            soOrderProdUserMap.setUserId(userId);
//            // 关系类型序号，type=306，3061业务、3062客服（关注）、3063操作
//            soOrderProdUserMap.setTypeId(3063);
//            // 订单项和用户关系状态，type=314，3141正在负责、3142曾经负责
//            soOrderProdUserMap.setStatusId(3141);
//            soOrderProdUserMap.setAddTime(new Date());
//            list.add(soOrderProdUserMap);
//        }
//        soOrderProdUserMapService.insertBatch(list);
//
//        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//        if (isSend && !loginUser.getRoleTags().contains(RoleTag.ROLE_GLY)) {
//            try {
//                soOrderProdService.sendfzrEmail(orderProdId, userIdList);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        SoOrderProd orderProd = soOrderProdService.findById(orderProdId);
//        if (orderProd == null) {
//            data.setCode(-1);
//            data.setMsg("产品订单不存在");
//            return data;
//        }
//        SoOrder order = soOrderService.findById(NumberUtils.toInt(orderProd.getOrderId()));
//        if (order == null) {
//            data.setCode(-1);
//            data.setMsg("订单不存在");
//            return data;
//        }
//
//        Map<Integer, UcUser> userMap = ucUserService.findMapByIds(userIdList);
//
//        UcUser user = null;
//        List<String> names = new ArrayList<>();
//        for(SoOrderProdUserMap orderProdUserMap : list) {
//            user = userMap.get(orderProdUserMap.getUserId());
//            if(user != null && StringUtils.isNotBlank(user.getRealName())) {
//                names.add(user.getRealName());
//            }
//        }
//
//        SoOrderProdTrace soOrderProdTrace = new SoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(orderProdId);
//        soOrderProdTrace.setOrderProdStatusId(orderProd.getProcessStatusId());
//        /** 315 订单项记录类型：3151 更改状态、3152 备注、3153 上传材料、3154 提示客户、3155 快递、3156 帐号密码、3157 标记投诉、3158 提醒负责人 */
//        soOrderProdTrace.setTypeId(3158);
//        soOrderProdTrace.setInfo("【"+loginUser.getUcUser().getRealName()+"】添加【"+StringUtils.join(names, ",")+"】为负责人");
//        soOrderProdTrace.setIsSendSms(0);
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(loginUser.getUcUser().getPkid());
//        soOrderProdTrace.setAddTime(new Date());
//        soOrderProdTrace.setRemark("");
//        soOrderProdTrace.setExpressContent("");
//        soOrderProdTrace.setExpressTo("");
//        soOrderProdTrace.setExpressCompanyName("");
//        soOrderProdTrace.setExpressNo("");
//        soOrderProdTrace.setProcessdDays(0);
//        soOrderProdTrace.setTimeoutDays(0);
//        // 插入订单项跟进记录
//        soOrderProdTraceService.insert(soOrderProdTrace);
//
//
//        String mail = null;
//        Map<Integer, List<SoOrderProdUserMap>> map = soOrderProdUserMapService.findYWYByOrderProdIds(Arrays.asList(orderProdId));
//        if(MapUtils.isNotEmpty(map)) {
//            List<SoOrderProdUserMap> lstOrderProdUserMap = map.get(orderProdId);
//            if(CollectionUtils.isNotEmpty(lstOrderProdUserMap)) {
//                mail = lstOrderProdUserMap.get(0).getUcUser().getEmail();
//            }
//        }
//
//        try {
//            if(StringUtils.isNotBlank(mail)) {
//                Map<Integer, CrmCompanyIntention> companyMap = crmCompanyIntentionService.findMapByIds(Arrays.asList(order.getCompanyId(), orderProd.getCompanyId()));
//                CrmCompanyIntention orderCompany = companyMap.get(order.getCompanyId());
//                CrmCompanyIntention orderProdCompany = companyMap.get(orderProd.getCompanyId());
//
//                List<String> companyNameList = new ArrayList<>();
//                if (null != orderCompany && StringUtils.isNotBlank(orderCompany.getCompanyName())) {
//                    companyNameList.add("订单公司【"+ orderCompany.getCompanyName() + "】");
//                }
//                if (null != orderProdCompany && StringUtils.isNotBlank(orderProdCompany.getCompanyName())) {
//                    companyNameList.add("明细订单公司【" + orderProdCompany.getCompanyName() + "】");
//                }
//
//                String companyName = "";
//                if (CollectionUtils.isNotEmpty(companyNameList)) {
//                    companyName = "，" + StringUtils.join(companyNameList, "，");
//                }
//
//                String html = "您跟进中的订单【"+order.getNo()+"】由【"+loginUser.getUcUser().getRealName()+"】添加【"+StringUtils.join(names, ",")+"】为负责人" + companyName;
//                String title = "您跟进中的订单【"+order.getNo()+"】负责人变动提醒";
//                emailService.sendMail(mail, html, title);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }

}