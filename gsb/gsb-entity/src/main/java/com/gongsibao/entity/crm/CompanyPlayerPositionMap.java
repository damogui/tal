package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_player_position_map",header="员工和职位关联信息表")
public class CompanyPlayerPositionMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8117690667495216183L;
	@Column(name="player_id",header="")
    private Integer playerId;
    @Column(name="position_id",header="")
    private Integer positionId;

    public Integer getPlayerId() {
        return playerId;
    }
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
    public Integer getPositionId() {
        return positionId;
    }
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}