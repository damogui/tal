package org.netsharp.api.controller.yj;

import javax.ws.rs.Path;

/**
 * Created by cxq on 2017/2/13.
 */

@Path("/ei")
public class EnterpriseInfoController {
//
//    @Autowired
//    private YjRequestLogService yjRequestLogService;
//
//    @Autowired
//    private CrmCompanyIntentionService crmCompanyIntentionService;
//
//    @Autowired
//    private BdDictService bdDictService;
//
//    /*记录日志*/
//    private static Logger log = Logger.getLogger(EnterpriseInfoController.class);
//
//    /**
//     * 查询企业工商信息
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("/getdetailbyid")
//    public ResponseData getDetailById(int id) {
//        ResponseData data = new ResponseData();
//
//        CrmCompanyIntention crmCompanyIntention = crmCompanyIntentionService.findById(id);
//        if (crmCompanyIntention == null) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//
//        String entName = StringUtils.trimToEmpty(crmCompanyIntention.getCompanyName());
//        if (StringUtils.isBlank(entName)) {
//            data.setMsg("企业名称不能为空！");
//            return data;
//        }
//        //当名称中有空格，则直接返回查无信息
//        if (entName.contains(" ")) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//
//        //是否包含空格，如果包含则直接返回【无此企业信息】,因为企查查接口，不支持有空格的参数
//        if (RegexUtils.containSpace(entName)) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//
//        YjResult yr = yjRequestLogService.insertByQueryYj(1, entName,2,true,0,0,false);
//        LinkedHashMap result = (LinkedHashMap) yr.getResult();
//
//        if (result == null) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//
//        //region 加密
//        //注册号
//        result.put("No", getHalfStr(result, "No"));
//        //统一社会信用代码
//        result.put("CreditCode", getHalfStr(result, "CreditCode"));
//        //法定代表人
//        result.put("OperName", getNameStr(result, "OperName"));
//        //住所
//        result.put("Address", getHalfStr(result, "Address"));
//        //公司名称
//        result.put("Name", getCompanyNameStr(result, "Name"));
//        //股东
//        ArrayList partnerList = (ArrayList) result.get("Partners");
//        if (CollectionUtils.isNotEmpty(partnerList)) {
//            for (Object partner : partnerList) {
//                LinkedHashMap partnerMap = (LinkedHashMap) partner;
//                //股东/发起人
//                partnerMap.put("StockName", getNameStr(partnerMap, "StockName"));
//            }
//        }
//        //Branches
//        ArrayList BranchesList = (ArrayList) result.get("Branches");
//        if (CollectionUtils.isNotEmpty(BranchesList)) {
//            for (Object Branches : BranchesList) {
//                LinkedHashMap BranchesMap = (LinkedHashMap) Branches;
//                //公司名
//                BranchesMap.put("Name", getCompanyNameStr(BranchesMap, "Name"));
//                //公司注册号码
//                BranchesMap.put("RegNo", getHalfStr(BranchesMap, "RegNo"));
//            }
//        }
//        //员工
//        ArrayList employeesList = (ArrayList) result.get("Employees");
//        if (CollectionUtils.isNotEmpty(employeesList)) {
//            for (Object employee : employeesList) {
//                LinkedHashMap employeeMap = (LinkedHashMap) employee;
//                //股东/发起人
//                employeeMap.put("Name", getNameStr(employeeMap, "Name"));
//            }
//        }
//        //变更记录
//        ArrayList changeRecordsList = (ArrayList) result.get("ChangeRecords");
//        if (CollectionUtils.isNotEmpty(changeRecordsList)) {
//            for (Object changeRecord : changeRecordsList) {
//                LinkedHashMap changeRecordMap = (LinkedHashMap) changeRecord;
//                //变更前内容
//                changeRecordMap.put("BeforeContent", getNameStr(changeRecordMap, "BeforeContent"));
//                //String beforeContent = StringUtils.trimToEmpty(changeRecordMap.get("BeforeContent"));
//                /*String[] beforeContentList = StringUtils.split(beforeContent, ";");
//                for (String beforeContentItem : beforeContentList) {
//                    if (beforeContentItem.contains(",")) {
//                        String name = StringUtils.trimToEmpty(StringUtils.split(beforeContentItem, ",")[0]);
//                        changeRecordMap.put("BeforeContent", StringUtils.trimToEmpty(changeRecordMap.get("BeforeContent")).replace(name, getNameStrByStr(name)));
//                    }
//                }*/
//
//
//                //变更前内容
//                changeRecordMap.put("AfterContent", getNameStr(changeRecordMap, "AfterContent"));
//                /*String afterContent = StringUtils.trimToEmpty(changeRecordMap.get("AfterContent"));
//                String[] afterContentList = StringUtils.split(afterContent, ";");
//                for (String afterContentItem : afterContentList) {
//                    if (afterContentItem.contains(",")) {
//                        String name = StringUtils.trimToEmpty(StringUtils.split(afterContentItem, ",")[0]);
//                        changeRecordMap.put("AfterContent", StringUtils.trimToEmpty(changeRecordMap.get("AfterContent")).replace(name, getNameStrByStr(name)));
//                    }
//                }*/
//            }
//        }
//        //联系方式
//        LinkedHashMap employeeMap = (LinkedHashMap) result.get("ContactInfo");
//        if (employeeMap != null) {
//            employeeMap.put("PhoneNumber", getHalfStr(employeeMap, "PhoneNumber"));
//            employeeMap.put("Email", getEmailStr(employeeMap, "Email"));
//        }
//        // endregion
//
//        data.setData(getDataJsonStr(yr, result));
//        return data;
//    }
//
//    /**
//     * 企业年报查询
//     *
//     * @param keyno
//     * @return
//     */
//    @RequestMapping("/getannualreport")
//    public ResponseData getAnnualReport(String keyno) {
//        ResponseData data = new ResponseData();
//        String kn = StringUtils.trimToEmpty(Encodes.urlDecode(keyno));
//        if (StringUtils.isBlank(kn)) {
//            data.setMsg("KeyNo不能为空！");
//            return data;
//        }
//
//        YjResult yr = yjRequestLogService.insertByQueryYj(2, kn,2,true,0,0,false);
//
//
//        //region 加密
//        ArrayList reportList = (ArrayList) yr.getResult();
//        if (reportList == null) {
//            data.setMsg("无企业年报信息！");
//            return data;
//        }
//        for (Object report : reportList) {
//            LinkedHashMap reportMap = (LinkedHashMap) report;
//            LinkedHashMap basicInfoDataMap = (LinkedHashMap) reportMap.get("BasicInfoData");
//            //企业名称
//            basicInfoDataMap.put("CompanyName", getCompanyNameStr(basicInfoDataMap, "CompanyName"));
//            //经营者姓名
//            basicInfoDataMap.put("OperatorName", getNameStr(basicInfoDataMap, "OperatorName"));
//            //注册号
//            basicInfoDataMap.put("RegNo", getHalfStr(basicInfoDataMap, "RegNo"));
//            //企业通信地址
//            basicInfoDataMap.put("Address", getHalfStr(basicInfoDataMap, "Address"));
//            //企业联系电话
//            basicInfoDataMap.put("ContactNo", getContactNoStr(basicInfoDataMap, "ContactNo"));
//            //电子邮箱
//            basicInfoDataMap.put("EmailAddress", getEmailStr(basicInfoDataMap, "EmailAddress"));
//
//            //投资设立企业
//            ArrayList partnerList = (ArrayList) reportMap.get("PartnerList");
//            if (CollectionUtils.isNotEmpty(partnerList)) {
//                for (Object partner : partnerList) {
//                    LinkedHashMap partnerMap = (LinkedHashMap) partner;
//                    //股东/发起人
//                    partnerMap.put("Name", getNameStr(partnerMap, "Name"));
//                }
//            }
//
//            //股东
//            ArrayList investInfoList = (ArrayList) reportMap.get("InvestInfoList");
//            if (CollectionUtils.isNotEmpty(investInfoList)) {
//                for (Object invest : investInfoList) {
//                    LinkedHashMap investMap = (LinkedHashMap) invest;
//                    //投资设立企业或购买股权企业名称
//                    investMap.put("Name", getCompanyNameStr(investMap, "Name"));
//                    //股东/发起人
//                    investMap.put("RegNo", getHalfStr(investMap, "RegNo"));
//                }
//            }
//
//            ArrayList stockChangeList = (ArrayList) reportMap.get("StockChangeList");
//            if (CollectionUtils.isNotEmpty(stockChangeList)) {
//                for (Object stockChange : stockChangeList) {
//                    LinkedHashMap stockChangeMap = (LinkedHashMap) stockChange;
//                    //股东/发起人
//                    stockChangeMap.put("Name", getNameStr(stockChangeMap, "Name"));
//                }
//            }
//
//            //分支机构
//            ArrayList branchList = (ArrayList) reportMap.get("BranchList");
//            if (CollectionUtils.isNotEmpty(branchList)) {
//                for (Object branch : branchList) {
//                    LinkedHashMap branchMap = (LinkedHashMap) branch;
//                    //分支机构名称
//                    branchMap.put("Name", getCompanyNameStr(branchMap, "Name"));
//                    //注册号
//                    branchMap.put("RegNo", getHalfStr(branchMap, "RegNo"));
//                    //住所
//                    branchMap.put("Address", getHalfStr(branchMap, "Address"));
//                    //负责人
//                    branchMap.put("Principal", getNameStr(branchMap, "Principal"));
//                }
//            }
//
//            //员工
//            ArrayList employeeList = (ArrayList) reportMap.get("EmployeeList");
//            if (CollectionUtils.isNotEmpty(employeeList)) {
//                for (Object employee : employeeList) {
//                    LinkedHashMap employeeMap = (LinkedHashMap) employee;
//                    //股东/发起人
//                    employeeMap.put("Name", getNameStr(employeeMap, "Name"));
//                    //证照/证件号码
//                    employeeMap.put("CerNo", getHalfStr(employeeMap, "CerNo"));
//                }
//            }
//
//        }
//        // endregion
//
//        data.setData(getDataJsonStr(yr, reportList));
//
//        return data;
//    }
//
//    /**
//     * 获取企业经营异常信息
//     *
//     * @param keyno
//     * @return
//     */
//    @RequestMapping("/getopexception")
//    public ResponseData GetOpException(String keyno) {
//        ResponseData data = new ResponseData();
//        String kn = StringUtils.trimToEmpty(Encodes.urlDecode(keyno));
//        if (StringUtils.isBlank(kn)) {
//            data.setMsg("KeyNo不能为空！");
//            return data;
//        }
//
//        YjResult yr = yjRequestLogService.insertByQueryYj(3, kn,2,true,0,0,false);
//        data.setData(yr.getRawString());
//
//        return data;
//    }
//
//    /**
//     * 企业对外投资信息
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("/searchinvestmentbyid")
//    public ResponseData searchInvestment(int id) {
//        ResponseData data = new ResponseData();
//        //String entName = StringUtils.trimToEmpty(Encodes.urlDecode(name));
//        CrmCompanyIntention crmCompanyIntention = crmCompanyIntentionService.findById(id);
//        if (crmCompanyIntention == null) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//
//        String entName = StringUtils.trimToEmpty(crmCompanyIntention.getCompanyName());
//        if (StringUtils.isBlank(entName)) {
//            data.setMsg("企业名称不能为空！");
//            return data;
//        }
//
//
//        int LocalCount = crmCompanyIntentionService.countByName(entName);
//        if (LocalCount < 1) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//
//        YjResult yr = yjRequestLogService.insertByQueryYj(4, entName,2,true,0,0,false);
//
//        //region 加密
//        ArrayList investmentList = (ArrayList) yr.getResult();
//        if (investmentList == null) {
//            data.setMsg("无此企业信息！");
//            return data;
//        }
//        for (Object report : investmentList) {
//            LinkedHashMap investmentMap = (LinkedHashMap) report;
//            //公司名称
//            investmentMap.put("Name", getCompanyNameStr(investmentMap, "Name"));
//            //注册号
//            investmentMap.put("No", getNameStr(investmentMap, "No"));
//            //法定代表人
//            investmentMap.put("OperName", getNameStr(investmentMap, "OperName"));
//            //信用代码
//            investmentMap.put("CreditCode", getHalfStr(investmentMap, "CreditCode"));
//            //公司地址
//            investmentMap.put("Address", getHalfStr(investmentMap, "Address"));
//        }
//        // endregion
//
//        data.setData(getDataJsonStr(yr, investmentList));
//
//        return data;
//
//    }
//
//
//    /*@RequestMapping("/addlogdata")
//    public ResponseData addLogData(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        try {
//            yjRequestLogService.addLogData();
//            data.setMsg("操作成功");
//        } catch (Exception e) {
//            log.info("-------yjRequestLog:"+e.getMessage()+"---------");
//            e.printStackTrace();
//        }
//        return data;
//    }*/
//
//
//    //region 私有方法
//
//    /*获取指定数量的星号*/
//    private String getXinhao(int count) {
//        StringBuffer res = new StringBuffer("");
//        //当大于三十个时，则按30个处理
//        count = count >= 30 ? 30 : count;
//        for (int i = 0; i < count; i++) {
//            res.append("*");
//        }
//        return res.toString();
//    }
//
//
//    /*获取一半文字一半星号*/
//    private String getHalfStr(LinkedHashMap result, String key) {
//        return StringUtils.isBlank(StringUtils.trimToEmpty(result.get(key))) ? "" : StringUtils.substring(result.get(key).toString(), 0, (result.get(key).toString().length() / 2)) + getXinhao(result.get(key).toString().length() - (result.get(key).toString().length() / 2));
//    }
//
//    private String getHalfStrByStr(String val) {
//        return StringUtils.isBlank(val) ? "" : StringUtils.substring(val, 0, (val.length() / 2)) + getXinhao(val.length() - (val.length() / 2));
//    }
//
//
//    /*获取一半文字一半星号*/
//    private String getContactNoStr(LinkedHashMap result, String key) {
//        String val = StringUtils.trimToEmpty(result.get(key));
//        //座机
//        if (val.contains("-")) {
//            String[] valArr = StringUtils.split(val, "-");
//            return valArr[0] + (valArr.length <= 1 ? "" : "-" + getHalfStrByStr(valArr[1]));
//        }
//        return getHalfStr(result, key);
//    }
//
//
//    /*获取人名的打码字符串*/
//    private String getNameStr(LinkedHashMap result, String key) {
//        return StringUtils.isBlank(StringUtils.trimToEmpty(result.get(key))) ? "" : StringUtils.substring(result.get(key).toString(), 0, 1) + getXinhao(result.get(key).toString().length() - 1);
//    }
//
//    /*获取人名的打码字符串*/
//    private String getNameStrByStr(String name) {
//        return StringUtils.isBlank(name) ? "" : StringUtils.substring(name, 0, 1) + getXinhao(name.length() - 1);
//    }
//
//    /*获取邮箱的打码字符串*/
//    private String getEmailStr(LinkedHashMap map, String key) {
//        return StringUtils.isBlank(StringUtils.trimToEmpty(map.get(key))) ? "" : getXinhao(map.get(key).toString().length() - ((StringUtils.split(map.get(key).toString(), "@"))[0]).length()) + "@" + (StringUtils.split(map.get(key).toString(), "@"))[1];
//    }
//
//    private String getCompanyNameStr(LinkedHashMap map, String key) {
//        String val = StringUtils.trimToEmpty(map.get(key));
//        if (val.contains("集团")) {
//            int count = val.indexOf("集团");
//            return getXinhao(count) + StringUtils.substring(val, count);
//        }
//
//        if (val.contains("("))
//            return getFrountStrByStr(val, "(");
//        if (val.contains("（"))
//            return getFrountStrByStr(val, "（");
//
//        //地域+公司名+行业特征+有限公司   北京汉唐科技有限公司
//        List<BdDict> cityList = bdDictService.findByType(BdDict.TYPE_101);
//        int citycount = -1;
//        int btcount = -1;
//        List<String> btList = PubStatic.getBTList();
//        for (BdDict city : cityList) {
//            String cityname = StringUtils.substring(city.getName(), 0, city.getName().length() - 1);
//            if (val.contains(cityname)) {
//                citycount = val.contains(city.getName()) ? city.getName().length() : cityname.length();
//                break;
//            }
//        }
//        for (String bt : btList) {
//            btcount = val.indexOf(bt);
//            if (btcount >= 0) {
//                break;
//            }
//        }
//        String zihaoStr = StringUtils.substring(val, citycount, btcount);
//        return val.replace(zihaoStr, getXinhao(zihaoStr.length()));
//    }
//
//    private String getFrountStrByStr(String val, String Str) {
//        if (val.contains(Str)) {
//            String[] valarr = StringUtils.split(val, Str);
//            String fountStr = valarr[0];
//            List<String> btList = PubStatic.getBTList();
//            int fountStrCount = 0;
//            for (String bt : btList) {
//                fountStrCount = fountStr.indexOf(bt);
//                if (fountStrCount >= 0) {
//                    break;
//                } else {
//                    fountStrCount = val.indexOf(Str);
//                }
//            }
//            return getXinhao(fountStrCount) + StringUtils.substring(val, fountStrCount);
//        }
//        return "";
//    }
//
//    /*获取返回json字符串*/
//    private String getDataJsonStr(YjResult yr, Object result) {
//        HashMap<String, Object> resMap = new HashMap<>();
//        resMap.put("Status", yr.getStatus());
//        resMap.put("Message", yr.getMessage());
//        resMap.put("Result", result);
//        return JsonUtils.objectToJson(resMap);
//    }
//    // endregion


}

