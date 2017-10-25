package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

/**
 * Created by oupeng on 17/4/27.
 */
@Path("/stat/screen/businesscenter")
public class ScreenBusinessCenterController {
//    @Autowired
//    private ScreenDatavService screenDatavService;
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    /**
//     * 自营业务中心分析 分公司业绩 按时间段查询
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/branch/achievement")
//    public Object marketDataStat(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//
//        return screenDatavService.getBranchAchievementByPayTime(begin,end);
//    }
//
//    /**
//     * 自营业务中心分析 本月最佳业务员
//     * @param response
//     * @param request
//     * @return
//     */
//    @RequestMapping("/best/personal")
//    public Object findBestPersonal(HttpServletResponse response, HttpServletRequest request) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//        Integer limit = NumberUtils.toInt(request.getParameter("limit"));
//
//        return ucUserService.billboardOfUser(begin, end, limit);
//
//    }
//
//    /**
//     * 自营业务中心分析 本月最热卖商品
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/best/product")
//    public Object findBestProduct(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//        Integer limit = NumberUtils.toInt(request.getParameter("limit"));
//
//        return soOrderProdService.findBestProduct(begin, end, limit);
//
//    }
//
//    /**
//     * 新老客户业绩和订单量对比
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/customer/compare")
//    public Object customerCompareByTime(HttpServletRequest request, HttpServletResponse response) {
//
//        return screenDatavService.getCountByAccountTypeAndTime();
//    }
//
//    /**
//     * 大客户订单量
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/customer/order/count")
//    public Object findMostOrderByPayTime(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(new Date(), "yyyy") + "-01-01 00:00:00";
//        String end = DateUtils.dateStr(new Date(), "yyyy") + "-12-31 23:59:59";
//
//        return screenDatavService.getMostOrderByPayTime(begin, end, 10);
//    }
//
//    /**
//     * 大客户订单总额
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/customer/order/sum")
//    public Object getSumPayByPayTime(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(new Date(), "yyyy") + "-01-01 00:00:00";
//        String end = DateUtils.dateStr(new Date(), "yyyy") + "-12-31 23:59:59";
//
//        return screenDatavService.getSumPayByPayTime(begin, end, 10);
//    }
//
//    /**
//     * 客户咨询列表
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/customer/consult")
//    public Object getCustomerConsultList(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getCustomerConsultList();
//    }
//
//    /**
//     * 客户本年咨询到下单的平均时间
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/average/time")
//    public Object getAverageConsultTime(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getAverageConsultTime();
//    }
//
//    /**
//     * 获取业务线月累计咨询
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/business/line")
//    public Object getBusinessLineConsult(HttpServletRequest request, HttpServletResponse response) {
//        String monthBegin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String monthEnd = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//
//        return screenDatavService.getBusinessLineConsult(monthBegin,monthEnd);
//    }
//
//    /**
//     * 本月累计客户量 排名
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/customer/stat")
//    public Object getCustomerSortByCondition(HttpServletRequest request, HttpServletResponse response) {
//        String monthBegin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String monthEnd = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("beginTime",monthBegin);
//        condition.put("endTime", monthEnd);
//        condition.put("groupColumn", "follow_user_id");
//        condition.put("limit", 10);
//
//        return screenDatavService.getCustomerSortByCondition(condition);
//    }
//
//    /**
//     * 未跟进客户量 排名
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/customer/unfollow/stat")
//    public Object getUnfollowCustomerSorByCondition(HttpServletRequest request, HttpServletResponse response) {
//        String monthBegin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String monthEnd = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("beginTime",monthBegin);
//        condition.put("endTime", monthEnd);
//        condition.put("followStatus", 4011); //未跟进
//        condition.put("groupColumn", "follow_user_id");
//        condition.put("limit", 10);
//
//        return screenDatavService.getCustomerSortByCondition(condition);
//    }
//
//    /**
//     * 未跟进客户占比
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/unfollow/ratio")
//    public Object getUnfollowOfRatioInAll(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(new Date(), "yyyy") + "-01-01 00:00:00";
//        String end = DateUtils.dateStr(new Date(), "yyyy") + "-12-31 23:59:59";
//
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("beginTime",begin);
//        condition.put("endTime", end);
//
//        return screenDatavService.getUnfollowOfRatioInAll(condition);
//
//    }
//
//    /**
//     * 老客户平均复购次数
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/repetition")
//    public Object getRepeatedPayStat(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavService.getRepeatedPayCount();
//    }
}
