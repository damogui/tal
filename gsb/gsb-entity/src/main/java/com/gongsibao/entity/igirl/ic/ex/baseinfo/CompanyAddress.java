package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_company_address",header = "企业地址")
public class CompanyAddress extends Entity {
    @Column(name = "city",header = "市")
    private String city = "北京市";

    @Column(name = "district",header = "区")
    private String district;

    @Column(name = "street",header = "街道")
    private String street;

    @Column(name = "no",header = "号")
    private String no;

    @Column(name = "floor",header = "层")
    private String floor;

    @Column(name = "room",header = "室")
    private String room;

    private String item = new StringBuffer(city)
            .append(district).append("区")
            .append(street).append("街道")
            .append(no).append("号")
            .append(floor).append("层")
            .append(room).append("室")
            .toString();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
