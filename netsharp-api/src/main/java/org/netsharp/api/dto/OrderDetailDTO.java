package org.netsharp.api.dto;

import java.math.BigDecimal;

/**
 * @author hw
 * 订单明细
 */
public class OrderDetailDTO extends BaseDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6098317021230943692L;

	/**
     * 订单Id
     */
    private Long orderId;

    /**
     * 服务项Id
     */
    private Long goodsId;

    /**
     * 名称
     */
    private String name = "";

    /**
     * 服务项分类
     */
    private Long goodsClassId;
    
    /**
     * 服务项类型
     */
    private Long goodsTypeId;
    
    /**
     * 原价单价
     */
    private BigDecimal originalPrice = BigDecimal.ZERO;
    
    
    /**
     * 结算单价
     */
    private BigDecimal price = BigDecimal.ZERO;


    /**
     * 数量
     */
    private BigDecimal quantity = BigDecimal.ONE;

    
    /**
     * 原价金额
     */
    private BigDecimal originalAmount = BigDecimal.ZERO; 
    
    
    /**
     * 结算金额
     */
    private BigDecimal amount;

    
    /**
     * 描述
     */
    private String  description = "";

    /**
     * 工时
     */
    private Integer duration  = 0;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGoodsClassId() {
		return goodsClassId;
	}

	public void setGoodsClassId(Long goodsClassId) {
		this.goodsClassId = goodsClassId;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
