package com.gongsibao.api.conroller.sys;

import javax.ws.rs.Path;

/**
 * Created by wk on 2016/3/28.
 */
@Path("/oss")
public class OSSController {
//
//    @Autowired
//    BdFileService bdFileService;
//
//    /**
//     * 私有图片测试类
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/test")
//    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mv = new ModelAndView("oss_test");
//        mv.addObject("img", request.getContextPath() + "/oss/img/?p=" + Encodes.urlEncode("/test/pic3.png"));
//        mv.addObject("randomKey", RandomStringUtils.randomAlphanumeric(32));
//        return mv;
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param file
//     */
//    @RequestMapping("/upload")
//    public ResponseData getpic(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        ResponseData responseData = new ResponseData();
//        Map<String, Object> map = new HashMap<String, Object>();
//        responseData.setData(map);
//
//        String folder = StringUtils.trimToEmpty(request.getParameter("folder"));
//        String fileName = StringUtils.trimToEmpty(request.getParameter("fileName"));
//        try {
//            String url = null;
//            url = OSSFileUtils.uploadFileWithName(folder, file, fileName);
//
//            map.put(OSSFileUtils.FILE_RES_URL, url);
//            map.put(OSSFileUtils.FILE_RES_NAME, StringUtils.isBlank(fileName) ? file.getOriginalFilename() : fileName);
//            map.put("isImg", OSSFileUtils.isImg(url));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return responseData;
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param file
//     */
//    @RequestMapping("/uploadAndSave")
//    public ResponseData getpic(@RequestParam("file") MultipartFile file, HttpServletRequest request, LoginUser user) {
//
//        ResponseData responseData = new ResponseData();
//
//        String tabName = StringUtils.trimToEmpty(request.getParameter("tabName"));
//        String folder = StringUtils.trimToEmpty(request.getParameter("folder"));
//        int formId = NumberUtils.toInt(request.getParameter("formId"));
//        try {
//            String url = OSSFileUtils.uploadFile(folder, file);
//            Map<String, Object> map = new HashMap<String, Object>();
//            if (StringUtils.isNotBlank(url)) {
//                Integer pkid = bdFileService.insert(new BdFile() {{
//                    setFormId(0);
//                    setTabName(tabName);
//                    setName(StringUtils.isBlank(file.getOriginalFilename()) ? "附件" : file.getOriginalFilename());
//                    setUrl(url);
//                    setFormId(formId);
//                    setAddUserId(user.getUcUser().getPkid());
//                }});
//                map.put("pkidStr", SecurityUtils.rc4Encrypt(pkid));
//            }
//
//            map.put(OSSFileUtils.FILE_RES_URL, url);
//            map.put(OSSFileUtils.FILE_RES_NAME, file.getOriginalFilename());
//            map.put("isImg", OSSFileUtils.isImg(url));
//            responseData.setData(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return responseData;
//    }


}