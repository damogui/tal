package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_receiver")
public class Receiver extends BaseEntity {
	
	private static final long serialVersionUID = 5946395941667874307L;
	
	@Column(header="receiver")
    private String receiver;
    @Column(header="type")
    private String type;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

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
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}