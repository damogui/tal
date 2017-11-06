package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyvalidity")
public class YjCompanyValidityController {
//
//    @Autowired
//    private YjCompanyValidityService yjCompanyValidityService;
//
//    @RequestMapping(value = "/save")
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        YjCompanyValidity validity = JsonUtils.jsonToObject(json, YjCompanyValidity.class);
//        yjCompanyValidityService.save(validity);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        yjCompanyValidityService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"), 1);
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Pager<YjCompanyValidity> pager = yjCompanyValidityService.findByCondition(new HashMap<>(), currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        data.setData(yjCompanyValidityService.findById(pkid));
//        return data;
//    }

}