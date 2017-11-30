package com.gongsibao.api.conroller.yj;


import javax.ws.rs.Path;

@Path("/yjrequestlog")
public class YjRequestLogController {
//
//    private static Logger log = Logger.getLogger(YjRequestLogController.class);
//    @Autowired
//    private YjRequestLogService yjRequestLogService;
//    @Autowired
//    private CrmCompanyIntentionService crmCompanyIntentionService;
//    @Autowired
//    private YjCompanyService yjCompanyService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute YjRequestLog yjRequestLog) {
//        ResponseData data = new ResponseData();
//        /* yjRequestLogService.insert(yjRequestLog);*/
//        /*List<String> companyNameList = crmCompanyIntentionService.getAllCompanyNameList();
//        *//*List<String> companyNameList = new ArrayList<>();
//        companyNameList.add("北京饰橱网络科技有限公司");*//*
//        for (String companyName : companyNameList) {
//            yjRequestLogService.insertYjCompanyByCriName(StringUtils.trimToEmpty(companyName));
//        }*/
//        //yjRequestLogService.updateSizeName();
//
//        //yjRequestLogService.updateErrorRegistCapiNum();
//
//
//        //List<String> companyNameListBySb = yjCompanyService.getAllCompanyNameListByTabType(1);
//        /*List<String> companyNameListBySb = new ArrayList<>();
//        companyNameListBySb.add("小");
//        for (String companyName : companyNameListBySb) {
//            //插入商标
//            try {
//                YjResult yjResult = yjRequestLogService.insertByQueryYj(5, companyName, 3,false,0,0);
//            } catch (Exception e) {
//                log.info("---------------- YjTrademark script 商标： " + companyName);
//                log.error(e);
//                e.printStackTrace();
//                e.printStackTrace();
//            }
//        }*/
//
//        //查出还没有查询出的著作权公司名称
//        /*List<String> companyNameListByZzq = yjCompanyService.getAllCompanyNameListByTabType(2);
//        for (String companyName : companyNameListByZzq) {
//            //插入软件著作权
//            yjRequestLogService.insertByQueryYj(7, companyName);
//            //插入作品著作权
//            yjRequestLogService.insertByQueryYj(10, companyName);
//        }*/
//
//        /*//专利
//        List<String> companyNameListByZl = yjCompanyService.getAllCompanyNameListByTabType(3);
//        for (String companyName : companyNameListByZl) {
//            //插入著作权
//            yjRequestLogService.insertByQueryYj(8, companyName);
//        }*/
//
//        /*//专利
//        yjRequestLogService.insertJhDataToBd(8, 4);
//        //商标
//        yjRequestLogService.insertJhDataToBd(5, 3);*/
//        /*int yjCompanyId = 0;
//
//        List<String> nameList = new ArrayList<>();
//        nameList.add("corpid");
//        nameList.add("钉钉线下部署沟通组");
//        nameList.add("北京醉纯科技有限公司");
//        nameList.add("钉钉开放平台");
//        nameList.add("汉唐信通公司宝");
//        nameList.add("沃尔奔达新能源");
//        nameList.add("睿码科技");
//        nameList.add("海居派");
//        nameList.add("晟之铭集团");
//        nameList.add("极骑（上海）信息技术有限公司");
//        nameList.add("东欣总汇");
//        nameList.add("埃森客文化客户体验馆");
//        nameList.add("Test企业");
//        nameList.add("南京鸿游奥");
//        nameList.add("江苏鸿游奥");
//        nameList.add("铠熳文化");
//        nameList.add("国网山东监理项目");
//        nameList.add("枣庄力源项目");
//        nameList.add("杭州欧亚教育");
//        nameList.add("北分基础一");
//        nameList.add("快视鱼");
//        nameList.add("曹亚杰的加油军团");
//        nameList.add("360");
//        nameList.add("澳丝界");
//        nameList.add("倪雪萍");
//        nameList.add("极骑（上海）信息技术有限公司");
//        nameList.add("众创实验室");
//
//        for (String name : nameList) {
//            try {
//                yjCompanyId = yjRequestLogService.insertYjCompanyByCriName2(name, 2, true);
//                if (yjCompanyId <= 0) {
//                    yjCompanyId = addCom(name);
//                }
//            } catch (Exception e) {
//                log.info("dingtakl,调企查查接口失败插入企业信息失败：" + e.getMessage());
//            }
//            //插入商标信息
//            try {
//                //换成聚合
//                YjResult yjResult = yjRequestLogService.insertByQueryYj(12, name, 3, true, 0, 0, false);
//            } catch (Exception e) {
//                log.info("dingtakl,调企查查接口失败插入商标信息失败：" + e.getMessage());
//            }
//        }*/
//
//
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<YjRequestLog> pager = yjRequestLogService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        YjRequestLog yjRequestLog = yjRequestLogService.findById(pkid);
//        data.setData(yjRequestLog);
//        return data;
//    }
//
//    private int addCom(String corpName) {
//        List<CrmCompanyIntention> cricomList = crmCompanyIntentionService.findByCompanyName2(corpName);
//        if (CollectionUtils.isEmpty(cricomList)) {//当没有大表单是，重新生成一个大表达数据
//            CrmCompanyIntention companyIntention = new CrmCompanyIntention();
//            companyIntention.setFullName(corpName);
//            companyIntention.setCompanyName(corpName);
//            Integer comId = crmCompanyIntentionService.insert(companyIntention);
//            companyIntention.setPkid(comId);
//            cricomList = cricomList == null ? new ArrayList<>() : cricomList;
//            cricomList.add(companyIntention);
//        }
//        YjCompany yjCompany = yjCompanyService.getByName(corpName);
//        Integer yjCompanyId = 0;
//        if (yjCompany == null) {
//            yjCompany = new YjCompany();
//            yjCompany.setName(corpName);
//            yjCompany.setCompanyId(CollectionUtils.isEmpty(cricomList) ? 0 : cricomList.get(0).getPkid());
//            yjCompanyId = yjCompanyService.insert(yjCompany);
//        } else {
//            yjCompanyId = yjCompany.getPkid();
//            if (CollectionUtils.isNotEmpty(cricomList)) {
//                yjCompany.setCompanyId(cricomList.get(0).getPkid());
//                yjCompanyService.update(yjCompany);
//            }
//        }
//        return yjCompanyId;
//    }

}