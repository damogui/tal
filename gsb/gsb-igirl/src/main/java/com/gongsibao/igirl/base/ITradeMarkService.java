package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.igirl.dto.TradeMark.TradeMarkApplyInfo;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface ITradeMarkService extends IPersistableService<TradeMark> {
      Boolean  checkShareGroupIsModifyed(TradeMark persistedMark);
      Boolean  checkTargetShareGroupIsExisted(TradeMark persistedMark);
      void deleteAttachmentByTradeMarkId(Integer tmid);
      Boolean  checkAttachmentIsExisted(Integer tmid);
      void addAttachmentByTradeMark(TradeMark tm);
      void deleteColorfulTradeMarkPict(TradeMark tm);
      void addColorfulTradeMarkPict(TradeMark tm);

      List<TradeMarkApplyInfo> tmsForRobot(Integer innerHour);

      String updateMarkState(String ids,String type);
}