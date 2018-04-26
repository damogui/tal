package com.gongsibao.panda.platform.trade.workspace.data;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INOrderStageService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.AmountUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangchao on 2018/4/2.
 */
public class ImportOrderStageData {
    ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);
    INOrderStageService orderStageService = ServiceFactory.create(INOrderStageService.class);

    @Test
    public void run() {
        //分期订单
        List<SoOrder> StageOrderList = getInstallmentOrder();
        for (SoOrder order : StageOrderList) {
            String installmentMode = order.getInstallmentMode();
            Double totalpercentage = 0d;
            try {
                List<NOrderStage> orderStageList = getOrderStageListByInstallmentMode(installmentMode, totalpercentage, order.getId());
                orderStageService.saves(orderStageList);
            } catch (Exception e) {
            }

        }
    }


    //region 私有方法
    private List<SoOrder> getInstallmentOrder() {
        Oql orderOql = new Oql();
        {
            orderOql.setType(SoOrder.class);
            orderOql.setSelects("pkid,is_installment,installment_mode,payable_price");
            orderOql.setFilter("is_installment = 1 AND installment_mode !='' AND pkid NOT IN(SELECT order_id FROM `so_order_stage`) ");
            orderOql.setOrderby("pkid asc");
        }
        //分期订单
        return soOrderService.queryList(orderOql);
    }

    private boolean IshasOrderStageByOrderId(Integer orderId) {
        int orderStageCount = 0;
        Oql orderStageOql = new Oql();
        {
            orderStageOql.setType(NOrderStage.class);
            orderStageOql.setSelects("*");
            orderStageOql.setFilter("order_id = ?");
            orderStageOql.getParameters().add("order_id", orderId, Types.INTEGER);
        }
        orderStageCount = orderStageService.queryCount(orderStageOql);
        return orderStageCount > 0;
    }

    private List<NOrderStage> getOrderStageListByInstallmentMode(String installmentMode, Double totalpercentage, Integer orderId) {
        List<NOrderStage> orderStageList = new ArrayList();
        if (StringManager.isNullOrEmpty(installmentMode)) return orderStageList;
        List<String> installmentModeList = Arrays.asList(installmentMode.split("\\|"));
        Integer totalPrice = getTotalPrice(installmentModeList);
        Integer index = 0;
        for (String installmentModeItem : installmentModeList) {
            NOrderStage orderStage = new NOrderStage();
            installmentModeItem = installmentModeItem.contains(".") ? installmentModeItem.split("\\.")[0] : installmentModeItem;
            Integer amount = NumberUtils.toInt(installmentModeItem);
            index++;
            //最后一个比例，就是1减去其他比例的和
            Double percentage = getPercentage(installmentModeList.size(), index, totalpercentage, amount, totalPrice);
            orderStage.setEntityState(EntityState.New);
            orderStage.setAmount(amount);
            orderStage.setOrderId(orderId);
            orderStage.setInstalmentIndex(index);
            orderStage.setPercentage(percentage * 100);
            orderStageList.add(orderStage);
            totalpercentage = totalpercentage + percentage;
        }

        return orderStageList;
    }

    private Double getPercentage(Integer tatalCount, Integer index, Double totalpercentage, Integer amount, Integer totalPrice) {
        Double res = 0d;
        if (totalPrice <= 0) {
            return res;
        }
        if (tatalCount <= index) {
            res = 1 - totalpercentage;
        } else {
            res = AmountUtils.div(amount, totalPrice);
        }
        return res;
    }

    private Integer getTotalPrice(List<String> installmentModeList) {
        Integer res = 0;

        if (CollectionUtils.isEmpty(installmentModeList)) {
            return 0;
        }

        for (String installmentMode : installmentModeList) {
            installmentMode = installmentMode.contains(".") ? installmentMode.split("\\.")[0] : installmentMode;
            Integer amount = NumberUtils.toInt(installmentMode);
            res = res + amount;
        }
        return res;
    }

    //regionend

}