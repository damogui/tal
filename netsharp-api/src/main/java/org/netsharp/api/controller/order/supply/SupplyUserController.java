package org.netsharp.api.controller.order.supply;

import javax.ws.rs.Path;

/**
 * Created by duan on 2016/7/3.
 */
@Path("/api/supply/user")
public class SupplyUserController {
//	
//    Log log = LogFactory.getLog(SupplyUserController.class);
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private CaptchaService captchaService;
//    @Autowired
//    private SmsService smsService;
//    @Autowired
//    private SupplerService supplerService;
//
//    @Autowired
//    private CacheService cacheService;
//
//    private String SMS_SUPPLY_KEY_PREV = "SMS_SUPPLY_KEY_PREV_";
//
//    /**
//     * 基本资料
//     */
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    public ResponseData userInfo(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Suppler suppler = supplerService.getSupplerInfo(loginUser.getUcUser().getPkid());
//        data.setData(suppler);
//        return data;
//    }
//
//    /**
//     * 修改资料
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ResponseData updateUserInfo(@RequestBody String json, HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Suppler suppler = JsonUtils.jsonToObject(json, Suppler.class, "yyyy-MM-dd");
//        if (suppler == null) {
//            data.setMsg("数据错误");
//            return data;
//        }
//        Suppler supplerInfo = supplerService.getById(suppler.getPkid());
//        if (supplerInfo == null) {
//            data.setMsg("无此用户");
//            return data;
//        }
//        if (suppler.getProductTypeId() == null || suppler.getProductTypeId().size() == 0) {
//            data.setMsg("请选择服务项");
//            return data;
//        }
//        int row = supplerService.update(suppler);
//        if (row == 1) {
//            data.setMsg("操作成功");
//        } else {
//            data.setMsg("操作失败");
//        }
//        return data;
//    }
//
//    /***
//     * 新用户
//     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public ResponseData createUserInfo(@RequestBody String json, HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) {
//        ResponseData data = new ResponseData();
//        Suppler suppler = JsonUtils.jsonToObject(json, Suppler.class, "yyyy-MM-dd");
//        if (suppler == null) {
//            data.setMsg("数据错误");
//            return data;
//        }
//        //验证码校验
//        if (!captchaService.validCaptchaText(suppler.getMobilePhone(), code)) {
//            data.setCode(500);
//            data.setMsg("验证码错误");
//            return data;
//        }
//        if (suppler.getProductTypeId() == null || suppler.getProductTypeId().size() == 0) {
//            data.setMsg("请选择服务项");
//            return data;
//        }
//        if (null == suppler.getUcUserId() || suppler.getUcUserId() == 0) {
//            data.setMsg("请先注册");
//            return data;
//        }
//        supplerService.insert(suppler);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 发送验证码
//     */
//    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
//    public ResponseData sendCode(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                                 @RequestParam("mobilePhone") String mobilePhone) {
//        ResponseData data = new ResponseData();
//        //手机号校验
//        if (!checkMobilePhone(mobilePhone)) {
//            data.setCode(500);
//            data.setMsg("手机号错误");
//            return data;
//        }
//        //生成验证码并保存至缓存中;
//        String code = RandomStringUtils.randomNumeric(6);
//        captchaService.setCaptchaText(mobilePhone, code);
//        log.info("code=" + code + "| mobilePhone : " + mobilePhone);
//        //发送验证码至指定手机号
//        data.setCode(200);
//        data.setMsg("验证码已发送，请注意查收");
//        //发送短信
//        Integer appId = 2;
//        String smsString = "【公司宝】您的验证码为：" + code;
//        new Thread() {
//            @Override
//            public void run() {
//                smsService.send(appId, mobilePhone, smsString);
//            }
//        }.start();
//        return data;
//    }
//
//    //手机号校验
//    private boolean checkMobilePhone(String mobilePhone) {
//        if (StringUtils.isBlank(mobilePhone)) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 修改手机号
//     */
//    @RequestMapping(value = "/changeMobile", method = RequestMethod.GET)
//    public ResponseData changeMobile(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                                     @RequestParam("mobilePhone") String mobilePhone, @RequestParam("code") String code) {
//        ResponseData data = new ResponseData();
//        UcUser ucUser = ucUserService.findUserInfoById(loginUser.getUcUser().getPkid());
//        //手机号校验
//        if (!checkMobilePhone(mobilePhone)) {
//            data.setCode(500);
//            data.setMsg("手机号错误");
//            return data;
//        }
//        //验证码校验
//        if (!captchaService.validCaptchaText(mobilePhone, code)) {
//            data.setCode(500);
//            data.setMsg("验证码错误");
//            return data;
//        }
//        //更新数据库记录
//        ucUser.setMobilePhone(mobilePhone);
//        ucUserService.updateMobilePhone(ucUser);
//        data.setCode(200);
//        data.setMsg("修改成功");
//        return data;
//    }
//
//    /**
//     * 修改密码
//     */
//    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
//    public ResponseData changePwd(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser,
//                                  @RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
//        ResponseData data = new ResponseData();
//        UcUser ucUser = ucUserService.findByLoginName(loginUser.getUcUser().getRealName());
//        //密码校验
//        if (!ucUser.getPasswd().equals(SecurityUtils.passwdMd5(oldPwd))) {
//            data.setCode(500);
//            data.setMsg("密码错误");
//            return data;
//        }
//        //新密码校验
//        if (StringUtils.isBlank(newPwd)) {
//            data.setCode(500);
//            data.setMsg("新密码不可为空");
//            return data;
//        }
//        //更新数据库记录
//        newPwd = SecurityUtils.passwdMd5(newPwd);
//        ucUser.setPasswd(newPwd);
//        ucUserService.updatePwd(ucUser);
//        data.setCode(200);
//        data.setMsg("修改成功");
//        return data;
//    }
//
//    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.GET)
//    public ResponseData sendSmsCode(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String mobilePhone = StringUtils.trimToEmpty(request.getParameter("mobilePhone"));
//        String captcha = StringUtils.trimToEmpty(request.getParameter("captcha"));
//        String randomKey = StringUtils.trimToEmpty(request.getParameter("randomKey"));
//
//        if (!checkMobilePhone(mobilePhone)) {
//            data.setCode(500);
//            data.setMsg("手机号错误");
//            return data;
//        }
//
//        if (!captchaService.validCaptchaText(randomKey, captcha)) {
//            data.setCode(500);
//            data.setMsg("验证码错误");
//            return data;
//        }
//
//        smsService.sendSmsValidCode(SMS_SUPPLY_KEY_PREV, mobilePhone, null);
////        String code = StringUtils.trimToEmpty(cacheService.get(SMS_SUPPLY_KEY_PREV + mobilePhone));
////        if (StringUtils.isBlank(code)) {
////            code = RandomStringUtils.randomNumeric(6);
////            cacheService.put(SMS_SUPPLY_KEY_PREV + mobilePhone, code, ConstantCache.TIME_HALF_HOUR);
////        } else {
////            cacheService.put(SMS_SUPPLY_KEY_PREV + mobilePhone, code, ConstantCache.TIME_HALF_HOUR);
////        }
////
////        log.info("===================================== SEND SMS ==============================================================");
////        int isSend = NumberUtils.toInt(cacheService.get(mobilePhone + "_time", Integer.class));
//////        isSend = 0;
////        if (isSend == 0) {
////            Integer appId = 2;
////            String smsString = "【公司宝】本次操作的验证码为：{code}，30分钟内有效。如非您本人操作，请忽略该短信。如需帮助，请拨打客服电话：4006-798-999。";
////            final String content = smsString.replace("{code}", code);
////            new Thread() {
////                @Override
////                public void run() {
////                    smsService.send(appId, mobilePhone, content);
////                }
////            }.start();
////            cacheService.put(mobilePhone + "_time", 1, ConstantCache.TIME_ONE_MINUTE);
////        }
////
////        log.info("code=" + code + "| mobilePhone : " + mobilePhone);
//        data.setCode(200);
//        data.setMsg("验证码已发送，请注意查收");
//        return data;
//    }
//
//    @RequestMapping(value = "/regist", method = RequestMethod.POST)
//    public ResponseData regist(HttpServletRequest request, HttpServletResponse response,
//                               @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        data.setCode(500);
//
//        String mobilePhone = StringUtils.trimToEmpty(param.get("mobilePhone"));
//        String code = StringUtils.trimToEmpty(param.get("code"));
//        String passwd = StringUtils.trimToEmpty(param.get("passwd"));
//        String passwdConfirm = StringUtils.trimToEmpty(param.get("passwdConfirm"));
//        int supplyType = NumberUtils.toInt(param.get("supplyType"));
//
//        if (RegexUtils.isNotPhone(mobilePhone)) {
//            data.setMsg("请输入正确手机号");
//            return data;
//        }
//        if (StringUtils.isBlank(passwd)) {
//            data.setMsg("请输入密码");
//            return data;
//        }
//        if (!StringUtils.equals(passwd, passwdConfirm)) {
//            data.setMsg("两次密码不一致");
//            return data;
//        }
//        if (!code.equals(StringUtils.trimToEmpty(cacheService.get(SMS_SUPPLY_KEY_PREV + mobilePhone)))) {
//            data.setMsg("验证码错误");
//            return data;
//        }
//
//        cacheService.delete(SMS_SUPPLY_KEY_PREV + mobilePhone);
//
//        UcUser ucUser = new UcUser();
//        ucUser.setMobilePhone(mobilePhone);
//        ucUser.setPasswd(SecurityUtils.passwdMd5(passwd));
//        ucUser.setTicket("");
//        ucUser.setRealName("");
//        ucUser.setEmail("");
//        ucUser.setQq("");
//        ucUser.setWeixin("");
//        ucUser.setSex(0);
//        ucUser.setAbilityId(1074);
//        ucUser.setPriorityId(1094);
//        ucUser.setIsInner(0);
//        ucUser.setHeadThumbFileUrl("");
//        ucUser.setHeadThumbFileId(0);
//        ucUser.setUserTypeId(1024);
//        ucUser.setIsEnabled(1);
//        ucUser.setAddUserId(0);
//        ucUser.setIsAcceptOrder(0);
//        ucUser.setOfficeId(49314);
//        ucUser.setRemark("");
//        ucUser.setSupplyStatus(SupplerStatus.INIT.getId());
//
//        Suppler suppler = new Suppler();
//        suppler.setMobilePhone(mobilePhone);
//        suppler.setIsEnable(1);
//        ucUser.setSupplyType(supplyType == 0 ? 1 : supplyType);
//
//        ucUser.setSuppler(suppler);
//
//        Integer pkid = ucUserService.saveSupplyUser(ucUser);
//        if (pkid > 0) {
//            LoginUser loginUser = ucUserService.findLoginUserReload(pkid);
//            if (loginUser != null) {
//                setLogin(request, response, loginUser);
//            }
//            data.setCode(200);
//            data.setData(SecurityUtils.rc4Encrypt(pkid));
//        } else if (pkid == -1) {
//            data.setMsg("手机号已注册");
//        } else {
//            data.setMsg("注册失败");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
//    public ResponseData saveInfo(@RequestBody Map<String, Object> param, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        data.setCode(500);
//
//        int ucUserId = loginUser.getUcUser().getPkid();
//
//        if (ucUserId == 0) {
//            data.setMsg("参数缺少ucUserIdStr");
//            return data;
//        }
//
//        List<Integer> productTypeIds = (List<Integer>) param.get("productTypeId");
//        if (CollectionUtils.isEmpty(productTypeIds)) {
//            data.setMsg("请选择服务项");
//            return data;
//        }
//
//        String name = StringUtils.trimToEmpty(param.get("name"));
//        int sex = NumberUtils.toInt(param.get("sex"));
//        String landLine = StringUtils.trimToEmpty(param.get("landLine"));
//        String qq = StringUtils.trimToEmpty(param.get("qq"));
//        String weixin = StringUtils.trimToEmpty(param.get("weixin"));
//        String email = StringUtils.trimToEmpty(param.get("email"));
//        String companyName = StringUtils.trimToEmpty(param.get("companyName"));
//
//        int headThumbFileId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("headThumbFileIdStr")));
//        int cityId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("cityIdStr")));
//        String cityName = StringUtils.trimToEmpty(param.get("cityName"));
//        String idCard = StringUtils.trimToEmpty(param.get("idCard"));
//        int idCardFrontFileId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("idCardFrontFileIdStr")));
//        int idCardBackFileId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(param.get("idCardBackFileIdStr")));
//        String license = StringUtils.trimToEmpty(param.get("license"));
//
//        String beginTime = StringUtils.trimToEmpty(param.get("beginTime"));
//        String preSales = StringUtils.trimToEmpty(param.get("preSales"));
//        String responseTime = StringUtils.trimToEmpty(param.get("responseTime"));
//        String declar = StringUtils.trimToEmpty(param.get("declar"));
//        String intro = StringUtils.trimToEmpty(param.get("intro"));
//        String receiveAddress = StringUtils.trimToEmpty(param.get("receiveAddress"));
//        String goodField = StringUtils.trimToEmpty(param.get("goodField"));
//        String serviceTag = StringUtils.trimToEmpty(param.get("serviceTag"));
//        String bankNo = StringUtils.trimToEmpty(param.get("bankNo"));
//        String bankType = StringUtils.trimToEmpty(param.get("bankType"));
//        String bankAddress = StringUtils.trimToEmpty(param.get("bankAddress"));
//        String bankAccount = StringUtils.trimToEmpty(param.get("bankAccount"));
//
//        List<Integer> qualifyFileIds = new ArrayList<>();
//        List<Map<String, Object>> qualifyFileParamList = (List<Map<String, Object>>) param.get("qualifyFileList");
//        if (CollectionUtils.isNotEmpty(qualifyFileParamList)) {
//            for (Map<String, Object> bdFileMap : qualifyFileParamList) {
//                int fileId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(bdFileMap.get("pkidStr")));
//                if (fileId > 0) {
//                    qualifyFileIds.add(fileId);
//                }
//            }
//        }
//
//        Suppler suppler = new Suppler();
//        suppler.setUcUserId(ucUserId);
//        suppler.setName(name);
//        suppler.setSex(sex);
//        suppler.setLandLine(landLine);
//        suppler.setQq(qq);
//        suppler.setWeixin(weixin);
//        suppler.setEmail(email);
//        suppler.setCompanyName(companyName);
//        suppler.setHeadThumbFileId(headThumbFileId);
//        suppler.setCityId(cityId);
//        suppler.setCityName(cityName);
//        suppler.setIdCard(idCard);
//
//        suppler.setIdCardFrontFileId(idCardFrontFileId);
//        suppler.setIdCardBackFileId(idCardBackFileId);
//        suppler.setLicense(license);
//        suppler.setBeginTime(RegexUtils.isDate(beginTime) ? DateUtils.strToDate(beginTime) : null);
//        suppler.setQualifyFileIds(qualifyFileIds);
//        suppler.setPreSales(preSales);
//        suppler.setResponseTime(responseTime);
//        suppler.setDeclar(declar);
//        suppler.setIntro(intro);
//        suppler.setReceiveAddress(receiveAddress);
//        suppler.setGoodField(goodField);
//        suppler.setServiceTag(serviceTag);
//        suppler.setBankNo(bankNo);
//        suppler.setBankType(bankType);
//        suppler.setBankAddress(bankAddress);
//        suppler.setBankAccount(bankAccount);
//
//        suppler.setProductTypeId(productTypeIds);
//
//        supplerService.saveInfo(suppler);
//        data.setCode(200);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    private void setLogin(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
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
//            cacheService.put(ConstantCache.LOGIN_KEY + ticket, loginUser);
//            WebUtils.setCookieCom(response, ConstantWeb.COOKIE_LOGIN_TICKET, ticket, ConstantCache.TIME_ONE_YEAR);
//        }
//    }
}
