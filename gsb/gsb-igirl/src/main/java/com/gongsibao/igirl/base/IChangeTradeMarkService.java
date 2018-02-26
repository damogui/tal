package com.gongsibao.igirl.base;

import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.igirl.dto.ChangeTradeMark.ChangeTradeMarkToRoBotDto;
import org.netsharp.base.IPersistableService;

public interface IChangeTradeMarkService extends IPersistableService<ChangeTradeMark> {
    ChangeTradeMarkToRoBotDto ctmToRobot();
}
