package com.gongsibao.entity.bd;

import com.gongsibao.entity.BaseEntity;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name = "bd_service_product", header = "服务产品列表")
public class BdServiceProduct extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "service_id", header = "服务名称 1工商, 2商标, 3著作权,4专利,5企业资质")
    private int serviceId;

    @Column(name = "product_id", header = "商品id")
    private int productId;

    @Column(name = "name", header = "服务名称")
    private String name;

    @Column(name = "img", header = "icon路径")
    private String img;

    @Column(name = "hot", header = "是否热门 -1全部 0否 1是，默认-1 ")
    private int hot;

    //商品pc版展示图标
    @Column(name = "pc_img", header = "商品pc版展示图标")
    private String pcImg;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

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

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getPcImg() {
        return pcImg;
    }

    public void setPcImg(String pcImg) {
        this.pcImg = pcImg;
    }
}
