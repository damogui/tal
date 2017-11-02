package com.gongsibao.taurus.entity;



/**
 * Created by cxq on 2017/11/1.
 */
public class CompanyEquity implements IEntity{

    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2984295440592404279L;

	private Long id;

    // 登记编号
    private String regNumber;

    // 出质人
    private String pledgor;

    // 质权人
    private String pledgee;

    // 出质权股份数额
    private String equityAmount;

    // 股权出质设立登记日期
    private String regDate;

    // 状态
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getPledgor() {
        return pledgor;
    }

    public void setPledgor(String pledgor) {
        this.pledgor = pledgor;
    }

    public String getPledgee() {
        return pledgee;
    }

    public void setPledgee(String pledgee) {
        this.pledgee = pledgee;
    }

    public String getEquityAmount() {
        return equityAmount;
    }

    public void setEquityAmount(String equityAmount) {
        this.equityAmount = equityAmount;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
