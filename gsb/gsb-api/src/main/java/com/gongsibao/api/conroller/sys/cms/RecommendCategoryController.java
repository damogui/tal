package com.gongsibao.api.conroller.sys.cms;


import javax.ws.rs.Path;

import com.gongsibao.api.conroller.sys.cms.base.CmsBaseController;

@Path("/cms/recommendcategory")
public class RecommendCategoryController extends CmsBaseController {
//
//    @Autowired
//    private CmsRecommendCategoryService cmsRecommendCategoryService;
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        cmsRecommendCategoryService.delete(getPkid(request), user.getUcUser().getPkid());
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/edit")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        String description = StringUtils.trimToEmpty(request.getParameter("description"));
//        String img = StringUtils.trimToEmpty(request.getParameter("img"));
//        String bgImg = StringUtils.trimToEmpty(request.getParameter("bgImg"));
//
//        CmsRecommendCategory cmsRecommendCategory = new CmsRecommendCategory();
//        cmsRecommendCategory.setPkid(pkid);
//        cmsRecommendCategory.setName(name);
//        cmsRecommendCategory.setDescription(description);
//        cmsRecommendCategory.setImg(img);
//        cmsRecommendCategory.setBgImg(bgImg);
//        cmsRecommendCategory.setAddUser(user.getUcUser().getPkid());
//        cmsRecommendCategory.setUpdUser(user.getUcUser().getPkid());
//
//        if (pkid == 0) {
//            cmsRecommendCategory.setSort(0);
//            cmsRecommendCategory.setStatus(CMSBase.STATUS_INIT);
//            cmsRecommendCategoryService.insert(cmsRecommendCategory);
//        } else {
//            cmsRecommendCategoryService.updateInfo(cmsRecommendCategory);
//        }
//
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("status", CMSBase.STATUS_SHOW);
//
//        List<CmsRecommendCategory> list = cmsRecommendCategoryService.listByProperties(properties);
//        data.setData(list);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int pkid = getPkid(request);
//
//        CmsRecommendCategory cmsRecommendCategory = cmsRecommendCategoryService.findById(pkid);
//        data.setData(cmsRecommendCategory);
//        return data;
//    }
//
//    @RequestMapping("/publish")
//    public ResponseData publish(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        String ids = StringUtils.trimToEmpty(request.getParameter("ids"));
//        String[] idArr = ids.split(",");
//        List<Integer> idList = new ArrayList<>();
//        for (String idStr : idArr) {
//            int id = NumberUtils.toInt(SecurityUtils.rc4Decrypt(idStr));
//            if (id > 0) {
//                idList.add(id);
//            }
//        }
//
//        cmsRecommendCategoryService.editPublish(idList, user.getUcUser().getPkid());
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/up")
//    public ResponseData up(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//
//        Boolean bool = cmsRecommendCategoryService.editSort(pkid, Boolean.TRUE, user.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
//
//    @RequestMapping("/down")
//    public ResponseData down(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//
//        Boolean bool = cmsRecommendCategoryService.editSort(pkid, Boolean.FALSE, user.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
}