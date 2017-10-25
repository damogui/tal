package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cms/recommendpackage")
public class RecommendPackageController extends CmsBaseController {
//
//    @Autowired
//    private CmsRecommendPackageService cmsRecommendPackageService;
//
//
//    /**
//     * 加载列表数据跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/list/data")
//    public ResponseData data(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("status", CMSBase.STATUS_SHOW);
//        List<CmsRecommendPackage> list = cmsRecommendPackageService.listByProperties(properties);
//        data.setData(list);
//        return data;
//    }
//
//    /**
//     * 编辑数据跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        CmsRecommendPackage cmsRecommendPackage = cmsRecommendPackageService.findById(getPkid(request));
//        data.setData(cmsRecommendPackage);
//        return data;
//    }
//
//    /**
//     * 新增或修改（编辑）操作跳转
//     *
//     * @param request
//     * @param response
//     * @param cmsRecommendPackage
//     * @return ResponseData
//     */
//    @RequestMapping("/edit")
//    public ResponseData edit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsRecommendPackage cmsRecommendPackage, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        if (pkid == 0) {
//            cmsRecommendPackage.setStatus(CMSBase.STATUS_INIT);
//            cmsRecommendPackage.setSort(0);
//            cmsRecommendPackage.setAddUser(user.getUcUser().getPkid());
//            cmsRecommendPackage.setUpdUser(user.getUcUser().getPkid());
//            cmsRecommendPackageService.insert(cmsRecommendPackage);
//        } else {
//            cmsRecommendPackage.setPkid(pkid);
//            cmsRecommendPackageService.update(cmsRecommendPackage);
//        }
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 删除操作跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        cmsRecommendPackageService.delete(pkid, user.getUcUser().getPkid());
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 上移操作跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/up")
//    public ResponseData up(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        Boolean bool = cmsRecommendPackageService.editSort(pkid, Boolean.TRUE, user.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
//
//    /**
//     * 下移操作跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/down")
//    public ResponseData down(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        Boolean bool = cmsRecommendPackageService.editSort(pkid, Boolean.FALSE, user.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
//
//    /**
//     * 发布操作跳转
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/publish")
//    public ResponseData publish(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        String ids = StringUtils.trimToEmpty(request.getParameter("ids"));
//        List<Integer> idList = new ArrayList<>();
//        String[] idArray = ids.split(",");
//
//        for (String idStr : idArray) {
//            int id = NumberUtils.toInt(SecurityUtils.rc4Decrypt(idStr));
//            if (id > 0) {
//                idList.add(id);
//            }
//        }
//        Boolean bool = cmsRecommendPackageService.editPublish(idList, loginUser.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
}