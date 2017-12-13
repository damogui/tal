package com.gongsibao.tools.temp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.pcc.base.IProvinceCityCountyService;
import org.netsharp.pcc.entity.ProvinceCityCounty;
import org.netsharp.util.JsonManage;

public class CityTest {

	IProvinceCityCountyService service = ServiceFactory.create(IProvinceCityCountyService.class);
	
	@Test
	public  void run() {
		
		List<CityDTO> dtoList = new ArrayList<CityDTO>();
		List<ProvinceCityCounty> pList = service.queryProvince();
		for (ProvinceCityCounty p:pList) {

			List<ProvinceCityCounty> cList = service.queryPcc(p.getId());
			
			CityDTO dto = getDto(p);
			List<CityDTO> subList = getDtoList(cList);
			dto.setSub(subList);
			dtoList.add(dto);
		}
		
		String json = JsonManage.serialize2(dtoList);
		System.out.println(json);
	}
	
	private List<CityDTO> getDtoList(List<ProvinceCityCounty> pccList){
		
		List<CityDTO> dtoList = new ArrayList<CityDTO>();
		for (ProvinceCityCounty c:pccList) {
			
			List<ProvinceCityCounty> dList = service.queryPcc(c.getId());
			CityDTO dto = this.getDto(c);
			List<CityDTO> subList = getDtoList(dList);
			dto.setSub(subList);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	
	private CityDTO getDto(ProvinceCityCounty pcc){
		
		CityDTO dto = new CityDTO();
		dto.setName(pcc.getName());
		dto.setCode(pcc.getId().toString());
		
		return dto;
	}
}
