package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjcopyright")
public class YjCopyrightController {
//
//    @Autowired
//    private YjCopyrightService yjCopyrightService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCopyright yjCopyright) {
//        ResponseData data = new ResponseData();
//        yjCopyrightService.insert(yjCopyright);
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
//        yjCopyrightService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCopyright yjCopyright) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        yjCopyright.setPkid(pkid);
//        yjCopyrightService.update(yjCopyright);
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
//        //类型：1作品著作权；2软件著作权
//        int type = NumberUtils.toInt(request.getParameter("type"));
//        if (type == 0) {
//            data.setCode(-1);
//            data.setMsg("type参数错误");
//            return data;
//        }
//        Map<String, Object> paramsMap = new HashMap<>();
//        //企业id
//        if (companyId > 0) {
//            paramsMap.put("yj_company_id", companyId);
//        }
//        //类型
//        if (type > 0) {
//            paramsMap.put("type", type);
//        }
//        Pager<YjCopyright> pager = yjCopyrightService.pageByProperties(paramsMap, currentPage, pageSize);
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
//        YjCopyright yjCopyright = yjCopyrightService.findById(pkid);
//        data.setData(yjCopyright);
//        return data;
//    }

}