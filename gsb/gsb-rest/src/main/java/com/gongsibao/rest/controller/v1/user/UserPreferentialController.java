package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.AccountWeiXin;
import com.gongsibao.rest.common.apiversion.Api;
import static com.gongsibao.rest.common.util.Assert.*;
import com.gongsibao.rest.common.web.Pager;
import com.gongsibao.rest.common.web.Result;
import com.gongsibao.rest.controller.BaseController;
import com.gongsibao.rest.dto.user.PreferentialCodeDTO;
import com.gongsibao.rest.dto.user.PreferentialUsageDTO;
import com.gongsibao.rest.service.user.UserPreferentialService;
import org.apache.commons.lang3.StringUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 我的 - 我的优惠券
 * @date 2018/4/17 11:40
 */
@RestController
@RequestMapping("/wx/{v}/account/preferential")
@Api(1)
public class UserPreferentialController extends BaseController {

    @Autowired
    private UserPreferentialService userPreferentialService;
    private IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    /**
     * 优惠券使用情况汇总
     *
     * @param openId 微信openId
     * @return Result<PreferentialUsageDTO>
     */
    @RequestMapping(value = "/usage", method = RequestMethod.GET)
    public Result<PreferentialUsageDTO> usage(@RequestParam("openId") String openId) {
        return Result.build(() -> {
            AccountWeiXin accountWeiXin = accountWeiXinService.queryByOpenId(openId);
            notNull(accountWeiXin, "账号未绑定");
            notNull(accountWeiXin.getAccountId(), "账号未绑定");
            return userPreferentialService.usage(accountWeiXin.getAccountId());
        });
    }

    /**
     * 我的优惠券列表
     *
     * @param openId 微信openId
     * @param status 优惠券状态
     * @return Result<PreferentialCodeDTO>
     */
    @RequestMapping(value = "/myList", method = RequestMethod.GET)
    public Result<Pager<PreferentialCodeDTO>> myList(@RequestParam("openId") String openId,
                                                     @RequestParam(value = "status") Integer status,
                                                     @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                                     @RequestParam(value = "pageSize", defaultValue = "0") int pageSize) {
        return Result.build(() -> {
            AccountWeiXin accountWeiXin = accountWeiXinService.queryByOpenId(openId);
            notNull(accountWeiXin, "账号未绑定");
            notNull(accountWeiXin.getAccountId(), "账号未绑定");
            return userPreferentialService.pageActiveByCondition(accountWeiXin.getAccountId(),
                    status, currentPage,pageSize);
        });
    }

    /**
     * 激活优惠券
     *
     * @param no 优惠卷编号
     * @return
     */
    @RequestMapping(value = "/active/{no}", method = RequestMethod.POST)
    public Result<String> active(HttpServletRequest request, @PathVariable("no") String no) {
        return Result.build(() -> {
            String openId = openId(request);
            hasText(openId, "尚未登录!");
            hasText(no, "请输入优惠码!");
            isTrue(StringUtils.trimToEmpty(no).length() <= 50, "您输入的优惠码过长!");
            AccountWeiXin accountWeiXin = accountWeiXinService.queryByOpenId(openId);
            notNull(accountWeiXin.getAccountId(), "账号未绑定");
            userPreferentialService.saveActive(accountWeiXin.getAccountId(), no);
            return "激活成功!";
        }).resetOkMsgFromData();
    }
}
