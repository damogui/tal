package com.gongsibao.entity.igirl.ic;


import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_member",header = "主要成员信息")
public class IcMember extends Entity{
    //noSet不设董事会,仅设立执行董事1人，yesSet设立董事会,董事会成员3-13人,其中董事长1人
    @Column(name = "executive",header = "是否设立董事会")
    private String executive;

    //董事
    private List<IcDirector> directors;

    //经理
    private List<IcManager> managers;

    @Column(name = "name",header = "法人名字")
    private String name;

    //supervisorNotSet:不设立监事会,监事人数1-2人     supervisorYesSet:设立监事会,监事人数至少为3人,其中设监事会主席1人
    @Column(name = "supervisor",header = "是否设立监事会")
    private String supervisor;

    //监事
    private List<IcConductor> conductors;

    public String getExecutive() {
        return executive;
    }

    public void setExecutive(String executive) {
        this.executive = executive;
    }

    public List<IcDirector> getDirectors() {
        return directors;
    }

    public void setDirectors(List<IcDirector> directors) {
        this.directors = directors;
    }

    public List<IcManager> getManagers() {
        return managers;
    }

    public void setManagers(List<IcManager> managers) {
        this.managers = managers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public List<IcConductor> getConductors() {
        return conductors;
    }

    public void setConductors(List<IcConductor> conductors) {
        this.conductors = conductors;
    }
}
