package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjcompanyplayer")
public class YjCompanyPlayerController {
//
//    @Autowired
//    private YjCompanyPlayerService yjCompanyPlayerService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyPlayer yjCompanyPlayer) {
//        ResponseData data = new ResponseData();
//        yjCompanyPlayerService.insert(yjCompanyPlayer);
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
//        Pager<YjCompanyPlayer> pager = yjCompanyPlayerService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyPlayer yjCompanyPlayer = yjCompanyPlayerService.findById(pkid);
//        data.setData(yjCompanyPlayer);
//        return data;
//    }

}