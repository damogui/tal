package org.netsharp.api.controller.order;


import javax.ws.rs.Path;

@Path("/api/socontract")
public class SoContractController {
//
//    @Autowired
//    private SoContractService soContractService;
//    @Autowired
//    private ProdPriceService prodPriceService;
//    @Autowired
//    private ProdServiceService prodServiceService;
//    @Autowired
//    private BdDictService bdDictService;
//    @Autowired
//    private ProdProductService prodProductService;
//    @Autowired
//    private UcAccountService ucAccountService;
//    @Autowired
//    private ProdPriceAuditService prodPriceAuditService;
//    @Autowired
//    private BdAuditLogService bdAuditLogService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoContract soContract) {
//        ResponseData data = new ResponseData();
//        soContractService.insert(soContract);
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
//        soContractService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoContract soContract) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soContract.setPkid(pkid);
//        soContractService.update(soContract);
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
//        Pager<SoContract> pager = soContractService.pageByProperties(null, Integer.valueOf(page));
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
//        SoContract soContract = soContractService.findById(pkid);
//        data.setData(soContract);
//        return data;
//    }
//
//    @RequestMapping("create")
//    public ResponseData addContract(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        String json = StringUtils.trimToEmpty(request.getParameter("param"));
//        SoContract soContract = JsonUtils.jsonToObject(json, SoContract.class);
//
//        if(StringUtils.isBlank(soContract.getAccountMobile())){
//            data.setCode(-1);
//            data.setMsg("请选择联系人手机");
//
//            return data;
//        }
//
//        if(StringUtils.isBlank(soContract.getEmail())){
//            data.setCode(-1);
//            data.setMsg("请输入邮箱");
//
//            return data;
//        }
//
//        if(NumberUtils.toInt(soContract.getIsElectronics(), -1) < 0) {
//            data.setCode(-1);
//            data.setMsg("请输入是否电子合同");
//            return data;
//        }
//
//        if(NumberUtils.toInt(soContract.getIsElectronics(), -1) == 1) {
//            if(StringUtils.isBlank(soContract.getIdentityCard())){
//                data.setCode(-1);
//                data.setMsg("请输入身份证");
//                return data;
//            }
//        }
//
//        if(StringUtils.isBlank(soContract.getContractTitle())){
//            data.setCode(-1);
//            data.setMsg("请输入合同标题");
//
//            return data;
//        }
//
//        if(NumberUtils.toInt(soContract.getContractType()) == 2) {
//            if(StringUtils.isBlank(soContract.getCompanyName())){
//                data.setCode(-1);
//                data.setMsg("请输入公司名称");
//
//                return data;
//            }
//            if(StringUtils.isBlank(soContract.getLicenseNo())){
//                data.setCode(-1);
//                data.setMsg("请输入营业执照号");
//
//                return data;
//            }
//        }
//
//        //比对方法,是否必选项
//        if (!checkMustServiceItem(soContract.getProdList())) {
//            data.setCode(200);
//            data.setMsg("必选服务未选中");
//            return data;
//        }
//
//        /*当【材料撰写次数类型】是“首期一次末期一次”时，则首末期款为必填*/
//        if (soContract.getDataFeeCountTypeId().equals(3174) && soContract.getFirstPayment().equals(0) && soContract.getFinalPayment().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("");
//            return data;
//        }
//
//        //总价
//        int totalPrice = 0;
//        int payablePrice = 0;
//        String prodName = new String();
//        Date dt = new Date();
//
//        //通过产品服务定价ID查找原价
//        if (soContract.getProdList().size() != 0) {
//            for (SoOrderProd soOrderProd : soContract.getProdList()) {
//                //产品总价
//                int priceOriginal = 0;
//                int price = 0;
//                //查询prod信息
//                for (SoOrderProdItem soOrderProdItem : soOrderProd.getItemList()) {
//                    //查询产品定价相关信息
//                    ProdPrice prodPrice = prodPriceService.findById(Integer.valueOf(SecurityUtils.rc4Decrypt(soOrderProdItem.getPriceIdStr())));
//                    priceOriginal += prodPrice.getOriginalPrice();
//                    price += soOrderProdItem.getPrice();
//
//                    soOrderProdItem.setPriceId(prodPrice.getPkid());  //产品id
//                    //产品服务项
//                    ProdService prodService = prodServiceService.findById(prodPrice.getServiceId());
//
//                    soOrderProdItem.setUnitName(bdDictService.queryDictName(202, prodService.getUnitId()));
//
//                    //特性+服务名称
//                    String propertServicename;
//                    if (prodService.getPropertyId().equals(0))
//                        propertServicename = bdDictService.queryDictName(5, prodService.getTypeId());
//                    else
//                        propertServicename = bdDictService.queryDictName(10, prodService.getPropertyId()) + bdDictService.queryDictName(5, prodService.getTypeId());
//                    soOrderProdItem.setServiceName(propertServicename); //产品服务名称
//                    soOrderProdItem.setPriceOriginal(prodPrice.getOriginalPrice());
//                    soOrderProdItem.setPrice(prodPrice.getOriginalPrice()); //订单价格  合同不允许改价
//                    soOrderProdItem.setPriceRefund(0);
//
//                }
//
//                //SoOrderProd编号暂时不考虑
//                ProdProduct prodProduct = prodProductService.findById(Integer.valueOf(SecurityUtils.rc4Decrypt(soOrderProd.getProductIdStr())));
//
//                soOrderProd.setNo("");
//                soOrderProd.setProductId(prodProduct.getPkid());
//                soOrderProd.setProductName(prodProduct.getName());
//                //order产品名称
//                prodName += prodProduct.getName() + ",";
//
//                soOrderProd.setCityId(Integer.valueOf(SecurityUtils.rc4Decrypt(soOrderProd.getCityIdStr())));
//                soOrderProd.setProcessStatusId(0);
//                soOrderProd.setAuditStatusId(1051);
//                soOrderProd.setPrice(priceOriginal);
//                soOrderProd.setPriceOriginal(priceOriginal);
//                soOrderProd.setIsRefund(0);
//                soOrderProd.setProcessedDays(0);
//
//                totalPrice += priceOriginal;
//                payablePrice += price;
//            }
//
//            SoOrder soOrder = new SoOrder();
//            //通过手机号获取用户信息
//            UcAccount account = ucAccountService.findByMobile(soOrder.getAccountMobile());
//            if (account == null) {
//                data.setCode(-1);
//                data.setMsg("客户信息错误");
//                return data;
//            }
//            account.setIdentityCard(soContract.getIdentityCard());
//            account.setEmail(soContract.getEmail());
//            account.setMobilePhone(soContract.getAccountMobile());
//            soContract.setAccount(account);
//
//            //订单编号
//            soOrder.setType(2);
//            soOrder.setNo("");
//            soOrder.setAccountId(account.getPkid());
//            soOrder.setAccountMobile(account.getMobilePhone());
//            soOrder.setAccountName(account.getRealName());
//            soOrder.setTotalPrice(totalPrice); //总金额
//            soOrder.setPayablePrice(payablePrice); //应付金额
//            soOrder.setPaidPrice(0); //已付金额
//
//            soOrder.setIsChangePrice(0);  //是否改价
//            soOrder.setPayStatusId(3011); //待付款
//            soOrder.setProcessStatusId(3021); // 待处理
//            soOrder.setRefundStatusId(0); //退款
//            soOrder.setSourceTypeId(3041); //来源类型 PC端
//            soOrder.setRemark(soContract.getRemark());
//
//            soOrder.setIsInstallment(soContract.getIsInstallment());  //合同分期
//            soOrder.setInstallmentMode(soContract.getInstallmentMode().replace(",", "|"));  //分期形式,存储金额
//            if (soContract.getIsInstallment().equals(1))
//                soOrder.setInstallmentAuditStatusId(1051); //分期待审核
//            else
//                soOrder.setInstallmentAuditStatusId(0);
//
//            soOrder.setChangePriceAuditStatusId(0); //改价申请状态
//            soOrder.setIsInvoice(0); //是否开发票
//            soOrder.setDescription("");
//            soOrder.setAddTime(dt);
//            soOrder.setAddUserId(loginUser.getUcUser().getPkid());
//            soOrder.setIsPackage(0);
//            soOrder.setPackageId(0);
//            soOrder.setProdName(prodName);
//
//            soOrder.setProdList(soContract.getProdList());
//
//            soContract.setSginingCompanyId(Integer.valueOf(SecurityUtils.rc4Decrypt(soContract.getSginingCompanyIdStr())));
//            soContract.setSginingUserId(loginUser.getUcUser().getPkid());  //签单业务员序号
//            soContract.setCustomerId(account.getPkid()); //客户序号
//            soContract.setDataFeeCountTypeId(Integer.valueOf(SecurityUtils.rc4Decrypt(soContract.getSginingCompanyIdStr())));  //材料撰写次数类型序号
//
//            //附件序号
//            soContract.setAuditStatusId(1051); //待审核状态
//            soContract.setAddUserId(loginUser.getUcUser().getPkid());
//            soContract.setAddTime(dt);
//            soContract.setSoOrder(soOrder);
//
//            try {
//                soContractService.insertSoContract(soContract, loginUser.getUcUser().getPkid());
//
//                data.setCode(200);
//                data.setMsg("操作成功");
//            } catch (AuditException e) {
//                data.setCode(-1);
//                data.setMsg(e.getMessage());
//            }
//        } else {
//            data.setCode(200);
//            data.setMsg("没用添加产品");
//            return data;
//        }
//
//        return data;
//    }
//
//    public boolean checkMustServiceItem(List<SoOrderProd> list) {
//        for (SoOrderProd soOrderProd : list) {
//            Map<String, Object> propertiesMap1 = new HashMap<>();
//            propertiesMap1.put("product_id", soOrderProd.getPkid());
//            //查询出当前产品下所有服务项
//            List<ProdService> prodServiceList = prodServiceService.pageByProperties(propertiesMap1, 0).getList();
//            //创建必选项服务项集合
//            List<ProdPrice> mustServiceItemList = new ArrayList<>();
//            for (ProdService prodService : prodServiceList) {
//                Map<String, Object> propertiesMap2 = new HashMap<>();
//                propertiesMap2.put("service_id", prodService.getPkid());
//                propertiesMap2.put("city_id", soOrderProd.getCityId());
//                propertiesMap2.put("is_must", 1);
//                //查询出当必选服务项且价格且审核状态通过
//                List<ProdPrice> prodPriceList = prodPriceService.pageByProperties(propertiesMap2, 0).getList();
//                for (ProdPrice prodPrice : prodPriceList) {
//                    ProdPriceAudit prodPriceAudit = prodPriceAuditService.findById(prodPrice.getPriceAuditId());
//                    if (prodPriceAudit.getAuditStatusId() == 1052) {
//                        mustServiceItemList.add(prodPrice);
//                    }
//                }
//            }
//            if (CollectionUtils.isNotEmpty(mustServiceItemList)) {
//                //有非必选项,遍历价格id开始检查,必选项必须全部选择才为true
//                List<SoOrderProdItem> soOrderProdItemList = soOrderProd.getItemList();
//                //订单中的价格Id集合(包含了必选项及非必项)
//                List<Integer> soOrderProdItemPriceIdList = new ArrayList<>();
//                for (SoOrderProdItem soOrderProdItem : soOrderProdItemList) {
//                    soOrderProdItemPriceIdList.add(soOrderProdItem.getPriceId());
//                }
//                //必选项目中价格Id集合
//                List<Integer> mustServiceItemPriceIdList = new ArrayList<>();
//                for (ProdPrice prodPrice : mustServiceItemList) {
//                    mustServiceItemPriceIdList.add(prodPrice.getPkid());
//                }
//                if (!soOrderProdItemPriceIdList.containsAll(mustServiceItemPriceIdList)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private ResponseData auditList(HttpServletRequest request, LoginUser loginUser, Integer... auditTypes) {
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
//        paramMap.put("typeId", 1043);
//        paramMap.put("auditUserId", loginUser.getUcUser().getPkid());
//
//        if (ArrayUtils.isNotEmpty(auditTypes)) {
//            paramMap.put("statusIds", Arrays.asList(auditTypes));
//        }
//
//        if (StringUtils.isNotBlank(productName)) {
//            paramMap.put("productName", productName);
//        }
//
//        if (StringUtils.isNotBlank(no)) {
//            paramMap.put("no", no);
//        }
//
//        if (StringUtils.isNotBlank(accountName)) {
//            paramMap.put("accountName", accountName);
//        }
//
//        if (StringUtils.isNotBlank(accountMobile)) {
//            paramMap.put("accountMobile", accountMobile);
//        }
//
//        if (StringUtils.isNotBlank(beginTime)) {
//            paramMap.put("beginTime", beginTime + " 00:00:00");
//        }
//
//        if (StringUtils.isNotBlank(endTime)) {
//            paramMap.put("endTime", endTime + " 23:59:59");
//        }
//
//        Pager<ContractList> pager = soContractService.listAuditContract(paramMap, currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//    /**
//     * 待审核
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/toaudit/list")
//    public ResponseData toauditList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.TO_AUDIT);
//    }
//
//    /**
//     * 审核通过
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/pass/list")
//    public ResponseData passList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_PASS);
//    }
//
//    /**
//     * 审核驳回
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/reject/list")
//    public ResponseData rejectList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user, AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    /**
//     * 全部审核
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/all/list")
//    public ResponseData auditAllList(HttpServletRequest request, LoginUser user) {
//        return auditList(request, user,
//                AuditStatusUtils.TO_AUDIT,
//                AuditStatusUtils.AUDIT_PASS,
//                AuditStatusUtils.AUDIT_REJECT);
//    }
//
//    /**
//     * 各审核状态数量
//     *
//     * @param user
//     * @return
//     */
//    @RequestMapping("/audit/nums")
//    public ResponseData auditNums(LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> auditNums = bdAuditLogService.getAuditNums(user.getUcUser().getPkid(), 1043);
//        data.setData(auditNums);
//        return data;
//    }
//
//    /**
//     * 合同查看浮层
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("info")
//    public ResponseData contractInfo(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("orderPkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        SoContract soContract = soContractService.getSoContractInfo(pkid);
//        data.setData(soContract);
//
//        return data;
//    }
//
//    /**
//     * 合同审核-审核通过
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/audit/pass")
//    public ResponseData auditPass(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//        String remark = StringUtils.trimToEmpty(request.getParameter("remark"));
//        try {
//            int rs = soContractService.editSoContractPass(pkid, user.getUcUser().getPkid(), remark);
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
//     * 合同审核-审核不通过
//     *
//     * @param request
//     * @param user
//     * @return
//     */
//    @RequestMapping("/audit/reject")
//    public ResponseData auditReject(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        if (pkid == 0) {
//            data.setMsg("参数错误");
//            return data;
//        }
//
//        String remark = StringUtils.trimToEmpty(request.getParameter("remark"));
//        try {
//            int rs = soContractService.editSoContractReject(pkid, user.getUcUser().getPkid(), remark);
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
//    @RequestMapping("/audit/process")
//    public ResponseData process(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderPkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        List<List<AuditNode>> list = bdAuditLogService.getAuditProcess(pkid, 1043);
//        data.setData(list);
//        return data;
//    }
}