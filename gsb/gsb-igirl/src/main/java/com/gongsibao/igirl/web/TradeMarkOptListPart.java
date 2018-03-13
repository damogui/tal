package com.gongsibao.igirl.web;
import java.sql.Types;

import org.netsharp.attachment.Attachment;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.utils.SupplierSessionManager;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkOptListPart extends ListPart{
    ITradeMarkService service = ServiceFactory.create(ITradeMarkService.class);   
    IUploadAttachmentService us=ServiceFactory.create(IUploadAttachmentService.class);
    public String updateMarkState(String[] ids,Integer type){
        return service.updateMarkState(String.join(",", ids),type);
    }
//hw 2018-03-11 未使用
//  private Map<Integer,String> buildShareGroupToTMAttachment(TradeMark tm) {
//	   Map<Integer,String> map=new HashMap<Integer,String>();
//	   for(UploadAttachment ua : tm.getTradeMarkCase().getUploadAttachments()) {
//		      if(ua.getAttachmentCat()==AttachmentCat.TRADEMARK_PICT && tm.getShareGroup()==ua.getShareGroup()) {
//		    	  Integer key=tm.getShareGroup().getValue();
//		    	  if(!map.containsKey(key)) {
//		    		    map.put(key, ua.getFileUrl());
//		    	         }
//		             }
//	      }
//	   return map;
//    }

	public String getIDPic(Integer markId) {
		String url="";
		Oql oql=new Oql();{
			oql.setType(TradeMark.class);
			oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id",markId,Types.INTEGER);
		}
		TradeMark tm=service.queryFirst(oql);
		@SuppressWarnings("unused")
		ShareGroup  sg;
		if(tm!=null){
			sg= tm.getShareGroup();
		}
		else
		{
			return url;
		}
		oql=new Oql();{
			oql.setType(UploadAttachment.class);
			oql.setSelects("UploadAttachment.*");
			oql.setFilter(" shareGroup=? and attachmentCat=? and tradeMarkCaseId=? and fileUrl is not null");
			oql.getParameters().add("shareGroup",0,Types.INTEGER);
			oql.getParameters().add("attachmentCat",AttachmentCat.PERSON_PROOF.getValue(),Types.INTEGER);
			oql.getParameters().add("tradeMarkCaseId",tm.getTradeMarkCaseId(),Types.INTEGER);
			oql.setOrderby("createTime desc");
		}
		UploadAttachment up=us.queryFirst(oql);
		if(up!=null && up.getFileUrl()!=null) {
			url=up.getFileUrl();
		}
		else
		{
			return url;
		}
		return url;
	}


	public String getBussinessPicUrl(Integer markId) {
		String url="";
		Oql oql=new Oql();{
			oql.setType(TradeMark.class);
			oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id",markId,Types.INTEGER);
		}
		TradeMark tm=service.queryFirst(oql);
		@SuppressWarnings("unused")
		ShareGroup  sg;
		if(tm!=null){
			sg= tm.getShareGroup();
		}
		else
		{
			return url;
		}
		oql=new Oql();{
			oql.setType(UploadAttachment.class);
			oql.setSelects("UploadAttachment.*");
			oql.setFilter(" shareGroup=? and attachmentCat=? and tradeMarkCaseId=? and fileUrl is not null");
			oql.getParameters().add("shareGroup",0,Types.INTEGER);
			oql.getParameters().add("attachmentCat",AttachmentCat.BUSINESS_LIEN.getValue(),Types.INTEGER);
			oql.getParameters().add("tradeMarkCaseId",tm.getTradeMarkCaseId(),Types.INTEGER);
			oql.setOrderby("createTime desc");
		}
		UploadAttachment up=us.queryFirst(oql);
		if(up!=null && up.getFileUrl()!=null) {
			url=up.getFileUrl();
		}
		else
		{
			return url;
		}
		return url;
	}

	public String getProxyPicUrl(Integer markId) {
		String url="";
		Oql oql=new Oql();{
			oql.setType(TradeMark.class);
			oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id",markId,Types.INTEGER);
		}
		TradeMark tm=service.queryFirst(oql);
		ShareGroup  sg;
		if(tm!=null){
			sg= tm.getShareGroup();
		}
		else
		{
			return url;
		}
		oql=new Oql();{
			oql.setType(UploadAttachment.class);
			oql.setSelects("UploadAttachment.*");
			oql.setFilter(" shareGroup=? and attachmentCat=? and tradeMarkCaseId=? and fileUrl is not null");
			oql.getParameters().add("shareGroup",sg.getValue(),Types.INTEGER);
			oql.getParameters().add("attachmentCat",AttachmentCat.DELEGATE_PROOF.getValue(),Types.INTEGER);
			oql.getParameters().add("tradeMarkCaseId",tm.getTradeMarkCaseId(),Types.INTEGER);
			oql.setOrderby("createTime desc");
		}
		UploadAttachment up=us.queryFirst(oql);
		if(up!=null && up.getFileUrl()!=null) {
			url=up.getFileUrl();
		}
		else
		{
			return url;
		}
		return url;
	}


  public String getTradeMarkPicUrl(Integer markId) {
	  String url="";
	  Oql oql=new Oql();{
			oql.setType(TradeMark.class);
			oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id",markId,Types.INTEGER);
		}
	  TradeMark tm=service.queryFirst(oql);
	  ShareGroup  sg;
	  if(tm!=null){
	  	sg= tm.getShareGroup();
	  }
	  else
	  {
	  	return url;
	  }


	  oql=new Oql();{
			oql.setType(UploadAttachment.class);
			oql.setSelects("UploadAttachment.*");
			oql.setFilter(" shareGroup=? and attachmentCat=? and tradeMarkCaseId=? and fileUrl is not null");
			oql.getParameters().add("shareGroup",sg.getValue(),Types.INTEGER);
			oql.getParameters().add("attachmentCat",AttachmentCat.TRADEMARK_PICT.getValue(),Types.INTEGER);
			oql.getParameters().add("tradeMarkCaseId",tm.getTradeMarkCaseId(),Types.INTEGER);
			oql.setOrderby("createTime desc");
		}
	  UploadAttachment up=us.queryFirst(oql);

	  if(up!=null && up.getFileUrl()!=null) {
		  url=up.getFileUrl();
	   }
	   else
	  {
	  	return url;
	  }
    return url;
    }
  
  public void updateMarkStateByUploadFiles(Attachment entity,String markcode,String state) {
	  service.updateMarkStateByUploadFiles(entity, markcode, state);
    }

}