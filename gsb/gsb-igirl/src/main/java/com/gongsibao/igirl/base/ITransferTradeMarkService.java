package com.gongsibao.igirl.base;

import com.gongsibao.entity.igirl.TransferTradeMark;
import com.gongsibao.igirl.dto.TransferTradeMarkDto;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface ITransferTradeMarkService  extends IPersistableService<TransferTradeMark> {
    List<TransferTradeMarkDto> ctmToRobot();

    TransferTradeMark updateTtmState(String agentFileNum, Integer state);
}
