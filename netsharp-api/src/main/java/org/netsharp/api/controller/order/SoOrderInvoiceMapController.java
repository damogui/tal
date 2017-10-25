package org.netsharp.api.controller.order;


import javax.ws.rs.Path;


@Path("/soorderinvoicemap")
public class SoOrderInvoiceMapController {
//
//    @Autowired
//    private SoOrderInvoiceMapService soOrderInvoiceMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderInvoiceMap soOrderInvoiceMap) {
//        ResponseData data = new ResponseData();
//        soOrderInvoiceMapService.insert(soOrderInvoiceMap);
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
//        soOrderInvoiceMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderInvoiceMap soOrderInvoiceMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderInvoiceMap.setPkid(pkid);
//        soOrderInvoiceMapService.update(soOrderInvoiceMap);
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
//        Pager<SoOrderInvoiceMap> pager = soOrderInvoiceMapService.pageByProperties(null, Integer.valueOf(page));
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
//        SoOrderInvoiceMap soOrderInvoiceMap = soOrderInvoiceMapService.findById(pkid);
//        data.setData(soOrderInvoiceMap);
//        return data;
//    }

}