package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucuserbusiness")
public class UcUserBusinessController {
//
//    @Autowired
//    private UcUserBusinessService ucUserBusinessService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcUserBusiness ucUserBusiness) {
//        ResponseData data = new ResponseData();
//        ucUserBusinessService.insert(ucUserBusiness);
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
//        Pager<UcUserBusiness> pager = ucUserBusinessService.pageByProperties(null, Integer.valueOf(page));
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
//        UcUserBusiness ucUserBusiness = ucUserBusinessService.findById(pkid);
//        data.setData(ucUserBusiness);
//        return data;
//    }

}