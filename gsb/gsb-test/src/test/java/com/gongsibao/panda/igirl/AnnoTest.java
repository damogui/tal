package com.gongsibao.panda.igirl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.igirl.base.IGirlConfigService;
import com.gongsibao.igirl.base.INCLOneService;
import com.gongsibao.igirl.base.INCLTwoService;
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
import org.netsharp.util.StringManager;

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

	@Test
	public void nclBatchToData() throws IOException {
		INCLOneService inclOneService = ServiceFactory.create(INCLOneService.class);
		INCLTwoService inclTwoService = ServiceFactory.create(INCLTwoService.class);
		INclBatchService iNclBatchService = ServiceFactory.create(INclBatchService.class);
		Oql oql = new Oql();
		oql.setType(NclBatch.class);
		oql.setSelects("NclBatch.*");
		oql.setFilter("currentStatus=?");
		oql.getParameters().add("currentStatus",true, Types.BOOLEAN);
		NclBatch nb = iNclBatchService.queryFirst(oql);
		List<NCLTwo> nclts = new ArrayList<>();

		File file = new File("D:/igirl.json");
		String str = FileUtils.readFileToString(file);
		str = str.replaceAll("\n","").replaceAll("\\s*","");
		JSONObject jsons = JSONObject.fromObject(str);
		JSONArray array = jsons.getJSONArray("data");
        NCLOne one = new NCLOne();
        one.toNew();
        for (int i=0;i<array.size();i++){
            JSONObject json = array.getJSONObject(i);
            if (json.get("level").toString().equals("1")){
                one.setCode(json.getString("code"));
                if(StringManager.isNullOrEmpty(json.getString("name"))) {
                    one.setName(json.getString("code"));
                }else {
                    one.setName(json.getString(json.getString("name")));
                }
                one.setMemo(json.getString("description"));
                one.setNclBatchId(nb.getId());
                one = inclOneService.save(one);
				System.out.println(one.getName());
            }else if(json.get("level").toString().equals("3")){
                NCLTwo two = new NCLTwo();
                two.toNew();
                two.setCode(json.getString("pid"));
                two.setName(json.getString("name"));
                two.setThirdCode(json.getString("code"));
                two.setNclOneId(one.getId());
                two.setNclOne(one);
				System.out.println(two.getName());
                nclts.add(two);
            }
            inclTwoService.saves(nclts);
        }
	}
}
