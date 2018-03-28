package com.gongsibao.trade.service;

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
	public List<NOrderStage> getStageListByOrderId(Integer orderId) {
		Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("orderId =" + orderId);
        }
        List<NOrderStage> stageList = super.queryList(oql);
        return stageList;
	}
}
