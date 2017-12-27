package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(isView=true, name = "")
public class CustomerProductReport extends BaseCustomerReportEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3236961207039594266L;
	 /**   
     * @Fields date : TODO(产品名称)   
     */   
    private String prodName;
    /**   
     * @Fields date : TODO(产品子类别)   
     */   
    private String prodSubClass;
    /**   
     * @Fields date : TODO(产品类别)   
     */   
    private String prodCategory;
    
    
    
    
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdSubClass() {
		return prodSubClass;
	}
	public void setProdSubClass(String prodSubClass) {
		this.prodSubClass = prodSubClass;
	}
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
}
