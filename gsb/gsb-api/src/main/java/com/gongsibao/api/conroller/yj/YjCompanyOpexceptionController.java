package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyopexception")
public class YjCompanyOpexceptionController {
//
//    @Autowired
//    private YjCompanyOpexceptionService yjCompanyOpexceptionService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyOpexception yjCompanyOpexception) {
//        ResponseData data = new ResponseData();
//        yjCompanyOpexceptionService.insert(yjCompanyOpexception);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        // id加密
//        String yjCompanyIdStr = StringUtils.trimToEmpty(request.getParameter("yjCompanyIdStr"));
//        yjCompanyIdStr = SecurityUtils.rc4Decrypt(yjCompanyIdStr);
//        Integer yjCompanyId = NumberUtils.toInt(yjCompanyIdStr);
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (yjCompanyId > 0) {
//            condition.put("yj_company_id", yjCompanyId);
//        }
//
//        Pager<YjCompanyOpexception> pager = yjCompanyOpexceptionService.pageByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
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
//        YjCompanyOpexception yjCompanyOpexception = yjCompanyOpexceptionService.findById(pkid);
//        data.setData(yjCompanyOpexception);
//        return data;
//    }

}