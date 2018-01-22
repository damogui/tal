package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;

public class TradeMarkCasePart extends FormPart {
     ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class); 
    public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile) {  	 
    	 return tradeMarkCaseService.getTradeMarkCaseModelByMobile(mobile);  	 
     }
	@Override
	public IPersistable save(IPersistable entity) {
		// TODO Auto-generated method stub
		//this.getContext().getWorkspace().ge
		//获取当前的域名
		TradeMarkCase entity1=(TradeMarkCase)entity;
		String qcurl="http://192.168.4.1:3000/qc?detailLink=http://192.168.28.41:8080/gsb/igirl/tmcase.html?mobile="+entity1.getMobile();
		entity1.setTokenImgUrl(qcurl);
		return super.save(entity1);
	}
     
    
     


}
