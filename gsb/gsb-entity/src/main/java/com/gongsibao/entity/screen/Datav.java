package com.gongsibao.entity.screen;

import com.gongsibao.entity.BaseEntity;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import java.util.Date;

@Table(name = "screen_datav", header = "大屏数据")
public class Datav extends BaseEntity {

    private static final long serialVersionUID = -6815677495441322163L;

    @Column(name="screen_id",header="类型")
    private Integer screenId;

    @Column(name="desc",header="备注说明")
    private String desc;

    @Column(name="content",header="json内容")
    private String content;

    @Column(name="upd_time",header="修改时间")
    private Date updTime;

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
}
