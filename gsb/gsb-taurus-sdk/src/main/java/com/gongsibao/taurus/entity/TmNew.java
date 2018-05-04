package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/3/1.
 */
public class TmNew implements IEntity {
    private static final long serialVersionUID = 4439460126093986286L;

    /* 商标注册号 */
    private String sbzch;
    /* 国际类别 */
    private String gjlb;
    /* 商标名称 */
    private String sbmc;
    /* 状态 */
    private String zt;
    /* 创建时间 */
    private String cjsj;
    /* 商标申请人 */
    private String sbsqr;
    /* 商标申请人中文地址 */
    private String sbsqrzwdz;
    /* 商标共有申请人 */
    private String sbgysqr;
    /* 商标申请人英文 */
    private String sbsrryw;
    /* 商标申请人英文地址 */
    private String sbsqrywdz;
    /* 初审公告期号 */
    private String csggh;
    /* 初审公告日期 */
    private String csggrq;
    /* 注册公告期号 */
    private String zcggh;
    /* 注册公告日期 */
    private String zcggrq;
    /* 专用权期限开始时间 */
    private String zyqqxkssj;
    /* 专用权期限结束时间 */
    private String zyqqxjssj;
    /* 代理事务所 */
    private String dlsws;
    /* 后期指定日期 */
    private String hqzdrq;
    /* 国际注册日期 */
    private String guzcrq;
    /* 优先权日期 */
    private String yxqrq;
    /* 商标图 目前格式base64 */
    private String logo;

    public String getSbzch() {
        return sbzch;
    }

    public void setSbzch(String sbzch) {
        this.sbzch = sbzch;
    }

    public String getGjlb() {
        return gjlb;
    }

    public void setGjlb(String gjlb) {
        this.gjlb = gjlb;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getSbsqr() {
        return sbsqr;
    }

    public void setSbsqr(String sbsqr) {
        this.sbsqr = sbsqr;
    }

    public String getSbsqrzwdz() {
        return sbsqrzwdz;
    }

    public void setSbsqrzwdz(String sbsqrzwdz) {
        this.sbsqrzwdz = sbsqrzwdz;
    }

    public String getSbgysqr() {
        return sbgysqr;
    }

    public void setSbgysqr(String sbgysqr) {
        this.sbgysqr = sbgysqr;
    }

    public String getSbsrryw() {
        return sbsrryw;
    }

    public void setSbsrryw(String sbsrryw) {
        this.sbsrryw = sbsrryw;
    }

    public String getSbsqrywdz() {
        return sbsqrywdz;
    }

    public void setSbsqrywdz(String sbsqrywdz) {
        this.sbsqrywdz = sbsqrywdz;
    }

    public String getCsggh() {
        return csggh;
    }

    public void setCsggh(String csggh) {
        this.csggh = csggh;
    }

    public String getCsggrq() {
        return csggrq;
    }

    public void setCsggrq(String csggrq) {
        this.csggrq = csggrq;
    }

    public String getZcggh() {
        return zcggh;
    }

    public void setZcggh(String zcggh) {
        this.zcggh = zcggh;
    }

    public String getZcggrq() {
        return zcggrq;
    }

    public void setZcggrq(String zcggrq) {
        this.zcggrq = zcggrq;
    }

    public String getZyqqxkssj() {
        return zyqqxkssj;
    }

    public void setZyqqxkssj(String zyqqxkssj) {
        this.zyqqxkssj = zyqqxkssj;
    }

    public String getZyqqxjssj() {
        return zyqqxjssj;
    }

    public void setZyqqxjssj(String zyqqxjssj) {
        this.zyqqxjssj = zyqqxjssj;
    }

    public String getDlsws() {
        return dlsws;
    }

    public void setDlsws(String dlsws) {
        this.dlsws = dlsws;
    }

    public String getHqzdrq() {
        return hqzdrq;
    }

    public void setHqzdrq(String hqzdrq) {
        this.hqzdrq = hqzdrq;
    }

    public String getGuzcrq() {
        return guzcrq;
    }

    public void setGuzcrq(String guzcrq) {
        this.guzcrq = guzcrq;
    }

    public String getYxqrq() {
        return yxqrq;
    }

    public void setYxqrq(String yxqrq) {
        this.yxqrq = yxqrq;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
