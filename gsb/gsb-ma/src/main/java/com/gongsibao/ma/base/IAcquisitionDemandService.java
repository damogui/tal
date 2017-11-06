package com.gongsibao.ma.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.dic.DemandSoldOutState;

public interface IAcquisitionDemandService extends IPersistableService<AcquisitionDemand> {

	/**   
	 * @Title: changeSoldOutState   
	 * @Description: TODO(更新上下架状态)   
	 * @param: @param id
	 * @param: @param soldOutState
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	boolean changeSoldOutState(int id,DemandSoldOutState soldOutState);
}
