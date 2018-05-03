package com.gongsibao.api.dto.ma;

/**
 * Created by win on 2018/2/2.
 */

/*从枚举类型转换的键值对*/
public class ServiceDic {

    private String id;

    private String name;

    public   ServiceDic(String id,String name){
        this.id=id;
        this.name=name;
    }


    public   ServiceDic(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
