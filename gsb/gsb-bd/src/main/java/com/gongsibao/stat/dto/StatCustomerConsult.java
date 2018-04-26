package com.gongsibao.stat.dto;

import java.io.Serializable;

public class StatCustomerConsult implements Serializable {
    private static final long serialVersionID = -8756589634018062278L;

    private Integer officeId;
    private String officeName;
    private Integer lastMonthEffectiveConsultation;
    private Integer currentMonthEffectiveConsultation;
    private Integer lastMonthUneffectiveConsultation;
    private Integer currentMonthUneffectiveConsultation;
    private Integer currentNewCustomerConsultCount;
    private float averageTime;

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getLastMonthEffectiveConsultation() {
        return lastMonthEffectiveConsultation;
    }

    public void setLastMonthEffectiveConsultation(Integer lastMonthEffectiveConsultation) {
        this.lastMonthEffectiveConsultation = lastMonthEffectiveConsultation;
    }

    public Integer getCurrentMonthEffectiveConsultation() {
        return currentMonthEffectiveConsultation;
    }

    public void setCurrentMonthEffectiveConsultation(Integer currentMonthEffectiveConsultation) {
        this.currentMonthEffectiveConsultation = currentMonthEffectiveConsultation;
    }

    public Integer getLastMonthUneffectiveConsultation() {
        return lastMonthUneffectiveConsultation;
    }

    public void setLastMonthUneffectiveConsultation(Integer lastMonthUneffectiveConsultation) {
        this.lastMonthUneffectiveConsultation = lastMonthUneffectiveConsultation;
    }

    public Integer getCurrentMonthUneffectiveConsultation() {
        return currentMonthUneffectiveConsultation;
    }

    public void setCurrentMonthUneffectiveConsultation(Integer currentMonthUneffectiveConsultation) {
        this.currentMonthUneffectiveConsultation = currentMonthUneffectiveConsultation;
    }

    public Integer getCurrentNewCustomerConsultCount() {
        return currentNewCustomerConsultCount;
    }

    public void setCurrentNewCustomerConsultCount(Integer currentNewCustomerConsultCount) {
        this.currentNewCustomerConsultCount = currentNewCustomerConsultCount;
    }

    public float getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(float averageTime) {
        this.averageTime = averageTime;
    }
}
