package com.gongsibao.igirl.base;
import java.util.List;

import org.netsharp.attachment.Attachment;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.igirl.dto.TradeMark.TradeMarkApplyInfo;


public interface ITradeMarkService extends IPersistableService<TradeMark> {


      List<TradeMarkApplyInfo> tmsForRobot(Integer innerHour);

      String updateMarkState(String ids,Integer type);

      TradeMark tmRobotUpdateMarkState(String proxyCode,Integer stateCode);

      TradeMark tmRobotUpdateMarkCode(String proxyCode,String code,Integer stateCode);
      
      public void updateMarkStateByUploadFiles(Attachment entity,String markcode,String state);
}