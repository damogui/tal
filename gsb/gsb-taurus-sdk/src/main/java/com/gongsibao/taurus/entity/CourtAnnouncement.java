package com.gongsibao.taurus.entity;

/**
 * Created by cxq on 2017/11/1.
 */
// 法院公告
public class CourtAnnouncement implements IEntity{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6200446647590122808L;

	private Long id;

    // 公示时间
    private String publishdate;

    // 分类
    private String bltntype;

    // 当事人
    private String party1;

    // 公告内容
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getBltntype() {
        return bltntype;
    }

    public void setBltntype(String bltntype) {
        this.bltntype = bltntype;
    }

    public String getParty1() {
        return party1;
    }

    public void setParty1(String party1) {
        this.party1 = party1;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
