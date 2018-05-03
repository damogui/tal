package com.gongsibao.rest.controller.v1.order;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderProcessStatusType;
import com.netsharp.rest.base.order.IOrderService;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.controller.exception.WxException;
import com.netsharp.rest.service.user.AccountService;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.controller.annotation.LoginCheck;
import com.netsharp.rest.controller.security.SecurityUtils;
import com.netsharp.rest.utils.NumberUtils;
import com.netsharp.rest.controller.result.RestResult;
import com.netsharp.rest.dto.order.OrderDTO;
import com.netsharp.rest.dto.order.OrderMessageDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: OrderController
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/21 12:56
 */
@RestController
@RequestMapping("/wx/{v}/order")
@ApiVersion(1)
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/test")
    public Map<String, Object> test(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("originalId", originalId(request));
        map.put("openId", openId(request));
        map.put("account", accountService.queryByOpenId(openId(request)));
        return map;
    }


    /* *
     * @Description: 获取订单进度详情
     * @param  []
     * @return com.netsharp.rest.common.web.ResponseData
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/21
     */
    @RequestMapping("/messageInfo")
    public OrderMessageDTO lstService(HttpServletRequest request) {
        int orderProdId = NumberUtils.toInt(request.getParameter("orderProdId"));
        OrderMessageDTO dto = orderService.getOrderMessage(orderProdId);
        if (null == dto) {
            throw new WxException(RestResult.FAIL, "订单不存在");
        }
        return dto;
    }

    /**
     * @param request
     * @return com.netsharp.rest.common.web.ResponseData
     * @Description: 支付成功后查询订单信息
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/24
     */
    @RequestMapping("/info")
    @LoginCheck
    public OrderDTO info(HttpServletRequest request) {
        int orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderIdStr")));
        if (orderId == 0) {
            throw new WxException(RestResult.FAIL, "订单不存在");
        }

        SoOrder order = orderService.getById(orderId);
        if (null == order) {
            throw new WxException(RestResult.FAIL, "订单不存在");
        }



        OrderDTO orderDTO = new OrderDTO();
        {
            orderDTO.setPkid(order.getId());
            orderDTO.setNo(order.getNo());
            orderDTO.setAddTime(order.getCreateTime());
            orderDTO.setAdd_time(order.getCreateTime());
            String[] proNames=order.getProdName().split("，");
            StringBuffer nameStr=new StringBuffer();{
                for(String name:proNames){nameStr.append(name).append(" br ");}
            }
            orderDTO.setProdName(nameStr.toString());
            orderDTO.setProcessStatusId(order.getProcessStatus().getValue());
            if(order.getIsDelete()){
                orderDTO.setProcessStatusId(OrderProcessStatusType.Yqx.getValue());
            }
            orderDTO.setPayStatusId(order.getPayStatus().getValue());
            orderDTO.setPayablePrice(order.getPayablePrice());
            orderDTO.setPaidPrice(order.getPaidPrice());
            orderDTO.setIsChangePrice(BooleanUtils.toInteger(order.getIsChangePrice(), 1, 0));
            orderDTO.setChangePriceAuditStatusId(order.getChangePriceAuditStatus().getValue());
            orderDTO.setType(order.getType().getValue());
            orderDTO.setIsInstallment(BooleanUtils.toInteger(order.getIsInstallment(), 1, 0));
            orderDTO.setInstallmentAuditStatusId(order.getInstallmentAuditStatusId().getValue());
        }
        return orderDTO;
    }
}
