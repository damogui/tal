package org.netsharp.api.controller.order;


import javax.ws.rs.Path;


@Path("/soorderprodorganizationmap")
public class SoOrderProdOrganizationMapController {
//
//    @Autowired
//    private SoOrderProdOrganizationMapService soOrderProdOrganizationMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdOrganizationMap soOrderProdOrganizationMap) {
//        ResponseData data = new ResponseData();
//        soOrderProdOrganizationMapService.insert(soOrderProdOrganizationMap);
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
//        soOrderProdOrganizationMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdOrganizationMap soOrderProdOrganizationMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderProdOrganizationMap.setPkid(pkid);
//        soOrderProdOrganizationMapService.update(soOrderProdOrganizationMap);
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
//        Pager<SoOrderProdOrganizationMap> pager = soOrderProdOrganizationMapService.pageByProperties(null, Integer.valueOf(page));
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
//        SoOrderProdOrganizationMap soOrderProdOrganizationMap = soOrderProdOrganizationMapService.findById(pkid);
//        data.setData(soOrderProdOrganizationMap);
//        return data;
//    }

}