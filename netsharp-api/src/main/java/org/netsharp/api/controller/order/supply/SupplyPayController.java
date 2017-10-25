package org.netsharp.api.controller.order.supply;

import javax.ws.rs.Path;

/**
 * Created by Administrator on 2016/7/13.
 */

@Path("/api/supply/pay")
public class SupplyPayController {
//    @Autowired
//    private SupplyPayService supplyPayService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    ProdIncomeSettleService prodIncomeSettleService;
//
//    //打款订单记录列表
//    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
//    public ResponseData orderPayInfoList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("currentPage");
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
//        List<Integer> ids = ucUserService.getUserPkid(userId);
//        ids.add(userId);
//        map.put("ids", ids);
//        setupParameters(map, request);
//        Pager<SupplySettle> orderPayInfoPager = prodIncomeSettleService.findSupplySettleList(map, Integer.valueOf(page), pageSize);
//        data.setData(orderPayInfoPager);
//        return data;
//    }
//
//
//    /***
//     * 导出excel
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping({"/orderList/export"})
//    public ResponseData exportOrderPayInfoList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> map = new HashMap<>();
//        Integer userId = loginUser.getUcUser().getPkid();
////        List<Integer> ids = ucUserService.getUserPkid(userId);
//        List<Integer> ids = new ArrayList<>();
//        ids.add(userId);
//        map.put("ids", ids);
//        setupParameters(map, request);
//        String filePath = prodIncomeSettleService.exportSupplySettleList(map);
//        FileUtils.downLoacl(request, response, filePath, "全部订单.csv");
//        FileUtils.removeLocal(new File(filePath));
//        return data;
//    }
//
//    //装载参数
//    private void setupParameters(Map<String, Object> map, HttpServletRequest request) {
//        //产品ID
//        String productIdStr = request.getParameter("productIdStr");
//        if (StringUtils.isNoneBlank(productIdStr)) {
//            map.put("productId", DecryptPkIdStr(productIdStr));
//        }
//        //类型 1 待打款 2已打款
//        ParameterUtils.addParameter(request, "type", Integer.class, map);
//        //公司名称&订单号&客户姓名
//        ParameterUtils.addParameter(request, "info", String.class, map);
//        //开始
//        String beginTime = request.getParameter("beginTime");
//        if (!StringUtils.isBlank(beginTime)) {
//            map.put("beginTime", beginTime + " 00:00:00");
//        }
//        //结束
//        String endTime = request.getParameter("endTime");
//        if (!StringUtils.isBlank(endTime)) {
//            map.put("endTime", endTime + " 23:59:59");
//        }
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
