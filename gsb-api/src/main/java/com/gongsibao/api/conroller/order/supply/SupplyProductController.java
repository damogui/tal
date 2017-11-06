package com.gongsibao.api.conroller.order.supply;

import javax.ws.rs.Path;

/**
 * Created by duan on 07-04.
 */
@Path("/api/supply/product")
public class SupplyProductController {
//    @Autowired
//    private SupplyProdService supplyProdService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    private Integer DecryptPkIdStr(String pkIdStr) {
//        String str = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkid = NumberUtils.toInt(str);
//        return pkid;
//    }
//
//    /***
//     * 供应商支持的产品列表
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData productList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = new HashMap<>();
//        //当前用户所属的组织机构节点
//        Integer userId = loginUser.getUcUser().getPkid();
//        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
//        map.put("ids", ids);
//        List<SupplyProdList> supplyProdLists = supplyProdService.getSupplyProdList(map);
//        data.setData(supplyProdLists);
//        return data;
//    }
//
//    /***
//     * 供应商支持的产品一级分类
//     */
//    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
//    public ResponseData productTypeList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Integer userId = loginUser.getUcUser().getPkid();
//        List<BdDict> bdDictList = supplyProdService.getProductTypeList(userId);
//        data.setData(bdDictList);
//        return data;
//    }
//
//    /***
//     * 供应商实际发布的服务列表
//     *
//     * @return
//     */
//    @RequestMapping(value = "/detailList", method = RequestMethod.GET)
//    public ResponseData detailList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = new HashMap<>();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Integer userId = loginUser.getUcUser().getPkid();
//        setupParameters(map, request);
//        //当前用户所属的组织机构节点
//        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
//        map.put("ids", ids);
//        Pager<DetailProdList> detailProdLists = supplyProdService.getDetailProdList(map, Integer.valueOf(page), pageSize);
//        data.setData(detailProdLists);
//        return data;
//    }
//
//    /***
//     * 供应商审核中的服务列表
//     *
//     * @return
//     */
//    @RequestMapping(value = "/detailAuditList", method = RequestMethod.GET)
//    public ResponseData detaiAuditlList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = new HashMap<>();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Integer userId = loginUser.getUcUser().getPkid();
//        setupParameters(map, request);
//        //当前用户所属的组织机构节点
//        List<Integer> ids = ucUserService.getUserOrganizationIds(userId);
//        map.put("ids", ids);
//        Pager<DetailProdList> detailProdLists = supplyProdService.getDetailAuditProdList(map, Integer.valueOf(page), pageSize);
//        data.setData(detailProdLists);
//        return data;
//    }
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        //产品ID
//        String productIdStr = request.getParameter("productIdStr");
//        if (StringUtils.isNoneBlank(productIdStr)) {
//            map.put("productId", DecryptPkIdStr(productIdStr));
//        }
//        //地区ID
//        String cityIdStr = request.getParameter("cityIdStr");
//        if (StringUtils.isNoneBlank(cityIdStr)) {
//            map.put("cityId", DecryptPkIdStr(cityIdStr));
//        }
//    }

}
