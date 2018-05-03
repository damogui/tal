package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.trade.dic.NOrderExchangeLogOperationType;
import com.gongsibao.entity.trade.dic.NOrderExchangeLogType;

/**
 * Created by zhangchao on 2018/3/7.
 */
@Table(name = "n_order_exchange_log", header = "订单流转日志")
public class NOrderExchangeLog extends Entity {


    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3969311434200532300L;

	@Column(name = "order_id", header = "订单id")
    private Integer orderId;

    @Column(name = "type", header = "类型（1:分配 2:转移）")
    private NOrderExchangeLogType nOrderExchangeLogType = NOrderExchangeLogType.Fenpei;

    @Column(name = "is_current", header = "是否当前（0:否（曾经负责） 1:是（正在负责））")
    private boolean isCurrent;

    @Column(name = "form_department_id", header = "来自的部门id")
    private Integer formDepartmentId;

    @Column(name = "form_department_name", header = "来自的部门名称")
    private String formDepartmentName;

    @Column(name = "form_supplier_id", header = "来自的平台id")
    private Integer formSupplierId;

    @Column(name = "form_supplier_name", header = "来自的服务商名称")
    private String formSupplierName;

    @Column(name = "form_user_id", header = "来自业务员id")
    private Integer formUserId;

    @Column(name = "form_user_name", header = "来自业务员名称")
    private String formUserName;

    @Column(name = "to_supplier_id", header = "去向服务商id")
    private Integer toSupplierId;

    @Column(name = "to_supplier_name", header = "去向服务商名称")
    private String toSupplierName;

    @Column(name = "to_department_id", header = "去向服务商部门id")
    private Integer toDepartmentId;

    @Column(name = "to_department_name", header = "去向服务商部门名称")
    private String toDepartmentName;

    @Column(name = "to_user_id", header = "去向的业务员id")
    private Integer toUserId;

    @Column(name = "to_user_name", header = "去向的业务员名称")
    private String toUserName;

    @Column(name = "operation_type", header = "操作类型（1：自动、2：手动）")
    private NOrderExchangeLogOperationType nOrderExchangeLogOperationType = NOrderExchangeLogOperationType.MANUAL;

    @Column(name = "content", header = "内容")
    private String content;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public NOrderExchangeLogType getnOrderExchangeLogType() {
        return nOrderExchangeLogType;
    }

    public void setnOrderExchangeLogType(NOrderExchangeLogType nOrderExchangeLogType) {
        this.nOrderExchangeLogType = nOrderExchangeLogType;
    }

    public Integer getFormDepartmentId() {
        return formDepartmentId;
    }

    public void setFormDepartmentId(Integer formDepartmentId) {
        this.formDepartmentId = formDepartmentId;
    }

    public String getFormDepartmentName() {
        return formDepartmentName;
    }

    public void setFormDepartmentName(String formDepartmentName) {
        this.formDepartmentName = formDepartmentName;
    }

    public Integer getFormSupplierId() {
        return formSupplierId;
    }

    public void setFormSupplierId(Integer formSupplierId) {
        this.formSupplierId = formSupplierId;
    }

    public String getFormSupplierName() {
        return formSupplierName;
    }

    public void setFormSupplierName(String formSupplierName) {
        this.formSupplierName = formSupplierName;
    }

    public Integer getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Integer formUserId) {
        this.formUserId = formUserId;
    }

    public String getFormUserName() {
        return formUserName;
    }

    public void setFormUserName(String formUserName) {
        this.formUserName = formUserName;
    }

    public Integer getToSupplierId() {
        return toSupplierId;
    }

    public void setToSupplierId(Integer toSupplierId) {
        this.toSupplierId = toSupplierId;
    }

    public String getToSupplierName() {
        return toSupplierName;
    }

    public void setToSupplierName(String toSupplierName) {
        this.toSupplierName = toSupplierName;
    }

    public Integer getToDepartmentId() {
        return toDepartmentId;
    }

    public void setToDepartmentId(Integer toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }

    public String getToDepartmentName() {
        return toDepartmentName;
    }

    public void setToDepartmentName(String toDepartmentName) {
        this.toDepartmentName = toDepartmentName;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public NOrderExchangeLogOperationType getnOrderExchangeLogOperationType() {
        return nOrderExchangeLogOperationType;
    }

    public void setnOrderExchangeLogOperationType(NOrderExchangeLogOperationType nOrderExchangeLogOperationType) {
        this.nOrderExchangeLogOperationType = nOrderExchangeLogOperationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
