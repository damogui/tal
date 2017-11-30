package com.gongsibao.api.conroller.sys;


import javax.ws.rs.Path;

@Path("/bdsync")
public class BdSyncController {
//
//    @Autowired
//    private BdSyncService bdSyncService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdSync bdSync) {
//        ResponseData data = new ResponseData();
//        bdSyncService.insert(bdSync);
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
//        Pager<BdSync> pager = bdSyncService.pageByProperties(null, Integer.valueOf(page));
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
//        BdSync bdSync = bdSyncService.findById(pkid);
//        data.setData(bdSync);
//        return data;
//    }

}