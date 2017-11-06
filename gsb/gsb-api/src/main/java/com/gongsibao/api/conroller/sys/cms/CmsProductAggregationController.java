package com.gongsibao.api.conroller.sys.cms;


import javax.ws.rs.Path;

import com.gongsibao.api.conroller.sys.cms.base.CmsBaseController;

@Path("/cms/productaggregation")
public class CmsProductAggregationController extends CmsBaseController {
//
//    @Autowired
//    private CmsProductAggregationService cmsProductAggregationService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductAggregation cmsProductAggregation) {
//        ResponseData data = new ResponseData();
//        cmsProductAggregationService.insert(cmsProductAggregation);
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
//        cmsProductAggregationService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductAggregation cmsProductAggregation) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        cmsProductAggregation.setPkid(pkid);
//        cmsProductAggregationService.update(cmsProductAggregation);
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
//        Pager<CmsProductAggregation> pager = cmsProductAggregationService.pageByProperties(null, Integer.valueOf(page), 10);
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
//        CmsProductAggregation cmsProductAggregation = cmsProductAggregationService.findById(pkid);
//        data.setData(cmsProductAggregation);
//        return data;
//    }

}