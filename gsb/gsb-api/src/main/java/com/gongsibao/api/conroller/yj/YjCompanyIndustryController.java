package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyindustry")
public class YjCompanyIndustryController {
//
//    @Autowired
//    private YjCompanyIndustryService yjCompanyIndustryService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyIndustry yjCompanyIndustry) {
//        ResponseData data = new ResponseData();
//        yjCompanyIndustryService.insert(yjCompanyIndustry);
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
//        Pager<YjCompanyIndustry> pager = yjCompanyIndustryService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyIndustry yjCompanyIndustry = yjCompanyIndustryService.findById(pkid);
//        data.setData(yjCompanyIndustry);
//        return data;
//    }

}