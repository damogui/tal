package org.netsharp.api.controller.sys.stat;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;



/**
 * Created by wk on 2017/3/6.
 */
@Path("/stat/screen/order")
public class ScreenOrderController {

    private static Logger log = Logger.getLogger(ScreenOrderController.class);
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @Autowired
//    private ScreenDatavService screenDatavService;
//
//    @Autowired
//    private SoOrderProdService soOrderProdService;
//
//    @Autowired
//    private ProdProductService prodProductService;
//
//    @Autowired
//    private BdDictService bdDictService;
//
//    @Autowired
//    private ProdWorkflowNodeService prodWorkflowNodeService;
//
//    @RequestMapping("/office/num/year")
//    public Object officeNumYear(HttpServletRequest request, HttpServletResponse response) {
//        String year = DateUtils.dateStr(new Date(), "yyyy");
//        String begin = year + "-01-01 00:00:00";
//        String end = year + "-12-31 23:59:59";
//
//        return soOrderService.statOfficeNumOrder(begin, end);
//    }
//
//    @RequestMapping("/office/amount/year")
//    public Object officeAmountYear(HttpServletRequest request, HttpServletResponse response) {
//        String year = DateUtils.dateStr(new Date(), "yyyy");
//        String begin = year + "-01-01 00:00:00";
//        String end = year + "-12-31 23:59:59";
//
//        return soOrderService.statOfficeAmountOrder(begin, end);
//    }
//
//    @RequestMapping("/office/num/month")
//    public Object officeNumMonth(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//        return soOrderService.statOfficeNumOrder(begin, end);
//    }
//
//    @RequestMapping("/office/amount/month")
//    public Object officeAmountMonth(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(DateUtils.getMonthBegin(new Date()), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(DateUtils.getMonthEnd(new Date()), "yyyy-MM-dd") + " 23:59:59";
//        return soOrderService.statOfficeAmountOrder(begin, end);
//    }
//
//    @RequestMapping("/office/num/day")
//    public Object officeNumDay(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 23:59:59";
//
//        return soOrderService.statOfficeNumOrder(begin, end);
//    }
//
//    @RequestMapping("/office/amount/day")
//    public Object officeAmountDay(HttpServletRequest request, HttpServletResponse response) {
//        String begin = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 00:00:00";
//        String end = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 23:59:59";
//
//        return soOrderService.statOfficeAmountOrder(begin, end);
//    }
//
//    @RequestMapping("/office/num/stat")
//    public Object officeNumStat(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//        return soOrderService.statOfficeNumOrder(begin, end);
//    }
//
//    @RequestMapping("/office/amount/stat")
//    public Object officeAmountStat(HttpServletRequest request, HttpServletResponse response) {
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 00:00:00";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 23:59:59";
//        return soOrderService.statOfficeAmountOrder(begin, end);
//    }
//
//    /**
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/quantity/day")
//    public Object quantityDay(HttpServletRequest request, HttpServletResponse response) {
//        List<Map<String, String>> res = new ArrayList<>();
//        Map<Integer, BdDict> dictMap = bdDictService.findOneLevelMapByType(201);
////        if (MapUtils.isEmpty(dictMap)) {
////            return res;
////        }
//        Map<Integer, String> result = new HashMap<>();
//        for (Integer key : dictMap.keySet()) {
//            if (dictMap.get(key) != null) {
//                result.put(key, dictMap.get(key).getName() + "#0#0");
//            }
//        }
//
//        Date now = new Date();
//        // 查询订单ID集合
//        List<Integer> orderIds = soOrderService.getIdsByTime(DateUtils.dateStr(now) + " 00:00:00", DateUtils.dateStr(now) + " 23:59:59");
//        if (CollectionUtils.isEmpty(orderIds)) {
////            data.setCode(-1);
////            data.setMsg("订单ID为空");
////            data.setData(result.values());
//            return res(result.values());
//        }
//        Map<Integer, SoOrderProd> orderProdMap = soOrderProdService.queryMapByOrderIds(orderIds);
//        if (MapUtils.isEmpty(orderProdMap)) {
////            data.setCode(-1);
////            data.setMsg("产品订单集合为空");
////            data.setData(result.values());
//            return res(result.values());
//        }
//        Set<Integer> prodIds = new HashSet<>();
//        for (Integer key : orderProdMap.keySet()) {
//            if (orderProdMap.get(key) != null) {
//                prodIds.add(orderProdMap.get(key).getProductId());
//            }
//        }
//        Map<Integer, ProdProduct> prodMap = prodProductService.findMapByIds(new ArrayList<>(prodIds));
//        if (MapUtils.isEmpty(prodMap)) {
////            data.setCode(-1);
////            data.setMsg("产品集合为空");
////            data.setData(result.values());
//            return res(result.values());
//        }
//        //产品订单ID集合查询应付金额MAP
//        Map<Integer, Integer> payablePriceMap = soOrderProdService.findPayablePrice(orderProdMap.keySet());
//        if (MapUtils.isEmpty(payablePriceMap)) {
////            data.setCode(-1);
////            data.setMsg("产品订单应付金额集合为空");
////            data.setData(result.values());
//            return res(result.values());
//        }
//
//        Integer typeId = null;
//        Set<Integer> typeIds = new HashSet<>();
//        for (Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            if (NumberUtils.toInt(typeId) > 0) {
//                typeIds.add(typeId);
//            }
//        }
//        Map<Integer, Integer> topParentIdMap = bdDictService.findTopParentIdMap(201, typeIds);
//
//        Integer topParentId = null;
//        String value = null;
//        int quantity = 0;
//        int sales = 0;
//        for (Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            topParentId = topParentIdMap.get(typeId);
//            if (NumberUtils.toInt(topParentId) == 0) {
//                continue;
//            }
//
//            value = result.get(topParentId);
//            if (StringUtils.isBlank(value)) {
//                continue;
//            }
//            quantity = NumberUtils.toInt(value.substring(value.indexOf("#") + 1, value.lastIndexOf("#")));
//            quantity++;
//            sales = NumberUtils.toInt(value.substring(value.lastIndexOf("#") + 1, value.length()));
//            sales += NumberUtils.toInt(payablePriceMap.get(key));
//            value = value.substring(0, value.indexOf("#")) + "#" + quantity + "#" + sales;
//            result.put(topParentId, value);
//        }
//
//        return res(sort(result));
//    }
//
//    private List<Map<String, Object>> res(Collection<String> list) {
//        List<Map<String, Object>> result = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(list)) {
//            for (String s : list) {
//                if (StringUtils.isBlank(s)) {
//                    continue;
//                }
//
//                String[] arr = s.split("#");
//
//                Map<String, Object> map1 = new HashMap<>();
//                map1.put("name", arr[0]);
//                map1.put("num", arr[1]);
//                map1.put("sort", 1);
//                result.add(map1);
//
//                Map<String, Object> map2 = new HashMap<>();
//                map2.put("name", arr[0]);
//                map2.put("num", AmountUtils.div(NumberUtils.toInt(arr[2]), 100 * 10000, 2));
//                map2.put("sort", 2);
//                result.add(map2);
//            }
//        }
//        return result;
//    }
//
//    @RequestMapping("/quantity/month")
//    public Object quantityMonth(HttpServletRequest request, HttpServletResponse response) {
//
//        Map<Integer, BdDict> dictMap = bdDictService.findOneLevelMapByType(201);
//        Map<Integer, String> result = new HashMap<>();
//        for (Integer key : dictMap.keySet()) {
//            if (dictMap.get(key) != null) {
//                result.put(key, dictMap.get(key).getName() + "#0#0");
//            }
//        }
//
//        Date now = new Date();
//        // 查询订单ID集合
//        List<Integer> orderIds = soOrderService.getIdsByTime(DateUtils.dateStr(DateUtils.getMonthBegin(now)) + " 00:00:00", DateUtils.dateStr(DateUtils.getMonthEnd(now)) + " 23:59:59");
//        if (CollectionUtils.isEmpty(orderIds)) {
//            return res(result.values());
//        }
//        Map<Integer, SoOrderProd> orderProdMap = soOrderProdService.queryMapByOrderIds(orderIds);
//        if (MapUtils.isEmpty(orderProdMap)) {
//            return res(result.values());
//        }
//        Set<Integer> prodIds = new HashSet<>();
//        for (Integer key : orderProdMap.keySet()) {
//            if (orderProdMap.get(key) != null) {
//                prodIds.add(orderProdMap.get(key).getProductId());
//            }
//        }
//        Map<Integer, ProdProduct> prodMap = prodProductService.findMapByIds(new ArrayList<>(prodIds));
//        if (MapUtils.isEmpty(prodMap)) {
//            return res(result.values());
//        }
//        //产品订单ID集合查询应付金额MAP
//        Map<Integer, Integer> payablePriceMap = soOrderProdService.findPayablePrice(orderProdMap.keySet());
//        if (MapUtils.isEmpty(payablePriceMap)) {
//            return res(result.values());
//        }
//
//        Integer typeId = null;
//        Set<Integer> typeIds = new HashSet<>();
//        for (Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            if (NumberUtils.toInt(typeId) > 0) {
//                typeIds.add(typeId);
//            }
//        }
//        Map<Integer, Integer> topParentIdMap = bdDictService.findTopParentIdMap(201, typeIds);
//
//        Integer topParentId = null;
//        String value = null;
//        int quantity = 0;
//        int sales = 0;
//        for (Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            topParentId = topParentIdMap.get(typeId);
//            if (NumberUtils.toInt(topParentId) == 0) {
//                continue;
//            }
//
//            value = result.get(topParentId);
//            if (StringUtils.isBlank(value)) {
//                continue;
//            }
//            quantity = NumberUtils.toInt(value.substring(value.indexOf("#") + 1, value.lastIndexOf("#")));
//            quantity++;
//            sales = NumberUtils.toInt(value.substring(value.lastIndexOf("#") + 1, value.length()));
//            sales += NumberUtils.toInt(payablePriceMap.get(key));
//            value = value.substring(0, value.indexOf("#")) + "#" + quantity + "#" + sales;
//            result.put(topParentId, value);
//        }
//
//        return res(sort(result));
//    }
//
//    @RequestMapping("/quantity/all")
//    public Object quantityAll(HttpServletRequest request, HttpServletResponse response) {
//        Map<Integer, BdDict> dictMap = bdDictService.findOneLevelMapByType(201);
//        Map<Integer, String> result = new HashMap<>();
//        for (Integer key : dictMap.keySet()) {
//            if (dictMap.get(key) != null) {
//                result.put(key, dictMap.get(key).getName() + "#0#0");
//            }
//        }
//
//        List<Integer> nodeIds = prodWorkflowNodeService.getIdsByProcess(Arrays.asList(2062, 2064));
//        if (CollectionUtils.isEmpty(nodeIds)) {
//            return res(result.values());
//        }
//
//        Map<Integer, SoOrderProd> orderProdMap = soOrderProdService.queryMapByProcessStatusIds(nodeIds);
//        if (MapUtils.isEmpty(orderProdMap)) {
//            return res(result.values());
//        }
//        Set<Integer> prodIds = new HashSet<>();
//        for (Integer key : orderProdMap.keySet()) {
//            if (orderProdMap.get(key) != null) {
//                prodIds.add(orderProdMap.get(key).getProductId());
//            }
//        }
//        Map<Integer, ProdProduct> prodMap = prodProductService.findMapByIds(new ArrayList<>(prodIds));
//        if (MapUtils.isEmpty(prodMap)) {
//            return res(result.values());
//        }
//
//        Integer typeId = null;
//        Set<Integer> typeIds = new HashSet<>();
//        for (Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            if (NumberUtils.toInt(typeId) > 0) {
//                typeIds.add(typeId);
//            }
//        }
//        Map<Integer, Integer> topParentIdMap = bdDictService.findTopParentIdMap(201, typeIds);
//
//        Integer topParentId = null;
//        String value = null;
//        int quantity = 0;
//        for (Integer key : orderProdMap.keySet()) {
//            typeId = prodMap.get(orderProdMap.get(key).getProductId()).getTypeId();
//            topParentId = topParentIdMap.get(typeId);
//            if (NumberUtils.toInt(topParentId) == 0) {
//                continue;
//            }
//
//            value = result.get(topParentId);
//            if (StringUtils.isBlank(value)) {
//                continue;
//            }
//            quantity = NumberUtils.toInt(value.substring(value.indexOf("#") + 1, value.lastIndexOf("#")));
//            quantity++;
//            value = value.substring(0, value.indexOf("#")) + "#" + quantity + "#0";
//            result.put(topParentId, value);
//        }
//
//        return res(sort(result, false));
//    }
//
//    private List<String> sort(Map<Integer, String> map) {
//        return sort(map, true);
//    }
//    private List<String> sort(Map<Integer, String> map, boolean isSubList) {
//        List<String> result = new ArrayList<>();
//        if (MapUtils.isEmpty(map)) {
//            return result;
//        }
//
//        List<Map.Entry<Integer, String>> list =
//                new ArrayList<>(map.entrySet());
//
//        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
//            public int compare(Map.Entry<Integer, String> o1,
//                               Map.Entry<Integer, String> o2) {
//                return (NumberUtils.toInt(o2.getValue().substring(o2.getValue().indexOf("#") + 1, o2.getValue().lastIndexOf("#")))
//                        - NumberUtils.toInt(o1.getValue().substring(o1.getValue().indexOf("#") + 1, o1.getValue().lastIndexOf("#"))));
//            }
//        });
//
//        for (Map.Entry<Integer, String> o : list) {
//            result.add(o.getValue());
//            log.info("==========key==========" + o.getKey() + "==========value==========" + o.getValue());
//        }
//
//        if (isSubList && result.size() >= 4) {
//            result = result.subList(0, 4);
//        }
//        return result;
//    }
//
//    /**
//     * 财务展屏 分公司 合同金额(payable) 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/amount/stat/office")
//    public Object financeAmountStatOffice(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financeAmountStatOffice();
//    }
//
//    /**
//     * 财务展平屏 事业部 合同金额(payable) 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/amount/stat/business")
//    public Object financeAmountStatBusiness(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financeAmountStatBusiness();
//    }
//
//    /**
//     * 财务展平屏 产品大类 合同金额(payable) 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/amount/stat/product")
//    public Object financeAmountStatProduct(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financeAmountStatProduct();
//    }
//
//    /**
//     * 财务展平屏 分公司 收款金额(so_pay) 本日/昨日/上周/上月/本年   so_pay
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/pay/stat/office")
//    public Object financePayStatOffice(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financePayStatOffice();
//    }
//
//    /**
//     * 财务展平屏 事业部 收款金额(so_pay) 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/pay/stat/business")
//    public Object financePayStatBusiness(HttpServletRequest request,HttpServletResponse response){
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin")) + " 14:59:59";
//        String end = StringUtils.trimToEmpty(request.getParameter("end")) + " 18:59:59";
//        return soOrderService.financePayStatBusiness(begin,end);
//    }
//
//    /**
//     * 财务展平屏 产品大类 收款金额(so_pay) 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/pay/stat/product")
//    public Object financePayStatProduct(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financePayStatProduct();
//    }
//
//    /**
//     * 财务展平屏 分公司 成本统计 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/cost/stat/office")
//    public Object financeCostStatOffice(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financeCostStatOffice();
//    }
//
//    /**
//     * 财务展平屏 事业部 成本统计 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/cost/stat/business")
//    public Object financeCostStatBusiness(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financeCostStatBusiness();
//    }
//
//    /**
//     * 财务展平屏 产品大类 成本统计 本日/昨日/上周/上月/本年
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/cost/stat/product")
//    public Object financeCostStatProduct(HttpServletRequest request,HttpServletResponse response){
//        return soOrderService.financeCostStatProduct();
//    }
//
//    /**
//     * 按各月统计 收款(sopay.amount)和应收款(so_order.payable_price)
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/group/month/stat")
//    public Object financeGroupMonthStat(HttpServletRequest request,HttpServletResponse response){
//
//        return soOrderService.financeGroupMonthStat();
//    }
//
//    /**
//     * 本年 总收款(sopay.amount)和总应收款(so_order.payable_price)
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/group/year/stat")
//    public Object financeGroupYearStat(HttpServletRequest request,HttpServletResponse response){
//
//        return soOrderService.financeGroupYearStat();
//    }
//
//    /**
//     * 直接读ScreenDatav数据
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/stat")
//    public Object financeStat(HttpServletRequest request,HttpServletResponse response){
//        Integer pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        if(pkid == 0){
//            return null;
//        }
//
//        return screenDatavService.getObjectById(pkid);
//    }
//
//    /**
//     * 饼图
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/pie")
//    public Object financePieStat(HttpServletRequest request,HttpServletResponse response){
//        Integer pkid = NumberUtils.toInt(request.getParameter("pkid"));
//        if(pkid == 0){
//            return null;
//        }
//
//        String content = screenDatavService.getObjectById(pkid);
//        if(StringUtils.isNotBlank(content)){
//            List<Map<String,String>> json = JsonUtils.jsonToObject(content,ArrayList.class);
//            Iterator<Map<String,String>> it = json.iterator();
//
//            while (it.hasNext()){
//                String title = MapUtils.getString(it.next(),("--"));
//
//                if(title.indexOf("总计") != -1){
//                    it.remove();
//                    continue;
//                }
//
//                if(title.indexOf("其他") != -1){
//                    it.remove();
//                    continue;
//                }
//            }
//            return json;
//        }
//        return content;
//    }
//
//    /**
//     * 按段时间查询
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/finance/data/stat/office")
//    public Object financeDataStat(HttpServletRequest request,HttpServletResponse response){
//        String begin = StringUtils.trimToEmpty(request.getParameter("begin"));
//        String end = StringUtils.trimToEmpty(request.getParameter("end"));
//
//        if (begin.compareTo("0000-00-00")== 0 || end.compareTo("0000-00-00") == 0) {
//            begin = DateUtils.lastFirday();
//            end = DateUtils.lastFirday();
//        }
//
//        List<Map<String, String>> officeMap = soOrderService.financeDataStat(begin + " 00:00:00", end+ " 23:59:59", 1);
//        List<Map<String, String>> businessMap = soOrderService.financeDataStat(begin + " 00:00:00", end+ " 23:59:59", 2);
//        List<Map<String, String>> productMap = soOrderService.financeDataStat(begin + " 00:00:00" , end+ " 23:59:59", 3);
//
//        String res = "================== 分公司, " + JsonUtils.objectToJson(officeMap);
//
//        if (CollectionUtils.isEmpty(officeMap)) {
//            return "无数据";
//        }
//        Map<String, String> totalOffice = officeMap.get(officeMap.size()-1);
//
//        totalOffice.replace("--", "总计", "基础事业部");
//        businessMap.add(0,totalOffice);
//        Iterator it = businessMap.iterator();
//        while(it.hasNext()){
//            Map<String, String> map = (Map<String, String>)it.next();
//            if(map.get("--").equals("北分公司宝")){
//                map.replace("--", "北分公司宝", "公司宝事业部");
//            }
//
//            if(map.get("--").equals("总部知产")){
//                map.replace("--", "总部知产", "知产事业部");
//            }
//            if (map.get("--").indexOf("分公司") != -1) {
//                it.remove();
//            }
//        }
//
//        res = res + " ,==============事业部, " + JsonUtils.objectToJson(businessMap) + " ,==================产品大类, " + JsonUtils.objectToJson(productMap);
//        res = res.toString().replaceAll(",", ",\r\n").replaceAll("\\{", "{\r\n").replaceAll("\\}", "\r\n}");
//
//        TxtUtils.writeToTxt(response,res,begin+"财务大屏统计");
//        String fileUrl = FileUtils.LOCAL_SAVE_PATH + ".txt";
//        FileUtils.downLoacl(request, response, fileUrl, begin+"财务大屏统计.txt");
//
//        return res;
//    }
}
