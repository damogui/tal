package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.igirl.dict.MarkState;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.igirl.dto.TradeMark.Step1;
import com.gongsibao.igirl.dto.TradeMark.TradeMarkApplyInfo;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

@Service
public class TradeMarkService extends GsbPersistableService<TradeMark> implements ITradeMarkService {
	IUploadAttachmentService upattachementService = ServiceFactory.create(IUploadAttachmentService.class);
	IDownloadAttachmentService downattachementService = ServiceFactory.create(IDownloadAttachmentService.class);


	public TradeMarkService() {
		super();
		this.type = TradeMark.class;
	}

	@Override
	public TradeMark newInstance() {
		// TODO Auto-generated method stub
		return super.newInstance();
	}

	@Override
	public Boolean checkShareGroupIsModifyed(TradeMark persistedMark) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		oql.setType(TradeMark.class);
		oql.setSelects("TradeMark.{id,shareGroup}");
		oql.setFilter("id=?");
		oql.getParameters().add("id", persistedMark.getId(), Types.INTEGER);
		TradeMark tmOrigin = this.queryFirst(oql);
		// 说明已经修改共享组
		if (tmOrigin.getShareGroup() != persistedMark.getShareGroup()) {
			return true;
			// 如果修改，那么就按照caseid查询目标共享组是否存在
			// 如果已经存在，则删除当前商标对应的附件
			// 如果不存在，就查询当前商标对应附件是否存在，如果存在就不创建附件，如果不存在就创建附件
		} else {
			return false;
		}
	}

	// 按照案件id检查目标共享组是否存在
	// 如果目标共享组是NOSHARE，如果有就那么不需要删除原有的文件
	// 如果没有就需要创建附件
	@Override
	public Boolean checkTargetShareGroupIsExisted(TradeMark persistedMark) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		oql.setType(TradeMark.class);
		oql.setSelects("TradeMark.{id}");
		oql.setFilter("shareGroup=? and tradeMarkCaseId=?");
		oql.getParameters().add("shareGroup", persistedMark.getShareGroup(), Types.INTEGER);
		oql.getParameters().add("tradeMarkCaseId", persistedMark.getTradeMarkCaseId(), Types.INTEGER);
		int tmOrigin = this.queryCount(oql);
		// 说明已经修改共享组
		if (tmOrigin > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteAttachmentByTradeMarkId(Integer tmid) {
		// TODO Auto-generated method stub
		String cmdstr = "delete from ig_up_attachment where trade_mark_id=?";
		Oql oql = new Oql();
		oql.getParameters().add("trade_mark_id", tmid, Types.INTEGER);
		this.pm.executeNonQuery(cmdstr, oql.getParameters());

		cmdstr = "delete from ig_down_attachment where trade_mark_id=?";
		oql = new Oql();
		oql.getParameters().add("trade_mark_id", tmid, Types.INTEGER);
		this.pm.executeNonQuery(cmdstr, oql.getParameters());

	}

	@Override
	public Boolean checkAttachmentIsExisted(Integer tmid) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		oql.setType(UploadAttachment.class);
		oql.setSelects("UploadAttachment.{id}");
		oql.setFilter("tradeMarkId=?");
		oql.getParameters().add("tradeMarkId", tmid, Types.INTEGER);
		int tc = this.queryCount(oql);
		return tc > 0;
	}

	@Override
	public void addAttachmentByTradeMark(TradeMark tmk) {
		// TODO Auto-generated method stub
		List<UploadAttachment> upas = new ArrayList<UploadAttachment>();
		List<DownloadAttachment> downs = new ArrayList<DownloadAttachment>();
		UploadAttachment ua = null;
		DownloadAttachment da = null;
		if (tmk.getHasColor()) {
			ua = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_彩色商标图样", AttachmentCat.TRADEMARK_PICT,
					tmk.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tmk.getId());
			upas.add(ua);

			da = this.buildDownloadAttachment(tmk.getMemo() + "_彩色委托书", AttachmentCat.DELEGATE_PROOF,
					tmk.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tmk.getId());
			downs.add(da);

		}
		ua = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_黑色商标图样", AttachmentCat.TRADEMARK_PICT,
				tmk.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, tmk.getId());
		upas.add(ua);

		ua = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_委托书", AttachmentCat.DELEGATE_PROOF,
				tmk.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, tmk.getId());
		upas.add(ua);

		ua = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_补充证明", AttachmentCat.MEMO_DESC,
				tmk.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tmk.getId());
		upas.add(ua);

		da = this.buildDownloadAttachment(tmk.getMemo() + "_黑色委托书", AttachmentCat.DELEGATE_PROOF,
				tmk.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tmk.getId());
		downs.add(da);
		upattachementService.saves(upas);
		downattachementService.saves(downs);
	}

	private UploadAttachment buildUploadAttachment(String name, AttachmentCat ac, Integer tmcid, FileType fileType,
			FileType tofileType, Integer tmid) {
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
		return attachment;
	}

	private DownloadAttachment buildDownloadAttachment(String name, AttachmentCat ac, Integer tmcid, FileType fileType,
			FileType tofileType, Integer tmid) {
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
		return attachment;
	}

	@Override
	public void deleteColorfulTradeMarkPict(TradeMark tm) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		String cmdstr = "delete from ig_up_attachment where attachment_cat=? and ( file_type=? or file_type=? ) and trade_mark_id=?";
		oql.getParameters().add("attachment_cat", AttachmentCat.TRADEMARK_PICT.getValue(), Types.INTEGER);
		oql.getParameters().add("file_type", FileType.JPGC.getValue(), Types.INTEGER);
		oql.getParameters().add("file_type", FileType.PNGC.getValue(), Types.INTEGER);
		oql.getParameters().add("trade_mark_id", tm.getId(), Types.INTEGER);
		this.pm.executeNonQuery(cmdstr, oql.getParameters());

		// 删除彩色委托书
		cmdstr = "delete from ig_down_attachment where attachment_cat=? and ( file_type=? or file_type=? ) and trade_mark_id=?";
		oql.getParameters().add("attachment_cat", AttachmentCat.DELEGATE_PROOF.getValue(), Types.INTEGER);
		oql.getParameters().add("file_type", FileType.JPGC.getValue(), Types.INTEGER);
		oql.getParameters().add("file_type", FileType.PNGC.getValue(), Types.INTEGER);
		oql.getParameters().add("trade_mark_id", tm.getId(), Types.INTEGER);
		this.pm.executeNonQuery(cmdstr, oql.getParameters());

	}

	@Override
	public void addColorfulTradeMarkPict(TradeMark tm) {
		// TODO Auto-generated method stub
		// 检查当前商标是否存在，如果不存在就添加彩色
		Oql oql = new Oql();
		String cmdstr = "select count(id) from ig_up_attachment where attachment_cat=? and ( file_type=? or file_type=? ) and trade_mark_id=?";
		oql.getParameters().add("attachment_cat", AttachmentCat.TRADEMARK_PICT.getValue(), Types.INTEGER);
		oql.getParameters().add("file_type", FileType.JPGC.getValue(), Types.INTEGER);
		oql.getParameters().add("file_type", FileType.PNGC.getValue(), Types.INTEGER);
		oql.getParameters().add("trade_mark_id", tm.getId(), Types.INTEGER);
		Long c=(Long)this.pm.executeScalar(cmdstr, oql.getParameters());
		if(c==0) {//创建上传彩色图样和下载委托书
			UploadAttachment attachment1 = (UploadAttachment) this.buildUploadAttachment(tm.getMemo() + "_彩色商标图样",
					AttachmentCat.TRADEMARK_PICT, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC,
					tm.getId());
			upattachementService.save(attachment1);

			DownloadAttachment attachment2 = this.buildDownloadAttachment(tm.getMemo() + "_彩色委托书",
					AttachmentCat.DELEGATE_PROOF, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC,
					tm.getId());
			downattachementService.save(attachment2);
			
		}

	}

	@Override
	public List<TradeMarkApplyInfo> tmsForRobot(Integer innerHour) {
		List<TradeMarkApplyInfo> tminfos = new ArrayList<>();
		TradeMarkApplyInfo tminfo;
		Step1 step1;
		Oql oql = new Oql();
		oql.setType(TradeMark.class);
		oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*");
		oql.setFilter("markState=?");
		oql.getParameters().add("markState", MarkState.WAITCOMMIT, Types.INTEGER);
		List<TradeMark> tms = this.queryList(oql);
		for (TradeMark tm:tms){
			tminfo = new TradeMarkApplyInfo();
			step1 = new Step1();
			tminfo.setCorpTrademarkId(tm.getProxyCode());
			step1.setAppGjdq(tm.getTradeMarkCase().getWriteType().getText());
			step1.setAppTypeId(tm.getTradeMarkCase().getApplierType().getText());
			tminfo.setStep1(step1);
			tminfos.add(tminfo);
		}
		return tminfos;
	}

}