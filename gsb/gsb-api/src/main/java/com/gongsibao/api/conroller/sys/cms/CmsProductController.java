package com.gongsibao.api.conroller.sys.cms;


import javax.ws.rs.Path;

import com.gongsibao.api.conroller.sys.cms.base.CmsBaseController;

@Path("/cms/product")
public class CmsProductController extends CmsBaseController {
//
//    //region 服务
//    @Autowired
//    private CmsProductService cmsProductService;
//    @Autowired
//    private CmsProductAggregationMapService cmsProductAggregationMapService;
//    @Autowired
//    private ProdProductService prodProductService;
//    // endregion
//
//    //region 自动生成的代码
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProduct cmsProduct) {
//        ResponseData data = new ResponseData();
//        cmsProductService.insert(cmsProduct);
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
//        cmsProductService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProduct cmsProduct) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        cmsProduct.setPkid(pkid);
//        cmsProductService.update(cmsProduct);
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
//        Pager<CmsProduct> pager = cmsProductService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/decrypteny")
//    public ResponseData DecryptEny(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String res;
//        //1:加密 0：解密
//        String type = request.getParameter("type");
//        String id = request.getParameter("id");
//        res = type.equals("1") ? SecurityUtils.rc4Encrypt(Integer.parseInt(id)) : SecurityUtils.rc4Decrypt(id);
//        HashMap<String, Object> resmap = new HashMap<>();
//        resmap.put("id", res);
//        data.setData(resmap);
//        return data;
//    }
//    // endregion
//
//    //region cms信息的Controller
//    //region 单品和聚合
//
//    /**
//     * 根据cmsid获取单品配置的cms信息
//     *
//     * @param request  pkidStr:cmsid（加密）
//     * @param response
//     * @return
//     */
//    @RequestMapping("/getById")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        Integer pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//
//        CmsProduct cmsProduct = cmsProductService.getDataById(pkid);
//        data.setData(cmsProduct);
//        return data;
//    }
//
//    /**
//     * 根据产品类别id获取cms信息列表
//     *
//     * @param request  typeIdStr:产品类别id（加密）
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/getByProductTypeId")
//    public ResponseData getByProductTypeId(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int prodTypeId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("typeIdStr"))));
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<CmsProduct> cmsProductPagerList = cmsProductService.getByProductTypeId(prodTypeId, currentPage, pageSize);
//        data.setData(cmsProductPagerList);
//        return data;
//    }
//
//    /**
//     * 根据产品类别id获取没有添加cms信息的产品信息
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/getNoCmsProdList")
//    public ResponseData getNoCmsProdList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int prodTypeId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("typeIdStr"))));
//        int cmsId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("cmsProdId"))));
//        if (prodTypeId == 0) {
//            data.setCode(-1);
//            data.setMsg("typeIdStr参数错误");
//            return data;
//        }
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<ProdProduct> noCmsProdList = cmsProductService.getNoCmsProdList(prodTypeId, cmsId, currentPage, pageSize);
//        data.setData(noCmsProdList);
//        return data;
//    }
//
//    /**
//     * 获取已经配过的cms信息的产品类别信息（树结构）
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/getProductTypeByHasCms")
//    public ResponseData getProductTypeByHasCms(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        List<BdDict> productTypeByHasCms = cmsProductService.getProductTypeByHasCms();
//        data.setData(productTypeByHasCms);
//        return data;
//    }
//
//    /**
//     * 通过产品类别获取已经配过的cms信息的产品信息
//     *
//     * @param request  typeIdStr:产品类别id
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/getProductByHasCmsTypeId")
//    public ResponseData getProductByHasCmsTypeId(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        //产品类别
//        Integer prodTypeId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("typeIdStr"))));
//        if (prodTypeId == 0) {
//            data.setCode(-1);
//            data.setMsg("typeIdStr参数错误");
//            return data;
//        }
//        //是否排除已经聚合的产品(0：不排除（添加推荐产品传0，因为一个产品可以被推荐多次）；1：排除（添加聚合产品传1，因为同一个产品不能数据多个聚合信息）)
//        Integer isRemoveAggregation = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("isRemoveAggregation")));
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<ProdProduct> productByHasCmsTypeIdList = cmsProductService.getProductByHasCmsTypeId(prodTypeId, isRemoveAggregation, currentPage, pageSize);
//        data.setData(productByHasCmsTypeIdList);
//        return data;
//    }
//
//    /**
//     * 保存单品配置的cms信息
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @param jsonParam(json字符串)
//     * @return
//     */
//    @RequestMapping("/saveSingleProd")
//    public ResponseData saveSingleProd(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody String jsonParam) {
//        ResponseData data = new ResponseData();
//        CmsProduct cmsProduct = JsonUtils.jsonToObject(jsonParam, CmsProduct.class);
//        //region 参数验证
//        if (cmsProduct.getProductId().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("关联商品参数错误");
//            return data;
//        }
//        if (StringUtils.isBlank(cmsProduct.getProdName())) {
//            data.setCode(-1);
//            data.setMsg("商品名称不能为空");
//            return data;
//        }
//        if (StringUtils.isBlank(cmsProduct.getShowprice())) {
//            data.setCode(-1);
//            data.setMsg("列表价格不能为空");
//            return data;
//        }
//        if (cmsProduct.getSort() == null) {
//            data.setCode(-1);
//            data.setMsg("排序不能为空");
//            return data;
//        }
//        /*if (cmsProduct.getPcProductImgList().isEmpty()) {
//            data.setCode(-1);
//            data.setMsg("PC产品图不能为空");
//            return data;
//        }
//        if (cmsProduct.getAppPordImgUrlId().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("手机产品图不能为空");
//            return data;
//        }
//        if (cmsProduct.getProdIconImgUrlId().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("产品小图标不能为空");
//            return data;
//        }*/
//        if (cmsProduct.getCmsProductTemplateList().stream().filter(x -> StringUtils.isBlank(x.getContent())).count() > 0) {
//            data.setCode(-1);
//            data.setMsg("产品的cms模板信息的商品详情不能为空！");
//            return data;
//        }
//        if (cmsProduct.getCmsProductTemplateList().stream().filter(x -> StringUtils.isBlank(x.getTitle())).count() > 0) {
//            data.setCode(-1);
//            data.setMsg("产品的cms模板信息的title不能为空！");
//            return data;
//        }
//        if (cmsProduct.getCmsProductTemplateList().stream().filter(x -> StringUtils.isBlank(x.getKeyword())).count() > 0) {
//            data.setCode(-1);
//            data.setMsg("产品的cms模板信息的关键词不能为空！");
//            return data;
//        }
//        for (CmsProductTemplate cmsProductTemplate : cmsProduct.getCmsProductTemplateList()) {
//            if (cmsProduct.getCmsProductTemplateList().stream().filter(x -> StringUtils.trimToEmpty(x.getName()).equals(StringUtils.trimToEmpty(cmsProductTemplate.getName()))).count() > 1) {
//                data.setCode(-1);
//                data.setMsg("产品的cms模板信息名称【" + cmsProductTemplate.getName() + "】,添加重复！");
//                return data;
//            }
//        }
//        if (cmsProduct.getCmsProductTemplateList().stream().filter(x -> x.getServiceCityList() == null || x.getServiceCityList().size() <= 0).count() <= 0) {
//            data.setCode(-1);
//            data.setMsg("产品的cms信息必须要有默认模板信息");
//            return data;
//        }
//        //该phpid的所有cms信息
//        if (!cmsProduct.getPhpId().equals(0)) {
//            //该phpid的所有cms信息
//            List<CmsProduct> phpidCmsProductList = cmsProductService.pageByProperties(new HashMap() {{
//                put("php_id", cmsProduct.getPhpId());
//                put("attribute_category", 0);
//            }}, 1).getList();
//            if (!phpidCmsProductList.isEmpty() && phpidCmsProductList.stream().noneMatch(x -> x.getProductId().equals(cmsProduct.getProductId()))) {
//                data.setCode(-1);
//                data.setMsg("该【原phpCMS的ID】已经存在，禁止保存");
//                return data;
//            }
//        }
//        cmsProduct.setAddUserId(user.getUcUser().getPkid());
//        // endregion
//
//        //region 调用服务以及对返回结果的验证
//        Integer cmsprodid = cmsProductService.saveSingleProd(cmsProduct);
//        if (cmsprodid.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不存在");
//            return data;
//        }
//        if (cmsprodid.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该产品不存在");
//            return data;
//        }
//        if (cmsprodid.equals(-2)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不是单品类别，禁止单品保存操作");
//            return data;
//        }
//        // endregion
//
//        //region 返回数据
//        data.setData(SecurityUtils.rc4Encrypt(cmsprodid));
//        data.setMsg("保存成功");
//        return data;
//        // endregion
//    }
//
//    /**
//     * 根据cmsid获取聚合产品配置的cms信息
//     *
//     * @param response
//     * @param request  pkidStr:主键id
//     * @param user
//     * @return
//     */
//    @RequestMapping("/getConvergeProdById")
//    public ResponseData getConvergeProdById(HttpServletResponse response, HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        CmsProduct cmsProduct = cmsProductService.getConvergeProdById(pkid);
//        if (cmsProduct.getPkid() == 0) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不存在");
//            return data;
//        }
//        data.setData(cmsProduct);
//        return data;
//    }
//
//    /**
//     * 发布（支持单个和批量）
//     *
//     * @param response
//     * @param request      pkidStrList:格式：[{pkidStr:'',isShow:1}],说明：‘pkidStr：主键’，‘isShow：是否显示’
//     * @param mapParamList
//     * @return
//     */
//    @RequestMapping("/releaseByIds")
//    public ResponseData releaseByIds(HttpServletResponse response, HttpServletRequest request, @RequestBody List<Map<String, Object>> mapParamList) {
//        ResponseData data = new ResponseData();
//        if (mapParamList == null) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        if (mapParamList.size() <= 0) {
//            data.setCode(-1);
//            data.setMsg("参数不能为空");
//            return data;
//        }
//        List<CmsProduct> paramCmsProductList = new ArrayList<>();
//        mapParamList.forEach(x -> {
//            paramCmsProductList.add(new CmsProduct() {{
//                setPkid(NumberUtils.toInt(SecurityUtils.rc4Decrypt(x.get("pkidStr"))));
//                //setIsShow(NumberUtils.toInt(x.get("isShow")));
//            }});
//        });
//        if (paramCmsProductList.stream().anyMatch(x -> x.getPkid().equals(0))) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        /*if (paramCmsProductList.stream().anyMatch(x -> x.getIsShow() == null)) {
//            data.setCode(-1);
//            data.setMsg("isShow参数错误");
//            return data;
//        }*/
//        cmsProductService.saveReleaseByIds(paramCmsProductList);
//        data.setMsg("发布成功");
//        return data;
//    }
//
//    @RequestMapping("/saveIsShow")
//    public ResponseData saveIsShow(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        Integer pkid = mapParam == null ? 0 : NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        Integer isShow = mapParam == null ? 0 : NumberUtils.toInt(mapParam.get("isShow"));
//        Integer type = mapParam == null ? 0 : NumberUtils.toInt(mapParam.get("type"));
//        if (type.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("type参数错误");
//            return data;
//        }
//        Integer res = cmsProductService.saveIsShow(pkid, isShow, type);
//        if (res.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不存在");
//            return data;
//        }
//        if (res.equals(-2)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不是单品信息");
//            return data;
//        }
//        if (res.equals(-3)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不是套餐信息");
//            return data;
//        }
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 根据最新的cmsid删除该产品的所有的cms信息
//     *
//     * @param response
//     * @param request  pkidStr：主键id，加密
//     * @param mapParam (参数map)
//     * @return
//     */
//    @RequestMapping("/deleteAllById")
//    public ResponseData deleteAllById(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        Integer pkid = mapParam == null ? 0 : NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        cmsProductService.deleteAllById(new ArrayList() {{
//            add(pkid);
//        }});
//        data.setMsg("删除成功");
//        return data;
//    }
//
//    /**
//     * 保存聚合商品的cms配置
//     *
//     * @param response
//     * @param request
//     * @param user
//     * @param jsonParam(json字符串)
//     * @return
//     */
//    @RequestMapping("/saveConvergeProd")
//    public ResponseData saveConvergeProd(HttpServletResponse response, HttpServletRequest request, LoginUser user, @RequestBody String jsonParam) {
//        ResponseData data = new ResponseData();
//        CmsProduct cmsProduct = JsonUtils.jsonToObject(jsonParam, CmsProduct.class);
//        //region 参数验证
//        if (StringUtils.isBlank(cmsProduct.getProdName())) {
//            data.setCode(-1);
//            data.setMsg("聚合商品名称不能为空");
//            return data;
//        }
//
//        if (cmsProduct.getCmsProductAggregationList().size() > 0 && cmsProduct.getCmsProductAggregationMapList().size() > 0) {
//            data.setCode(-1);
//            data.setMsg("当该cms信息添加无层级关联产品时，禁止添加聚合分类信息");
//            return data;
//        }
//
//        if (cmsProduct.getCmsProductAggregationList().stream().anyMatch(x -> x.getCmsProductAggregationMapList().size() > 0 && x.getChildren().size() > 0)) {
//            data.setCode(-1);
//            CmsProductAggregation cmsProductAggregation = cmsProduct.getCmsProductAggregationList().stream().filter(x -> x.getCmsProductAggregationMapList().size() > 0 && x.getChildren().size() > 0).collect(Collectors.toList()).get(0);
//            data.setMsg("聚合分类【" + cmsProductAggregation.getName() + "】,已经作为一级聚合了，不能在作为二级聚合，禁止保存");
//            return data;
//        }
//
//        //region 产品信息添加重复的验证
//        //产品id集合
//        List<Integer> prodIdList = new ArrayList<>();
//        prodIdList.addAll(cmsProduct.getCmsProductAggregationMapList().stream().map(x -> {
//            return x.getProdProductId();
//        }).collect(Collectors.toList()));
//        //递归获取该次保存的所有的产品id
//        diGuiGetProdIdList(cmsProduct.getCmsProductAggregationList(), prodIdList);
//        for (Integer prodid : prodIdList) {
//            if (prodIdList.stream().filter(pid -> pid.equals(prodid)).count() > 1) {
//                ProdProduct cfProd = prodProductService.findById(prodid);
//                data.setCode(-1);
//                if (cfProd == null) {
//                    data.setMsg("产品id:" + prodid + ",不存在对应的产品信息，禁止保存");
//                    return data;
//                }
//                data.setMsg("产品【" + cfProd.getName() + "】,添加重复，禁止保存");
//                return data;
//            }
//        }
//        for (Integer prodid : prodIdList) {
//            Map<String, Object> aggregationMapproperties = new HashMap<>();
//            aggregationMapproperties.put("prod_product_id", prodid);
//            if (!cmsProduct.getPkid().equals(0)) {//当是修改时
//                List<CmsProductAggregationMap> allByCmsProdIdList = cmsProductAggregationMapService.getAllByCmsProdId(cmsProduct.getPkid());
//                if (allByCmsProdIdList.stream().anyMatch(x -> x.getProdProductId().equals(prodid))) continue;
//            }
//
//            Pager<CmsProductAggregationMap> aggregationMap = cmsProductAggregationMapService.pageByProperties(aggregationMapproperties, 0, Integer.MAX_VALUE);
//            if (aggregationMap.getList().size() > 0) {
//                data.setCode(-1);
//                data.setMsg("产品【" + aggregationMap.getList().get(0).getProductName() + "】,已经添加过了聚合信息，禁止保存");
//                return data;
//            }
//        }
//        // endregion
//
//        // endregion
//
//        //region 调用服务，返回数据
//        cmsProduct.setAddUserId(user.getUcUser().getPkid());
//        Integer resCode = cmsProductService.saveConvergeProd(cmsProduct);
//        if (resCode.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不存在！");
//            return data;
//        }
//        if (resCode.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息是单品类别,禁止保存聚合信息！");
//            return data;
//        }
//        data.setData(SecurityUtils.rc4Encrypt(resCode));
//        data.setMsg("保存聚合cms信息成功");
//        return data;
//        // endregion
//
//    }
//
//    // endregion
//    //region 套餐
//    @RequestMapping("/getAllPackCityTreeByPackageId")
//    public ResponseData getAllPackCityTreeByPackageId(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Integer packageId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("packageIdStr")));
//        if (packageId.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("packageIdStr参数错误");
//            return data;
//        }
//        List<BdDict> allPackCityTreeByPackageIdList = cmsProductService.getAllPackCityTreeByPackageId(packageId);
//        data.setData(allPackCityTreeByPackageIdList);
//        return data;
//    }
//
//    @RequestMapping("/saveCmsPackage")
//    public ResponseData saveCmsPackage(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonParam, LoginUser user) {
//        ResponseData data = new ResponseData();
//        CmsProduct packCmsData = JsonUtils.jsonToObject(jsonParam, CmsProduct.class);
//        //region 参数验证
//        if (packCmsData.getPackageId().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("关联套餐参数错误");
//            return data;
//        }
//        if (StringUtils.isBlank(packCmsData.getProdName())) {
//            data.setCode(-1);
//            data.setMsg("套餐名称不能为空");
//            return data;
//        }
//        if (StringUtils.isBlank(packCmsData.getShowprice())) {
//            data.setCode(-1);
//            data.setMsg("列表价格不能为空");
//            return data;
//        }
//        if (packCmsData.getSort() == null) {
//            data.setCode(-1);
//            data.setMsg("排序不能为空");
//            return data;
//        }
//        /*if (packCmsData.getPcProductImgList().isEmpty()) {
//            data.setCode(-1);
//            data.setMsg("PC产品图不能为空");
//            return data;
//        }
//        if (packCmsData.getAppPordImgUrlId().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("手机产品图不能为空");
//            return data;
//        }*/
//        //该phpid的所有cms信息
//        if (!packCmsData.getPhpId().equals(0)) {
//            List<CmsProduct> phpidCmsProductList = cmsProductService.pageByProperties(new HashMap() {{
//                put("php_id", packCmsData.getPhpId());
//                put("attribute_category", 3);//套餐
//            }}, 1).getList();
//            if (!phpidCmsProductList.isEmpty() && phpidCmsProductList.stream().noneMatch(x -> x.getPackageId().equals(packCmsData.getPackageId()))) {
//                data.setCode(-1);
//                data.setMsg("该【原phpCMS的ID】已经存在，禁止保存");
//                return data;
//            }
//        }
//
//        // endregion
//        if (packCmsData.getCmsProductTemplateList().stream().filter(x -> StringUtils.isBlank(x.getContent())).count() > 0) {
//            data.setCode(-1);
//            data.setMsg("套餐的cms模板信息的商品详情不能为空！");
//            return data;
//        }
//        if (packCmsData.getCmsProductTemplateList().stream().filter(x -> StringUtils.isBlank(x.getTitle())).count() > 0) {
//            data.setCode(-1);
//            data.setMsg("套餐的cms模板信息的title不能为空！");
//            return data;
//        }
//        if (packCmsData.getCmsProductTemplateList().stream().filter(x -> StringUtils.isBlank(x.getKeyword())).count() > 0) {
//            data.setCode(-1);
//            data.setMsg("套餐的cms模板信息的关键词不能为空！");
//            return data;
//        }
//        for (CmsProductTemplate cmsProductTemplate : packCmsData.getCmsProductTemplateList()) {
//            if (packCmsData.getCmsProductTemplateList().stream().filter(x -> StringUtils.trimToEmpty(x.getName()).equals(StringUtils.trimToEmpty(cmsProductTemplate.getName()))).count() > 1) {
//                data.setCode(-1);
//                data.setMsg("套餐的cms模板信息名称【" + cmsProductTemplate.getName() + "】,添加重复！");
//                return data;
//            }
//        }
//        if (packCmsData.getCmsProductTemplateList().stream().filter(x -> x.getServiceCityList() == null || x.getServiceCityList().size() <= 0).count() <= 0) {
//            data.setCode(-1);
//            data.setMsg("套餐的cms信息必须要有默认模板信息");
//            return data;
//        }
//        packCmsData.setAddUserId(user.getUcUser().getPkid());
//        // endregion
//        //region 调用服务以及对返回结果的验证
//        Integer cmspackid = cmsProductService.saveCmsPackage(packCmsData);
//        if (cmspackid.equals(0)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不存在");
//            return data;
//        }
//        if (cmspackid.equals(-1)) {
//            data.setCode(-1);
//            data.setMsg("该套餐不存在");
//            return data;
//        }
//        if (cmspackid.equals(-2)) {
//            data.setCode(-1);
//            data.setMsg("该cms信息不是套餐类别，禁止单品保存操作");
//            return data;
//        }
//        // endregion
//        //region 返回数据
//        data.setData(SecurityUtils.rc4Encrypt(cmspackid));
//        data.setMsg("保存成功");
//        return data;
//        // endregion
//    }
//
//    @RequestMapping("/getCmsPackageList")
//    public ResponseData getCmsPackageList(HttpServletResponse response, HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<CmsProduct> cmsProductPagerList = cmsProductService.getCmsPackageList(currentPage, pageSize);
//        data.setData(cmsProductPagerList);
//        return data;
//    }
//    //endregion
//    // endregion
//
//    //region 私有方法
//    /*递归添加cms信息与聚合信息之间的关系*/
//    private void diGuiGetProdIdList(List<CmsProductAggregation> getCmsProductAggregationList, List<Integer> prodIdList) {
//        for (CmsProductAggregation aggregation : getCmsProductAggregationList) {
//            prodIdList.addAll(aggregation.getCmsProductAggregationMapList().stream().map(x -> {
//                return x.getProdProductId();
//            }).collect(Collectors.toList()));
//            //添加无层级的cms信息与产品之间的关系
//            if (aggregation.getChildren().size() > 0) {
//                diGuiGetProdIdList(aggregation.getChildren(), prodIdList);
//            }
//        }
//    }
//    // endregion

}