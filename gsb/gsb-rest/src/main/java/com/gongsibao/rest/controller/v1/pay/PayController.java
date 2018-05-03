package com.gongsibao.rest.controller.v1.pay;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.netsharp.rest.base.order.IOrderService;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.controller.security.SecurityUtils;
import com.netsharp.rest.utils.NumberUtils;
import com.netsharp.rest.utils.PayCommonUtil;
import com.netsharp.rest.utils.StringUtils;
import com.netsharp.rest.utils.XMLUtil;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * ClassName: PayController
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/20 18:08
 */

@RestController
@RequestMapping("/wx/{v}/pay/")
@ApiVersion(1)
@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class PayController extends BaseController {

    Log log = LogFactory.getLog(PayController.class);

    IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    @Autowired
    IOrderService orderService;
    @Value("${wx_notify_key}")
    private String wxNotifyKey;

    @RequestMapping(value = "/testNotify")
    @ResponseBody
    private void testNotify(HttpServletRequest request) {
        OrderPayDTO dto = new OrderPayDTO();
        {
            dto.setOrderId(235846);
            dto.setPayId(NumberUtils.toInt(115763985));
            dto.setTotalFee(NumberUtils.toInt(100));
            dto.setOnlineTradeNo("微信支付");
            dto.setSuccess(true);
        }
        // 修改自身业务逻辑状态
        try {
            orderService.updateOnlinePay(dto);
        } catch (BusinessException e) {
            log.error("========== updateOnlinePay error ========== ", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* *
     * @Description: 微信支付回调
     * @param  [request, response]
     * @return void
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/21
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @ResponseBody
    public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析xml成map
        Map<String, String> m = new HashMap<>();
        m = XMLUtil.doXMLParse(sb.toString());

        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }



        log.info("==========packageParams:==========" + packageParams);
        //判断签名是否正确
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, wxNotifyKey)) {
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            String total_fee = StringUtils.trimToEmpty(packageParams.get("total_fee"));
            boolean isSuccess = false;

            // 账号信息
            String out_trade_no = StringUtils.trimToEmpty(packageParams.get("out_trade_no"));

            String payId = out_trade_no.substring(out_trade_no.lastIndexOf("_") + 1, out_trade_no.length());

            out_trade_no = out_trade_no.substring(out_trade_no.indexOf("_") + 1, out_trade_no.lastIndexOf("_"));
            out_trade_no = SecurityUtils.rc4Decrypt(out_trade_no);
            int orderId = NumberUtils.toInt(out_trade_no);

            if ("SUCCESS".equals(String.valueOf(packageParams.get("result_code")).toUpperCase())) {
                isSuccess = true;
            }
            // 微信支付订单号流水号
            String transactionId = StringUtils.trimToEmpty(packageParams.get("transaction_id"));

            OrderPayDTO dto = new OrderPayDTO();
            {
                dto.setOrderId(orderId);
                dto.setPayId(NumberUtils.toInt(payId));
                dto.setTotalFee(NumberUtils.toInt(total_fee));
                dto.setOnlineTradeNo(transactionId);
                dto.setSuccess(isSuccess);
            }

            // 修改自身业务逻辑状态
            try {
                orderService.updateOnlinePay(dto);
            } catch (BusinessException e) {
                log.error("========== updateOnlinePay error ========== ", e);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 微信模板消息发送
            try {
                accountWeiXinService.buySuccessSendMsg(orderId, total_fee);
            }  catch (Exception e) {
                log.error("========== message send error ========== ", e);
            }

            String resXml = "";
            if (isSuccess) {
                // 这里是支付成功
                //////////执行自己的业务逻辑////////////////
                String mch_id = (String) packageParams.get("mch_id");
                String openid = (String) packageParams.get("openid");
                String is_subscribe = (String) packageParams.get("is_subscribe");

                log.info("==========mch_id:==========" + mch_id);
                log.info("==========openid:==========" + openid);
                log.info("==========is_subscribe:==========" + is_subscribe);
                log.info("==========out_trade_no:==========" + out_trade_no);
                log.info("==========total_fee:==========" + total_fee);

                //////////执行自己的业务逻辑////////////////  TODO

                log.info("==========支付成功==========");
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

            } else {
                log.info("==========支付失败,错误信息：==========" + packageParams.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            //------------------------------
            //处理业务完毕
            //------------------------------
            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
            log.info("==========通知签名验证失败==========");
        }

        //微信回调
//        weiXinPayService.weixinNotify(request, response);
    }
}
