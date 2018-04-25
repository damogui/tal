package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.trade.base.INOrderStageService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NOrderStageService extends PersistableService<NOrderStage> implements INOrderStageService {

    public NOrderStageService(){
        super();
        this.type=NOrderStage.class;
    }

	@Override
	public List<NOrderStage> getListByOrderId(Integer orderId) {
		Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("orderId=?");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }
        List<NOrderStage> list = this.queryList(oql);
		return list;
	}
}
