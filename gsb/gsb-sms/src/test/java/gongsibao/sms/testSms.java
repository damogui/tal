package gongsibao.sms;

import java.util.HashMap;
import java.util.Map;

import com.gongsibao.sms.base.ISmsService;
import com.gongsibao.sms.dto.SmsResponse;
import com.gongsibao.sms.dto.SmsTemplateResponse;
import com.gongsibao.sms.service.SmsServiceImpl;

public class testSms {
	public static void main(String[] args) {
		ISmsService sms = new SmsServiceImpl();
        //测试直接发送短信
        SmsResponse response = sms.send(1, "15600023673", "Test message from java");
        System.out.println(response.getIsSuccessful());

        //测试模板发送
        Map<String,String> map=new HashMap<String, String>();
        map.put("Test","Test Tempalte from java");
        response = sms.send(1, "15600023673",1,map);
        System.out.println(response.getIsSuccessful());

        //测试获取模板信息
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("Test", "Test Tempalte from java");
        SmsTemplateResponse t = sms.getTemplateBy(1, paras);
        System.out.println(t.getTemplate());
	}

}
