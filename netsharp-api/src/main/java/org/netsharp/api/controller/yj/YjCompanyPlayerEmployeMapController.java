package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyplayeremployemap")
public class YjCompanyPlayerEmployeMapController {
//
//    @Autowired
//    private YjCompanyPlayerEmployeMapService yjCompanyPlayerEmployeMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyPlayerEmployeMap yjCompanyPlayerEmployeMap) {
//        ResponseData data = new ResponseData();
//        yjCompanyPlayerEmployeMapService.insert(yjCompanyPlayerEmployeMap);
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
//        Pager<YjCompanyPlayerEmployeMap> pager = yjCompanyPlayerEmployeMapService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyPlayerEmployeMap yjCompanyPlayerEmployeMap = yjCompanyPlayerEmployeMapService.findById(pkid);
//        data.setData(yjCompanyPlayerEmployeMap);
//        return data;
//    }

}