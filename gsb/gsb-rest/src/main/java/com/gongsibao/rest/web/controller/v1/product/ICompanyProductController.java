package com.gongsibao.rest.web.controller.v1.product;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.bd.dic.CouponPlatformType;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.base.product.IProductPriceService;
import com.gongsibao.rest.base.product.IProductService;
import com.gongsibao.rest.base.user.IAccountService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.apiversion.LoginCheck;
import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.rest.web.common.util.JsonUtils;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.web.controller.BaseController;
import com.gongsibao.rest.web.dto.coupon.CouponUseDTO;
import com.gongsibao.rest.web.dto.order.OrderAddDTO;
import com.gongsibao.rest.web.dto.product.ProductPriceDTO;
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
    private ResponseData cmsInfo(HttpServletRequest request) {
        int productId = NumberUtils.toInt(request.getParameter("productId"));
        if (0 == productId) {
            return ResponseData.getError(ResponseData.FAIL, "商品不存在");
        }
        try {
            // 获取cms基础信息
            return ResponseData.getSuccess(productService.cmsInfo(productId), "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }


    /**
     * 获取地区省/市/区
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ResponseData cities(HttpServletRequest request) {
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            if (productId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "商品不能为空");
            }

            return ResponseData.getSuccess(productPriceService.findProductCities(productId, null), "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }

    /**
     * @param request
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @Description:TODO 根据服务地区和产品服务, 获取行业特点
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/12 20:32
     */
    @RequestMapping(value = "/cmsTemplate", method = RequestMethod.GET)
    public ResponseData cmsTemplate(HttpServletRequest request) {
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            int cityId = NumberUtils.toInt(request.getParameter("cityId"));

            if (productId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "商品不能为空");
            }
            if (cityId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "城市不能为空");
            }
            Product cmsProduct = productService.getLastCmsByProdId(productId);
            if (null == cmsProduct) {
                return ResponseData.getError(ResponseData.FAIL, "商品不存在");
            }


            ProductTemplate template = productService.getProductTemplateByCmsIdAndCityId(cmsProduct.getId(), cityId);
            return ResponseData.getSuccess(template, "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
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
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            int cityId = NumberUtils.toInt(request.getParameter("cityId"));

            if (productId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "商品不能为空");
            }
            if (cityId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "城市不能为空");
            }

            List<Dict> propertyList = productPriceService.findProductPropertyIds(productId, cityId);
            return ResponseData.getSuccess(propertyList, "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }

    /**
     * 获取无供应商的产品价格
     *
     * @param request
     * @return
     */
    @RequestMapping("/priceList")
    public ResponseData priceList(HttpServletRequest request) {
        try {
            int productId = NumberUtils.toInt(request.getParameter("productId"));
            int cityId = NumberUtils.toInt(request.getParameter("cityId"));
            int propertyId = NumberUtils.toInt(request.getParameter("propertyId"));
            if (productId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "商品不能为空");
            }

            if (cityId == 0) {
                return ResponseData.getError(ResponseData.FAIL, "城市不能为空");
            }

            if (propertyId == 0) {
                List<Dict> propertyList = productPriceService.findProductPropertyIds(productId, cityId);
                if (CollectionUtils.isNotEmpty(propertyList)) {
                    return ResponseData.getError(ResponseData.FAIL, "请选择产品特性");
                }
            }

            List<ProductPriceDTO> priceList = productPriceService.productPriceList(productId, cityId, propertyId);
            return ResponseData.getSuccess(priceList, "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }

    /**
     * @Description: 针对当前下单信息，获取用户可用优惠券
     * @param
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/19
     */
    @RequestMapping(value = "/preferential", method = RequestMethod.POST)
    @LoginCheck
    public ResponseData preferential(HttpServletRequest request, @RequestBody String req) {
        try {
            OrderAddDTO orderAddDTO = JsonUtils.jsonToObject(req, OrderAddDTO.class);
            if (null == orderAddDTO) {
                return ResponseData.getError(ResponseData.FAIL, "操作失败，参数错误");
            }

            // 获取当前登录用户
            Account account = accountService.queryByOpenId(openId(request));
            if (null == account) {
                return ResponseData.getError(ResponseData.FAIL, "请先绑定用户");
            }
            orderAddDTO.setAccount(account);
            orderAddDTO.setCompanyId(0);
            orderAddDTO.setCouponPlatformType(CouponPlatformType.WEIXIN);

            Result<CouponUseDTO> result = orderService.findOrderCoupon(orderAddDTO);
            if (Result.isSuccess(result)) {
                return ResponseData.getSuccess(result.getObj(), "");
            } else {
                return ResponseData.getError(ResponseData.FAIL, result.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @LoginCheck
    public ResponseData addOrder(HttpServletRequest request, @RequestBody String req) {
        OrderAddDTO orderAddDTO = JsonUtils.jsonToObject(req, OrderAddDTO.class);
        if (null == orderAddDTO) {
            return ResponseData.getError(ResponseData.FAIL, "操作失败，参数错误");
        }

        try {
            // 获取当前登录用户
            Account account = accountService.queryByOpenId(openId(request));
            if (null == account) {
                return ResponseData.getError(ResponseData.FAIL, "请先绑定用户");
            }
            orderAddDTO.setAccount(account);
            orderAddDTO.setCompanyId(0);
            orderAddDTO.setCouponPlatformType(CouponPlatformType.WEIXIN);

            Result<SoOrder> result = orderService.saveOrder(orderAddDTO);
            if (Result.isSuccess(result)) {
                Map<String, Object> orderInfo = new HashMap<>();
                orderInfo.put("orderId", result.getObj().getId());
                orderInfo.put("orderIdStr", SecurityUtils.rc4Encrypt(result.getObj().getId()));
                return ResponseData.getSuccess(orderInfo, "");
            } else {
                return ResponseData.getError(ResponseData.FAIL, result.getMsg());
            }
        } catch (Exception e) {
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }
}
