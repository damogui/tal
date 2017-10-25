package org.netsharp.api.controller.order;


import javax.ws.rs.Path;


@Path("/soorderprodtracefile")
public class SoOrderProdTraceFileController {
//
//    @Autowired
//    private SoOrderProdTraceFileService soOrderProdTraceFileService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdTraceFile soOrderProdTraceFile) {
//        ResponseData data = new ResponseData();
//        soOrderProdTraceFileService.insert(soOrderProdTraceFile);
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
//        soOrderProdTraceFileService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdTraceFile soOrderProdTraceFile) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderProdTraceFile.setPkid(pkid);
//        soOrderProdTraceFileService.update(soOrderProdTraceFile);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (orderProdId > 0) {
//            condition.put("orderProdId", orderProdId);
//        }
//
//        Pager<SoOrderProdTraceFile> pager = soOrderProdTraceFileService.pageByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/download")
//    public ResponseData filesDownload(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderProdIdStr")));
//
//        Map<String, String> result = soOrderProdTraceFileService.exportZipFiles(orderProdId);
//
//        String folder = StringUtils.trimToEmpty(result.get("folder"));
//        String file = StringUtils.trimToEmpty(result.get("file"));
//        if (StringUtils.isNotBlank(file)) {
//            FileUtils.downLoacl(request, response, file, "订单" + orderProdId + "材料.zip");
//        }
//
//        if (StringUtils.isNotBlank(folder)) {
//            FileUtils.removeLocalDir(folder);
//        }
//        data.setCode(200);
//        return data;
//    }
//
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        SoOrderProdTraceFile soOrderProdTraceFile = soOrderProdTraceFileService.findById(pkid);
//        data.setData(soOrderProdTraceFile);
//        return data;
//    }
//
//    @RequestMapping({"/preview"})
//    public ResponseData preview(HttpServletRequest request, HttpServletResponse response) {
//        // 产品订单ID加密串
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        orderProdIdStr = SecurityUtils.rc4Decrypt(orderProdIdStr);
//        int orderProdId = NumberUtils.toInt(orderProdIdStr);
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (orderProdId > 0) {
//            condition.put("orderProdId", orderProdId);
//        }
//
//        Pager<SoOrderProdTraceFile> pager = soOrderProdTraceFileService.queryPreview(condition, 0, Integer.MAX_VALUE);
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/isTop")
//    public ResponseData updateEnabled(HttpServletRequest request, HttpServletResponse response) {
//        // ID加密字符串
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("pkIdStr"));
//        pkIdStr = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkid = NumberUtils.toInt(pkIdStr, -1);
//
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        int result = soOrderProdTraceFileService.updateIsTop(pkid);
//        if (result <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }

}