package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.dict.ApplierType;
import com.gongsibao.entity.igirl.dict.ConfigType;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.entity.igirl.dict.TMCState;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.IGirlConfigService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.igirl.service.builder.TradeMarkCaseAttachmentBuiler;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.utils.SupplierSessionManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.joda.time.DateTime;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
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
	// 身份证明商标ID为赋予值为-3，因为多个商标共享
	public final static Integer PersonMarkProofID = -3;

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
		int n=0;
		for (TradeMark tm : entity.getTradeMarks()) {
			tm.setProxyCode( DateTime.now().toString("yyyyMMddHHmmssSSS")+n);
			n++;
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
			//entity.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
			// 填充加盟商信息
			entity = fillSupplierInfo(entity);
			entity = fillDepartmentInfo(entity);
			if (entity.getApplierType() == ApplierType.PUBLIC) {
				entity.setApplier(entity.getCompanyName());
			}
			
		}
		// //附件商标图样因为色彩而变化
		if (entity.getEntityState() == EntityState.Persist) {
			Integer sid = SupplierSessionManager.getSupplierId();
			Integer departmentId = SupplierSessionManager.getDepartmentId();
			String tmp="";
			int m=1;
			for (TradeMark tm : entity.getTradeMarks()) {
				if(StringManager.isNullOrEmpty(tm.getProxyCode())){
					tm.setProxyCode( DateTime.now().toString("yyyyMMddHHmmssSSS")+entity.getId()+m);
					m++;
				}	else {
					m++;
				}
				tm.setSupplierId(sid);
				tm.setDepartmentId(departmentId);
				if (tm.getEntityState() != EntityState.Deleted) {
					tmp+=tm.getNclOne().getCode()+" ";
			    }
			}
			entity.setTradeOptions(tmp);
		}
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

	@Override
	public String fetchQrCodeUrl(String url,String casecode) {
		// TODO Auto-generated method stub
		IGirlConfigService girlConf=ServiceFactory.create(IGirlConfigService.class);
		Oql oql=new Oql();{
			oql.setType(IGirlConfig.class);
			oql.setSelects("IGirlConfig.*");
			oql.setFilter("configType=? or configType=?");
			oql.getParameters().add("configType",ConfigType.IGIRL_QR_URL.getValue(),Types.INTEGER);
			oql.getParameters().add("configType",ConfigType.IGIRL_MOBILE_TESTURL.getValue(),Types.INTEGER);
		}
		List<IGirlConfig> configs=girlConf.queryList(oql);
		String qcurl="";
		if(configs.size()==1) {
			//qcurl="{qrServiceUrl}/qc?detailLink= {currentDomain}/gsb/igirl/tmcase.html?mobile="+mobile;
			//URLEncoder.encode(s)
			qcurl="{qrServiceUrl}/qc?detailLink=|{currentDomain}/gsb/igirl/mobile/main.html#/?spid="+SupplierSessionManager.getSupplierId()+"&casecode="+casecode+"&source=case";
			qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", url);
			try {
				qcurl=qcurl.split("\\|")[0]+URLEncoder.encode(qcurl.split("\\|")[1],"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(configs.size()==2) {
			qcurl="{qrServiceUrl}/qc?detailLink=|{currentDomain}/gsb/igirl/mobile/main.html#/?spid="+SupplierSessionManager.getSupplierId()+"&casecode="+casecode+"&source=case";
			qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", configs.get(1).getConfigValue());
			try {
				qcurl=qcurl.split("\\|")[0]+URLEncoder.encode(qcurl.split("\\|")[1],"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return qcurl;
	}

	@Override
	public int denyAdvice(String caseid, String advice) {
		// TODO Auto-generated method stub
		try {
			String cmdstr = "update ig_trade_mark_case set tmc_state=?,advice=? where id=?";
			Oql oql=new Oql();
			{
				oql.setFilter("tmc_state=?");
				oql.setFilter("advice=?");
				oql.setFilter("id=?");
				oql.getParameters().add("tmc_state",TMCState.ADVICE.getValue(),Types.INTEGER);
				oql.getParameters().add("advice",advice,Types.VARCHAR);
				oql.getParameters().add("id",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
		
	}

	@Override
	public int confirmCase(String caseid) {
		// TODO Auto-generated method stub
		try {
			String cmdstr = "update ig_trade_mark_case set tmc_state=? where id=?";
			Oql oql=new Oql();
			{
				oql.setFilter("tmc_state=?");
				oql.setFilter("id=?");
				oql.getParameters().add("tmc_state",TMCState.CONFIRMED.getValue(),Types.INTEGER);
				oql.getParameters().add("id",Integer.parseInt(caseid),Types.INTEGER);	
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
	}

	@Override
	public int attachmentMake(String caseid) {
		// TODO Auto-generated method stub
		//先删除当前案件的所有附件
		try {
			String cmdstr = "delete from ig_up_attachment where trade_mark_caseid=?";
			Oql oql=new Oql();
			{
				oql.setFilter("trade_mark_caseid=?");
				oql.getParameters().add("trade_mark_caseid",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			cmdstr = "delete from ig_down_attachment where trade_mark_caseid=?";
			oql=new Oql();
			{
					oql.setFilter("trade_mark_caseid=?");
					oql.getParameters().add("trade_mark_caseid",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			//生成附件
			Oql oqlx = new Oql();
			{
				oqlx.setType(TradeMarkCase.class);
				oqlx.setSelects("TradeMarkCase.*,TradeMarkCase.tradeMarks.*,TradeMarkCase.tradeMarks.nclOne.*,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*");
				oqlx.setFilter(" id=? ");
				oqlx.getParameters().add("id", Integer.parseInt(caseid), Types.INTEGER);
			}
			TradeMarkCase tmc=this.queryFirst(oqlx);
			//创建新的附件
			List<UploadAttachment> caseUps = tradeMarkCaseAttachmentBuiler.buildCaseShareUploads(tmc);	// 构建案件共享上传
			tmc.getUploadAttachments().addAll(caseUps);				
			List<UploadAttachment> markShareGroupUps = tradeMarkCaseAttachmentBuiler.buildMarkShareGroupUploads(tmc);
			tmc.getUploadAttachments().addAll(markShareGroupUps);
			List<DownloadAttachment> markShareGroupDowns = tradeMarkCaseAttachmentBuiler.buildDownloads(tmc);
			tmc.getDownLoadAttaments().addAll(markShareGroupDowns);
			tmc.toPersist();
			this.save(tmc);
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
	}
}