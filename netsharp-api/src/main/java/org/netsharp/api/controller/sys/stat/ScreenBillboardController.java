package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;

/**
 * Created by oupeng on 17/5/23.
 */
@Path("/stat/screen/billboard")
public class ScreenBillboardController {
    private static Logger log = Logger.getLogger(ScreenOrderController.class);
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    private ScreenDatavService screenDatavService;
//
//    @RequestMapping("/date")
//    public Object getBillboardInYear(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//
//        return ucUserService.billboardOfUser(begin, end,200);
//    }
//
//    @RequestMapping("/date/office")
//    public Object getBillboardInYearByOffice(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//
//        return ucUserService.getBillboardOfUserByOffice(begin, end,49304);
//    }
//
//    @RequestMapping("/1st")
//    public Object getBillboardPersonSingle(HttpServletRequest request, HttpServletResponse response) {
//        Integer rank = NumberUtils.toInt(request.getParameter("rank"));
//        Integer screen = NumberUtils.toInt(request.getParameter("screen"));
//
//        return screenDatavService.findByScreenIdAndRank(screen, rank);
//
//    }
//
//    @RequestMapping("/new")
//    public Object getNewestYWYInfomation(HttpServletRequest request, HttpServletResponse response) {
//        Integer screen = NumberUtils.toInt(request.getParameter("screen"));
//
//        return screenDatavService.getNewestYWYInfomation(screen);
//    }
//
//    @RequestMapping("/order/topic")
//    public Object getNewestOrderTopic(HttpServletRequest request, HttpServletResponse response) {
//        Integer num = NumberUtils.toInt(request.getParameter("num"));
//
//        return screenDatavService.getNewestOrderTopic(num);
//
//    }
//
//    @RequestMapping("/order/topic/read")
//    public Object getNewestOrderTopicByLimit(HttpServletRequest request, HttpServletResponse response) {
//        Integer num = NumberUtils.toInt(request.getParameter("limit"));
//        Integer screen = NumberUtils.toInt(request.getParameter("screen"));
//
//        return screenDatavService.getNewestOrderTopicByLimit(num, screen);
//    }
//
//    /**
//     * 直接读ScreenDatav数据
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/year")
//    public Object financeStat(HttpServletRequest request,HttpServletResponse response){
//        Integer pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        Integer limit = NumberUtils.toInt(request.getParameter("limit"),0);
//        if(pkid == 0){
//            return null;
//        }
//
//        List<StatBillboard> res = JsonUtils.jsonToList(screenDatavService.getObjectById(pkid),StatBillboard.class);
//
//        if (limit == 0 || limit > res.size()) {
//            return res;
//        }
//
//        return res.subList(0, limit);
//    }
}
