package com.gongsibao.rest.web.controller.v1.user;

import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.rest.web.common.util.Assert;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.web.Pager;
import com.gongsibao.rest.web.common.web.Result;
import com.gongsibao.rest.web.controller.BaseController;
import com.gongsibao.rest.web.dto.order.OrderDTO;
import com.gongsibao.rest.web.dto.order.OrderProdTraceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 我的订单
 * @date 2018/4/20 10:40
 */
@RestController
@RequestMapping("/wx/{v}/user/order")
@Api(1)
public class UserOrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    /**
     * 查询全部
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager<OrderDTO>>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result<Pager<OrderDTO>> all(HttpServletRequest request,
                                       @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return Result.build(() -> {
            return orderService.pageMyOrder(accountIdByOpenId(request),0, currentPage, pageSize);
        });
    }

    /**
     * 待付款
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager<OrderDTO>>
     */
    @RequestMapping(value = "/awaitPayment", method = RequestMethod.GET)
    public Result<Pager<OrderDTO>> awaitPayment(HttpServletRequest request,
                                                @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        return Result.build(() -> {
            return orderService.pageMyOrder(accountIdByOpenId(request),1, currentPage, pageSize);
        });
    }

    /**
     * 进行中
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager<OrderDTO>>
     */
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public Result<Pager<OrderDTO>> processing(HttpServletRequest request,
                                              @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                              @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return Result.build(() -> {
            return orderService.pageMyOrder(accountIdByOpenId(request), 2, currentPage, pageSize);
        });
    }

    /**
     * 已完成
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager<OrderDTO>>
     */
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public Result<Pager<OrderDTO>> done(HttpServletRequest request,
                                        @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return Result.build(() -> {
            return orderService.pageMyOrder(accountIdByOpenId(request), 3, currentPage, pageSize);
        });
    }

    /**
     * 查看进度
     *
     * @param request HttpServletRequest
     * @param orderProdIdStr 订单下产品ID加密串
     * @return
     */
    @RequestMapping(value = "/trace", method = RequestMethod.GET)
    public Result<List<OrderProdTraceDTO>> trace(HttpServletRequest request, @RequestParam("orderprodidstr") String orderProdIdStr) {
        return Result.build(() -> {
            Assert.hasText(orderProdIdStr,"产品订单信息错误");
            Integer orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdIdStr));
            return null;
        });
    }
}
