package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cms/titledesc")
public class CmsTitleDescController extends CmsBaseController {
//
//    @Autowired
//    private CmsTitleDescService cmsTitleDescService;
//
//    @RequestMapping("/edit")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsTitleDesc cmsTitleDesc, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//
//        cmsTitleDesc.setUpdUser(user.getUcUser().getPkid());
//        cmsTitleDesc.setAddUser(user.getUcUser().getPkid());
//        if (pkid == 0) {
//            cmsTitleDescService.insert(cmsTitleDesc);
//        } else {
//            cmsTitleDesc.setPkid(pkid);
//            cmsTitleDescService.update(cmsTitleDesc);
//        }
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        CmsTitleDesc cmsTitleDesc = cmsTitleDescService.get();
//        data.setData(cmsTitleDesc);
//        return data;
//    }
}