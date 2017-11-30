package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/prodincomesettleorderprod")
public class ProdIncomeSettleOrderprodController {
//
//    @Autowired
//    private ProdIncomeSettleOrderprodService prodIncomeSettleOrderprodService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdIncomeSettleOrderprod prodIncomeSettleOrderprod) {
//        ResponseData data = new ResponseData();
//        prodIncomeSettleOrderprodService.insert(prodIncomeSettleOrderprod);
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
//        prodIncomeSettleOrderprodService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdIncomeSettleOrderprod prodIncomeSettleOrderprod) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodIncomeSettleOrderprod.setPkid(pkid);
//        prodIncomeSettleOrderprodService.update(prodIncomeSettleOrderprod);
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
//        Pager<ProdIncomeSettleOrderprod> pager = prodIncomeSettleOrderprodService.pageByProperties(null, Integer.valueOf(page));
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
//        ProdIncomeSettleOrderprod prodIncomeSettleOrderprod = prodIncomeSettleOrderprodService.findById(pkid);
//        data.setData(prodIncomeSettleOrderprod);
//        return data;
//    }

}