package com.gongsibao.cms.service;

import com.gongsibao.cms.base.IProductService;
import com.gongsibao.cms.base.IProductTemplateService;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTemplateService extends PersistableService<ProductTemplate> implements IProductTemplateService {
    IProductService productService = ServiceFactory.create(IProductService.class);

    public ProductTemplateService() {
        super();
        this.type = ProductTemplate.class;
    }

    @Override
    public ProductTemplate getProductTemplateByCmsIdAndCityId(Integer cmsId, Integer cityId) {
        Product cmsProduct = productService.byId(cmsId);
        if (cmsProduct == null) {
            return null;
        }
        ProductTemplate resdata = getTemplateBycmsIdCityId(cmsId, cityId);
        if (resdata == null) {
            List<ProductTemplate> cmsProductTemplateList = byCmsIdAndDefault(cmsId, 1);
            if (cmsProductTemplateList.isEmpty()) {
                return null;
            }
            resdata = cmsProductTemplateList.stream().sorted((t1, t2) -> {
                return t2.getId().compareTo(t1.getId());
            }).collect(Collectors.toList()).get(0);
        }
        return resdata;
    }

    private List<ProductTemplate> byCmsIdAndDefault(Integer productId, int i) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("cms_product_id = " + productId + " AND is_default=" + i);
        }
        return this.queryList(oql);
    }

    private ProductTemplate getTemplateBycmsIdCityId(Integer cmsId, Integer cityId) {
        ProductTemplate productTemplate = null;
        StringBuilder selectBuilder = new StringBuilder();
        {
            selectBuilder.append(" SELECT pt.* FROM cms_product_template pt ");
            selectBuilder.append(" JOIN cms_product_template_bd_dict_map ptm ON pt.pkid=ptm.template_id  ");
            selectBuilder.append(" WHERE pt.cms_product_id= " + cmsId + " AND ptm.city_id= " + cityId + "");
            selectBuilder.append(" ORDER BY pt.add_time DESC ");
        }
        DataTable dataTable = this.pm.executeTable(selectBuilder.toString(), null);
        if (null != dataTable && dataTable.size() > 0) {
            productTemplate = new ProductTemplate();
            IRow row = dataTable.get(0);
            productTemplate.setId(row.getInteger("pkid"));
            productTemplate.setCreateTime(row.getDate("add_time"));
            productTemplate.setAddUserId(row.getInteger("add_user_id"));
            productTemplate.setCmsProductId(row.getInteger("cms_product_id"));
            productTemplate.setPageDescription(row.getString("page_description"));
            productTemplate.setProductId(row.getInteger("product_id"));
            productTemplate.setProductName(row.getString("product_name"));
            productTemplate.setTitle(row.getString("title"));
            productTemplate.setContent(row.getString("content"));
        }
        return productTemplate;
    }
}