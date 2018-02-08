package com.gongsibao.igirl.service.builder.base;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.igirl.dict.ShareGroup;

public class AttachmentBuilderManager implements IAttachmentBuilder {


	private List<IAttachmentBuilder> upbuilders = new ArrayList<IAttachmentBuilder>();
	private List<IAttachmentBuilder> downbuilders = new ArrayList<IAttachmentBuilder>();
	@Override
	public List<UploadAttachment> buildUploads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> rtnUas=new ArrayList<UploadAttachment>();
		// TODO Auto-generated method stub
		for(IAttachmentBuilder builder : upbuilders) {
			List<UploadAttachment> uas=builder.buildUploads(tm,tmc);
			rtnUas.addAll(uas);
		}
		return rtnUas;
	}

	@Override
	public List<DownloadAttachment> buildDownloads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> rtnUas=new ArrayList<DownloadAttachment>();
		// TODO Auto-generated method stub
		for(IAttachmentBuilder builder : downbuilders) {
			List<DownloadAttachment> uas=builder.buildDownloads(tm,tmc);
			rtnUas.addAll(uas);
		}
		return rtnUas;
	}
    
	public static UploadAttachment buildUploadAttachment(String name, AttachmentCat ac, Integer tmcid, FileType fileType,
			FileType tofileType, Integer tmid,ShareGroup shareGroupx) {
		UploadAttachment attachment = new UploadAttachment();
		attachment.toNew();
		attachment.setName(name);
		attachment.setAttachmentCat(ac);
		attachment.setTradeMarkCaseId(tmcid);
		attachment.setTradeMarkId(tmid);
		
		// 上传时，修改文件类型
		attachment.setFileType(fileType);
		attachment.setToFileType(tofileType);

		attachment.setMinPx(500);
		attachment.setMaxPx(2000);
		attachment.setMinBytes(100);
		attachment.setMaxBytes(500);
		attachment.setShareGroup(shareGroupx);
		return attachment;
	}
	
	public static DownloadAttachment buildDownloadAttachment(String name, AttachmentCat ac, Integer tmcid, FileType fileType,
			FileType tofileType, Integer tmid,ShareGroup sg) {
		DownloadAttachment attachment = new DownloadAttachment();
		attachment.toNew();
		attachment.setName(name);
		attachment.setAttachmentCat(ac);
		attachment.setTradeMarkCaseId(tmcid);
		attachment.setTradeMarkId(tmid);
		// 上传时，修改文件类型
		attachment.setFileType(fileType);
		attachment.setToFileType(tofileType);

		attachment.setMinPx(500);
		attachment.setMaxPx(2000);
		attachment.setMinBytes(100);
		attachment.setMaxBytes(500);
		attachment.setShareGroup(sg);
		return attachment;
	}

	public List<IAttachmentBuilder> getUpbuilders() {
		return upbuilders;
	}

	public void setUpbuilders(List<IAttachmentBuilder> upbuilders) {
		this.upbuilders = upbuilders;
	}

	public List<IAttachmentBuilder> getDownbuilders() {
		return downbuilders;
	}

	public void setDownbuilders(List<IAttachmentBuilder> downbuilders) {
		this.downbuilders = downbuilders;
	}


}
