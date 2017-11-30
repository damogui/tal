package com.gongsibao.api.conroller.product;


import javax.ws.rs.Path;

@Path("/api/prod/income")
public class ProdIncomeController {
//
//    @Autowired
//    private ProdIncomeService prodIncomeService;
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, @RequestBody String json, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        ProdIncome income = JsonUtils.jsonToObject(json, ProdIncome.class);
//        income.setAddUserId(loginUser.getUcUser().getPkid());
//        income.setIsEnabled(1);
//        int pkid = prodIncomeService.save(income);
//        data.setMsg("操作成功");
//        data.setData(SecurityUtils.rc4Encrypt(pkid));
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
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        int productId = NumberUtils.toInt(request.getParameter("productId"));
//        int cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("cityIdStr")));
//        int isEnabled = NumberUtils.toInt(request.getParameter("isEnabled"), -1);
//        int prodTypeId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("prodTypeId")));
//
//        Map<String, Object> condition = new HashMap<>();
//        if (StringUtils.isNotBlank(productName)) {
//            condition.put("productName", productName);
//        }
//
//        if (productId > 0) {
//            condition.put("productId", productId);
//        }
//
//        if (cityId > 0) {
//            condition.put("cityId", cityId);
//        }
//
//        if (isEnabled > -1) {
//            condition.put("isEnabled", isEnabled);
//        }
//        if (prodTypeId > 0) {
//            condition.put("prodTypeId", prodTypeId);
//        }
//
//        Pager<ProdIncome> pager = prodIncomeService.findByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//
//        ProdIncome prodIncome = prodIncomeService.findInfoById(pkid);
//        data.setData(prodIncome);
//        return data;
//    }
//
//    @RequestMapping("/toggle")
//    public ResponseData toggle(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        prodIncomeService.editIsEnabled(pkid);
//        return data;
//    }
}