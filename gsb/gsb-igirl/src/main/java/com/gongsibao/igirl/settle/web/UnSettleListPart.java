package com.gongsibao.igirl.settle.web;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.igirl.settle.base.ISettleService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * 待结算订单列表
 */
public class UnSettleListPart extends ListPart {

    ISettleService settleService = ServiceFactory.create(ISettleService.class);
    @Override
    protected String getExtraFilter() {
        List<String> filterList = new ArrayList<>();

        // 产品id过滤, 目前只支持商标结算
        filterList.add(" orderProd.productId IN (1137, 1177, 1823, 1514) ");
        filterList.add(" orderProd.settleStatus = " + SettleStatus.NO_SETTLEMENT.getValue());

        // TODO 订单金额条件判断
        filterList.add(" supplierId = " + SupplierSessionManager.getSupplierId() + " ");
        // 结算状态
//        filterList.add(" orderProd.settleStatus = " + SettleStatus.NO_SETTLEMENT.getValue());
        return StringManager.join(" AND ", filterList);
    }

    public Result<Settle> submitSettle(String orderProdCaseStrIds) {
        if (StringManager.isNullOrEmpty(orderProdCaseStrIds)) {
            return new Result<>(ResponseStatus.FAILED, "请选择订单");
        }

        String[] idArr = orderProdCaseStrIds.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s : idArr) {
            int id = NumberUtils.toInt(s);
            if (id > 0) {
                idList.add(id);
            }
        }

        return settleService.saveSettle(idList);
    }
}
