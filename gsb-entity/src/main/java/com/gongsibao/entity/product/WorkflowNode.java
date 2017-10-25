package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_workflow_node")
public class WorkflowNode extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9028859712115172315L;
	@Column(name="workflow_id")
    private Integer workflowId;
    @Column(name="type_id")
    private Integer typeId;
    private String name;
    private Double sort;
    @Column(name="weekday_count")
    private Integer weekdayCount;
    private Integer version;
    @Column(name="name_no")
    private Integer nameNo;

    public Integer getWorkflowId() {
        return workflowId;
    }
    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public Integer getWeekdayCount() {
        return weekdayCount;
    }
    public void setWeekdayCount(Integer weekdayCount) {
        this.weekdayCount = weekdayCount;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Integer getNameNo() {
        return nameNo;
    }
    public void setNameNo(Integer nameNo) {
        this.nameNo = nameNo;
    }
}