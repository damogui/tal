package com.gongsibao.rest.controller.v1.user;

import com.netsharp.rest.base.order.IOrderProdTraceService;
import com.netsharp.rest.base.order.IOrderService;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.controller.security.SecurityUtils;
import com.netsharp.rest.utils.Assert;
import com.netsharp.rest.utils.NumberUtils;
import com.netsharp.rest.controller.result.Pager;
import com.netsharp.rest.dto.order.OrderDTO;
import com.netsharp.rest.dto.order.OrderProdTraceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 我的订单
 * @date 2018/4/20 10:40
 */
@RestController
@RequestMapping("/wx/{v}/user/order")
@ApiVersion(1)
public class UserOrderController extends BaseController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderProdTraceService orderProdTraceService;


    /**
     * 查询全部
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager               <               OrderDTO>>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Pager<OrderDTO> all(HttpServletRequest request,
                               @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                               @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.pageMyOrder(accountIdByOpenId(request), 0, currentPage, pageSize);
    }

    /**
     * 待付款
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager               <               OrderDTO>>
     */
    @RequestMapping(value = "/awaitPayment", method = RequestMethod.GET)
    public Pager<OrderDTO> awaitPayment(HttpServletRequest request,
                                        @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.pageMyOrder(accountIdByOpenId(request), 1, currentPage, pageSize);
    }

    /**
     * 进行中
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager               <               OrderDTO>>
     */
    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public Pager<OrderDTO> processing(HttpServletRequest request,
                                      @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.pageMyOrder(accountIdByOpenId(request), 2, currentPage, pageSize);
    }

    /**
     * 已完成
     *
     * @param request     HttpServletRequest
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @return Result<Pager               <               OrderDTO>>
     */
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public Pager<OrderDTO> done(HttpServletRequest request,
                                @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.pageMyOrder(accountIdByOpenId(request), 3, currentPage, pageSize);
    }

    /**
     * 查看进度
     *
     * @param request        HttpServletRequest
     * @param orderProdIdStr 订单下产品ID加密串
     * @return
     */
    @RequestMapping(value = "/trace", method = RequestMethod.GET)
    public List<OrderProdTraceDTO> trace(HttpServletRequest request, @RequestParam("orderprodidstr") String orderProdIdStr) {
        Assert.hasText(orderProdIdStr, "产品订单信息错误");
        Integer orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdIdStr));
        return orderProdTraceService.queryTraceByCondition(orderProdId, Arrays.asList(3151, 3153, 31501));
    }

    /**
     * 取消订单
     *
     * @param request    HttpServletRequest
     * @param orderIdStr 订单ID加密串
     * @return
     */
    @RequestMapping(value = "/cancel/{orderIdStr}", method = RequestMethod.POST)
    public String cancel(HttpServletRequest request, @PathVariable("orderIdStr") String orderIdStr) {
        Assert.hasText(orderIdStr, "订单ID不能为空!");
        orderService.updateToCancel(accountIdByOpenId(request), Integer.valueOf(SecurityUtils.rc4Decrypt
                (orderIdStr)));
        return "取消成功";
    }
}
