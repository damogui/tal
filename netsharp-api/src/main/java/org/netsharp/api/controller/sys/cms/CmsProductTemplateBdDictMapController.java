package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cms/producttemplatebddictmap")
public class CmsProductTemplateBdDictMapController extends CmsBaseController {
//
//    @Autowired
//    private CmsProductTemplateBdDictMapService cmsProductTemplateBdDictMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductTemplateBdDictMap cmsProductTemplateBdDictMap) {
//        ResponseData data = new ResponseData();
//        cmsProductTemplateBdDictMapService.insert(cmsProductTemplateBdDictMap);
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
//        cmsProductTemplateBdDictMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductTemplateBdDictMap cmsProductTemplateBdDictMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        cmsProductTemplateBdDictMap.setPkid(pkid);
//        cmsProductTemplateBdDictMapService.update(cmsProductTemplateBdDictMap);
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
//        Pager<CmsProductTemplateBdDictMap> pager = cmsProductTemplateBdDictMapService.pageByProperties(null, Integer.valueOf(page));
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
//        CmsProductTemplateBdDictMap cmsProductTemplateBdDictMap = cmsProductTemplateBdDictMapService.findById(pkid);
//        data.setData(cmsProductTemplateBdDictMap);
//        return data;
//    }

}