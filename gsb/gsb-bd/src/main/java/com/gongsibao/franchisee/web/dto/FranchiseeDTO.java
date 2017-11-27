package com.gongsibao.franchisee.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.franchisee.Franchisee;


public class FranchiseeDTO {

    private int id;
    
    private String name;
    
    private String trackProgress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrackProgress() {
		return trackProgress;
	}

	public void setTrackProgress(String trackProgress) {
		this.trackProgress = trackProgress;
	}
	
	public static List<FranchiseeDTO> toDtoList(List<Franchisee> list){
		
		List<FranchiseeDTO> dtoList = new ArrayList<FranchiseeDTO>();
		for(Franchisee franchisee :list){
			
			FranchiseeDTO dto = new FranchiseeDTO();{
				
				dto.setId(franchisee.getId());
				dto.setName(franchisee.getName());
				
				if(franchisee.getTrackProgress() != null){

					dto.setTrackProgress(franchisee.getTrackProgress().getText());
				}
			}
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}
