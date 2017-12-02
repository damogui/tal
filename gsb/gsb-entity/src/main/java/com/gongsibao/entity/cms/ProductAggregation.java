package com.gongsibao.entity.cms;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

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
    private String name;
    private Double sort;
    private String remark;

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