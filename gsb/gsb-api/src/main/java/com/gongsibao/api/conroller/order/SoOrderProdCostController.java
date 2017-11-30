package com.gongsibao.api.conroller.order;


import javax.ws.rs.Path;


@Path("/api/soorderprodcost")
public class SoOrderProdCostController {
//
//    private Log log = LogFactory.getLog(SoOrderProdCostController.class);
//
//    @Autowired
//    private SoOrderProdCostService soOrderProdCostService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private UcUserBusinessService ucUserBusinessService;
//
//    @Autowired
//    private BdDictService bdDictService;
//
//    @RequestMapping("/list")
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        Map<Object, Object> extendMap = new HashMap<>();
//        extendMap.put("isCBGL", loginUser.getRoleTags().contains(RoleTag.ROLE_CBGL));
//
//        List<UcUserBusiness> businessList = ucUserBusinessService.findByUserId(loginUser.getUcUser().getPkid());
//        if (CollectionUtils.isEmpty(businessList)) {
//            return data;
//        }
//        List<Integer> businessIds = businessList.stream().map(UcUserBusiness::getBusinessId).collect(Collectors.toList());
////        if (businessIds.contains(1081)) {
////            businessIds = null;
////        } else {
////            businessIds.add(1081);
////        }
//
//        int currentPage = NumberUtils.toInt(request.getParameter("currentPage"));
//        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"));
//
//        String orderNo = StringUtils.trimToEmpty(request.getParameter("orderNo"));
//        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
//        String productName = StringUtils.trimToEmpty(request.getParameter("productName"));
//        String receiver = StringUtils.trimToEmpty(request.getParameter("receiver"));
//        String beginTime = request.getParameter("beginTime");
//        String endTime = request.getParameter("endTime");
//        int costStatus = NumberUtils.toInt(request.getParameter("costStatus"), -1);
//
//        Map<String, Object> condition = new HashMap<>();
//        if (CollectionUtils.isNotEmpty(businessIds)) {
//            condition.put("businessIds", businessIds);
//        }
//
//        if (StringUtils.isNotBlank(orderNo)) {
//            condition.put("orderNo", orderNo);
//        }
//        if (StringUtils.isNotBlank(productName)) {
//            condition.put("productName", productName);
//        }
//        if (StringUtils.isNotBlank(companyName)) {
//            condition.put("companyName", companyName);
//        }
//        if (StringUtils.isNotBlank(receiver)) {
//            condition.put("receiver", receiver);
//        }
//        if (!StringUtils.isBlank(beginTime)) {
//            condition.put("beginTime", beginTime + " 00:00:00");
//        }
//        if (!StringUtils.isBlank(endTime)) {
//            condition.put("endTime", endTime + " 23:59:59");
//        }
//
//        if (costStatus > -1) {
//            condition.put("costStatus", costStatus);
//        }
//
//        Pager<CostRow> pager = soOrderProdCostService.findByCondition(condition, currentPage, pageSize);
//        data.setData(pager);
//        pager.setExtend(extendMap);
//        return data;
//    }
//
//
//    @RequestMapping(value = "/addCost")
//    public ResponseData add(LoginUser loginUser, @RequestBody String req) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_CBGL)) {
//            data.setMsg("您没有权限");
//            return data;
//        }
//        SoOrderProdCost cost = JsonUtils.jsonToObject(req, SoOrderProdCost.class);
//
//        if (cost.getReceiverId() == 0) {
//            data.setMsg("收款方必须选择");
//            return data;
//        }
//        if (cost.getOrderProdId() == 0) {
//            data.setMsg("产品订单ID为空");
//            return data;
//        }
//        if (cost.getOrganizationId() == 0) {
//            data.setMsg("成本部门必须选择");
//            return data;
//        }
//        cost.setAddUserId(loginUser.getUcUser().getPkid());
//
//        Integer rs = soOrderProdCostService.addCost(cost);
//
//        if (rs > 0) {
//            log.info("## user[" + loginUser.getUcUser().getPkid() + "] addCost[" + cost.getOrderProdId() + "][" + rs + "] ");
//            data.setCode(200);
//        } else if (rs == -1) {
//            data.setMsg("明细订单不存在");
//        } else if (rs == -2) {
//            data.setMsg("该订单成本录入已完成");
//        }
//        return data;
//    }
//
//    @RequestMapping("/complete")
//    public ResponseData complete(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//        if (!loginUser.getRoleTags().contains(RoleTag.ROLE_CBGL)) {
//            data.setMsg("您没有权限");
//            return data;
//        }
//        int orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderProdIdStr")));
//        int rows = soOrderProdService.updateCostStatus(orderProdId, 2);
//        if (rows > 0) {
//            data.setCode(200);
//            log.info("## user[" + loginUser.getUcUser().getPkid() + "] complete[" + orderProdId + "] ");
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/save")
//    public ResponseData saveOrderProdCost(HttpServletRequest request, @RequestBody Map<String, Object> param) {
//        ResponseData data = new ResponseData();
//        data.setCode(-1);
//
//        String receiverIdStr = StringUtils.trimToEmpty(request.getParameter("receiverIdStr"));
//        int cost = NumberUtils.toInt(request.getParameter("cost"));
//        String orderProdIdStr = StringUtils.trimToEmpty(request.getParameter("orderProdIdStr"));
//        String organizationIdStr = StringUtils.trimToEmpty(request.getParameter("organizationIdStr"));
//        String remark = StringUtils.trimToEmpty(request.getParameter("remark"));
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("pkIdStr"));
//
//        if (StringUtils.isBlank(receiverIdStr)) {
//            data.setMsg("收款方必须选择");
//            return data;
//        }
//        if (StringUtils.isBlank(orderProdIdStr)) {
//            data.setMsg("产品订单ID为空");
//            return data;
//        }
//        if (StringUtils.isBlank(organizationIdStr)) {
//            data.setMsg("成本部门必须选择");
//            return data;
//        }
//
//        int receiverId = 0, orderProdId = 0, organizationId = 0, pkId = 0;
//
//        try {
//            receiverId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(receiverIdStr));
//            orderProdId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderProdIdStr));
//            organizationId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(organizationIdStr));
//
//        } catch (Exception e) {
//            data.setMsg(e.getMessage());
//            return data;
//        }
//
//        if (receiverId == 0) {
//            data.setMsg("收款方必须选择");
//            return data;
//        }
//        if (orderProdId == 0) {
//            data.setMsg("产品订单ID为空");
//            return data;
//        }
//        if (organizationId == 0) {
//            data.setMsg("成本部门必须选择");
//            return data;
//        }
//
//        SoOrderProdCost prodCost = new SoOrderProdCost();
//        prodCost.setPkid(pkId);
//        prodCost.setReceiverId(receiverId);
//        prodCost.setOrderProdId(orderProdId);
//        prodCost.setCost(cost);
//        prodCost.setOrganizationId(organizationId);
//        prodCost.setRemark(remark);
//        prodCost.setAddTime(new Date());
//
//        soOrderProdCostService.insert(prodCost);
//        data.setCode(200);
//
//        return data;
//    }
}