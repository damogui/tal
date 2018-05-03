package com.gongsibao.igirl.tm.base;

import com.gongsibao.entity.igirl.tm.ChangeTradeMark;
import com.gongsibao.igirl.tm.dto.ChangeTradeMarkDto;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface IChangeTradeMarkService extends IPersistableService<ChangeTradeMark> {
    List<ChangeTradeMarkDto> ctmToRobot();

    ChangeTradeMark updateCtmState(String agentFileNum,Integer state);

    ChangeTradeMark updateOwner(Integer ctmId,Integer ownerId);
}
