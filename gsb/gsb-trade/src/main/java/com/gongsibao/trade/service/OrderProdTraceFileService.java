package com.gongsibao.trade.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderProdTraceFile;
import com.gongsibao.trade.base.IOrderProdTraceFileService;

@Service
public class OrderProdTraceFileService extends PersistableService<OrderProdTraceFile> implements IOrderProdTraceFileService {

	public OrderProdTraceFileService() {
		super();
		this.type = OrderProdTraceFile.class;
	}

	@Override
	public List<Integer> queryWorkflowFileId(List<Integer> orderProdTraceIds) {

		String idsStr = StringManager.join(",", orderProdTraceIds);
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,fileId");
			oql.setFilter("orderProdTraceId in (" + idsStr + ")");
		}
		List<OrderProdTraceFile> fileList = this.queryList(oql);
		List<Integer> idList = new ArrayList<Integer>();
		for (OrderProdTraceFile file : fileList) {
			idList.add(file.getFileId());
		}
		return idList;
	}
}