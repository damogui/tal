package com.gongsibao.api.conroller.product;

import javax.ws.rs.Path;

@Path("/prodworkflownode")
public class ProdWorkflowNodeController {
//
//    @Autowired
//    private ProdWorkflowNodeService prodWorkflowNodeService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdWorkflowNode prodWorkflowNode) {
//        ResponseData data = new ResponseData();
//        //prodWorkflowNodeService.insert(prodWorkflowNode);
//
//        List<ProdWorkflowNode> itemList = new ArrayList<>();
//        for(int i=0;i<3;i++){
//            ProdWorkflowNode info = new ProdWorkflowNode();
//            info.setName("aa");
//            info.setSort(1.2);
//            info.setTypeId(i);
//            info.setWeekdayCount(i);
//            info.setWorkflowId(i);
//            itemList.add(info);
//        }
//
//        prodWorkflowNodeService.insertBatch(itemList);
//
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
//        prodWorkflowNodeService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdWorkflowNode prodWorkflowNode) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodWorkflowNode.setPkid(pkid);
//        prodWorkflowNodeService.update(prodWorkflowNode);
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
//        Pager<ProdWorkflowNode> pager = prodWorkflowNodeService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdWorkflowNode prodWorkflowNode = prodWorkflowNodeService.findById(pkid);
//        data.setData(prodWorkflowNode);
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
//        List<ProdWorkflowNode> list = prodWorkflowNodeService.queryWorkflowNodeListByOrderProdId(orderProdId);
//        ResponseData data = new ResponseData();
//        data.setData(list);
//        return data;
//    }

}