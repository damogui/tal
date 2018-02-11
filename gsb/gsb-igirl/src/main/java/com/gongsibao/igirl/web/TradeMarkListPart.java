package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.ConfigType;
import com.gongsibao.entity.igirl.dict.MarkState;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.utils.SupplierSessionManager;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.netsharp.attachment.Attachment;
import org.netsharp.attachment.IAttachmentService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.wx.ea.base.IEaMessageService;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkListPart extends ListPart{
    ITradeMarkService service = ServiceFactory.create(ITradeMarkService.class);   
    IUploadAttachmentService us=ServiceFactory.create(IUploadAttachmentService.class);
    public String updateMarkState(String[] ids,String type){
        return service.updateMarkState(String.join(",", ids),type);
    }
    
  private Map<Integer,String> buildShareGroupToTMAttachment(TradeMark tm) {
	   Map<Integer,String> map=new HashMap<Integer,String>();
	   for(UploadAttachment ua : tm.getTradeMarkCase().getUploadAttachments()) {
		      if(ua.getAttachmentCat()==AttachmentCat.TRADEMARK_PICT && tm.getShareGroup()==ua.getShareGroup()) {
		    	  Integer key=tm.getShareGroup().getValue();
		    	  if(!map.containsKey(key)) {
		    		    map.put(key, ua.getFileUrl());
		    	         }
		             }
	      }
	   return map;
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
	  ShareGroup  sg= tm.getShareGroup();
	  oql=new Oql();{
			oql.setType(UploadAttachment.class);
			oql.setSelects("UploadAttachment.*");
			oql.setFilter(" shareGroup=? ").setFilter("attachmentCat=?").setFilter("fileUrl is not null");
			oql.getParameters().add("shareGroup",sg.getValue(),Types.INTEGER);
			oql.getParameters().add("attachmentCat",AttachmentCat.TRADEMARK_PICT.getValue(),Types.INTEGER);
			oql.setOrderby("createTime desc");
		}
	  UploadAttachment up=us.queryFirst(oql);
	  if(up.getFileUrl()!=null) {
		  url=up.getFileUrl();
	   }
    return url;
    }
  
  public void updateMarkStateByUploadFiles(Attachment entity,String markcode,String state) {
	  service.updateMarkStateByUploadFiles(entity, markcode, state);
    }
  
   @Override
	 protected String getExtraFilter() {
			// TODO Auto-generated method stub
	   String filter=" supplierId = "+SupplierSessionManager.getSupplierId();
		 return filter;
	 }
}