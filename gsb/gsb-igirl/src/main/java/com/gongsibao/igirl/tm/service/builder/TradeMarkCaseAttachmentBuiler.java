package com.gongsibao.igirl.tm.service.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.core.EntityState;

import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.entity.igirl.tm.dict.ShareGroup;
import com.gongsibao.igirl.tm.service.builder.base.AttachmentBuilderManager;

public class TradeMarkCaseAttachmentBuiler{
	
	public static AttachmentBuilderManager abCaseShareManager=new AttachmentBuilderManager();
	public static AttachmentBuilderManager abMarkShareGroupManager=new AttachmentBuilderManager();
	public TradeMarkCaseAttachmentBuiler() {
		abCaseShareManager.getUpbuilders().add(new CaseShareAttachmentBuilder());
		abMarkShareGroupManager.getUpbuilders().add(new CommonAttachmentBuilder());//商标共享组通用附件
		abMarkShareGroupManager.getUpbuilders().add(new ColorfulAttachmentBuilder());//商标共享组彩色附件
		//abMarkShareGroupManager.getUpbuilders().add(new PersonalAttachmentBuilder());//商标共享组彩色附件
		
		abMarkShareGroupManager.getDownbuilders().add(new CommonAttachmentBuilder());//商标共享组通用附件
		abMarkShareGroupManager.getDownbuilders().add(new ColorfulAttachmentBuilder());//商标共享组彩色附件
	
	}

	public List<UploadAttachment> buildCaseShareUploads(TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> uas=new ArrayList<UploadAttachment>();
		if(tmc.getTradeMarks().size()==0 && tmc.getEntityState()==EntityState.New) {
			TradeMark tmTmp=new TradeMark();
			tmTmp.setTradeMarkCaseId(tmc.getId());
			uas.addAll(abCaseShareManager.buildUploads(tmTmp,tmc));
		}else {
			for(TradeMark tm : tmc.getTradeMarks()) {
				if(tm.getEntityState()!=EntityState.Deleted) {
					uas.addAll(abCaseShareManager.buildUploads(tm,tmc));
					break;
				}
			}	
		}
		return uas;
	}
	
	public List<UploadAttachment> buildMarkShareGroupUploadsByTm(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> uas=new ArrayList<UploadAttachment>();
		uas.addAll(abMarkShareGroupManager.buildUploads(tm,tmc));
		return uas;
	}
	public List<DownloadAttachment> buildMarkShareGroupDownloadsByTm(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		ds.addAll(abMarkShareGroupManager.buildDownloads(tm,tmc));
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
					uas.addAll(abMarkShareGroupManager.buildUploads(tm,tmc));
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
					ds.addAll(abMarkShareGroupManager.buildDownloads(tm,tmc));
				}
				
			}
		}
		return ds;
	}
	

}
