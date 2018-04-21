package com.gongsibao.rest.web.controller.v1.user;

import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.controller.BaseController;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.accesstoken.PAccount;
import org.netsharp.wx.mp.api.accesstoken.PaConfiguration;
import org.netsharp.wx.mp.api.qrcode.QrCodeRequest;
import org.netsharp.wx.mp.api.qrcode.QrCodeResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/wx/{v}/user")
@Api(1)
@CrossOrigin
public class QrCodeController extends BaseController {
    /**
     * @Description:TODO 生成二维码
     * @param  request, mobile
     * @return org.netsharp.wx.mp.api.qrcode.QrCodeResponse
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/20 9:37
     */
    @RequestMapping(value = "/qrcode",method = RequestMethod.GET)
    @CrossOrigin
    public String qrcode(HttpServletRequest request,
                         @RequestParam("mobile") String mobile,
                         @RequestParam("businessId") String businessId,
                         @RequestParam("source") String source
    ){
        PAccount pa = PaConfiguration.get(originalId(request));
        AccessToken at = AccessTokenManage.getTokenByAppId( pa.getAppId() );
        QrCodeRequest requestQ = new QrCodeRequest();
        StringBuffer senceStr=new StringBuffer(mobile);
        requestQ.setTokenInfo(at);
        requestQ.setSenceStr(senceStr.append("|").append(businessId).append("|").append(source).toString());
        // 过期时间，为0表示是持久的二维码，否则是临时二维码
        requestQ.setExpireSeconds(60*60*24*30);
        QrCodeResponse response = requestQ.getResponse();
        return response.getQrCodeUrl();
    }

}
