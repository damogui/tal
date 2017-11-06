package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/UcAccountDingtalkCompany")
public class UcAccountDingtalkCompanyController {
//
//    @Autowired
//    private UcAccountDingtalkCompanyService UcAccountDingtalkCompanyService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountDingtalkCompany UcAccountDingtalkCompany) {
//        ResponseData data = new ResponseData();
//        UcAccountDingtalkCompanyService.insert(UcAccountDingtalkCompany);
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
//        UcAccountDingtalkCompanyService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountDingtalkCompany UcAccountDingtalkCompany) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        UcAccountDingtalkCompany.setPkid(pkid);
//        UcAccountDingtalkCompanyService.update(UcAccountDingtalkCompany);
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
//        Pager<UcAccountDingtalkCompany> pager = UcAccountDingtalkCompanyService.pageByProperties(null, Integer.valueOf(page));
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
//        UcAccountDingtalkCompany UcAccountDingtalkCompany = UcAccountDingtalkCompanyService.findById(pkid);
//        data.setData(UcAccountDingtalkCompany);
//        return data;
//    }

}