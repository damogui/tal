package org.netsharp.api.controller.uc;


import javax.ws.rs.Path;

@Path("/ucaccountunfollowcompany")
public class UcAccountUnfollowCompanyController {
//
//    @Autowired
//    private UcAccountUnfollowCompanyService ucAccountUnfollowCompanyService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountUnfollowCompany ucAccountUnfollowCompany) {
//        ResponseData data = new ResponseData();
//        ucAccountUnfollowCompanyService.insert(ucAccountUnfollowCompany);
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
//        ucAccountUnfollowCompanyService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountUnfollowCompany ucAccountUnfollowCompany) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ucAccountUnfollowCompany.setPkid(pkid);
//        ucAccountUnfollowCompanyService.update(ucAccountUnfollowCompany);
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
//        Pager<UcAccountUnfollowCompany> pager = ucAccountUnfollowCompanyService.pageByProperties(null, Integer.valueOf(page));
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
//        UcAccountUnfollowCompany ucAccountUnfollowCompany = ucAccountUnfollowCompanyService.findById(pkid);
//        data.setData(ucAccountUnfollowCompany);
//        return data;
//    }

}