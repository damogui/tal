package com.gongsibao.rest.controller.v1.product;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.product.IProductPriceService;
import com.gongsibao.rest.service.product.IProductService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wx/{v}/icompany/product")
@Api(1)
public class ICompanyProductController {

    @Autowired
    IProductPriceService productPriceService;
    @Autowired
    IProductService productService;

    /**
     * 包含产品cms信息，产品聚合信息，产品流程信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cmsInfo", method = RequestMethod.GET)
    private ResponseData cmsInfo(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        int productId = NumberUtils.toInt(request.getParameter("productId"));
        if (0 == productId) {
            data.setCode(-1);
            data.setMsg("商品不存在");
            return data;
        }
        try {
            // 获取cms基础信息
            data.setData(productService.cmsInfo(productId));
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(-1);
            data.setMsg("您的网络不稳定，请稍后再试。");
        }
        return data;
    }


    /**
     * 获取地区省/市/区
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ResponseData cities(HttpServletRequest request) {
        ResponseData data = new ResponseData();

        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            if (productId == 0) {
                data.setMsg("产品不能为空");
                return data;
            }

            data.setData(productPriceService.findProductCities(productId, null));
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(-1);
            data.setMsg("您的网络不稳定，请稍后再试。");
        }
        return data;
    }

    /**
     * @param request
     * @return com.gongsibao.rest.common.web.ResponseData
     * @Description:TODO 根据服务地区和产品服务, 获取行业特点
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 20:32
     */
    @RequestMapping(value = "/cmsTemplate", method = RequestMethod.GET)
    public ResponseData cmsTemplate(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        data.setCode(-1);
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            int cityId = NumberUtils.toInt(request.getParameter("cityId"));

            if (productId == 0) {
                data.setMsg("产品不能为空");
                return data;
            }
            if (cityId == 0) {
                data.setMsg("城市不能为空");
                return data;
            }
            Product cmsProduct = productService.getLastCmsByProdId(productId);
            if (null == cmsProduct) {
                data.setMsg("产品不存在");
                return data;
            }

            data.setData(productService.getProductTemplateByCmsIdAndCityId(cmsProduct.getId(), cityId));
            data.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(-1);
            data.setMsg("您的网络不稳定，请稍后再试。");
        }
        return data;
    }


    /**
     * @param
     * @return
     * @Description:TODO 根据服务地区和产品服务, 获取行业特点
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/13 10:24
     */
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public ResponseData properties(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        data.setCode(-1);
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            int cityId = NumberUtils.toInt(request.getParameter("cityId"));

            if (productId == 0) {
                data.setMsg("产品不能为空");
                return data;
            }
            if (cityId == 0) {
                data.setMsg("城市不能为空");
                return data;
            }
            data.setData(productPriceService.findProductPropertyIds(productId, cityId));
            data.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(-1);
            data.setMsg("您的网络不稳定，请稍后再试。");
        }
        return data;
    }

    /**
     * 获取无供应商的产品价格
     *
     * @param request
     * @return
     */
    @RequestMapping("/priceList")
    public ResponseData priceList(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            int cityId = NumberUtils.toInt(request.getParameter("cityId"));
            int propertyId = NumberUtils.toInt(request.getParameter("propertyId"));
            if (productId == 0) {
                data.setMsg("产品不能为空");
                return data;
            }

            if (cityId == 0) {
                data.setMsg("城市不能为空");
                return data;
            }

            if (propertyId == 0) {
                List<Dict> propertyList = productPriceService.findProductPropertyIds(productId, cityId);
                if (CollectionUtils.isNotEmpty(propertyList)) {
                    data.setMsg("请选择产品特性");
                    return data;
                }
            }
            data.setCode(200);
            data.setData(productPriceService.productPriceList(productId, cityId, propertyId));

//            Map<String, Object> params = new HashMap<>();
//            params.put("city_id", cityId);
//            params.put("product_id", productId);
//            if (productId > 0) {
//                params.put("property_id", propertyId);
//            }
//
//            List<ProdPriceAuditWebRequest> webRequests = prodPriceAuditService.getOrganizationInfo(params);
//            if (CollectionUtils.isEmpty(webRequests)) {
//                data.setMsg("该地区没有服务项");
//                return data;
//            }
//
//            result = new ArrayList<>();
//            Set<Integer> orgIds = webRequests.stream().map(ProdPriceAuditWebRequest::getOrganizationId).collect(Collectors.toSet());
//
//            int curOrgId = 4;
//            if (!orgIds.contains(4)) {
//                curOrgId = webRequests.get(0).getOrganizationId();
//            }
//
//            for (ProdPriceAuditWebRequest webRequest : webRequests) {
//                Integer organizationId = webRequest.getOrganizationId();
//                if (organizationId.compareTo(curOrgId) == 0) {
//                    result.add(webRequest);
//                }
//            }
//
//            data.setCode(200);
//            data.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(-1);
            data.setMsg("您的网络不稳定，请稍后再试。");
        }

        return data;
    }
}
