package com.gongsibao.igirl.tm.web;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkCaseOptListPart extends ListPart{
	ITradeMarkCaseService service = ServiceFactory.create(ITradeMarkCaseService.class);
}