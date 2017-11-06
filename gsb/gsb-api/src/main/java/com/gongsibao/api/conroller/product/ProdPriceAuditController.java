package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/prodpriceaudit")
public class ProdPriceAuditController {
//
//    @Autowired
//    private ProdPriceAuditService prodPriceAuditService;
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private ProdPriceService prodPriceService;
//
//
//    @RequestMapping(value = "/add_demo")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPriceAudit prodPriceAudit) {
//        ResponseData data = new ResponseData();
//        prodPriceAuditService.insert(prodPriceAudit);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        prodPriceAuditService.delete(getId(request));
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update_demo")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response,
//                               @ModelAttribute ProdPriceAudit prodPriceAudit) {
//        ResponseData data = new ResponseData();
//        prodPriceAudit.setPkid(getId(request));
//        prodPriceAuditService.update(prodPriceAudit);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ProdPriceAudit prodPriceAudit = prodPriceAuditService.findById(pkid);
//        data.setData(prodPriceAudit);
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
//        Pager<ProdPriceAudit> pager = prodPriceAuditService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping({"/count"})
//    public ResponseData getRowsCount(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        // 待审核状态ID
//        String auditStatusId = StringUtils.trimToEmpty(request.getParameter("auditStatusIdStr"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (NumberUtils.toInt(auditStatusId) > 0) {
//            condition.put("audit_status_id", NumberUtils.toInt(auditStatusId));
//        }
//
//        // 获取当前登录者所在组织机构ID集合
//        List<Integer> organizationIds = ucUserService.getUserOrganizationIds(loginUser.getUcUser().getPkid());
//        if (CollectionUtils.isNotEmpty(organizationIds)) {
//            condition.put("organizationIds", organizationIds);
//        }
//
//        Integer result = prodPriceAuditService.countProdPriceAuditRows(condition);
//        data.setMsg("操作成功");
//        data.setData(result);
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData getRows(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        // 产品分类ID（套餐，还是单品）
//        String prodTypeId = StringUtils.trimToEmpty(request.getParameter("prodType"));
//        if (NumberUtils.toInt(prodTypeId, -1) == 2) {
//            // 暂时无套餐业务，查询套餐直接返回空数据
//            data.setData(new Pager<ProdPriceAuditRow>(0, 0));
//            return data;
//        }
//
//        // 当前页
//        String page = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//        Map<String, Object> condition = getQueryProdPriceRowsParas(request);
//
//        // 获取当前登录者所在组织机构ID集合
//        List<Integer> organizationIds = ucUserService.getUserOrganizationIds(loginUser.getUcUser().getPkid());
//        if (CollectionUtils.isNotEmpty(organizationIds)) {
//            condition.put("organizationIds", organizationIds);
//        }
//
//        Pager<ProdPriceAuditRow> pager = prodPriceAuditService.pageProdPriceAuditRows(condition,
//                NumberUtils.toInt(page), NumberUtils.toInt(pageSize));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping({"/listonsale"})
//    public ResponseData getOnSaleProdPriceRows(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        // 当前页
//        String page = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        Map<String, Object> condition = new HashMap();
//        // 审核状态
//        String auditStatusStr = StringUtils.trimToEmpty(request.getParameter("auditStatusIdStr"));
//        if (StringUtils.isBlank(auditStatusStr)) {
//            data.setCode(400);
//            data.setMsg("auditStatusIdStr不能为空");
//            return data;
//        }
//        condition.put("audit_status_id", NumberUtils.toInt(auditStatusStr, -1));
//        condition.put("is_on_sale", 0);
//        // 审核通过的时候  默认为查询上架的
//        if (NumberUtils.toInt(auditStatusStr, -1) == 1054) {
//            condition.put("is_on_sale", 1);
//        }
//
//        // 组织ID查询条件
//        String organizationIdStr = StringUtils.trimToEmpty(request.getParameter("organizationIdStr"));
//        if (StringUtils.isNotBlank(organizationIdStr)) {
//            condition.put("organization_id", getIntegerIdFromEncryptStr(organizationIdStr));
//        }
//
//        // 获取当前登录者所在组织机构ID集合
//        List<Integer> organizationIds = ucUserService.getUserOrganizationIds(loginUser.getUcUser().getPkid());
//        if (CollectionUtils.isNotEmpty(organizationIds)) {
//            condition.put("organizationIds", organizationIds);
//        }
//
//        // 产品名称
//        String prodName = StringUtils.trimToEmpty(request.getParameter("prodName"));
//        if (StringUtils.isNotBlank(prodName)) {
//            condition.put("name", prodName + "%");
//        }
//
//        // 地区ID
//        String cityAreaIdStr = StringUtils.trimToEmpty(request.getParameter("cityIdStr"));
//        if (StringUtils.isNotBlank(cityAreaIdStr)) {
//            condition.put("city_id", getIntegerIdFromEncryptStr(cityAreaIdStr));
//        }
//
//        Pager<ProdPriceOnSaleRow> pager = prodPriceAuditService.pageProdPriceOnSaleRows(condition,
//                NumberUtils.toInt(page), NumberUtils.toInt(pageSize));
//        data.setData(pager);
//        return data;
//    }
//
//
//    @RequestMapping({"/detail"})
//    public ResponseData getProdPriceDetail(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getId(request);
//        Map<String, Object> result = prodPriceAuditService.findDetailById(pkid);
//        data.setData(result);
//        data.setMsg("成功获取数据");
//        return data;
//    }
//
//    @RequestMapping({"/onsaledetail"})
//    public ResponseData getProdPriceDetail(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> condition = new HashMap();
//
//        // 审核状态
//        String auditStatusStr = StringUtils.trimToEmpty(request.getParameter("auditStatusIdStr"));
//        if (StringUtils.isNotBlank(auditStatusStr)) {
//            condition.put("audit_status_id", NumberUtils.toInt(auditStatusStr, -1));
//        }
//        condition.put("is_on_sale", 0);
//        // 审核通过的时候  默认为查询上架的
//        if (NumberUtils.toInt(auditStatusStr, -1) == 1054) {
//            condition.put("is_on_sale", 1);
//        }
//
//        //组织ids
//        String organizationIdStr = StringUtils.trimToEmpty(request.getParameter("organizationIdStr"));
//        if (StringUtils.isNotBlank(organizationIdStr)) {
//            condition.put("organization_id", getIntegerIdFromEncryptStr(organizationIdStr));
//        }
//
//        // 产品Id
//        String prodIdStr = StringUtils.trimToEmpty(request.getParameter("prodIdStr"));
//        if (StringUtils.isNotBlank(prodIdStr)) {
//            condition.put("product_id", getIntegerIdFromEncryptStr(prodIdStr));
//        }
//
//        ProdPriceOnSaleRow result = prodPriceAuditService.findOrgProdPricesDetailById(condition);
//
//        data.setData(result);
//        data.setMsg("成功获取数据");
//        return data;
//    }
//
//
//    @RequestMapping("/prodpriceservices")
//    public ResponseData getProdPriceServices(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> condition = new HashMap();
//
//        // 审核状态
//        String auditStatusStr = StringUtils.trimToEmpty(request.getParameter("auditStatusIdStr"));
//        if (StringUtils.isBlank(auditStatusStr)) {
//            data.setCode(400);
//            data.setMsg("auditStatusIdStr 不能为空");
//            return data;
//        }
//        condition.put("audit_status_id", NumberUtils.toInt(auditStatusStr, -1));
//        condition.put("is_on_sale", 0);
//        // 审核通过的时候  默认为查询上架的
//        if (NumberUtils.toInt(auditStatusStr, -1) == 1054) {
//            condition.put("is_on_sale", 1);
//        }
//
//        //组织ids
//        String organizationIdStr = StringUtils.trimToEmpty(request.getParameter("organizationIdStr"));
//        if (StringUtils.isBlank(organizationIdStr)) {
//            data.setCode(400);
//            data.setMsg("organizationIdStr 不能为空");
//            return data;
//        }
//        Integer orgId = getIntegerIdFromEncryptStr(organizationIdStr);
//        condition.put("organization_id", orgId);
//
//        // 产品Id
//        String prodIdStr = StringUtils.trimToEmpty(request.getParameter("prodIdStr"));
//        if (StringUtils.isBlank(prodIdStr)) {
//            data.setCode(400);
//            data.setMsg("prodIdStr 不能为空");
//            return data;
//        }
//        condition.put("product_id", getIntegerIdFromEncryptStr(prodIdStr));
//
//        // 地区ID
//        String cityAreaIdStr = StringUtils.trimToEmpty(request.getParameter("cityIdStr"));
//        if (StringUtils.isBlank(cityAreaIdStr)) {
//            data.setCode(400);
//            data.setMsg("cityIdStr 不能为空");
//            return data;
//        }
//        condition.put("city_id", getIntegerIdFromEncryptStr(cityAreaIdStr));
//
//        List<ProdPriceDetail> prodPriceDetails = prodPriceAuditService.getProdPricesList(condition);
//        data.setCode(200);
//        data.setMsg("操作成功");
//        data.setData(prodPriceDetails);
//        return data;
//    }
//
//
//    /**
//     * 增加产品定价申请
//     */
//    @RequestMapping(path = "/add", method = RequestMethod.POST)
//    public ResponseData addProdPrices(HttpServletRequest request, HttpServletResponse response
//            , LoginUser loginUser) {
//
//        ResponseData data = new ResponseData();
//        ProdPriceAuditRequest priceAuditRequest = getProdPriceAuditRequest(request, loginUser, data);
//
//        if (priceAuditRequest == null) {
//            data.setCode(400);
//            return data;
//        }
//
//        List<Integer> priceAuditIds = prodPriceService.findPriceAuditIds(priceAuditRequest.getCityAreaIds());
//        List<ProdPriceAudit> lstProdPriceAudit = prodPriceAuditService.findUnAuditProdPriceAuditsBy(priceAuditRequest.getProdId(), priceAuditRequest.getOrganizationId(), priceAuditIds);
//        if (CollectionUtils.isNotEmpty(lstProdPriceAudit)) {
//            data.setCode(400);
//            data.setMsg("该产品还未审核通过的记录，不能进行再次上架操作！请联系管理员撤销上次上架申请！");
//            return data;
//        }
//        boolean isOk = prodPriceAuditService.addProdPriceAudit(priceAuditRequest, loginUser);
//        if (isOk) {
//            data.setMsg("操作成功");
//        } else {
//            data.setCode(400);
//            data.setMsg("请求出错");
//        }
//        return data;
//    }
//
//    /**
//     * 更新产品定价
//     */
//    @RequestMapping(path = "/update", method = RequestMethod.POST)
//    public ResponseData updateProdPrices(HttpServletRequest request, HttpServletResponse response
//            , LoginUser loginUser) {
//
//        ResponseData data = new ResponseData();
//        //String json = "{\"prodIdStr\":\"1XA~\",\"organizationIdStr\":\"1nY~\",\"cityProdPrices\":{\"1nR73dy9EFJQ\":[{\"pkid\":409,\"serviceId\":156,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":true,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.02,\"original_price\":-1,\"cost\":0.03,\"sort\":null,\"pkidStr\":\"03Rz\",\"originalPrice\":2},{\"pkid\":410,\"serviceId\":157,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":true,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.03,\"original_price\":-1,\"cost\":0.03,\"sort\":null,\"pkidStr\":\"03V6\",\"originalPrice\":2},{\"pkid\":433,\"serviceId\":156,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":true,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.11,\"original_price\":-1,\"cost\":0.09,\"sort\":null,\"pkidStr\":\"03d5\",\"originalPrice\":2},{\"pkid\":434,\"serviceId\":157,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":true,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.11,\"original_price\":-1,\"cost\":0.09,\"sort\":null,\"pkidStr\":\"03d-\",\"originalPrice\":2}],\"1nR72Ny9EFJQ\":[{\"pkid\":413,\"serviceId\":156,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":false,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.12,\"original_price\":-1,\"cost\":0.12,\"sort\":null,\"pkidStr\":\"03V5\",\"originalPrice\":3},{\"pkid\":414,\"serviceId\":157,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":false,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.12,\"original_price\":-1,\"cost\":0.02,\"sort\":null,\"pkidStr\":\"03V-\",\"originalPrice\":3},{\"pkid\":423,\"serviceId\":156,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":true,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.11,\"original_price\":-1,\"cost\":0.09,\"sort\":null,\"pkidStr\":\"03Z5\",\"originalPrice\":3},{\"pkid\":424,\"serviceId\":157,\"serviceName\":null,\"serviceTypeId\":null,\"serviceTypeName\":null,\"isMust\":true,\"unit\":null,\"hasStock\":null,\"stock\":-1,\"price\":0.11,\"original_price\":-1,\"cost\":0.09,\"sort\":null,\"pkidStr\":\"03Z-\",\"originalPrice\":3}]}}";
//
//        String json = getPostJsonString(request, data);
//
//        ProdPriceAuditUpdateRequest priceAuditRequest = JsonUtils.jsonToObject(json, ProdPriceAuditUpdateRequest.class);
//        if (priceAuditRequest == null) {
//            data.setCode(400);
//            return data;
//        }
//
//        //设置组织Id
//        Integer organizationId = getIntegerIdFromEncryptStr(priceAuditRequest.getOrganizationIdStr());
//        priceAuditRequest.setOrganizationId(organizationId);
//
//        //解密产品Id
//        Integer prodId = getIntegerIdFromEncryptStr(priceAuditRequest.getProdIdStr());
//        priceAuditRequest.setProdId(prodId);
//
//        Map<String, List<ProdPriceRequest>> cityProdPrices = priceAuditRequest.getCityProdPrices();
//        List<Integer> cityIds = null;
//        if (MapUtils.isNotEmpty(cityProdPrices)) {
//            cityIds = new ArrayList<>();
//            for (String key : cityProdPrices.keySet()) {
//                cityIds.add(NumberUtils.toInt(SecurityUtils.rc4Decrypt(key)));
//            }
//        }
//        List<Integer> priceAuditIds = prodPriceService.findPriceAuditIds(cityIds);
//        List<ProdPriceAudit> lstProdPriceAudit = prodPriceAuditService.findUnAuditProdPriceAuditsBy(priceAuditRequest.getProdId(), priceAuditRequest.getOrganizationId(), priceAuditIds);
//        if (CollectionUtils.isNotEmpty(lstProdPriceAudit)) {
//            data.setCode(400);
//            data.setMsg("该产品还未审核通过上架申请，不能进行再次上架操作！请联系管理员撤销上次上架申请！");
//            return data;
//        }
//        boolean isOk = prodPriceAuditService.updateProdPriceAudit(priceAuditRequest, loginUser);
//        if (isOk) {
//            data.setMsg("操作成功");
//        } else {
//            data.setCode(400);
//            data.setMsg("请求出错");
//        }
//        return data;
//    }
//
//
//    /**
//     * 审核产品价格
//     */
//    @RequestMapping("/audit")
//    public ResponseData auditProdPrice(HttpServletRequest request, HttpServletResponse response
//            , LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String pkidStr = StringUtils.trimToEmpty(request.getParameter("pkidStr"));
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = NumberUtils.toInt(pkidStr);
//
//        String content = StringUtils.trimToEmpty(request.getParameter("content"));
//        String isAllowStr = StringUtils.trimToEmpty(request.getParameter("isAllowed"));
//        if (StringUtils.isBlank(isAllowStr)) {
//            data.setCode(400);
//            data.setMsg("isAllowed 参数必须指定");
//            return data;
//        }
//        boolean isAllowed = false;
//        if ("true".equals(isAllowStr)) {
//            isAllowed = true;
//        }
//
//        boolean isOk = prodPriceAuditService.updateProdPriceAuditStatus(pkid, content, isAllowed, loginUser);
//
//        if (isOk) {
//            data.setMsg("操作成功");
//        } else {
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    /**
//     * 获取尚未审核的上架地区
//     */
//    @RequestMapping("/UnAuidtCitys")
//    public ResponseData getUnauditPriceCitys(HttpServletRequest request, HttpServletResponse response
//            , LoginUser loginUser) {
//
//        ResponseData data = new ResponseData();
//
//        List<UcOrganization> orgs = loginUser.getUcOrganizationList();
//        if (CollectionUtils.isEmpty(orgs)) {
//            data.setCode(400);
//            data.setMsg("找不到登陆用户的相关组织");
//            return data;
//        }
//
//        //获取产品Id
//        Integer prodId = getIntegerIdFromEncryptStr(request.getParameter("prodIdStr"));
//        if (prodId < 1) {
//            data.setCode(400);
//            data.setMsg("找不到相关产品");
//            return data;
//        }
//
//        List<CityArea> cityAreass = prodPriceAuditService.findUnauditCityAreasByProdIdAndOrgId(prodId, orgs.get(1).getPkid());
//        data.setCode(200);
//        data.setMsg("成功获取数据");
//        data.setData(cityAreass);
//
//        return data;
//    }
//
//    private Integer getId(HttpServletRequest request) {
//        // 产品定价审批ID
//        String pkidStr = request.getParameter("pkidStr");
//        if (StringUtils.isNotBlank(pkidStr)) {
//            //TODO 正式环境需要开启解密
//            pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        }
//        return NumberUtils.toInt(pkidStr, -1);
//    }
//
//    private Integer getIntegerIdFromEncryptStr(String encryKeyStr) {
//        String pkidStr = encryKeyStr;
//        if (StringUtils.isNotBlank(pkidStr)) {
//            pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        }
//        return NumberUtils.toInt(pkidStr, -1);
//    }
//
//    private ProdPriceAuditRequest getProdPriceAuditRequest(HttpServletRequest request, LoginUser loginUser, ResponseData data) {
//        ProdPriceAuditRequest priceAuditRequest = null;
//        List<UcOrganization> orgs = loginUser.getUcOrganizationList();
//
//        if (CollectionUtils.isEmpty(orgs)) {
//            data.setMsg("无法获取相应的组织Id");
//            return null;
//        }
//
//        String json = getPostJsonString(request, data);
//
//        priceAuditRequest = JsonUtils.jsonToObject(json, ProdPriceAuditRequest.class);
//
//        if (priceAuditRequest == null) {
//            data.setCode(400);
//            data.setMsg("请求参数不符合格式");
//            return null;
//        }
//        //设置组织Id
//        Integer organizationId = orgs.get(0).getPkid();
//        priceAuditRequest.setOrganizationId(organizationId);
//
//        //解密产品Id
//        Integer prodId = getIntegerIdFromEncryptStr(priceAuditRequest.getProdIdStr());
//        priceAuditRequest.setProdId(prodId);
//
//        //解密服务Id
//        for (ProdPriceRequest priceRequest : priceAuditRequest.getProdServices()) {
//            Integer serviceId = getIntegerIdFromEncryptStr(priceRequest.getServiceIdStr());
//            if (serviceId < 0) {
//                data.setMsg("找不到相应的服务");
//                return null;
//            }
//            priceRequest.setProdServiceId(serviceId);
//        }
//        //解密城市Id
//        List<Integer> cityIds = new ArrayList<>();
//        for (String cityIdStr : priceAuditRequest.getCityAreaIdStrs()) {
//            Integer cityId = getIntegerIdFromEncryptStr(cityIdStr);
//            if (cityId < 0) {
//                data.setMsg("找不到相应的城市");
//                return null;
//            }
//            cityIds.add(cityId);
//        }
//        priceAuditRequest.setCityAreaIds(cityIds);
//
//        return priceAuditRequest;
//    }
//
//    /**
//     * 获取Post方法时，Post 的Body中Json。
//     */
//    private String getPostJsonString(HttpServletRequest request, ResponseData data) {
//        String json = null;
//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            try {
//                json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            } catch (IOException e) {
//                e.printStackTrace();
//                data.setMsg(e.getMessage());
//            }
//        }
//        return json;
//    }
//
//    /**
//     * 获取产品价格参数
//     */
//    private Map<String, Object> getQueryProdPriceRowsParas(HttpServletRequest request) {
//
//        // 产品名称
//        String prodName = StringUtils.trimToEmpty(request.getParameter("prodName"));
//        //组织id
//        String organizationIdStr = StringUtils.trimToEmpty(request.getParameter("organizationIdStr"));
//        // 审核状态
//        String auditStatusStr = StringUtils.trimToEmpty(request.getParameter("auditStatusIdStr"));
//        // 地区ID
//        String cityAreaIdStr = StringUtils.trimToEmpty(request.getParameter("cityIdStr"));
//        // 销售方名称(组织)
//        String organizationName = StringUtils.trimToEmpty(request.getParameter("organizationName"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (StringUtils.isNotBlank(prodName)) {
//            condition.put("name", prodName + "%");
//        }
//        if (StringUtils.isNotBlank(organizationIdStr)) {
//            Integer organizationId = getIntegerIdFromEncryptStr(organizationIdStr);
//            condition.put("organization_id", organizationId);
//        }
//        if (StringUtils.isNotBlank(cityAreaIdStr)) {
//            Integer cityAreaId = getIntegerIdFromEncryptStr(cityAreaIdStr);
//            condition.put("city_id", cityAreaId);
//        }
//        if (StringUtils.isNotBlank(auditStatusStr)) {
//            condition.put("audit_status_id", auditStatusStr);
//        }
//        if (StringUtils.isNotBlank(organizationName)) {
//            condition.put("organizationName", organizationName + "%");
//        }
//        return condition;
//    }
}


