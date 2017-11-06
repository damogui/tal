package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyplayerpartnermap")
public class YjCompanyPlayerPartnerMapController {
//
//    @Autowired
//    private YjCompanyPlayerPartnerMapService yjCompanyPlayerPartnerMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyPlayerPartnerMap yjCompanyPlayerPartnerMap) {
//        ResponseData data = new ResponseData();
//        yjCompanyPlayerPartnerMapService.insert(yjCompanyPlayerPartnerMap);
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
//        Pager<YjCompanyPlayerPartnerMap> pager = yjCompanyPlayerPartnerMapService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyPlayerPartnerMap yjCompanyPlayerPartnerMap = yjCompanyPlayerPartnerMapService.findById(pkid);
//        data.setData(yjCompanyPlayerPartnerMap);
//        return data;
//    }

}