package com.gongsibao.rest.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.weixin.pa.WeixinTestConfiguration;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.messagetemplate.*;
import org.netsharp.wx.pa.WeixinMessageListener;
import org.netsharp.wx.pa.startup.StartupWeixin;

public class TemplateMessageTest {
    private AccessToken token;

    @Before
    public void initialize() {
        new StartupWeixin().startup();
        WeixinMessageListener listner = new WeixinMessageListener();
        AccessTokenManage.refreshToken("wxd17b135853f81896");
        token = listner.getAccessToken("gh_29f5a8b8da16");
    }

    @Test
    public void getPrivateTemplates(){

        GetAllPrivateTemplateRequest request = new GetAllPrivateTemplateRequest();
        {
            request.setTokenInfo(token);
        }

        request.getResponse();
    }


    @Test
    public void addTemplate(){

        this.getTemplateId();
    }

    @Test
    public void sendMessage(){

        String template_id = this.getTemplateId();

        SendTemplateData data = new SendTemplateData();
        {
            data.getFirst().setValue("上课通知");//这个模板下没有使用这个关键字
            data.getKeynotes().put("userName",new KeyNote("张三"));
            data.getKeynotes().put("courseName",new KeyNote("书法欣赏"));
            data.getKeynotes().put("date",new KeyNote("下周二下午3:00"));
            data.getRemark().setValue("请准时参加，谢谢！");
        }

        SendTemplateMessageRequest request = new SendTemplateMessageRequest();
        {
            request.setTokenInfo(token);
//			request.setTouser("og-Yqw10pa_lcnZk_VFkEmpdy_Go");
            request.setTouser("oXlM_1iqE0muAEMtuFlNx_sLdtjc");
            request.setTemplate_id(template_id);
            request.setPageUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd17b135853f81896&redirect_uri=http%3a%2f%2ficompany.gongsibao.net%2findex.html&response_type=code&scope=snsapi_base&state=111echat_redirect&connect_redirect=1#wechat_redirect");
            request.setData(data);
        }

        request.getResponse();
    }

    private String getTemplateId(){
        AddTemplateRequest request = new AddTemplateRequest();
        {
            request.setTokenInfo(token);
            request.setTemplate_id_short("TM00080");
        }
        AddTemplateResponse response = request.getResponse();
        System.out.println(response.getTemplate_id());
        return response.getTemplate_id();
    }
}
