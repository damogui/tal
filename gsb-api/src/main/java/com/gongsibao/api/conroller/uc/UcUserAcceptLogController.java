package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucuseracceptlog")
public class UcUserAcceptLogController {
//
//    @Autowired
//    private UcUserAcceptLogService ucUserAcceptLogService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcUserAcceptLog ucUserAcceptLog) {
//        ResponseData data = new ResponseData();
//        ucUserAcceptLogService.insert(ucUserAcceptLog);
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
//        Pager<UcUserAcceptLog> pager = ucUserAcceptLogService.pageByProperties(null, Integer.valueOf(page));
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
//        UcUserAcceptLog ucUserAcceptLog = ucUserAcceptLogService.findById(pkid);
//        data.setData(ucUserAcceptLog);
//        return data;
//    }

}