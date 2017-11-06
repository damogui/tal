package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/prodincomeorganization")
public class ProdIncomeOrganizationController {
//
//    @Autowired
//    private ProdIncomeOrganizationService prodIncomeOrganizationService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdIncomeOrganization prodIncomeOrganization) {
//        ResponseData data = new ResponseData();
//        prodIncomeOrganizationService.insert(prodIncomeOrganization);
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
//        prodIncomeOrganizationService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdIncomeOrganization prodIncomeOrganization) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodIncomeOrganization.setPkid(pkid);
//        prodIncomeOrganizationService.update(prodIncomeOrganization);
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
//        Pager<ProdIncomeOrganization> pager = prodIncomeOrganizationService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdIncomeOrganization prodIncomeOrganization = prodIncomeOrganizationService.findById(pkid);
//        data.setData(prodIncomeOrganization);
//        return data;
//    }

}