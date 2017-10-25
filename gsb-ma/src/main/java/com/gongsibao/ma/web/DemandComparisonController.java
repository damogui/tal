package com.gongsibao.ma.web;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.base.IAcquisitionDemandService;
import com.gongsibao.ma.base.ISellingDemandService;
import com.gongsibao.ma.service.matching.MatchingHandler;
import com.gongsibao.ma.web.dto.ComparisonModel;

public class DemandComparisonController {

	IAcquisitionDemandService acquisitionDemandService = ServiceFactory.create(IAcquisitionDemandService.class);
	
	ISellingDemandService sellingDemandService = ServiceFactory.create(ISellingDemandService.class);
	/**   
	 * @Title: getComparisonModel   
	 * @Description: TODO(获取对比模型)   
	 * @param: @param acquisitionDemandId
	 * @param: @param sellingDemandId
	 * @param: @return      
	 * @return: ComparisonModel      
	 * @throws   
	 */
	public ComparisonModel getComparisonModel(Integer acquisitionDemandId,Integer sellingDemandId){

		AcquisitionDemand acquisitionDemand = this.getAcquisitionDemandById(acquisitionDemandId);
		SellingDemand sellingDemand = this.getSellingDemandById(sellingDemandId);
		ComparisonModel model = MatchingHandler.getComparisonModelItems(acquisitionDemand, sellingDemand);
		return model;
	}
	
	public AcquisitionDemand getAcquisitionDemandById(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(AcquisitionDemand.class);
			oql.setSelects("AcquisitionDemand.*,pcdDetails.*,industryFeatureDetails.*,intangibleAssetss.*,fixedAssetss.*,qualificationDetails.*");
			oql.setFilter("id=?");
			oql.getParameters().add("id",id, Types.INTEGER);
		}

		return acquisitionDemandService.queryFirst(oql);
	}
	
	public SellingDemand getSellingDemandById(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(SellingDemand.class);
			oql.setSelects("SellingDemand.*,province.{id,name},city.{id,name},county.{id,name},subdiaryieCompanyDetails.*,brancheCompanyDetails.*,turnoverDetails.*,intangibleAssetss.*,fixedAssetss.*,qualificationDetails.*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		return sellingDemandService.queryFirst(oql);
	}
}
