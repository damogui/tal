package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanychangerecord")
public class YjCompanyChangeRecordController {
//
//    @Autowired
//    private YjCompanyChangeRecordService yjCompanyChangeRecordService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyChangeRecord yjCompanyChangeRecord) {
//        ResponseData data = new ResponseData();
//        yjCompanyChangeRecordService.insert(yjCompanyChangeRecord);
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
//        Pager<YjCompanyChangeRecord> pager = yjCompanyChangeRecordService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyChangeRecord yjCompanyChangeRecord = yjCompanyChangeRecordService.findById(pkid);
//        data.setData(yjCompanyChangeRecord);
//        return data;
//    }

}