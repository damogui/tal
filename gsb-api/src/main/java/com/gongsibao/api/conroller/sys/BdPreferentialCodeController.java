package com.gongsibao.api.conroller.sys;


import javax.ws.rs.Path;

@Path("/bdpreferentialcode")
public class BdPreferentialCodeController {
//
//    @Autowired
//    private BdPreferentialCodeService bdPreferentialCodeService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BdPreferentialCode bdPreferentialCode) {
//        ResponseData data = new ResponseData();
//        bdPreferentialCodeService.insert(bdPreferentialCode);
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
//        Pager<BdPreferentialCode> pager = bdPreferentialCodeService.pageByProperties(null, Integer.valueOf(page), 10);
//        Pager<BdPreferentialCode> pager1 = new Pager<>(0, 1);
//        List<BdPreferentialCode> dd = new ArrayList<>();
//        dd.add(new BdPreferentialCode());
//        pager1.setList(dd);
//        data.setData(pager1);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        BdPreferentialCode bdPreferentialCode = bdPreferentialCodeService.findById(pkid);
//        data.setData(bdPreferentialCode);
//        return data;
//    }
//
//    @RequestMapping("/getByPreferentialId")
//    public ResponseData getByPreferentialId(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        //解密
//        Integer prefertailId = Integer.valueOf(SecurityUtils.rc4Decrypt(request.getParameter("preferentialIdStr")));
//        if (prefertailId == 0) {
//            data.setCode(-1);
//            data.setMsg("preferentialIdStr参数错误");
//            return data;
//        }
//        //页码
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer.MAX_VALUE);
//        Pager<BdPreferentialCode> bdPreferentialCodeList = bdPreferentialCodeService.getByPreferentialId(prefertailId, currentPage, pageSize);
//        data.setData(bdPreferentialCodeList);
//        return data;
//    }
//
//    @RequestMapping("/exportByPreferentialId")
//    public ResponseData exportByPreferentialId(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        //解密
//        Integer prefertailId = Integer.valueOf(SecurityUtils.rc4Decrypt(request.getParameter("preferentialIdStr")));
//        if (prefertailId == 0) {
//            data.setCode(-1);
//            data.setMsg("preferentialIdStr参数错误");
//            return data;
//        }
//        //页码
//        String filePath = bdPreferentialCodeService.exportByPreferentialId(prefertailId, loginUser.getUcUser().getPkid());
//        FileUtils.downLoacl(request, response, filePath, "优惠码.csv");
//        FileUtils.removeLocal(new File(filePath));
//
//        return data;
//    }
//
//
//    @RequestMapping("/saveEnableDisableCode")
//    public ResponseData saveEnableDisableCode(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Map<String, Object>> paramMap) {
//        ResponseData data = new ResponseData();
//        if (paramMap.size() <= 0) {
//            data.setCode(-1);
//            data.setMsg("preferentialIdStr参数错误");
//            return data;
//        }
//        bdPreferentialCodeService.saveEnableDisableCode(paramMap);
//        data.setMsg("操作成功");
//        return data;
//    }


}