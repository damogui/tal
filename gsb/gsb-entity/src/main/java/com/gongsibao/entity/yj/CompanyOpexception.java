package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_company_opexception")
public class CompanyOpexception extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6950823464265815410L;
	@Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="key_no",header="KeyNo")
    private String keyNo;
    @Column(name="add_reason",header="AddReason")
    private String addReason;
    @Column(name="add_date",header="AddDate")
    private Timestamp addDate;
    @Column(name="romove_reason",header="RomoveReason")
    private String romoveReason;
    @Column(name="remove_date",header="RemoveDate")
    private Timestamp removeDate;
    @Column(name="decision_office",header="DecisionOffice")
    private String decisionOffice;
    @Column(name="remove_decision_office",header="RemoveDecisionOffice")
    private String removeDecisionOffice;

    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getKeyNo() {
        return keyNo;
    }
    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }
    public String getAddReason() {
        return addReason;
    }
    public void setAddReason(String addReason) {
        this.addReason = addReason;
    }
    public Timestamp getAddDate() {
        return addDate;
    }
    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
    public String getRomoveReason() {
        return romoveReason;
    }
    public void setRomoveReason(String romoveReason) {
        this.romoveReason = romoveReason;
    }
    public Timestamp getRemoveDate() {
        return removeDate;
    }
    public void setRemoveDate(Timestamp removeDate) {
        this.removeDate = removeDate;
    }
    public String getDecisionOffice() {
        return decisionOffice;
    }
    public void setDecisionOffice(String decisionOffice) {
        this.decisionOffice = decisionOffice;
    }
    public String getRemoveDecisionOffice() {
        return removeDecisionOffice;
    }
    public void setRemoveDecisionOffice(String removeDecisionOffice) {
        this.removeDecisionOffice = removeDecisionOffice;
    }
}