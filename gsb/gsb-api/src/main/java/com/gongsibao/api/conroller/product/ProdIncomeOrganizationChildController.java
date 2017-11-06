package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/prodincomeorganizationchild")
public class ProdIncomeOrganizationChildController {
//
//    @Autowired
//    private ProdIncomeOrganizationChildService prodIncomeOrganizationChildService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdIncomeOrganizationChild prodIncomeOrganizationChild) {
//        ResponseData data = new ResponseData();
//        prodIncomeOrganizationChildService.insert(prodIncomeOrganizationChild);
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
//        prodIncomeOrganizationChildService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdIncomeOrganizationChild prodIncomeOrganizationChild) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodIncomeOrganizationChild.setPkid(pkid);
//        prodIncomeOrganizationChildService.update(prodIncomeOrganizationChild);
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
//        Pager<ProdIncomeOrganizationChild> pager = prodIncomeOrganizationChildService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdIncomeOrganizationChild prodIncomeOrganizationChild = prodIncomeOrganizationChildService.findById(pkid);
//        data.setData(prodIncomeOrganizationChild);
//        return data;
//    }

}