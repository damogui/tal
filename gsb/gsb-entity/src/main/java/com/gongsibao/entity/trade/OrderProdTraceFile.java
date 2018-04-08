package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.File;

@Table(name="so_order_prod_trace_file")
public class OrderProdTraceFile extends BaseEntity {

	private static final long serialVersionUID = 7952438505448731011L;
	  
	@Column(name="order_prod_trace_id",header="订单项记录序号")
    private Integer orderProdTraceId;
	
    @Column(name="prod_workflow_file_id",header="订单处理流程材料序号")
    private Integer prodWorkflowFileId;
    
    @Column(name="prod_workflow_file_name",header="订单处理流程材料名称")
    private String prodWorkflowFileName;
    
    @Column(name="file_id",header="上传材料序号")
    private Integer fileId;
    
	@Reference(foreignKey = "fileId", header = "上传材料")
	private File file;
    
    @Column(name="is_new",header="上传材料是否最新(1:最新;2:历史)")
    private Integer isNew;
    
    @Column(name="audit_status_id",header="审核状态序号，type=105，1051 待审核、1053 驳回审核、1054 审核通过")
    private Integer auditStatusId;
    
    @Column(name="remark",header="说明")
    private String remark;
    
    @Column(name="is_top",header="是否置顶：0否 1是")
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}