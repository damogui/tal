package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.ApplierType;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.igirl.service.builder.TradeMarkCaseAttachmentBuiler;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.utils.SupplierSessionManager;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class TradeMarkCaseService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseService {
	ISupplierService supplierServcie = ServiceFactory.create(ISupplierService.class);
	IUploadAttachmentService upattachementService = ServiceFactory.create(IUploadAttachmentService.class);
	IDownloadAttachmentService downattachementService = ServiceFactory.create(IDownloadAttachmentService.class);
	ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);
	private static TradeMarkCaseAttachmentBuiler tradeMarkCaseAttachmentBuiler = new TradeMarkCaseAttachmentBuiler();
	// 附件营业执照商标ID赋值为-1，因为多个商标共享
	public final static Integer TradeMarkBizLienseID = -1;
	// 付款证明商标ID为赋予值为-2，因为多个商标共享
	public final static Integer TradeMarkPayProofID = -2;

	public TradeMarkCaseService() {
		super();
		this.type = TradeMarkCase.class;
	}

	/**
	 * 填充加盟商信息
	 * 
	 * @param entity
	 */
	private TradeMarkCase fillSupplierInfo(TradeMarkCase entity) {
		Integer sid = SupplierSessionManager.getSupplierId();
		entity.setSupplierId(sid);
		// 设置加盟商信息
		Supplier sl = supplierServcie.byId(sid);
		if (sl != null) {
			entity.setProxyCompanyName(sl.getName());
			entity.setAccountNo(sl.getBankNum());
			entity.setSupplierId(sid);
//		  entity.setYwPhone(sl.getFixPhone());
//		  entity.setMailCode(sl.getPostcode());
//			entity.setFax(sl.getFax());

		}
		// 设置商标的服务商id
	  String tmp="";
		for (TradeMark tm : entity.getTradeMarks()) {
			tm.setSupplierId(sid);
			//设置
			tmp+=tm.getNclOne().getCode()+" ";
		}
		entity.setTradeOptions(tmp);
		return entity;
	}

	/**
	 * 填充部门信息
	 *
	 * @param entity
	 */
	private TradeMarkCase fillDepartmentInfo(TradeMarkCase entity) {
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		entity.setDepartmentId(departmentId);
		// 设置商标的服务商id
		String tmp="";
		for (TradeMark tm : entity.getTradeMarks()) {
			tm.setDepartmentId(departmentId);
			//设置
			tmp+=tm.getNclOne().getCode()+" ";
		}
		entity.setTradeOptions(tmp);
		return entity;
	}

	private Map<ShareGroup, Integer> buildShareGroupCountMap(TradeMarkCase tmc) {
		Map<ShareGroup, Integer> shareGroupCountMap = new HashMap<ShareGroup, Integer>();
		for (TradeMark tm : tmc.getTradeMarks()) {
			if (!shareGroupCountMap.containsKey(tm.getShareGroup())) {
				shareGroupCountMap.put(tm.getShareGroup(), 1);
			} else {
				int n = shareGroupCountMap.get(tm.getShareGroup()) + 1;
				shareGroupCountMap.put(tm.getShareGroup(), n);
			}
		}
		return shareGroupCountMap;
	}

	@Override
	public TradeMarkCase save(TradeMarkCase entity) {
		if (entity.getEntityState() == EntityState.New) {
			entity.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
			// 填充加盟商信息
			entity = fillSupplierInfo(entity);
			entity = fillDepartmentInfo(entity);
			if (entity.getApplierType() == ApplierType.PUBLIC) {
				entity.setApplier(entity.getCompanyName());
			}
			// 构建案件共享上传
			// tradeMarkCaseAttamentBuiler.buildUploads(tm)；
			List<UploadAttachment> caseUps = tradeMarkCaseAttachmentBuiler.buildCaseShareUploads(entity);
			entity.getUploadAttachments().addAll(caseUps);
			
			List<UploadAttachment> markShareGroupUps = tradeMarkCaseAttachmentBuiler.buildMarkShareGroupUploads(entity);
			entity.getUploadAttachments().addAll(markShareGroupUps);
			List<DownloadAttachment> markShareGroupDowns = tradeMarkCaseAttachmentBuiler.buildDownloads(entity);
			entity.getDownLoadAttaments().addAll(markShareGroupDowns);
		}
		// //附件商标图样因为色彩而变化
		if (entity.getEntityState() == EntityState.Persist) {
			// 先获取原始原始的实体
			Oql oqlx = new Oql();
			{
				oqlx.setType(TradeMarkCase.class);
				oqlx.setSelects("TradeMarkCase.*,TradeMarkCase.tradeMarks.*");
				oqlx.setFilter(" id=? ");
				oqlx.getParameters().add("id", entity.getId(), Types.INTEGER);
			}
			TradeMarkCase origin = this.queryFirst(oqlx);
			Map<ShareGroup, Integer> shareGroupCountMap = this.buildShareGroupCountMap(origin);
			Integer sid = SupplierSessionManager.getSupplierId();
			Integer departmentId = SupplierSessionManager.getDepartmentId();
			String tmp="";
			for (TradeMark tm : entity.getTradeMarks()) {
				tm.setSupplierId(sid);
				tm.setDepartmentId(departmentId);
				if (tm.getEntityState() != EntityState.Deleted) {
					tmp+=tm.getNclOne().getCode()+" ";
					if (!shareGroupCountMap.containsKey(tm.getShareGroup())) {
						// 如果是新增一个分组--tm
						List<UploadAttachment> casenewUps = tradeMarkCaseAttachmentBuiler
								.buildMarkShareGroupUploadsByTm(tm,entity);
						entity.getUploadAttachments().addAll(casenewUps);
						
						List<DownloadAttachment> markShareGroupDowns = tradeMarkCaseAttachmentBuiler.buildDownloads(entity);
						entity.getDownLoadAttaments().addAll(markShareGroupDowns);
						
						
						shareGroupCountMap.put(tm.getShareGroup(), 1);
//						if( entity.getTradeMarks().size()==1) {//如果当前商标项是1，那么就增加营业执照
						
						// 查看当前tm原先的分组
						ShareGroup sg = null;
						for (TradeMark tm1 : origin.getTradeMarks()) {
							if (tm1.getId() == tm.getId()) {
								sg = tm1.getShareGroup();
								break;
							}
						}
						if (sg != tm.getShareGroup()) {
							// 当前修改了分组，查看原先分组的计数，如果原先分组是1，那么修改后，应该删除原先分组对应的附件
							if (sg!=null && shareGroupCountMap.get(sg) == 1) {
								// 删除原先分组及对应的附件
								shareGroupCountMap.remove(sg);
								List<UploadAttachment> ups = entity.getUploadAttachments();
								for (int i = 0; i < ups.size(); i++) {
									UploadAttachment uploadAttachment = ups.get(i);
									if (uploadAttachment.getShareGroup() == sg) {
										uploadAttachment.setEntityState(EntityState.Deleted);
									}
								}
								List<DownloadAttachment> ds = entity.getDownLoadAttaments();
								for (int i = 0; i < ds.size(); i++) {
									DownloadAttachment downloadAttachment = ds.get(i);
									if (downloadAttachment.getShareGroup() == sg) {
										downloadAttachment.setEntityState(EntityState.Deleted);
									}

								}
							}

						}
//							
//						}
					} else {// 修改后目标分组在map中
							// 如果改变到的目标分组存在已有分组，分组+1
						shareGroupCountMap.put(tm.getShareGroup(), shareGroupCountMap.get(tm.getShareGroup()) + 1);
						// 查看当前tm原先的分组
						ShareGroup sg = null;
						for (TradeMark tm1 : origin.getTradeMarks()) {
							if (tm1.getId() == tm.getId()) {
								sg = tm1.getShareGroup();
								break;
							}
						}
						if (sg != tm.getShareGroup()) {
							// 当前修改了分组，查看原先分组的计数，如果原先分组是1，那么修改后，应该删除原先分组对应的附件
							if (sg!=null && shareGroupCountMap.get(sg) == 1) {
								// 删除原先分组及对应的附件
								shareGroupCountMap.remove(sg);
								List<UploadAttachment> ups = entity.getUploadAttachments();
								for (int i = 0; i < ups.size(); i++) {
									UploadAttachment uploadAttachment = ups.get(i);
									if (uploadAttachment.getShareGroup() == sg) {
										uploadAttachment.setEntityState(EntityState.Deleted);
									}
								}
								List<DownloadAttachment> ds = entity.getDownLoadAttaments();
								for (int i = 0; i < ds.size(); i++) {
									DownloadAttachment downloadAttachment = ds.get(i);
									if (downloadAttachment.getShareGroup() == sg) {
										downloadAttachment.setEntityState(EntityState.Deleted);
									}

								}
							}

						}

					}
				}
				if (tm.getEntityState() == EntityState.Deleted) {
					//
					int n = shareGroupCountMap.get(tm.getShareGroup());
					if (n == 1) {// 删除对应的分组
						shareGroupCountMap.remove(tm.getShareGroup());
						List<UploadAttachment> ups = entity.getUploadAttachments();
						for (int i = 0; i < ups.size(); i++) {
							UploadAttachment uploadAttachment = ups.get(i);
							if (uploadAttachment.getShareGroup() == tm.getShareGroup()) {
								uploadAttachment.setEntityState(EntityState.Deleted);
							}
						}
						List<DownloadAttachment> ds = entity.getDownLoadAttaments();
						for (int i = 0; i < ds.size(); i++) {
							DownloadAttachment downloadAttachment = ds.get(i);
							if (downloadAttachment.getShareGroup() == tm.getShareGroup()) {
								downloadAttachment.setEntityState(EntityState.Deleted);
							}

						}
					}
				}
               
			}
			entity.setTradeOptions(tmp);
		}
		// 获取原先的共享组集合

		// {// 删除
		// List<UploadAttachment> ups = entity.getUploadAttachments();
		// for (int i = 0; i < ups.size(); i++) {
		// UploadAttachment uploadAttachment = ups.get(i);
		// uploadAttachment.setEntityState(EntityState.Deleted);
		//
		// }
		// List<DownloadAttachment> ds = entity.getDownLoadAttaments();
		// for (int i = 0; i < ds.size(); i++) {
		// DownloadAttachment downloadAttachment = ds.get(i);
		// downloadAttachment.setEntityState(EntityState.Deleted);
		// }
		// }
		// // 新增
		// List<UploadAttachment> caseUps =
		// tradeMarkCaseAttachmentBuiler.buildCaseShareUploads(entity);
		// entity.getUploadAttachments().addAll(caseUps);
		// List<UploadAttachment> markShareGroupUps =
		// tradeMarkCaseAttachmentBuiler.buildMarkShareGroupUploads(entity);
		// entity.getUploadAttachments().addAll(markShareGroupUps);
		// List<DownloadAttachment> markShareGroupDowns =
		// tradeMarkCaseAttachmentBuiler.buildDownloads(entity);
		// entity.getDownLoadAttaments().addAll(markShareGroupDowns);

		// 删除附件明细
		if (entity.getEntityState() == EntityState.Deleted) {

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
		return entity;
	}

	@Override
	public TradeMarkCase newInstance() {
		// TODO Auto-generated method stub
		Integer sid = SupplierSessionManager.getSupplierId();
		Supplier sl = supplierServcie.byId(sid);
		TradeMarkCase tc = super.newInstance();
//		tc.setYwPhone("010-84927588");
		tc.setMailCode("100000");
//		tc.setFax("010-84927588");	
		tc.setYwPhone(sl.getFax());
		//tc.setMailCode(sl.getPostCode());
		tc.setFax(sl.getFax());
		// 查出当前登陆人办的最后一个案子，取出案件联系人，然后赋予初值
		Oql oql = new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.{creator,caseProxyContactPerson}");
			oql.setFilter("creator=?");
			oql.setOrderby("createTime desc");
			oql.getParameters().add("creator", SessionManager.getUserName(), Types.VARCHAR);
			Paging p = new Paging();
			p.pageNo = 1;
			p.pageSize = 10;
			oql.setPaging(p);
		}
		TradeMarkCase tmc = this.queryFirst(oql);
		if (tmc != null) {
			tc.setCaseProxyContactPerson(tmc.getCaseProxyContactPerson());
		}
		return tc;
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