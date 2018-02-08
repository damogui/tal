package com.gongsibao.igirl.service.builder.base;

import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;

public interface ISwitch {
	public boolean isOpen(TradeMark tm,TradeMarkCase tmc);

}
