package com.gongsibao.entity.product;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_workflow_bd_dict_map")
public class WorkflowBdDictMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2494526331730019569L;
	@Column(name="workflow_id")
    private Integer workflowId;
    @Column(name="city_id")
    private Integer cityId;

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
}