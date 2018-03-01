package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.igirl.base.IChangeTradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class MyChangeTradeMarkListPart extends ListPart{
    IChangeTradeMarkService service = ServiceFactory.create(IChangeTradeMarkService.class);
 
   @Override
	 protected String getExtraFilter() {
	   String filter=" supplierId = "+ SupplierSessionManager.getSupplierId();
	   return filter;
	 }
}