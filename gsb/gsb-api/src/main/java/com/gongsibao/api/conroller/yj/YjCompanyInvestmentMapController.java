package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyinvestmentmap")
public class YjCompanyInvestmentMapController {
//
//    @Autowired
//    private YjCompanyInvestmentMapService yjCompanyInvestmentMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyInvestmentMap yjCompanyInvestmentMap) {
//        ResponseData data = new ResponseData();
//        yjCompanyInvestmentMapService.insert(yjCompanyInvestmentMap);
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
//        Pager<YjCompanyInvestmentMap> pager = yjCompanyInvestmentMapService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyInvestmentMap yjCompanyInvestmentMap = yjCompanyInvestmentMapService.findById(pkid);
//        data.setData(yjCompanyInvestmentMap);
//        return data;
//    }

}