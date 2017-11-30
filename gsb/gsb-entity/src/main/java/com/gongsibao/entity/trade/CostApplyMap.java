package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_cost_apply_map")
public class CostApplyMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6076329817296408228L;
	@Column(name="apply_id",header="ApplyId")
    private Integer applyId;
    @Column(name="cost_id",header="CostId")
    private Integer costId;

    public Integer getApplyId() {
        return applyId;
    }
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }
    public Integer getCostId() {
        return costId;
    }
    public void setCostId(Integer costId) {
        this.costId = costId;
    }
}