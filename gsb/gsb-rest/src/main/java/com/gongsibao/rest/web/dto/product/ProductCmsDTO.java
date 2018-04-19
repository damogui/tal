package com.gongsibao.rest.web.dto.product;

import com.gongsibao.entity.cms.AggregationResponse;
import com.gongsibao.entity.cms.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 产品CMS相关信息
 */
public class ProductCmsDTO implements Serializable{

    private static final long serialVersionUID = 7765701090760226876L;

    /* 产品描述 */
    private String description;
    /* 办理步骤 */
    private List<String> flowList;
    /* 所需材料 */
    private List<String> materialList;
    /* cms产品对象 */
    private Product product;
    /* cms产品聚合信息 */
    private AggregationResponse aggregation;
    /* 显示价格 */
    private String showprice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<String> flowList) {
        this.flowList = flowList;
    }

    public List<String> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<String> materialList) {
        this.materialList = materialList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AggregationResponse getAggregation() {
        return aggregation;
    }

    public void setAggregation(AggregationResponse aggregation) {
        this.aggregation = aggregation;
    }

    public String getShowprice() {
        return showprice;
    }

    public void setShowprice(String showprice) {
        this.showprice = showprice;
    }
}
