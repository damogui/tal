package org.netsharp.api.controller.uc;


import javax.ws.rs.Path;

@Path("/ucaccountdingtalkfile")
public class UcAccountDingtalkFileController {
//
//    @Autowired
//    private UcAccountDingtalkFileService ucAccountDingtalkFileService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountDingtalkFile ucAccountDingtalkFile) {
//        ResponseData data = new ResponseData();
//        ucAccountDingtalkFileService.insert(ucAccountDingtalkFile);
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
//        ucAccountDingtalkFileService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountDingtalkFile ucAccountDingtalkFile) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ucAccountDingtalkFile.setPkid(pkid);
//        ucAccountDingtalkFileService.update(ucAccountDingtalkFile);
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
//        Pager<UcAccountDingtalkFile> pager = ucAccountDingtalkFileService.pageByProperties(null, Integer.valueOf(page));
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
//        UcAccountDingtalkFile ucAccountDingtalkFile = ucAccountDingtalkFileService.findById(pkid);
//        data.setData(ucAccountDingtalkFile);
//        return data;
//    }

}