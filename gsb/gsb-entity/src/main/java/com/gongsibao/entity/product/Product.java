package com.gongsibao.entity.product;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name = "prod_product", header = "产品")
public class Product extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4367629857164993608L;

	@Column(name = "type_id", header = "产品分类序号，字典表type=201")
	private Integer typeId;
	
	@Reference(foreignKey="typeId")
    private Dict type;
    
	@Column(name = "dealer_type_id", header = "销售方类型序号，字典表type=8")
	private Integer dealerTypeId;

	@Reference(foreignKey="dealerTypeId")
    private Dict dealerType;
	
	@Column(name = "name", header = "产品名称")
	private String name;

	@Column(name = "no", header = "产品编号")
	private String no;

	@Column(name = "description", header = "描述信息")
	private String description;

	@Column(name = "sort", header = "排序")
	private Double sort = 1D;

	@Column(name = "is_enabled", header = "是否启用")
	private Boolean enabled = true;

	@Column(name = "remark", header = "说明")
	private String remark;

	@Column(name = "is_ordered_background_only", header = "是否仅可后台购买（不会在网站展示）")
	private Boolean isOrderedBackgroundOnly = false;

	@Column(name = "is_allowed_add_to_cart", header = "是否可以加入购物车")
	private Boolean isAllowedAddToCart = true;

	@Column(name = "is_required_ems_address", header = "是否需要填写邮寄地址（此产品需要邮寄给客户）")
	private Boolean isRequiredEmsAddress = false;

	@Column(name = "is_allowed_buy_one_more", header = "是否可以复数购买 (多于一个)")
	private Boolean isAllowedBuyOneMore = false;

	@Column(name = "is_required_service_lifecycle", header = "是否需要选择服务周期（适用于以时间为单位的产品）")
	private Boolean isRequiredServiceLifecycle = false;

	@Column(name = "is_required_company_register_info", header = "是否需要填写公司注册基本信息（仅涉及PC端）")
	private Boolean isRequiredCompanyRegisterInfo = false;

	@Column(name = "is_required_check_name_info", header = "是否需要填写核名信息（仅涉及APP）")
	private Boolean isRequiredCheckNameInfo = false;

	@Column(name = "is_required_company_register_address", header = "是否需要公司注册地址(是否需要选择“是否自有地址”)")
	private Boolean isRequiredCompanyRegisterAddress = false;

	@Column(name = "is_negotiable", header = "是否价格面议")
	private Boolean isNegotiable = false;

	@Column(name = "is_apply_no", header = "是否有申请号 0无 1有")
	private Boolean isApplyNo = false;

	@Column(name = "is_handle", header = "是否有办理名称 0无 1有")
	private Boolean isHandle = false;

	@Column(name = "income_rate", header = "供应商分层比例")
	private Integer incomeRate = 80;
	
	@Subs(subType = ProductBusiness.class, foreignKey = "productId", header = "归属事业部")
	private List<ProductBusiness> business;
	
	@Subs(subType = ProductService.class, foreignKey = "productId", header = "产品服务")
	private List<ProductService> services;

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsOrderedBackgroundOnly() {
		return isOrderedBackgroundOnly;
	}

	public void setIsOrderedBackgroundOnly(Boolean isOrderedBackgroundOnly) {
		this.isOrderedBackgroundOnly = isOrderedBackgroundOnly;
	}

	public Boolean getIsAllowedAddToCart() {
		return isAllowedAddToCart;
	}

	public void setIsAllowedAddToCart(Boolean isAllowedAddToCart) {
		this.isAllowedAddToCart = isAllowedAddToCart;
	}

	public Boolean getIsRequiredEmsAddress() {
		return isRequiredEmsAddress;
	}

	public void setIsRequiredEmsAddress(Boolean isRequiredEmsAddress) {
		this.isRequiredEmsAddress = isRequiredEmsAddress;
	}

	public Boolean getIsAllowedBuyOneMore() {
		return isAllowedBuyOneMore;
	}

	public void setIsAllowedBuyOneMore(Boolean isAllowedBuyOneMore) {
		this.isAllowedBuyOneMore = isAllowedBuyOneMore;
	}

	public Boolean getIsRequiredServiceLifecycle() {
		return isRequiredServiceLifecycle;
	}

	public void setIsRequiredServiceLifecycle(Boolean isRequiredServiceLifecycle) {
		this.isRequiredServiceLifecycle = isRequiredServiceLifecycle;
	}

	public Boolean getIsRequiredCompanyRegisterInfo() {
		return isRequiredCompanyRegisterInfo;
	}

	public void setIsRequiredCompanyRegisterInfo(Boolean isRequiredCompanyRegisterInfo) {
		this.isRequiredCompanyRegisterInfo = isRequiredCompanyRegisterInfo;
	}

	public Boolean getIsRequiredCheckNameInfo() {
		return isRequiredCheckNameInfo;
	}

	public void setIsRequiredCheckNameInfo(Boolean isRequiredCheckNameInfo) {
		this.isRequiredCheckNameInfo = isRequiredCheckNameInfo;
	}

	public Boolean getIsRequiredCompanyRegisterAddress() {
		return isRequiredCompanyRegisterAddress;
	}

	public void setIsRequiredCompanyRegisterAddress(Boolean isRequiredCompanyRegisterAddress) {
		this.isRequiredCompanyRegisterAddress = isRequiredCompanyRegisterAddress;
	}

	public Boolean getIsNegotiable() {
		return isNegotiable;
	}

	public void setIsNegotiable(Boolean isNegotiable) {
		this.isNegotiable = isNegotiable;
	}

	public Boolean getIsApplyNo() {
		return isApplyNo;
	}

	public void setIsApplyNo(Boolean isApplyNo) {
		this.isApplyNo = isApplyNo;
	}

	public Boolean getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}

	public Integer getIncomeRate() {
		return incomeRate;
	}

	public void setIncomeRate(Integer incomeRate) {
		this.incomeRate = incomeRate;
	}
}