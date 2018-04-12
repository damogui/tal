package com.gongsibao.rest.controller;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.web.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wx/{v}")
@Api(1)
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseData countInUse(Account ucAccount){
        ResponseData data = new ResponseData();
        try {
//            data.setData(); //0-金牛座，1-双子座，2-钉钉 等
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(500);
        }
        return data;
    }
}
