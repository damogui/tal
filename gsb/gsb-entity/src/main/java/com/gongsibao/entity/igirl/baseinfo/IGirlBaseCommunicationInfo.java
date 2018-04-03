package com.gongsibao.entity.igirl.baseinfo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_base_communication_info",header = "客户交流信息")
public class IGirlBaseCommunicationInfo extends Entity{

    @Column(name = "message",header = "交流内容")
    private String message;

    @Column(name = "communication_id",header = "基础客户ID")
    private Integer communicationId;

    @JsonIgnore
    @Reference(foreignKey = "communicationId", header = "基础客户")
    private IGirlBaseCommunicationInfo communication;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(Integer communicationId) {
        this.communicationId = communicationId;
    }

    public IGirlBaseCommunicationInfo getCommunication() {
        return communication;
    }

    public void setCommunication(IGirlBaseCommunicationInfo communication) {
        this.communication = communication;
    }
}
