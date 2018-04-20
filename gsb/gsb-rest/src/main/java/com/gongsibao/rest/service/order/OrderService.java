package com.gongsibao.rest.service.order;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.bd.Preferential;
import com.gongsibao.entity.bd.PreferentialCode;
import com.gongsibao.entity.bd.dic.DictType;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.entity.product.Price;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dic.*;
import com.gongsibao.rest.base.bd.ICouponService;
import com.gongsibao.rest.base.customer.ICustomerService;
import com.gongsibao.rest.base.order.IInvoiceService;
import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.base.product.IProductService;
import com.gongsibao.rest.dto.coupon.CouponUseDTO;
import com.gongsibao.rest.dto.coupon.CouponValidateDTO;
import com.gongsibao.rest.dto.order.OrderProdAddDTO;
import com.gongsibao.rest.dto.product.ProductPriceDTO;
import com.gongsibao.rest.web.common.util.AmountUtils;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.util.StringUtils;
import com.gongsibao.rest.dto.order.OrderAddDTO;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.annotations.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date $ $
 */
@Service
public class OrderService implements IOrderService {
    private static AtomicLong atomicLong = new AtomicLong();

    // 订单服务
    com.gongsibao.trade.base.IOrderService tradeOrderService = ServiceFactory.create(com.gongsibao.trade.base.IOrderService.class);

    IPayService payService = ServiceFactory.create(IPayService.class);

    // 字典服务
    IDictService dictService = ServiceFactory.create(IDictService.class);

    // 产品服务
    @Autowired
    IProductService productService;

    // CRM客户服务
    @Autowired
    ICustomerService customerService;

    // 优惠券服务
    @Autowired
    ICouponService couponService;

    @Autowired
    IInvoiceService invoiceService;

    @Override
    @SuppressWarnings({"unchecked"})
    @Transaction
    public Result<SoOrder> saveOrder(OrderAddDTO orderAddDTO) {
        // 1. 处理订单信息
        Result<SoOrder> result = doOrder(orderAddDTO);
        if (Result.isFailed(result)) {
            return result;
        }
        SoOrder order = result.getObj();

        // 2. 处理优惠券信息
        Result<Preferential> couponResult = doCoupon(order, orderAddDTO);
        if (Result.isFailed(couponResult)) {
            result.setObj(null);
            result.setMsg(couponResult.getMsg());
            result.setStatus(ResponseStatus.FAILED);
            return result;
        }

        // 3. 处理发票信息
        Invoice invoice = doInvoice(order, orderAddDTO);

        // 4. 保存订单
        order = tradeOrderService.saveWebOrder(order, invoice, StringUtils.stringToList(orderAddDTO.getOrderDiscount()));

        result.setObj(order);
        return result;
    }

    @Override
    public Result<CouponUseDTO> findOrderCoupon(OrderAddDTO orderAddDTO) {
        Result<CouponUseDTO> result = new Result<>();

        Result<SoOrder> orderResult = doOrder(orderAddDTO);
        if (Result.isFailed(orderResult)) {
            result.setMsg(orderResult.getMsg());
            result.setStatus(orderResult.getStatus());
            return result;
        }

        SoOrder order = orderResult.getObj();

        CouponUseDTO couponDto = couponService.findAccountCoupons(orderAddDTO.getAccount().getId(), orderAddDTO.getCouponPlatformType(), order);
        result.setObj(couponDto);
        return result;
    }

    @Override
    public Integer countByAccountId(Integer accountId, boolean isPaid) {
        return tradeOrderService.countByAccountId(accountId, isPaid);
    }

    @Override
    public void updateOnlinePay(OrderPayDTO orderPayDTO) {
        tradeOrderService.updateOnlinePay(orderPayDTO);
    }

    private Invoice doInvoice(SoOrder order, OrderAddDTO orderAddDTO) {
        // 发票信息处理
        Integer invoiceId = orderAddDTO.getInvoiceId();
        if (null != invoiceId && invoiceId > 0) {
            Invoice invoice = invoiceService.byId(invoiceId);
            if (null != invoice) {
                // 订单状态
                order.setIsInvoice(true);
                // 发票更新
                {
                    invoice.toPersist();
                    invoice.setAmount(order.getPayablePrice());
                    invoice.setContent(orderAddDTO.getCouponPlatformType().getText() + "下单发票");
                }

                // 订单关联发票
                OrderInvoiceMap orderInvoiceMap = new OrderInvoiceMap();
                {
                    orderInvoiceMap.toNew();
                    orderInvoiceMap.setInvoiceId(invoice.getId());
                }
                invoice.setOrderInvoiceMaps(Arrays.asList(orderInvoiceMap));
                return invoice;
            }
        }
        return null;
    }
    @SuppressWarnings({ "unchecked" })
    private Result doCoupon(SoOrder order, OrderAddDTO orderAddDTO) {
        Result<SoOrder> result = new Result<SoOrder>();
        // 订单原价
        double payablePrice = order.getPayablePrice();

        List<String> couponNoList = StringUtils.stringToList(orderAddDTO.getOrderDiscount());
        if (CollectionUtils.isNotEmpty(couponNoList)) {
            // 查询历史订单数量
            Integer orderCount = countByAccountId(orderAddDTO.getAccount().getId(), true);

            // 优惠券查询
            Map<String, PreferentialCode> couponMap = couponService.mapByNos(couponNoList);

            // 优惠券使用平台查询
            Map<Integer, Dict> platformMap = dictService.mapByType(DictType.Yhqsypt.getValue());

            List<OrderDiscount> orderDiscountList = new ArrayList<>();
            for (String no : couponNoList) {
                PreferentialCode code = couponMap.get(no);
                if (null == code || null == code.getPreferential()) {
                    return new Result(ResponseStatus.FAILED, "优惠券[" + no + "]不存在");
                }
                Preferential coupon = code.getPreferential();
                // 封装优惠券验证对象
                CouponValidateDTO validateDTO = new CouponValidateDTO();
                {
                    validateDTO.setOrder(order);
                    validateDTO.setCouponNo(no);
                    validateDTO.setOrderCount(orderCount);
                    validateDTO.setPlatformType(orderAddDTO.getCouponPlatformType());
                    validateDTO.setPreferentialCode(code);
                    validateDTO.setPlatformMap(platformMap);
                }

                // 验证优惠券是否可用
                result = couponService.validOrderCoupon(validateDTO);
                if (Result.isFailed(result)) {
                    return result;
                }

                // 计算订单价格
                Double couponPrice = couponService.couponPrice(payablePrice, coupon);
                payablePrice = AmountUtils.sub(payablePrice, couponPrice);

                // 设置订单关联优惠券
                OrderDiscount orderDiscount = new OrderDiscount();
                {
                    orderDiscount.toNew();
                    orderDiscount.setAmount(NumberUtils.doubleRoundInt(couponPrice));
                    orderDiscount.setPreferentialId(coupon.getId());
                    orderDiscount.setNo(no);
                    orderDiscount.setSqlid("");
                    orderDiscount.setRemark(orderAddDTO.getCouponPlatformType().getText() + "下单使用");
                }
                orderDiscountList.add(orderDiscount);
            }

            // 特么的不会优惠到负值吧！
            if (payablePrice < 0) {
                payablePrice = 0d;
            }

            order.setPayablePrice(NumberUtils.doubleRoundInt(payablePrice));
            order.setDiscounts(orderDiscountList);
        }

        return result;
    }


    private Result<SoOrder> doOrder(OrderAddDTO orderAddDTO) {
        Account account = orderAddDTO.getAccount();
        // 组建order对象
        List<OrderProdAddDTO> prodDtoList = orderAddDTO.getProductList();
        if (CollectionUtils.isEmpty(prodDtoList)) {
            return new Result<>(ResponseStatus.FAILED, "商品不能为空");
        }

        int customerId = 0;
        Customer customer = customerService.byAccountId(account.getId());
        if (null != customer) {
            customerId = customer.getId();
        }

        // 产品id
        List<Integer> productIds = new ArrayList<>();
        // 字段id
        List<Integer> dictIds = new ArrayList<>();
        // 定价id
        List<Integer> priceIds = new ArrayList<>();
        for (OrderProdAddDTO orderProdAddDto : prodDtoList) {
            List<ProductPriceDTO> priceList = orderProdAddDto.getPriceList();
            if (CollectionUtils.isEmpty(priceList)) {
                return new Result<>(ResponseStatus.FAILED, "定价不能为空");
            }

            productIds.add(orderProdAddDto.getProductId());
            dictIds.add(orderProdAddDto.getCityId());

            for (ProductPriceDTO productPriceDTO : priceList) {
                priceIds.add(productPriceDTO.getPriceId());
            }
        }

        // 获取产品
        Map<Integer, Product> productMap = productService.mapByIds(productIds);
        // 字典
        Map<Integer, Dict> dictMap = dictService.findMapByIds(dictIds);
        // 定价
        Map<Integer, Price> priceMap = productService.mapPriceByIds(priceIds);

        // 封装orderProd 对象
        List<OrderProd> orderProdList = new ArrayList<>();
        Map<String, Integer> productNumMap = new HashMap<>();
        for (OrderProdAddDTO orderProdAddDto : prodDtoList) {
            Integer productId = orderProdAddDto.getProductId();
            Product product = productMap.get(productId);
            if (null == product) {
                return new Result<>(ResponseStatus.FAILED, "商品不能为空");
            }
            Dict city = dictMap.get(orderProdAddDto.getCityId());
            if (null == city) {
                return new Result<>(ResponseStatus.FAILED, "请选择城市");
            }
            int quantity = 0;
            if (StringUtils.isNotBlank(orderAddDTO.getCategoryIds())) {
                quantity = StringUtils.idsToList(orderAddDTO.getCategoryIds()).size();
            } else {
                quantity = orderProdAddDto.getQuantity();
            }

            if (quantity == 0) {
                quantity = 1;
            }

            for (int i = 0; i < quantity; i++) {
                // 现价
                int opPrice = 0;
                // 原价
                int opOriginalPrice = 0;

                List<OrderProdItem> itemList = new ArrayList<>();
                for (ProductPriceDTO productPriceDTO : orderProdAddDto.getPriceList()) {
                    Price price = priceMap.get(productPriceDTO.getPriceId());
                    if (null == price) {
                        return new Result<>(ResponseStatus.FAILED, "定价不存在");
                    }
                    opPrice = opPrice + price.getPrice();
                    opOriginalPrice = opOriginalPrice + price.getOriginalPrice();

                    OrderProdItem item = new OrderProdItem();
                    {
                        item.toNew();
                        item.setPrice(price.getPrice());
                        item.setPriceOriginal(price.getOriginalPrice());
                        item.setServiceName(StringUtils.trimToEmpty(productPriceDTO.getTypeName()));
                        item.setPriceId(productPriceDTO.getPriceId());
                        item.setUnitName(StringUtils.trimToEmpty(productPriceDTO.getUnitName()));
                        item.setPriceRefund(0);
                        item.setIsBbk("");
                        item.setCreatorId(0);
                    }
                    itemList.add(item);
                }

                OrderProd orderProd = new OrderProd();
                {
                    orderProd.toNew();
                    orderProd.setNo("");
                    orderProd.setOrderId(0);
                    orderProd.setProductId(productId);
                    orderProd.setProductName(product.getName());
                    orderProd.setCityId(orderProdAddDto.getCityId());
                    orderProd.setCity(city);
                    orderProd.setCityName(city.getName());
                    orderProd.setQuantity(1);
                    orderProd.setCompanyId(orderAddDTO.getCompanyId());
                    orderProd.setTrademarkId(0);
                    orderProd.setPrice(opPrice);
                    orderProd.setPriceOriginal(opOriginalPrice);
                    orderProd.setCostStatus(CostStatus.NOENTRY);
                    orderProd.setOwnerId(orderAddDTO.getOwnerId());
                    orderProd.setCustomerId(customerId);
                    orderProd.setSettleStatus(SettleStatus.NO_SETTLEMENT);
                    orderProd.setItems(itemList);
                    orderProd.setCompanyId(0);
                    orderProd.setCreatorId(0);
                }
                productNumMap.put(product.getName(), 1 + NumberUtils.toInt(productNumMap.get(product.getName())));
                orderProdList.add(orderProd);
            }
        }

        int orderPrice = 0;
        int orderPayablePrice = 0;
        for (OrderProd orderProd : orderProdList) {
            orderPrice = orderPrice + orderProd.getPriceOriginal();
            orderPayablePrice = orderPayablePrice + orderProd.getPrice();
        }

        StringBuilder prodName = new StringBuilder();
        for (Map.Entry<String, Integer> entry : productNumMap.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            if (v == 1) {
                prodName.append(k).append(",");
            } else {
                prodName.append(k).append("*").append(v).append(",");
            }
        }

        if (prodName.length() > 0) {
            prodName = prodName.delete(prodName.length() - 1, prodName.length());
        }

        SoOrder order = new SoOrder();
        {
            order.toNew();
            order.setNo(System.currentTimeMillis() + "-" + String.valueOf(atomicLong.getAndIncrement() % 1024));
            order.setProducts(orderProdList);
            order.setAccountId(account.getId());
            order.setAccountMobile(account.getMobilePhone());
            order.setAccountName(StringUtils.isBlank(account.getRealName()) ? account.getMobilePhone() : account.getRealName());
            order.setOwnerId(orderAddDTO.getOwnerId());
            order.setTaskId(0);
            order.setTotalPrice(orderPrice);
            order.setPayablePrice(orderPayablePrice);
            order.setPaidPrice(0);
            order.setChannelOrderNo("");
            order.setSourceType(OrderSourceType.IGIRL_TM);
            order.setIsInstallment(false);
            order.setInstallmentAuditStatusId(AuditStatusType.wu);
            order.setCompanyId(orderAddDTO.getCompanyId());
            order.setPlatformSource(OrderPlatformSourceType.Gsb);
            order.setCustomerId(customer.getId());
            order.setCustomerName(customer.getRealName());
            order.setDeliverId(orderAddDTO.getDeliverId());
            order.setCompanyId(orderAddDTO.getCompanyId());
            order.setCreatorId(0);
            order.setProdName(prodName.toString());

        }
        Result<SoOrder> result = new Result<>();
        result.setObj(order);
        return result;
    }
}
