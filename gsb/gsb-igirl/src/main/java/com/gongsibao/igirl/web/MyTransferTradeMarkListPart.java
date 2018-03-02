package com.gongsibao.igirl.web;
import com.gongsibao.igirl.base.IChangeTradeMarkService;
import com.gongsibao.igirl.base.ITransferTradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class MyTransferTradeMarkListPart extends ListPart{
	ITransferTradeMarkService service = ServiceFactory.create(ITransferTradeMarkService.class);
 
   @Override
	 protected String getExtraFilter() {
	   String filter=" supplierId = "+ SupplierSessionManager.getSupplierId();
	   return filter;
	 }
}