package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.bd.Dict;

@Table(isView=true, name = "")
public class CustomerDistrictReport extends BaseCustomerReportEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1662389480505010763L;

	/**   
	 * @Fields id : TODO(Id)   
	 */
	@Id
	private Integer id;
	
	/**   
	 * @Fields parentId : TODO(上级Id)   
	 */   
	private Integer parentId;
	
    @Reference(foreignKey="districtId")
	private Dict district;

	private Integer districtId;
	
	/**   
     * @Fields date : TODO(区)   
     */   
    private String zone;
    /**   
     * @Fields date : TODO(市)   
     */   
    private String city;
    /**   
     * @Fields date : TODO(省)   
     */   
    private String province;
    
    
    
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Dict getDistrict() {
		return district;
	}
	public void setDistrict(Dict district) {
		this.district = district;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}
