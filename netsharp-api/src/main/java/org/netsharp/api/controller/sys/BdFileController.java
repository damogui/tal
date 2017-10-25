package org.netsharp.api.controller.sys;


import javax.ws.rs.Path;

@Path("/bdfile")
public class BdFileController {
//
//    @Autowired
//    private BdFileService bdFileService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        BdFile bdFile = new BdFile();
//        bdFile.setFormId(1);
//        bdFile.setTabName("123");
//        bdFile.setAddUserId(1);
//        bdFile.setName("aaa");
//        bdFile.setUrl("url");
//        bdFileService.insert(bdFile);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping(value = "/addBatch")
//    public ResponseData addBatch(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        List<BdFile> list = new ArrayList<>();
//        for(int i = 0; i < 5; i++) {
//            BdFile bdFile = new BdFile();
//            bdFile.setFormId(2);
//            bdFile.setTabName("123");
//            bdFile.setAddUserId(1);
//            bdFile.setName("aaa" + i);
//            bdFile.setUrl("url" + i);
//            list.add(bdFile);
//        }
//        bdFileService.insertBatch(list);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        bdFileService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        List<BdFile> listByFormId = bdFileService.getListByFormId(1, "123");
//        System.out.println(listByFormId);
//
//        Map<Integer, List<BdFile>> mapByFormIds = bdFileService.getMapByFormIds(new ArrayList<Integer>() {{
//            add(1);
//            add(2);
//        }}, "123");
//
//        data.setData(mapByFormIds);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData getById(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        int pkid = NumberUtils.toInt(pkidStr);
//        BdFile file = bdFileService.findById(pkid);
//        data.setData(file);
//        return data;
//    }
}