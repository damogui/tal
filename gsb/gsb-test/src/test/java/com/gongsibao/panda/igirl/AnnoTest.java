package com.gongsibao.panda.igirl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.igirl.base.INCLOneService;
import com.gongsibao.igirl.base.INCLTwoService;
import com.gongsibao.igirl.base.INclBatchService;
import com.gongsibao.igirl.web.TradeMarkCaseController;

public class AnnoTest {
	@Test
	public void anTest() {
		TradeMarkCaseController tmc=new TradeMarkCaseController();
		Method method = ReflectManager.getMethods(TradeMarkCaseController.class,"TradeMarkCaseController");
		method.getAnnotation(Authorization.class);
	}

	@Test
	public void nclBatch() {
		INCLOneService nos = ServiceFactory.create(INCLOneService.class);
		INCLTwoService nts = ServiceFactory.create(INCLTwoService.class);
		INclBatchService nbs = ServiceFactory.create(INclBatchService.class);
		List<NCLTwo> nclts;
		Oql oql = new Oql();
		oql.setType(NclBatch.class);
		oql.setSelects("NclBatch.*");
		oql.setFilter("currentStatus=?");
		oql.getParameters().add("currentStatus",true, Types.BOOLEAN);
		NclBatch nb = nbs.queryFirst(oql);
		List<JSONArray> arrays = readfile("D:/json");
		for (JSONArray array:arrays){
			nclts = new ArrayList<>();
			NCLOne one = new NCLOne();
			one.toNew();
			for (int i=0;i<array.size();i++){
				JSONObject json = array.getJSONObject(i);
				System.out.println(json.toString());
				if (json.get("level").toString().equals("1")){
					one.setCode(json.getString("code"));
					one.setName(json.getString("name"));
					one.setMemo(json.getString("description"));
					one.setPeriod(nb.getCode());
					one = nos.save(one);
				}else if(json.get("level").toString().equals("3")){
					NCLTwo two = new NCLTwo();
					two.toNew();
					two.setCode(json.getString("pid"));
					two.setName(json.getString("name"));
					two.setThirdCode(json.getString("code"));
					two.setNclOneId(one.getId());
					two.setNclOne(one);
					nclts.add(two);
				}
				nts.saves(nclts);
			}
		}
		System.out.println("注入完成");
	}

	public static List<JSONArray> readfile(String filepath){
		List<JSONArray> arrays = new ArrayList<>();
		File file = new File(filepath);
		String[] files = file.list();
		for (int i = 0; i < files.length; i++) {
			File json = new File(filepath + File.separator + files[i]);
			try {
				String str = FileUtils.readFileToString(json);
				JSONArray array = JSONObject.fromObject(str).getJSONArray("data");
				arrays.add(array);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arrays;
	}

	@Test

	public void test(){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost/panda/rest/service");

	}
}
