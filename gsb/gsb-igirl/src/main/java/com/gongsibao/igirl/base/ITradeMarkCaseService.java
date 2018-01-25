package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.TradeMarkCase;
import org.netsharp.base.IPersistableService;

public interface ITradeMarkCaseService extends IPersistableService<TradeMarkCase> {
	public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile);
	
	

}