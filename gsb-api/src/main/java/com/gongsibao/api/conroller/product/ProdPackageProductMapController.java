package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/prodpackageproductmap")
public class ProdPackageProductMapController {
//
//    @Autowired
//    private ProdPackageProductMapService prodPackageProductMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPackageProductMap prodPackageProductMap) {
//        ResponseData data = new ResponseData();
//        prodPackageProductMapService.insert(prodPackageProductMap);
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
//        prodPackageProductMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPackageProductMap prodPackageProductMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodPackageProductMap.setPkid(pkid);
//        prodPackageProductMapService.update(prodPackageProductMap);
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
//        Pager<ProdPackageProductMap> pager = prodPackageProductMapService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdPackageProductMap prodPackageProductMap = prodPackageProductMapService.findById(pkid);
//        data.setData(prodPackageProductMap);
//        return data;
//    }

}