package com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Supplier;
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
@Table(name = "n_crm_customer_product_map", header = "客户意向产品")
public class ImNCustomerProduct extends Persistable implements IEntity {

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

    @Column(name = "supplier_id", header = "分配服务商Id")
    private Integer supplierId;

    @Reference(foreignKey = "supplierId", header = "分配服务商")
    private Supplier supplier;

    @JsonIgnore
    @Reference(foreignKey = "customerId", header = "客户")
    private NCustomer customer;

    @Column(name = "customer_id", header = "客户")
    private Integer customerId;

    @JsonIgnore
    @Reference(foreignKey = "taskId", header = "客户任务")
    private NCustomerTask task;

    @Column(name = "task_id", header = "客户任务Id")
    private Integer taskId;

    @Column(name = "product_category_id_1")
    private Integer productCategoryId1;

    @Reference(foreignKey = "productCategoryId1",  header = "产品一级分类")
    private Dict productCategory1;

    @Column(name = "product_category_id_2")
    private Integer productCategoryId2;

    @Reference(foreignKey = "productCategoryId2", header = "产品二级分类")
    private Dict productCategory2;

    @Column(name = "product_id")
    private Integer productId;

    @Reference(foreignKey = "productId", header = "产品")
    private Product product;

    @Column(name = "province_id")
    private Integer provinceId;

    @Reference(foreignKey = "provinceId", header = "省份")
    private Dict province;

    @Column(name = "city_id")
    private Integer cityId;

    @Reference(foreignKey = "cityId", header = "城市")
    private Dict city;

    @Column(name = "county_id")
    private Integer countyId;

    @Reference(foreignKey = "countyId",header = "区/县")
    private Dict county;



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

    public NCustomerTask getTask() {
        return task;
    }

    public void setTask(NCustomerTask task) {
        this.task = task;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Dict getProvince() {
        return province;
    }

    public void setProvince(Dict province) {
        this.province = province;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Dict getCity() {
        return city;
    }

    public void setCity(Dict city) {
        this.city = city;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Dict getCounty() {
        return county;
    }

    public void setCounty(Dict county) {
        this.county = county;
    }
}
