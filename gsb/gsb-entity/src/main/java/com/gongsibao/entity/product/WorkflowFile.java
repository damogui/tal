package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_workflow_file")
public class WorkflowFile extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2448898261986342744L;
	@Column(name="prod_workflow_id")
    private Integer prodWorkflowId;
    private String name;
    @Column(name="is_must")
    private Integer isMust;
    private Double sort;
    private Integer version;

    public Integer getProdWorkflowId() {
        return prodWorkflowId;
    }
    public void setProdWorkflowId(Integer prodWorkflowId) {
        this.prodWorkflowId = prodWorkflowId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsMust() {
        return isMust;
    }
    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}