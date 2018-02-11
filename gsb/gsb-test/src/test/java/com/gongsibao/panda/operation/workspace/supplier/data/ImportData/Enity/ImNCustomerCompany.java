package com.gongsibao.panda.operation.workspace.supplier.data.ImportData.Enity;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.Persistable;

import java.util.Date;

/**
 * Created by win on 2018/2/11.
 */
@Table(name="n_crm_customer_company_map",header="客户关联企业")
public class ImNCustomerCompany extends Persistable implements IEntity {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 4684375504055933956L;


    @Id

    @Column(name = "id", header = "主键")
    private Integer id;

    @Column(name = "creator_id", header = "创建人ID")
    private Integer creatorId;

    @Column(name = "creator", header = "创建人名称")
    private String creator;

    @Column(name = "create_time", header = "创建时间")
    private Date createTime;

    @Column(name = "updator_id", header = "更新人ID")
    private Integer updatorId;

    @Column(name = "updator", header = "更新人名称")
    private String updator;

    @Column(name = "update_time", header = "更新时间")
    private Date updateTime;

    //private Date ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//	public Date getTs() {
//		return ts;
//	}
//
//	public void setTs(Date ts) {
//		this.ts = ts;
//	}

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Integer updatorId) {
        this.updatorId = updatorId;
    }

    public boolean hasId() {
        Mtable meta = MtableManager.getMtable(this.getClass());
        return !meta.getId().isEmpty(this.id);
    }
    @JsonIgnore
    @Reference(foreignKey = "customerId", header = "客户")
    private NCustomer customer;

    @Column(name = "customer_id", header = "客户")
    private Integer customerId;

    @Column(name="company_id",header="")
    private Integer companyId;

    @Reference(foreignKey="companyId",header="")
    private CompanyIntention company;

    @Column(name = "supplier_id", header = "分配服务商Id")
    private Integer supplierId;

    @Reference(foreignKey = "supplierId", header = "分配服务商")
    private Supplier supplier;

    @Column(name = "department_id", header = "分配服务商部门Id")
    private Integer departmentId;

    @Reference(foreignKey = "departmentId", header = "分配服务商部门")
    private SupplierDepartment department;

    public NCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(NCustomer customer) {
        this.customer = customer;
    }

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

    public CompanyIntention getCompany() {
        return company;
    }

    public void setCompany(CompanyIntention company) {
        this.company = company;
    }

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


}
