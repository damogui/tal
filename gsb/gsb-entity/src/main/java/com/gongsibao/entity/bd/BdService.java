package com.gongsibao.entity.bd;

import com.gongsibao.entity.BaseEntity;
import org.netsharp.core.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "bd_service", header = "服务列表")
public class BdService extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Auto
    @Column(name="pkid",header="id")
    private Long pkid;

    @Column(name = "name", header = "服务名称")
    private String name;

    @Column(name = "img", header = "icon图片")
    private String img;

    @Column(name = "is_enabled", header = "是否可用 0否 1是")
    private Integer isEnabled;

    @Subs(subType = BdServiceProduct.class, foreignKey = "serviceId", header = "商品列表")
    private List<BdServiceProduct> productList = new ArrayList<BdServiceProduct>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    public List<BdServiceProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<BdServiceProduct> productList) {
        this.productList = productList;
    }
}
