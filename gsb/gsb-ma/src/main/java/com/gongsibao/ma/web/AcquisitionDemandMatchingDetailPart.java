package com.gongsibao.ma.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.panda.commerce.DetailPart;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.json.DatagridResultJson;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.AcquisitionDemandMatchingDetail;
import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.base.IAcquisitionDemandMatchingDetailService;
import com.gongsibao.ma.base.IAcquisitionDemandService;
import com.gongsibao.ma.base.IMaOrderService;
import com.gongsibao.ma.base.ISellingDemandService;

public class AcquisitionDemandMatchingDetailPart extends DetailPart{

	IMaOrderService maOrderService = ServiceFactory.create(IMaOrderService.class);
	
	ISellingDemandService sellingDemandService = ServiceFactory.create(ISellingDemandService.class);
	
	IAcquisitionDemandService acquisitionDemandService = ServiceFactory.create(IAcquisitionDemandService.class);
	
	IAcquisitionDemandMatchingDetailService service = ServiceFactory.create(IAcquisitionDemandMatchingDetailService.class);
	/**   
	 * @Title: refresh   
	 * @Description: TODO(刷新匹配明细)   
	 * @param: @param acquisitionDemandId
	 * @param: @return      
	 * @return: ArrayList<AcquisitionDemandMatchingDetail>      
	 * @throws   
	 */
	public boolean refresh(Integer acquisitionDemandId){
		
		return service.refresh(acquisitionDemandId);
	}
	
	/**   
	 * @Title: createOrder   
	 * @Description: TODO(生成订单)   
	 * @param: @param acquisitionDemandId
	 * @param: @param sellingDemandId
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	public Integer createOrder(Integer acquisitionDemandId,Integer sellingDemandId){
		
		//是否要控制同一收购需求和出售需求多次生单？
		
		AcquisitionDemand acquisitionDemand = acquisitionDemandService.byId(acquisitionDemandId);
		SellingDemand sellingDemand = sellingDemandService.byId(sellingDemandId);
		
		MaOrder order = new MaOrder();
		order.toNew();
		order.setCompanyName(sellingDemand.getCompanyName());
		order.setSellerId(sellingDemand.getCreatorId());
		order.setBuyerId(acquisitionDemand.getCreatorId());
		
		maOrderService.save(order);
		return order.getId();
	}
	
	/**
	 * 查询匹配明细
	 * @param acquisitionDemandId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Object queryList(Integer acquisitionDemandId,int pageIndex,int pageSize){
		
		Paging paging = new Paging();
		{
			paging.setPageNo(pageIndex);
			paging.setPageSize(pageSize);
		}
		
		Oql oql = new Oql();
		{
			oql.setSelects("*");
			oql.setType(AcquisitionDemandMatchingDetail.class);
			oql.setFilter("acquisitionDemandId=?");
			oql.getParameters().add("acquisitionDemandId", acquisitionDemandId, Types.INTEGER);
			oql.setPaging(paging);
			oql.setOrderby("matchingRate Desc");
		}
		
		List<AcquisitionDemandMatchingDetail> rows = service.queryList(oql);
		EasyuiDatagridResult result = new EasyuiDatagridResult();{
			result.setRows(rows);
			result.setTotal(oql.getPaging().getTotalCount());
		}

		DatagridResultJson parser = new DatagridResultJson(result, pdatagrid);
		Object json = parser.parse();
		return json;
	}
}
