package com.gongsibao.api.conroller.sys;


import javax.ws.rs.Path;

@Path("/cmsproductpackagerelated")
public class CmsProductPackageRelatedController {
//
//    @Autowired
//    private CmsProductPackageRelatedService cmsProductPackageRelatedService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductPackageRelated cmsProductPackageRelated) {
//        ResponseData data = new ResponseData();
//        cmsProductPackageRelatedService.insert(cmsProductPackageRelated);
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
//        Pager<CmsProductPackageRelated> pager = cmsProductPackageRelatedService.pageByProperties(null, Integer.valueOf(page));
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
//        CmsProductPackageRelated cmsProductPackageRelated = cmsProductPackageRelatedService.findById(pkid);
//        data.setData(cmsProductPackageRelated);
//        return data;
//    }

}