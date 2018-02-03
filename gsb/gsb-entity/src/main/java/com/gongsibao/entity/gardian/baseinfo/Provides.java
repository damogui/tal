package com.gongsibao.entity.gardian.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.Date;
import java.util.List;


@Table(name="gd_provides_list",header="服务列表")
public class Provides extends Entity {

    @Column(name = "name", header = "名称")
    private String name;

    @Column(name = "useage", header = "用途")
    private String useage;

    @Column(name = "occurrences", header = "预计并发数")
    private int occurrences;

    @Column(name = "domain_name", header = "使用域名")
    private String domainname;

    @Subs(foreignKey="providesId",header="服务环境",subType=ProvidesEnv.class)
    private List<ProvidesEnv> providesEnv;

    public Provides() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseage() {
        return useage;
    }

    public void setUseage(String useage) {
        this.useage = useage;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }



    public List<ProvidesEnv> getProvidesEnv() {
        return providesEnv;
    }

    public void setProvidesEnv(List<ProvidesEnv> providesEnv) {
        this.providesEnv = providesEnv;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }


//    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
//    private TradeMarkCase tradeMarkCase;

}

