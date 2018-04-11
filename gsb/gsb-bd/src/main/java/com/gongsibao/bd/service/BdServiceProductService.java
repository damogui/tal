package com.gongsibao.bd.service;

import com.gongsibao.bd.base.IBdServiceProductService;
import com.gongsibao.entity.bd.BdService;
import com.gongsibao.entity.bd.BdServiceProduct;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class BdServiceProductService extends PersistableService<BdServiceProduct> implements IBdServiceProductService {

    public BdServiceProductService() {
        super();
        this.type = BdServiceProduct.class;
    }

    @Override
    public List<BdServiceProduct> findByServiceIds(Collection<Integer> serviceIds) {
        return findByServiceIds(serviceIds, -1);
    }

    @Override
    public List<Integer> findProductIdsByServiceId(int serviceId) {
        return null;
    }

    @Override
    public List<BdServiceProduct> findByServiceIds(Collection<Integer> serviceIds, int hot) {
        if (CollectionUtils.isEmpty(serviceIds)) {
            return new ArrayList<>();
        }
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            String isList = StringManager.join(",", Arrays.asList(serviceIds.toArray()));
            oql.setFilter("serviceId in   (" + isList + ")");
            if (hot > -1) {
                oql.setFilter("hot=? ");
                oql.getParameters().add("hot", hot, Types.INTEGER);
            }
        }
        return this.queryList(oql);
    }
}
