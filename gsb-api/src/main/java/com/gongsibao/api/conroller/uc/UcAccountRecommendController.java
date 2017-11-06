package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucaccount/recommend")
public class UcAccountRecommendController {
//
//    @Autowired
//    private UcAccountRecommendService ucAccountRecommendService;
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        UcAccountRecommend recommend = JsonUtils.jsonToObject(json, UcAccountRecommend.class);
//        if (null == recommend) {
//            data.setCode(-1);
//            data.setMsg("参数错误 [" + json + "]");
//            return data;
//        }
//        int pkid = ucAccountRecommendService.save(recommend);
//        data.setMsg("操作成功");
//        data.setData(SecurityUtils.rc4Encrypt(pkid));
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        ucAccountRecommendService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        Map<String, Object> condition = new HashMap<>();
//        int pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        if (pkid > 0) {
//            condition.put("pkid", pkid);
//        }
//
//        String title = StringUtils.trimToEmpty(request.getParameter("title"));
//        if (StringUtils.isNotBlank(title)) {
//            condition.put("title", title);
//        }
//
//        Pager<UcAccountRecommend> pager = ucAccountRecommendService.pageByProperties(condition, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        UcAccountRecommend ucAccountRecommend = ucAccountRecommendService.findById(pkid);
//        data.setData(ucAccountRecommend);
//        return data;
//    }
//
//    @RequestMapping("/editSort")
//    public ResponseData editSort(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        int sort = NumberUtils.toInt(request.getParameter("sort"));
//        ucAccountRecommendService.updateSort(pkid, sort);
//        return data;
//    }
}