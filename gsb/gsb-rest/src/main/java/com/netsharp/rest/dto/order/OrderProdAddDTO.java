package com.netsharp.rest.dto.order;

import com.netsharp.rest.dto.product.ProductPriceDTO;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date $ $
 */
public class OrderProdAddDTO implements Serializable {

    private static final long serialVersionUID = 85986305035205911L;

    /* 产品id */
    private Integer productId;
    /* 城市id */
    private Integer cityId;
    /* 购买数量 */
    private Integer quantity;
    /* 定价信息 */
    private List<ProductPriceDTO> priceList;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<ProductPriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<ProductPriceDTO> priceList) {
        this.priceList = priceList;
    }
}
