package com.gongsibao.tools.temp;

import java.util.List;

public class CityDTO {

	private String name;
	
	private String code;
	
	private List<CityDTO> sub;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CityDTO> getSub() {
		return sub;
	}

	public void setSub(List<CityDTO> sub) {
		this.sub = sub;
	}
	
	
}
