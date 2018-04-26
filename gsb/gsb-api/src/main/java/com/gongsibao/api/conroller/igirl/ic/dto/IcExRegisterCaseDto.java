package com.gongsibao.api.conroller.igirl.ic.dto;

public class IcExRegisterCaseDto {
    private Integer id;

    private String approvalName;

    private String ownerTel;

    private String operatorTel;

    private String collectorTel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
    }

    public String getOperatorTel() {
        return operatorTel;
    }

    public void setOperatorTel(String operatorTel) {
        this.operatorTel = operatorTel;
    }

    public String getCollectorTel() {
        return collectorTel;
    }

    public void setCollectorTel(String collectorTel) {
        this.collectorTel = collectorTel;
    }
}
