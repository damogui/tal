package com.gongsibao.trade.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

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

	@Override
	public List<OrderProdTraceFile> queryOrderProdTraceFiles(Integer orderProdId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrderProdTraceFile.*,file.*");
			oql.setFilter("order_prod_trace_id in (select pkid from so_order_prod_trace where order_prod_id = " + orderProdId + ")");
			oql.setOrderby(" isTop DESC");
		}
		return this.queryList(oql);
	}

	@Override
	public Boolean topTraceFile(Integer orderProdId, Integer traceFileId) {
		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order_prod_trace_file");
			updateSql.set("is_top", 0);
			updateSql.where("order_prod_trace_id in (select pkid from so_order_prod_trace where order_prod_id = " + orderProdId + ")");
		}
		this.pm.executeNonQuery(updateSql.toSQL(), null);
		
		updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order_prod_trace_file");
			updateSql.set("is_top", 1);
			updateSql.where("pkid =" + traceFileId);
		}
		return this.pm.executeNonQuery(updateSql.toSQL(), null) > 0;
	}
}