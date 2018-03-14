package com.gongsibao.panda.supplier.igirl;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.igirl.base.*;
import com.gongsibao.tools.HttpUtils;
import com.sun.xml.internal.bind.v2.TODO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.netsharp.cache.service.redis.RedisCacheCommand;
import org.netsharp.cache.service.redis.RedisConnection;
import org.netsharp.communication.ServiceFactory;

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
		for (int i=0;i<data.size();i++){
			JSONObject step = data.getJSONObject(i);
			corpTrademarkId = step.getString("corpTrademarkId");
			JSONObject step1 = step.getJSONObject("step1");
			JSONObject step2 = step.getJSONObject("step2");
			JSONObject step3 = step.getJSONObject("step3");
			JSONObject step4 = step.getJSONObject("step4");
			JSONObject step5 = step.getJSONObject("step5");

			JSONObject step6 = step.getJSONObject("step6");
			JSONArray goods = step6.getJSONArray("goods");
			JSONObject good;
			for(int m=0;m<goods.size();m++){
				good = goods.getJSONObject(m);
				String classes = good.getString("classes");
				NCLOne nclOne = oneService.getNclOneByCode(classes);
				//TODO nclOne 数据
				String group = good.getString("group");
				JSONArray nameList = good.getJSONArray("nameList");
				for (int j=nameList.size()-1;j>=0;j--){
					String name = nameList.getString(i);
					NCLTwo nclTwo = twoService.findNclTwoByThirdCode(group,name);
					//TODO nclTwo 数据
				}

			}
			JSONObject step7 = step.getJSONObject("step7");

			//TODO tradeMark 数据
			TradeMark tradeMark = new TradeMark();
			tradeMarkService.save(tradeMark);

			//TODO tradeMarkCase 数据
			TradeMarkCase tradeMarkCase = new TradeMarkCase();
			tradeMarkCaseService.save(tradeMarkCase);

			//TODO uploadAttachment 数据
			UploadAttachment uploadAttachment = new UploadAttachment();
			upattachementService.save(uploadAttachment);
		}
	}

	

}
