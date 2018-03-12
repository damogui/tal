package com.gongsibao.taurus.api;

import com.gongsibao.taurus.entity.TmNew;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wk on 2018/1/30.
 * 大数据dataapi新接口测试
 */
public class TmNewApiTest {
    String companyName = "汉唐信通（北京）咨询股份有限公司";
    static ExecutorService exec = Executors.newFixedThreadPool(50);
    ObjectMapper mapper = new JacksonObjectMapper();

    String json = "";

    @Test
    public void test() {
        System.out.println("商标：");

        //region 可使用接口
//        tmNewByCompany("天津市真地商贸有限公司金街酒销售分公司");
//        tmNewByRegNo("6201543");
//        tmNewByName("y");
//        System.err.println("/**************************************************/");

        try {
            tmNewByCompanyConcurrent("天津市真地商贸有限公司金街酒销售分公司");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tmNewByCompanyConcurrent(String companyName) {
        try {
            long t1 = System.currentTimeMillis();
            ResponseMessage<TmNew> response = TaurusApiService.getTmNewByCompanyConcurrent(companyName, 100);
            long t2 = System.currentTimeMillis();

            System.out.println("100条耗时：" + (t2 - t1));

            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void tmNewByCompany(String companyName) {
        try {
            ResponseMessage<TmNew> response = TaurusApiService.getTmNewByCompany(companyName, 0, 15);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tmNewByName(String companyName) {
        try {
            ResponseMessage<TmNew> response = TaurusApiService.getTmNewByName(companyName, 0, 20);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tmNewByRegNo(String regNo) {
        try {
            ResponseMessage<TmNew> response = TaurusApiService.getTmNewByRegNo(regNo, 0, 10);
            System.err.println(response);
            System.out.println(response.getResult() + "：" + response.getResultMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
