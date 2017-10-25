package org.netsharp.api.dto;

import java.math.BigDecimal;

/**
 * @author hw
 * 服务项
 */
public class GoodsDTO extends BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1931988684440679949L;

	/**
	 * 名称
	 */
	private String name = "";

	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 工时
	 */
	private Integer duration = 0;
	
	/**
	 * 描述
	 */
	private String description = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
