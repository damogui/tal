package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpBoolean;
import com.gongsibao.entity.igirl.ic.dict.CorpLicenceType;
import com.gongsibao.entity.igirl.ic.dict.CorpPartyBuildMethod;
import com.gongsibao.entity.igirl.ic.dict.CorpPartySystem;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_extra_info",header = "补充信息")
public class IcExtraInfo extends Entity {
    @Column(name = "ent_which_type1",header = "总部企业")
    private Integer entWhichType1;

    @Column(name = "ent_which_type2",header = "研发中心")
    private Integer entWhichType2;

    @Column(name = "ent_which_type3",header = "投资人为上年度世界500强企业")
    private Integer entWhichType3;

    @Column(name = "ent_which_type4",header = "其他")
    private Integer entWhichType4;

    @Column(name = "has_bj_or_army_ivtr",header = "投资人是否有中央单位")
    private CorpBoolean hasBjOrArmyIvtr;

    @Column(name = "has_party",header = "建立党组织")
    private CorpBoolean hasParty;

    @Column(name = "par_ins",header = "党委")
    private CorpPartySystem parIns;

    @Column(name = "par_org_w",header = "单独组建")
    private CorpPartyBuildMethod parOrgW;

    @Column(name = "an_org_par_sign",header = "党组织是否本年度组建")
    private CorpBoolean anOrgParSign;

    @Column(name = "res_par_sec_sign",header = "法定代表人")
    private CorpBoolean resParSecSign;

    @Column(name = "has_league",header = "建立团组织")
    private CorpBoolean hasLeague;

    @Column(name = "league_number",header = "团员人数")
    private CorpBoolean leagueNumber;

    @Column(name = "has_union",header = "建立工会组织")
    private CorpBoolean hasUnion;

    @Column(name = "union_number",header = "工会人数")
    private CorpBoolean unionNumber;

    @Column(name = "members",header = "从业人数")
    private CorpBoolean members;

    @Column(name = "incity_number",header = "本市人数")
    private CorpBoolean incityNumber;

    @Column(name = "other_city_number",header = "外地人数")
    private CorpBoolean othercityNumber;

    @Column(name = "female_number",header = "女性人数")
    private CorpBoolean femaleNumber;

    @Column(name = "laidoff_number",header = "下岗失业人数")
    private CorpBoolean laidoffNumber;

    @Column(name = "has_new_gra_ivtr",header = "投资人中是否有本年度应届高校毕业生")
    private CorpBoolean hasNewGraIvtr;

    @Column(name = "has_new_gra_ivtr_bj",header = "该毕业生是否为在京生源")
    private CorpBoolean hasNewGraIvtrBj;

    public Integer getEntWhichType1() {
        return entWhichType1;
    }

    public void setEntWhichType1(Integer entWhichType1) {
        this.entWhichType1 = entWhichType1;
    }

    public Integer getEntWhichType2() {
        return entWhichType2;
    }

    public void setEntWhichType2(Integer entWhichType2) {
        this.entWhichType2 = entWhichType2;
    }

    public Integer getEntWhichType3() {
        return entWhichType3;
    }

    public void setEntWhichType3(Integer entWhichType3) {
        this.entWhichType3 = entWhichType3;
    }

    public Integer getEntWhichType4() {
        return entWhichType4;
    }

    public void setEntWhichType4(Integer entWhichType4) {
        this.entWhichType4 = entWhichType4;
    }

    public CorpBoolean getHasBjOrArmyIvtr() {
        return hasBjOrArmyIvtr;
    }

    public void setHasBjOrArmyIvtr(CorpBoolean hasBjOrArmyIvtr) {
        this.hasBjOrArmyIvtr = hasBjOrArmyIvtr;
    }

    public CorpBoolean getHasParty() {
        return hasParty;
    }

    public void setHasParty(CorpBoolean hasParty) {
        this.hasParty = hasParty;
    }

    public CorpPartySystem getParIns() {
        return parIns;
    }

    public void setParIns(CorpPartySystem parIns) {
        this.parIns = parIns;
    }

    public CorpPartyBuildMethod getParOrgW() {
        return parOrgW;
    }

    public void setParOrgW(CorpPartyBuildMethod parOrgW) {
        this.parOrgW = parOrgW;
    }

    public CorpBoolean getAnOrgParSign() {
        return anOrgParSign;
    }

    public void setAnOrgParSign(CorpBoolean anOrgParSign) {
        this.anOrgParSign = anOrgParSign;
    }

    public CorpBoolean getResParSecSign() {
        return resParSecSign;
    }

    public void setResParSecSign(CorpBoolean resParSecSign) {
        this.resParSecSign = resParSecSign;
    }

    public CorpBoolean getHasLeague() {
        return hasLeague;
    }

    public void setHasLeague(CorpBoolean hasLeague) {
        this.hasLeague = hasLeague;
    }

    public CorpBoolean getLeagueNumber() {
        return leagueNumber;
    }

    public void setLeagueNumber(CorpBoolean leagueNumber) {
        this.leagueNumber = leagueNumber;
    }

    public CorpBoolean getHasUnion() {
        return hasUnion;
    }

    public void setHasUnion(CorpBoolean hasUnion) {
        this.hasUnion = hasUnion;
    }

    public CorpBoolean getUnionNumber() {
        return unionNumber;
    }

    public void setUnionNumber(CorpBoolean unionNumber) {
        this.unionNumber = unionNumber;
    }

    public CorpBoolean getMembers() {
        return members;
    }

    public void setMembers(CorpBoolean members) {
        this.members = members;
    }

    public CorpBoolean getIncityNumber() {
        return incityNumber;
    }

    public void setIncityNumber(CorpBoolean incityNumber) {
        this.incityNumber = incityNumber;
    }

    public CorpBoolean getOthercityNumber() {
        return othercityNumber;
    }

    public void setOthercityNumber(CorpBoolean othercityNumber) {
        this.othercityNumber = othercityNumber;
    }

    public CorpBoolean getFemaleNumber() {
        return femaleNumber;
    }

    public void setFemaleNumber(CorpBoolean femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    public CorpBoolean getLaidoffNumber() {
        return laidoffNumber;
    }

    public void setLaidoffNumber(CorpBoolean laidoffNumber) {
        this.laidoffNumber = laidoffNumber;
    }

    public CorpBoolean getHasNewGraIvtr() {
        return hasNewGraIvtr;
    }

    public void setHasNewGraIvtr(CorpBoolean hasNewGraIvtr) {
        this.hasNewGraIvtr = hasNewGraIvtr;
    }

    public CorpBoolean getHasNewGraIvtrBj() {
        return hasNewGraIvtrBj;
    }

    public void setHasNewGraIvtrBj(CorpBoolean hasNewGraIvtrBj) {
        this.hasNewGraIvtrBj = hasNewGraIvtrBj;
    }
}
