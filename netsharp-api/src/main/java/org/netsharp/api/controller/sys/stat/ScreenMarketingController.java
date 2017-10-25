package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

/**
 * Created by oupeng on 17/3/30.
 */
@Path("/stat/screen/market")
public class ScreenMarketingController {
//
//    @Autowired
//    private ScreenDatavService screenDatavService;
//
//    @Autowired
//    private CrmCustomerService crmCustomerService;
//
//    /**
//     * 今日流量来源 pv uv
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/data")
//    public Object marketDataStat(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getUvPvData();
//    }
//
//    /**
//     * 今日热门产品
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/fantastic/product/day")
//    public Object fantasticProductForDay(HttpServletRequest request, HttpServletResponse response){
//        return screenDatavService.getFantasticProductForDay();
//    }
//
//    /**
//     * 本月热门产品
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/fantastic/product/month")
//    public Object fantasticProductForMonth(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getFantasticProductForMonth();
//    }
//
//    /**
//     * 咨询量和订单转化率   归属事业部列表
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/validity/rate/stat/business")
//    public Object validCustomerAndOrderRateStatBusiness(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getValidCustomerAndOrderRateStatForBusiness();
//    }
//
//    /**
//     * 咨询量和订单转化率   分公司列表
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/validity/rate/stat/office")
//    public Object validCustomerAndOrderRateStatOffice(HttpServletRequest request,HttpServletResponse response){
//        return screenDatavService.getValidCustomerAndOrderRateStatForOffice();
//    }
//
//    @RequestMapping("/news")
//    public Object getNewsFromWebsite(HttpServletRequest request, HttpServletResponse response) {
//        String keyword = StringUtils.trimToEmpty(request.getParameter("keywd"));
//
//        return screenDatavService.getNewsFromWebSite(keyword);
//    }
//
//    /**
//     * KPI
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/month/kpi")
//    public Object getMonthKPI(HttpServletRequest request, HttpServletResponse response) {
//        return crmCustomerService.statMonthKPI();
//    }
//
//    /**
//     * 获取同行业新闻
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/other/news")
//    public Object getOtherNews(HttpServletRequest request, HttpServletResponse response) {
//        String keywds = StringUtils.trimToEmpty(request.getParameter("keywds"));
//
//        List<String> list = new ArrayList<>();
//        if (StringUtils.isNotBlank(keywds)) {
//            String[] str_ = keywds.split(",");
//            list = Arrays.asList(str_);
//        }
//
//        return screenDatavService.getOtherNews(list);
//
//    }
//
//    @RequestMapping("/compare")
//    public Object getCountByAccountTypeAndTime(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getCountByAccountTypeAndTime();
//    }
//
//    @RequestMapping("/news/add")
//    public Object addBaiduNewsTopic(HttpServletRequest request, HttpServletResponse response) {
//        String topic = screenDatavService.getObjectById(35);
//
//        Map<Integer, List<Map<String, String>>> map = new HashMap<>();
//        Integer res = 0;
//        if (StringUtils.isNotBlank(topic)) {
//            List<String> list = Arrays.asList(topic.split(","));
//
//
//            if (CollectionUtils.isNotEmpty(list)) {
//                int rank = 1;
//                for(String str : list) {
//                    List<Map<String, String>> news = screenDatavService.getNewsFromWebSite(str);
//
//                    map.put(rank, news);
//                    rank++;
//                }
//            }
//
//            String json = JsonUtils.objectToJson(map);
//
//            ScreenDatav datav = new ScreenDatav();
//            datav.setPkid(36);
//            datav.setScreenId(6);
//            datav.setContent(json);
//            datav.setUpdTime(new Date());
//            datav.setDesc("舆情监控 所有新闻汇总");
//
//            ScreenDatav datav1 = screenDatavService.findById(datav.getPkid());
//            if (datav1 != null) {
//                return screenDatavService.updateByPkid(datav);
//            } else {
//                return screenDatavService.insert(datav);
//            }
//        }
//
//        return res;
//    }

}
