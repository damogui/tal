package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_receiver",header="收款方")
public class Receiver extends BaseEntity {
	
	private static final long serialVersionUID = 5946395941667874307L;
	
	@Column(header="收款方")
    private String receiver;
	
    @Column(header="成本类型")
    private String type;

    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}