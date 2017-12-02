package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_customer_message")
public class CustomerMessage extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5310826140972542053L;
	@Column(name="er_customer_id",header="ErCustomerId")
    private Integer erCustomerId;
    @Column(name="data_id",header="DataId")
    private Integer dataId;
    @Column(name="data_type",header="DataType")
    private Integer dataType;
    @Column(name="",header="content")
    private String content;
    @Column(name="",header="type")
    private Integer type;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

    public Integer getErCustomerId() {
        return erCustomerId;
    }
    public void setErCustomerId(Integer erCustomerId) {
        this.erCustomerId = erCustomerId;
    }
    public Integer getDataId() {
        return dataId;
    }
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }
    public Integer getDataType() {
        return dataType;
    }
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}