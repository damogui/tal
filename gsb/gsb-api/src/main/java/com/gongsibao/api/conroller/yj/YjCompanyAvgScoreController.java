package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;


@Path("/yjcompanyavgscore")
public class YjCompanyAvgScoreController {
//
//    @Autowired
//    private YjCompanyAvgScoreService yjCompanyAvgScoreService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyAvgScore yjCompanyAvgScore) {
//        ResponseData data = new ResponseData();
//        yjCompanyAvgScoreService.insert(yjCompanyAvgScore);
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
//        yjCompanyAvgScoreService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjCompanyAvgScore yjCompanyAvgScore) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        yjCompanyAvgScore.setPkid(pkid);
//        yjCompanyAvgScoreService.update(yjCompanyAvgScore);
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
//        Pager<YjCompanyAvgScore> pager = yjCompanyAvgScoreService.pageByProperties(null, Integer.valueOf(page));
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
//        YjCompanyAvgScore yjCompanyAvgScore = yjCompanyAvgScoreService.findById(pkid);
//        data.setData(yjCompanyAvgScore);
//        return data;
//    }

}