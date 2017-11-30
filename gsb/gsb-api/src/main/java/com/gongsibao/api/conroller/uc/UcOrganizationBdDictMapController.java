package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucorganizationbddictmap")
public class UcOrganizationBdDictMapController {
//
//    @Autowired
//    private UcOrganizationBdDictMapService ucOrganizationBdDictMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcOrganizationBdDictMap ucOrganizationBdDictMap) {
//        ResponseData data = new ResponseData();
//        ucOrganizationBdDictMapService.insert(ucOrganizationBdDictMap);
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
//        ucOrganizationBdDictMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcOrganizationBdDictMap ucOrganizationBdDictMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ucOrganizationBdDictMap.setPkid(pkid);
//        ucOrganizationBdDictMapService.update(ucOrganizationBdDictMap);
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
//        Pager<UcOrganizationBdDictMap> pager = ucOrganizationBdDictMapService.pageByProperties(null, Integer.valueOf(page));
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
//        UcOrganizationBdDictMap ucOrganizationBdDictMap = ucOrganizationBdDictMapService.findById(pkid);
//        data.setData(ucOrganizationBdDictMap);
//        return data;
//    }

}