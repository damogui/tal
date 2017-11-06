package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;


@Path("/soorderprodaccount")
public class SoOrderProdAccountController {
//
//    @Autowired
//    private SoOrderProdAccountService soOrderProdAccountService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdAccount soOrderProdAccount) {
//        ResponseData data = new ResponseData();
//        soOrderProdAccountService.insert(soOrderProdAccount);
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
//        soOrderProdAccountService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SoOrderProdAccount soOrderProdAccount) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        soOrderProdAccount.setPkid(pkid);
//        soOrderProdAccountService.update(soOrderProdAccount);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "1";
//        }
//        String pagesize = request.getParameter("pagesize");
//        if (StringUtils.isBlank(pagesize)) {
//            page = "10";
//        }
//        Pager<SoOrderProdAccount> pager = soOrderProdAccountService.pageByProperties(null, Integer.valueOf(page), Integer.valueOf(pagesize));
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
//        SoOrderProdAccount soOrderProdAccount = soOrderProdAccountService.findById(pkid);
//        data.setData(soOrderProdAccount);
//        return data;
//    }

}