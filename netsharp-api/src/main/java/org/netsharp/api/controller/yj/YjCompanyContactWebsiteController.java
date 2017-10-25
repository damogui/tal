package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanycontactwebsite")
public class YjCompanyContactWebsiteController {
//
//    @Autowired
//    private YjCompanyContactWebsiteService yjCompanyContactWebsiteService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyContactWebsite yjCompanyContactWebsite) {
//        ResponseData data = new ResponseData();
//        yjCompanyContactWebsiteService.insert(yjCompanyContactWebsite);
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
//        Pager<YjCompanyContactWebsite> pager = yjCompanyContactWebsiteService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyContactWebsite yjCompanyContactWebsite = yjCompanyContactWebsiteService.findById(pkid);
//        data.setData(yjCompanyContactWebsite);
//        return data;
//    }

}