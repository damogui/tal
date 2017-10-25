package org.netsharp.api.controller.sys;


import javax.ws.rs.Path;

@Path("/bdexpresscourier")
public class BdExpressCourierController {
//
//    @Autowired
//    private BdExpressCourierService bdExpressCourierService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdExpressCourier bdExpressCourier) {
//        ResponseData data = new ResponseData();
//        bdExpressCourierService.insert(bdExpressCourier);
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
//        bdExpressCourierService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdExpressCourier bdExpressCourier) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        bdExpressCourier.setPkid(pkid);
//        bdExpressCourierService.update(bdExpressCourier);
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
//        Pager<BdExpressCourier> pager = bdExpressCourierService.pageByProperties(null, Integer.valueOf(page));
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
//        BdExpressCourier bdExpressCourier = bdExpressCourierService.findById(pkid);
//        data.setData(bdExpressCourier);
//        return data;
//    }
//
//    @RequestMapping("/getall")
//    public ResponseData getall(HttpServletRequest request, HttpServletResponse response) {
//        // 获取查询参数 是否热门 0:否1：是
//        String isHot = StringUtils.trimToEmpty(request.getParameter("isHot"));
//
//        // 封装查询条件MAP
//        Map<String, Object> properties = new HashMap<>();
//        if (NumberUtils.toInt(isHot, -1) >= 0) {
//            properties.put("isHot", isHot);
//        }
//
//        ResponseData data = new ResponseData();
//        List<BdExpressCourier> list = bdExpressCourierService.findAllBdExpressCourier(properties);
//        data.setData(list);
//        return data;
//    }
}