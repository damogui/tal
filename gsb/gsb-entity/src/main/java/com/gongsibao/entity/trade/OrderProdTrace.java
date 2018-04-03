package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.trade.dic.OrderProdTraceOperatorType;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;

@Table(name = "so_order_prod_trace")
public class OrderProdTrace extends BaseEntity {

	private static final long serialVersionUID = -5637326992812474888L;
	@Column(name = "order_prod_id", header = "产品")
	private Integer orderProdId;

	@Column(name = "order_prod_status_id", header = "产品状态")
	private Integer orderProdStatusId;
	
    @Reference(foreignKey = "orderProdStatusId", header = "产品状态")
    private WorkflowNode orderProdStatus;

	@Column(name = "type_id", header = "产品类型")
	private OrderProdTraceType typeId = OrderProdTraceType.wu;

	@Column(name = "operator_type", header = "操作类型")
	private OrderProdTraceOperatorType operatorType = OrderProdTraceOperatorType.wu;

	@Column(header = "说明")
	private String info;

	@Column(name = "operator_id", header = "操作人Id")
	private Integer operatorId;
	
    @Reference(foreignKey = "operatorId", header = "操作人")
    private Employee operator;

	@Column(name = "is_bbk", header = "IsBbk")
	private String isBbk = "0";

	@Column(header = "remark")
	private String remark;

	@Column(name = "is_send_sms", header = "短信发送")
	private Integer isSendSms;

	@Column(name = "express_content", header = "发送内容")
	private String expressContent;

	@Column(name = "express_to", header = "接收人")
	private String expressTo;

	@Column(name = "express_company_name", header = "发送公司名称")
	private String expressCompanyName;

	@Column(name = "express_no", header = "发送编号")
	private String expressNo;

	@Column(name = "processd_days", header = "已处理天数")
	private Integer processdDays;

	@Column(name = "timeout_days", header = "待处理天数")
	private Integer timeoutDays;

	@Column(name = "tip_color", header = "提醒颜色")
	private String tipColor;

	@Subs(subType = OrderProdTraceFile.class, foreignKey = "orderProdTraceId", primaryKey = "pkid")
	private List<OrderProdTraceFile> files = new ArrayList<OrderProdTraceFile>();

	public Integer getOrderProdId() {
		return orderProdId;
	}

	public void setOrderProdId(Integer orderProdId) {
		this.orderProdId = orderProdId;
	}

	public Integer getOrderProdStatusId() {
		return orderProdStatusId;
	}

	public void setOrderProdStatusId(Integer orderProdStatusId) {
		this.orderProdStatusId = orderProdStatusId;
	}

	public OrderProdTraceType getTypeId() {
		return typeId;
	}

	public void setTypeId(OrderProdTraceType typeId) {
		this.typeId = typeId;
	}

	public OrderProdTraceOperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OrderProdTraceOperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getIsBbk() {
		return isBbk;
	}

	public void setIsBbk(String isBbk) {
		this.isBbk = isBbk;
	}

	public String getRemark() {
		return remark == null ? "" : remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsSendSms() {
		return isSendSms == null ? 0 : isSendSms;
	}

	public void setIsSendSms(Integer isSendSms) {
		this.isSendSms = isSendSms;
	}

	public String getExpressContent() {
		return expressContent;
	}

	public void setExpressContent(String expressContent) {
		this.expressContent = expressContent;
	}

	public String getExpressTo() {
		return expressTo;
	}

	public void setExpressTo(String expressTo) {
		this.expressTo = expressTo;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Integer getProcessdDays() {
		return processdDays;
	}

	public void setProcessdDays(Integer processdDays) {
		this.processdDays = processdDays;
	}

	public Integer getTimeoutDays() {
		return timeoutDays;
	}

	public void setTimeoutDays(Integer timeoutDays) {
		this.timeoutDays = timeoutDays;
	}

	public String getTipColor() {
		return tipColor;
	}

	public void setTipColor(String tipColor) {
		this.tipColor = tipColor;
	}

	public List<OrderProdTraceFile> getFiles() {
		return files;
	}

	public void setFiles(List<OrderProdTraceFile> files) {
		this.files = files;
	}

	public Employee getOperator() {
		return operator;
	}

	public void setOperator(Employee operator) {
		this.operator = operator;
	}

	public WorkflowNode getOrderProdStatus() {
		return orderProdStatus;
	}

	public void setOrderProdStatus(WorkflowNode orderProdStatus) {
		this.orderProdStatus = orderProdStatus;
	}
	
	
}