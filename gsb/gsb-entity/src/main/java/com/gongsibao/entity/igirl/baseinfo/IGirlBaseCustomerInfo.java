package com.gongsibao.entity.igirl.baseinfo;

import com.gongsibao.entity.igirl.dict.IGirlServiceType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_base_customer_info",header = "基础客户信息")
public class IGirlBaseCustomerInfo extends Entity{

    @Column(name = "name",header = "客户姓名")
    private String name;

    @Column(name = "phone",header = "电话号码")
    private String phone;

    @Column(name = "service_type",header = "服务类型")
    private IGirlServiceType serviceType;

    @Column(name = "owner_id",header = "客户所属人ID")
    private Integer ownerId;

    @Column(name = "owner_name",header = "客户所属人名称")
    private String ownerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public IGirlServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(IGirlServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
