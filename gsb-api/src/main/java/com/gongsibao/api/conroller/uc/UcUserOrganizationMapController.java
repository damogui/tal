package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucuserorganizationmap")
public class UcUserOrganizationMapController {
//
//    @Autowired
//    private UcUserOrganizationMapService ucUserOrganizationMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcUserOrganizationMap ucUserOrganizationMap) {
//        ResponseData data = new ResponseData();
//        ucUserOrganizationMapService.insert(ucUserOrganizationMap);
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
//        ucUserOrganizationMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcUserOrganizationMap ucUserOrganizationMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ucUserOrganizationMap.setPkid(pkid);
//        ucUserOrganizationMapService.update(ucUserOrganizationMap);
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
//        Pager<UcUserOrganizationMap> pager = ucUserOrganizationMapService.pageByProperties(null, Integer.valueOf(page));
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
//        UcUserOrganizationMap ucUserOrganizationMap = ucUserOrganizationMapService.findById(pkid);
//        data.setData(ucUserOrganizationMap);
//        return data;
//    }

}