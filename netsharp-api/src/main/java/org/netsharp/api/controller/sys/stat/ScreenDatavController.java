package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;

/**
 * Created by wk on 2017/3/6.
 */
@Path("/stat/screen/datav")
public class ScreenDatavController {
    private static Logger log = Logger.getLogger(ScreenDatavController.class);
//
//    @Autowired
//    private ScreenDatavService screenDatavService;
//
//    @Autowired
//    private ScreenDatavLogService screenDatavLogService;
//
//    @Autowired
//    private ScreenDatavReferDayService screenDatavReferDayService;
//
//    @Autowired
//    private ScreenDatavUserMeasuresDayService screenDatavUserMeasuresDayService;
//
//    @RequestMapping("/get")
//    public Object get(HttpServletRequest request, HttpServletResponse response) {
//        int dataId = NumberUtils.toInt(request.getParameter("pkid"));
//        if (dataId == 0) {
//            return null;
//        }
//        screenDatavService.saveBaiduNews("公司宝");
//        return screenDatavService.getObjectById(dataId);
//    }
//
//    @RequestMapping("/map")
//    public Object getDataVForPlatform(){
//        return screenDatavService.getDataVForPlatform();
//    }
//
//    @RequestMapping("/map/object")
//    public Object getScreenMap(HttpServletRequest request, HttpServletResponse response) {
//        Integer pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        Integer index = NumberUtils.toInt(request.getParameter("index"));
//
//        String content = screenDatavService.getObjectById(pkid);
//        if (StringUtils.isBlank(content)) {
//            return null;
//        }
//
//        Map<Integer, Object> list = JsonUtils.jsonToMap(content, Integer.class, Object.class);
//
//        return MapUtils.getObject(list,index,new HashMap<>());
//
//    }
//
//    @RequestMapping("/refer/day")
//    public Object getRefererDay(HttpServletRequest request, HttpServletResponse response) {
//        Integer year = NumberUtils.toInt(DateUtils.dateStr(new Date(), "yyyy"));
//        Integer month = NumberUtils.toInt(DateUtils.dateStr(new Date(), "MM"));
//        Integer day = NumberUtils.toInt(DateUtils.dateStr(new Date(), "dd"));
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("year", year);
//        params.put("month", month);
//        params.put("day", day);
//
//        List<ScreenDatavReferDay> list = screenDatavLogService.getReferDayStat(params);
//        if (CollectionUtils.isNotEmpty(list)) {
//            Map<String, Object> condition = new HashMap<>();
//            condition.put("year", list.get(0).getStatYear());
//            condition.put("month", list.get(0).getStatMonth());
//            condition.put("day", list.get(0).getStatDay());
//
//            screenDatavReferDayService.delByCondition(condition);
//
//            try {
//                screenDatavReferDayService.insertBatch(list);
//            } catch (Exception ex) {
//                return "has inserted";
//            }
//
//            return list;
//        }
//
//        return "fail";
//    }
//
//    @RequestMapping("/measures/day")
//    public Object getUserMeasuresDay(HttpServletResponse response, HttpServletRequest request) {
//        return screenDatavService.getDatavLog();
//    }
//
//    @RequestMapping("/clear/logs")
//    public Object getClearDatavLogs(HttpServletRequest request, HttpServletResponse response) {
//        return screenDatavLogService.delLog();
//    }
}
