package com.gongsibao.igirl.service.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.core.EntityState;

import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.igirl.service.builder.base.AttachmentBuilderManager;
import com.gongsibao.igirl.service.builder.base.IAttachmentBuilder;

public class TradeMarkCaseAttachmentBuiler{
	
	public static AttachmentBuilderManager abCaseShareManager=new AttachmentBuilderManager();
	public static AttachmentBuilderManager abMarkShareGroupManager=new AttachmentBuilderManager();
	public TradeMarkCaseAttachmentBuiler() {
		abCaseShareManager.getUpbuilders().add(new CaseShareAttachmentBuilder());
		abMarkShareGroupManager.getUpbuilders().add(new CommonAttachmentBuilder());//商标共享组通用附件
		abMarkShareGroupManager.getUpbuilders().add(new ColorfulAttachmentBuilder());//商标共享组彩色附件
		abMarkShareGroupManager.getDownbuilders().add(new CommonAttachmentBuilder());//商标共享组通用附件
		abMarkShareGroupManager.getDownbuilders().add(new ColorfulAttachmentBuilder());//商标共享组彩色附件
	}

	public List<UploadAttachment> buildCaseShareUploads(TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> uas=new ArrayList<UploadAttachment>();
		if(tmc.getTradeMarks().size()>0 && tmc.getEntityState()==EntityState.New) {
			uas.addAll(abCaseShareManager.buildUploads(tmc.getTradeMarks().get(0)));
		}else {
			for(TradeMark tm : tmc.getTradeMarks()) {
				if(tm.getEntityState()!=EntityState.Deleted) {
					uas.addAll(abCaseShareManager.buildUploads(tm));
					break;
				}
			}	
		}
		return uas;
	}
	
	public List<UploadAttachment> buildMarkShareGroupUploadsByTm(TradeMark tm) {
		// TODO Auto-generated method stub
		List<UploadAttachment> uas=new ArrayList<UploadAttachment>();
		uas.addAll(abMarkShareGroupManager.buildUploads(tm));
		return uas;
	}
	public List<DownloadAttachment> buildMarkShareGroupDownloadsByTm(TradeMark tm) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		ds.addAll(abMarkShareGroupManager.buildDownloads(tm));
		return ds;
	}
	
	public List<UploadAttachment> buildMarkShareGroupUploads(TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> uas=new ArrayList<UploadAttachment>();
		Map<ShareGroup,Integer> mapShareGroupCountMap=new HashMap<ShareGroup,Integer>();
		for(TradeMark tm : tmc.getTradeMarks()) {
			if(!mapShareGroupCountMap.containsKey(tm.getShareGroup())) {
				mapShareGroupCountMap.put(tm.getShareGroup(), 1);
				if(tm.getEntityState()!=EntityState.Deleted) {
					uas.addAll(abMarkShareGroupManager.buildUploads(tm));
				}
			
			}
		}
		return uas;
	}

	public List<DownloadAttachment> buildDownloads(TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		Map<ShareGroup,Integer> mapShareGroupCountMap=new HashMap<ShareGroup,Integer>();
		for(TradeMark tm : tmc.getTradeMarks()) {
			if(!mapShareGroupCountMap.containsKey(tm.getShareGroup())) {
				mapShareGroupCountMap.put(tm.getShareGroup(), 1);
				if(tm.getEntityState()!=EntityState.Deleted) {
					ds.addAll(abMarkShareGroupManager.buildDownloads(tm));
				}
				
			}
		}
		return ds;
	}
	

}
