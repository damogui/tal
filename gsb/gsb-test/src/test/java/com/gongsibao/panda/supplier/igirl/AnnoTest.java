package com.gongsibao.panda.supplier.igirl;

import java.io.File;
import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.tm.dict.ConfigType;
import com.gongsibao.igirl.tm.base.IGirlConfigService;
import com.gongsibao.igirl.tm.base.IGirlRobotService;
import com.gongsibao.igirl.tm.utils.JsonFormatTool;
import com.gongsibao.utils.SupplierSessionManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

public class AnnoTest {
	@Test
	public void getNclBatchData() {
		IGirlConfigService iGirlConfigService = ServiceFactory.create(IGirlConfigService.class);
		Oql oql = new Oql();
		oql.setType(IGirlConfig.class);
		oql.setSelects("IGirlConfig.*");
		oql.setFilter("configType = ?");
		oql.getParameters().add("configType", ConfigType.IGIRL_JSON_IN.getValue(), Types.INTEGER);
		IGirlConfig in = iGirlConfigService.queryFirst(oql);

		Oql oql1 = new Oql();
		oql1.setType(IGirlConfig.class);
		oql1.setSelects("IGirlConfig.*");
		oql1.setFilter("configType = ?");
		oql1.getParameters().add("configType",ConfigType.IGIRL_JSON_OUT.getValue(),Types.INTEGER);
		IGirlConfig out = iGirlConfigService.queryFirst(oql1);

		Oql oql2 = new Oql();
		oql2.setType(IGirlConfig.class);
		oql2.setSelects("IGirlConfig.*");
		oql2.setFilter("configType = ?");
		oql2.getParameters().add("configType",ConfigType.IGIRL_JSON_NAME.getValue(),Types.INTEGER);
		IGirlConfig name = iGirlConfigService.queryFirst(oql2);

		List<JSONArray> arrays = fileToJson(in.getConfigValue());
		JSONArray data = new JSONArray();
		for (JSONArray array:arrays){
			data.addAll(array);
		}
		JSONObject json = new JSONObject();
		json.put("code",200);
		json.put("data",data);
		JsonFormatTool.createJsonFile(json.toString(),out.getConfigValue(),name.getConfigValue());
		System.out.println("获取数据源文件成功,路径："+out.getConfigValue()+name.getConfigValue());
	}

	public List<JSONArray> fileToJson(String filepath){
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
	public void testRobot(){
		IGirlRobotService robotService = ServiceFactory.create(IGirlRobotService.class);
		String result = robotService.postToRobot("123123");
		String resultErr =  robotService.postToRobotErrorMsg("1231231231");
		System.out.println(result);
		System.out.println(resultErr);
	}

	@Test
	public void testDepartment(){
		String departmentIds = SupplierSessionManager.getSubDepartmentIdsStr();
		System.out.println(departmentIds);
	}

//
//	public void nclBatchToData() throws IOException {
//		INCLOneService inclOneService = ServiceFactory.create(INCLOneService.class);
//		INCLTwoService inclTwoService = ServiceFactory.create(INCLTwoService.class);
//		INclBatchService iNclBatchService = ServiceFactory.create(INclBatchService.class);
//		Oql oql = new Oql();
//		oql.setType(NclBatch.class);
//		oql.setSelects("NclBatch.*");
//		oql.setFilter("currentStatus=?");
//		oql.getParameters().add("currentStatus",true, Types.BOOLEAN);
//		NclBatch nb = iNclBatchService.queryFirst(oql);
//		File file = new File("D:/igirl.json");
//		String str = FileUtils.readFileToString(file);
//		str = str.replaceAll("\n","").replaceAll("\\s*","");
//		JSONObject jsons = JSONObject.fromObject(str);
//		JSONArray array = jsons.getJSONArray("data");
//        NCLOne one = new NCLOne();
//        one.toNew();
//		List<NCLTwo> nclTwos = new ArrayList<>();
//        for (int i=0;i<array.size();i++){
//            JSONObject json = array.getJSONObject(i);
//            if (json.get("level").toString().equals("1")){
//				one = new NCLOne();
//				one.toNew();
//                one.setCode(json.getString("code"));
//                if(StringManager.isNullOrEmpty(json.getString("name"))) {
//                    one.setName(json.getString("code"));
//                }else {
//                    one.setName(json.getString(json.getString("name")));
//                }
//                one.setMemo(json.getString("description"));
//                one.setNclBatchId(nb.getId());
//                one = inclOneService.save(one);
//				System.out.println(one.getName());
//            }else if(json.get("level").toString().equals("3")){
//                NCLTwo two = new NCLTwo();
//                two.toNew();
//                two.setCode(json.getString("pid"));
//                two.setName(json.getString("name"));
//                two.setThirdCode(json.getString("code"));
//                two.setNclOneId(one.getId());
//                two.setNclOne(one);
//				nclTwos.add(two);
//				System.out.println(two.getName());
//				if (nclTwos.size()==1000){
//					inclTwoService.saves(nclTwos);
//					nclTwos = new ArrayList<>();
//				}
//				if (i==array.size()-1){
//					inclTwoService.saves(nclTwos);
//				}
//            }
//        }
//		System.out.println("完成所有源数据导入");
//	}
}
