package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/prodworkflowfile")
public class ProdWorkflowFileController {
//
//    @Autowired
//    private ProdWorkflowFileService prodWorkflowFileService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdWorkflowFile prodWorkflowFile) {
//        ResponseData data = new ResponseData();
//        prodWorkflowFileService.insert(prodWorkflowFile);
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
//        prodWorkflowFileService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdWorkflowFile prodWorkflowFile) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodWorkflowFile.setPkid(pkid);
//        prodWorkflowFileService.update(prodWorkflowFile);
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
//        Pager<ProdWorkflowFile> pager = prodWorkflowFileService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdWorkflowFile prodWorkflowFile = prodWorkflowFileService.findById(pkid);
//        data.setData(prodWorkflowFile);
//        return data;
//    }
//
//    @RequestMapping({"/selects"})
//    public ResponseData queryListByOrderProdId(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        List<ProdWorkflowFile> list = prodWorkflowFileService.queryWorkflowFileListByOrderProdId(orderProdId);
//        ResponseData data = new ResponseData();
//        data.setData(list);
//        return data;
//    }

}