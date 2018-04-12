package com.gongsibao.cms.service;

import com.gongsibao.cms.base.IProductAggregationMapService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductAggregationService;
import com.gongsibao.entity.cms.ProductAggregation;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAggregationService extends PersistableService<ProductAggregation> implements IProductAggregationService {
    IProductAggregationMapService cmsProductAggregationMapService= ServiceFactory.create(IProductAggregationMapService.class);
    public ProductAggregationService(){
        super();
        this.type=ProductAggregation.class;
    }

    @Override
    public List<ProductAggregation> getByCmsProdId(Integer cmsprodid) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("cmsProductId = "+cmsprodid+"");
        }
        List<ProductAggregation> byCmsProdIdList = this.queryList(oql);
        byCmsProdIdList = byCmsProdIdList.stream().filter(x -> x.getParentId().equals(0)).collect(Collectors.toList());
        //调用递归方法
        getAllAggregationByDiGui(byCmsProdIdList);
        return byCmsProdIdList;
    }

    /*递归获取所有层级的聚合信息*/
    private void getAllAggregationByDiGui(List<ProductAggregation> byCmsProdIdList) {
        byCmsProdIdList.forEach(x -> {
            List<ProductAggregation> aggregationList = getByCmsParentId(x.getId());
            x.setChildren(aggregationList);
            x.setCmsProductAggregationMapList(cmsProductAggregationMapService.getByAggregationId(x.getId()));
            if (x.getChildren().size() > 0)
                getAllAggregationByDiGui(x.getChildren());
        });
    }

    @Override
    public List<ProductAggregation> getByCmsParentId(Integer id) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("parentId = "+id+"");
        }
        return this.queryList(oql);
    }
}