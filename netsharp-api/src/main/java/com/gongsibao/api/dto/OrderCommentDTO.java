package com.gongsibao.api.dto;

/**
 * @author hw
 * 订单评论
 */
public class OrderCommentDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4756205458279961295L;

	/**
	 * 订单ID
	 */
	private Long orderId;
	
	/**
	 * 订单明细ID
	 */
	private Long orderDetailId;
	
    /**
     * 服务项分类
     */
    private Long goodsClassId;
    
    /**
     * 服务项类型
     */
    private Long goodsTypeId;
    
    /**
     * 服务项Id
     */
    private Long goodsId;   
	
	/**
	 * 门店ID
	 */
	private Long shopId;
	
	/**
	 * 评分
	 */
	private int score = 0;
	
	/**
	 * 内容
	 */
	private String content;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
}
