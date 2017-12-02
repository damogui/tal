package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_cost_invoice_map",header="请款申请和成本关联关系")
public class CostInvoiceMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -831734029562333975L;
	
	@Column(name="invoice_id",header="发票申请id")
    private Integer invoiceId;
	
    @Column(name="cost_id",header="成本id")
    private Integer costId;

    public Integer getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public Integer getCostId() {
        return costId;
    }
    public void setCostId(Integer costId) {
        this.costId = costId;
    }
} 