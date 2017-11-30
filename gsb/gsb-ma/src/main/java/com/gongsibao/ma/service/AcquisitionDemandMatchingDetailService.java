package com.gongsibao.ma.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.AcquisitionDemandMatchingDetail;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.base.IAcquisitionDemandMatchingDetailService;
import com.gongsibao.ma.base.IAcquisitionDemandService;
import com.gongsibao.ma.base.ISellingDemandService;
import com.gongsibao.ma.service.matching.MatchingHandler;

@Service
public class AcquisitionDemandMatchingDetailService extends PersistableService<AcquisitionDemandMatchingDetail> implements IAcquisitionDemandMatchingDetailService {

	public AcquisitionDemandMatchingDetailService() {

		super();
		this.type = AcquisitionDemandMatchingDetail.class;
	}

	@Override
	public boolean refresh(Integer acquisitionDemandId) {

		IAcquisitionDemandService acquisitionDemandService = ServiceFactory.create(IAcquisitionDemandService.class);
		AcquisitionDemand acquisitionDemand = acquisitionDemandService.byId(acquisitionDemandId);

		ISellingDemandService sellingDemandService = ServiceFactory.create(ISellingDemandService.class);
		List<SellingDemand> sellingDemandList = sellingDemandService.getMatchingList();

		// 删除旧的
		this.deleteByAcquisitionDemandId(acquisitionDemandId);
		
		AcquisitionDemandMatchingDetail mathingDetail = null;
		for (SellingDemand sellingDemand : sellingDemandList) {

			mathingDetail = new AcquisitionDemandMatchingDetail();
			mathingDetail.toNew();
			
			int matchingRate = MatchingHandler.matching(acquisitionDemand, sellingDemand);
			mathingDetail.setMatchingRate(matchingRate);
			
			mathingDetail.setAcquisitionDemandId(acquisitionDemandId);
			mathingDetail.setSellingDemandId(sellingDemand.getId());
			mathingDetail.setSelingStatus(sellingDemand.getSelingStatus());
			mathingDetail.setCompanyName(sellingDemand.getCompanyName());
			this.save(mathingDetail);
		}


		return true;
	}

	/**
	 * @Title: deleteByAcquisitionDemandId
	 * @Description: TODO(根据acquisitionDemandId删除明细)
	 * @param: @param acquisitionDemandId
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	private boolean deleteByAcquisitionDemandId(Integer acquisitionDemandId) {

		String cmdText = "DELETE FROM ma_acquisition_demand_matching_detail WHERE acquisition_demand_id=?";
		QueryParameters qps = new QueryParameters();
		{
			qps.add("@acquisitionDemandId", acquisitionDemandId, Types.INTEGER);
		}
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}

	@Override
	public List<AcquisitionDemandMatchingDetail> getByAcquisitionDemandId(Integer acquisitionDemandId) {
		Oql oql = new Oql();
		{

			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("acquisitionDemandId=?");
			oql.getParameters().add("acquisitionDemandId", acquisitionDemandId, Types.INTEGER);
		}

		return this.queryList(oql);
	}

}
