package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjtrademark")
public class YjTrademarkController {
//
//    @Autowired
//    private YjTrademarkService yjTrademarkService;
//
//    //region 系统生成
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjTrademark yjTrademark) {
//        ResponseData data = new ResponseData();
//        yjTrademarkService.insert(yjTrademark);
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
//        yjTrademarkService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjTrademark yjTrademark) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        yjTrademark.setPkid(pkid);
//        yjTrademarkService.update(yjTrademark);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Map<String, Object> paramsMap = new HashMap<>();
//        //企业id(加密)
//        int companyId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("companyIdStr")));
//        if (companyId == 0) {
//            data.setCode(-1);
//            data.setMsg("companyIdStr参数错误");
//            return data;
//        }
//        //状态
//        String status = StringUtils.trimToEmpty(request.getParameter("status"));
//        //类别
//        String category = StringUtils.trimToEmpty(request.getParameter("category"));
//        //企业id
//        if (companyId > 0) {
//            paramsMap.put("yj_company_id", companyId);
//        }
//        if (StringUtils.isNotBlank(status)) {
//            paramsMap.put("flow_status", status);
//        }
//        if (StringUtils.isNotBlank(category)) {
//            paramsMap.put("int_cls", category);
//        }
//        Pager<YjTrademark> pager = yjTrademarkService.pageByProperties(paramsMap, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /*根据id获取商标详情*/
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//
//        YjTrademark yjTrademark = yjTrademarkService.findById(pkid);
//        data.setData(yjTrademark);
//        return data;
//    }
//    // endregion
//
//    /*获取商标类别*/
//    @RequestMapping("/getCategoryList")
//    public ResponseData getCategoryList(HttpServletResponse response, HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        List<Map<String, Object>> categoryList = yjTrademarkService.getCategoryList();
//        data.setData(categoryList);
//        return data;
//    }
//
//    /*获取商标状态*/
//    @RequestMapping("/getStatusList")
//    public ResponseData getStatusList(HttpServletResponse response, HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        List<Map<String, Object>> StatusList = yjTrademarkService.getStatusList();
//        data.setData(StatusList);
//        return data;
//    }


}