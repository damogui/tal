package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_order_prod_duty_data", header = "订单产品优惠券")
public class NOrderProdDutyData  extends Entity {
    @Column(name = "data_name", header = "数据名称")
    private  String dataName;
    @Column(name = "data_template_id", header = "数据模板Id")
    private  String dataTemplateId;
    @Column(name = "data_type", header = "数据类型")
    private  Integer dataType;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataTemplateId() {
        return dataTemplateId;
    }

    public void setDataTemplateId(String dataTemplateId) {
        this.dataTemplateId = dataTemplateId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}
