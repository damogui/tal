package com.gongsibao.rest.controller.v1.product;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.bd.dic.CouponPlatformType;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;
import com.gongsibao.entity.trade.SoOrder;
import com.netsharp.rest.base.order.IOrderService;
import com.netsharp.rest.base.product.IProductPriceService;
import com.netsharp.rest.base.product.IProductService;
import com.netsharp.rest.base.user.IAccountService;
import com.netsharp.rest.controller.annotation.Api;
import com.netsharp.rest.controller.annotation.LoginCheck;
import com.netsharp.rest.controller.exception.WxException;
import com.netsharp.rest.controller.result.BdCity;
import com.netsharp.rest.controller.security.SecurityUtils;
import com.netsharp.rest.dto.product.ProductCmsDTO;
import com.netsharp.rest.utils.JsonUtils;
import com.netsharp.rest.controller.result.RestResult;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.dto.coupon.CouponUseDTO;
import com.netsharp.rest.dto.order.OrderAddDTO;
import com.netsharp.rest.dto.product.ProductPriceDTO;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/{v}/icompany/product")
@Api(1)
public class ICompanyProductController extends BaseController {

    @Autowired
    IProductPriceService productPriceService;
    @Autowired
    IProductService productService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IOrderService orderService;

    /**
     * 包含产品cms信息，产品聚合信息，产品流程信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cmsInfo", method = RequestMethod.GET)
    private ProductCmsDTO cmsInfo(HttpServletRequest request) {
        int productId = NumberUtils.toInt(request.getParameter("productId"));
        if (0 == productId) {
            throw new WxException(RestResult.FAIL, "商品不存在");
        }
        return productService.cmsInfo(productId);
    }


    /**
     * 获取地区省/市/区
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<BdCity> cities(HttpServletRequest request) {
        int productId = NumberUtils.toInt(request.getParameter("productId"));
        if (productId == 0) {
            throw new WxException(RestResult.FAIL, "商品不能为空");
        }

        return productPriceService.findProductCities(productId, null);
    }

    /**
     * @param request
     * @return com.netsharp.rest.common.web.ResponseData
     * @Description:TODO 根据服务地区和产品服务, 获取行业特点
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 20:32
     */
    @RequestMapping(value = "/cmsTemplate", method = RequestMethod.GET)
    public ProductTemplate cmsTemplate(HttpServletRequest request) {

        int productId = NumberUtils.toInt(request.getParameter("productId"));

        int cityId = NumberUtils.toInt(request.getParameter("cityId"));

        if (productId == 0) {
            throw new WxException(RestResult.FAIL, "商品不能为空");
        }
        if (cityId == 0) {
            throw new WxException(RestResult.FAIL, "城市不能为空");
        }
        Product cmsProduct = productService.getLastCmsByProdId(productId);
        if (null == cmsProduct) {
            throw new WxException(RestResult.FAIL, "商品不存在");
        }

        ProductTemplate template = productService.getProductTemplateByCmsIdAndCityId(cmsProduct.getId(), cityId);
        return template;
    }


    /**
     * @param
     * @return
     * @Description:TODO 根据服务地区和产品服务, 获取行业特点
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/13 10:24
     */
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public List<Dict> properties(HttpServletRequest request) {
        int productId = NumberUtils.toInt(request.getParameter("productId"));
        int cityId = NumberUtils.toInt(request.getParameter("cityId"));

        if (productId == 0) {
            throw new WxException(RestResult.FAIL, "商品不能为空");
        }
        if (cityId == 0) {
            throw new WxException(RestResult.FAIL, "城市不能为空");
        }

        return productPriceService.findProductPropertyIds(productId, cityId);
    }

    /**
     * 获取无供应商的产品价格
     *
     * @param request
     * @return
     */
    @RequestMapping("/priceList")
    public List<ProductPriceDTO> priceList(HttpServletRequest request) {
        int productId = NumberUtils.toInt(request.getParameter("productId"));
        int cityId = NumberUtils.toInt(request.getParameter("cityId"));
        int propertyId = NumberUtils.toInt(request.getParameter("propertyId"));
        if (productId == 0) {
            throw new WxException(RestResult.FAIL, "商品不能为空");
        }

        if (cityId == 0) {
            throw new WxException(RestResult.FAIL, "城市不能为空");
        }

        if (propertyId == 0) {
            List<Dict> propertyList = productPriceService.findProductPropertyIds(productId, cityId);
            if (CollectionUtils.isNotEmpty(propertyList)) {
                throw new WxException(RestResult.FAIL, "请选择产品特性");
            }
        }

        return productPriceService.productPriceList(productId, cityId, propertyId);
    }

    /**
     * @param
     * @return
     * @Description: 针对当前下单信息，获取用户可用优惠券
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/19
     */
    @RequestMapping(value = "/preferential", method = RequestMethod.POST)
    @LoginCheck
    public CouponUseDTO preferential(HttpServletRequest request, @RequestBody String req) {
        OrderAddDTO orderAddDTO = JsonUtils.jsonToObject(req, OrderAddDTO.class);
        if (null == orderAddDTO) {
            throw new WxException(RestResult.FAIL, "操作失败，参数错误");
        }
        // 获取当前登录用户
        Account account = accountService.queryByOpenId(openId(request));
        if (null == account) {
            throw new WxException(RestResult.FAIL, "请先绑定用户");
        }
        orderAddDTO.setAccount(account);
        orderAddDTO.setCompanyId(0);
        orderAddDTO.setCouponPlatformType(CouponPlatformType.WEIXIN);
        Result<CouponUseDTO> result = orderService.findOrderCoupon(orderAddDTO);
        if (Result.isSuccess(result)) {
            return result.getObj();
        } else {
            throw new WxException(RestResult.FAIL, result.getMsg());
        }
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @LoginCheck
    public Map<String, Object> addOrder(HttpServletRequest request, @RequestBody String req) {
        OrderAddDTO orderAddDTO = JsonUtils.jsonToObject(req, OrderAddDTO.class);
        if (null == orderAddDTO) {
            throw new WxException(RestResult.FAIL, "操作失败，参数错误");
        }
        // 获取当前登录用户
        Account account = accountService.queryByOpenId(openId(request));
        if (null == account) {
            throw new WxException(RestResult.FAIL, "请先绑定用户");
        }
        orderAddDTO.setAccount(account);
        orderAddDTO.setCompanyId(0);
        orderAddDTO.setCouponPlatformType(CouponPlatformType.WEIXIN);

        Result<SoOrder> result = orderService.saveOrder(orderAddDTO);
        if (Result.isSuccess(result)) {
            Map<String, Object> orderInfo = new HashMap<>();
            orderInfo.put("orderId", result.getObj().getId());
            orderInfo.put("orderIdStr", SecurityUtils.rc4Encrypt(result.getObj().getId()));
            return orderInfo;
        } else {
            throw new WxException(RestResult.FAIL, result.getMsg());
        }
    }
}
