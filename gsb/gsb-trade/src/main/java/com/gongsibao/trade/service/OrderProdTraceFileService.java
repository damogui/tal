package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.OrderProdTraceFile;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.entity.trade.dic.TraceFileStatus;
import com.gongsibao.trade.base.IOrderProdTraceFileService;
import com.gongsibao.trade.base.IOrderProdTraceService;

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
	public List<OrderProdTraceFile> queryList(Integer orderProdId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrderProdTraceFile.*,file.*,workflowFile.*");
			oql.setFilter("order_prod_trace_id in (select pkid from so_order_prod_trace where order_prod_id = " + orderProdId + ")");
			oql.setOrderby(" createTime DESC");
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

	@Override
	public Boolean addTraceFile(OrderProdTrace orderProdTrace, List<OrderProdTraceFile> traceFileList) {

		// 1.保存跟进
		IOrderProdTraceService traceService = ServiceFactory.create(IOrderProdTraceService.class);
		orderProdTrace = traceService.save(orderProdTrace);

		// 2.更新同类文件为历史
		this.updateStatus(orderProdTrace.getOrderProdId(), TraceFileStatus.HISTORY);

		// 3.保存跟进文件
		IFileService fileService = ServiceFactory.create(IFileService.class);
		for (OrderProdTraceFile traceFile : traceFileList) {

			File file = fileService.save(traceFile.getFile());
			traceFile.setFile(null);
			traceFile.setFileId(file.getId());
			traceFile.setOrderProdTraceId(orderProdTrace.getId());
		}
		this.saves(traceFileList);

		return true;
	}

	private void updateStatus(Integer orderProdId, TraceFileStatus status) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE so_order_prod_trace_file set is_new = ? ");
		buffer.append("WHERE order_prod_trace_id IN ( ");
		buffer.append("	SELECT pkid FROM so_order_prod_trace WHERE order_prod_id = ? AND type_id = ? ");
		buffer.append(")");

		QueryParameters qps = new QueryParameters();
		qps.add("status", status.getValue(), Types.INTEGER);
		qps.add("orderProdId", orderProdId, Types.INTEGER);
		qps.add("Type", OrderProdTraceType.Sccl.getValue(), Types.INTEGER);
		this.pm.executeNonQuery(buffer.toString(), qps);
	}
}