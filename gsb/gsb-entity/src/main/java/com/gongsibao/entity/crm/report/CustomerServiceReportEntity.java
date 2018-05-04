package com.gongsibao.entity.crm.report;

import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Product;


@Table(isView=true, name = "")
public class CustomerServiceReportEntity extends Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	/*客服名称*/
	private String name;

	/**   
     * @Fields date : TODO(日期)   
     */   
    private String date;
    
    private String productCategoryId1Name;
    
    private Integer productCategoryId1;
    
    @Reference(foreignKey = "productCategoryId1",  header = "产品一级分类")
	private Dict productCategory1;

    private String productCategoryId2Name;
    
	private Integer productCategoryId2;

	@Reference(foreignKey = "productCategoryId2", header = "产品二级分类")
	private Dict productCategory2;

	private String productName;
	
	private Integer productId;

	@Reference(foreignKey = "productId", header = "产品")
	private Product product;
    
    
	/**   
	 * @Fields creatCustomerCount : TODO(创建客户数)   
	 */   
	private Integer creatCustomerCount = 0;
	
	/**   
	 * @Fields creatTaskCount : TODO(创建商机数)   
	 */   
	private Integer creatTaskCount = 0;
	
	/**   
	 * @Fields unSignTaskCount : TODO(无法签单商机数)   
	 */   
	private Integer unSignTaskCount = 0;
	
	/**   
	 * @Fields manualDistribution : TODO(手动分配数)   
	 */   
	private Integer manualDistribution = 0;

	/**
	 * @Fields noTaskCustomerCount : TODO(无商机客户数)
	 */
	private Integer noTaskCustomerCount;

	/**
	 * @Fields unAllotTaskCount : TODO(未分配商机数)
	 */
	private Integer unAllotTaskCount;

	/**
	 * @Fields unStartTaskCount : TODO(未启动商机数)
	 */
	private Integer unStartTaskCount;

	/**
	 * @Fields openSeasCount : TODO(公海数)
	 */
	private Integer openSeasCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProductCategoryId1Name() {
		return productCategoryId1Name;
	}

	public void setProductCategoryId1Name(String productCategoryId1Name) {
		this.productCategoryId1Name = productCategoryId1Name;
	}

	public Integer getProductCategoryId1() {
		return productCategoryId1;
	}

	public void setProductCategoryId1(Integer productCategoryId1) {
		this.productCategoryId1 = productCategoryId1;
	}

	public Dict getProductCategory1() {
		return productCategory1;
	}

	public void setProductCategory1(Dict productCategory1) {
		this.productCategory1 = productCategory1;
	}

	public String getProductCategoryId2Name() {
		return productCategoryId2Name;
	}

	public void setProductCategoryId2Name(String productCategoryId2Name) {
		this.productCategoryId2Name = productCategoryId2Name;
	}

	public Integer getProductCategoryId2() {
		return productCategoryId2;
	}

	public void setProductCategoryId2(Integer productCategoryId2) {
		this.productCategoryId2 = productCategoryId2;
	}

	public Dict getProductCategory2() {
		return productCategory2;
	}

	public void setProductCategory2(Dict productCategory2) {
		this.productCategory2 = productCategory2;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCreatCustomerCount() {
		return creatCustomerCount;
	}

	public void setCreatCustomerCount(Integer creatCustomerCount) {
		this.creatCustomerCount = creatCustomerCount;
	}

	public Integer getCreatTaskCount() {
		return creatTaskCount;
	}

	public void setCreatTaskCount(Integer creatTaskCount) {
		this.creatTaskCount = creatTaskCount;
	}

	public Integer getUnSignTaskCount() {
		return unSignTaskCount;
	}

	public void setUnSignTaskCount(Integer unSignTaskCount) {
		this.unSignTaskCount = unSignTaskCount;
	}

	public Integer getManualDistribution() {
		return manualDistribution;
	}

	public void setManualDistribution(Integer manualDistribution) {
		this.manualDistribution = manualDistribution;
	}

	public Integer getNoTaskCustomerCount() {
		return noTaskCustomerCount;
	}

	public void setNoTaskCustomerCount(Integer noTaskCustomerCount) {
		this.noTaskCustomerCount = noTaskCustomerCount;
	}

	public Integer getUnAllotTaskCount() {
		return unAllotTaskCount;
	}

	public void setUnAllotTaskCount(Integer unAllotTaskCount) {
		this.unAllotTaskCount = unAllotTaskCount;
	}

	public Integer getUnStartTaskCount() {
		return unStartTaskCount;
	}

	public void setUnStartTaskCount(Integer unStartTaskCount) {
		this.unStartTaskCount = unStartTaskCount;
	}

	public Integer getOpenSeasCount() {
		return openSeasCount;
	}

	public void setOpenSeasCount(Integer openSeasCount) {
		this.openSeasCount = openSeasCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
