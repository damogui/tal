package com.gongsibao.rest.controller.v1.user;

import com.netsharp.rest.common.annotation.Api;
import com.netsharp.rest.common.result.ResponseData;
import com.gongsibao.rest.controller.BaseController;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.qrcode.QrCodeRequest;
import org.netsharp.wx.mp.api.qrcode.QrCodeResponse;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/wx/{v}/user")
@Api(1)
@CrossOrigin
public class QrCodeController extends BaseController {
    @Value("${oid}")
    private String oid;
    /**
     * @Description:TODO 生成二维码
     * @param  request, mobile
     * @return org.netsharp.wx.mp.api.qrcode.QrCodeResponse
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/20 9:37
     */
    @RequestMapping(value = "/qrcode",method = RequestMethod.GET)
    @CrossOrigin
    public ResponseData qrcode(HttpServletRequest request,
                         @RequestParam("mobile") String mobile,
                         @RequestParam("businessId") String businessId,
                         @RequestParam("source") String source
    ){
        IPublicAccountService publicAccountService= ServiceFactory.create(IPublicAccountService.class);
        PublicAccount pa=publicAccountService.byOriginalId(oid);
        AccessToken at = AccessTokenManage.getTokenByAppId( pa.getAppId() );
        QrCodeRequest requestQ = new QrCodeRequest();
        StringBuffer senceStr=new StringBuffer(mobile);
        requestQ.setTokenInfo(at);
        requestQ.setSenceStr(senceStr.append("|").append(businessId).append("|").append(source).toString());
        // 过期时间，为0表示是持久的二维码，否则是临时二维码
        requestQ.setExpireSeconds(60*60*24*30);
        QrCodeResponse response = requestQ.getResponse();
        return ResponseData.getSuccess(response.getQrCodeUrl(),"获取成功!");
    }

}
