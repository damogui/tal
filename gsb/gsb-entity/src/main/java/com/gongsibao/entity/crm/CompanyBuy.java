package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_buy",header="")
public class CompanyBuy extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1235112721795040523L;
	@Column(name="contact_name",header="")
    private String contactName;
	
    @Column(name="contact_mobile",header="")
    private String contactMobile;
    
    @Column(name="condition",header="")
    private String condition;
    
    @Column(name="reg_capital_type",header="")
    private Integer regCapitalType;
    
    @Column(name="set_up_year",header="")
    private Integer setUpYear;
    
    @Column(name="city_id",header="")
    private Integer cityId;
    
    @Column(name="remark",header="")
    private String remark;
    
    @Column(name="status",header="")
    private Integer status;
    
    @Column(name="follow_user_id",header="")
    private Integer followUserId;

}