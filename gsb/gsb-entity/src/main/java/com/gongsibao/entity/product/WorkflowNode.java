package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.product.dic.WorkflowNodeName;

@Table(name="prod_workflow_node", header = "产品处理流程节点")
public class WorkflowNode extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 9028859712115172315L;

	  
	@Column(name="workflow_id", header = "处理流程序号")
    private Integer workflowId;
	
    @Column(name="type_id", header = "节点类型序号，type=9")
    private Integer typeId;
    
    @Column(name="name", header = "名称")
    private String name;
    
    @Column(name="sort", header = "排序")
    private Double sort = 1D;
    
    @Column(name="weekday_count", header = "办理工作日天数")
    private Integer weekdayCount;
    
    @Column(name="version", header = "版本号")
    private Integer version = 0;
    
    @Column(name="name_no", header = "名称编号（1：核名、2：网提、3：下载、打印、4：预约、5：交件、6：取证、7：备案刻章、8：结束(已结算，type=2064)））")
    private WorkflowNodeName nameNo;

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

	public WorkflowNodeName getNameNo() {
		return nameNo;
	}

	public void setNameNo(WorkflowNodeName nameNo) {
		this.nameNo = nameNo;
	}
}