package com.gongsibao.rest.service.order;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.bd.base.IPreferentialCodeService;
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
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dic.*;
import com.gongsibao.product.base.IWorkflowNodeService;
import com.gongsibao.rest.base.bd.ICouponService;
import com.gongsibao.rest.base.customer.ICustomerService;
import com.gongsibao.rest.base.order.IInvoiceService;
import com.gongsibao.rest.base.order.IOrderProdTraceService;
import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.base.product.IProductService;
import com.netsharp.rest.common.security.SecurityUtils;
import com.netsharp.rest.util.AmountUtils;
import com.netsharp.rest.util.NumberUtils;
import com.netsharp.rest.util.StringUtils;
import com.netsharp.rest.common.result.Pager;
import com.netsharp.rest.dto.coupon.CouponUseDTO;
import com.netsharp.rest.dto.coupon.CouponValidateDTO;
import com.netsharp.rest.dto.order.*;
import com.netsharp.rest.dto.product.ProductPriceDTO;
import com.netsharp.rest.dto.order.OrderAddDTO;
import com.gongsibao.trade.base.IOrderDiscountService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.annotations.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date $ $
 */
@Service
@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class OrderService implements IOrderService {
    private static AtomicLong atomicLong = new AtomicLong();

    // 订单服务
    com.gongsibao.trade.base.IOrderService tradeOrderService = ServiceFactory.create(com.gongsibao.trade.base.IOrderService.class);

    // 明细订单服务
    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

    // so_pay支付服务
    IPayService payService = ServiceFactory.create(IPayService.class);

    // 字典服务
    IDictService dictService = ServiceFactory.create(IDictService.class);
    IWorkflowNodeService workflowNodeService = ServiceFactory.create(IWorkflowNodeService.class);
    IOrderDiscountService orderDiscountService = ServiceFactory.create(IOrderDiscountService.class);
    IPreferentialCodeService preferentialCodeService = ServiceFactory.create(IPreferentialCodeService.class);

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

    @Autowired
    IOrderProdTraceService orderProdTraceService;
    @Autowired
    private com.gongsibao.rest.base.dict.IDictService dictRestService;

    @Override
    public SoOrder getById(Integer orderId) {
        return tradeOrderService.getByOrderId(orderId);
    }

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

    @Override
    public Pager<OrderDTO> pageMyOrder(Integer accountId, Integer status, int currentPage, int pageSize) {
        int total = tradeOrderService.countOrderAccountIdStatus(accountId, status);
        if (total <= 0) {
            return new Pager<>(total, currentPage, pageSize);
        }
        Pager<OrderDTO> pager = new Pager<OrderDTO>(total, currentPage, pageSize);
        List<SoOrder> soOrders = tradeOrderService.pageOrderListByAccountIdStatus(accountId, status, currentPage,
                pageSize);
        if (soOrders != null) {
            List<Integer> cityIds = new ArrayList<>();
            soOrders.stream().forEach(soOrder -> {
                soOrder.getProducts().stream().forEach(orderProd -> {
                    cityIds.add(orderProd.getCityId());
                });
            });
            Map<Integer, String> cityIdNames = dictRestService.queryDictNames(101, cityIds);
            List<OrderDTO> orderDtoList = soOrders.stream().sorted((order1, order2) -> {
                Long first = order1.getCreateTime() == null ? 0 : order1.getCreateTime().getTime();
                Long next = order2.getCreateTime() == null ? 0 : order2.getCreateTime().getTime();
                return next.compareTo(first);
            }).map(soOrder -> {
                OrderDTO orderDTO = new OrderDTO();
                {
                    orderDTO.setPkid(soOrder.getId());
                    orderDTO.setNo(soOrder.getNo());
                    orderDTO.setAddTime(soOrder.getCreateTime());
                    orderDTO.setAdd_time(soOrder.getCreateTime());
                    orderDTO.setProcessStatusId(soOrder.getProcessStatus().getValue());
                    orderDTO.setPayStatusId(soOrder.getPayStatus().getValue());
                    orderDTO.setPayablePrice(soOrder.getPayablePrice());
                    orderDTO.setPaidPrice(soOrder.getPaidPrice());
                    orderDTO.setIsChangePrice(BooleanUtils.toInteger(soOrder.getIsChangePrice(), 1, 0));
                    orderDTO.setChangePriceAuditStatusId(soOrder.getChangePriceAuditStatus().getValue());
                    orderDTO.setType(soOrder.getType().getValue());
                    orderDTO.setIsInstallment(BooleanUtils.toInteger(soOrder.getIsInstallment(), 1, 0));
                    orderDTO.setInstallmentAuditStatusId(soOrder.getInstallmentAuditStatusId().getValue());
                    List<OrderProd> products = soOrder.getProducts().stream().sorted((orderProd1, orderProd2) -> {
                        Long first = orderProd1.getCreateTime() == null ? 0 : orderProd1.getCreateTime().getTime();
                        Long next = orderProd2.getCreateTime() == null ? 0 : orderProd2.getCreateTime().getTime();
                        return next.compareTo(first);
                    }).collect(Collectors.toList());
                    orderDTO.setOrderProdListWebs(products.stream().map(orderProd -> {
                        OrderProductDTO orderProductDTO = convertTo(soOrder, orderProd);
                        orderProductDTO.setCityName(cityIdNames.get(orderProd.getCityId()));
                        return orderProductDTO;
                    }).collect(Collectors.toList()));
                    if (!soOrder.getIsInstallment() && soOrder.getPaidPrice() < soOrder.getPayablePrice()) {
                        orderDTO.setPayBtn(1);
                    }

                }
                return orderDTO;
            }).collect(Collectors.toList());
            pager.setList(orderDtoList);
        }
        return pager;
    }

    @Override
    public OrderMessageDTO getOrderMessage(Integer orderProdId) {
        OrderProd orderProd = orderProdService.getById(orderProdId);
        if (null == orderProd) {
            return null;
        }
        SoOrder order = getById(orderProd.getOrderId());

        List<OrderProdTraceDTO> traceList = orderProdTraceService.queryTraceByCondition(orderProdId, Arrays.asList(3151, 3153, 31501));
        Dict city = dictService.byId(orderProd.getCityId());
        String cityName = null == city ? "" : city.getName();

        OrderMessageDTO dto = new OrderMessageDTO();
        // 订单内信息
        dto.setAccountName(order.getAccountName());
        dto.setOrderNo(order.getNo());
        dto.setCreateTime(order.getCreateTime());
        dto.setOrderPrice(BigDecimal.valueOf(NumberUtils.getRealMoney(order.getPayablePrice())));
        dto.setProcessStatus(null == order.getProcessStatus() ? 0 : order.getProcessStatus().getValue());

        // 明细订单内信息
        dto.setProductName(orderProd.getProductName());
        dto.setOrderProdPrice(BigDecimal.valueOf(NumberUtils.getRealMoney(orderProd.getPrice())));
        dto.setCityName(cityName);

        // 操作日志
        dto.setTraceList(traceList);
        return dto;
    }

    @Override
    public void updateToCancel(Integer accountId, Integer orderId) {
        tradeOrderService.updateCancelOrder(accountId, orderId);
    }

    private OrderProductDTO convertTo(SoOrder soOrder,OrderProd orderProd){
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setAccountMobile(soOrder.getAccountMobile());
        orderProductDTO.setAccountName(soOrder.getAccountName());
        orderProductDTO.setAddTime(soOrder.getCreateTime());
        orderProductDTO.setAuditStatusId(orderProd.getAuditStatus().getValue());
        orderProductDTO.setAuditStatusName(orderProd.getAuditStatus().getText());
        orderProductDTO.setCityName(dictService.byId(orderProd.getCityId()).getName());
        orderProductDTO.setIsComplaint(BooleanUtils.toInteger(orderProd.getIsComplaint(),1,0));
        orderProductDTO.setIsPackage(BooleanUtils.toInteger(soOrder.getIsPackage(),1,0));
        orderProductDTO.setIsRefund(BooleanUtils.toInteger(orderProd.getIsRefund(),1,0));
        orderProductDTO.setIsinstallment(BooleanUtils.toInteger(soOrder.getIsInstallment(),1,0));
        orderProductDTO.setNeedDays(orderProd.getNeedDays());
        orderProductDTO.setNo(orderProd.getNo());
        orderProductDTO.setOrderId(orderProd.getOrderId());
        orderProductDTO.setOrderNo(soOrder.getNo());
        orderProductDTO.setOrderProdIdStr(SecurityUtils.rc4Encrypt(soOrder.getId()));
        orderProductDTO.setPkid(0);
        orderProductDTO.setPrice(orderProd.getPrice());
        orderProductDTO.setPriceOriginal(orderProd.getPriceOriginal());
        orderProductDTO.setProcessedDays(orderProd.getProcessedDays());
        orderProductDTO.setProductIdStr(SecurityUtils.rc4Encrypt(orderProd.getProductId()));
        orderProductDTO.setProductName(orderProd.getProductName());
        orderProductDTO.setShowProcess(0);//TODO 不显示
        orderProductDTO.setSoOrderAddtime(soOrder.getCreateTime());
        orderProductDTO.setSoOrderPaidPrice(soOrder.getPaidPrice());
        orderProductDTO.setSoOrderPayStatusId(soOrder.getPayStatus().getValue());
        orderProductDTO.setSoOrderPrice(soOrder.getTotalPrice());
        orderProductDTO.setSoOrderPriceOriginal(soOrder.getPayablePrice());
        orderProductDTO.setTimeoutDays(orderProd.getTimeoutDays());
        orderProductDTO.setType(soOrder.getType().getValue());
        if (orderProductDTO.getSoOrderPaidPrice().compareTo(orderProductDTO.getSoOrderPriceOriginal()) == 0 || orderProd.getProcessStatusId() > 0) {
            orderProductDTO.setShowProcess(1);
        }
        if(soOrder.getPayStatus().getValue().equals(3011)){
            if(soOrder.getPayStatus().getValue().equals(3023)){
                orderProductDTO.setSoOrderProcessStatusName("已取消");
            }else{
                orderProductDTO.setSoOrderProcessStatusName("待付款");
            }
        }else if(soOrder.getPayStatus().getValue().equals(3012)){
            if (orderProductDTO.getIsinstallment() == 1) {
                WorkflowNode workflowNode = workflowNodeService.byId(orderProd.getProcessStatusId());
                if(workflowNode==null){
                    orderProductDTO.setSoOrderProcessStatusName("待办理");
                }else{
                    orderProductDTO.setSoOrderProcessStatusName(workflowNode.getName());
                }
            } else {
                orderProductDTO.setSoOrderProcessStatusName("已付部分款");
            }
        }else {
            WorkflowNode workflowNode = workflowNodeService.byId(orderProd.getProcessStatusId());
            if (null == workflowNode) {
                orderProductDTO.setSoOrderProcessStatusName("待办理");
            } else {
                orderProductDTO.setSoOrderProcessStatusName(workflowNode.getName());
            }
        }
        orderProductDTO.setOrderIdStr(SecurityUtils.rc4Encrypt(orderProd.getOrderId()));
        orderProductDTO.setCityId(0);
        orderProductDTO.setOrderId(0);
        orderProductDTO.setSoOrderProcessStatusId(0);
        orderProductDTO.setProcessStatusId(0);
        orderProductDTO.setOrderProdIdStr(SecurityUtils.rc4Encrypt(orderProd.getId()));
        orderProductDTO.setPkid(0);
        orderProductDTO.setAuditStatusId(0);
        orderProductDTO.setIsAssign(0);
        return orderProductDTO;
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

    private Result doCoupon(SoOrder order, OrderAddDTO orderAddDTO) {
        Result<SoOrder> result = new Result<SoOrder>();
        // 订单原价
        int payablePrice = order.getPayablePrice();

        // 优惠总额
        Integer couponTotalPrice = 0;

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
                int couponPrice = couponService.couponPrice(payablePrice, coupon);
                payablePrice = payablePrice - couponPrice;

                couponTotalPrice = couponTotalPrice + couponPrice;
                // 设置订单关联优惠券
                OrderDiscount orderDiscount = new OrderDiscount();
                {
                    orderDiscount.toNew();
                    orderDiscount.setAmount(NumberUtils.doubleRoundInt(couponPrice));
                    orderDiscount.setPreferentialId(coupon.getId());
                    orderDiscount.setNo(no);
                    orderDiscount.setSqlid("");
                    orderDiscount.setRemark(orderAddDTO.getOrderPlatformSourceType().getText() + "下单使用");
                }
                orderDiscountList.add(orderDiscount);
            }

            // 特么的不会优惠到负值吧！
            if (payablePrice < 0) {
                payablePrice = 0;
            }

            // 计算OrderProd价格
            List<OrderProd> products = order.getProducts();

            // 计算逻辑: 订单优惠价格/订单应付价格 = 明细订单优惠价格/明细订单价格
            int couponPriceUse = 0;
            for (int i = 0; i < products.size(); i++) {
                OrderProd orderProd = products.get(i);
                if (i == products.size() - 1) {
                    int lastCouponPrice = couponTotalPrice - couponPriceUse;
                    lastCouponPrice = lastCouponPrice < 0 ? 0 : lastCouponPrice;
                    orderProd.setPrice(orderProd.getPrice() - (lastCouponPrice));
                } else {
                    // 明细订单优惠价格
                    int orderProdCouponPrice = NumberUtils.doubleRoundInt(AmountUtils.div(AmountUtils.mul(couponTotalPrice, orderProd.getPrice()), order.getPayablePrice(), 2));
                    // 设置明细订单真实价格
                    orderProd.setPrice(orderProd.getPrice() - orderProdCouponPrice);
                    couponPriceUse = couponPriceUse + orderProdCouponPrice;
                }
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
        String customerName = "";
        Customer customer = customerService.byAccountId(account.getId());
        if (null != customer) {
            customerId = customer.getId();
            customerName = customer.getRealName();
        }

        // 产品id
        List<Integer> productIds = new ArrayList<>();
        // 字段id
        List<Integer> dictIds = new ArrayList<>();
        // 定价id
        List<Integer> priceIds = new ArrayList<>();
        for (OrderProdAddDTO OrderProdAddDTO : prodDtoList) {
            List<ProductPriceDTO> priceList = OrderProdAddDTO.getPriceList();
            if (CollectionUtils.isEmpty(priceList)) {
                return new Result<>(ResponseStatus.FAILED, "定价不能为空");
            }

            productIds.add(OrderProdAddDTO.getProductId());
            dictIds.add(OrderProdAddDTO.getCityId());

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
        for (OrderProdAddDTO OrderProdAddDTO : prodDtoList) {
            Integer productId = OrderProdAddDTO.getProductId();
            Product product = productMap.get(productId);
            if (null == product) {
                return new Result<>(ResponseStatus.FAILED, "商品不能为空");
            }
            Dict city = dictMap.get(OrderProdAddDTO.getCityId());
            if (null == city) {
                return new Result<>(ResponseStatus.FAILED, "请选择城市");
            }
            int quantity = 0;
            if (StringUtils.isNotBlank(orderAddDTO.getCategoryIds())) {
                quantity = StringUtils.idsToList(orderAddDTO.getCategoryIds()).size();
            } else {
                quantity = OrderProdAddDTO.getQuantity();
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
                for (ProductPriceDTO productPriceDTO : OrderProdAddDTO.getPriceList()) {
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
                    orderProd.setCityId(OrderProdAddDTO.getCityId());
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
            order.setCustomerId(customerId);
            order.setCustomerName(customerName);
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
