package org.netsharp.api.controller.uc;


import javax.ws.rs.Path;

@Path("/ertenant")
public class ErTenantController {
//
//    @Autowired
//    private ErTenantService erTenantService;
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        String domain = StringUtils.trimToEmpty(request.getParameter("domain"));
//        String managerName = StringUtils.trimToEmpty(request.getParameter("managerName"));
//        String managerMobile = StringUtils.trimToEmpty(request.getParameter("managerMobile"));
//
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("companyName", companyName);
//        condition.put("domain", domain);
//        condition.put("managerName", managerName);
//        condition.put("managerMobile", managerMobile);
//
//        Pager<UcTenant> pager = erTenantService.pageUcTenantByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        UcTenant ucTenant = erTenantService.findUcTenantById(pkid);
//        data.setData(ucTenant);
//        return data;
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ResponseData save(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> req, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(req.get("pkidStr")));
//
//        String companyName = StringUtils.trimToEmpty(req.get("companyName"));
//        if (StringUtils.isBlank(companyName)) {
//            data.setMsg("企业名称不能为空");
//            return data;
//        }
//
//        int maxAccount = NumberUtils.toInt(req.get("maxAccount"));
//        if (maxAccount == 0) {
//            data.setMsg("请填写账号数量");
//            return data;
//        }
//
//        String managerName = StringUtils.trimToEmpty(req.get("managerName"));
//        String managerMobile = StringUtils.trimToEmpty(req.get("managerMobile"));
//        if (RegexUtils.isNotPhone(managerMobile)) {
//            data.setMsg("联系电话格式错误");
//            return data;
//        }
//
//        String managerPassword = StringUtils.trimToEmpty(req.get("managerPassword"));
//        if (pkid == 0 && StringUtils.isBlank(managerPassword)) {
//            data.setMsg("请填写管理员密码");
//            return data;
//        }
//
//        String domain = StringUtils.trimToEmpty(req.get("domain"));
//        if (StringUtils.isBlank(domain)) {
//            data.setMsg("请填写企业域名");
//            return data;
//        }
//
//        int useRoleGroup = NumberUtils.toInt(req.get("useRoleGroup"));
//        int useOrderGroup = NumberUtils.toInt(req.get("useOrderGroup"));
//
//        UcTenant ucTenant = new UcTenant();
//
//        ucTenant.setPkid(pkid);
//        ucTenant.setManagerName(managerName);
//        ucTenant.setCompanyName(companyName);
//        ucTenant.setMaxAccount(maxAccount);
//        ucTenant.setManagerMobile(managerMobile);
//        ucTenant.setManagerPassword(managerPassword);
//        ucTenant.setDomain(domain);
//        ucTenant.setUseRoleGroup(useRoleGroup);
//        ucTenant.setUseOrderGroup(useOrderGroup);
//        ucTenant.setAddUserId(loginUser.getUcUser().getPkid());
//
//        try {
//            erTenantService.saveUcTenant(ucTenant);
//        } catch (CustomerDuplicateKeyException e) {
//            data.setMsg(e.getMessage());
//            return data;
//        }
//        data.setCode(200);
//        return data;
//    }

}