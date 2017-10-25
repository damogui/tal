package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanycontactinfo")
public class YjCompanyContactinfoController {
//
//    @Autowired
//    private YjCompanyContactinfoService yjCompanyContactinfoService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyContactinfo yjCompanyContactinfo) {
//        ResponseData data = new ResponseData();
//        yjCompanyContactinfoService.insert(yjCompanyContactinfo);
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
//        Pager<YjCompanyContactinfo> pager = yjCompanyContactinfoService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyContactinfo yjCompanyContactinfo = yjCompanyContactinfoService.findById(pkid);
//        data.setData(yjCompanyContactinfo);
//        return data;
//    }

}