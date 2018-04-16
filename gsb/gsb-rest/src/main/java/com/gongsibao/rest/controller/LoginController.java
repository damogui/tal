package com.gongsibao.rest.controller;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.util.RedisClient;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.user.IAccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/wx/{v}")
@Api(1)
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    IAccountService accountService;
    @Autowired
    private RedisClient redisClient;
    /**
     * @Description:TODO 登录验证
     * @param  openId
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 19:17
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseData login(@RequestParam("openId") String openId){

        ResponseData data = new ResponseData();
        try {
            data.setData(accountService.login(openId)); //null 未绑定过手机
            if(accountService.login(openId)==null){
                data.setCode(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(500);
        }
        return data;
    }

    /**
     * @Description:TODO 发送验证码
     * @param  mobilePhone
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 19:17
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
    public ResponseData sendCode(@RequestParam("mobilePhone") String mobilePhone) {

        ResponseData data = new ResponseData();
        //手机号校验
        if (!checkMobilePhone(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号错误");
            return data;
        }
        //生成验证码并保存至缓存中;
        String code = RandomStringUtils.randomNumeric(6);
        redisClient.set(mobilePhone, code,60*15L);
        logger.info("code=" + code + "| mobilePhone : " + mobilePhone);
        //发送验证码至指定手机号
        data.setCode(200);
        data.setMsg("验证码已发送，请注意查收");
        //发送短信
        String smsString = "【公司宝】您的验证码为：" + code;
        new Thread() {
            @Override
            public void run() {
                accountService.sendSms(mobilePhone, smsString);
            }
        }.start();
        return data;
    }

    /**
     * @Description:TODO 账号绑定手机
     * @param  mobilePhone, openId, code
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 19:18
     */
    @RequestMapping(value = "/bandMobile", method = RequestMethod.GET)
    public ResponseData changeMobile(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("openId") String openId,
            @RequestParam("code") String code) {

        ResponseData data = new ResponseData();
        //手机号校验
        if (!checkMobilePhone(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号错误");
            return data;
        }
        //验证码校验
        if (null==redisClient.get(mobilePhone)||!redisClient.get(mobilePhone).equals(code)) {
            data.setCode(500);
            data.setMsg("验证码错误");
            return data;
        }
        //更新数据库记录
        accountService.updateAccount(mobilePhone,openId);
        data.setCode(200);
        data.setMsg("修改成功");
        return data;
    }

    //手机号校验
    private boolean checkMobilePhone(String mobilePhone) {
        if (StringUtils.isBlank(mobilePhone)) {
            return false;
        }
        return true;
    }
}
