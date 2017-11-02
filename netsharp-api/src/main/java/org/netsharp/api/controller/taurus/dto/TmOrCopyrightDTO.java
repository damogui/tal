package org.netsharp.api.controller.taurus.dto;

import java.util.List;

import com.gongsibao.taurus.entity.Copyright;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.entity.WorksCopyright;

public class TmOrCopyrightDTO {

	List<Tm> tmList;

	List<Copyright> copyrightList;

	List<WorksCopyright> worksCopyrightList;

	public List<Tm> getTmList() {
		return tmList;
	}

	public void setTmList(List<Tm> tmList) {
		this.tmList = tmList;
	}

	public List<Copyright> getCopyrightList() {
		return copyrightList;
	}

	public void setCopyrightList(List<Copyright> copyrightList) {
		this.copyrightList = copyrightList;
	}

	public List<WorksCopyright> getWorksCopyrightList() {
		return worksCopyrightList;
	}

	public void setWorksCopyrightList(List<WorksCopyright> worksCopyrightList) {
		this.worksCopyrightList = worksCopyrightList;
	}
	
	
}
