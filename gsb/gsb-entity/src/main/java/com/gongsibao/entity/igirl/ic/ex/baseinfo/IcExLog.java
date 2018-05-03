package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_log",header = "商标状态日志表")
public class IcExLog extends Entity{


    @Column(name = "title",header = "标题")
    private String title;

    @Column(name = "content",header = "内容")
    private String content;

    @Column(name = "company_name",header = "公司名称")
    private String companyName;

    @Column(name = "corp_reg_state",header = "状态")
    private CorpRegStatue corpRegStatue;

    @Column(name = "state",header = "状态")
    private String state = CorpRegStatue.ACCEPTED.getText();

    @Column(name="ex_case_id",header = "工商案子id")
    private Integer excId;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public Integer getExcId() {
        return excId;
    }

    public void setExcId(Integer excId) {
        this.excId = excId;
    }

    public CorpRegStatue getCorpRegStatue() {
        return corpRegStatue;
    }

    public void setCorpRegStatue(CorpRegStatue corpRegStatue) {
        this.corpRegStatue = corpRegStatue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
