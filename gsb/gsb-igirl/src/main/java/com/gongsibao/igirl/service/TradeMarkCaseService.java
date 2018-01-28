package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.ApplierType;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.utils.SupplierSessionManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;

@Service
public class TradeMarkCaseService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseService {
	ISupplierService supplierServcie = ServiceFactory.create(ISupplierService.class);
	IUploadAttachmentService upattachementService = ServiceFactory.create(IUploadAttachmentService.class);
	IDownloadAttachmentService downattachementService = ServiceFactory.create(IDownloadAttachmentService.class);
	ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);

	public TradeMarkCaseService() {
		super();
		this.type = TradeMarkCase.class;
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
	public TradeMarkCase save(TradeMarkCase entity) {
		// 设置编码样式 和所在的代理公司
		if (entity.getEntityState() == EntityState.New) {
			entity.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
			Integer id=SupplierSessionManager.getSupplierId();
			entity.setSupplierId(id);
			for(TradeMark tm :entity.getTradeMarks()) {
				tm.setSupplierId(id);
			}
			//设置加盟商信息
			Integer sid=SupplierSessionManager.getSupplierId();
			Supplier sl=supplierServcie.byId(sid);
		  entity.setProxyCompanyName(sl.getName());
			entity.setAccountNo(sl.getBankNum());
			
			if (entity.getApplierType() == ApplierType.PUBLIC) {
				entity.setApplier(entity.getCompanyName());
			}
			UploadAttachment attachment2 = (UploadAttachment) this.buildUploadAttachment("营业执照",
					AttachmentCat.BUSINESS_LIEN, entity.getId(), FileType.JPGC, FileType.PDF, -1);
			entity.getUploadAttachments().add(attachment2);
		}

		// //附件商标图样因为色彩而变化
		if (entity.getEntityState() == EntityState.Persist) {
			List<TradeMark> tmks = entity.getTradeMarks();
			for (int i = 0; i < tmks.size(); i++) {
				TradeMark tmk = tmks.get(i);
				if (tmk.getEntityState() == EntityState.Deleted) {// 如果是删除，那么需要删除相关的需要上传的附件和下载的附件
					tradeMarkService.deleteAttachmentByTradeMarkId(tmk.getId());
				}
				// 如果商标是新增,后面后处理，同首次创建案件新增商标处理
				if (tmk.getEntityState() == EntityState.New) {

				}
				// 如果商标是更新
				if (tmk.getEntityState() == EntityState.Persist) {
					// to do 抽到商标服务中
					// 查询当前共享组是否被修改
					if (tradeMarkService.checkShareGroupIsModifyed(tmk)) {
						// 如果修改后的目标共享组已经存在，那么就删除当前商标所挂的附件,共享目标共享组的附件
						if (tradeMarkService.checkTargetShareGroupIsExisted(tmk)) {
							// 如果目标共享组已经存在且不是独享
							if (tmk.getShareGroup() != ShareGroup.NOSHARRE) {
								tradeMarkService.deleteAttachmentByTradeMarkId(tmk.getId());
							} else {
								// 如果目标是独享，检查附件是否存在，如果不存在就创建附件
								// 如果不存在就创建附件,存在就维持不变
								if (!tradeMarkService.checkAttachmentIsExisted(tmk.getId())) {
									// 如果不存在就创建当前商标附件
									tradeMarkService.addAttachmentByTradeMark(tmk);
								}
							}

						}
					}

					// 颜色检查
					if (!tmk.getHasColor()) {
						// 如果没有颜色
						tradeMarkService.deleteColorfulTradeMarkPict(tmk);
					} else {// 检查颜色是否改变
							// 检查是否存在彩色图样，不存在就添加
						tradeMarkService.addColorfulTradeMarkPict(tmk);
					}

				}

			}

		}
		Boolean deleted = false;
		// 删除附件明细
		if (entity.getEntityState() == EntityState.Deleted) {
			deleted = true;
			Oql oql = new Oql();
			{
				oql.setType(this.type);
				oql.setSelects(
						"TradeMarkCase.id,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*,TradeMarkCase.tradeMarks.*");
				oql.setFilter(" id=? ");
				oql.getParameters().add("id", entity.getId(), Types.INTEGER);
			}
			entity = this.queryFirst(oql);

			entity.setEntityState(EntityState.Deleted);
			List<UploadAttachment> ups = entity.getUploadAttachments();
			for (int i = 0; i < ups.size(); i++) {
				UploadAttachment uploadAttachment = ups.get(i);
				uploadAttachment.setEntityState(EntityState.Deleted);

			}
			List<DownloadAttachment> ds = entity.getDownLoadAttaments();
			for (int i = 0; i < ds.size(); i++) {
				DownloadAttachment downloadAttachment = ds.get(i);
				downloadAttachment.setEntityState(EntityState.Deleted);

			}
			List<TradeMark> tms = entity.getTradeMarks();
			for (int i = 0; i < tms.size(); i++) {
				TradeMark tm = tms.get(i);
				tm.setEntityState(EntityState.Deleted);

			}

		}

		// 默认设置为联系人电话
		entity.setToken(entity.getMobile());
		// 设置tokenImageUrl
		entity = super.save(entity);

		// 查询出id不在上传附件列表中的商标,当首次保存或者更新新增商标时，需要添加新的附件
		if (!deleted) {
			String cmdstr = "select trade_mark_id from  ig_up_attachment where trade_mark_caseid=?";
			Oql oql = new Oql();
			oql.getParameters().add("trade_mark_caseid", entity.getId(), Types.INTEGER);
			DataTable dt = tradeMarkService.executeTable(cmdstr, oql.getParameters());
			Iterator<Row> rows = dt.iterator();
			Map<Integer, Integer> dicTmp = new HashMap<Integer, Integer>();
			while (rows.hasNext()) {
				Row row = rows.next();
				Integer newid = row.getInteger("trade_mark_id");
				if (newid != null)
					dicTmp.put(newid, newid);
			}
			List<UploadAttachment> upas = new ArrayList<UploadAttachment>();
			List<DownloadAttachment> downs = new ArrayList<DownloadAttachment>();
			List<TradeMark> tmks = entity.getTradeMarks();
			Map<ShareGroup, Integer> dic = new HashMap<ShareGroup, Integer>();
			UploadAttachment attachment1 = null;
			DownloadAttachment attachment2 = null;
			for (int i = 0; i < tmks.size(); i++) {
				TradeMark tmk = tmks.get(i);
				// 如果更新新增的不在上传附件的商标ID中
				if (!dicTmp.containsKey(tmk.getId())) {
					// 如果附件不共享，那么就单独生成三个需要上传的附件
					if (tmk.getShareGroup() == ShareGroup.NOSHARRE) {
						if (tmk.getHasColor()) {
							attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_彩色商标图样",
									AttachmentCat.TRADEMARK_PICT, entity.getId(), FileType.JPGC, FileType.JPGC,
									tmk.getId());
							upas.add(attachment1);

							attachment2 = this.buildDownloadAttachment(tmk.getMemo() + "_彩色委托书",
									AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGC, FileType.JPGC,
									tmk.getId());
							downs.add(attachment2);

						}
						attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_黑色商标图样",
								AttachmentCat.TRADEMARK_PICT, entity.getId(), FileType.JPGB, FileType.JPGB,
								tmk.getId());
						upas.add(attachment1);

						attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_委托书",
								AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGB, FileType.JPGB,
								tmk.getId());
						upas.add(attachment1);

						attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_补充证明",
								AttachmentCat.MEMO_DESC, entity.getId(), FileType.JPGC, FileType.JPGC, tmk.getId());
						upas.add(attachment1);

						attachment2 = this.buildDownloadAttachment(tmk.getMemo() + "_黑色委托书",
								AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGC, FileType.JPGC,
								tmk.getId());
						downs.add(attachment2);

					} else {
						if (!dic.containsKey(tmk.getShareGroup())) {
							// 如果不存在相同的共享组，添加要被共享的附件
							if (tmk.getHasColor()) {
								attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_彩色商标图样",
										AttachmentCat.TRADEMARK_PICT, entity.getId(), FileType.JPGC, FileType.JPGC,
										tmk.getId());
								upas.add(attachment1);

								attachment2 = this.buildDownloadAttachment(tmk.getMemo() + "_彩色委托书",
										AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGC, FileType.JPGC,
										tmk.getId());
								downs.add(attachment2);

							}
							attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_黑色商标图样",
									AttachmentCat.TRADEMARK_PICT, entity.getId(), FileType.JPGB, FileType.JPGB,
									tmk.getId());
							upas.add(attachment1);

							attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_委托书",
									AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGB, FileType.JPGB,
									tmk.getId());
							upas.add(attachment1);

							attachment1 = (UploadAttachment) this.buildUploadAttachment(tmk.getMemo() + "_补充证明",
									AttachmentCat.MEMO_DESC, entity.getId(), FileType.JPGC, FileType.JPGC, tmk.getId());
							upas.add(attachment1);

							attachment2 = this.buildDownloadAttachment(tmk.getMemo() + "_黑色委托书",
									AttachmentCat.DELEGATE_PROOF, entity.getId(), FileType.JPGC, FileType.JPGC,
									tmk.getId());
							downs.add(attachment2);

							dic.put(tmk.getShareGroup(), 1);

						} else {
							// 如果新增的包含

						}

					}

				} else {
					dic.put(tmk.getShareGroup(), 1);
				}
			}
			upattachementService.saves(upas);
			downattachementService.saves(downs);

		}
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
		Oql oql = new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.id,TradeMarkCase.code,TradeMarkCase.caseAmount,TradeMarkCase.tradeMarks.*");
			oql.setFilter("mobile=?");
			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}
}