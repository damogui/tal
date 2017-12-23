package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.bd.Dict;

@Table(isView=true, name = "")
public class CustomerDistrictReport extends Persistable{

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
	
	/**   
	 * @Fields orgName : TODO(地区名称)   
	 */   
	private String districtName;
	
    @Reference(foreignKey="districtId")
	private Dict district;

	private Integer districtId;
	
	
	/**   
	 * @Fields newCount : TODO(新增用户数)   
	 */   
	private Integer count = 0;
	
	/**   
	 * @Fields newShareCount : TODO(新增分享数)   
	 */   
	private Integer shareCount = 0;

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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
}
