package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_divination")
public class Divination extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2763765359108418280L;
	@Column(header="name")
    private String name;
    @Column(header="desciption")
    private String desciption;
    @Column(header="summarize")
    private String summarize;
    @Column(header="fate")
    private String fate;
    @Column(header="career")
    private String career;
    @Column(header="business")
    private String business;
    @Column(header="reputation")
    private String reputation;
    @Column(name="luck_analyze",header="LuckAnalyze")
    private String luckAnalyze;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesciption() {
        return desciption;
    }
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
    public String getSummarize() {
        return summarize;
    }
    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }
    public String getFate() {
        return fate;
    }
    public void setFate(String fate) {
        this.fate = fate;
    }
    public String getCareer() {
        return career;
    }
    public void setCareer(String career) {
        this.career = career;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public String getReputation() {
        return reputation;
    }
    public void setReputation(String reputation) {
        this.reputation = reputation;
    }
    public String getLuckAnalyze() {
        return luckAnalyze;
    }
    public void setLuckAnalyze(String luckAnalyze) {
        this.luckAnalyze = luckAnalyze;
    }
}