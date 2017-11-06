package com.gongsibao.api.conroller.uc;


import javax.ws.rs.Path;

@Path("/ucorganization")
public class UcOrganizationController {
//
//    @Autowired
//    private UcOrganizationService ucOrganizationService;
//
//    /**
//     * 组织机构详情查询
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/info")
//    public ResponseData info(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orgPkidStr")));
//
//        UcOrganization ucOrganization = ucOrganizationService.findOrgDetail(pkid);
//        data.setData(ucOrganization);
//        return data;
//    }
//
//    @RequestMapping("/all")
//    public ResponseData all(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        List<UcOrganization> list = ucOrganizationService.findAll();
//        data.setData(list);
//        return data;
//    }
//
//    /**
//     * 组织机构树查询
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/tree")
//    public ResponseData tree(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        Integer orgPkid = user.getUcOrganizationList().get(0).getPkid();
//        UcOrganization ucOrganization = ucOrganizationService.findTreeById(orgPkid, true);
//        data.setData(ucOrganization);
//        return data;
//    }
//
//    /**
//     * 查所有底层机构
//     *
//     * @return
//     */
//    @RequestMapping("/floor")
//    public ResponseData bottomList() {
//        ResponseData data = new ResponseData();
//        data.setData(ucOrganizationService.findLeafOrganizationList());
//        return data;
//    }
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//        int orgPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orgPkidStr")));
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        String shortName = StringUtils.trimToEmpty(request.getParameter("shortName"));
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("currentUserId", user.getUcUser().getPkid());
//        if (orgPkid > 0) {
//            paramMap.put("orgPkid", orgPkid);
//        }
//
//        if (StringUtils.isNotBlank(name)) {
//            paramMap.put("name", name);
//        }
//
//        if (StringUtils.isNotBlank(shortName)) {
//            paramMap.put("shortName", shortName);
//        }
//
//        Pager<UcOrganization> pager = ucOrganizationService.findOrgList(paramMap, currentPage, pageSize);
//        data.setData(pager);
//
//        return data;
//    }
//
//    @RequestMapping("/oplist")
//    public ResponseData oplist(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
////      权限过滤什么的, 都是画蛇添足, 影响使用, 切忌切记！
////        int userId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("userIdStr")));
////
////        UcOrganization myOrg = user.getUcOrganizationList().get(0);
////        List<Integer> userIds = new ArrayList<>();
////        userIds.add(userId);
////        Map<Integer, List<UcOrganization>> mapByUserIds = ucOrganizationService.findMapByUserIds(userIds);
////        List<UcOrganization> ucOrganizations = mapByUserIds.get(userId);
////        List<UcOrganization> leafOrganizationList = ucOrganizationService.findLeafOrganizationList();
////        List<UcOrganization> res = new ArrayList<>();
////        if (ucOrganizations!=null) {
////
////            for (UcOrganization o : ucOrganizations) {
////                if (leafOrganizationList.contains(o)) {
////                    res.add(o);
////                }
////            }
////        }
////        if (leafOrganizationList!=null) {
////
////            for (UcOrganization o : leafOrganizationList) {
////                if (!res.contains(o)) {
////                    res.add(o);
////                }
////            }
////        }
////
////        UcOrganization tmpUcOrg = null;
////        for (UcOrganization ucOrg : res) {
////            if (ucOrg.getPkid() == myOrg.getPkid()) {
////                tmpUcOrg = ucOrg;
////                break;
////            }
////        }
////
////        if (null != tmpUcOrg) {
////            res.remove(tmpUcOrg);
////            res.add(0, tmpUcOrg);
////        }
//
//        UcOrganization myOrg = user.getUcOrganizationList().get(0);
//        List<UcOrganization> all = ucOrganizationService.findAll();
//        for (UcOrganization organization : all) {
//            if (organization.getPkid() == myOrg.getPkid()) {
//                all.remove(organization);
//                break;
//            }
//        }
//
//        all.add(0, myOrg);
//        data.setData(all);
//        return data;
//    }
//
//
//    @RequestMapping("/myList")
//    public ResponseData userOrgList(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int exceptOrgId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("exceptOrganizationIdStr")));
//        List<UcOrganization> userOrgList = ucOrganizationService.getUserOrgList(user.getUcUser().getPkid(), exceptOrgId);
//        data.setData(userOrgList);
//        return data;
//    }
//
//    /**
//     * 查所有底层机构
//     *
//     * @return
//     */
//    @RequestMapping("/del")
//    public ResponseData del(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orgPkidStr")));
//        if (pkid == 0) {
//            data.setCode(-1);
//            data.setMsg("删除失败, 不要修改参数");
//            return data;
//        }
//
//        int rs = ucOrganizationService.delete(pkid, user.getUcUser().getPkid());
//        if (rs > 0) {
//            data.setCode(200);
//            data.setMsg("删除成功");
//        } else if (rs == -1) {
//            data.setCode(-1);
//            data.setMsg("您无权删除该组织机构");
//        } else if (rs == -2) {
//            data.setCode(-2);
//            data.setMsg("存在配置了此组织的用户，无法删除！\n" +
//                    "您可以在用户设置中查询配置此了组织的用户");
//        } else if (rs == 0) {
//            data.setCode(0);
//            data.setMsg("删除失败");
//        }
//        return data;
//    }
//
//    /**
//     * 查所有底层机构
//     *
//     * @return
//     */
//    @RequestMapping("/save")
//    public ResponseData save(LoginUser user,  @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        OrgRequest orgRequest = JsonUtils.jsonToObject(json, OrgRequest.class);
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orgRequest.getOrganizationPkidStr()));
//        int pid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orgRequest.getPidStr()));
//        String name = StringUtils.trimToEmpty(orgRequest.getName());
//        String shortName = StringUtils.trimToEmpty(orgRequest.getShortName());
//        int leaderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orgRequest.getLeaderIdStr()));
//        int cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orgRequest.getCityIdStr()));
//
//        String categoryIds = StringUtils.trimToEmpty(orgRequest.getCategoryIds());
//        String cityIds = StringUtils.trimToEmpty(orgRequest.getCityIds());
//
//        if (pkid == 0 && pid == 0) {
//            data.setMsg("请选择上级组织机构");
//            return data;
//        }
//
//        if (StringUtils.isBlank(name)) {
//            throw new IllegalArgumentException("empty name[" + orgRequest.getName() + "]");
//        }
//
//        if (StringUtils.isBlank(shortName)) {
//            throw new IllegalArgumentException("empty name[" + orgRequest.getShortName() + "]");
//        }
//
//        UcOrganization organization = new UcOrganization() {{
//            setPid(pid);
//            setName(name);
//            setCityId(cityId);
//            setShortName(shortName);
//            setLeaderId(leaderId);
//            setCategoryIds(SecurityUtils.rc4DecryptBatch(categoryIds.split(",")));
//            setCityIds(SecurityUtils.rc4DecryptBatch(cityIds.split(",")));
//
//            setIsEnabled(1);
//            setIsLeaf(1);
//            setAddUserId(user.getUcUser().getPkid());
//        }};
//        organization.setPkid(pkid);
//
//        int rs = ucOrganizationService.save(organization);
//
//        if (rs > 0) {
//            data.setCode(200);
//        } else if (rs == -1) {
//            data.setMsg("您无法修改该组织机构");
//        } else if (rs == -2) {
//            data.setMsg("上级组织选择错误");
//        } else {
//            data.setMsg("保存失败");
//        }
//        return data;
//    }
//
//    /**
//     * 查所有底层机构
//     *
//     * @return
//     */
//    @RequestMapping("/suggest")
//    public ResponseData suggest(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        List<OrgSuggest> list = ucOrganizationService.getSuggest(name);
//        data.setData(list);
//        return data;
//    }
//
//    @RequestMapping("/sync")
//    public ResponseData syncSql(HttpServletRequest request,HttpServletResponse response){
//        ResponseData data = new ResponseData();
//
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyname"));
//        String userName = StringUtils.trimToEmpty(request.getParameter("username"));
//
//        if(StringUtils.isBlank(companyName)){
//            data.setMsg("公司名必填");
//            return data;
//        }
//
//        if (StringUtils.isBlank(userName)) {
//            data.setMsg("用户名必填");
//            return data;
//        }
//
//        ucOrganizationService.syncSql(companyName, userName,data.getMsg());
//
//        return data;
//    }
}