package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cms/banner")
public class BannerController extends CmsBaseController {
//
//    private static Logger log = Logger.getLogger(BannerController.class);
//
//    @Autowired
//    private CmsBannerService cmsBannerService;
//
//
//    /**
//     * 加载列表数据跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping({"/list/data"})
//    public ResponseData listData(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        long begin = System.currentTimeMillis();
//        ResponseData data = new ResponseData();
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("status", CMSBase.STATUS_SHOW);
//        List<CmsBanner> list = cmsBannerService.listByProperties(properties);
//        data.setData(list);
//        long end = System.currentTimeMillis();
//        log.info("query time = " + (end-begin));
//        return data;
//    }
//
//    /**
//     * 编辑数据跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        int pkid = getPkid(request);
//        CmsBanner cmsBanner = cmsBannerService.findById(pkid);
//        data.setData(cmsBanner);
//        return data;
//    }
//
//    /**
//     * 新增或修改（编辑）操作跳转
//     *
//     * @param request
//     * @param response
//     * @param cmsBanner
//     * @return ResponseData
//     */
//    @RequestMapping("/edit")
//    public ResponseData edit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsBanner cmsBanner, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//        if (pkid == 0) {
//            cmsBanner.setStatus(CMSBase.STATUS_INIT);
//            cmsBanner.setSort(0);
//            cmsBanner.setAddUser(user.getUcUser().getPkid());
//            cmsBanner.setUpdUser(user.getUcUser().getPkid());
//            cmsBannerService.insert(cmsBanner);
//        } else {
//            cmsBanner.setPkid(pkid);
//            cmsBannerService.update(cmsBanner);
//        }
//        data.setMsg("操作成功");
//        data.setData(pkid);
//        return data;
//    }
//
//    /**
//     * 删除操作跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//
//        cmsBannerService.delete(pkid, user.getUcUser().getPkid());
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 上移操作跳转
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/up")
//    public ResponseData up(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//
//        Boolean bool = cmsBannerService.editSort(pkid, Boolean.TRUE, user.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
//
//    /**
//     * 下移操作跳转
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/down")
//    public ResponseData down(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int pkid = getPkid(request);
//
//        Boolean bool = cmsBannerService.editSort(pkid, Boolean.FALSE, user.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }
//
//    /**
//     * 发布操作跳转
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/publish")
//    public ResponseData publish(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String ids = StringUtils.trimToEmpty(request.getParameter("ids"));
//        List<Integer> idList = new ArrayList<>();
//        String[] idArray = ids.split(",");
//        for (String idStr : idArray) {
//            int id = NumberUtils.toInt(SecurityUtils.rc4Decrypt(idStr));
//            if (id > 0) {
//                idList.add(id);
//            }
//        }
//        Boolean bool = cmsBannerService.editPublish(idList, loginUser.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }


}