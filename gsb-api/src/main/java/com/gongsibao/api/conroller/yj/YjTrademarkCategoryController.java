package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjtrademarkcategory")
public class YjTrademarkCategoryController {
//
//    @Autowired
//    private YjTrademarkCategoryService yjTrademarkCategoryService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjTrademarkCategory yjTrademarkCategory) {
//        ResponseData data = new ResponseData();
//        yjTrademarkCategoryService.insert(yjTrademarkCategory);
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
//        yjTrademarkCategoryService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjTrademarkCategory yjTrademarkCategory) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        yjTrademarkCategory.setPkid(pkid);
//        yjTrademarkCategoryService.update(yjTrademarkCategory);
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
//        Pager<YjTrademarkCategory> pager = yjTrademarkCategoryService.pageByProperties(null, Integer.valueOf(page));
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
//        YjTrademarkCategory yjTrademarkCategory = yjTrademarkCategoryService.findById(pkid);
//        data.setData(yjTrademarkCategory);
//        return data;
//    }

}