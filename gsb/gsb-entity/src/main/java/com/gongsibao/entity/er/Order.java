package com.gongsibao.entity.er;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order")
public class Order extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4632999821097567562L;
	@Column(name="no",header="no")
    private String no;
    @Column(name="customer_id",header="CustomerId")
    private Integer customerId;
    @Column(name="product_id",header="ProductId")
    private Integer productId;
    @Column(name="city_id",header="CityId")
    private Integer cityId;
    @Column(name="workflow_id",header="WorkflowId")
    private Integer workflowId;
    @Column(name="workflow_name",header="WorkflowName")
    private String workflowName;
    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;
    @Column(name="price",header="price")
    private Integer price;
    @Column(name="so_order_id",header="SoOrderId")
    private Integer soOrderId;
    @Column(name="order_no",header="OrderNo")
    private String orderNo;
    @Column(name="group_name",header="GroupName")
    private String groupName;
    @Column(name="company_name",header="CompanyName")
    private String companyName;
    @Column(name="end_time",header="EndTime")
    private Date endTime;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getWorkflowId() {
        return workflowId;
    }
    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }
    public String getWorkflowName() {
        return workflowName;
    }
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
    public Integer getTenantId() {
        return tenantId;
    }
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getSoOrderId() {
        return soOrderId;
    }
    public void setSoOrderId(Integer soOrderId) {
        this.soOrderId = soOrderId;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}