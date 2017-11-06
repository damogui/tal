package com.gongsibao.api.conroller.order.manager;

import javax.ws.rs.Path;

/**
 * 退单申请模块
 */

@Path("/api/refundApply")
public class RefundApplyController {
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private SoRefundService soRefundService;
//
//    @RequestMapping("/check")
//    public ResponseData check(HttpServletRequest request, HttpServletResponse response) {
//        return soOrderProdService.checkRefund(NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr")))));
//    }
//
//    @RequestMapping("/getAbleProdList")
//    public ResponseData getProdList(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        int pkid = NumberUtils.toInt(SecurityUtils.rc4Decrypt(StringUtils.trimToEmpty(request.getParameter("pkidStr"))));
//        List<SoOrderProd> orderProdList = soOrderProdService.getApplyRefundByOrderId(pkid);
//        if (null == orderProdList) {
//            data.setCode(-1);
//            data.setMsg("该订单无产品可退");
//        } else {
//            soOrderProdService.setApplyRefundProdItem(orderProdList);
//            data.setData(orderProdList);
//        }
//        return data;
//    }
//
//    @RequestMapping("/addRefund")
//    public ResponseData addRefund(HttpServletRequest request, HttpServletResponse response, LoginUser user, @RequestBody Map<String, Object> map) {
//        ResponseData data = new ResponseData();
//
//        if (MapUtils.isEmpty(map)) {
//            data.setCode(-1);
//            data.setMsg("参数为空");
//            return data;
//        }
//
//        int orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(map.get("pkidStr")));
//        Integer wayTypeId = NumberUtils.toInt(map.get("wayTypeId"));
//        Integer isFullRefund = NumberUtils.toInt(map.get("isFullRefund"));
//        String payerName = StringUtils.trimToEmpty(map.get("payerName"));
//        String bankNo = StringUtils.trimToEmpty(map.get("bankNo"));
//        int refundAmount = NumberUtils.doubleRoundInt(StringUtils.trimToEmpty(map.get("amount")));
//        int refundCost = NumberUtils.doubleRoundInt(StringUtils.trimToEmpty(map.get("cost")));
//        String remark = StringUtils.trimToEmpty(map.get("remark"));
//
//        List<RefundProdReq> list = JsonUtils.jsonToList(JsonUtils.objectToJson(map.get("list")), RefundProdReq.class);
//
//        if (CollectionUtils.isEmpty(list)) {
//            data.setCode(-1);
//            data.setMsg("请选择退款产品");
//            return data;
//        }
//
//        /** soRefund对象 */
//        SoRefund soRefund = new SoRefund();
//        soRefund.setWayTypeId(wayTypeId);
//        soRefund.setIsFullRefund(isFullRefund);
//        soRefund.setPayerName(payerName);
//        soRefund.setBankNo(bankNo);
//        soRefund.setAmount(refundAmount);
//        soRefund.setCost(refundCost);
//        soRefund.setRemark(remark);
//        soRefund.setOrderId(orderId);
//        soRefund.setAddUserId(user.getUcUser().getPkid());
//        soRefund.setAuditStatusId(1051);
//
//        if (CollectionUtils.isNotEmpty(list)) {
//            Iterator<RefundProdReq> prodReqIterator = list.iterator();
//            while (prodReqIterator.hasNext()) {
//                RefundProdReq refundProdReq = prodReqIterator.next();
//                int orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(refundProdReq.getOrderProdIdStr()));
//                int orderProdItemId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(refundProdReq.getOrderProdItemIdStr()));
//
//                refundProdReq.setOrderProdId(orderProdId);
//                refundProdReq.setOrderProdItemId(orderProdItemId);
//
//                if (refundProdReq.getAmount() <= 0) {
//                    prodReqIterator.remove();
//                }
//            }
//        }
//
//        if (CollectionUtils.isEmpty(list)) {
//            data.setCode(-1);
//            data.setMsg("请填写退款金额");
//            return data;
//        }
//
//        try {
//            data = soRefundService.insertRefundApply(soRefund, list);
//        } catch (AuditException e) {
//            data = new ResponseData();
//            data.setCode(-1);
//            data.setMsg(e.getMessage());
//        }
//
//        return data;
//    }

}

