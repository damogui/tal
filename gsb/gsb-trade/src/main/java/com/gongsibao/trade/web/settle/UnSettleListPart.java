package com.gongsibao.trade.web.settle;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.trade.base.settle.ISettleService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

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
        filterList.add(" orderProd.soOrder.payablePrice <= orderProd.soOrder.paidPrice ");

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

        Result<Settle> result = null;
        try {
            result = settleService.saveSettle(idList);
        } catch (BusinessException e) {
            result = new Result<>(ResponseStatus.FAILED, e.getMessage());
        } catch (Exception e) {
            result = new Result<>(ResponseStatus.FAILED, "您的网络不稳定，请稍后再试。");
            e.printStackTrace();
        }
        return result;
    }
}
