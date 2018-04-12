package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

import java.util.List;

@Table(name="cms_product_aggregation",header="")
public class ProductAggregation extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6570890560339636600L;
	
	@Column(name="parent_id",header="")
    private Integer parentId;
	
    @Column(name="cms_product_id",header="")
    private Integer cmsProductId;
    
    @Column(name="name",header="")
    private String name;
    
    @Column(name="sort",header="")
    private Double sort;
    
    @Column(name="remark",header="")
    private String remark;

    /*cms表和聚合表的中间集合*/
    @Exclusive
    private List<ProductAggregationMap> cmsProductAggregationMapList;
    @Exclusive
    private List<ProductAggregation> children;

    public List<ProductAggregationMap> getCmsProductAggregationMapList() {
        return cmsProductAggregationMapList;
    }

    public void setCmsProductAggregationMapList(List<ProductAggregationMap> cmsProductAggregationMapList) {
        this.cmsProductAggregationMapList = cmsProductAggregationMapList;
    }

    public List<ProductAggregation> getChildren() {
        return children;
    }

    public void setChildren(List<ProductAggregation> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getCmsProductId() {
        return cmsProductId;
    }
    public void setCmsProductId(Integer cmsProductId) {
        this.cmsProductId = cmsProductId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}