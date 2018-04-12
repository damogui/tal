package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpBoolean;
import com.gongsibao.entity.igirl.ic.dict.CorpJyMark;
import com.gongsibao.entity.igirl.ic.dict.CorpJyType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name = "ic_crjjyjyjbjqyba",header = "补充信息")
public class IcCrjjyjyjbjqyba {
    @Column(name = "qylb",header = "进境植物产品运输单位")
    private CorpJyType qylb;

    @Column(name = "eng_name",header = "英文名称")
    private String engname;

    @Column(name = "jycs",header = "经营场所")
    private String jycs;

    @Column(name = "jycsyzbm",header = "经营地邮政编码")
    private String jycsyzbm;

    @Column(name = "record_type",header = "报检标识")
    private CorpJyMark recordtype;

    @Column(name = "lep_fax",header = "法定代表人")
    private String lepfax;

    @Column(name = "is_inner",header = "是否从事进出口业务")
    private CorpBoolean isinner;

    @Column(name = "innerlicno",header = "进出口资格相关证明号码")
    private String innerlicno;

    @Column(name = "expresslicno",header = "快递业务经营许可证号")
    private String expresslicno;

    @Column(name = "lxr",header = "联系人姓名")
    private String lxr;

    @Column(name = "lxr_dh",header = "电话")
    private String lxrdh;

    @Column(name = "lxr_mobel",header = "手机号码")
    private String lxrmobel;

    @Column(name = "lxr_fax",header = "传真")
    private String lxrfax;

    @Column(name = "lxr_email",header = "电子邮箱")
    private String lxryemail;

    public CorpJyType getQylb() {
        return qylb;
    }

    public void setQylb(CorpJyType qylb) {
        this.qylb = qylb;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getJycs() {
        return jycs;
    }

    public void setJycs(String jycs) {
        this.jycs = jycs;
    }

    public String getJycsyzbm() {
        return jycsyzbm;
    }

    public void setJycsyzbm(String jycsyzbm) {
        this.jycsyzbm = jycsyzbm;
    }

    public CorpJyMark getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(CorpJyMark recordtype) {
        this.recordtype = recordtype;
    }

    public String getLepfax() {
        return lepfax;
    }

    public void setLepfax(String lepfax) {
        this.lepfax = lepfax;
    }

    public CorpBoolean getIsinner() {
        return isinner;
    }

    public void setIsinner(CorpBoolean isinner) {
        this.isinner = isinner;
    }

    public String getInnerlicno() {
        return innerlicno;
    }

    public void setInnerlicno(String innerlicno) {
        this.innerlicno = innerlicno;
    }

    public String getExpresslicno() {
        return expresslicno;
    }

    public void setExpresslicno(String expresslicno) {
        this.expresslicno = expresslicno;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxrdh() {
        return lxrdh;
    }

    public void setLxrdh(String lxrdh) {
        this.lxrdh = lxrdh;
    }

    public String getLxrmobel() {
        return lxrmobel;
    }

    public void setLxrmobel(String lxrmobel) {
        this.lxrmobel = lxrmobel;
    }

    public String getLxrfax() {
        return lxrfax;
    }

    public void setLxrfax(String lxrfax) {
        this.lxrfax = lxrfax;
    }

    public String getLxryemail() {
        return lxryemail;
    }

    public void setLxryemail(String lxryemail) {
        this.lxryemail = lxryemail;
    }
}
