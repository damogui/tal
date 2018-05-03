package com.gongsibao.igirl.tm.web;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.igirl.tm.ChangeTradeMark;
import com.gongsibao.igirl.tm.base.IChangeTradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class ChangeTradeMarkListPart extends ListPart{
    IChangeTradeMarkService service = ServiceFactory.create(IChangeTradeMarkService.class);
 
   @Override
	 protected String getExtraFilter() {
			// TODO Auto-generated method stub
	   String filter=" supplierId = "+SupplierSessionManager.getSupplierId();
		 return filter;
	 }

   public ChangeTradeMark updateState(String agentFileNum,Integer state){
       return service.updateCtmState(agentFileNum,state);
   }

   public ChangeTradeMark updateOwner(Integer ctmId,Integer ownerId){
        return service.updateOwner(ctmId,ownerId);
   }

    public Integer getTradeMarkCaseSupplierId(){
        return SupplierSessionManager.getSupplierId();
    }
}