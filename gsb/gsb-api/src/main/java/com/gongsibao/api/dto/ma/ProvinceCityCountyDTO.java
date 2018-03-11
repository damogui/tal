package com.gongsibao.api.dto.ma;


/**
 *提供省市县接口
 */
public class ProvinceCityCountyDTO {

   /*名称*/
    private String name;
    /*代码*/
    private  int id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
