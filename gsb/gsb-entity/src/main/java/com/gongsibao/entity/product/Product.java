package com.gongsibao.entity.product;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_product")
public class Product extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4367629857164993608L;
	@Column(name="type_id")
    private Integer typeId;
    @Column(name="dealer_type_id")
    private Integer dealerTypeId;
    private String name;
    private String no;
    private String description;
    private Double sort;
    @Column(name="is_enabled")
    private Integer isEnabled;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user_id")
    private Integer addUserId;
    private String remark;
    @Column(name="is_ordered_background_only")
    private Integer isOrderedBackgroundOnly;
    @Column(name="is_allowed_add_to_cart")
    private Integer isAllowedAddToCart;
    @Column(name="is_required_ems_address")
    private Integer isRequiredEmsAddress;
    @Column(name="is_allowed_buy_one_more")
    private Integer isAllowedBuyOneMore;
    @Column(name="is_required_service_lifecycle")
    private Integer isRequiredServiceLifecycle;
    @Column(name="is_required_company_register_info")
    private Integer isRequiredCompanyRegisterInfo;
    @Column(name="is_required_check_name_info")
    private Integer isRequiredCheckNameInfo;
    @Column(name="is_required_company_register_address")
    private Integer isRequiredCompanyRegisterAddress;
    @Column(name="is_negotiable")
    private Integer isNegotiable;
    @Column(name="is_apply_no")
    private Integer isApplyNo;
    @Column(name="is_handle")
    private Integer isHandle;
    @Column(name="income_rate")
    private Integer incomeRate;

    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getDealerTypeId() {
        return dealerTypeId;
    }
    public void setDealerTypeId(Integer dealerTypeId) {
        this.dealerTypeId = dealerTypeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getIsOrderedBackgroundOnly() {
        return isOrderedBackgroundOnly;
    }
    public void setIsOrderedBackgroundOnly(Integer isOrderedBackgroundOnly) {
        this.isOrderedBackgroundOnly = isOrderedBackgroundOnly;
    }
    public Integer getIsAllowedAddToCart() {
        return isAllowedAddToCart;
    }
    public void setIsAllowedAddToCart(Integer isAllowedAddToCart) {
        this.isAllowedAddToCart = isAllowedAddToCart;
    }
    public Integer getIsRequiredEmsAddress() {
        return isRequiredEmsAddress;
    }
    public void setIsRequiredEmsAddress(Integer isRequiredEmsAddress) {
        this.isRequiredEmsAddress = isRequiredEmsAddress;
    }
    public Integer getIsAllowedBuyOneMore() {
        return isAllowedBuyOneMore;
    }
    public void setIsAllowedBuyOneMore(Integer isAllowedBuyOneMore) {
        this.isAllowedBuyOneMore = isAllowedBuyOneMore;
    }
    public Integer getIsRequiredServiceLifecycle() {
        return isRequiredServiceLifecycle;
    }
    public void setIsRequiredServiceLifecycle(Integer isRequiredServiceLifecycle) {
        this.isRequiredServiceLifecycle = isRequiredServiceLifecycle;
    }
    public Integer getIsRequiredCompanyRegisterInfo() {
        return isRequiredCompanyRegisterInfo;
    }
    public void setIsRequiredCompanyRegisterInfo(Integer isRequiredCompanyRegisterInfo) {
        this.isRequiredCompanyRegisterInfo = isRequiredCompanyRegisterInfo;
    }
    public Integer getIsRequiredCheckNameInfo() {
        return isRequiredCheckNameInfo;
    }
    public void setIsRequiredCheckNameInfo(Integer isRequiredCheckNameInfo) {
        this.isRequiredCheckNameInfo = isRequiredCheckNameInfo;
    }
    public Integer getIsRequiredCompanyRegisterAddress() {
        return isRequiredCompanyRegisterAddress;
    }
    public void setIsRequiredCompanyRegisterAddress(Integer isRequiredCompanyRegisterAddress) {
        this.isRequiredCompanyRegisterAddress = isRequiredCompanyRegisterAddress;
    }
    public Integer getIsNegotiable() {
        return isNegotiable;
    }
    public void setIsNegotiable(Integer isNegotiable) {
        this.isNegotiable = isNegotiable;
    }
    public Integer getIsApplyNo() {
        return isApplyNo;
    }
    public void setIsApplyNo(Integer isApplyNo) {
        this.isApplyNo = isApplyNo;
    }
    public Integer getIsHandle() {
        return isHandle;
    }
    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }
    public Integer getIncomeRate() {
        return incomeRate;
    }
    public void setIncomeRate(Integer incomeRate) {
        this.incomeRate = incomeRate;
    }
}