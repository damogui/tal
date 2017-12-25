package com.gongsibao.entity.yj;

import java.sql.Timestamp;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_trademark_flow")
public class TrademarkFlow extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6719132155710931166L;
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="flow_id",header="FlowId")
    private String flowId;
    @Column(name="yj_trademark_id",header="YjTrademarkId")
    private Integer yjTrademarkId;
    @Column(name="flow_item",header="FlowItem")
    private String flowItem;
    @Column(name="flow_date",header="FlowDate")
    private Timestamp flowDate;

    public String getFlowId() {
        return flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    public Integer getYjTrademarkId() {
        return yjTrademarkId;
    }
    public void setYjTrademarkId(Integer yjTrademarkId) {
        this.yjTrademarkId = yjTrademarkId;
    }
    public String getFlowItem() {
        return flowItem;
    }
    public void setFlowItem(String flowItem) {
        this.flowItem = flowItem;
    }
    public Timestamp getFlowDate() {
        return flowDate;
    }
    public void setFlowDate(Timestamp flowDate) {
        this.flowDate = flowDate;
    }
}