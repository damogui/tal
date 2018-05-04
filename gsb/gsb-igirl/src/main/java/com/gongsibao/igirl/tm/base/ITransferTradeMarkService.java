package com.gongsibao.igirl.tm.base;

import com.gongsibao.entity.igirl.tm.TransferTradeMark;
import com.gongsibao.igirl.tm.dto.TransferTradeMarkDto;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface ITransferTradeMarkService  extends IPersistableService<TransferTradeMark> {
    List<TransferTradeMarkDto> ctmToRobot();

    TransferTradeMark updateTtmState(String agentFileNum, Integer state);

    TransferTradeMark updateOwner(Integer ttmId,Integer ownerId);
}
