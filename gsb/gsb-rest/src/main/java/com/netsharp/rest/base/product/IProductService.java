package com.netsharp.rest.base.product;

import com.gongsibao.entity.bd.BdService;
import com.gongsibao.entity.bd.BdServiceProduct;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;
import com.gongsibao.entity.product.Price;
import com.netsharp.rest.dto.product.ProductCmsDTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IProductService {
    /**
     * @Description:TODO 根据产品获取最新的显示且发布的cms信息
     * @param
     * @return
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 20:00
     */
    Product getLastCmsByProdId(Integer productId);
    /**
     * @Description:TODO 根据cmsid和区域id获取，该产品的产品详情
     * @param
     * @return
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 20:00
     */
    ProductTemplate getProductTemplateByCmsIdAndCityId(Integer cmsId, Integer cityId);

    /**
     * @Description: 查询全部产品服务列表
     * @param
     * @return java.util.List<com.gongsibao.entity.bd.BdService>
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/16
     */
    List<BdService> findServiceList();

    /**
     * @Description: 通过服务id列表, 查询对应产品列表
     * @param
     * @return
     * @throws
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/11 13:30
     */
    Map<Integer, List<BdServiceProduct>> findProductMapByServiceIds(Collection<Integer> serviceIds);

    /**
     * @Description:商品详情页 - 商品CMS信息查询
     * @param
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/16
     */
    ProductCmsDTO cmsInfo(Integer productId);

    Map<Integer, com.gongsibao.entity.product.Product> mapByIds(Collection<Integer> productIds);

    Map<Integer, Price> mapPriceByIds(Collection<Integer> priceIds);
}
