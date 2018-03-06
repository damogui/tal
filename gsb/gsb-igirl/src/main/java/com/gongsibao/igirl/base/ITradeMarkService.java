package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.igirl.dto.TradeMark.TmForRobotDto;

import org.netsharp.attachment.Attachment;
import org.netsharp.base.IPersistableService;


public interface ITradeMarkService extends IPersistableService<TradeMark> {
    

      TmForRobotDto tmsForRobot(Integer innerHour);

      String updateMarkState(String ids,Integer type);

      TradeMark tmRobotUpdateMarkState(String proxyCode,Integer stateCode);

      TradeMark tmRobotUpdateMarkCode(String proxyCode,String code,Integer stateCode);
      
      public void updateMarkStateByUploadFiles(Attachment entity,String markcode,String state);
}