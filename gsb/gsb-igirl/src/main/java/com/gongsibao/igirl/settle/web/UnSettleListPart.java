package com.gongsibao.igirl.settle.web;

import org.netsharp.panda.commerce.ListPart;

import java.util.ArrayList;
import java.util.List;

/**
 * 未结算订单列表
 */
public class UnSettleListPart extends ListPart {

    @Override
    protected String getExtraFilter() {
        List<String> list = new ArrayList<>();

        // 产品id过滤
        list.add(" orderProd.productId IN (1137, 1177, 1823, 1514) ");

        // 未结算订单
        return super.getExtraFilter();
    }

    public Object submitSettle(List<Integer> orderProdIds) {
        return null;
    }
}
