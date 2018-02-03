package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class CompanyPotentialCount implements IEntity {

    private static final long serialVersionUID = -775278495178162373L;

    /* 公司名称 */
    private String name;
    /* 商标总数 */
    private int tmSum;
    /* 聚合后商标推荐分类数量相加 */
    private int recommendCount;
    /* 商标潜在变更数量 */
    private int tmChangeCount;
    /* 商标潜在续展数量 */
    private int tmRenewalCount;

    /* 专利总数 */
    private int patentSum;
    /* 专利潜在注册数量 */
    private int patentNewCount;
    /* 潜在变更数量 */
    private int patentChangeCount;
    /* 专利变更数 */
    private int patentRenewalCount;

    /* 著作权数量 */
    private int copyright;

    // TODO 以下机会未给出
    //----------------- add by wwm---------------
    // 年报
    private int annualReportCount;
    // 增值电信(暂时系统自行运算)
    // 娱乐牌照
    private int entertainmentCount;
    // 高新企业
    private int hiEnterpriseUnitCount;
    // 税收筹划
    private int planningUnitCount;
    // 影视审批
    private int moviesUnitCount;
    // 食品流通
    private int foodUnitCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTmSum() {
        return tmSum;
    }

    public void setTmSum(int tmSum) {
        this.tmSum = tmSum;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    public int getTmChangeCount() {
        return tmChangeCount;
    }

    public void setTmChangeCount(int tmChangeCount) {
        this.tmChangeCount = tmChangeCount;
    }

    public int getTmRenewalCount() {
        return tmRenewalCount;
    }

    public void setTmRenewalCount(int tmRenewalCount) {
        this.tmRenewalCount = tmRenewalCount;
    }

    public int getPatentSum() {
        return patentSum;
    }

    public void setPatentSum(int patentSum) {
        this.patentSum = patentSum;
    }

    public int getPatentNewCount() {
        return patentNewCount;
    }

    public void setPatentNewCount(int patentNewCount) {
        this.patentNewCount = patentNewCount;
    }

    public int getPatentChangeCount() {
        return patentChangeCount;
    }

    public void setPatentChangeCount(int patentChangeCount) {
        this.patentChangeCount = patentChangeCount;
    }

    public int getPatentRenewalCount() {
        return patentRenewalCount;
    }

    public void setPatentRenewalCount(int patentRenewalCount) {
        this.patentRenewalCount = patentRenewalCount;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
    }

	public int getAnnualReportCount() {
		return annualReportCount;
	}

	public void setAnnualReportCount(int annualReportCount) {
		this.annualReportCount = annualReportCount;
	}

	public int getEntertainmentCount() {
		return entertainmentCount;
	}

	public void setEntertainmentCount(int entertainmentCount) {
		this.entertainmentCount = entertainmentCount;
	}

	public int getHiEnterpriseUnitCount() {
		return hiEnterpriseUnitCount;
	}

	public void setHiEnterpriseUnitCount(int hiEnterpriseUnitCount) {
		this.hiEnterpriseUnitCount = hiEnterpriseUnitCount;
	}

	public int getPlanningUnitCount() {
		return planningUnitCount;
	}

	public void setPlanningUnitCount(int planningUnitCount) {
		this.planningUnitCount = planningUnitCount;
	}

	public int getMoviesUnitCount() {
		return moviesUnitCount;
	}

	public void setMoviesUnitCount(int moviesUnitCount) {
		this.moviesUnitCount = moviesUnitCount;
	}

	public int getFoodUnitCount() {
		return foodUnitCount;
	}

	public void setFoodUnitCount(int foodUnitCount) {
		this.foodUnitCount = foodUnitCount;
	}
    
    
}
