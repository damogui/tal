package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;
import com.gongsibao.u8.base.ISoOrderService;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.controller.constant.Constant;
import com.gongsibao.rest.controller.BaseController;
//import io.swagger.annotations.*;
import net.sf.json.JSONObject;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.qrcode.QrCodeRequest;
import org.netsharp.wx.mp.api.qrcode.QrCodeResponse;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.base.ISceneService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.entity.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "/wx/{v}/user")
@ApiVersion(1)
@CrossOrigin
//@Api(value = "QrCodeController", description = "二维码api")
public class QrCodeController extends BaseController {
    @Value("${oid}")
    private String oid;
    ISceneService sceneService = ServiceFactory.create(ISceneService.class);
    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
    ITradeMarkCaseService caseService = ServiceFactory.create(ITradeMarkCaseService.class);

    /**
     * @param request, mobile
     * @return org.netsharp.wx.mp.api.qrcode.QrCodeResponse
     * @Description:TODO 生成二维码
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/20 9:37
     */
    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    @CrossOrigin
//    @ApiOperation(value = "生成二维码", notes = "生成二维码")
    public String qrcode(HttpServletRequest request,
                           @RequestParam("mobile") String mobile,
                           @RequestParam("businessId") String businessId,
                           @RequestParam("source") String source
    ) {
        IPublicAccountService publicAccountService = ServiceFactory.create(IPublicAccountService.class);
        PublicAccount pa = publicAccountService.byOriginalId(oid);
        AccessToken at = AccessTokenManage.getTokenByAppId(pa.getAppId());
        QrCodeRequest requestQ = new QrCodeRequest();{
            StringBuffer senceStr = new StringBuffer(mobile);
            requestQ.setTokenInfo(at);
            requestQ.setSenceStr(senceStr.append("|").append(businessId).append("|").append(source).toString());
            // 过期时间，为0表示是持久的二维码，否则是临时二维码
            requestQ.setExpireSeconds(60 * 60 * 24 * 30);
        }

        QrCodeResponse response = requestQ.getResponse();
        String codeUrl = response.getQrCodeUrl();
        JSONObject jsonObject = new JSONObject();
        {
            jsonObject.put("mobile", mobile);
            jsonObject.put("businessId", businessId);
            jsonObject.put("source", source);
        }
        Scene scene = new Scene();
        {
            scene.toNew();
            scene.setQrCodeUrl(codeUrl);
            scene.setCodeType(Constant.TEMPQRCODE);
            scene.setCreateTime(new Date());
            scene.setJsonString(jsonObject.toString());
            scene.setExpireSeconds(requestQ.getExpireSeconds());
            scene.setCode(requestQ.getSenceStr());
        }
        if (source.equals(Constant.QRCODE_SB)) {
            scene.setName("商标临时二维码");
            TradeMarkCase tmc = caseService.byId(businessId);
            if (tmc != null && null != tmc.getCreator()) {
                scene.setCreatorId(tmc.getCreatorId());
                scene.setCreator(tmc.getCreator());
            }
        } else if (source.equals(Constant.QRCODE_ORDER)) {
            scene.setName("订单临时二维码");
            SoOrder order = orderService.getByOrderId(Integer.valueOf(businessId));
            if (order != null && null != order.getCreator()) {
                scene.setCreatorId(order.getCreatorId());
                scene.setCreator(order.getCreator());
            }
        }
        sceneService.save(scene);
        return codeUrl;
    }

}
