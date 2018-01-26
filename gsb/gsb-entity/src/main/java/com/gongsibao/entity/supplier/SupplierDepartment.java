package com.gongsibao.entity.supplier;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.wx.qy.department.Department;

import java.util.List;

@Table(name="sp_department",header="服务商部门")
public class SupplierDepartment extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6566344416046564027L;

	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId = 0;

	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;

    @Column(name = "dp_service_id", header = "服务产品id")
    private Integer dpserviceid = 0;
    @Subs(foreignKey = "dpserviceid", header = "部门服务地区范围", subType = DepartmentServiceProduct.class)
    private List<DepartmentServiceProduct> serviceProducts;

    @Column(name = "is_old_client", header = "是否新客户")
    private Boolean isoldclient;


	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


    public Integer getDpserviceid() {
        return dpserviceid;
    }

    public void setDpserviceid(Integer dpserviceid) {
        this.dpserviceid = dpserviceid;
    }

    public List<DepartmentServiceProduct> getServiceProducts() {
        return serviceProducts;
    }

    public void setServiceProducts(List<DepartmentServiceProduct> serviceProducts) {
        this.serviceProducts = serviceProducts;
    }

    public Boolean getIsoldclient() {
        return isoldclient;
    }

    public void setIsoldclient(Boolean isoldclient) {
        this.isoldclient = isoldclient;
    }
}
