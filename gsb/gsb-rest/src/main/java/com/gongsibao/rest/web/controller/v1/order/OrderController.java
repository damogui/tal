package com.gongsibao.rest.web.controller.v1.order;

import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.web.controller.BaseController;
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
        if (orderProdId == 0) {
            data.setCode(-1);
            data.setMsg("订单不存在");
            return data;
        }

        orderService.getOrderMessage(orderProdId);

        return data;

    }
}
