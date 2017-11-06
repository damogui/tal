package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjpatent")
public class YjPatentController {
//
//    @Autowired
//    private YjPatentService yjPatentService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjPatent yjPatent) {
//        ResponseData data = new ResponseData();
//        yjPatentService.insert(yjPatent);
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
//        yjPatentService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjPatent yjPatent) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        yjPatent.setPkid(pkid);
//        yjPatentService.update(yjPatent);
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
//        //企业id(加密)
//        int companyId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("companyIdStr")));
//        if (companyId == 0) {
//            data.setCode(-1);
//            data.setMsg("companyIdStr参数错误");
//            return data;
//        }
//        Map<String, Object> paramsMap = new HashMap<>();
//        //企业id
//        if (companyId > 0) {
//            paramsMap.put("yj_company_id", companyId);
//        }
//        //类别
//        String type = StringUtils.trimToEmpty(request.getParameter("type"));
//        if (StringUtils.isNotBlank(type)) {
//            paramsMap.put("kind_code_desc", type);
//        }
//        Pager<YjPatent> pager = yjPatentService.pageByProperties(paramsMap, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /*根据id获取详情*/
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        YjPatent yjPatent = yjPatentService.findById(pkid);
//        data.setData(yjPatent);
//        return data;
//    }
//
//    /*获取专利类别*/
//    @RequestMapping("/getTypeList")
//    public ResponseData getTypeList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        List<String> typeList = yjPatentService.getTypeList();
//        data.setData(typeList);
//        return data;
//    }

}