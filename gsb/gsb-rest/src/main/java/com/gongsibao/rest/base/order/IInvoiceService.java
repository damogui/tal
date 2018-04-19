package com.gongsibao.rest.base.order;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;

import java.util.List;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 发票服务
 * @date $ $
 */
public interface IInvoiceService {

    /**
     * @Description: 查询
     * @param
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/19
     */
    Invoice byId(int invoiceId);

    /**
     * @Description: 保存发票
     * @param
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/19
     */
    Invoice saveInvoice(Invoice invoice);

    /**
     * @Description:保存发票与订单关联关系
     * @param
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/19
     */
    void saveOrderInvoiceMaps(List<OrderInvoiceMap> orderInvoiceMapList);
}
