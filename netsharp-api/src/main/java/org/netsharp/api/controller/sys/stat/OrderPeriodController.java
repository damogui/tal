package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

/**
 * Created by wk on 2016/3/28.
 */
@Path("/stat/order")
public class OrderPeriodController {
//
//    @Autowired
//    SoOrderService soOrderService;
//
//    /**
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/period")
//    public ResponseData period(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String begin = null;
//        String end = null;
//
//        String beginStr = StringUtils.trimToEmpty(request.getParameter("begin"));
//        String endStr = StringUtils.trimToEmpty(request.getParameter("end"));
//
//        if (StringUtils.isNotBlank(beginStr)) {
//            begin = beginStr + " 00:00:00";
//        } else {
//            begin = DateUtils.dateStr(DateUtils.addDays(new Date(), -64), "yyyy-MM-dd") + " 00:00:00";
//        }
//
//        if (StringUtils.isNotBlank(endStr)) {
//            end = endStr + " 23:59:59";
//        }
//
//        data.setData(soOrderService.findPeriodStat(StringUtils.trimToEmpty(begin), StringUtils.trimToEmpty(end)));
//        return data;
//    }
//
//    @RequestMapping("/period/export")
//    public ResponseData exportPeriod(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//
//        String begin = null;
//        String end = null;
//
//        String beginStr = StringUtils.trimToEmpty(request.getParameter("begin"));
//        String endStr = StringUtils.trimToEmpty(request.getParameter("end"));
//
//        if (StringUtils.isNotBlank(beginStr)) {
//            begin = beginStr + " 00:00:00";
//        } else {
//            begin = DateUtils.dateStr(DateUtils.addDays(new Date(), -64), "yyyy-MM-dd") + " 00:00:00";
//        }
//
//        if (StringUtils.isNotBlank(endStr)) {
//            end = endStr + " 23:59:59";
//        }
//
//        String filePath = soOrderService.exportPeriodStat(StringUtils.trimToEmpty(begin), StringUtils.trimToEmpty(end));
//        FileUtils.downLoacl(request, response, filePath, "成单周期.csv");
//        FileUtils.removeLocal(new File(filePath));
//
//        return data;
//    }
//
//
//    public static void main(String[] args) {
//        System.out.println(DateUtils.dateStr(DateUtils.addDays(new Date(), -64), "yyyy-MM-dd") + " 00:00:00");
//    }
}