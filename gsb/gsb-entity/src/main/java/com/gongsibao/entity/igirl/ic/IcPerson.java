package com.gongsibao.entity.igirl.ic;

import com.gongsibao.entity.igirl.ic.baseinfo.AreaOne;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import com.gongsibao.entity.igirl.ic.dict.CorpNation;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_person",header = "股东信息")
public class IcPerson extends Entity {
    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "cer_no",header = "身份证号码")
    private String cerNo;

    @Column(name = "folk",header = "民族")
    private CorpNation folk;

    @Column(name = "area_one_id",header = "身份证登记住址,省")
    private Integer areaOneId;

    @Reference(foreignKey = "areaOneId", header = "省市大类")
    private AreaOne prov;

    @Column(name = "area_two_id",header = "县级地区ID")
    private Integer areaTwoId;

    @Reference(foreignKey = "areaTwoId",header = "县级地区")
    private AreaTwo city;

    @Column(name = "dom_other",header = "身份证登记住址街道")
    private String domOther;

    private List<IcCost> cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCerNo() {
        return cerNo;
    }

    public void setCerNo(String cerNo) {
        this.cerNo = cerNo;
    }

    public CorpNation getFolk() {
        return folk;
    }

    public void setFolk(CorpNation folk) {
        this.folk = folk;
    }

    public Integer getAreaOneId() {
        return areaOneId;
    }

    public void setAreaOneId(Integer areaOneId) {
        this.areaOneId = areaOneId;
    }

    public AreaOne getProv() {
        return prov;
    }

    public void setProv(AreaOne prov) {
        this.prov = prov;
    }

    public Integer getAreaTwoId() {
        return areaTwoId;
    }

    public void setAreaTwoId(Integer areaTwoId) {
        this.areaTwoId = areaTwoId;
    }

    public AreaTwo getCity() {
        return city;
    }

    public void setCity(AreaTwo city) {
        this.city = city;
    }

    public String getDomOther() {
        return domOther;
    }

    public void setDomOther(String domOther) {
        this.domOther = domOther;
    }

    public List<IcCost> getCost() {
        return cost;
    }

    public void setCost(List<IcCost> cost) {
        this.cost = cost;
    }
}
