package com.gongsibao.igirl.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.igirl.dict.MarkState;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.igirl.dto.TradeMark.Goods;
import com.gongsibao.igirl.dto.TradeMark.Step1;
import com.gongsibao.igirl.dto.TradeMark.Step2;
import com.gongsibao.igirl.dto.TradeMark.Step3;
import com.gongsibao.igirl.dto.TradeMark.Step4;
import com.gongsibao.igirl.dto.TradeMark.Step5;
import com.gongsibao.igirl.dto.TradeMark.Step6;
import com.gongsibao.igirl.dto.TradeMark.Step7;
import com.gongsibao.igirl.dto.TradeMark.TmForRobotDto;
import com.gongsibao.igirl.dto.TradeMark.TradeMarkApplyInfo;
@Service
public class TradeMarkService extends GsbPersistableService<TradeMark> implements ITradeMarkService {
	private final static String contantSeprate = "弌";
    
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
		Oql oql2 = new Oql();
		cmdstr = "delete from ig_down_attachment where attachment_cat=? and ( file_type=? or file_type=? ) and trade_mark_id=?";
		oql2.getParameters().add("attachment_cat", AttachmentCat.DELEGATE_PROOF.getValue(), Types.INTEGER);
		oql2.getParameters().add("file_type", FileType.JPGC.getValue(), Types.INTEGER);
		oql2.getParameters().add("file_type", FileType.PNGC.getValue(), Types.INTEGER);
		oql2.getParameters().add("trade_mark_id", tm.getId(), Types.INTEGER);
		this.pm.executeNonQuery(cmdstr, oql2.getParameters());

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
		Long c = (Long) this.pm.executeScalar(cmdstr, oql.getParameters());
		if (c == 0) {// 创建上传彩色图样和下载委托书
			UploadAttachment attachment1 = (UploadAttachment) this.buildUploadAttachment(tm.getMemo() + "_彩色商标图样",
					AttachmentCat.TRADEMARK_PICT, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tm.getId());
			upattachementService.save(attachment1);

			DownloadAttachment attachment2 = this.buildDownloadAttachment(tm.getMemo() + "_彩色委托书",
					AttachmentCat.DELEGATE_PROOF, tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tm.getId());
			downattachementService.save(attachment2);

		}

	}

	// 查询上传附件列表，构造MAP
	// key:caseid_shareroup_fileCat,value:fileurl_filename
	public Map<String, String> buildCaseShareGroupToAttachFileMap() {
		Map<String, String> shareGroupToTradeMarkMap = new HashMap<String, String>();
		Oql oql = new Oql();
		{
			oql.setType(UploadAttachment.class);
			oql.setSelects("UploadAttachment.*,UploadAttachment.tradeMark.*,UploadAttachment.tradeMarkCase.*");
			oql.setFilter("tradeMark.markState=? or tradeMarkId=?");
			oql.getParameters().add("tradeMark.markState", MarkState.WAITCOMMIT.getValue(), Types.INTEGER);
			oql.getParameters().add("tradeMarkId", -1, Types.INTEGER);

		}
		List<UploadAttachment> ups = upattachementService.queryList(oql);
		for (UploadAttachment ua : ups) {
			String fileurl = ua.getFileUrl();
			if(!StringManager.isNullOrEmpty(fileurl)) {
				int index = fileurl.lastIndexOf("/");
				String filename = fileurl.substring(index + 1);
				String key = "";
				TradeMark tm = ua.getTradeMark();
				if (ua.getTradeMarkId() == TradeMarkCaseService.TradeMarkBizLienseID) {// 营业执照
					key = ua.getTradeMarkCaseId() + contantSeprate + "zz"+ contantSeprate + "zz";
				} else {
					if(ua.getTradeMarkId() == TradeMarkCaseService.TradeMarkPayProofID) {//表示付款证明
						continue;
					}
					key = ua.getTradeMarkCaseId() + contantSeprate + tm.getShareGroup().getValue() + contantSeprate
							+ ua.getAttachmentCat().getValue();
					// 如果指定颜色，并且是图样，那么设置key的后缀为文件类型
					if (tm.getHasColor() && ua.getAttachmentCat()==AttachmentCat.TRADEMARK_PICT) {
						key = key + contantSeprate + ua.getFileType().getValue();
					}

				}
				if (!shareGroupToTradeMarkMap.containsKey(key)) {
					shareGroupToTradeMarkMap.put(key, fileurl + contantSeprate + filename);
				}
			}
		}
		// 查询附件
		return shareGroupToTradeMarkMap;
	}

	// 构造营业执照搜索附件的key
	private Map<String, String> getBusinessLienceAttachment(TradeMark tm, Map<String, String> attachmentsMap) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		String step2key = tm.getTradeMarkCaseId() + contantSeprate + "zz" + contantSeprate +"zz";
		if(attachmentsMap.containsKey(step2key)) {
			String fileinfo = attachmentsMap.get(step2key);
			String fileUrl = fileinfo.split(contantSeprate)[0];
			String fileName = fileinfo.split(contantSeprate)[1];
			rtnMap.put("fileUrl", fileUrl);
			rtnMap.put("fileName", fileName);
		}
		return rtnMap;
	}

	// 构造获取委托书的key
	private Map<String, String> getDeleProofAttachment(TradeMark tm, Map<String, String> attachmentsMap) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		String step2key = tm.getTradeMarkCaseId() + contantSeprate + tm.getShareGroup().getValue() + contantSeprate
				+ AttachmentCat.DELEGATE_PROOF.getValue();
//		if (tm.getHasColor()) {
//			step2key += contantSeprate + FileType.JPGC.getValue();
//			step3key += contantSeprate + FileType.PNGC.getValue();
//		}
		String fileinfo = attachmentsMap.get(step2key);
		if (!StringManager.isNullOrEmpty(fileinfo)) {
			String fileUrl = fileinfo.split(contantSeprate)[0];
			String fileName = fileinfo.split(contantSeprate)[1];
			rtnMap.put("fileUrl", fileUrl);
			rtnMap.put("fileName", fileName);
		} 
		return rtnMap;
	}

	// 构造获取图样的key
	private Map<String, String> getTradePictAttachment(TradeMark tm, Map<String, String> attachmentsMap) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		String stepbasekey = tm.getTradeMarkCaseId() + contantSeprate + tm.getShareGroup().getValue() + contantSeprate
				+ AttachmentCat.TRADEMARK_PICT.getValue();
//		String step8key = tm.getTradeMarkCaseId() + contantSeprate + tm.getShareGroup().getValue() + contantSeprate
//				+ AttachmentCat.TRADEMARK_PICT.getValue();
		String stepcolorkeyjpg=null;
		String stepcolorkeypng=null;

		if (tm.getHasColor()) {
			stepcolorkeyjpg = stepbasekey+contantSeprate + FileType.JPGC.getValue();
			stepcolorkeypng = stepbasekey+contantSeprate + FileType.PNGC.getValue();
			String fileinfocolorjpg = attachmentsMap.get(stepcolorkeyjpg);
			String fileinfo2colorpng = attachmentsMap.get(stepcolorkeypng);
			if (!StringManager.isNullOrEmpty(fileinfocolorjpg)) {
				String fileUrl = fileinfocolorjpg.split(contantSeprate)[0];
				String fileName = fileinfocolorjpg.split(contantSeprate)[1];
				rtnMap.put("fileUrl_color", fileUrl);
				rtnMap.put("fileName_color", fileName);
			} else {
				String fileUrl = fileinfo2colorpng.split(contantSeprate)[0];
				String fileName = fileinfo2colorpng.split(contantSeprate)[1];
				rtnMap.put("fileUrl_color", fileUrl);
				rtnMap.put("fileName_color", fileName);
			}
			//黑色
			String stepblackkeyjpg=null;
			String stepblackkeypng=null;
			stepblackkeyjpg = stepbasekey+contantSeprate + FileType.JPGB.getValue();
			stepblackkeypng = stepbasekey+contantSeprate + FileType.PNGB.getValue();
			String fileinfoblackjpg = attachmentsMap.get(stepblackkeyjpg);
			String fileinfo2blackpng = attachmentsMap.get(stepblackkeypng);
			if (!StringManager.isNullOrEmpty(fileinfoblackjpg)) {
				String fileUrl = fileinfoblackjpg.split(contantSeprate)[0];
				String fileName = fileinfoblackjpg.split(contantSeprate)[1];
				rtnMap.put("fileUrl_black", fileUrl);
				rtnMap.put("fileName_black", fileName);
			} else {
				String fileUrl = fileinfo2blackpng.split(contantSeprate)[0];
				String fileName = fileinfo2blackpng.split(contantSeprate)[1];
				rtnMap.put("fileUrl_black", fileUrl);
				rtnMap.put("fileName_black", fileName);
			}
		}else {
			//黑色
			String stepblackkeyjpg=null;
			String stepblackkeypng=null;
			stepblackkeyjpg = stepbasekey;
			stepblackkeypng = stepbasekey;
			String fileinfoblackjpg = attachmentsMap.get(stepblackkeyjpg);
			String fileinfo2blackpng = attachmentsMap.get(stepblackkeypng);
			if (!StringManager.isNullOrEmpty(fileinfoblackjpg)) {
				String fileUrl = fileinfoblackjpg.split(contantSeprate)[0];
				String fileName = fileinfoblackjpg.split(contantSeprate)[1];
				rtnMap.put("fileUrl_black", fileUrl);
				rtnMap.put("fileName_black", fileName);
			} else {
				String fileUrl = fileinfo2blackpng.split(contantSeprate)[0];
				String fileName = fileinfo2blackpng.split(contantSeprate)[1];
				rtnMap.put("fileUrl_black", fileUrl);
				rtnMap.put("fileName_black", fileName);
			}
		}
	
	
		return rtnMap;
	}

	// 构造获取其它证明的key
	private Map<String, String> getCommentAttachment(TradeMark tm, Map<String, String> attachmentsMap) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		String step2key = tm.getTradeMarkCaseId() + contantSeprate + tm.getShareGroup().getValue() + contantSeprate+ AttachmentCat.MEMO_DESC.getValue();
		if(attachmentsMap.containsKey(step2key)) {
			String fileinfo = attachmentsMap.get(step2key);
			String fileUrl = fileinfo.split(contantSeprate)[0];
			String fileName = fileinfo.split(contantSeprate)[1];
			rtnMap.put("fileUrl", fileUrl);
			rtnMap.put("fileName", fileName);
		}
		return rtnMap;
	}

	@Override
	public TmForRobotDto tmsForRobot(Integer innerHour) {
	
		List<TradeMarkApplyInfo> tminfos = new ArrayList<>();
		TradeMarkApplyInfo tminfo;
		Step1 step1;
		Step2 step2;
		Step3 step3;
		Step4 step4;
		Step5 step5;
		Step6 step6;
		Step7 step7;
		Oql oql = new Oql();
		oql.setType(TradeMark.class);
		oql.setSelects("TradeMark.*,TradeMark.nclOne.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
		oql.setFilter("markState=?");
		oql.getParameters().add("markState", MarkState.WAITCOMMIT.getValue(), Types.INTEGER);
		oql.setOrderby("tradeMarkCase.urgency asc");
		List<TradeMark> tms = this.queryList(oql);
		// 查询出上传附件列表，然后构造一个案件共享组附件映射
		Map<String, String> shareGroupToTradeMarkMap = this.buildCaseShareGroupToAttachFileMap();
		for (TradeMark tm : tms) {
			TradeMarkCase tmc = tm.getTradeMarkCase();
			tminfo = new TradeMarkApplyInfo();
			step1 = new Step1();
			tminfo.setCorpTrademarkId(tm.getProxyCode());
			step1.setAppGjdq(tmc.getWriteType().getText());
			step1.setAppTypeId(tmc.getApplierType().getText());
			tminfo.setStep1(step1);
			step2 = new Step2();
			step2.setAgentFilenum(tm.getProxyCode());
			step2.setAgentPerson(tmc.getCaseProxyContactPerson());
			//设置委托书
			step2.setAgentBookPath(this.getDeleProofAttachment(tm, shareGroupToTradeMarkMap).get("fileUrl"));
			step2.setAgentBookName(this.getDeleProofAttachment(tm, shareGroupToTradeMarkMap).get("fileName"));

			step2.setCertFilePath(this.getBusinessLienceAttachment(tm, shareGroupToTradeMarkMap).get("fileUrl"));
			step2.setCertFileName(this.getBusinessLienceAttachment(tm, shareGroupToTradeMarkMap).get("fileName"));

			List<UploadAttachment> uas = tmc.getUploadAttachments();
	

		
			step2.setCertCode(tmc.getCreditCode());
			step2.setAppCnName(tmc.getApplier());
			step2.setAppCnAddr(tmc.getApplierAddress());
			step2.setAppContactPerson(tmc.getCreator());
			step2.setAppContactTel(tmc.getYwPhone());
			step2.setAppContactFax(tmc.getFax());
			step2.setAppContactZip(tmc.getMailCode());
			tminfo.setStep2(step2);
			step3 = new Step3();
			step3.setTmType(tm.getTradeMarkType().getContent());

			// TODO(?)
			if (tm.getWhetherThirdSpace()){
				step3.setIfSolidTm("ifSolidTm2");
			}else{
				step3.setIfSolidTm("ifSolidTm1");
			}

			// TODO(?)
			if (tm.getWhetherColorGroup()){
				step3.setColourSign("colourSign2");
			}else{
				step3.setColourSign("colourSign1");
			}
			step3.setTmFormType(tm.getWhetherSound().toString());
			step3.setTmFormTypeFilePath("");
			step3.setTmFormTypeFileName("");
			step3.setTmDesignDeclare(tm.getMemo());
			tminfo.setStep3(step3);


			step4 = new Step4();
			// TODO(?)
			if(tm.getWhetherShare()){
				step4.setIfShareTm("ifShareTm2");
			}else {
				step4.setIfShareTm("ifShareTm1");
			}
			tminfo.setStep4(step4);

			step5 = new Step5();
			// TODO(?)
			step5.setPriorityType("priorityType1");
			tminfo.setStep5(step5);

			step6 = new Step6();
			String str = tm.getSelectedTwoStr();
			step6.setGoods(goodSl(str, tm.getNclOne().getCode()));
			tminfo.setStep6(step6);
			
			
			step7 = new Step7();
	
			
			if(tm.getHasColor()){

				step7.setPicPath(this.getTradePictAttachment(tm, shareGroupToTradeMarkMap).get("fileUrl_color"));
				step7.setPicName(this.getTradePictAttachment(tm, shareGroupToTradeMarkMap).get("fileName_color"));
				//是否用下方的黑白按钮
				//Yuxi：这里必须逆向处理一下，和之前的机器人兼容
				step7.setIsBlack("false");
				step7.setBlackPath(this.getTradePictAttachment(tm,shareGroupToTradeMarkMap).get("fileUrl_black"));
				step7.setBlackName(this.getTradePictAttachment(tm,shareGroupToTradeMarkMap).get("fileName_black"));
			}else {
				step7.setPicPath(this.getTradePictAttachment(tm, shareGroupToTradeMarkMap).get("fileUrl_black"));
				step7.setPicName(this.getTradePictAttachment(tm, shareGroupToTradeMarkMap).get("fileName_black"));
				step7.setIsBlack("true");
				step7.setBlackPath("");
				step7.setBlackName("");
			}
			
			
			step7.setCommentPath(this.getCommentAttachment(tm, shareGroupToTradeMarkMap).get("fileUrl"));
			step7.setCommentName(this.getCommentAttachment(tm, shareGroupToTradeMarkMap).get("fileName"));
			step7.setIsPersonPhoto(tm.getWhetherPersonPhoto()==true?"true":"false");
			tminfo.setStep7(step7);
			tminfos.add(tminfo);
		}
		TmForRobotDto tfr=new TmForRobotDto();
		tfr.setCode("200");
		tfr.setCount(String.valueOf(tms.size()));
		tfr.setData(tminfos);
		return tfr;
	}

	public List<Goods> goodSl(String str, String code) {
		if(code.length()==1) {
			code="0"+code;
		}
		List<Goods> goodsList = new ArrayList<>();
		Goods goods;
		String[] lines = str.split("\\n");
		Map<String,List<String>> map = new HashMap<>();
		List<String> stss;
		for (String s : lines) {
			String[] sts = s.split(":");
			if (map.containsKey(sts[2])) {
				stss = map.get(sts[2]);
			} else {
				stss = new ArrayList<>();
			}
			stss.add(sts[1]);
			map.put(sts[2], stss);
		}
		Set<String> set = map.keySet();
		for (String st : set) {
			goods = new Goods();
			goods.setClasses(code);
			goods.setGroup(st);
			goods.setNameList(map.get(st));
			goodsList.add(goods);
		}
		return goodsList;
	}

	public TradeMark tmRobotUpdateMarkState(String proxyCode,Integer stateCode){
		Oql oql = new Oql();
		oql.setSelects("TradeMark.*");
		oql.setType(TradeMark.class);
		oql.setFilter("proxyCode = ?");
		oql.getParameters().add("proxyCode",proxyCode,Types.INTEGER);
		TradeMark tm = this.queryFirst(oql);
		tm.setMarkState(MarkState.getItem(stateCode));
		tm.toPersist();
		tm = this.save(tm);
		return tm;
	}

	@Override
	public TradeMark tmRobotUpdateMarkCode(String proxyCode, String code, Integer stateCode) {
		Oql oql = new Oql();
		oql.setSelects("TradeMark.*");
		oql.setType(TradeMark.class);
		oql.setFilter("proxyCode = ?");
		oql.getParameters().add("proxyCode",proxyCode,Types.INTEGER);
		TradeMark tm = this.queryFirst(oql);
		tm.setCode(code);
		tm.setMarkState(MarkState.getItem(stateCode));
		tm.toPersist();
		tm = this.save(tm);
		return tm;
	}

	public String updateMarkState(String ids,String type){
		Oql oql = new Oql();
		String cmdstr = "select id from ig_trade_mark where id in (?)".replace("?",ids);
		DataTable dataTable = this.pm.executeTable(cmdstr, oql.getParameters());
		List<Map<String,Object>> maps = dataTable.getValueMapList();
		List<TradeMark> list = new ArrayList<>();
		StringBuffer str = new StringBuffer("");
		for (Map map:maps){
			int id  = (int) map.get("id");
			oql = new Oql();
			oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
			oql.setType(TradeMark.class);
			oql.setFilter("id = ?");
			oql.getParameters().add("id",id,Types.INTEGER);
			TradeMark tm = this.queryFirst(oql);
			TradeMarkCase tmc = tm.getTradeMarkCase();
			List<UploadAttachment> uas = tmc.getUploadAttachments();
			boolean boo = true;
			for(UploadAttachment ua:uas){
				if (ua.getNeeded()&&StringManager.isNullOrEmpty(ua.getFileUrl())){
					boo = false;
					str.append(tm.getProxyCode()).append(",");
					break;
				}else{
					boo = true;
				}
			}
			if (boo){
				if (type.equals("1")){
					tm.setMarkState(MarkState.WAITCOMMIT);
				}else{
					tm.setMarkState(MarkState.READY);
				}
				list.add(tm);
			}
		}
		if (!list.isEmpty()){
			this.saves(list);
		}
		if (str.length()>0){
			return str.substring(0,str.lastIndexOf(",")+1);
		}else{
			return "";
		}
	}
}