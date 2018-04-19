package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.dict.FinanceDict;

@Service
public class CostDetailService extends PersistableService<CostDetail> implements ICostDetailService {

	public CostDetailService() {
		super();
		this.type = CostDetail.class;
	}
	

	public List<CostDetail> getCostDetailItem(Integer formId,Integer formType){
		//费用明细
		Oql oql = new Oql();
		oql.setType(CostDetail.class);
		oql.setSelects("costDetail.*,costType.code,costType.cashItem");
		oql.setFilter("formId=? and formType= ?");
		oql.getParameters().add("formId", formId, Types.INTEGER);
		oql.getParameters().add("formType", formType, Types.INTEGER);
		return this.queryList(oql);
	}

}
