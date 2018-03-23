package com.gongsibao.panda.supplier.igirl;
import com.gongsibao.entity.igirl.dict.ApplierType;
import com.gongsibao.entity.igirl.dict.TMCState;
import com.gongsibao.entity.igirl.dict.WriteType;
import com.gongsibao.taurus.util.StringManager;
import com.google.common.base.Ascii;
import com.sun.org.apache.xpath.internal.SourceTree;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.netsharp.cache.service.redis.RedisCacheCommand;
import org.netsharp.cache.service.redis.RedisConnection;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.igirl.base.INCLOneService;
import com.gongsibao.igirl.base.INCLTwoService;
import com.gongsibao.igirl.base.INclBatchService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.tools.HttpUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnoTest2 {
	@Test
	public void testcache() {
		RedisConnection con=new RedisConnection();
		con.setDatabase(0);
		con.setIp("123.57.156.212");
		con.setPort(8967);
		con.setPassword("Gongsibao2018");
		RedisCacheCommand cmd=new RedisCacheCommand();
		cmd.open(con);
		cmd.set("new_order_275070","新单测试通知：张三,李四，您的客户马寅已经下单! 下单内容为：, 测试1，测试1，测试1，请及时跟进");
		cmd.close();
		
//		String st=cmd.get("new_order_273962");
//		System.out.println(st);
//		for(String s :st) {
//			System.out.println(s);
//		}
	}

	@Test
	//TODO IGIRL 老数据入库
	public void transferData(){
		IUploadAttachmentService upattachementService = ServiceFactory.create(IUploadAttachmentService.class);
		ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);
		ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
		INclBatchService nclBatchService = ServiceFactory.create(INclBatchService.class);
		INCLOneService oneService = ServiceFactory.create(INCLOneService.class);
		INCLTwoService twoService = ServiceFactory.create(INCLTwoService.class);
		String result = HttpUtils.get("http://192.168.2.169/igirl/api/trademark/report/fill");
		//String result = HttpUtils.get("http://192.168.2.169/igirl2/api/trademark/report/fill");
		JSONObject json  = JSONObject.fromObject(result);
		JSONArray data = json.getJSONArray("data");
		String corpTrademarkId;
        Date updTime=new Date();
		TradeMark tradeMark;
		TradeMarkCase tradeMarkCase;
		UploadAttachment uploadAttachment;
        String caseCode;
        NCLOne nclOne = new NCLOne();
        //获取每一个数据的基础json数据
        for (int i=0;i<data.size();i++) {
            JSONObject step = data.getJSONObject(i);
            corpTrademarkId = step.getString("corpTrademarkId");
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            try {
                updTime =sDateFormat.parse(step.getString("updTime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            JSONObject step1 = step.getJSONObject("step1");
            JSONObject step2 = step.getJSONObject("step2");
            JSONObject step3 = step.getJSONObject("step3");
            JSONObject step4 = step.getJSONObject("step4");
            JSONObject step5 = step.getJSONObject("step5");
            JSONObject step6 = step.getJSONObject("step6");
            JSONArray goods = step6.getJSONArray("goods");
            JSONObject good;
            String tradeOption="";
            int tradeMarkCaseID;

            List<String> ncls = new ArrayList<>();
            StringBuffer ncl = new StringBuffer("");
            int count = 1;
            for (int m = 0; m < goods.size(); m++) {
                good = goods.getJSONObject(m);
                String classes = good.getString("classes");
                tradeOption=classes;
                nclOne =oneService.getNclOneByCode(classes);
                //TODO nclOne 数据
                String group = good.getString("group");
                JSONArray nameList= good.getJSONArray("nameList");
                for (int j = nameList.size() - 1; j >= 0; j--) {
                    String name = nameList.getString(i);
                    NCLTwo nclTwo = twoService.findNclTwoByCode(group, name);
                    //TODO nclTwo 数据
                    ncl.append(count).append(":");
                    ncl.append(name).append(":");
                    ncl.append(group).append(":");
                    ncl.append(nclTwo.getId());
                    ncls.add(ncl.toString());
                    count++;
                }
            }
            String nclStr = StringManager.join("/r/n",ncls);

            System.out.println(tradeOption);
            JSONObject step7 = step.getJSONObject("step7");
            //写入新数据库
            {
                //TODO tradeMarkCase 数据
                tradeMarkCase = new TradeMarkCase();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

                //caseCode = TimeToCode(updTime, corpTrademarkId);
                caseCode=sdf.format(updTime);

                tradeMarkCase.setCode(caseCode);

                tradeMarkCase.setMobile("13000000000");
                tradeMarkCase.setContactName("未知");
                tradeMarkCase.setTmcState(TMCState.WAIT_CONFIRM);
                tradeMarkCase.setOwnerId(1808);
                tradeMarkCase.setOwnerName("曹玉玺");
                tradeMarkCase.setCreatorId(1808);
                tradeMarkCase.setCreator("曹玉玺");
                tradeMarkCase.setCreateTime(updTime);
                tradeMarkCase.setUpdator("曹玉玺");
                tradeMarkCase.setUpdatorId(1808);
                tradeMarkCase.setUpdateTime(updTime);
                tradeMarkCase.setSupplierId(1);
                 //写入大类
                tradeMarkCase.setTradeOptions(tradeOption+" ");

                tradeMarkCase.setApplier(step2.getString("appCnName"));
                tradeMarkCase.setCompanyName(step2.getString("appCnName"));
                tradeMarkCase.setApplierType(ApplierType.PUBLIC);
                tradeMarkCase.setWriteType(WriteType.DALU);
                tradeMarkCase.setYwPhone("010-84927588");
                tradeMarkCase.setFax("010-84927588");
                tradeMarkCase.setCreditCode(step2.getString("certCode"));
                tradeMarkCase.setApplierAddress(step2.getString("appCnAddr"));
                tradeMarkCase.setMailCode("1000000");
                tradeMarkCase.setContactName("曹玉玺");
                tradeMarkCase.setToken("13000000000");
                tradeMarkCase.setTokenImgUrl("http://123.57.156.212:9999/qc?detailLink=http%3A%2F%2Figirl.gongsibao.com%2F%2Fgsb%2Figirl%2Fmobile%2Fmain.html%23%2F%3Fspid%3D1%26casecode%3D"+caseCode+"%26source%3Dcase");

                tradeMarkCase.setCaseAmount(new BigDecimal(0));
                tradeMarkCase.setProxyCompanyName("汉唐信通（北京）科技有限公司");
                tradeMarkCase.setCaseProxyContactPerson("张飞龙");
                tradeMarkCase.setAccountNo("2110444010400028162");
                tradeMarkCase.setUrgency(72);
                tradeMarkCase.setDepartmentId(2);

                tradeMarkCaseService.save(tradeMarkCase);
                tradeMarkCaseID=tradeMarkCase.getId();


                //TODO tradeMark 数据
                tradeMark = new TradeMark();
                tradeMark.setTradeMarkCaseId(tradeMarkCaseID);
                if (nclOne!=null){
                    tradeMark.setNclOneId(nclOne.getId());
                }
                tradeMark.setSelectedTwoStr(nclStr);
                tradeMark.setCreatorId(1808);
                tradeMark.setCreator("曹玉玺");
                tradeMark.setCreateTime(updTime);
                tradeMark.setUpdator("曹玉玺");
                tradeMark.setUpdatorId(1808);
                tradeMark.setUpdateTime(updTime);
                //TODO 未完待续
                tradeMarkService.save(tradeMark);

                //TODO uploadAttachment 数据
                uploadAttachment = new UploadAttachment();
                uploadAttachment.setName("营业执照");

                upattachementService.save(uploadAttachment);
            }

        }
	}

	public String TimeToCode(String updTime,String id)
    {   //2018-01-22 13:33:17.0
        String[] tempCode=updTime.split("\\ ");
        String[] tempDate=tempCode[0].split("\\-");
        String[] tempTime=tempCode[1].split("\\:");
        String[] tempSecond=tempTime[2].split("\\.");
        //int a = (int)(Math.random()*(9999-1000+1))+1000;
        //String r=tempDate[0]+tempDate[1]+tempDate[2]+tempTime[0]+tempTime[1]+tempSecond[0]+Integer.toString(a);
        String r=tempDate[0]+tempDate[1]+tempDate[2]+tempTime[0]+tempTime[1]+tempSecond[0];
        return r;
    }

}
