package com.gongsibao.taurus.api;

import com.gongsibao.taurus.entity.*;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

/**
 * Created by wk on 2018/1/30.
 * 大数据dataapi新接口测试
 */
public class DataApiTest {
    String companyName = "汉唐信通（北京）咨询股份有限公司";

    ObjectMapper mapper = new JacksonObjectMapper();

    String json = "";

    @Test
    public void test() {
        System.out.println("商标：");
//        testGetByName("四川腾中重工机械有限公司");

//        testGetCompanyByKey("腾中重工");
//        testCompanyNameByKey("中重工有限");

//        testGetCompanyTmList("四川腾中重工机械有限公司");
//        testGetTmChangeList("四川腾中重工机械有限公司");
        testGetTmRenewalList("四川腾中重工机械有限公司", "2022-12-20");
//        testTmAssemble("四川腾中重工机械有限公司");
//        testGetCompanyBusinessCount("四川腾中重工机械有限公司");
//        testGetCompanyPotentialCount("四川腾中重工机械有限公司");
//        testTmCategoryCount("四川腾中重工机械有限公司");
//        testPatentCount("四川腾中重工机械有限公司");
//        testCopyrightCount("四川腾中重工机械有限公司");
//        testYearReportCount("四川腾中重工机械有限公司");

//        testDianXin("四川腾中重工机械有限公司");
//        testYuLe("四川腾中重工机械有限公司");
//        testGaoxin("四川腾中重工机械有限公司");
//        testShuiShou("四川腾中重工机械有限公司");
//        testYingShi("四川腾中重工机械有限公司");
//        testShiPin("四川腾中重工机械有限公司");
        System.err.println("/**************************************************/");
    }

    /**
     * 获取公司详情
     */
    public void testGetByName(String companyName) {
        try {
            ResponseMessage<CompanyInfo> response = TaurusApiService.getCompanyInfo(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key模糊查询公司列表
     */
    public void testGetCompanyByKey(String key) {
        try {
            ResponseMessage<CompanyInfo> response = TaurusApiService.getCompanyListByKey(key, 1, 10);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过公司名称查询商标信息
     */
    public void testGetCompanyTmList(String key) {
        try {
            ResponseMessage<TmInfo> response = TaurusApiService.getCompanyTmList(key, 1, 1);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过公司名称查询商标信息
     */
    public void testGetTmChangeList(String key) {
        try {
            ResponseMessage<TmInfo> response = TaurusApiService.getTmChangeList(key, 1, 1);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过公司名称查询商标续展信息
     */
    public void testGetTmRenewalList(String key, String endDate) {
        try {
            ResponseMessage<TmInfo> response = TaurusApiService.getTmRenewalList(key, endDate, 1, 1);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key模糊查询公司名称
     */
    public void testCompanyNameByKey(String key) {
        try {
            ResponseMessage<CompanyNameByKey> response = TaurusApiService.getCompanyNameByKey(key, 1, 10);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 商标聚合查询
     */
    public void testTmAssemble(String companyName) {
        try {
            ResponseMessage<TmAssemble> response = TaurusApiService.getTmAssemble(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 公司商标、专利、著作权等项目已有数量查询
     */
    public void testGetCompanyBusinessCount(String companyName) {
        try {
            ResponseMessage<CompanyBusinessCount> response = TaurusApiService.getCompanyBusinessCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公司潜在数量查询
     */
    public void testGetCompanyPotentialCount(String companyName) {
        try {
            ResponseMessage<CompanyPotentialCount> response = TaurusApiService.getCompanyPotentialCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 商标覆盖分类数量查询
     */
    public void testTmCategoryCount(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getTmCategoryCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 专利商机数量查询
     */
    public void testPatentCount(String companyName) {
        try {
            ResponseMessage<PatentCount> response = TaurusApiService.getPatentCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 著作权商机数量查询
     */
    public void testCopyrightCount(String companyName) {
        try {
            ResponseMessage<CopyrightCount> response = TaurusApiService.getCopyrightCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询年报数量
     */
    public void testYearReportCount(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getYearReportCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询增值电信机会数量
     */
    public void testDianXin(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getDianXinCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询娱乐牌照数量
     */
    public void testYuLe(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getYuLeCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询高新企业机会
     */
    public void testGaoxin(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getGaoXinCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询年报数量
     */
    public void testShuiShou(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getShuiShouCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询年报数量
     */
    public void testYingShi(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getYingShiCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询年报数量
     */
    public void testShiPin(String companyName) {
        try {
            ResponseMessage<ItemCount> response = TaurusApiService.getShiPinCount(companyName);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
