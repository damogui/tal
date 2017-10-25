package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/prodproduct")
public class ProdProductController {
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private ProdProductService prodProductService;
//    @Autowired
//    private ProdPriceAuditService prodPriceAuditService;
//    @Autowired
//    private ProdServiceService prodServiceService;
//    @Autowired
//    private ProdPriceService prodPriceService;
//    @Autowired
//    private BdDictService bdDictService;
//    @Autowired
//    private UcOrganizationService ucOrganizationService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdProduct prodProduct, LoginUser user, @RequestBody Map<String, Object> paramMap) {
//        ResponseData data = new ResponseData();
//
//        String param = JsonUtils.objectToJson(paramMap.get("param"));
//        if (StringUtils.isBlank(param)) {
//            data.setMsg("参数不能为空");
//            data.setCode(402);
//            return data;
//        }
//        RequestProduct requestProduct = JsonUtils.jsonToObject(param, RequestProduct.class);
//        if (requestProduct == null) {
//            data.setMsg("参数不正确");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getName())) {
//            data.setMsg("产品名不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getTypeIdStr())) {
//            data.setMsg("产品类型不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getDealerTypeIdStr())) {
//            data.setMsg("产品销售方不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (CollectionUtils.isEmpty(requestProduct.getProdServiceList())) {
//            data.setMsg("产品服务项不能为空");
//            data.setCode(402);
//            return data;
//        }
//        for (ServiceList sl : requestProduct.getProdServiceList()) {
//            if (StringUtils.isBlank(sl.getTypeIdStr())) {
//                data.setMsg("产品服务项类型不能为空");
//                data.setCode(402);
//                return data;
//            }
//            if (StringUtils.isBlank(sl.getUnitIdStr())) {
//                data.setMsg("产品服务项单位不能为空");
//                data.setCode(402);
//                return data;
//            }
////            if(StringUtils.isBlank(sl.getPropertyIdStr()))
////            {
////                data.setMsg("产品服务项服务特性不能为空");
////                data.setCode(402);
////                return data;
////            }
//            if (sl.getHasStock() == null) {
//                data.setMsg("产品服务项有无库存不能为空");
//                data.setCode(402);
//                return data;
//            }
//        }
//
//        prodProduct.setName(requestProduct.getName());
//        prodProduct.setTypeId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(requestProduct.getTypeIdStr())));
//        prodProduct.setDealerTypeId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(requestProduct.getDealerTypeIdStr())));
//        prodProduct.setNo("");
//        prodProduct.setDescription(StringUtils.isBlank(requestProduct.getDescription()) ? "" : requestProduct.getDescription());
//        prodProduct.setSort(1000d);
//        prodProduct.setIsEnabled(1);
//        prodProduct.setAddUserId(user.getUcUser().getPkid());
//        prodProduct.setRemark("");
//
//        prodProduct.setIsAllowedAddToCart(requestProduct.getIsAllowedAddToCart());
//        prodProduct.setIsAllowedBuyOneMore(requestProduct.getIsAllowedBuyOneMore());
//        prodProduct.setIsOrderedBackgroundOnly(requestProduct.getIsOrderedBackgroundOnly());
//        prodProduct.setIsRequiredCheckNameInfo(requestProduct.getIsRequiredCheckNameInfo());
//        prodProduct.setIsRequiredCompanyRegisterAddress(requestProduct.getIsRequiredCompanyRegisterAddress());
//        prodProduct.setIsRequiredCompanyRegisterInfo(requestProduct.getIsRequiredCompanyRegisterInfo());
//        prodProduct.setIsRequiredEmsAddress(requestProduct.getIsRequiredEmsAddress());
//        prodProduct.setIsRequiredServiceLifecycle(requestProduct.getIsRequiredServiceLifecycle());
//        prodProduct.setIsNegotiable(requestProduct.getIsNegotiable());
//        prodProduct.setIncomeRate(requestProduct.getIncomeRate());
//        prodProduct.setIsHandle(requestProduct.getIsHandle());
//        prodProduct.setIsApplyNo(requestProduct.getIsApplyNo());
//        prodProduct.setBusinessList(requestProduct.getBusinessList());
//
//        List<ProdService> pslist = new ArrayList<>();
//        for (ServiceList sl : requestProduct.getProdServiceList()) {
//            ProdService ps = new ProdService();
//            ps.setUnitId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getUnitIdStr())));
//            ps.setPropertyId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getPropertyIdStr())));
//            ps.setTypeId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getTypeIdStr())));
//            ps.setDescription("");
//            ps.setSort(1000d);
//            ps.setIsEnabled(1);
//            ps.setHasStock(sl.getHasStock());
//            ps.setAddUserId(user.getUcUser().getPkid());
//            ps.setRemark("");
//            pslist.add(ps);
//        }
//
//        data.setData(prodProductService.saveProduct(prodProduct, pslist));
//
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, ProdProduct prodProduct, LoginUser user, @RequestBody Map<String, Object> paramMap) {
//        ResponseData data = new ResponseData();
//
//        String param = JsonUtils.objectToJson(paramMap.get("param"));
//
//        if (StringUtils.isBlank(param)) {
//            data.setMsg("参数不能为空");
//            data.setCode(402);
//            return data;
//        }
//        RequestProduct requestProduct = JsonUtils.jsonToObject(param, RequestProduct.class);
//
//        if (requestProduct == null) {
//            data.setMsg("参数不正确");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getPkidStr())) {
//            data.setMsg("产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getName())) {
//            data.setMsg("产品名不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getTypeIdStr())) {
//            data.setMsg("产品类型不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(requestProduct.getDealerTypeIdStr())) {
//            data.setMsg("产品销售方不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (CollectionUtils.isEmpty(requestProduct.getProdServiceList())) {
//            data.setMsg("产品服务项不能为空");
//            data.setCode(402);
//            return data;
//        }
//        for (ServiceList sl : requestProduct.getProdServiceList()) {
//            if (StringUtils.isBlank(sl.getTypeIdStr())) {
//                data.setMsg("产品服务项类型不能为空");
//                data.setCode(402);
//                return data;
//            }
//            if (StringUtils.isBlank(sl.getUnitIdStr())) {
//                data.setMsg("产品服务项单位不能为空");
//                data.setCode(402);
//                return data;
//            }
////            if(StringUtils.isBlank(sl.getPropertyIdStr()))
////            {
////                data.setMsg("产品服务项服务特性不能为空");
////                data.setCode(402);
////                return data;
////            }
//            if (sl.getHasStock() == null) {
//                data.setMsg("产品服务项有无库存不能为空");
//                data.setCode(402);
//                return data;
//            }
//        }
//
//        prodProduct.setPkid(NumberUtils.toInt(SecurityUtils.rc4Decrypt(requestProduct.getPkidStr())));
//        prodProduct.setName(requestProduct.getName());
//        prodProduct.setTypeId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(requestProduct.getTypeIdStr())));
//        prodProduct.setDealerTypeId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(requestProduct.getDealerTypeIdStr())));
//        prodProduct.setNo("");
//        prodProduct.setDescription(requestProduct.getDescription());
//        prodProduct.setSort(1000d);
//        prodProduct.setIsEnabled(1);
//        prodProduct.setAddUserId(user.getUcUser().getPkid());
//        prodProduct.setRemark("");
//
//        prodProduct.setIsAllowedAddToCart(requestProduct.getIsAllowedAddToCart());
//        prodProduct.setIsAllowedBuyOneMore(requestProduct.getIsAllowedBuyOneMore());
//        prodProduct.setIsOrderedBackgroundOnly(requestProduct.getIsOrderedBackgroundOnly());
//        prodProduct.setIsRequiredCheckNameInfo(requestProduct.getIsRequiredCheckNameInfo());
//        prodProduct.setIsRequiredCompanyRegisterAddress(requestProduct.getIsRequiredCompanyRegisterAddress());
//        prodProduct.setIsRequiredCompanyRegisterInfo(requestProduct.getIsRequiredCompanyRegisterInfo());
//        prodProduct.setIsRequiredEmsAddress(requestProduct.getIsRequiredEmsAddress());
//        prodProduct.setIsRequiredServiceLifecycle(requestProduct.getIsRequiredServiceLifecycle());
//        prodProduct.setIsNegotiable(requestProduct.getIsNegotiable());
//        prodProduct.setIncomeRate(requestProduct.getIncomeRate());
//        prodProduct.setIsHandle(requestProduct.getIsHandle());
//        prodProduct.setIsApplyNo(requestProduct.getIsApplyNo());
//        prodProduct.setBusinessList(requestProduct.getBusinessList());
//        List<ProdService> pslist = new ArrayList<>();
//        for (ServiceList sl : requestProduct.getProdServiceList()) {
//            ProdService ps = new ProdService();
//            if (sl.getPkidStr() != null) {
//                ps.setPkid(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getPkidStr())));
//            }
//            ps.setProductId(prodProduct.getPkid());
//            ps.setUnitId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getUnitIdStr())));
//            ps.setPropertyId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getPropertyIdStr())));
//            ps.setTypeId(NumberUtils.toInt(SecurityUtils.rc4Decrypt(sl.getTypeIdStr())));
//            ps.setDescription("");
//            ps.setSort(1000d);
//            ps.setIsEnabled(1);
//            ps.setHasStock(sl.getHasStock());
//            ps.setAddUserId(user.getUcUser().getPkid());
//            ps.setRemark("");
//            pslist.add(ps);
//        }
//
////        ProdProduct old = prodProductService.findById(prodProduct.getPkid());
//
////        // 只有钟铮可以修改
////        if (prodProduct.getAddUserId() != 2133 && old.getIncomeRate() != prodProduct.getIncomeRate()) {
////            data.setCode(-1);
////            data.setMsg("不允许修改分成比例，如必须修改请联系钟铮总");
////            return data;
////        }
//
//        data.setData(prodProductService.updateProduct(prodProduct, pslist));
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String page = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        String pagesize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        String dealerTypeId = StringUtils.trimToEmpty(request.getParameter("dealerTypeIdStr"));
//        String typeId = StringUtils.trimToEmpty(request.getParameter("typeIdStr"));
//        String isEnabled = StringUtils.trimToEmpty(request.getParameter("isEnabled"));
//
//        Map<String, Object> paramMap = new HashMap();
//        if (StringUtils.isNotEmpty(name)) {
//            paramMap.put("name", name);
//        }
//        if (StringUtils.isNotEmpty(dealerTypeId)) {
//            paramMap.put("dealerTypeId", NumberUtils.toInt(SecurityUtils.rc4Decrypt(dealerTypeId)));
//        }
//        if (StringUtils.isNotEmpty(typeId)) {
//            paramMap.put("typeId", NumberUtils.toInt(SecurityUtils.rc4Decrypt(typeId)));
//        }
//        if (StringUtils.isNotEmpty(isEnabled)) {
//            paramMap.put("isEnabled", NumberUtils.toInt(isEnabled));
//        }
//        data.setData(prodProductService.pageByProducts(paramMap, NumberUtils.toInt(page), NumberUtils.toInt(pagesize)));
//        return data;
//    }
//
//    @RequestMapping("/myList")
//    public ResponseData myList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        List<ProdProduct> myProductList = prodProductService.getMyProductList(user.getUcUser().getPkid());
//        data.setData(myProductList);
//        return data;
//    }
//
//    @RequestMapping("/myCities")
//    public ResponseData myCities(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int productId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("productIdStr")));
//        int pid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("cityIdStr")));
//
//        List<BdDict> dictList = prodProductService.getCityDictList(productId, pid);
//        data.setData(dictList);
//        return data;
//    }
//
//    @RequestMapping("/myPrices")
//    public ResponseData myServices(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int productId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("productIdStr")));
//        int cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("cityIdStr")));
//
//        List<ProdPrice> priceList = prodProductService.getProductPriceList(productId, cityId);
//
//        // 注意：此处配合前端和订单插入，将原价改为现价
//        if (CollectionUtils.isNotEmpty(priceList)) {
//            priceList.stream().forEach(p -> p.setOriginalPrice(p.getPrice()));
//        }
//
//        data.setData(priceList);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String pkidStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//        if (StringUtils.isBlank(pkidStr)) {
//            data.setMsg("产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(pkidStr));
//        ProdProduct prodProduct = prodProductService.findInfoById(pkid);
//        Map<String, Object> pidMap = new HashMap();
//        pidMap.put("product_id", prodProduct.getPkid());
//        pidMap.put("is_enabled", 1);// 设置启用的服务
//        List<ProdService> prodServiceList = prodServiceService.findByProperties(pidMap, 0, Integer.MAX_VALUE);
//        List<Integer> bdIds = new ArrayList();
//        for (ProdService item : prodServiceList) {
//            bdIds.add(item.getUnitId());
//            bdIds.add(item.getTypeId());
//            bdIds.add(item.getPropertyId());
//        }
//
//        Map<Integer, BdDict> dictMap = bdDictService.findMapByIds(bdIds);
//        BdDict dict = null;
//        List<ProdService> prodServiceList1 = new ArrayList();
//        for (ProdService item : prodServiceList) {
//            int n = 0;
//
//            dict = dictMap.get(item.getTypeId());
//            if (dict == null) {
//                item.setTypeName("");
//                n++;
//            } else {
//                item.setTypeName(StringUtils.trimToEmpty(dict.getName()));
//                if (NumberUtils.toInt(dict.getIsEnabled()) == 0) {
//                    n++;
//                }
//            }
//
//            dict = dictMap.get(item.getPropertyId());
//            if (dict == null) {
//                item.setPropertyName("");
//            } else {
//                item.setPropertyName(StringUtils.trimToEmpty(dict.getName()));
//                if (NumberUtils.toInt(dict.getIsEnabled()) == 0) {
//                    n++;
//                }
//            }
//
//
//            dict = dictMap.get(item.getUnitId());
//            if (dict == null) {
//                item.setUnitName("");
//                n++;
//            } else {
//                item.setUnitName(StringUtils.trimToEmpty(dict.getName()));
//                if (NumberUtils.toInt(dict.getIsEnabled()) == 0) {
//                    n++;
//                }
//            }
//
//            if (n == 0) {
//                prodServiceList1.add(item);
//            }
//        }
//        prodProduct.setProdServiceList(prodServiceList1);
//        data.setData(prodProduct);
//        return data;
//    }
//
//    @RequestMapping("/getallorganization")
//    public ResponseData getallorganization(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        String productIdStr = checkParameters(request, "productId", data);
//        if (StringUtils.isEmpty(productIdStr)) {
//            return data;
//        }
//        Integer productId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(productIdStr));
//
//        //检查权限,拿到当前用户的组织及其子组织
//        List<Integer> orgIds = ucUserService.getUserOrganizationIds(loginUser.getUcUser().getPkid());
//
//        Map<String, Object> propertiesMap = new HashMap<>();
//        propertiesMap.put("organization_id", orgIds);
//        propertiesMap.put("product_id", productId);
//        propertiesMap.put("audit_status_id", AuditStatuses.Type_1054);
//        //在审核表中查询当前产品所有审核通过的ProdPriceAudit 且是當前登錄者下面的
//        List<ProdPriceAudit> prodPriceAuditlist = prodPriceAuditService.findByProperties(propertiesMap);
//        if (CollectionUtils.isEmpty(prodPriceAuditlist)) {
//            data.setCode(-1);
//            data.setMsg("定价审核通过的记录为空");
//            return data;
//        }
//
//        List<Integer> organizationPkids = new ArrayList<>();
//        for (ProdPriceAudit prodPriceAudit : prodPriceAuditlist) {
//            //根据审核Id查询出所有上架产品,当集合大于0时证明供应商有上架了此产品
//            List<ProdPrice> prodPriceList = prodPriceService.findByAuditIdAndSaleState(prodPriceAudit.getPkid(), 1);
//            if (CollectionUtils.isNotEmpty(prodPriceList)) {
//                organizationPkids.add(prodPriceAudit.getOrganizationId());
//            }
//        }
//
//        List<UcOrganization> ucOrganizationList = ucOrganizationService.findByIds(organizationPkids);
//        data.setData(ucOrganizationList);
//        return data;
//    }
//
//    @RequestMapping("/getallcity")
//    public ResponseData getallcity(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String productIdStr = checkParameters(request, "productId", data);
//        if (StringUtils.isEmpty(productIdStr)) {
//            return data;
//        }
//        Integer productId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(productIdStr));
//
//        String organizationIdStr = StringUtils.trimToEmpty(request.getParameter("organizationId"));
//        /*是否是所有的数据，包含所有的组织机构下的定过价的区域，（为空或‘0’时不是所有组织机构的数据，为‘1’时则是所有组织机构的数据）*/
//        Integer isAll = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("isAll")));
//        Integer organizationId = 0;
//        Map<String, Object> propertiesMap = new HashMap<>();
//        propertiesMap.put("product_id", productId);
//        if (isAll == 0) {
//            if (StringUtils.isEmpty(organizationIdStr)) {
//                // 获取当前登录者所在组织  即集合里的第一条记录
//                List<UcOrganization> list = loginUser.getUcOrganizationList();
//                if (CollectionUtils.isNotEmpty(list)) {
//                    organizationId = list.get(0).getPkid();
//                }
//            } else {
//                organizationId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(organizationIdStr));
//            }
//            if (NumberUtils.toInt(organizationId) > 0) {
//                propertiesMap.put("organization_id", organizationId);
//            }
//        }
//        List<BdDict> list = prodProductService.findAllCity(propertiesMap);
//        data.setData(list);
//        return data;
//    }
//
//    @RequestMapping("/isenabled")
//    public ResponseData isenabled(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdProduct prodProduct, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String pkidStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//        if (StringUtils.isBlank(pkidStr)) {
//            data.setMsg("产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        ProdProduct pp = new ProdProduct();
//        pp.setPkid(NumberUtils.toInt(SecurityUtils.rc4Decrypt(pkidStr)));
//        pp.setIsEnabled(NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("enabled"))));
//        data.setData(prodProductService.updateEnabled(pp));
//        return data;
//    }
//
//    @RequestMapping("/byidlist")
//    public ResponseData byidlist(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        String pagesize = request.getParameter("pageSize");
//        String pkidStr = request.getParameter("pkidStr");
//        if (pkidStr == null || pkidStr.equals("")) {
//            data.setMsg("产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        Integer pkid = Integer.valueOf(SecurityUtils.rc4Decrypt(pkidStr));
//        ProdProduct prodProduct = prodProductService.findById(pkid);
//        if (StringUtils.isBlank(page)) {
//            page = "1";
//        }
//        if (StringUtils.isBlank(pagesize)) {
//            pagesize = "100";
//        }
//        Map<String, Object> paramMap = new HashMap();
//        if (prodProduct.getTypeId() != 0 || prodProduct != null) {
//            paramMap.put("typeId", prodProduct.getTypeId());
//        }
//        data.setData(prodProductService.pageByProducts(paramMap, Integer.valueOf(page), Integer.valueOf(pagesize)));
//        return data;
//    }
//
//    @RequestMapping("/undercarriage")
//    public ResponseData undercarriage(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String productIdStr = checkParameters(request, "productId", data);
//        String organizationIdStr = checkParameters(request, "organizationId", data);
//        if (StringUtils.isBlank(productIdStr) || StringUtils.isBlank(organizationIdStr)) {
//            return data;
//        }
//        String allCityIdStr = StringUtils.trimToEmpty(request.getParameter("allCityId"));
//        if (StringUtils.isBlank(allCityIdStr)) {
//            data.setCode(-1);
//            data.setMsg("参数allCityId为空");
//            return data;
//        }
//        String[] allCityId = allCityIdStr.split(",");
//        if (allCityId == null || allCityId.length == 0) {
//            data.setCode(403);
//            data.setMsg("参数allCityId错误");
//            return data;
//        }
//        Integer productId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(productIdStr));
//        Integer organizationId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(organizationIdStr));
//        Set<Integer> citySet = new HashSet<>();
//        //防止前端人员传递重复地区
//        for (String string : allCityId) {
//            citySet.add(NumberUtils.toInt(SecurityUtils.rc4Decrypt(string)));
//        }
//
//        Map<String, Object> propertiesMap = new HashMap<>();
//        propertiesMap.put("product_id", productId);
//        propertiesMap.put("organization_id", organizationId);
//        propertiesMap.put("audit_status_id", AuditStatuses.Type_1054);
//        List<ProdPriceAudit> prodPriceAuditlist = prodPriceAuditService.findByProperties(propertiesMap);
//        if (CollectionUtils.isEmpty(prodPriceAuditlist)) {
//            data.setCode(-1);
//            data.setMsg("下架失败");
//            return data;
//        }
//
//        Set<Integer> auditIds = new HashSet<>();
//        for (ProdPriceAudit prodPriceAudit : prodPriceAuditlist) {
//            auditIds.add(prodPriceAudit.getPkid());
//        }
//        List<ProdPrice> prodPriceList = prodPriceService.findByAuditIdAndCityID(auditIds, citySet, 1);
//
//        if (CollectionUtils.isEmpty(prodPriceList)) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        } else {
//            Set<Integer> allPkis = new HashSet<>();
//            for (ProdPrice prodPrice : prodPriceList) {
//                allPkis.add(prodPrice.getPkid());
//            }
//            // 批量下架
//            prodPriceService.editProdPriceWithIsOnSale(allPkis, 0);
//            data.setMsg("下架成功");
//        }
//        return data;
//    }
//
//    private String checkParameters(HttpServletRequest request, String parameterName, ResponseData data) {
//        String rc4EncryptStr = StringUtils.trimToEmpty(request.getParameter(parameterName));
//        if (StringUtils.isBlank(rc4EncryptStr)) {
//            data.setCode(403);
//            data.setMsg("缺少" + parameterName + "参数");
//        }
//        return rc4EncryptStr;
//    }

}