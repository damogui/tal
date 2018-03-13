package com.gongsibao.igirl.web;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.utils.SupplierSessionManager;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkCaseOptListPart extends ListPart{
	ITradeMarkCaseService service = ServiceFactory.create(ITradeMarkCaseService.class);
}