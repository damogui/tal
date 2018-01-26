package com.gongsibao.entity.supplier;

import com.gongsibao.entity.bd.Dict;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/1/26.
 */
@Table(name="sp_service_product",header="服务商部门的服务地区")
public class DepartmentServiceProduct  extends Entity {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -6865133330856235904L;

    @Column(name="department_id",header="服务商主键")
    private Integer departmentId;

    @JsonIgnore
    @Reference(foreignKey="departmentId")
    private SupplierDepartment supplierDepartment;

    @Column(name = "product_category_id_1")
    private Integer productCategoryId1;

    @Reference(foreignKey = "productCategoryId1",  header = "产品一级分类")
    private Dict productCategory1;

    @Column(name = "product_category_id_2")
    private Integer productCategoryId2;

    @Reference(foreignKey = "productCategoryId2", header = "产品二级分类")
    private Dict productCategory2;

    @Column(name="product_id")
    private Integer productId;

    @Reference(foreignKey="productId",header="产品")
    private Dict product;

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



    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Dict getProduct() {
        return product;
    }

    public void setProduct(Dict product) {
        this.product = product;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public SupplierDepartment getSupplierDepartment() {
        return supplierDepartment;
    }

    public void setSupplierDepartment(SupplierDepartment supplierDepartment) {
        this.supplierDepartment = supplierDepartment;
    }
}
