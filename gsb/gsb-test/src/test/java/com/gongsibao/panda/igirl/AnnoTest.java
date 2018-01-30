package com.gongsibao.panda.igirl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.igirl.base.IGirlConfigService;
import com.gongsibao.igirl.utils.JsonFormatTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.util.ReflectManager;

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
	public void getNclBatchData() {
		IGirlConfigService service = ServiceFactory.create(IGirlConfigService.class);
		Oql oql = new Oql();
		oql.setType(IGirlConfig.class);
		oql.setSelects("IGirlConfig.*");
		oql.setFilter("code = ?");
		oql.getParameters().add("code","IGIRL_JSON_IN", Types.VARCHAR);
		IGirlConfig in = service.queryFirst(oql);

		oql = new Oql();
		oql.setType(IGirlConfig.class);
		oql.setSelects("IGirlConfig.*");
		oql.setFilter("code = ?");
		oql.getParameters().add("code","IGIRL_JSON_OUT",Types.VARCHAR);
		IGirlConfig out = service.queryFirst(oql);

		oql = new Oql();
		oql.setType(IGirlConfig.class);
		oql.setSelects("IGirlConfig.*");
		oql.setFilter("code = ?");
		oql.getParameters().add("code","IGIRL_JSON_NAME",Types.VARCHAR);
		IGirlConfig name = service.queryFirst(oql);

		List<JSONArray> arrays = fileToJson(in.getValue());
		JSONArray data = new JSONArray();
		for (JSONArray array:arrays){
			data.addAll(array);
		}
		JSONObject json = new JSONObject();
		json.put("code",200);
		json.put("data",data);
		JsonFormatTool.createJsonFile(json.toString(),out.getValue(),name.getValue());
		System.out.println("获取数据源文件成功,路径："+out.getValue()+name.getValue());
	}

	public static List<JSONArray> fileToJson(String filepath){
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
}
