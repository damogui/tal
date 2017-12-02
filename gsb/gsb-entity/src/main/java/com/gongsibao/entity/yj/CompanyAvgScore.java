package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_company_avg_score")
public class CompanyAvgScore extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5001645859834937388L;
	@Column(header="type")
    private Integer type;
    @Column(header="month")
    private String month;
    @Column(header="score")
    private BigDecimal score;
    @Column(name="add_time",header="AddTime")
    private Timestamp addTime;

    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public BigDecimal getScore() {
        return score;
    }
    public void setScore(BigDecimal score) {
        this.score = score;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
}