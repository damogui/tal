package org.netsharp.api.controller.order.supply;

import javax.ws.rs.Path;

/**
 * Created by duan on 07-04.
 */

@Path("/api/supply/invoice")
public class SupplyInvoiceController {
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private SupplyInvoiceService supplyInvoiceService;
//
//    /***
//     * 供应商发票列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData orderList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
//        //List<Integer> ids = ucUserService.getUserPkid(userId);
//        List<Integer> ids = new ArrayList<>();
//        ids.add(userId);
//        map.put("ids", ids);
//        setupParameters(map, request);
//        Pager<SupplyInvoice> pager = supplyInvoiceService.pageSupplyInvoiceByProperties(map, Integer.valueOf(page), pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    /***
//     * 更改发票投递状态
//     */
//    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
//    public ResponseData changeInvoiceState(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser
//            ,@RequestParam("orderProdIdStr") String orderProdIdStr){
//        ResponseData data = new ResponseData();
//        Integer orderProdId = DecryptPkIdStr(orderProdIdStr);
//        supplyInvoiceService.updateInvoiceState(orderProdId);
//        data.setMsg("更改成功");
//        return data;
//    }
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        //客户手机
//        ParameterUtils.addParameter(request, "customerPhone", String.class, map);
//        //1、待开发票 2、已开发票 3、已投递
//        ParameterUtils.addParameter(request, "type", Integer.class, map);
//    }
//
//    /**
//     * 解析 pkIdStr 2 pkId
//     *
//     * @param pkIdStr
//     * @return
//     */
//    private Integer DecryptPkIdStr(String pkIdStr) {
//        String str = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkid = NumberUtils.toInt(str);
//        return pkid;
//    }
}
