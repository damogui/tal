package com.gongsibao.trade.service.action.order.stage;

import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INOrderStageService;

/**   
 * @ClassName:  ActionApplyStagePersist   
 * @Description:TODO 申请分期保存
 * @author: 韩伟
 * @date:   2018年3月12日 下午4:52:38   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionApplyStagePersist implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		String installmentMode = "";
		INOrderStageService stageService = ServiceFactory.create(INOrderStageService.class);
		for (NOrderStage item : order.getStages()) {
			installmentMode += item.getAmount().toString() + "|";
			item.toNew();
			stageService.save(item);	
		}
		order.setStageCreateTime(new Date());
		order.setInstallmentMode(installmentMode.substring(0,installmentMode.length()-1));		
	}

}
