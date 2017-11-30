package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucrole")
public class UcRoleController {
/*
    @Autowired
    private UcRoleService ucRoleService;

    @RequestMapping(value = "/passList")
    public ResponseData add(LoginUser user) {
        ResponseData data = new ResponseData();
        List<UcRole> roleList = ucRoleService.findByUserPkid(user.getUcUser().getPkid(), 1);
        data.setData(roleList);
        return data;
    }

    @RequestMapping("/delete")
    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
        ResponseData data = new ResponseData();
        String pkidStr = request.getParameter("pkidStr");
        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
        Integer pkid = Integer.valueOf(pkidStr);
        ucRoleService.delete(pkid);
        data.setMsg("操作成功");
        return data;
    }

    @RequestMapping("/update")
    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UcRole ucRole) {
        ResponseData data = new ResponseData();
        String pkidStr = request.getParameter("pkidStr");
        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
        Integer pkid = Integer.valueOf(pkidStr);
        ucRole.setPkid(pkid);
        ucRoleService.update(ucRole);
        data.setMsg("操作成功");
        return data;
    }

    @RequestMapping({"/list"})
    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
        ResponseData data = new ResponseData();
        String page = request.getParameter("page");
        if (StringUtils.isBlank(page)) {
            page = "0";
        }
        Pager<UcRole> pager = ucRoleService.pageByProperties(null, Integer.valueOf(page));
        data.setData(pager);
        return data;
    }

    @RequestMapping("/get")
    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
        ResponseData data = new ResponseData();
        String pkidStr = request.getParameter("pkidStr");
        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
        Integer pkid = Integer.valueOf(pkidStr);
        UcRole ucRole = ucRoleService.findById(pkid);
        data.setData(ucRole);
        return data;
    }
*/
}