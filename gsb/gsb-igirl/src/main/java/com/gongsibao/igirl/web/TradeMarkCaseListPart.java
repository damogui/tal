package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkCaseListPart extends ListPart{
	ITradeMarkCaseService service = ServiceFactory.create(ITradeMarkCaseService.class);

   @Override
	 protected String getExtraFilter() {
			// TODO Auto-generated method stub
	   String filter=" supplierId = "+SupplierSessionManager.getSupplierId();
		 return filter;
	 }
	public TradeMarkCase updateOwner(Integer ttmId, Integer ownerId){
		return this.service.updateOwner(ttmId,ownerId);
	}

}