package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.web.WebResult;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/{v}/icompany/account/company")
@Api(1)
public class ICompanyUserCompanyController {
    IAccountCompanyService accountCompanyService= ServiceFactory.create(IAccountCompanyService.class);

    @RequestMapping(value = "/countInUse", method = RequestMethod.GET)
    public WebResult countInUse(Account ucAccount){
        WebResult data = new WebResult();
        try {
            data.setData(accountCompanyService.countByAccount(ucAccount.getId(), 1)); //0-金牛座，1-双子座，2-钉钉 等
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(500);
        }
        return data;
    }
}
