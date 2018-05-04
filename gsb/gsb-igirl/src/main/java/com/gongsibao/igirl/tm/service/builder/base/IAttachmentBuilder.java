package com.gongsibao.igirl.tm.service.builder.base;

import java.util.List;

import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.UploadAttachment;

public interface IAttachmentBuilder {
      public List<UploadAttachment> buildUploads(TradeMark tm,TradeMarkCase tmc);
      public List<DownloadAttachment> buildDownloads(TradeMark tm,TradeMarkCase tmc);
}
