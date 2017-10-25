package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cmsproductaggregationmap")
public class CmsProductAggregationMapController extends CmsBaseController {
//
//    @Autowired
//    private CmsProductAggregationMapService cmsProductAggregationMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductAggregationMap cmsProductAggregationMap) {
//        ResponseData data = new ResponseData();
//        cmsProductAggregationMapService.insert(cmsProductAggregationMap);
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
//        cmsProductAggregationMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsProductAggregationMap cmsProductAggregationMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        cmsProductAggregationMap.setPkid(pkid);
//        cmsProductAggregationMapService.update(cmsProductAggregationMap);
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
//        Pager<CmsProductAggregationMap> pager = cmsProductAggregationMapService.pageByProperties(null, Integer.valueOf(page),Integer.MAX_VALUE);
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
//        CmsProductAggregationMap cmsProductAggregationMap = cmsProductAggregationMapService.findById(pkid);
//        data.setData(cmsProductAggregationMap);
//        return data;
//    }

}