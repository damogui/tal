package com.gongsibao.igirl.service.builder;
import java.util.ArrayList;
import java.util.List;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.igirl.service.builder.base.AbstractSwitchBuilder;
import com.gongsibao.igirl.service.builder.base.AttachmentBuilderManager;
import com.gongsibao.igirl.service.builder.base.ISwitch;
/**
 * 
 * @author jy
 * 
 * 颜色组合生成附件
 * -------------上传
 * 彩色商标图样
 *彩色委托书
 *--------------下载
 *彩色委托书
 */
public class ColorfulAttachmentBuilder extends AbstractSwitchBuilder{
	@Override
	public boolean isOpen(TradeMark tm) {
		// TODO Auto-generated method stub
		return tm.getHasColor();
	}

	@Override
	public List<UploadAttachment> buildUps(TradeMark tm) {
		// TODO Auto-generated method stub
		List<UploadAttachment> ups=new ArrayList<UploadAttachment>();
		UploadAttachment attachment1 = AttachmentBuilderManager.buildUploadAttachment(tm.getMemo() + "彩色_商标图样",
				AttachmentCat.TRADEMARK_PICT, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tm.getId(),tm.getShareGroup());
		ups.add(attachment1);

		attachment1 =  AttachmentBuilderManager.buildUploadAttachment(tm.getMemo() + "_彩色委托书",
				AttachmentCat.DELEGATE_PROOF, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tm.getId(),tm.getShareGroup());
		ups.add(attachment1);
    return ups;
	}

	@Override
	public List<DownloadAttachment> buildDowns(TradeMark tm) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		DownloadAttachment attachment1 =AttachmentBuilderManager.buildDownloadAttachment(tm.getMemo() + "_彩色委托书",
				AttachmentCat.DELEGATE_PROOF, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tm.getId(),tm.getShareGroup());
		ds.add(attachment1);	
		return ds;
	}




}
