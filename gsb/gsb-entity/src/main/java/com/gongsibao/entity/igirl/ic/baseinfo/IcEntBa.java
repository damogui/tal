package com.gongsibao.entity.igirl.ic.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

@Table(name = "ic_ent_ba",header = "补充信息")
public class IcEntBa {
    /*
    * "baNotice": { //公安机关公章刻制备案
                    "nameseal": "1", //复选，法定名称章,1勾选，0不选
                    "financeseal": "1", //复选，财务专用章,1勾选，0不选
                    "invoiceseal": "1", //复选，发票专用章,1勾选，0不选
                    "contractseal": "1", //复选，合同专用章,1勾选，0不选
                    "area": "朝阳区", //刻章区
                    "name": "XXXXX印章服务部" //刻章单位名称
                },


                entba
fundBa
crjjyjyjbjqyba
    * */

    @Column(name = "baNotice_id",header = "公安机关公章刻制备案Id")
    private Integer baNoticeId;

    @Reference(foreignKey = "baNoticeId",header = "公安机关公章刻制备案")
    private IcBaNotice baNotice;

    @Column(name = "fundBa_id",header = "公积金备案Id")
    private Integer fundBaId;

    @Reference(foreignKey = "fundBaId",header = "公积金备案")
    private IcFundBa fundBa;

    @Column(name = "fundBa_id",header = "出入境检验检疫备案Id")
    private Integer crjjyjyjbjqybaId;

    @Reference(foreignKey = "fundBaId",header = "出入境检验检疫备案")
    private IcCrjjyjyjbjqyba crjjyjyjbjqyba;

    public Integer getBaNoticeId() {
        return baNoticeId;
    }

    public void setBaNoticeId(Integer baNoticeId) {
        this.baNoticeId = baNoticeId;
    }

    public IcBaNotice getBaNotice() {
        return baNotice;
    }

    public void setBaNotice(IcBaNotice baNotice) {
        this.baNotice = baNotice;
    }

    public Integer getFundBaId() {
        return fundBaId;
    }

    public void setFundBaId(Integer fundBaId) {
        this.fundBaId = fundBaId;
    }

    public IcFundBa getFundBa() {
        return fundBa;
    }

    public void setFundBa(IcFundBa fundBa) {
        this.fundBa = fundBa;
    }

    public Integer getCrjjyjyjbjqybaId() {
        return crjjyjyjbjqybaId;
    }

    public void setCrjjyjyjbjqybaId(Integer crjjyjyjbjqybaId) {
        this.crjjyjyjbjqybaId = crjjyjyjbjqybaId;
    }

    public IcCrjjyjyjbjqyba getCrjjyjyjbjqyba() {
        return crjjyjyjbjqyba;
    }

    public void setCrjjyjyjbjqyba(IcCrjjyjyjbjqyba crjjyjyjbjqyba) {
        this.crjjyjyjbjqyba = crjjyjyjbjqyba;
    }
}
