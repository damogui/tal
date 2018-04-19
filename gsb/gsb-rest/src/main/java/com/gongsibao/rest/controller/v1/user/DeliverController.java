package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.AccountDeliverAddress;
import com.gongsibao.entity.acount.AccountWeiXin;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.util.Assert;
import com.gongsibao.rest.common.web.Result;
import com.gongsibao.rest.controller.BaseController;
import com.gongsibao.rest.dto.user.AccountDeliverAddressDTO;
import com.gongsibao.rest.service.user.AccountDeliverAddressService;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 收货地址
 * @date 2018/4/18 16:34
 */
@RestController
@RequestMapping("/wx/{v}/account/deliver")
@Api(1)
public class DeliverController extends BaseController {

    @Autowired
    private AccountDeliverAddressService accountDeliverAddressService;
    private IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    /**
     * 获取默认收货地址
     *
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping(value = "/getDefault", method = RequestMethod.GET)
    public Result<AccountDeliverAddressDTO> getDefault(HttpServletRequest request) {
        return Result.build(() -> {
            String openId = openId(request);
            Assert.hasText(openId, "当前用户尚未绑定!");
            AccountWeiXin accountWeiXin = accountWeiXinService.queryByOpenId(openId);
            Assert.notNull(accountWeiXin, "当前用户尚未绑定!");
            Assert.notNull(accountWeiXin.getAccountId(), "获取用户信息失败!");
            return accountDeliverAddressService.queryDefault(accountWeiXin.getAccountId());
        });
    }

    /**
     * 获取收货地址信息
     *
     * @param request HttpServletRequest
     * @param pkId 收货地址ID
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<AccountDeliverAddressDTO> info(HttpServletRequest request, @RequestParam("pkid") Integer pkId){
        return Result.build(()->{
            Assert.hasText(openId(request), "当前用户尚未绑定!");//这里只是验证一下是否合法用户
            Assert.notNull(pkId, "获取收货地址失败!");
            return accountDeliverAddressService.byId(pkId);
        });

    }

    /**
     * 获取收货地址列表
     *
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<AccountDeliverAddressDTO>> list(HttpServletRequest request) {
        return Result.build(() -> {
            String openId = openId(request);
            Assert.hasText(openId, "当前用户尚未绑定!");
            AccountWeiXin accountWeiXin = accountWeiXinService.queryByOpenId(openId);
            Assert.notNull(accountWeiXin, "当前用户尚未绑定!");
            Assert.notNull(accountWeiXin.getAccountId(), "获取用户信息失败!");
            return accountDeliverAddressService.queryList(accountWeiXin.getAccountId());
        });
    }
}
