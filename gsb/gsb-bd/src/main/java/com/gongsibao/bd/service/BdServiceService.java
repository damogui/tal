package com.gongsibao.bd.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IBdServiceProductService;
import com.gongsibao.bd.base.IBdServiceService;
import com.gongsibao.entity.bd.BdService;
import com.gongsibao.entity.bd.BdServiceProduct;
@Service
public class BdServiceService extends PersistableService<BdService> implements IBdServiceService {

    IBdServiceProductService productService=ServiceFactory.create(IBdServiceProductService.class);
    public BdServiceService() {
        super();
        this.type = BdService.class;
    }


    @Override
    public List<BdService> findServiceList() {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("isEnabled=? ");
            oql.getParameters().add("isEnabled", 1, Types.INTEGER);
        }
        List<BdService> list = this.queryList(oql);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<BdServiceProduct> findProductList(int serviceId) {
        return null;
    }

    @Override
    public List<BdServiceProduct> findProductList(int serviceId, int hot) {
        return null;
    }


}
