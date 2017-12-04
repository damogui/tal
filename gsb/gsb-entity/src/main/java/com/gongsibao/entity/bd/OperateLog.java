package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_operate_log")
public class OperateLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5618276699313434971L;
	@Column(name="tab_name",header="TabName")
    private String tabName;
    @Column(name="form_id",header="FormId")
    private Integer formId;
    @Column(name="change_record",header="ChangeRecord")
    private String changeRecord;
    @Column(name="operate_status",header="OperateStatus")
    private Integer operateStatus;

    public String getTabName() {
        return tabName;
    }
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
    public Integer getFormId() {
        return formId;
    }
    public void setFormId(Integer formId) {
        this.formId = formId;
    }
    public String getChangeRecord() {
        return changeRecord;
    }
    public void setChangeRecord(String changeRecord) {
        this.changeRecord = changeRecord;
    }
    public Integer getOperateStatus() {
        return operateStatus;
    }
    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

}