package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cms/productrelated")
public class CmsProductRelatedController extends CmsBaseController {
//
//    @Autowired
//    private CmsProductRelatedService cmsProductRelatedService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductRelated  cmsProductRelated) {
//        ResponseData data = new ResponseData();
//        cmsProductRelatedService.insert( cmsProductRelated);
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
//        cmsProductRelatedService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductRelated  cmsProductRelated) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//         cmsProductRelated.setPkid(pkid);
//        cmsProductRelatedService.update( cmsProductRelated);
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
//        Pager<CmsProductRelated> pager = cmsProductRelatedService.pageByProperties(null, Integer.valueOf(page));
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
//        CmsProductRelated  cmsProductRelated = cmsProductRelatedService.findById(pkid);
//        data.setData( cmsProductRelated);
//        return data;
//    }
//
//
//    @RequestMapping("/getprodnotrelated")
//    public ResponseData getprodnotrelated(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String prodIdStr = request.getParameter("prodIdStr");
//        String page = request.getParameter("currentPage");
//        String pagesize = request.getParameter("pageSize");
//        if (StringUtils.isEmpty(prodIdStr)) {
//            data.setMsg("产品id不能为空");
//            data.setCode(402);
//            return data;
//        }
//        if (StringUtils.isBlank(page)) {
//            page = "1";
//        }
//        if (StringUtils.isBlank(pagesize)) {
//            pagesize = "10";
//        }
//        prodIdStr = SecurityUtils.rc4Decrypt(prodIdStr);
//        Integer prodpkid = Integer.valueOf(prodIdStr);
//        Pager<ProdProduct>  cmsProductRelated = cmsProductRelatedService.getProdNotRelated(prodpkid, Integer.parseInt(page), Integer.parseInt(pagesize));
//        data.setData( cmsProductRelated);
//        return data;
//    }

}