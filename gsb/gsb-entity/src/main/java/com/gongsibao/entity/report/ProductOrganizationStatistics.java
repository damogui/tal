package com.gongsibao.entity.report;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.uc.Organization;

@Table(name="report_product_organization",header="产品按部门统计")
public class ProductOrganizationStatistics  extends BasePerformanceEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8845589664519198989L;

    @Reference(foreignKey="departmentId",primaryKey="pkid")
	private Organization department;

	@Column(name = "department_id", header = "部门Id")
	private Integer departmentId;
	
    @Reference(foreignKey="productCategoryId",primaryKey="pkid")
	private Dict productCategory;

	@Column(name = "product_category_id", header = "产品分类Id")
	private Integer productCategoryId;
	
    @Reference(foreignKey="productId",primaryKey="pkid")
	private Product product;

	@Column(name = "product_id", header = "产品Id")
	private Integer productId;
	

	public Organization getDepartment() {
		return department;
	}

	public void setDepartment(Organization department) {
		this.department = department;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Dict getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Dict productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}
