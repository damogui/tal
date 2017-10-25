package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

@Path("/cms/hotkeywrod")
public class CmsHotKeywrodController {
//
//    @Autowired
//    private CmsHotKeywrodService cmsHotKeywrodService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsHotKeywrod cmsHotKeywrod) {
//        ResponseData data = new ResponseData();
//        cmsHotKeywrodService.insert(cmsHotKeywrod);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> mapParam) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(mapParam.get("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("pkidStr参数错误");
//            return data;
//        }
//        cmsHotKeywrodService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsHotKeywrod cmsHotKeywrod) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        cmsHotKeywrod.setPkid(pkid);
//        cmsHotKeywrodService.update(cmsHotKeywrod);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/save")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonParam, LoginUser user) {
//        ResponseData data = new ResponseData();
//        CmsHotKeywrod cmsHotKeywrod = JsonUtils.jsonToObject(jsonParam, CmsHotKeywrod.class);
//        cmsHotKeywrod.setAddUserId(user.getUcUser().getPkid());
//        List<CmsHotKeywrod> templist = cmsHotKeywrodService.pageByProperties(new HashMap() {{
//            put("name", cmsHotKeywrod.getName());
//        }}, 0, Integer.MAX_VALUE).getList();
//        if (cmsHotKeywrod.getPkid().equals(0)) {
//            //查询重复数据
//            if (!templist.isEmpty()) {
//                data.setCode(-1);
//                data.setMsg("该名称已经添加，禁止添加重复数据");
//                return data;
//            }
//            cmsHotKeywrod.setAddTime(new Date());
//            cmsHotKeywrodService.insert(cmsHotKeywrod);
//        } else {
//            if (!templist.isEmpty() && templist.stream().noneMatch(c -> c.getPkid().equals(cmsHotKeywrod.getPkid()))) {
//                data.setCode(-1);
//                data.setMsg("该名称已经添加，禁止添加重复数据");
//                return data;
//            }
//            cmsHotKeywrodService.update(cmsHotKeywrod);
//        }
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
//        Pager<CmsHotKeywrod> pager = cmsHotKeywrodService.pageByProperties(new HashMap<>(), Integer.valueOf(currentPage), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("参数错误");
//            return data;
//        }
//        CmsHotKeywrod cmsHotKeywrod = cmsHotKeywrodService.findById(pkid);
//        data.setData(cmsHotKeywrod);
//        return data;
//    }

}