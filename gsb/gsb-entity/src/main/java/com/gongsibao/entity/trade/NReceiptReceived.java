package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_receipt_received", header = "应收订单表")
public class NReceiptReceived extends Entity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5802457239814483246L;
	@Column(name = "receipt_type", header = "收据类型")
    private  Integer receiptType;
    @Column(name = "screen_shot", header = "抓屏")
    private  Integer screenShot;
    @Column(name = "supplier_id", header = "供应商Id")
    private  Integer supplierId;

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public Integer getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(Integer screenShot) {
        this.screenShot = screenShot;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
