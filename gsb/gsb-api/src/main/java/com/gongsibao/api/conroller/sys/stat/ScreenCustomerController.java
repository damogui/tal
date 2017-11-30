package com.gongsibao.api.conroller.sys.stat;

import javax.ws.rs.Path;

/**
 * Created by wk on 2017/3/6.
 */
@Path("/stat/screen/customer")
public class ScreenCustomerController {
//
//    @Autowired
//    CrmCustomerService crmCustomerService;
//
//    @RequestMapping("/province/day")
//    public Object provinceDay(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 23:59:59";
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        return crmCustomerService.statCustomer(begin, end, pageSize);
//    }
//    @RequestMapping("/province/stat")
//    public Object provinceStat(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        return crmCustomerService.statCustomer(begin, end, pageSize);
//    }
//
//    @RequestMapping("stat/month/kpi")
//    public Object statMonthKPI(HttpServletRequest request,HttpServletResponse response){
//        return crmCustomerService.statMonthKPI();
//    }
//
//    @RequestMapping("/conversion/effective/rate/month")
//    public Object effectiveRateMonth(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("bili", crmCustomerService.statUvConversionRate(begin, end));
//        return Arrays.asList(rs);
//    }
//    @RequestMapping("/conversion/effective/rate/stat")
//    public Object effectiveRateStat(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("bili", crmCustomerService.statUvConversionRate(begin, end));
//        return Arrays.asList(rs);
//    }
//
//    @RequestMapping("/conversion/order/rate/month")
//    public Object rateMonth(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("bili", crmCustomerService.statOrderConversionRate(begin, end));
//        return Arrays.asList(rs);
//    }
//    @RequestMapping("/conversion/order/rate/stat")
//    public Object rateStat(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("bili", crmCustomerService.statOrderConversionRate(begin, end));
//        return Arrays.asList(rs);
//    }



}
