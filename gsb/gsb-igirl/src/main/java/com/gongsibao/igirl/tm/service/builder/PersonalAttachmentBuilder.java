package com.gongsibao.igirl.tm.service.builder;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.entity.igirl.tm.dict.ApplierType;
import com.gongsibao.entity.igirl.tm.dict.AttachmentCat;
import com.gongsibao.entity.igirl.tm.dict.FileType;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;
import com.gongsibao.igirl.tm.service.builder.base.AbstractSwitchBuilder;
import com.gongsibao.igirl.tm.service.builder.base.AttachmentBuilderManager;
/**
 * 
 * @author jy
 * 
 * 自然人身份证明
 */
public class PersonalAttachmentBuilder extends AbstractSwitchBuilder{
	ITradeMarkCaseService ts=ServiceFactory.create(ITradeMarkCaseService.class);
	@Override
	public boolean isOpen(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		boolean p=tmc.getApplierType()==ApplierType.PRIVATE;
		return p;
	}

	@Override
	public List<UploadAttachment> buildUps(TradeMark tm) {
		// TODO Auto-generated method stub
		List<UploadAttachment> ups=new ArrayList<UploadAttachment>();
		UploadAttachment attachment1 = AttachmentBuilderManager.buildUploadAttachment(tm.getMemo() + "_身份证明",
				AttachmentCat.PERSON_PROOF, tm.getTradeMarkCaseId(), FileType.PDF, FileType.PDF, tm.getId(),tm.getShareGroup());
		ups.add(attachment1);

    return ups;
	}

	@Override
	public List<DownloadAttachment> buildDowns(TradeMark tm) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		return ds;
	}
}
