package com.gongsibao.stat.dto;

import java.io.Serializable;

public class StatCityCustomer implements Serializable {

    private static final long serialVersionUID = 6896783226856309905L;

    private int cityId;
    private String cityName;
    private int count;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(int num) {
        setCount(this.count + num);
    }
}
