package com.gongsibao.igirl.service.builder.base;

import java.util.List;

import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;

public interface IAttachmentBuilder {
      public List<UploadAttachment> buildUploads(TradeMark tm,TradeMarkCase tmc);
      public List<DownloadAttachment> buildDownloads(TradeMark tm,TradeMarkCase tmc);
}
