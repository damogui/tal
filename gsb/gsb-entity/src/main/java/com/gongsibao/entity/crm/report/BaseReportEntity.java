package com.gongsibao.entity.crm.report;

import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(isView=true, name = "")
public class BaseReportEntity extends Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
     * @Fields date : TODO(日期)   
     */   
    private String date;
	
	/**   
	 * @Fields orgName : TODO(分配服务商部门名称)   
	 */   
	private String departmentName;
    
	private Integer departmentId;
	
    @Reference(foreignKey = "departmentId", header = "分配服务商部门")
	private SupplierDepartment department;
	
	/**   
	 * @Fields isLeaf : TODO(是否末节点)   
	 */   
	private Boolean isLeaf;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
	
	
}
