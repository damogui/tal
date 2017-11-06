package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;

@Path("/socostinvoicemap")
public class SoCostInvoiceMapController {
//
//    @Autowired
//    private SoCostInvoiceMapService soCostInvoiceMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoCostInvoiceMap soCostInvoiceMap) {
//        ResponseData data = new ResponseData();
//        soCostInvoiceMapService.insert(soCostInvoiceMap);
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
//        soCostInvoiceMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoCostInvoiceMap soCostInvoiceMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soCostInvoiceMap.setPkid(pkid);
//        soCostInvoiceMapService.update(soCostInvoiceMap);
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
//        Pager<SoCostInvoiceMap> pager = soCostInvoiceMapService.pageByProperties(null, Integer.valueOf(page));
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
//        SoCostInvoiceMap soCostInvoiceMap = soCostInvoiceMapService.findById(pkid);
//        data.setData(soCostInvoiceMap);
//        return data;
//    }

}