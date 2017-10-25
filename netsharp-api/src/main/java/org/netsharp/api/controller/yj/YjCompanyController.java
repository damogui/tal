package org.netsharp.api.controller.yj;


import javax.ws.rs.Path;

@Path("/yjcompany")
public class YjCompanyController {
//
//    @Autowired
//    private YjCompanyService yjCompanyService;
//
//    @Autowired
//    private SoOrderService soOrderService;
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("pkIdStr"));
//        pkIdStr = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkId = NumberUtils.toInt(pkIdStr);
//        YjCompany yjCompany = yjCompanyService.queryById(pkId);
//        data.setData(yjCompany);
//        return data;
//    }
//
//    @RequestMapping("/types")
//    public ResponseData getTypes(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        List<String> result = yjCompanyService.getTypes();
//        if (CollectionUtils.isEmpty(result)) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        data.setData(result);
//        return data;
//    }
//
//    @RequestMapping("/years")
//    public ResponseData getYears(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        List<Integer> result = yjCompanyService.getYears();
//        if (CollectionUtils.isEmpty(result)) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        data.setData(result);
//        return data;
//    }
//
//    @RequestMapping("/provinces")
//    public ResponseData getProvinces(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        data.setMsg("操作成功");
//
//        List<BdDict> result = yjCompanyService.getProvinces();
//        if (CollectionUtils.isEmpty(result)) {
//            data.setCode(-1);
//            data.setMsg("操作失败");
//        }
//        data.setData(result);
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        // 名称
//        String name = StringUtils.trimToEmpty(request.getParameter("name"));
//        // 企业状态
//        String status = StringUtils.trimToEmpty(request.getParameter("status"));
//        // 1：500万以下 2：500万~1000万 3：1000万~5000万 4：5000万以上
//        String registCapiNum = StringUtils.trimToEmpty(request.getParameter("registCapiNum"));
//        // 年份
//        String years = StringUtils.trimToEmpty(request.getParameter("years"));
//        // 省份
//        String provinces = StringUtils.trimToEmpty(request.getParameter("provinces"));
//        // 分类
//        String businessTypes = StringUtils.trimToEmpty(request.getParameter("businessTypes"));
//        // 1：5单以下 2:5~10单 3:10单以上
//        String count = StringUtils.trimToEmpty(request.getParameter("count"));
//        // 1：5000元以下 2:5000~10000元 3:10000~50000元 4:50000以上
//        String sum = StringUtils.trimToEmpty(request.getParameter("sum"));
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (StringUtils.isNotBlank(name)) {
//            condition.put("name", name);
//        }
//        if (StringUtils.isNotBlank(status)) {
//            condition.put("status", status);
//        }
//        if (NumberUtils.toInt(registCapiNum) > 0) {
//            condition.put("registCapiNum", NumberUtils.toInt(registCapiNum));
//        }
//        if (NumberUtils.toInt(count) > 0) {
//            condition.put("count", NumberUtils.toInt(count));
//        }
//        if (NumberUtils.toInt(sum) > 0) {
//            condition.put("sum", NumberUtils.toInt(sum));
//        }
//        if (StringUtils.isNotBlank(years)) {
//            condition.put("years", Arrays.asList(years.split(",")));
//        }
//        if (StringUtils.isNotBlank(provinces)) {
//            condition.put("provinces", Arrays.asList(provinces.split(",")));
//        }
//        if (StringUtils.isNotBlank(businessTypes)) {
//            condition.put("businessTypes", Arrays.asList(businessTypes.split(",")));
//        }
//
//        Pager<YjCompany> pager = yjCompanyService.queryByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping({"/investment/list"})
//    public ResponseData lstInvestment(HttpServletRequest request, HttpServletResponse response) {
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("pkIdStr"));
//        pkIdStr = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkId = NumberUtils.toInt(pkIdStr);
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (pkId > 0) {
//            condition.put("pkid", pkId);
//        }
//
//        Pager<YjCompany> pager = yjCompanyService.pageInvestmentByProperties(condition, NumberUtils.toInt(currentPage), NumberUtils.toInt(pageSize));
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/year/report")
//    public ResponseData report(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkIdStr = StringUtils.trimToEmpty(request.getParameter("pkIdStr"));
//        pkIdStr = SecurityUtils.rc4Decrypt(pkIdStr);
//        Integer pkId = NumberUtils.toInt(pkIdStr);
//        YjCompany yjCompany = yjCompanyService.queryById(pkId);
//        if(yjCompany != null) {
//            data.setData(yjCompany.getYearReportInfo());
//        }
//        return data;
//    }
//
//    @RequestMapping({"/yjorders"})
//    public ResponseData yjorders(HttpServletRequest request, HttpServletResponse response) {
//        String companyIdStr = StringUtils.trimToEmpty(request.getParameter("companyIdStr"));
//        companyIdStr = SecurityUtils.rc4Decrypt(companyIdStr);
//        Integer companyId = NumberUtils.toInt(companyIdStr);
//        // 当前页
//        String currentPage = StringUtils.trimToEmpty(request.getParameter("currentPage"));
//        // 每页显示记录数量
//        String pageSize = StringUtils.trimToEmpty(request.getParameter("pageSize"));
//
//        // 封装查询条件
//        Map<String, Object> condition = new HashMap<>();
//        if (companyId > 0) {
//            condition.put("company_id", companyId);
//        }
//
//        // 不分页
//        Pager<SoOrder> pager = soOrderService.findByYjCompany(condition, NumberUtils.toInt(currentPage), Integer.MAX_VALUE);
//        ResponseData data = new ResponseData();
//        data.setData(pager);
//        return data;
//    }

}