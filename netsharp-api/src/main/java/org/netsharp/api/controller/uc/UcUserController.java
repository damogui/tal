package org.netsharp.api.controller.uc;


import javax.ws.rs.Path;

@Path("/ucuser")
public class UcUserController {
//
//    @Autowired
//    private UcUserService ucUserService;
//
//    @Autowired
//    private UcRoleService ucRoleService;
//
//    @Autowired
//    private CacheService cacheService;
//
//    @Autowired
//    private BdSyncService bdSyncService;
//
//    @Autowired
//    private CaptchaService captchaService;
//
//    @Autowired
//    private SmsService smsService;
//
//    @Autowired
//    private UcUserLoginLogService ucUserLoginLogService;
//
//    @Autowired
//    private SupplerService supplerService;
//
//    private static final long LOGIN_MAX_TIMES = 3;
//    private static final String NEED_CAPTCHA = "need_captcha";
//
//
//    @RequestMapping("/menu")
//    public ResponseData menu(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        List<BaseAuthMenu> menuList = user.getMenuList();
//
//        data.setData(menuList);
//        return data;
//    }
//
//    @RequestMapping("/currentInfo")
//    public ResponseData user(HttpServletRequest request, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> userInfo = new HashMap<>();
//        userInfo.put("pkidStr", StringUtils.trimToEmpty(user.getUcUser().getPkidStr()));
//        userInfo.put("userName", StringUtils.trimToEmpty(user.getUcUser().getRealName()));
//        userInfo.put("headThumbFileUrl", StringUtils.trimToEmpty(user.getUcUser().getHeadThumbFileUrl()));
//        userInfo.put("organizationName", StringUtils.trimToEmpty(user.getUcOrganizationList().get(0).getShortName()));
//        userInfo.put("supplyStatus", user.getUcUser().getSupplyStatus());
//        userInfo.put("organization", user.getUcOrganizationList().get(0));
//        userInfo.put("roleTags", user.getRoleTags());
//
//        UcUser niubiUser = ucUserService.findById(user.getUcUser().getPkid());
//        userInfo.put("isAcceptOrder", niubiUser.getIsAcceptOrder());
//        userInfo.put("supplyType", niubiUser.getSupplyType());
//        data.setData(userInfo);
//        return data;
//    }
//
//    @RequestMapping("/info")
//    public ResponseData info(HttpServletRequest request) {
//        ResponseData data = new ResponseData();
//        int userId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("userPkidStr")));
//        if (userId == 0) {
//            throw new IllegalArgumentException("userId[" + request.getParameter("userPkidStr") + "]");
//        }
//        UcUser user = ucUserService.findUserInfoById(userId);
//        user.setPubKey(ucUserService.getPubKey(userId));
//        data.setData(user);
//        return data;
//    }
//
//    @RequestMapping("/save")
//    public ResponseData save(HttpServletRequest request, LoginUser user, @RequestBody String json) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        UcUserRequest userRequest = JsonUtils.jsonToObject(json, UcUserRequest.class);
//
//        Integer currentUserId = user.getUcUser().getPkid();
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(userRequest.getPkidStr()));
//        int orgId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(userRequest.getOrganizationPkidStr()));
//
//        String mobilePhone = StringUtils.trimToEmpty(userRequest.getMobilePhone());
//        String realName = StringUtils.trimToEmpty(userRequest.getRealName());
//        String email = StringUtils.trimToEmpty(userRequest.getEmail());
//        String qq = StringUtils.trimToEmpty(userRequest.getQq());
//        String weixin = StringUtils.trimToEmpty(userRequest.getWeixin());
//        String passwd = StringUtils.trimToEmpty(userRequest.getPasswd());
//        String passwdConfirm = StringUtils.trimToEmpty(userRequest.getPasswdConfirm());
//        String headThumbFileUrl = StringUtils.trimToEmpty(userRequest.getHeadThumbFileUrl());
//        String ukeyPid = StringUtils.trimToEmpty(userRequest.getUkeyPid());
//        int abilityId = NumberUtils.toInt(userRequest.getAbilityId(), 1072);
//        int priorityId = NumberUtils.toInt(userRequest.getPriorityId(), 1092);
//        int officeId = NumberUtils.toInt(userRequest.getOfficeId());
//
//        int sex = NumberUtils.toInt(userRequest.getSex());
//        int isInner = NumberUtils.toInt(userRequest.getIsInner());
//
//        String roleIdStrs = StringUtils.trimToEmpty(userRequest.getRoleIds());
//        String[] canPassArr = StringUtils.trimToEmpty(userRequest.getCanPass()).split(",");
//
//        List<Integer> roleIds = SecurityUtils.rc4DecryptBatch(roleIdStrs.split(","));
//
//        // 手机号码
//        if (StringUtils.isBlank(mobilePhone)) {
//            data.setMsg("请填写手机号码");
//            return data;
//        }
//
//        // 姓名
//        if (StringUtils.isBlank(realName)) {
//            data.setMsg("请填写手机号码");
//            return data;
//        }
//
//        // 组织机构
//        if (orgId == 0) {
//            data.setMsg("请先选择组织机构");
//            return data;
//        }
//
//        // 角色
//        if (CollectionUtils.isEmpty(roleIds)) {
//            data.setMsg("请先选择角色");
//            return data;
//        }
//
//        if (roleIds.size() != canPassArr.length) {
//            data.setMsg("角色数据错误");
//            return data;
//        }
//
//        if (StringUtils.isBlank(passwd) && pkid == 0) {
//            data.setMsg("请输入密码");
//            return data;
//        }
//
//        if (!passwd.equals(passwdConfirm)) {
//            data.setMsg("两次密码不一致");
//            return data;
//        }
//
//        UcUser ucUser = new UcUser() {{
//            setMobilePhone(mobilePhone);
//            setRealName(realName);
//            setIsInner(isInner);
//            setEmail(email);
//            setQq(qq);
//            setWeixin(weixin);
//            setSex(sex);
//            setAbilityId(abilityId);
//            setPriorityId(priorityId);
//            setPasswd(passwd);
//            setHeadThumbFileUrl(headThumbFileUrl);
//            setHeadThumbFileId(0);
//            setAddUserId(currentUserId);
//            setOfficeId(officeId);
//            setUserTypeId(1021); // 系统用户
//            setIsEnabled(1);     // 默认启用
////            setIsAcceptOrder(0); // 默认不接单
//        }};
//        ucUser.setUkeyPid(ukeyPid);
//        ucUser.setPkid(pkid);
//        ucUser.setPubKey(StringUtils.trimToEmpty(userRequest.getPubKey()));
//        ucUser.setLoginValid(userRequest.getLoginValid());
//        ucUser.setBusinessList(userRequest.getBusinessList());
//
//        UcUserOrganizationMap ucUserOrganizationMap = new UcUserOrganizationMap();
//        ucUserOrganizationMap.setOrganizationId(orgId);
//        ucUserOrganizationMap.setAddUserId(currentUserId);
//
//        // 组织
//        ucUser.setUserOrganizationMapList(new ArrayList<UcUserOrganizationMap>() {{
//            add(ucUserOrganizationMap);
//        }});
//
//        List<UcUserRoleMap> userRoleMapList = new ArrayList<>();
//        // 权限
//        for (int i = 0; i < roleIds.size(); i++) {
//            int roleId = roleIds.get(i);
//            int canPass = NumberUtils.toInt(canPassArr[i]);
//            UcUserRoleMap map = new UcUserRoleMap();
//            map.setRoleId(roleId);
//            map.setCanPass(canPass);
//            map.setAddUserId(currentUserId);
//
//            userRoleMapList.add(map);
//        }
//
//        ucUser.setUserRoleMapList(userRoleMapList);
//        int rs = ucUserService.saveUcUser(ucUser);
//        if (rs == -1) {
//            data.setMsg("组织机构错误");
//        } else if (rs == -2) {
//            data.setMsg("手机号码重复");
//        } else {
//            data.setCode(200);
//        }
//
//        return data;
//    }
//
//    @RequestMapping("/nums")
//    public ResponseData nums(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Map<String, Integer> map = ucUserService.findUserNums(user.getUcUser().getPkid());
//        data.setData(map);
//        return data;
//    }
//
//    @RequestMapping("/managerList")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//        int isEnabled = NumberUtils.toInt(request.getParameter("isEnabled"), -1);
//        int organizationPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("organizationPkidStr")));
//
//        String mobilePhone = StringUtils.trimToEmpty(request.getParameter("mobilePhone"));
//        String realName = StringUtils.trimToEmpty(request.getParameter("realName"));
//        String roleName = StringUtils.trimToEmpty(request.getParameter("roleName"));
//
//        Map<String, Object> param = new HashMap<>();
//        param.put("currentUserId", user.getUcUser().getPkid());
//
//        if (isEnabled > -1) {
//            param.put("isEnabled", isEnabled);
//        }
//
//        if (organizationPkid > 0) {
//            param.put("organizationPkid", organizationPkid);
//        }
//
//        if (StringUtils.isNotBlank(mobilePhone)) {
//            param.put("mobilePhone", mobilePhone);
//        }
//
//        if (StringUtils.isNotBlank(realName)) {
//            param.put("realName", realName);
//        }
//
//        if (StringUtils.isNotBlank(roleName)) {
//            param.put("roleName", roleName);
//        }
//
//        Pager<UcUser> pager = ucUserService.findManagerUserList(param, currentPage, pageSize);
//        data.setData(pager);
//        return data;
//    }
//
//    // 当前用户组织机构下用户
//    @RequestMapping("/orgUsers")
//    public ResponseData orgUsers(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        int orgPkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orgPkidStr")));
//        data.setData(ucUserService.findUsersByOrgId(orgPkid));
//        return data;
//    }
//
//    /**
//     * 在职
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/enable")
//    public ResponseData enable(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("userPkidStr")));
//        if (pkid == 0) {
//            throw new IllegalArgumentException("userPkid[" + request.getParameter("userPkid") + "]");
//        }
//
//        int rs = ucUserService.editUserEnabled(pkid, 1, user.getUcUser().getPkid());
//        if (rs == -1) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    /**
//     * 离职
//     *
//     * @param request
//     * @param response
//     * @param user
//     * @return
//     */
//    @RequestMapping("/unEnable")
//    public ResponseData unEnable(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("userPkidStr")));
//
//        int rs = ucUserService.editUserEnabled(pkid, 0, user.getUcUser().getPkid());
//        if (rs <= 0) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//
//        return data;
//    }
//
//
//    @RequestMapping("/login/page")
//    public ModelAndView tologin(HttpServletRequest request, String msg) {
//        ModelAndView mv = new ModelAndView("login");
//        request.setAttribute("redirectUrl", request.getParameter("redirectUrl"));
//
//        if (StringUtils.isNotBlank(msg)) {
//            mv.addObject("msg", msg);
//        }
//
//        int times = getCaptchaTimes(request);
//        if (times > LOGIN_MAX_TIMES) {
//            mv.addObject(NEED_CAPTCHA, true);
//            mv.addObject("randomKey", RandomStringUtils.randomAlphanumeric(32));
//            mv.addObject("ctx_sys", PropertiesReader.getValue("project", "project_addr") + "/gongsibao-sys/");
//        }
//        mv.addObject("loginName", StringUtils.trimToEmpty(request.getParameter("loginName")));
//        return mv;
//    }
//
//    @RequestMapping("/login")
//    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mv = new ModelAndView();
//        String loginName = StringUtils.trimToEmpty(request.getParameter("loginName"));
//        String passwd = StringUtils.trimToEmpty(request.getParameter("passwd"));
//        String captcha = StringUtils.trimToEmpty(request.getParameter("captcha"));
//        String randomKey = StringUtils.trimToEmpty(request.getParameter("randomKey"));
//
//        String redirectUrl = StringUtils.trimToEmpty(request.getParameter("redirectUrl"));
//        // 获取登录次数
//        int times = getCaptchaTimes(request);
//        if (times > LOGIN_MAX_TIMES) {
//            // 验证图片验证码
//            if (!captchaService.validCaptchaText(randomKey, captcha)) {
//                return tologin(request, "验证码错误");
//            }
//        }
//
//        // 登录次数 +1
//        incrCaptchaTimes(request);
//
//        UcUser ucUser = ucUserService.findByLoginName(loginName);
//
//        if (ucUser != null) {
//            if (ucUser.getIsEnabled() != 1) {
//                //启动状态
//                return tologin(request, "禁止登录");
//            }
//            if (!ucUser.getPasswd().equals(SecurityUtils.passwdMd5(passwd))) {
//                //密码不正确
//                return tologin(request, "用户名或密码错误");
//            }
//
//            LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//            if (loginUser != null) {
//                setLogin(request, response, loginUser);
//            }
//            if (StringUtils.isBlank(redirectUrl)) {
//                mv = new ModelAndView(new RedirectView("/cms/"));
//            } else {
//                mv = new ModelAndView(new RedirectView(redirectUrl));
//            }
//        } else {
//            return tologin(request, "用户名不正确");
//        }
//        return mv;
//    }
//
//    private void setLogin(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 清cookie
//        WebUtils.removeCookieNet(response, ConstantWeb.COOKIE_LOGIN_TICKET);
//
//        if (null != loginUser) {
//            String ticket = loginUser.getUcUser().getTicket();
//            boolean isLoginSingle = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_login_single"));
//            final Integer userId = loginUser.getUcUser().getPkid();
//            if (isLoginSingle) {
//                // 单点登录
//                if (StringUtils.isNotBlank(ticket)) {
//                    cacheService.delete(ConstantCache.LOGIN_KEY + ticket);
//                }
//                ticket = UUID.randomUUID().toString();
//                loginUser.getUcUser().setTicket(ticket);
//                ucUserService.updateTicket(userId, ticket);
//            } else {
//                // 未单点登录
//                if (StringUtils.isBlank(ticket)) {
//                    ticket = UUID.randomUUID().toString();
//                    loginUser.getUcUser().setTicket(ticket);
//                    ucUserService.updateTicket(userId, ticket);
//                }
//            }
//
//            cacheService.put(ConstantCache.LOGIN_KEY + ticket, loginUser);
//
//            boolean isOnline = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_online"));
//            WebUtils.setCookieNet(response, ConstantWeb.COOKIE_LOGIN_TICKET, ticket, isOnline ? -1 : ConstantCache.TIME_ONE_YEAR);
//
//            // 清空登录次数
//            removeCaptchaTimes(request);
//
//            final String ipAddr = WebUtils.getIpAddr(request);
//            final String userAgent = WebUtils.getUserAgent(request);
//            try {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        ucUserLoginLogService.saveLog(userId, ipAddr, userAgent);
//                    }
//                }.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void setLoginCom(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 清cookie
//        WebUtils.removeCookieCom(response, ConstantWeb.COOKIE_LOGIN_TICKET);
//
//        if (null != loginUser) {
//            String ticket = loginUser.getUcUser().getTicket();
//            boolean isLoginSingle = BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_login_single"));
//            if (isLoginSingle) {
//                // 单点登录
//                if (StringUtils.isNotBlank(ticket)) {
//                    cacheService.delete(ConstantCache.LOGIN_KEY + ticket);
//                }
//                ticket = UUID.randomUUID().toString();
//                loginUser.getUcUser().setTicket(ticket);
//                ucUserService.updateTicket(loginUser.getUcUser().getPkid(), ticket);
//            } else {
//                // 未单点登录
//                if (StringUtils.isBlank(ticket)) {
//                    ticket = UUID.randomUUID().toString();
//                    loginUser.getUcUser().setTicket(ticket);
//                    ucUserService.updateTicket(loginUser.getUcUser().getPkid(), ticket);
//                }
//            }
//
//            cacheService.put(ConstantCache.LOGIN_KEY + ticket, loginUser);
//
//            WebUtils.setCookieCom(response, ConstantWeb.COOKIE_LOGIN_TICKET, ticket, ConstantCache.TIME_ONE_YEAR);
//
//            // 清空登录次数
//            removeCaptchaTimes(request);
//        }
//    }
//
//    @RequestMapping("/logout")
//    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        String redirectUrl = StringUtils.trimToEmpty(request.getParameter("redirectUrl"));
//
//        // 清cookie
//        WebUtils.removeCookieNet(response, ConstantWeb.COOKIE_LOGIN_TICKET);
//
//        String ticket = null;
//        if (null != user) {
//            ticket = user.getUcUser().getTicket();
//        }
//        ticket = StringUtils.trimToEmpty(ticket);
//        // 清缓存
//        cacheService.delete(ConstantCache.LOGIN_KEY + ticket);
//
//        ModelAndView mv = null;
//        if (StringUtils.isBlank(redirectUrl)) {
//            mv = new ModelAndView(new RedirectView(PropertiesReader.getValue("project", "project_addr") + "/gongsibao-uc/ucuser/login/page"));
//        } else {
//            mv = new ModelAndView(new RedirectView(redirectUrl));
//        }
//        return mv;
//    }
//
//    @RequestMapping("/logoutAjax")
//    public ResponseData logoutAjax(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        data.setCode(200);
//
//        // 清cookie
//        WebUtils.removeCookieNet(response, ConstantWeb.COOKIE_LOGIN_TICKET);
//
//        String ticket = null;
//        if (null != user) {
//            ticket = user.getUcUser().getTicket();
//        }
//        ticket = StringUtils.trimToEmpty(ticket);
//        // 清缓存
//        cacheService.delete(ConstantCache.LOGIN_KEY + ticket);
//
//        return data;
//    }
//
//    @RequestMapping("/loginAjax")
//    public ResponseData loginAjax(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        String loginName = StringUtils.trimToEmpty(request.getParameter("loginName"));
//        String passwd = StringUtils.trimToEmpty(request.getParameter("passwd"));
//        String captcha = StringUtils.trimToEmpty(request.getParameter("captcha"));
//        String randomKey = StringUtils.trimToEmpty(request.getParameter("randomKey"));
//
//        // 获取登录次数
//        int times = getCaptchaTimes(request);
//        data.setCode(times >= LOGIN_MAX_TIMES ? -2 : -1);
//        if (times > LOGIN_MAX_TIMES) {
//            // 验证图片验证码
//            if (!captchaService.validCaptchaText(randomKey, captcha)) {
//                data.setMsg("图片验证码错误");
//                return data;
//            }
//        }
//
//        // 登录次数 +1
//        incrCaptchaTimes(request);
//
//        UcUser ucUser = ucUserService.findByLoginName(loginName);
//
//        if (ucUser != null) {
//            if (ucUser.getIsEnabled() != 1) {
//                data.setMsg("禁止登录");
//                return data;
//            }
//            if (!ucUser.getPasswd().equals(SecurityUtils.passwdMd5(passwd))) {
//                data.setMsg("用户名或密码错误");
//                return data;
//            }
//
//            List<UcRole> ucRoleList = ucRoleService.findByUserPkid(ucUser.getPkid());
//            List<String> roleTags = ucRoleList.stream().map(UcRole::getTag).collect(Collectors.toList());
//
//            Suppler suppler = supplerService.getById(ucUser.getPkid());
//            if (roleTags.contains(RoleTag.ROLE_CJYH) || null != suppler) {
//                LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//                if (loginUser != null) {
//                    setLogin(request, response, loginUser);
//                    data.setCode(201);
//                    return data;
//                }
//            }
////            if (ucUser.getPkid() == 3465) {
////                LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
////                if (loginUser != null) {
////                    setLogin(request, response, loginUser);
////                    data.setCode(201);
////                    return data;
////                }
////            }
////            if (null != suppler && ucUser.getIsInner() == 0) {
////                data.setCode(-1);
////                data.setMsg("供应商账号请到http://s.gongsibao.com下登录");
////                return data;
////            }
//
//            String current = StringUtils.trimToEmpty(PropertiesReader.getValue("project", "current_environment"));
//            if (current.equals("dev")) {
//                LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//                if (loginUser != null) {
//                    setLogin(request, response, loginUser);
//                    data.setCode(201);
//                    return data;
//                }
//            }
//
//            int loginValid = ucUser.getLoginValid();
//
//            if (loginValid == 0) {
//                LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//                setLogin(request, response, loginUser);
//                data.setCode(201);
//                return data;
//            }
//
//            String mobilePhone = ucUser.getMobilePhone();
//            if (RegexUtils.isNotPhone(mobilePhone)) {
//                data.setMsg("用户手机号码" + (StringUtils.isBlank(mobilePhone) ? "请联系管理员设置您的手机号码" : "您的手机号码格式错误，请联系管理员设置"));
//                return data;
//            }
//
//
//            String userKey = RandomStringUtils.randomNumeric(6);
//            String securityKey = RandomStringUtils.randomAlphabetic(10);
//            Map<String, Object> resultData = new HashMap<>();
//            resultData.put("pkidStr", ucUser.getPkidStr());
//            resultData.put("loginValid", loginValid);
//            resultData.put("ukeyPid", ucUser.getUkeyPid());
//            resultData.put("userKey", userKey);
//
//            cacheService.put(ConstantCache.UKEY_RANDOM + ucUser.getPkid() + "_" + userKey, securityKey);
//            if (loginValid == 1 || loginValid == 3) {
//                String pubKey = ucUserService.getPubKey(ucUser.getPkid());
//                if (StringUtils.isBlank(pubKey)) {
//                    data.setMsg("请联系用户管理员为您设置ukey公钥");
//                    return data;
//                }
//
//                resultData.put("encryptKey", StringUtils.bytesToHexString(RSA.encryptByPublicKey2(Arrays.copyOf(("aa" + securityKey).getBytes(), 128), pubKey)));
//            }
//
//            if (loginValid == 2 || loginValid == 3) {
//                String content = "【公司宝】本次登录的验证码为：{code}，30分钟内有效。如非您本人操作，请及时更改密码。";
//                smsService.sendSmsValidCode(ConstantCache.LOGIN_SMS_PREV_KEY, 2, mobilePhone, content);
//                data.setMsg("短信已发送");
//            }
//
//            data.setCode(200);
//            data.setData(resultData);
//            removeCaptchaTimes(request);
//        } else {
//            data.setMsg("用户名或密码错误");
//            return data;
//        }
//
//        return data;
//    }
//
//    @RequestMapping("/login/ukey")
//    public ResponseData loginUkey(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        String decryptKey = StringUtils.trimToEmpty(request.getParameter("decryptKey"));
//        String userKey = StringUtils.trimToEmpty(request.getParameter("userKey"));
//        String code = StringUtils.trimToEmpty(request.getParameter("code"));
//
//        if (StringUtils.isBlank(decryptKey)) {
//            data.setMsg("请插入UKEY");
//            return data;
//        }
//
//        UcUser ucUser = ucUserService.findById(pkid);
//
//        if (ucUser.getIsEnabled() != 1) {
//            data.setMsg("禁止登录");
//            return data;
//        }
//
//        if (null == ucUser) {
//            data.setMsg("登录失败");
//            return data;
//        }
//
//        int loginValid = ucUser.getLoginValid();
//
//        if (loginValid == 1 || loginValid == 3) {
//            String securityKey = StringUtils.trimToEmpty(cacheService.get(ConstantCache.UKEY_RANDOM + ucUser.getPkid() + "_" + userKey, String.class));
//            if (!securityKey.equals(decryptKey)) {
//                data.setMsg("非法登录");
//                return data;
//            }
//        }
//
//        if (loginValid == 2 || loginValid == 3) {
//            String timesKey = ConstantCache.LOGIN_SMS_TIMES + ucUser.getMobilePhone();
//            String codeKey = ConstantCache.LOGIN_SMS_PREV_KEY + ucUser.getMobilePhone();
//
//            int useTimes = getUseTimes(timesKey);
//
//            boolean isClean = useTimes >= 3;
//
//            if (!smsService.validMobileCode(codeKey, code, true)) {
//                if (isClean) {
//                    removeUseTimes(timesKey);
//                    data.setMsg("三次输入错误，短信验证码作废，请刷新页面重新登录");
//                    return data;
//                } else {
//                    incrUseTimes(timesKey);
//                    data.setMsg("短信验证码错误，请重新输入");
//                    return data;
//                }
//            }
//            removeUseTimes(timesKey);
//            cacheService.delete(codeKey);
//        }
//
//        LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//        if (loginUser != null) {
//            setLogin(request, response, loginUser);
//        }
//        data.setCode(200);
//        return data;
//    }
//
//    @RequestMapping("/login/sms")
//    public ResponseData loginSms(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        String loginName = StringUtils.trimToEmpty(request.getParameter("loginName"));
//        String passwd = StringUtils.trimToEmpty(request.getParameter("passwd"));
//        String code = StringUtils.trimToEmpty(request.getParameter("code"));
//
//        UcUser ucUser = ucUserService.findByLoginName(loginName);
//        String timesKey = ConstantCache.LOGIN_SMS_TIMES + ucUser.getMobilePhone();
//        String codeKey = ConstantCache.LOGIN_SMS_PREV_KEY + ucUser.getMobilePhone();
//        int useTimes = getUseTimes(timesKey);
//
//        boolean isClean = useTimes >= 3;
//
//        if (!smsService.validMobileCode(codeKey, code, isClean)) {
//            if (isClean) {
//                removeUseTimes(timesKey);
//                data.setMsg("三次输入错误，短信验证码作废，请刷新页面重新登录");
//                return data;
//            } else {
//                incrUseTimes(timesKey);
//                data.setMsg("短信验证码错误，请重新输入");
//                return data;
//            }
//        }
//
//        if (ucUser != null) {
//            if (ucUser.getIsEnabled() != 1) {
//                data.setMsg("禁止登录");
//                return data;
//            }
//            if (!ucUser.getPasswd().equals(SecurityUtils.passwdMd5(passwd))) {
//                data.setMsg("用户名或密码错误");
//                return data;
//            }
//
//            LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//            if (loginUser != null) {
//                setLogin(request, response, loginUser);
//            }
//            removeUseTimes(timesKey);
//            cacheService.delete(codeKey);
//        } else {
//            data.setMsg("用户名不正确");
//            return data;
//        }
//        data.setCode(200);
//        return data;
//    }
//
//    @RequestMapping("/logoutCom")
//    public ResponseData logoutCom(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        data.setCode(200);
//
//        // 清cookie
//        WebUtils.removeCookieCom(response, ConstantWeb.COOKIE_LOGIN_TICKET);
//
//        String ticket = null;
//        if (null != user) {
//            ticket = user.getUcUser().getTicket();
//        }
//        ticket = StringUtils.trimToEmpty(ticket);
//        // 清缓存
//        cacheService.delete(ConstantCache.LOGIN_KEY + ticket);
//
//        return data;
//    }
//
//
//    @RequestMapping("/loginCom")
//    public ResponseData loginCom(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String loginName = StringUtils.trimToEmpty(request.getParameter("loginName"));
//        String passwd = StringUtils.trimToEmpty(request.getParameter("passwd"));
//        String captcha = StringUtils.trimToEmpty(request.getParameter("captcha"));
//        String randomKey = StringUtils.trimToEmpty(request.getParameter("randomKey"));
//
//        final int COM_MAX_LOGIN_TIMES = 100;
//        // 获取登录次数
//        int times = getCaptchaTimes(request);
//        data.setCode(times >= COM_MAX_LOGIN_TIMES ? -2 : -1);
//        if (times > COM_MAX_LOGIN_TIMES) {
//            // 验证图片验证码
//            if (!captchaService.validCaptchaText(randomKey, captcha)) {
//                data.setMsg("验证码错误");
//                return data;
//            }
//        }
//
//        // 登录次数 +1
//        incrCaptchaTimes(request);
//
//        UcUser ucUser = ucUserService.findByLoginName(loginName);
//
//        if (ucUser != null) {
//            if (ucUser.getIsEnabled() != 1) {
//                data.setMsg("禁止登录");
//                return data;
//            }
//            if (!ucUser.getPasswd().equals(SecurityUtils.passwdMd5(passwd))) {
//                data.setMsg("用户名或密码错误");
//                return data;
//            }
//
//            LoginUser loginUser = ucUserService.findLoginUserReload(ucUser.getPkid());
//            if (loginUser != null) {
//                setLoginCom(request, response, loginUser);
//            }
//        } else {
//            data.setMsg("用户名不正确");
//            return data;
//        }
//        data.setCode(200);
//        return data;
//    }
//
//    @RequestMapping("/ticketValid")
//    public ResponseData isLogin(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        String ticket = request.getParameter("ticket");
//        if (StringUtils.isBlank(ticket)) {
//            // 空的滚粗
//            return data;
//        }
//
//        // 缓存找
//        LoginUser loginUser = cacheService.get(ConstantCache.LOGIN_KEY + ticket, LoginUser.class);
//        BdSync bdSync = null;
//        if (null == loginUser) {
//            // 缓存没有的话
//            UcUser ucUser = ucUserService.findByTicket(ticket);
//            if (null == ucUser) {
//                return data;
//            }
//
//            bdSync = bdSyncService.findByMPkidAndTableName(ucUser.getPkid(), "uc_user");
//        } else {
//            bdSync = loginUser.getBdSync();
//        }
//
//        if (null == bdSync || StringUtils.isBlank(bdSync.getsId())) {
//            return data;
//        }
//
//        Map<String, String> rs = new HashMap<>();
//        rs.put("sid", bdSync.getsId());
//        data.setCode(200);
//        data.setData(rs);
//        return data;
//    }
//
//    @RequestMapping("/getInfo.json")
//    public ResponseData getInfoJson(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setData(loginUser);
//        return data;
//    }
//
//    @RequestMapping("/manageList")
//    public ResponseData manageList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 名称
//        String realName = StringUtils.trimToEmpty(request.getParameter("realName"));
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("currentUserId", loginUser.getUcUser().getPkid());// 当前登陆者
//        if (StringUtils.isNotBlank(realName)) {
//            condition.put("realName", realName);
//        }
//
//        Pager<UcUser> pager = ucUserService.findByCondition(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 业务员
//     *
//     * @param request
//     * @param response
//     * @param loginUser
//     * @return
//     */
//    @RequestMapping("/ywyList")
//    public ResponseData aList(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 当前页
//        int currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        // 每页显示记录数量
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        Map<String, Object> condition = getConditionMap(request, loginUser);
//        condition.put("hasAuth", 1);
//        condition.put("isEnabled", 1);
//
//        Pager<UcUser> pager = ucUserService.findByCondition(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/ywyListAll")
//    public ResponseData ywyAll(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        // 当前页
//        int currentPage = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("currentPage")));
//        // 每页显示记录数量
//        int pageSize = NumberUtils.toInt(StringUtils.trimToEmpty(request.getParameter("pageSize")));
//        String module = StringUtils.trimToEmpty(request.getParameter("module"));
//        Map<String, Object> condition = getConditionMap(request, loginUser);
//
//        condition.put("module", module);
//        condition.put("isEnabled", 1);
//
//        Pager<UcUser> pager = ucUserService.findByCondition(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    /**
//     * 更新是否接单
//     */
//    @RequestMapping(value = "/acceptOrder", method = RequestMethod.GET)
//    public ResponseData acceptOrder(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        int userId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
//        if (userId == 0) {
//            data.setMsg("pkidStr[" + request.getParameter("pkidStr") + "]");
//            return data;
//        }
//        int isAcceptOrder = NumberUtils.toInt(request.getParameter("isAcceptOrder"), 1);
//        int rs = ucUserService.updateIsAcceptOrder(userId, isAcceptOrder, loginUser.getUcUser().getPkid());
//        if (rs == -1) {
//            data.setMsg("该用户不是业务员，不能设置接单");
//        } else {
//            data.setCode(200);
//            data.setMsg("修改成功");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/loginList", method = RequestMethod.GET)
//    public ResponseData loginList(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_DLRZGLY)) {
//            return data;
//        }
//        data.setCode(-1);
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//        pageSize = pageSize == 0 ? 1000 : pageSize;
//
//        int userId = NumberUtils.toInt(request.getParameter("userId"));
//        String userName = StringUtils.trimToEmpty(request.getParameter("userName"));
//        String ip = StringUtils.trimToEmpty(request.getParameter("ip"));
//        String userAgent = StringUtils.trimToEmpty(request.getParameter("userAgent"));
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//
//        Map<String, Object> paramMap = new HashMap<>();
//        if (userId > 0) {
//            paramMap.put("userId", userId);
//        }
//        if (StringUtils.isNotBlank(userName)) {
//            paramMap.put("userName", userName);
//        }
//        if (StringUtils.isNotBlank(ip)) {
//            paramMap.put("ip", ip);
//        }
//        if (StringUtils.isNotBlank(userAgent)) {
//            paramMap.put("userAgent", userAgent);
//        }
//        if (StringUtils.isNotBlank(beginTime)) {
//            paramMap.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(endTime)) {
//            paramMap.put("endTime", endTime + " 23:59:59");
//        }
//
//        Pager<UcUserLoginLog> pager = ucUserLoginLogService.findLoginList(paramMap, currentPage, pageSize);
//        data.setCode(200);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping(value = "/loginUserStat", method = RequestMethod.GET)
//    public ResponseData loginStat(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_DLRZGLY)) {
//            return data;
//        }
//        data.setCode(-1);
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//        pageSize = pageSize == 0 ? 1000 : pageSize;
//
//        int userId = NumberUtils.toInt(request.getParameter("userId"));
//        if (userId == 0) {
//            data.setMsg("请选择用户");
//            return data;
//        }
//        String beginTime = StringUtils.trimToEmpty(request.getParameter("beginTime"));
//        String endTime = StringUtils.trimToEmpty(request.getParameter("endTime"));
//
//        Map<String, Object> paramMap = new HashMap<>();
//        if (userId > 0) {
//            paramMap.put("userId", userId);
//        }
//        if (StringUtils.isNotBlank(beginTime)) {
//            paramMap.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(endTime)) {
//            paramMap.put("endTime", endTime + " 23:59:59");
//        }
//
//        Pager<UcUserLoginLog> pager = ucUserLoginLogService.findLoginIpStatList(paramMap, currentPage, pageSize);
//        data.setCode(200);
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping(value = "/suggest", method = RequestMethod.GET)
//    public ResponseData suggest(HttpServletRequest request, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        if (StringUtils.isBlank(name)) {
//            return data;
//        }
//
//        data.setCode(200);
//        data.setData(ucUserService.findSuggest(name, 20));
//        return data;
//    }
//
//    private Map<String, Object> getConditionMap(HttpServletRequest request, LoginUser loginUser) {
//        // 城市id
//        int cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("cityIdStr")));
//        String realName = StringUtils.trimToEmpty(request.getParameter("realName"));
//
//        int isAcceptOrder = NumberUtils.toInt(request.getParameter("isAcceptOrder"), -1);
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("needFollow", "needFollow");  // 是否查询跟进状态
//        condition.put("currentUserId", loginUser.getUcUser().getPkid());// 当前登陆者
//        condition.put("roleTagList", new ArrayList<String>() {{
//            add(RoleTag.ROLE_YWY);
//            add(RoleTag.ROLE_DDCZ);
//        }});
//
//        if (cityId > 0) {
//            condition.put("cityId", cityId);
//        }
//
//        if (StringUtils.isNotBlank(realName)) {
//            condition.put("realName", realName);
//        }
//
//        if (isAcceptOrder > -1) {
//            condition.put("isAcceptOrder", isAcceptOrder);
//        }
//        return condition;
//    }
//
//
//    /**
//     * 清空登录次数
//     *
//     * @param request
//     */
//    private void removeCaptchaTimes(HttpServletRequest request) {
//        cacheService.delete(ConstantCache.LOGIN_CAPTCHA_TIMES + WebUtils.getIpAddr(request));
//    }
//
//    /**
//     * 获取登录次数
//     *
//     * @param request
//     * @return
//     */
//    private int getCaptchaTimes(HttpServletRequest request) {
//        return NumberUtils.toInt(cacheService.get(ConstantCache.LOGIN_CAPTCHA_TIMES + WebUtils.getIpAddr(request)), 1);
//    }
//
//    /**
//     * 自增登录次数
//     *
//     * @param request
//     */
//    private void incrCaptchaTimes(HttpServletRequest request) {
//        String ip = WebUtils.getIpAddr(request);
//        int times = NumberUtils.toInt(cacheService.get(ConstantCache.LOGIN_CAPTCHA_TIMES + ip));
//        times++;
//        cacheService.put(ConstantCache.LOGIN_CAPTCHA_TIMES + ip, times, ConstantCache.TIME_ONE_DAY);
//    }
//
//    /**
//     * 获取key使用次数
//     *
//     * @param key
//     * @return
//     */
//    private int getUseTimes(String key) {
//        return NumberUtils.toInt(cacheService.get(key), 1);
//    }
//
//    /**
//     * 自增key使用次数
//     *
//     * @param key
//     */
//    private void incrUseTimes(String key) {
//        int times = NumberUtils.toInt(cacheService.get(key));
//        times++;
//        cacheService.put(key, times, ConstantCache.TIME_ONE_DAY);
//    }
//
//    /**
//     * 清空key使用次数
//     *
//     * @param key
//     */
//    private void removeUseTimes(String key) {
//        cacheService.delete(key);
//    }
//
//
////    @RequestMapping(value = "/data", method = RequestMethod.GET)
////    public ResponseData data(HttpServletRequest request, LoginUser loginUser) {
////        ResponseData data = new ResponseData();
////        new Thread(){
////            @Override
////            public void run() {
////                List<String> ips = ucUserLoginLogService.findIps();
////                if (CollectionUtils.isEmpty(ips)) {
////                    return;
////                }
////                for (String ip : ips) {
////                    try {
////                        String ipApiUrl = PropertiesReader.getValue("project", "ip_area");
////                        String json = HttpClientUtil.httpGet(ipApiUrl.replace("{ip}", ip), "utf-8");
////                        IpResultObj ipResultObj = JsonUtils.jsonToObject(json, IpResultObj.class);
////                        String ipAddr = ipResultObj.getIpAddr();
////                        ucUserLoginLogService.updateIpAddr(ip, ipAddr);
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        }.start();
////        return data;
////    }
}