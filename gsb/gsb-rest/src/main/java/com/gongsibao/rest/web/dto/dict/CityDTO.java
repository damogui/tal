package com.gongsibao.rest.web.dto.dict;

import java.io.Serializable;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 省市区三级联动 DTO
 * @date 2018/4/19 10:29
 */
public class CityDTO implements Serializable{

    private String name;
    private String value;
    private String parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        if(value!=null){
            this.value = value.toString();

        }
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setParent(Integer parent) {
        if(parent!=null){
            this.parent = parent.toString();

        }
    }
}
