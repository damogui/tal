package com.gongsibao.igirl.service.builder.base;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;

public abstract class AbstractSwitchBuilder implements ISwitch,IAttachmentBuilder{

	@Override
	public List<UploadAttachment> buildUploads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> uas=new ArrayList<UploadAttachment>();
		if(this.isOpen(tm,tmc)) {
			return this.buildUps(tm);
		}
		return uas;
	}
	@Override
	public List<DownloadAttachment> buildDownloads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		if(this.isOpen(tm,tmc)) {
			return this.buildDowns(tm);
		}
		return ds;
	}

	@Override
	public boolean isOpen(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		return false;
	}
	public abstract List<UploadAttachment> buildUps(TradeMark tm);
	public abstract List<DownloadAttachment> buildDowns(TradeMark tm);

}
