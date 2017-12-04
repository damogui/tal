package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="cms_product_view",isView=true)
public class ProductView extends Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Auto
	private Integer id;
	private String serviceClass;
	private String prodName;
	private String showprice;
	private Integer total;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getShowprice() {
		return showprice;
	}
	public void setShowprice(String showprice) {
		this.showprice = showprice;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
}
