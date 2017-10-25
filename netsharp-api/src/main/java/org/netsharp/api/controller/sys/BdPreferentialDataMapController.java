package org.netsharp.api.controller.sys;


import javax.ws.rs.Path;

@Path("/bdpreferentialdatamap")
public class BdPreferentialDataMapController {
//
//    @Autowired
//    private BdPreferentialDataMapService bdPreferentialDataMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdPreferentialDataMap bdPreferentialDataMap) {
//        ResponseData data = new ResponseData();
//        bdPreferentialDataMapService.insert(bdPreferentialDataMap);
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
//        Pager<BdPreferentialDataMap> pager = bdPreferentialDataMapService.pageByProperties(null, Integer.valueOf(page));
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
//        BdPreferentialDataMap bdPreferentialDataMap = bdPreferentialDataMapService.findById(pkid);
//        data.setData(bdPreferentialDataMap);
//        return data;
//    }

}