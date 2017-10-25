package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/prodworkflowbddictmap")
public class ProdWorkflowBdDictMapController {
//
//    @Autowired
//    private ProdWorkflowBdDictMapService prodWorkflowBdDictMapService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdWorkflowBdDictMap prodWorkflowBdDictMap) {
//        ResponseData data = new ResponseData();
//        prodWorkflowBdDictMapService.insert(prodWorkflowBdDictMap);
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
//        prodWorkflowBdDictMapService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdWorkflowBdDictMap prodWorkflowBdDictMap) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodWorkflowBdDictMap.setPkid(pkid);
//        prodWorkflowBdDictMapService.update(prodWorkflowBdDictMap);
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
//        Pager<ProdWorkflowBdDictMap> pager = prodWorkflowBdDictMapService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdWorkflowBdDictMap prodWorkflowBdDictMap = prodWorkflowBdDictMapService.findById(pkid);
//        data.setData(prodWorkflowBdDictMap);
//        return data;
//    }
//
//    @RequestMapping("/getRegion")
//    public ResponseData getRegion(HttpServletRequest request, HttpServletResponse response) {
//
//        String prodIdStr = StringUtils.trimToEmpty(request.getParameter("prodIdStr"));
//        prodIdStr = SecurityUtils.rc4Decrypt(prodIdStr);
//        Integer prodId = NumberUtils.toInt(prodIdStr, -1);
//
//        ResponseData data = new ResponseData();
//        List<ProdWorkflowBdDictList> result = prodWorkflowBdDictMapService.getRegionsByProdId(prodId);
//        data.setData(result);
//        return data;
//    }

}