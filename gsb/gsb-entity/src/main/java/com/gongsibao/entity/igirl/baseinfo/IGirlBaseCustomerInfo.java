package com.gongsibao.entity.igirl.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_base_customer_info",header = "基础客户信息")
public class IGirlBaseCustomerInfo extends Entity{

    @Column(name = "name",header = "客户姓名")
    private String name;

    @Column(name = "phone",header = "电话号码")
    private String phone;

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
}
