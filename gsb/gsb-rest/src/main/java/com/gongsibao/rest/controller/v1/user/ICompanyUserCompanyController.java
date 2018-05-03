package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.controller.exception.WxException;
import com.netsharp.rest.controller.security.SecurityUtils;
import com.netsharp.rest.controller.result.RestResult;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wx/{v}/icompany/account/company")
@ApiVersion(1)
public class ICompanyUserCompanyController {
    IAccountCompanyService accountCompanyService = ServiceFactory.create(IAccountCompanyService.class);

    @RequestMapping(value = "/countInUse", method = RequestMethod.GET)
    public int countInUse(Account ucAccount) {
        return accountCompanyService.countByAccount(ucAccount.getId(), 1);
    }

    @RequestMapping(value = "/choose", method = RequestMethod.GET)
    public void choose(HttpServletRequest request, Account ucAccount) {
        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("pkidStr")));
        AccountCompany ucAccountCompany = accountCompanyService.byId(pkid);
        if (null == ucAccountCompany) {
            throw new WxException(RestResult.FAIL, "公司不存在");
        }
        List<AccountCompany> list = accountCompanyService.findByAccount(ucAccount.getId(), 1);
        List<Integer> idList = list.stream().map(AccountCompany::getId).collect(Collectors.toList());
        if (!idList.contains(pkid)) {
            throw new WxException(RestResult.FAIL, "该公司未完成");
        }
    }
}
