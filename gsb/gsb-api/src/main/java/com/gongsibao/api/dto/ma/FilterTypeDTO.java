package com.gongsibao.api.dto.ma;

import java.util.ArrayList;

/**
 * Created by win on 2018/2/2.
 */
/*筛选条件的DTO*/
public class FilterTypeDTO {
    private int filterType;//类型id

    private String filterName;//名称
    private ArrayList<ServiceDic> filterList;//包括集合


    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public ArrayList<ServiceDic> getFilterList() {
        return filterList;
    }

    public void setFilterList(ArrayList<ServiceDic> filterList) {
        this.filterList = filterList;
    }
}
