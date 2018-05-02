package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.common.annotation.Api;
import static com.netsharp.rest.util.Assert.*;

import com.netsharp.rest.common.result.Result;
import com.netsharp.rest.dto.user.PreferentialCodeDTO;
import com.netsharp.rest.dto.user.PreferentialUsageDTO;
import com.gongsibao.rest.base.user.IUserPreferentialService;
import org.apache.commons.lang3.StringUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private IUserPreferentialService userPreferentialService;
    private IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    /**
     * 优惠券使用情况汇总
     *
     * @param request HttpServletRequest
     * @return Result<PreferentialUsageDTO>
     */
    @RequestMapping(value = "/usage", method = RequestMethod.GET)
    public Result<PreferentialUsageDTO> usage(HttpServletRequest request) {
        return Result.build(() -> {
            return userPreferentialService.usage(accountIdByOpenId(request));
        });
    }

    /**
     * 我的优惠券列表
     *
     * @param request HttpServletRequest
     * @param status 优惠券状态
     * @return Result<PreferentialCodeDTO>
     */
    @RequestMapping(value = "/myList", method = RequestMethod.GET)
    public Result<List<PreferentialCodeDTO>> myList(HttpServletRequest request,
                                                    @RequestParam(value = "status") Integer status) {
        return Result.build(() -> {
            return userPreferentialService.pageActiveByCondition(accountIdByOpenId(request),status);
        });
    }

    /**
     * 激活优惠券
     *
     * @param request HttpServletRequest
     * @param no      优惠卷编号
     * @return
     */
    @RequestMapping(value = "/active/{no}", method = RequestMethod.POST)
    public Result<String> active(HttpServletRequest request, @PathVariable("no") String no) {
        return Result.build(() -> {
            hasText(no, "请输入优惠码!");
            isTrue(StringUtils.trimToEmpty(no).length() <= 50, "您输入的优惠码过长!");
            userPreferentialService.saveActive(accountIdByOpenId(request), no);
            return "激活成功!";
        }).resetOkMsgFromData();
    }
}
