package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjtrademarkrecord")
public class YjTrademarkRecordController {
//
//    @Autowired
//    private YjTrademarkRecordService yjTrademarkRecordService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjTrademarkRecord yjTrademarkRecord) {
//        ResponseData data = new ResponseData();
//        yjTrademarkRecordService.insert(yjTrademarkRecord);
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
//        yjTrademarkRecordService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjTrademarkRecord yjTrademarkRecord) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        yjTrademarkRecord.setPkid(pkid);
//        yjTrademarkRecordService.update(yjTrademarkRecord);
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
//        Pager<YjTrademarkRecord> pager = yjTrademarkRecordService.pageByProperties(null, Integer.valueOf(page));
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
//        YjTrademarkRecord yjTrademarkRecord = yjTrademarkRecordService.findById(pkid);
//        data.setData(yjTrademarkRecord);
//        return data;
//    }

}