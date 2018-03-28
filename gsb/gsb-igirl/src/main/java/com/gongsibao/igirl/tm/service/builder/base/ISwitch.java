package com.gongsibao.igirl.tm.service.builder.base;

import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;

public interface ISwitch {
	public boolean isOpen(TradeMark tm,TradeMarkCase tmc);

}
