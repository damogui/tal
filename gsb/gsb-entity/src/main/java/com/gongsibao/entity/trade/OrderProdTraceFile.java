package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod_trace_file")
public class OrderProdTraceFile extends BaseEntity {

	private static final long serialVersionUID = 7952438505448731011L;
	
	@Column(name="order_prod_trace_id",header="OrderProdTraceId")
    private Integer orderProdTraceId;
    @Column(name="prod_workflow_file_id",header="ProdWorkflowFileId")
    private Integer prodWorkflowFileId;
    @Column(name="prod_workflow_file_name",header="ProdWorkflowFileName")
    private String prodWorkflowFileName;
    @Column(name="file_id",header="FileId")
    private Integer fileId;
    @Column(name="is_new",header="IsNew")
    private Integer isNew;
    @Column(name="audit_status_id",header="AuditStatusId")
    private Integer auditStatusId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(header="remark")
    private String remark;
    @Column(name="is_top",header="IsTop")
    private Integer isTop;

    public Integer getOrderProdTraceId() {
        return orderProdTraceId;
    }
    public void setOrderProdTraceId(Integer orderProdTraceId) {
        this.orderProdTraceId = orderProdTraceId;
    }
    public Integer getProdWorkflowFileId() {
        return prodWorkflowFileId;
    }
    public void setProdWorkflowFileId(Integer prodWorkflowFileId) {
        this.prodWorkflowFileId = prodWorkflowFileId;
    }
    public String getProdWorkflowFileName() {
        return prodWorkflowFileName;
    }
    public void setProdWorkflowFileName(String prodWorkflowFileName) {
        this.prodWorkflowFileName = prodWorkflowFileName;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public Integer getIsNew() {
        return isNew;
    }
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
    public Integer getAuditStatusId() {
        return auditStatusId;
    }
    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getIsTop() {
        return isTop;
    }
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }
}