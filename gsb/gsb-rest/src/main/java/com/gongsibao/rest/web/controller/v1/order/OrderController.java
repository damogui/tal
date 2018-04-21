package com.gongsibao.rest.web.controller.v1.order;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.apiversion.LoginCheck;
import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.rest.web.common.util.JsonUtils;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.web.controller.BaseController;
import com.gongsibao.rest.web.dto.order.OrderMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: OrderController
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/21 12:56
 */
@RestController
@RequestMapping("/wx/{v}/order")
@Api(1)
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /* *
     * @Description: 获取订单进度详情
     * @param  []
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/21
     */
    @RequestMapping("/messageInfo")
    public ResponseData lstService(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        int orderProdId = NumberUtils.toInt(request.getParameter("orderProdId"));

        OrderMessageDTO dto = orderService.getOrderMessage(orderProdId);
        if (null == dto) {
            data.setCode(-1);
            data.setMsg("订单不存在");
            return data;
        }
        data.setData(dto);

        return data;
    }

    @RequestMapping("/info")
    @LoginCheck
    public ResponseData info(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        int orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderIdStr")));
        if (orderId == 0) {
            data.setCode(-1);
            data.setMsg("订单不存在");
            return data;
        }

        SoOrder order = orderService.getById(orderId);
        if (null == order) {
            data.setCode(-1);
            data.setMsg("订单不存在");
            return data;
        }

        data.setData(order);
        return data;
    }
}
