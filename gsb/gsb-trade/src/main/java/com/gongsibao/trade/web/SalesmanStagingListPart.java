package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.SoOrder;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.core.Oql;

import java.util.List;

/**
 * Created by zhangchao on 2018/3/15.
 */
public class SalesmanStagingListPart extends SalesmanAllOrderListPart {


    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("soOrder.*,owner.{id,name},companyIntention.{pkid,name,full_name,company_name},stages.*");
        List<SoOrder> resList = (List<SoOrder>) super.doQuery(oql);

        for (SoOrder order : resList) {
            List<NOrderStage> stages = order.getStages();
            if (CollectionUtils.isNotEmpty(stages)) {
                order.setStageCreateTime(stages.get(0).getCreateTime());
                order.setStageCreator(stages.get(0).getCreator());
            }
        }
        
        return resList;
    }
}
