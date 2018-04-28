package com.gongsibao.dingtalkrobot.action.broadcast;

import com.gongsibao.dingtalkrobot.base.ICustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.utils.DateUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionBroadcastPrepare implements IAction {

    ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

    //准备消息
    @Override
    public void execute(ActionContext ctx) {
        Map<String, Object> ctxStatus = ctx.getStatus();
        if (ctxStatus == null) return;
        String ywyMobile = StringUtils.trimToEmpty((String) ctxStatus.get("ywyMobile"));
        String atMobile = StringUtils.trimToEmpty((String) ctxStatus.get("atMobile"));
        String atName = StringUtils.trimToEmpty((String) ctxStatus.get("atName"));
        Integer accountId = NumberUtils.toInt(ctxStatus.get("accountId"));//statusMap.put("isSend", 0);
        boolean isSend = (boolean) ctxStatus.get("isSend");
        String prodName = StringUtils.trimToEmpty((String) ctxStatus.get("prodName"));
        List<String> ywyNames = (List<String>) ctxStatus.get("ywyNames");
        if (!isSend) {
            return;
        }
        NCustomer crm = customerService.getByAccount(accountId);
        String customerName;
        if (crm == null) {
            customerName = "******";
        } else {
            if (StringUtils.isNotBlank(crm.getRealName())) {
                Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                Matcher m = p.matcher(crm.getRealName());
                if (m.matches()) {
                    customerName = crm.getRealName().substring(0, 5) + "******";
                } else {
                    customerName = crm.getRealName();
                }
            } else if (StringUtils.isNotBlank(crm.getMobile())) {
                customerName = crm.getMobile().substring(0, 5) + "******";
            } else {
                customerName = "vip客户";
            }
        }
        String combinationStr = "客户 **" + customerName + "** 已经下单! 下单内容为：" + prodName + ", 请及时跟进";
        String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"新单通知\",\"text\": \" **新单通知** " + atName + "\\n > " + combinationStr + "\\n > ###### " + DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss") + " 发布 [公司宝](http://www.gongsibao.com/) \\n \"}, \"at\": {\"atMobiles\":[" + ywyMobile + "],\"isAtAll\":false}}";
        String readMsg = "新单通知：" + StringUtils.join(ywyNames, ",") + "，您的客户" + customerName + "已经下单! 下单内容为：" + prodName + ", 请及时跟进";
        ctxStatus.put("textMsg", textMsg);
        ctxStatus.put("readMsg", readMsg);
    }
}
