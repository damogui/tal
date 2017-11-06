package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/prodpackagepricemap")
public class ProdPackagePriceMapController {
//
//    @Autowired
//    private ProdPackagePriceMapService prodPackagePriceMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPackagePriceMap prodPackagePriceMap) {
//        ResponseData data = new ResponseData();
//        prodPackagePriceMapService.insert(prodPackagePriceMap);
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
//        prodPackagePriceMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPackagePriceMap prodPackagePriceMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodPackagePriceMap.setPkid(pkid);
//        prodPackagePriceMapService.update(prodPackagePriceMap);
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
//        Pager<ProdPackagePriceMap> pager = prodPackagePriceMapService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdPackagePriceMap prodPackagePriceMap = prodPackagePriceMapService.findById(pkid);
//        data.setData(prodPackagePriceMap);
//        return data;
//    }

}