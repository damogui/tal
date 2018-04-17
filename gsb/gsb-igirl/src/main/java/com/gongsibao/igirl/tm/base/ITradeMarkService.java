package com.gongsibao.igirl.tm.base;
import java.util.List;

import org.netsharp.attachment.Attachment;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.igirl.tm.dto.AbnormalNoticeDto;
import com.gongsibao.igirl.tm.dto.SysAttachmentDto;
import com.gongsibao.igirl.tm.dto.TradeMark.TradeMarkApplyInfo;


public interface ITradeMarkService extends IPersistableService<TradeMark> {


      List<TradeMarkApplyInfo> tmsForRobot(Integer innerHour);

      String updateMarkState(String ids,Integer type);

      Integer tmRobotUpdateMarkState(String proxyCode,Integer stateCode);

      Integer tmRobotUpdateMarkCode(String proxyCode,String code,Integer stateCode);
      
      public void updateMarkStateByUploadFiles(Attachment entity,String markcode,String state);
      
      List<SysAttachmentDto> findUrlById(int caseId);
      
      List<AbnormalNoticeDto> getAbnormalNotice();

}