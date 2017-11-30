package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;


@Path("/soorderprodtrace")
public class SoOrderProdTraceController {
//
//    private Log log = LogFactory.getLog(SoOrderProdTraceController.class);
//
//    @Autowired
//    private SoOrderProdTraceService soOrderProdTraceService;
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//    @Autowired
//    private SoOrderProdUserMapService soOrderProdUserMapService;
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private SoOrderProdAccountService soOrderProdAccountService;
//    @Autowired
//    private ProdWorkflowNodeService prodWorkflowNodeService;
//    @Autowired
//    private ProdWorkflowFileService prodWorkflowFileService;
//    @Autowired
//    private SoOrderProdTraceFileService soOrderProdTraceFileService;
//    @Autowired
//    private SmsService smsService;
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private TemplateService templateService;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private CrmCompanyIntentionService crmCompanyIntentionService;
//
//    @RequestMapping("/updatestatus")
//    public ResponseData updatestatus(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> param) {
//
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(param.get("orderProdId"));
//        String processStatusId = StringUtils.trimToEmpty(param.get("processStatusId"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(processStatusId)) {
//            data.setMsg("订单产品状态id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer isSendSms = 0;
//        if (StringUtils.trimToEmpty(param.get("isSendSms")) != null) {
//            isSendSms = NumberUtils.toInt(StringUtils.trimToEmpty(param.get("isSendSms")));
//        }
//        SoOrderProd soOrderProd = new SoOrderProd();
//        soOrderProd.setPkid(NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//        soOrderProd = soOrderProdService.findById(soOrderProd.getPkid());
//        Integer oldProcessStatusId = soOrderProd.getProcessStatusId();
//        Integer newProcessStatusId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(processStatusId));
//        soOrderProd.setProcessStatusId(newProcessStatusId);
//
//        SoOrderProdTrace soOrderProdTrace = new SoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3151);
//        soOrderProdTrace.setIsSendSms(isSendSms);
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//
//        Map<Integer, ProdWorkflowNode> workflowMap = prodWorkflowNodeService.findMapByIds(new ArrayList<Integer>() {{
//            add(oldProcessStatusId);
//            add(newProcessStatusId);
//        }});
//
//        ProdWorkflowNode oldBdDict = workflowMap.get(oldProcessStatusId);
//        ProdWorkflowNode newBdDict = workflowMap.get(newProcessStatusId);
//        soOrderProdTrace.setInfo("更新状态:" + newBdDict.getName());
//        if (null != oldBdDict && null != newBdDict && oldBdDict.getSort() > newBdDict.getSort()) {
//            soOrderProdTrace.setOperatorType(315101);
//            soOrderProdTrace.setInfo("更新状态(回退): " + newBdDict.getName());
//        }
//
//        List<ProdWorkflowNode> prodWorkflowNodeList = prodWorkflowNodeService.queryWorkflowNodeListByOrderProdId(soOrderProd.getPkid());
//        if (CollectionUtils.isEmpty(prodWorkflowNodeList)) {
//            data.setMsg("未找到流程方案");
//            data.setCode(402);
//            return data;
//        }
//        ProdWorkflowNode prodWorkflowNode = prodWorkflowNodeList.get(prodWorkflowNodeList.size() - 1);
//        if (soOrderProd.getProcessStatusId().compareTo(prodWorkflowNode.getPkid()) == 0) {
//            List<ProdWorkflowFile> prodWorkflowFileList = prodWorkflowFileService.queryWorkflowFileListByOrderProdId(soOrderProd.getPkid());
//            List<Integer> ismustProdWorkflowFilelist = new ArrayList();
//            if (CollectionUtils.isNotEmpty(prodWorkflowFileList)) {
//                for (ProdWorkflowFile prodWorkflowFile : prodWorkflowFileList) {
//                    if (prodWorkflowFile.getIsMust() == 1) {
//                        ismustProdWorkflowFilelist.add(prodWorkflowFile.getPkid());
//                    }
//                }
//            }
//
//            if (!CollectionUtils.isNotEmpty(ismustProdWorkflowFilelist)) {
//                Map<String, Object> soOrderProdTraceMap = new HashMap();
//                soOrderProdTraceMap.put("order_prod_id", soOrderProdTrace.getOrderProdId());
//                soOrderProdTraceMap.put("type_id", 3153);
//                List<SoOrderProdTrace> soOrderProdTraceList = soOrderProdTraceService.findByProperties(soOrderProdTraceMap, 0, Integer.MAX_VALUE);
//                if (CollectionUtils.isEmpty(soOrderProdTraceList)) {
//                    data.setMsg("必须材料没有上传完");
//                    data.setCode(402);
//                    return data;
//                }
//
//                List<Integer> soOrderProdTraceIds = new ArrayList();
//                for (SoOrderProdTrace item : soOrderProdTraceList) {
//                    soOrderProdTraceIds.add(item.getPkid());
//                }
//                List<Integer> soOrderProdTraceFileList = soOrderProdTraceFileService.findProdWorkflowFileIdByOrderProdTraceId(soOrderProdTraceIds);
//
////              新写法
//                ismustProdWorkflowFilelist.removeAll(soOrderProdTraceFileList);
//                if (CollectionUtils.isNotEmpty(ismustProdWorkflowFilelist)) {
//                    data.setMsg("必须材料没有上传完");
//                    data.setCode(402);
//                    return data;
//                }
////                这种神级代码...
////                Integer ismustnum = 0;
////                for (Integer itemint : ismustProdWorkflowFilelist) {
////                    for (Integer item : soOrderProdTraceFileList) {
////                        if (itemint.equals(item)) {
////                            ismustnum++;
////                            continue;
////                        }
////                    }
////                }
////
////                if (ismustnum != ismustProdWorkflowFilelist.size()) {
////                    data.setMsg("必须材料没有上传完");
////                    data.setCode(402);
////                    return data;
////                }
//            }
//
//            soOrderProd.setAuditStatusId(1052);
//            Boolean internal_check = Boolean.valueOf(PropertiesReader.getValue("project", "internal_check"));
//            // 内部人员并且设置了不审核属性  则直接审核通过
//            if (!internal_check && NumberUtils.toInt(user.getUcUser().getIsInner()) == 1) {
//                soOrderProd.setAuditStatusId(1054);
//            }
//        }
//        if (isSendSms == 1) {
//            SoOrder soOrder = soOrderService.findById(soOrderProd.getOrderId());
//            Calendar now = Calendar.getInstance();
//            String smsString = "【公司宝】您的订单：" + soOrder.getNo() + ",已于" + now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH) + 1) +
//                    "月" + now.get(Calendar.DAY_OF_MONTH) + "日变更为“" + newBdDict.getName() + "”状态。如需帮助，请拨打服务热线：4006-798-999。";
//            //发送短信
//            new Thread() {
//                @Override
//                public void run() {
//                    smsService.send(2, soOrder.getAccountMobile(), smsString);
//                }
//            }.start();
//        }
//
//        data.setData(soOrderProdTraceService.updateStatus(soOrderProd, soOrderProdTrace));
//
//
//        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//        if (isSend) {
//            sendOPEmail(soOrderProd, user, "更改状态为：" + newBdDict.getName());
//        }
//
//        return data;
//    }
//
//    @RequestMapping("/addremark")
//    public ResponseData addremark(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(param.get("orderProdId"));
//        String info = StringUtils.trimToEmpty(param.get("info"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(info)) {
//            data.setMsg("备注内容不能为空");
//            data.setCode(402);
//            return data;
//        }
//        SoOrderProd soOrderProd = soOrderProdService.findById(NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//        if (soOrderProd == null) {
//            data.setMsg("产品订单不存在");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProdTrace soOrderProdTrace = newSoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3152);
//        soOrderProdTrace.setInfo("备注: " + info);
//        soOrderProdTrace.setIsSendSms(0);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//
//        Map<String, Object> userMap = new HashMap<>();
//        userMap.put("orderProdId", soOrderProd.getPkid());
//        userMap.put("userId", user.getUcUser().getPkid());
//        List<SoOrderProdUserMap> soOrderProdUserMapList = soOrderProdUserMapService.findByProperties(new HashMap<>(), 0, 1);
//        SoOrderProdUserMap soOrderProdUserMap = soOrderProdUserMapList.get(0);
//        if (soOrderProdUserMap.getTypeId() == 3063) {
//            soOrderProdTrace.setOperatorType(315201);
//        } else if (soOrderProdUserMap.getTypeId() == 3061 || soOrderProdUserMap.getTypeId() == 3062) {
//            soOrderProdTrace.setOperatorType(315202);
//        } else {
//            soOrderProdTrace.setOperatorType(0);
//        }
//
//        data.setData(soOrderProdTraceService.findById(soOrderProdTraceService.insert(soOrderProdTrace)));
//
//        boolean isSend = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"));
//        if (isSend) {
//            sendOPEmail(soOrderProd, user, "备注修改为：" + info);
//        }
//        return data;
//    }
//
//    private void sendOPEmail(final SoOrderProd orderProd, final LoginUser opUser, final String opName) {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    String now = DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm");
//
//                    String title = "[进度] 亲！你的{companyName}{orderNo}进度在{TIME}有更新呦~~~！~！马上看看吧！";
//                    String prodId = String.valueOf(orderProd.getPkid());
//                    String orderNo = null;
//                    String accountName = null;
//                    String accountMobile = null;
//                    String companyName = null;
//                    String orderCompanyName = null;
//                    String prodName = null;
//                    String followLog = "跟进记录：“{CZR}”已进行如下操作：“{CZ}”。操作时间：{TIME}";
//                    String opUserName = opUser.getUcUser().getRealName();
//                    String processName = null;
//                    String czyNames = null;
//
//                    ProdWorkflowNode node = prodWorkflowNodeService.findById(orderProd.getProcessStatusId());
//                    if (null != node) {
//                        processName = node.getName();
//                    }
//
//                    SoOrder order = soOrderService.findById(orderProd.getOrderId());
//                    if (order.getCompanyId() > 0) {
//                        CrmCompanyIntention company = crmCompanyIntentionService.findById(order.getCompanyId());
//                        if (null != company) {
//                            companyName = company.getMyName();
//                        }
//                    }
//                    CrmCompanyIntention prodCompany = crmCompanyIntentionService.findById(orderProd.getCompanyId());
//                    if (null != prodCompany) {
//                        orderCompanyName = prodCompany.getMyName();
//                    }
//
//                    orderProd.getCompanyId();
//
//                    Map<Integer, List<SoOrderProdUserMap>> prodUserMap = soOrderProdUserMapService.findYWYByOrderProdIds(Arrays.asList(orderProd.getPkid()));
//                    List<SoOrderProdUserMap> userMapList = prodUserMap.get(orderProd.getPkid());
//                    if (CollectionUtils.isEmpty(userMapList)) {
//                        log.info("sendOPEmail " + prodId + " no ywy in orderprod");
//                        return;
//                    }
//
//                    List<SoOrderProdUserMap> czyList = soOrderProdUserMapService.findCZYByOrderProdIds(Arrays.asList(orderProd.getPkid())).get(orderProd.getPkid());
//                    if (CollectionUtils.isNotEmpty(czyList)) {
//                        Set<String> czySet = new HashSet<>();
//                        for (SoOrderProdUserMap userMap : czyList) {
//                            if (StringUtils.isBlank(userMap.getUserName())) {
//                                continue;
//                            }
//                            czySet.add(userMap.getUserName());
//                        }
//
//                        czyNames = StringUtils.join(czySet, ",");
//                    }
//
//                    List<Integer> userIds = userMapList.stream().map(SoOrderProdUserMap::getUserId).collect(Collectors.toList());
//                    List<UcUser> userList = ucUserService.findByIds(userIds);
//
//                    List<String> emailList = new ArrayList<>();
//
//                    for (UcUser ucUser : userList) {
//                        String email = ucUser.getEmail();
//                        if (RegexUtils.isNotEmail(email)) {
//                            continue;
//                        }
//                        emailList.add(email);
//                    }
//
//                    if (CollectionUtils.isEmpty(emailList)) {
//                        log.info("sendOPEmail" + prodId + " no email in ywy" + StringUtils.join(userIds, ","));
//                        return;
//                    }
//
//                    orderNo = order.getNo() + " - " + prodId;
//                    accountName = order.getAccountName();
//                    accountMobile = order.getAccountMobile();
//                    prodName = orderProd.getProductName();
//
//                    title = title.replace("{companyName}", StringUtils.trimToEmpty(companyName));
//                    title = title.replace("{orderNo}", orderNo).replace("{TIME}", now);
//
//                    followLog = followLog.replace("{CZR}", StringUtils.trimToEmpty(opUserName))
//                            .replace("{CZ}", StringUtils.trimToEmpty(opName))
//                            .replace("{TIME}", now);
//
//                    Map<String, Object> params = new HashMap<>();
//                    params.put("opName", StringUtils.isBlank(opName) ? "-" : opName);
//                    params.put("opUserName", StringUtils.isBlank(opUserName) ? "-" : opUserName);
//                    params.put("now", now);
//                    params.put("opStatus", StringUtils.isBlank(processName) ? "-" : processName);
//
//                    params.put("orderNo", orderNo);
//                    params.put("orderAddTime", DateUtils.dateStr(order.getAddTime()));
//                    params.put("accountName", StringUtils.isBlank(accountName) ? "-" : accountName);
//                    params.put("accountMobile", StringUtils.isBlank(accountMobile) ? "-" : accountMobile);
//                    params.put("companyName", StringUtils.isBlank(companyName) ? "-" : companyName);
//                    params.put("orderCompanyName", StringUtils.isBlank(orderCompanyName) ? "-" : orderCompanyName);
//
//                    params.put("prodName", StringUtils.isBlank(prodName) ? "-" : prodName);
//                    params.put("czyNames", StringUtils.isBlank(czyNames) ? "-" : czyNames);
//                    params.put("followLog", StringUtils.isBlank(followLog) ? "-" : followLog);
//
//                    // 订单公司
//                    String content = templateService.process(params, "email/product_operate_mail");
//                    String mailTo = emailList.get(0);
////                    if (NumberUtils.toInt(order.getType()) == 2) {
////                        emailList.add("chiying@sino-tone.com"); 加上没几天又删掉，没比操闲的
////                    }
//                    String[] cc = emailList.toArray(new String[]{});
//                    emailService.sendMail(mailTo, cc, content, title);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    @RequestMapping("/addfile")
//    public ResponseData addfile(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(param.get("orderProdId"));
//        String prodWorkflowFileId = StringUtils.trimToEmpty(param.get("prodWorkflowFileId"));
//        String prodWorkflowFileName = StringUtils.trimToEmpty(param.get("prodWorkflowFileName"));
//        String fileIdStr = StringUtils.trimToEmpty(param.get("fileId"));
//        String info = StringUtils.trimToEmpty(param.get("info"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(prodWorkflowFileId)) {
//            data.setMsg("上传文件类型id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(prodWorkflowFileName)) {
//            data.setMsg("上传文件名称不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(fileIdStr)) {
//            data.setMsg("上传文件id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(info)) {
//            data.setMsg("备注信息不能为空");
//            data.setCode(402);
//            return data;
//        }
//        String[] fileIds = fileIdStr.split(",");
//        if (fileIds == null || fileIds.length <= 0) {
//            data.setMsg("上传文件id不能为空");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProd soOrderProd = soOrderProdService.findById(NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//        if (soOrderProd == null) {
//            data.setMsg("产品订单不存在");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProdTrace soOrderProdTrace = newSoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3153);
//        soOrderProdTrace.setInfo("文件上传: " + prodWorkflowFileName);
//        soOrderProdTrace.setIsSendSms(0);
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//
//        List<SoOrderProdTraceFile> traceFileList = new ArrayList<>();
//        for (String fileId : fileIds) {
//            SoOrderProdTraceFile soOrderProdTraceFile = new SoOrderProdTraceFile();
//            if (prodWorkflowFileId.equals("0")) {
//                soOrderProdTraceFile.setProdWorkflowFileId(NumberUtils.toInt(prodWorkflowFileId));
//            } else {
//                soOrderProdTraceFile.setProdWorkflowFileId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(prodWorkflowFileId)));
//            }
//            soOrderProdTraceFile.setProdWorkflowFileName(prodWorkflowFileName);
//            soOrderProdTraceFile.setFileId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(fileId)));
//            soOrderProdTraceFile.setAuditStatusId(1051);
//            soOrderProdTraceFile.setIsNew(1);
//            soOrderProdTraceFile.setAddUserId(user.getUcUser().getPkid());
//            soOrderProdTraceFile.setRemark(info);
//            traceFileList.add(soOrderProdTraceFile);
//        }
//
//        data.setData(soOrderProdTraceService.addfile(soOrderProdTrace, traceFileList));
//        return data;
//    }
//
//    @RequestMapping("/tipcustomers")
//    public ResponseData tipcustomers(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(param.get("orderProdId"));
//        String info = StringUtils.trimToEmpty(param.get("info"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(info)) {
//            data.setMsg("提醒内容不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer isSendSms = 0;
//        if (param.get("isSendSms") != null) {
//            isSendSms = NumberUtils.toInt(param.get("isSendSms"));
//        }
//        SoOrderProd soOrderProd = soOrderProdService.findById(NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//        if (soOrderProd == null) {
//            data.setMsg("产品订单不存在");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProdTrace soOrderProdTrace = newSoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3154);
//        soOrderProdTrace.setInfo("提醒内容: " + info);
//        soOrderProdTrace.setIsSendSms(isSendSms);
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//
//        if (isSendSms == 1) {
//            //发短信
//            SoOrder soOrder = soOrderService.findById(soOrderProd.getOrderId());
//            String smsString = "【公司宝】尊敬的公司宝用户您好：" + info + "。如需帮助，请拨打服务热线：4006-798-999。";
//            //发送短信
//            new Thread() {
//                @Override
//                public void run() {
//                    smsService.send(2, soOrder.getAccountMobile(), smsString);
//                }
//            }.start();
//        }
//        data.setData(soOrderProdTraceService.findById(soOrderProdTraceService.insert(soOrderProdTrace)));
//
//        return data;
//    }
//
//    @RequestMapping("/sendexpress")
//    public ResponseData sendexpress(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(param.get("orderProdId"));
//        String expressContent = StringUtils.trimToEmpty(param.get("expressContent"));
//        String expressTo = StringUtils.trimToEmpty(param.get("expressTo"));
//        String expressCompanyName = StringUtils.trimToEmpty(param.get("expressCompanyName"));
//        String expressNo = StringUtils.trimToEmpty(param.get("expressNo"));
//        String info = StringUtils.trimToEmpty(param.get("info"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(expressContent)) {
//            data.setMsg("快递清单不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(expressTo)) {
//            data.setMsg("收件人不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(expressCompanyName)) {
//            data.setMsg("快递公司不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(expressNo)) {
//            data.setMsg("快递单号不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(info)) {
//            data.setMsg("补充说明不能为空");
//            data.setCode(402);
//            return data;
//        }
//
//        Integer isSendSms = NumberUtils.toInt(StringUtils.trimToEmpty(param.get("isSendSms")));
//
//        SoOrderProd soOrderProd = soOrderProdService.findById(NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//        if (soOrderProd == null) {
//            data.setMsg("产品订单不存在");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProdTrace soOrderProdTrace = new SoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3155);
//        soOrderProdTrace.setInfo("快递: " + expressContent + "，" + expressCompanyName + "：" + expressNo + "。补充说明：" + info);
//        soOrderProdTrace.setIsSendSms(isSendSms);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//        soOrderProdTrace.setRemark("");
//        soOrderProdTrace.setExpressContent(expressContent);
//        soOrderProdTrace.setExpressTo(expressTo);
//        soOrderProdTrace.setExpressCompanyName(expressCompanyName);
//        soOrderProdTrace.setExpressNo(expressNo);
//        soOrderProdTrace.setProcessdDays(0);
//        soOrderProdTrace.setTimeoutDays(0);
//        soOrderProdTrace.setOperatorType(0);
//        if (isSendSms == 1) {
//            //发短信
//            SoOrder soOrder = soOrderService.findById(soOrderProd.getOrderId());
//            String smsString = "【公司宝】您的订单：" + soOrder.getNo() + "有一条新快递信息。快递清单：" + expressContent + "。快递公司：" + expressCompanyName + "。" +
//                    "快递单号：" + expressNo + "。发件人留言：" + info + "。如需帮助，请拨打服务热线：4006-798-999。";
//            //发送短信
//            new Thread() {
//                @Override
//                public void run() {
//                    smsService.send(2, soOrder.getAccountMobile(), smsString);
//                }
//            }.start();
//        }
//
//        data.setData(soOrderProdTraceService.findById(soOrderProdTraceService.insert(soOrderProdTrace)));
//
//        return data;
//    }
//
//    @RequestMapping("/markcomplaints")
//    public ResponseData markcomplaints(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(param.get("orderProdId"));
//        String info = StringUtils.trimToEmpty(param.get("info"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(info)) {
//            data.setMsg("投诉说明不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer isSendSms = NumberUtils.toInt(param.get("isSendSms"));
//
//        Integer isFocus = NumberUtils.toInt(param.get("isFocus"));
//
//        SoOrderProd soOrderProd = soOrderProdService.findById(NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//        if (soOrderProd == null) {
//            data.setMsg("产品订单不存在");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProdTrace soOrderProdTrace = newSoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3157);
//        soOrderProdTrace.setInfo("投诉: " + info);
//        soOrderProdTrace.setIsSendSms(0);
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//        if (isSendSms != 0) {
//            Map<String, Object> userWhere = new HashMap();
//            userWhere.put("order_prod_id", soOrderProd.getPkid());
//            userWhere.put("type_id", 3061);
//            List<SoOrderProdUserMap> soOrderProdUserMapList = soOrderProdUserMapService.findByProperties(userWhere, 0, Integer.MAX_VALUE);
//            if (soOrderProdUserMapList.size() > 0) {
//                List<Integer> userIds = new ArrayList();
//                for (SoOrderProdUserMap item : soOrderProdUserMapList) {
//                    userIds.add(item.getUserId());
//                }
//                List<UcUser> ucusers = ucUserService.findByIds(userIds);
//                //给这些业务员们发短信
//                for (UcUser ucUser : ucusers) {
//                    //发短信
//                    SoOrder soOrder = soOrderService.findById(soOrderProd.getOrderId());
//                    String smsString = "【公司宝】" + ucUser.getRealName() + "，您好！" + user.getUcUser().getRealName() + "给您发了一条订单（订单号：" + soOrder.getNo() + "）提醒：" + info + "。如需帮助，请拨打服务热线：4006-798-999。";
//                    //发送短信
//                    new Thread() {
//                        @Override
//                        public void run() {
//                            smsService.send(2, ucUser.getMobilePhone(), smsString);
//                        }
//                    }.start();
//                }
//            }
//        }
//        if (isFocus != 0) {
//            SoOrderProdUserMap soOrderProdUserMap = new SoOrderProdUserMap();
//            soOrderProdUserMap.setUserId(0);
//            soOrderProdUserMap.setOrderProdId(soOrderProd.getPkid());
//            soOrderProdUserMap.setTypeId(3062);
//            soOrderProdUserMap.setStatusId(3141);
//            soOrderProdUserMapService.insert(soOrderProdUserMap);
//        }
//        soOrderProdService.updateIsComplaint(soOrderProdService.findById(soOrderProdTrace.getOrderProdId()));
//
//        data.setData(soOrderProdTraceService.findById(soOrderProdTraceService.insert(soOrderProdTrace)));
//
//        return data;
//    }
//
//    @RequestMapping("/getrecordinfo")
//    public ResponseData getrecordinfo(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String orderProdId = StringUtils.trimToEmpty(request.getParameter("orderProdId"));
//        if (StringUtils.isBlank(orderProdId)) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        String page = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        String pagesize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        Map<String, Object> accountWhere = new HashMap();
//        accountWhere.put("order_prod_id", NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdId)));
//
//        data.setData(soOrderProdAccountService.pageByProperties(accountWhere, NumberUtils.toInt(page), NumberUtils.toInt(pagesize)));
//
//        return data;
//    }
//
//    @RequestMapping("/editrecordinfo")
//    public ResponseData editrecordinfo(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> paramMap) {
//        ResponseData data = new ResponseData();
//        String param = JsonUtils.objectToJson(paramMap.get("param"));
//        if (StringUtils.isEmpty(param)) {
//            data.setMsg("参数不能为空");
//            data.setCode(402);
//            return data;
//        }
//        RequestOrderProd list = JsonUtils.jsonToObject(param, RequestOrderProd.class);
//
//        if (list == null) {
//            data.setMsg("参数不正确");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(list.getOrderProdId())) {
//            data.setMsg("订单产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (CollectionUtils.isEmpty(list.getRecordInfoList())) {
//            data.setMsg("账号密码信息不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(list.getOrderProdId()));
//
//        SoOrderProd soOrderProd = soOrderProdService.findById(orderProdId);
//        if (soOrderProd == null) {
//            data.setMsg("产品订单不存在");
//            data.setCode(402);
//            return data;
//        }
//
//        SoOrderProdTrace soOrderProdTrace = newSoOrderProdTrace();
//        soOrderProdTrace.setOrderProdId(soOrderProd.getPkid());
//        soOrderProdTrace.setOrderProdStatusId(soOrderProd.getProcessStatusId());
//        soOrderProdTrace.setTypeId(3156);
//        soOrderProdTrace.setInfo("资料信息: 账号密码已保存。");
//        soOrderProdTrace.setIsSendSms(0);
//        soOrderProdTrace.setOperatorType(0);
//        soOrderProdTrace.setOperatorId(user.getUcUser().getPkid());
//
//        List<SoOrderProdAccount> sopalist = new ArrayList();
//        for (RequestOrderProdAccount item : list.getRecordInfoList()) {
//            SoOrderProdAccount sopaitem = new SoOrderProdAccount();
//            if (item.getId() != null) {
//                sopaitem.setPkid(Integer.valueOf(SecurityUtils.rc4Decrypt(item.getId())));
//            }
//            sopaitem.setOrderProdId(orderProdId);
//            sopaitem.setAccount(item.getAccount());
//            sopaitem.setPasswd(item.getPasswd());
//            sopaitem.setAddUserId(user.getUcUser().getPkid());
//            sopaitem.setRemark(item.getRemark());
//            sopaitem.setIsCrawl(item.getIsCrawl());
//            sopalist.add(sopaitem);
//        }
//
//        data.setData(soOrderProdTraceService.saveAccount(soOrderProdTrace, sopalist));
//
//        return data;
//    }
//
//    private SoOrderProdTrace newSoOrderProdTrace() {
//        SoOrderProdTrace soOrderProdTrace = new SoOrderProdTrace();
//        soOrderProdTrace.setRemark("");
//        soOrderProdTrace.setExpressContent("");
//        soOrderProdTrace.setExpressTo("");
//        soOrderProdTrace.setExpressCompanyName("");
//        soOrderProdTrace.setExpressNo("");
//        soOrderProdTrace.setProcessdDays(0);
//        soOrderProdTrace.setTimeoutDays(0);
//        return soOrderProdTrace;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
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
//        if (orderProdId > 0) {
//            condition.put("order_prod_id", orderProdId);
//        }
//
//        Pager<SoOrderProdTrace> pager = soOrderProdTraceService.pageByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    //更新追踪记录颜色
//    @RequestMapping("/updatecolor")
//    public ResponseData updateTipColor(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        Integer pkId = NumberUtils.toInt(request.getParameter("pkId"));
//        String tipColor = StringUtils.trimToEmpty(request.getParameter("tipcolor"));
//
//        if (pkId == 0) {
//            data.setMsg("记录编号为空");
//            return data;
//        }
//
//        if (StringUtils.isBlank(tipColor)) {
//            tipColor = "default";
//        }
//
//        soOrderProdTraceService.updateTipColor(tipColor, pkId);
//
//        data.setCode(200);
//        return data;
//
//    }
}