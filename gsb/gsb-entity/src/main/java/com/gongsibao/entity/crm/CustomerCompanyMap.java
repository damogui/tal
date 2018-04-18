package com.gongsibao.entity.crm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_customer_company_map",header="客户与企业信息关联表")
public class CustomerCompanyMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1106443484204494839L;
	@Column(name="customer_id",header="")
    private Integer customerId;
	
	@JsonIgnore
    @Reference(foreignKey="customerId",header="",primaryKey="pkid")
    private Customer customer;
    
    @Column(name="company_id",header="")
    private Integer companyId;
    
    @Reference(foreignKey="companyId",header="",primaryKey="pkid")
    private CompanyIntention company;
    
    @Column(name="is_bbk",header="")
    private String isBbk = "0";

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CompanyIntention getCompany() {
		return company;
	}
	public void setCompany(CompanyIntention company) {
		this.company = company;
	}
    
}