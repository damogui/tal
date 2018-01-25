package com.gongsibao.igirl.web;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.anno.Authorization;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.dto.TradeMark.TradeMarkApplyInfo;

public class TradeMarkCasePart extends FormPart {
     ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
	ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);

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

	@Authorization(is = false)
	public List<TradeMarkApplyInfo> tmsForRobot(Integer innerHour){
		return tradeMarkService.tmsForRobot(innerHour);
	}
     
    
     


}
