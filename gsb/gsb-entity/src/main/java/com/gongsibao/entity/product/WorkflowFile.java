package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "prod_workflow_file", header = "产品处理流程材料")
public class WorkflowFile extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 2448898261986342744L;

	@Column(name = "prod_workflow_id", header = "产品处理流程序号")
	private Integer prodWorkflowId;

	@Column(name = "name",size=500, header = "材料名称")
	private String name;

	@Column(name = "is_must", header = "是否必须提供")
	private Boolean must = true;

	@Column(name = "sort", header = "排序")
	private Double sort = 1D;

	@Column(name = "version", header = "版本号")
	private Integer version = 0;

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


	public Boolean getMust() {
		return must;
	}

	public void setMust(Boolean must) {
		this.must = must;
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