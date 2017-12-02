package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_company_validity")
public class CompanyValidity extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2028359047581885875L;
	@Column(name="company_id",header="CompanyId")
    private Integer companyId;
    @Column(name="product_id",header="ProductId")
    private Integer productId;
    @Column(header="image")
    private String image;
    @Column(name="handler_time",header="HandlerTime")
    private Timestamp handlerTime;
    @Column(name="end_time",header="EndTime")
    private Timestamp endTime;
    @Column(name="add_time",header="AddTime")
    private Timestamp addTime;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Timestamp getHandlerTime() {
        return handlerTime;
    }
    public void setHandlerTime(Timestamp handlerTime) {
        this.handlerTime = handlerTime;
    }
    public Timestamp getEndTime() {
        return endTime;
    }
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
}