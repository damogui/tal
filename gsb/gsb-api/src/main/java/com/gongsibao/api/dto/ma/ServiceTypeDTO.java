package com.gongsibao.api.dto.ma;

import java.util.ArrayList;

/**
 * Created by win on 2018/2/2.
 */
/*服务类型DTO*/
public class ServiceTypeDTO {

    private int serviceType;//类型

    private String serviceName;//名称
    private ArrayList<ServiceDic> serviceList;//包括集合

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ArrayList<ServiceDic> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<ServiceDic> serviceList) {
        this.serviceList = serviceList;
    }
}


