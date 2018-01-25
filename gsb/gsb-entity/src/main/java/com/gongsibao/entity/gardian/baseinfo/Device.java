package com.gongsibao.entity.gardian.baseinfo;
import com.gongsibao.entity.gardian.dict.DeviceStatus;
import com.gongsibao.entity.gardian.dict.DeviceType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.Date;


@Table(name="gd_device",header="设备列表")
public class Device extends Entity {

    @Column(name = "name", header = "名称")
    private String name;

    @Column(name = "code", header = "编号")
    private String code;

    @Column(name = "device_type", header = "设备类型")
    private DeviceType deviceType;

    @Column(name = "device_status", header = "设备状态")
    private DeviceStatus deviceStatus;

    @Column(name = "buy_date", header = "设备购入日期")
    private Date buydate;

    @Column(name = "life_months", header = "设备寿命")
    private int lifemonths=36;

    @Column(name = "alert_days", header = "设备预警天数")
    private int alertdays=30;

    @Column(name = "cost", header = "设备购置金额")
    private double cost;


    @Column(name = "hdd_date", header = "硬盘装机日期")
    private Date hdddate;

    @Column(name = "memory", header = "内存")
    private Integer memory = 1;

    @Column(name = "core", header = "CPU")
    private Integer core = 1;

    @Column(name = "hdd", header = "硬盘")
    private Integer hdd = 10;

    @Column(name = "cloud_hdd", header = "云盘")
    private Integer cloudhdd = 10;

    @Column(name = "private_ip", header = "内网ip")
    private String privateip;

    @Column(name = "public_ip", header = "公网IP")
    private String publicip;

    @Column(name = "purpose", header = "用途")
    private String purpose;

    @Column(name = "memo", header = "备注")
    private String memo;

    public Device() {
    }


//    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
//    private TradeMarkCase tradeMarkCase;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    public Integer getHdd() {
        return hdd;
    }

    public void setHdd(Integer hdd) {
        this.hdd = hdd;
    }

    public Integer getCloudhdd() {
        return cloudhdd;
    }

    public void setCloudhdd(Integer cloudhdd) {
        this.cloudhdd = cloudhdd;
    }

    public String getPrivateip() {
        return privateip;
    }

    public void setPrivateip(String privateip) {
        this.privateip = privateip;
    }

    public String getPublicip() {
        return publicip;
    }

    public void setPublicip(String publicip) {
        this.publicip = publicip;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public Date getHdddate() {
        return hdddate;
    }

    public void setHdddate(Date hdddate) {
        this.hdddate = hdddate;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }



    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAlertdays() {
        return alertdays;
    }

    public void setAlertdays(int alertdays) {
        this.alertdays = alertdays;
    }


    public int getLifemonths() {
        return lifemonths;
    }

    public void setLifemonths(int lifemonths) {
        this.lifemonths = lifemonths;
    }
}

