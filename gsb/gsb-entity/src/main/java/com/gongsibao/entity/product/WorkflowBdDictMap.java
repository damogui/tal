package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="prod_workflow_bd_dict_map",header="产品处理流程与地区关系")
public class WorkflowBdDictMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2494526331730019569L;
	@Column(name="workflow_id",header="产品流程序号")
    private Integer workflowId;
	
    @Column(name="city_id",header="地区序号，type=1")
    private Integer cityId;

	@Reference(foreignKey="cityId",header="地区")
	private Dict city;

	public Integer getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}
	
	
} 