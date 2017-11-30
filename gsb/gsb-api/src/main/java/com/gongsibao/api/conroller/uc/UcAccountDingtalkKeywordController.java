package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucaccountdingtalkkeyword")
public class UcAccountDingtalkKeywordController {

//    @Autowired
//    private UcAccountDingtalkKeywordService ucAccountDingtalkKeywordService;
//    @Autowired
//    private UcAccountDingtalkService ucAccountDingtalkService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonParam) {
//        ResponseData data = new ResponseData();
//        UcAccountDingtalkKeyword ucAccountDingtalkKeyword = JsonUtils.jsonToObject(jsonParam, UcAccountDingtalkKeyword.class);
//        ucAccountDingtalkKeyword.setAddTime(new Date());
//        if (ucAccountDingtalkKeyword.getAccountId().equals(0)) {
//            data.setCode(-1);
//            data.setMsg("钉钉会员id不能为空");
//            return data;
//        }
//        if (StringUtils.isBlank(ucAccountDingtalkKeyword.getKeyword())) {
//            data.setCode(-1);
//            data.setMsg("关键字不能为空");
//            return data;
//        }
//        UcAccountDingtalk ucAccountDingtalk = ucAccountDingtalkService.findById(ucAccountDingtalkKeyword.getAccountId());
//        if (ucAccountDingtalk == null) {
//            data.setCode(-1);
//            data.setMsg("该用户不存在");
//            return data;
//        }
//        ucAccountDingtalkKeywordService.insert(ucAccountDingtalkKeyword);
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
//        ucAccountDingtalkKeywordService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcAccountDingtalkKeyword ucAccountDingtalkKeyword) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ucAccountDingtalkKeyword.setPkid(pkid);
//        ucAccountDingtalkKeywordService.update(ucAccountDingtalkKeyword);
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
//        Pager<UcAccountDingtalkKeyword> pager = ucAccountDingtalkKeywordService.pageByProperties(null, Integer.valueOf(page));
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
//        UcAccountDingtalkKeyword ucAccountDingtalkKeyword = ucAccountDingtalkKeywordService.findById(pkid);
//        data.setData(ucAccountDingtalkKeyword);
//        return data;
//    }


}