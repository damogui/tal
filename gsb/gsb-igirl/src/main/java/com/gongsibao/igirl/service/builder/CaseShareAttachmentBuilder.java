package com.gongsibao.igirl.service.builder;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.ApplierType;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.igirl.service.builder.base.AttachmentBuilderManager;
import com.gongsibao.igirl.service.builder.base.IAttachmentBuilder;
/**
 * @author jy
 * -------------上传
 *负责创建当前商标共享组对应的基本附件集合
 *黑白商标图样
 *委托书
 *其它证明
 *--------------下载
 *黑白委托书
 */
public class CaseShareAttachmentBuilder implements IAttachmentBuilder {
	//附件营业执照商标ID赋值为-1，因为多个商标共享
	public final static Integer TradeMarkBizLienseID = -1;
	//付款证明商标ID为赋予值为-2，因为多个商标共享
	public final static Integer TradeMarkPayProofID = -2;
	
	//身份证明商标ID为赋予值为-3，因为多个商标共享
	public final static Integer PersonMarkProofID = -3;
	@Override
	public List<UploadAttachment> buildUploads(TradeMark tm,TradeMarkCase tmc) {
		List<UploadAttachment> ups=new ArrayList<UploadAttachment>();
		// TODO Auto-generated method stub
		UploadAttachment attachment2 =  AttachmentBuilderManager.buildUploadAttachment("营业执照",
				AttachmentCat.BUSINESS_LIEN, tm.getTradeMarkCaseId(), FileType.PDF, FileType.PDF, TradeMarkBizLienseID,ShareGroup.CASESHARRE);
		//attachment2.setTradeMarkCase(tm.getTradeMarkCase());
		ups.add(attachment2);
		if(tmc.getApplierType()==ApplierType.PRIVATE) {
			attachment2 = (UploadAttachment) AttachmentBuilderManager.buildUploadAttachment("身份证明", AttachmentCat.PERSON_PROOF,
					tm.getTradeMarkCaseId(), FileType.PDF, FileType.PDF, PersonMarkProofID,ShareGroup.CASESHARRE);
			ups.add(attachment2);
		}
		attachment2 = (UploadAttachment) AttachmentBuilderManager.buildUploadAttachment("付款证明", AttachmentCat.PAYMENT_PROOF,
				tm.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, TradeMarkPayProofID,ShareGroup.CASESHARRE);
		//attachment2.setTradeMarkCase(tm.getTradeMarkCase());
		//默认设置为不需要传
		attachment2.setNeeded(false);
		ups.add(attachment2);
		return ups;
	}

	@Override
	public List<DownloadAttachment> buildDownloads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		return null;
	}

}
