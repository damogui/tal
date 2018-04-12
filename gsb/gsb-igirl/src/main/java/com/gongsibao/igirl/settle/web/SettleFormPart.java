package com.gongsibao.igirl.settle.web;

import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.entity.igirl.settle.SettleOrder;
import com.gongsibao.igirl.settle.base.ISettleOrderService;
import com.gongsibao.igirl.settle.base.ISettleService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import java.util.List;

public class SettleFormPart extends FormPart {
    ISettleService settleService = ServiceFactory.create(ISettleService.class);

    ISettleOrderService settleOrderService = ServiceFactory.create(ISettleOrderService.class);

    public Settle settleDetail(Integer id) {
        if (null == id || id == 0) {
            return null;
        }

        return settleService.byId(id);
    }

    public List<SettleOrder> settleOrderList(Integer id) {
        if (null == id || id == 0) {
            return null;
        }

        return settleOrderService.bySettleId(id);
    }
}
