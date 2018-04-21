package com.gongsibao.rest.web.controller.v1.user;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.rest.web.dto.user.AccountDeliverAddressDTO;
import com.gongsibao.rest.base.user.IUserDeliverAddressService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.util.Assert;
import com.gongsibao.rest.web.common.web.Result;
import com.gongsibao.rest.web.controller.BaseController;
import com.gongsibao.rest.web.request.DeliverAddressRequest;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class UserDeliverController extends BaseController {

    @Autowired
    private IUserDeliverAddressService accountDeliverAddressService;
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
            return accountDeliverAddressService.queryDefault(accountIdByOpenId(request));
        });
    }

    /**
     * 获取收货地址信息
     *
     * @param request HttpServletRequest
     * @param pkId    收货地址ID
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<AccountDeliverAddressDTO> info(HttpServletRequest request, @RequestParam("pkid") Integer pkId) {
        return Result.build(() -> {
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
            return accountDeliverAddressService.queryList(accountIdByOpenId(request));
        });
    }

    /**
     * 保存 & 修改地址
     *
     * @return Result<Integer> 地址ID
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody DeliverAddressRequest addressRequest, HttpServletRequest request) {
        return Result.build(() -> {
            Integer accountId = accountIdByOpenId(request);
            Assert.hasText(addressRequest.getContacts(), "请填写收货人!");
            Assert.isTrue(addressRequest.getContacts().length() <= 20, "收货人不能超过20字!");
            Assert.hasText(addressRequest.getMobilePhone(), "请填写联系方式!");
            Assert.isTrue(addressRequest.getMobilePhone().length() <= 20, "联系方式不能超过20字!");
            Assert.notNull(addressRequest.getCityId(), "请填写省市区!");
            Assert.isTrue(addressRequest.getCityId() != 0, "请填写省市区!");
            Assert.hasText(addressRequest.getAddress(), "请填写详细地址!");
            Assert.isTrue(addressRequest.getAddress().length() <= 50, "详细地址不能超过50字!");
            addressRequest.setAccountId(accountId);
            return accountDeliverAddressService.saveUpdate(addressRequest);
        });
    }

    /**
     * 设置默认收货地址
     *
     * @return Result<String>
     */
    @RequestMapping(value = "/setDefault", method = RequestMethod.POST)
    public Result<String> setDefault(@RequestParam("pkid") Integer pkid, HttpServletRequest request) {
        return Result.build(() -> {
            accountDeliverAddressService.updateDefault(accountIdByOpenId(request),pkid);
            return "设置成功!";
        }).resetOkMsgFromData();
    }

    /**
     * 删除收货地址
     *
     * @param pkid 主键
     * @param request HttpServletRequest
     * @return Result<String>
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result<String> remove(@RequestParam("pkid") Integer pkid, HttpServletRequest request){
        return Result.build(()->{
            accountDeliverAddressService.remove(accountIdByOpenId(request),pkid);
           return "删除成功!";
        }).resetOkMsgFromData();
    }
}
