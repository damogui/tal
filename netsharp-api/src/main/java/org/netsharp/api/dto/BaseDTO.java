package org.netsharp.api.dto;

import java.io.Serializable;


public abstract class BaseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8778464920745455891L;
	/**
	 * 主键
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	public BaseDTO toDTO(IEntity entity){
//		
//		this.setId(entity.getId());
//		
//		return this;
//	}
	
//	public IEntity toEntity(BaseDTO dto){
//
//	}
}
