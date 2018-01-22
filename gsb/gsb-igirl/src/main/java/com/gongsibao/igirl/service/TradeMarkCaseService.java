package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.utils.SupplierSessionManager;

import java.sql.Types;
import java.util.List;

import org.joda.time.DateTime;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;

@Service
public class TradeMarkCaseService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseService {
	ISupplierService supplierServcie=ServiceFactory.create(ISupplierService.class);
	IUploadAttachmentService upattachementService = ServiceFactory.create(IUploadAttachmentService.class);
	IDownloadAttachmentService downattachementService = ServiceFactory.create(IDownloadAttachmentService.class);

	public TradeMarkCaseService() {
		super();
		this.type = TradeMarkCase.class;
	}

	private UploadAttachment buildUploadAttachment(String name, AttachmentCat ac, Integer tmcid, FileType fileType,
			FileType tofileType) {
		UploadAttachment attachment = new UploadAttachment();
		attachment.toNew();
		attachment.setName(name);
		attachment.setAttachmentCat(ac);
		attachment.setTradeMarkCaseId(tmcid);
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
			FileType tofileType) {
		DownloadAttachment attachment = new DownloadAttachment();
		attachment.toNew();
		attachment.setName(name);
		attachment.setAttachmentCat(ac);
		attachment.setTradeMarkCaseId(tmcid);
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
	public TradeMarkCase save(TradeMarkCase entity) {

		// 设置编码样式 和所在的代理公司
		if (entity.getEntityState() == EntityState.New) {
			entity.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
			// IUserService userService=ServiceFactory.create(IUser)
//			int id=SupplierSessionManager.getSupplierId();
//			Supplier sl=supplierServcie.byId(id);
//			entity.setProxyCompanyName(sl.getName());
//			entity.setAccountNo(accountNo);

			// 判断是否选中多，生成上传附件列表，如果选择
			UploadAttachment attachment0 = (UploadAttachment) this.buildUploadAttachment("黑色商标图样",
					AttachmentCat.TRADEMARK_PICT, entity.getId(), FileType.JPGB, FileType.JPGB);
			entity.getUploadAttachments().add(attachment0);
			if (entity.getHasColor()) {
				// 彩色商标图样
				UploadAttachment attachment1 = (UploadAttachment) this.buildUploadAttachment("彩色商标图样",
						AttachmentCat.TRADEMARK_PICT, entity.getId(), FileType.JPGC, FileType.JPGC);
				entity.getUploadAttachments().add(attachment1);
			}
			UploadAttachment attachment2 = (UploadAttachment) this.buildUploadAttachment("营业执照",
					AttachmentCat.BUSINESS_LIEN, entity.getId(), FileType.JPGC, FileType.PDF);
			entity.getUploadAttachments().add(attachment2);

			UploadAttachment attachment3 = (UploadAttachment) this.buildUploadAttachment("委托书",
					AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGC, FileType.JPGC);
			entity.getUploadAttachments().add(attachment3);

			UploadAttachment attachment4 = (UploadAttachment) this.buildUploadAttachment("确认函",
					AttachmentCat.CONFIRM_PROOF, entity.getId(), FileType.JPGC, FileType.JPGC);
			entity.getUploadAttachments().add(attachment4);

			UploadAttachment attachment5 = (UploadAttachment) this.buildUploadAttachment("补充证明（可不传）",
					AttachmentCat.MEMO_DESC, entity.getId(), FileType.JPGC, FileType.JPGC);
			entity.getUploadAttachments().add(attachment5);

			// 下载附件一个是委托书、一个是确认函
			DownloadAttachment dattahment0 = this.buildDownloadAttachment("委托书", AttachmentCat.DELEGATE_PROOF,
					entity.getId(), FileType.JPGC, FileType.JPGC);
			entity.getDownLoadAttaments().add(dattahment0);

			DownloadAttachment dattahment1 = this.buildDownloadAttachment("确认函", AttachmentCat.CONFIRM_PROOF,
					entity.getId(), FileType.JPGC, FileType.JPGC);
			entity.getDownLoadAttaments().add(dattahment1);
			
			

		}

		// //附件商标图样因为色彩而变化
		if (entity.getEntityState() == EntityState.Persist) {
			if (!entity.getHasColor()) {
				// 查询出彩色附件，并删除
				Oql oql = new Oql();
				String cmdstr = "delete from ig_up_attachment where attachment_cat=? and ( file_type=? or file_type=? ) and trade_mark_caseid=?";
				oql.getParameters().add("attachment_cat", AttachmentCat.TRADEMARK_PICT.getValue(), Types.INTEGER);
				oql.getParameters().add("file_type", FileType.JPGC.getValue(), Types.INTEGER);
				oql.getParameters().add("file_type", FileType.PNGC.getValue(), Types.INTEGER);
				oql.getParameters().add("trade_mark_caseid", entity.getId(), Types.INTEGER);
				this.pm.executeNonQuery(cmdstr, oql.getParameters());
			} else {
				// 有颜色，没有上传附件，就添加
				Oql oql = new Oql();

				String cmdStr = "select id from ig_up_attachment where attachment_cat=? and ( file_type=? or file_type=? ) and trade_mark_caseid=?";
				oql.getParameters().add("attachmentCat", AttachmentCat.TRADEMARK_PICT.getValue(), Types.INTEGER);
				oql.getParameters().add("fileType", FileType.JPGC.getValue(), Types.INTEGER);
				oql.getParameters().add("fileType", FileType.PNGC.getValue(), Types.INTEGER);
				oql.getParameters().add("tradeMarkCaseId", entity.getId(), Types.INTEGER);
				DataTable dt = upattachementService.executeTable(cmdStr, oql.getParameters());
				int c = dt.getTotalCount();
				if (c == 0) {
					UploadAttachment uaa = this.buildUploadAttachment("彩色商标图样", AttachmentCat.TRADEMARK_PICT,
							entity.getId(), FileType.JPGC, FileType.JPGC);
					upattachementService.save(uaa);
				}
			}

		}
		//删除附件明细
		if(entity.getEntityState() == EntityState.Deleted) {
			 Oql oql = new Oql();
			 {
			 oql.setType(this.type);
			 oql.setSelects("TradeMarkCase.id,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*,TradeMarkCase.tradeMarks.*");
			 oql.setFilter(" id=? ");
			 oql.getParameters().add("id", entity.getId(), Types.INTEGER);
			 }
			 entity=this.queryFirst(oql);
			 
			 entity.setEntityState(EntityState.Deleted);
			 List<UploadAttachment> ups=entity.getUploadAttachments();
			 for(int i=0;i<ups.size();i++) {
				 UploadAttachment uploadAttachment= ups.get(i);
				 uploadAttachment.setEntityState(EntityState.Deleted);
				 
			 }
			 List<DownloadAttachment> ds=entity.getDownLoadAttaments();
			 for(int i=0;i<ds.size();i++) {
				 DownloadAttachment downloadAttachment= ds.get(i);
				 downloadAttachment.setEntityState(EntityState.Deleted);
				 
			 }
			 List<TradeMark> tms=entity.getTradeMarks();
			 for(int i=0;i<tms.size();i++) {
				 TradeMark tm= tms.get(i);
				 tm.setEntityState(EntityState.Deleted);
				 
			 }
			 
		}

		// 默认设置为联系人电话
		entity.setToken(entity.getMobile());
		
		// 设置tokenImageUrl
		entity = super.save(entity);
		return entity;
	}

	@Override
	public TradeMarkCase newInstance() {
		// TODO Auto-generated method stub
		return super.newInstance();
	}
	

	@Override
	public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile) {
		// TODO Auto-generated method stub
		Oql oql=new Oql();{
   		 oql.setType(TradeMarkCase.class);
   		 oql.setSelects("TradeMarkCase.id,TradeMarkCase.code,TradeMarkCase.caseAmount,TradeMarkCase.tradeMarks.*");
   		 oql.setFilter("mobile=?");
   		 oql.getParameters().add("mobile",mobile,Types.VARCHAR);
   	    }
		return this.queryFirst(oql);
	}
}