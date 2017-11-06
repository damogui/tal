package com.gongsibao.api.conroller.sys;

import javax.ws.rs.Path;

/**
 * Created by wk on 2017/8/22.
 */

@Path("/birthday")
public class BirthdayController {
//    @Autowired
//    UcUserBirthdayService ucUserBirthdayService;
//
//    @RequestMapping("/list")
//    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
//        return new ModelAndView("birthday/list");
//    }
//
//    @RequestMapping("/list/data")
//    public ResponseData listData(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        //每页显示的行数
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 10);
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        String birthday = StringUtils.trimToEmpty(request.getParameter("birthday"));
//        String email = StringUtils.trimToEmpty(request.getParameter("email"));
//
//        Map<String, Object> properties = new HashMap<>();
//        if (StringUtils.isNotBlank(name)) {
//            properties.put("name", name);
//        }
//        if (StringUtils.isNotBlank(birthday)) {
//            properties.put("birdthday", birthday);
//        }
//        if (StringUtils.isNotBlank(email)) {
//            properties.put("email", email);
//        }
//
//        PagerJsp<UcUserBirthday> pager = ucUserBirthdayService.findByCondition(properties, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/save")
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        String birthday = StringUtils.trimToEmpty(request.getParameter("birthday"));
//        String email = StringUtils.trimToEmpty(request.getParameter("email"));
//
//        if (RegexUtils.isNotEmail(email)) {
//            data.setMsg("email格式不正确");
//            return data;
//        }
//        UcUserBirthday ucUserBirthday = new UcUserBirthday();
//        ucUserBirthday.setPkid(pkid);
//        ucUserBirthday.setUserId(0);
//        ucUserBirthday.setName(name);
//        ucUserBirthday.setBirdthday(birthday);
//        ucUserBirthday.setEmail(email);
//        ucUserBirthdayService.save(ucUserBirthday);
//        data.setCode(200);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        data.setData(ucUserBirthdayService.findById(pkid));
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        ucUserBirthdayService.delete(pkid);
//        return data;
//    }
}
