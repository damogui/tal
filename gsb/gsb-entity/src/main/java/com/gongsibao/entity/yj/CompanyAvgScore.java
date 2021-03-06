package com.gongsibao.entity.yj;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_company_avg_score")
public class CompanyAvgScore extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5001645859834937388L;
	
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
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