package com.gongsibao.taurus.entity;

import com.gongsibao.taurus.entity.IEntity;

import java.util.Date;

/**
 * Created by wk on 2018/2/2.
 */
public class TmInfo implements IEntity {
    private static final long serialVersionUID = 1741891906560227362L;
    /* 商标id */
    private long id;
    /* 商标注册号 */
    private String regNo;
    /* 分类号 */
    private int intCls;
    /* 商标名称 */
    private String tmName;
    /* 商标图片 */
    private String path;
    /* 申请日期 */
    private Date appDate;
    /* 申请人中文 */
    private String applicantCn;
    /* 申请人英文 */
    private String applicantEn;
    /* 申请人地址中文 */
    private String addressCn;
    /* 申请人地址英文 */
    private String addressEn;
    /* 共有申请人1 */
    private String applicantOther1;
    /* 共有申请人2 */
    private String applicantOther2;
    /* 初审公告期号 */
    private int announcemenIssue;
    /* 初审公告日期 */
    private Date announcementDate;
    /* 专用权期限开始日期 */
    private Date privateDateStart;
    /* 专用权期限结束日期 */
    private Date privateDateEnd;
    /* 0-普通商标1-特殊商标2-集体商标3-证明商标 */
    private int category;
    /* 代理人名称 */
    private String agent;
    /* 指定颜色 */
    private String color;
    /* 注册公告期号 */
    private int regIssue;
    /* 注册公告日期 */
    private Date regDate;
    /* 优先权日期 */
    private String yxqrq;
    /* 国际注册日期 */
    private String gjzcrq;
    /* 后期指定日期 */
    private String hqzdrq;
    /* 商标状态,1:有效；2:无效；3:待审；4:不定  5-未知状态 */
    private int status;

    /* 最后一次法律状态 */
    private String lastFlowCategoryName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public int getIntCls() {
        return intCls;
    }

    public void setIntCls(int intCls) {
        this.intCls = intCls;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getApplicantCn() {
        return applicantCn;
    }

    public void setApplicantCn(String applicantCn) {
        this.applicantCn = applicantCn;
    }

    public String getApplicantEn() {
        return applicantEn;
    }

    public void setApplicantEn(String applicantEn) {
        this.applicantEn = applicantEn;
    }

    public String getAddressCn() {
        return addressCn;
    }

    public void setAddressCn(String addressCn) {
        this.addressCn = addressCn;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public String getApplicantOther1() {
        return applicantOther1;
    }

    public void setApplicantOther1(String applicantOther1) {
        this.applicantOther1 = applicantOther1;
    }

    public String getApplicantOther2() {
        return applicantOther2;
    }

    public void setApplicantOther2(String applicantOther2) {
        this.applicantOther2 = applicantOther2;
    }

    public int getAnnouncemenIssue() {
        return announcemenIssue;
    }

    public void setAnnouncemenIssue(int announcemenIssue) {
        this.announcemenIssue = announcemenIssue;
    }

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(Date announcementDate) {
        this.announcementDate = announcementDate;
    }

    public Date getPrivateDateStart() {
        return privateDateStart;
    }

    public void setPrivateDateStart(Date privateDateStart) {
        this.privateDateStart = privateDateStart;
    }

    public Date getPrivateDateEnd() {
        return privateDateEnd;
    }

    public void setPrivateDateEnd(Date privateDateEnd) {
        this.privateDateEnd = privateDateEnd;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRegIssue() {
        return regIssue;
    }

    public void setRegIssue(int regIssue) {
        this.regIssue = regIssue;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getYxqrq() {
        return yxqrq;
    }

    public void setYxqrq(String yxqrq) {
        this.yxqrq = yxqrq;
    }

    public String getGjzcrq() {
        return gjzcrq;
    }

    public void setGjzcrq(String gjzcrq) {
        this.gjzcrq = gjzcrq;
    }

    public String getHqzdrq() {
        return hqzdrq;
    }

    public void setHqzdrq(String hqzdrq) {
        this.hqzdrq = hqzdrq;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastFlowCategoryName() {
        return lastFlowCategoryName;
    }

    public void setLastFlowCategoryName(String lastFlowCategoryName) {
        this.lastFlowCategoryName = lastFlowCategoryName;
    }
}
