package com.gongsibao.panda;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.netsharp.organization.dic.PostType;
import org.netsharp.panda.utils.EnumUtil;

public class AppConfigTest {

	@Test
	public void run() throws JsonGenerationException, JsonMappingException,
			IOException {
		String str = "aaa\"aaaa";
        System.out.println(str);
        String s = str.replaceAll("\"", "\\\\\"");
        System.out.println(s);
        
		String json = EnumUtil.getColumnFormatter(PostType.class);
		System.out.println(json);
		json = json.replaceAll("\"", "\\\\\"");
		System.out.println(json);
		
		// IAppconfigService service =
		// ServiceFactory.create(IAppconfigService.class);
		// Appconfig entity = null;
		// entity = new Appconfig();{
		// entity.toNew();
		// entity.setIsSystem(true);
		// entity.setCode("environment.qiniu.accessKey");
		// entity.setValue("XZer639PBrrnxtHmpQaBtJX0X4mEjnPeOfw70v48");
		// entity.setName("七牛密钥");
		// entity.setGroupName("七牛");
		// entity.setResourceNodeId(844433933914497024L);
		// service.save(entity);
		// }
		//
		// entity = new Appconfig();{
		// entity.toNew();
		// entity.setIsSystem(true);
		// entity.setCode("environment.qiniu.secretKey");
		// entity.setValue("jUu1Oy9cCM3HqfKPoXRCAcgqb04dYTi5s0QZ3NEk");
		// entity.setName("七牛秘密密钥");
		// entity.setGroupName("七牛");
		// entity.setResourceNodeId(844433933914497024L);
		// service.save(entity);
		// }
		//
		// entity = new Appconfig();{
		// entity.toNew();
		// entity.setIsSystem(true);
		// entity.setCode("environment.qiniu.bucket");
		// entity.setValue("cdn2");
		// entity.setName("七牛上传空间");
		// entity.setGroupName("七牛");
		// entity.setResourceNodeId(844433933914497024L);
		// service.save(entity);
		// }

		// entity = new Appconfig();{
		// entity.toNew();
		// entity.setIsSystem(true);
		// entity.setCode("app.name");
		// entity.setValue("NetSharp");
		// entity.setName("系统名称");
		// entity.setGroupName("系统设置");
		// entity.setResourceNodeId(844433933914497024L);
		// service.save(entity);
		// }

//		System.out.println("EnumTest.FRI 的 value = " + EnumTest.FRI.getValue());
//		System.out.println("EnumTest.FRI 的 text = " + EnumTest.FRI.getText());
	}
}
