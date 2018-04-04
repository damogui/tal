package com.gongsibao.trade.service.action.order.invoice;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.InvoiceType;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.RegexUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.trade.Invoice;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.List;
import java.util.Map;

public class ActionApplyInvoiceVerify implements IAction {

    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);

    ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

    @Override
    public void execute(ActionContext ctx) {
        Invoice invoice = (Invoice) ctx.getItem();


        if (StringManager.isNullOrEmpty(invoice.getTitle())) {
            throw new BusinessException("发票抬头不能为空");
        }

        if (invoice.getTitleType() == null) {
            throw new BusinessException("抬头类型不能为空");
        }

        if (invoice.getCompanyId() == null || NumberUtils.toInt(invoice.getCompanyId().getValue()) == 0) {
            throw new BusinessException("开票公司不能为空");
        }

        if (invoice.getTypeId() == null) {
            throw new BusinessException("发票类型不能为空");
        }

        if (NumberUtils.toInt(invoice.getAmount()) == 0) {
            throw new BusinessException("发票金额不能为零");
        }
        if (StringManager.isNullOrEmpty(invoice.getContent())) {
            throw new BusinessException("发票内容不能为空");
        }
        if (StringManager.isNullOrEmpty(invoice.getVatTaxNo())) {
            throw new BusinessException("开票公司税号不能为空");
        }
        if (StringManager.isNullOrEmpty(invoice.getReceiverName())) {
            throw new BusinessException("发票接收人不能为空");
        }

        if (StringManager.isNullOrEmpty(invoice.getReceiverMobilePhone())) {
            throw new BusinessException("手机号码不能为空");
        }
        if (RegexUtils.isNotPhone(invoice.getReceiverMobilePhone())) {
            throw new BusinessException("手机号码格式错误");
        }

        if (StringManager.isNullOrEmpty(invoice.getReceiverEmail())) {
            throw new BusinessException("邮箱不能为空");
        }
        if (!RegexUtils.isEmail(invoice.getReceiverEmail())) {
            throw new BusinessException("邮箱格式错误");
        }

        if (invoice.getTypeId().equals(InvoiceType.Zzszy)) {
            if (StringManager.isNullOrEmpty(invoice.getVatAddress())) {
                throw new BusinessException("开票公司注册地址不能为空");
            }
            if (StringManager.isNullOrEmpty(invoice.getVatPhone())) {
                throw new BusinessException("开票公司注册电话不能为空");
            }
            if (StringManager.isNullOrEmpty(invoice.getVatBankName())) {
                throw new BusinessException("开户银行名称不能为空");
            }
            if (StringManager.isNullOrEmpty(invoice.getVatBankNo())) {
                throw new BusinessException("开户银行账号不能为空");
            }
        }

        Map<String, Object> statusMap = ctx.getStatus();
        Integer orderId = (Integer) statusMap.get("orderId");

        Oql oql = new Oql();
        {
            oql.setType(OrderInvoiceMap.class);
            oql.setSelects("*");
            oql.setFilter("order_id = ? and invoice_id in(select pkid from so_invoice where audit_status_id in(" + AuditStatusType.Dsh.getValue() + "," + AuditStatusType.Shtg.getValue() + "," + AuditStatusType.Shz.getValue() + "))");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }

        List<OrderInvoiceMap> orderInvoiceMapList = orderInvoiceMapService.queryList(oql);

        if (CollectionUtils.isNotEmpty(orderInvoiceMapList)) {
            throw new BusinessException("该订单已经存在发票了，禁止提交！");
        }

        SoOrder order = soOrderService.getByOrderId(orderId);
        if (invoice.getAmount() > (order.getPaidPrice() - NumberUtils.toInt(order.getRefundPrice()))) {
            throw new BusinessException("发票金额不能大于订单可开发票额！");
        }


    }

}
