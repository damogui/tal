package com.netsharp.rest.controller.result;

import com.gongsibao.entity.bd.Dict;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BdCity implements Serializable {
    private static final long serialVersionUID = 7447938651449258944L;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public static BdCity getCity(Dict dict) {
        if (null == dict) {
            return null;
        }
        BdCity city = new BdCity();
        city.setName(dict.getName());
        city.setValue(String.valueOf(dict.getId()));
        city.setParent(String.valueOf(dict.getParentId()));
        return city;
    }

    public static List<BdCity> getCity(List<Dict> dictList) {
        List<BdCity> cityList = new ArrayList<>();
        if (CollectionUtils.isEmpty(dictList)) {
            return null;
        }
        for (Dict dict : dictList) {
            BdCity city = getCity(dict);
            if (null != city) {
                cityList.add(city);
            }
        }
        return cityList;
    }
}