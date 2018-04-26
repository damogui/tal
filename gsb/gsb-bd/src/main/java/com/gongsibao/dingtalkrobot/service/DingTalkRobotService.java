package com.gongsibao.dingtalkrobot.service;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.dingtalkrobot.base.ICustomerService;
import com.gongsibao.dingtalkrobot.base.IDingTalkRobotService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.uc.User;
import com.gongsibao.redis.base.IRedisAliyunService;
import com.gongsibao.redis.dto.ConstantCache;
import com.gongsibao.screendatav.base.IScreenDatavService;
import com.gongsibao.stat.dto.StatBillboard;
import com.gongsibao.u8.base.IOrderProdService;
import com.gongsibao.u8.base.ISoOrderProdUserMapService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.u8.base.IUserService;
import com.gongsibao.utils.DateUtils;
import com.gongsibao.utils.JsonUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DingTalkRobotService implements IDingTalkRobotService {


    private ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

    private IOrderProdService soOrderProdService = ServiceFactory.create(IOrderProdService.class);

    private ISoOrderProdUserMapService soOrderProdUserMapService = ServiceFactory.create(ISoOrderProdUserMapService.class);

    private IUserService ucUserService = ServiceFactory.create(IUserService.class);

    private IScreenDatavService screenDatavService = ServiceFactory.create(IScreenDatavService.class);

    private IDictService bdDictService = ServiceFactory.create(IDictService.class);

    private ICustomerService crmCustomerService = ServiceFactory.create(ICustomerService.class);

    private IRedisAliyunService redisAliyunService = ServiceFactory.create(IRedisAliyunService.class);

    private String ADVERTISEMENT = "http://gsb-public.oss-cn-beijing.aliyuncs.com/99a787bf6f235a37cb0fe8730e6954d1.png";

    //默认头像
    private String HEAD = "http://gsb-public.oss-cn-beijing.aliyuncs.com/445e4d00a4befdcf45db64adbffd648b.png";

    //公司宝钉钉大群  排名
//    private static String GSB_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9f8b7092c0103f6241cd54a5fd87168d7b7a7bdbe3693956ca3c4d6d30f9b365";

    //公司宝钉钉大群  无排名 跟进
    private static String GSB_FOLLOW_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=dfe03844c6df7439758fb464dc699dd5e8ca93db7729ef9940c665ad4f2fdbaa";

    //测试机器人
//    private static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9d8d816d0defa568325c58b4ef42b6501130efb6043de0a284eb69a19901f546";

    //机器人测试小群    推荐
    private static String privacy = "https://oapi.dingtalk.com/robot/send?access_token=31a2422c60c9845119a27aa0c2d936d8a53199b608c198b3dcc4968e7de3d471";

    //机器人群-播报机器人
    private static String radio = "https://oapi.dingtalk.com/robot/send?access_token=89770f9b5a70bfffad5cdda33c61a900932688d68b75064498b31f0b05d85327";

    @Override
    public int postRobotMsg(Map<String, Object> condition) {

        String token = MapUtils.getString(condition, "token", null);
        if (StringUtils.isBlank(token)) {
            return -2;
        }

        String beginTime = "", endTime = "";
        String time_zero = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 00:00:00";
        String time_10th = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 10:02:00";
        String time_13th = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 13:02:00";
        String time_16th = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 16:02:00";
        String time_18th = DateUtils.dateStr(new Date(), "yyyy-MM-dd") + " 18:02:00";

        Date dt = new Date();
        if (dt.getTime() < (DateUtils.strToDateTime(time_10th)).getTime()) {
            beginTime = time_zero;
            endTime = time_10th;
        }

        if (dt.getTime() < (DateUtils.strToDateTime(time_13th)).getTime() && dt.getTime() > (DateUtils.strToDateTime(time_10th)).getTime()) {
            beginTime = time_10th;
            endTime = time_13th;
        }
        if (dt.getTime() < (DateUtils.strToDateTime(time_16th)).getTime() && dt.getTime() > (DateUtils.strToDateTime(time_13th)).getTime()) {
            beginTime = time_13th;
            endTime = time_16th;
        }
        if (dt.getTime() < (DateUtils.strToDateTime(time_18th)).getTime() && dt.getTime() > (DateUtils.strToDateTime(time_16th)).getTime()) {
            beginTime = time_16th;
            endTime = time_18th;
        }

        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)) {
            return -1;
        }

        List<SoOrder> list = soOrderService.findByPayTime(beginTime, endTime);
        if (CollectionUtils.isEmpty(list)) {
            return -1;
        }
        List<Integer> orderIds = new ArrayList<>();
        Map<Integer, SoOrder> orderMap = new HashMap<>();

        for (SoOrder item : list) {
            orderIds.add(item.getId());
            orderMap.put(item.getId(), item);
        }

        List<OrderProd> prodList = soOrderProdService.findOrderProdByOrderIds(orderIds);
        if (CollectionUtils.isEmpty(prodList)) {
            return -1;
        }

        Map<Integer, String> officeMap = new HashMap<>();
        List<Dict> orgList = bdDictService.byType(493);

        for (Dict item : orgList) {
            if (item.getId() > 49300 && item.getId() < 49316) {   //分公司office统计只取这个区间的id
                officeMap.put(item.getId(), item.getName());
            }
        }
        // 要换成Employee
        List<User> ucUserList = ucUserService.findByRole("YWY", 1);
        if (CollectionUtils.isEmpty(ucUserList)) {
            return -1;
        }

        Set<Integer> userIds = new HashSet<>();
        for (User item : ucUserList) {
            if (NumberUtils.toInt(item.getOfficeId(), 0) != 0) {
                userIds.add(item.getId());
            }
        }

        List<Integer> prodIds = new ArrayList<>();
        for (OrderProd item : prodList) {
            prodIds.add(item.getId());
        }

        Map<Integer, User> ucUserMap = ucUserService.findMapByIds(new ArrayList<>(userIds));

        Map<Integer, List<OrderProdUserMap>> prodUserMap = soOrderProdUserMapService.findYWYByOrderProdIds(prodIds);
        Map<Integer, Integer> prodPayableMap = soOrderProdService.findPayablePrice(prodIds);

        //String screenStr = screenDatavService.getObjectById(20);
        String screenStr = "";
        Map<Integer, Integer> userRank = new HashMap<>();
        if (StringUtils.isNotBlank(screenStr)) {

            List<StatBillboard> res = JsonUtils.jsonToList(screenStr, StatBillboard.class);
            if (CollectionUtils.isNotEmpty(res)) {
                for (StatBillboard item : res) {
                    if (StringUtils.isNotBlank(item.getUserIdStr())) {
                        userRank.put(item.getUserId(), res.indexOf(item) + 1);
                    }
                }
            }
        }

        Map<String, Integer> msgMap = new HashMap<>();
//      feedcard格式才需要业务员头像,markdown格式可注释掉
//        Map<String, String> msgHeadUrlMap = new HashMap<>();
//        Set<Integer> headIds = new HashSet<>();

        for (OrderProd item : prodList) {
            List<OrderProdUserMap> prodUserMaps = (ArrayList) MapUtils.getObject(prodUserMap, item.getId(), new ArrayList<>());
            if (CollectionUtils.isEmpty(prodUserMaps)) continue;


            User user = (User) MapUtils.getObject(ucUserMap, prodUserMaps.get(0).getId(), null);
            if (user == null) continue;
            if (user.getOfficeId() == 0) continue;


            String officeName = MapUtils.getString(officeMap, user.getOfficeId(), null);
            if (officeName == null) continue;


            SoOrder soOrder = (SoOrder) MapUtils.getObject(orderMap, item.getOrderId(), null);
            if (soOrder == null) continue;

            Integer payablePrice = MapUtils.getInteger(prodPayableMap, item.getId(), 0);
            if (payablePrice == 0) continue;

            Integer rank = MapUtils.getInteger(userRank, user.getId(), 0);
            if (rank == 0) continue;

            String title = "> - 祝贺" + officeName + "" + user.getName() + "(全公司年度排名: " + rank + ") 签约 " + item.getProductName() + ".";
            Integer statPrice = MapUtils.getInteger(msgMap, title, 0);

//            String head = MapUtils.getString(msgHeadUrlMap, title, null);
//            if (head == null) {
//                msgHeadUrlMap.put(title, user.getHeadThumbFileUrl());
//                headIds.add(user.getHeadThumbFileId());
//            }

            if (statPrice == 0) {
                msgMap.put(title, payablePrice);
                continue;
            }

            msgMap.replace(title, statPrice + payablePrice);
        }

//        Map<Integer, BdFile> fileMap = bdFileService.findMapByIds(new ArrayList<>(headIds));

        String msg = "";
        String msgMoney = "";
        for (Map.Entry item : msgMap.entrySet()) {
//            Integer HeadId = MapUtils.getInteger(msgHeadUrlMap, item.getKey(), 0);
//            String headStr = HEAD;
//            if(HeadId != 0) {
//                BdFile bdFile = (BdFile) MapUtils.getObject(fileMap, headIds, null);
//                headStr = bdFile.getUrl();
//            }
            Integer payablePrice = NumberUtils.toInt(item.getValue());
            if (payablePrice == 0) continue;
            msg += item.getKey() + " \\n";
            msgMoney += item.getKey() + " 的合作，产品订单金额( " + payablePrice / 100 + " ￥) \\n";   //这个是保留金额的
            //msg += "{\"title\": \" " + item.getKey() + " 的合作，产品订单金额( " + payablePrice / 100 + " \", \"messageURL\": \"www.baidu.com\", \"picURL\": \" "+headStr+" \" },";
        }

        int type = MapUtils.getInteger(condition, "type", 0);
        String textMsg = "";

        if (type == 0) {  //不带钱的,公司群
            if (StringUtils.isNotBlank(msg)) {
//            msg = msg.substring(0,msg.length() - 1);
                textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"每日新单\",\"text\": \" **每日新单** \\n > ![screenshot](" + ADVERTISEMENT + " )\\n " + msg + " > ###### " + DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss") + " 发布 [公司宝](http://www.gongsibao.com/) \\n \"}}";
                if (StringUtils.isNotBlank(msg)) {
                    final String finalBeginTime = beginTime;
                    final String finalMsg = msg.substring(0, msg.length() - 1);
                    try {
                        redisAliyunService.put("everyday_" + finalBeginTime, finalMsg, ConstantCache.TIME_ONE_DAY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (StringUtils.isNotBlank(msgMoney)) {
                textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"每日新单\",\"text\": \" **每日新单** \\n > ![screenshot](" + ADVERTISEMENT + " )\\n " + msgMoney + " > ###### " + DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss") + " 发布 [公司宝](http://www.gongsibao.com/) \\n \"}}";
            }
        }

        if (StringUtils.isNotBlank(textMsg)) {
            postToRobot(textMsg, token);
        }

        return 0;
    }

    /*
    付款时候发送给大群的
     */
    @Override
    public void postIndividualMsg(Integer pkid) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("type", 0); //0播报不带钱 1是带钱的
//        condition.put("token", GSB_TOKEN);

        /*
        注意  现在是全群播报
         */
        condition.put("token", GSB_FOLLOW_TOKEN);
        condition.put("orderId", pkid);

        //带排名,实时
//        autoPostMsgInGroupUncall(condition);

        autoPostMsgInGroupCallSomeone(condition);

    }

    /*
    发送至大群，带排行榜排名(下单即发送，实时播报使用)
    样例: 祝贺 X分公司 XXX(全公司年度排名: XX) 签约 内资公司变更（法人）.
     */
    private int autoPostMsgInGroupUncall(Map<String, Object> condition) {
        String token = MapUtils.getString(condition, "token", null);
        if (StringUtils.isBlank(token)) {
            return -2;
        }

        Integer orderId = MapUtils.getInteger(condition, "orderId", 0);
        if (orderId == 0) return -2;

        List<OrderProd> prodList = soOrderProdService.findOrderProdByOrderIds(Arrays.asList(orderId));

        if (CollectionUtils.isEmpty(prodList)) {
            return -1;
        }

        Map<Integer, String> officeMap = new HashMap<>();
        List<Dict> orgList = bdDictService.byType(493);

        for (Dict item : orgList) {
            if (item.getId() > 49300 && item.getId() < 49316) {   //分公司office统计只取这个区间的id
                officeMap.put(item.getId(), item.getName());
            }
        }

        List<User> ucUserList = ucUserService.findByRole("YWY", 1);
        if (CollectionUtils.isEmpty(ucUserList)) {
            return -1;
        }

        Set<Integer> userIds = new HashSet<>();
        for (User item : ucUserList) {
            if (NumberUtils.toInt(item.getOfficeId(), 0) != 0) {
                userIds.add(item.getId());
            }
        }

        List<Integer> prodIds = new ArrayList<>();
        for (OrderProd item : prodList) {
            prodIds.add(item.getId());
        }

        Map<Integer, User> ucUserMap = ucUserService.findMapByIds(new ArrayList<>(userIds));

        Map<Integer, List<OrderProdUserMap>> prodUserMap = soOrderProdUserMapService.findYWYByOrderProdIds(prodIds);
        Map<Integer, Integer> prodPayableMap = soOrderProdService.findPayablePrice(prodIds);

        //String screenStr = screenDatavService.getObjectById(20);
        String screenStr = "";
        Map<Integer, Integer> userRank = new HashMap<>();
        if (StringUtils.isNotBlank(screenStr)) {
            List<StatBillboard> res = JsonUtils.jsonToList(screenStr, StatBillboard.class);
            if (CollectionUtils.isNotEmpty(res)) {
                for (StatBillboard item : res) {
                    if (StringUtils.isNotBlank(item.getUserIdStr())) {
                        userRank.put(item.getUserId(), res.indexOf(item) + 1);
                    }
                }
            }
        }

        Map<String, Integer> msgMap = new HashMap<>();

        for (OrderProd item : prodList) {
            List<OrderProdUserMap> prodUserMaps = (ArrayList) MapUtils.getObject(prodUserMap, item.getId(), new ArrayList<>());
            if (CollectionUtils.isEmpty(prodUserMaps)) continue;


            User user = (User) MapUtils.getObject(ucUserMap, prodUserMaps.get(0).getPrincipalId(), null);
            if (user == null) continue;
            if (user.getOfficeId() == 0) continue;


            String officeName = MapUtils.getString(officeMap, user.getOfficeId(), null);
            if (officeName == null) continue;


            Integer payablePrice = MapUtils.getInteger(prodPayableMap, item.getId(), 0);
            if (payablePrice == 0) continue;

            Integer rank = MapUtils.getInteger(userRank, user.getId(), 0);
            if (rank == 0) continue;

            String title = "> - 祝贺" + officeName + "" + user.getName() + "(全公司年度排名: " + rank + ") 签约 " + item.getProductName() + ".";
            Integer statPrice = MapUtils.getInteger(msgMap, title, 0);

            if (statPrice == 0) {
                msgMap.put(title, payablePrice);
                continue;
            }

            msgMap.replace(title, statPrice + payablePrice);
        }

        String msg = "";
        String msgMoney = "";
        for (Map.Entry item : msgMap.entrySet()) {

            Integer payablePrice = NumberUtils.toInt(item.getValue());
            if (payablePrice == 0) continue;
            msg += item.getKey() + " \\n";
            msgMoney += item.getKey() + " 的合作，产品订单金额( " + payablePrice / 100 + " ￥) \\n";   //这个是保留金额的
        }

        int type = MapUtils.getInteger(condition, "type", 0);
        if (type == 0) {  //不带钱的,公司群
            if (StringUtils.isNotBlank(msg)) {
//            msg = msg.substring(0,msg.length() - 1);
                String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"每日新单\",\"text\": \" **每日新单** \\n > ![screenshot](" + ADVERTISEMENT + " )\\n " + msg + " > ###### " + DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss") + " 发布 [公司宝](http://www.gongsibao.com/) \\n \"}}";

                postToRobot(textMsg, token);
            }
        } else {
            if (StringUtils.isNotBlank(msgMoney)) {
                String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"每日新单\",\"text\": \" **每日新单** \\n > ![screenshot](" + ADVERTISEMENT + " )\\n " + msgMoney + " > ###### " + DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss") + " 发布 [公司宝](http://www.gongsibao.com/) \\n \"}}";
                postToRobot(textMsg, token);
            }
        }

        return 0;
    }

    /*
    发送至大群，不带排名(下单即发送，实时播报使用)
    样例：@XXX，客户XXX已经下单，下单内容为：XXX订单名称  请及时跟进
     */
    private int autoPostMsgInGroupCallSomeone(Map<String, Object> condition) {
        String token = MapUtils.getString(condition, "token", null);
        if (StringUtils.isBlank(token)) {
            return -2;
        }

        Integer orderId = MapUtils.getInteger(condition, "orderId", 0);

        SoOrder soOrder = soOrderService.getByOrderId(orderId);
        if (soOrder == null) return -3;

        //屏蔽曹玉玺
        if (soOrder.getAccountId() == 95608) return -10;

        if (orderId == 0) return -2;

        List<OrderProd> prodList = soOrderProdService.findOrderProdByOrderIds(Arrays.asList(orderId));

        if (CollectionUtils.isEmpty(prodList)) {
            return -1;
        }

        Map<Integer, String> officeMap = new HashMap<>();
        List<Dict> orgList = bdDictService.byType(493);

        for (Dict item : orgList) {
            if (item.getId() > 49300 && item.getId() < 49316) {   //分公司office统计只取这个区间的id
                officeMap.put(item.getId(), item.getName());
            }
        }

        Map<Integer, NCustomer> crmCustomerMap = crmCustomerService.findMapByAccountIds(Arrays.asList(soOrder.getAccountId()));

        List<User> ucUserList = ucUserService.findByRole("YWY", 1);
        if (CollectionUtils.isEmpty(ucUserList)) {
            return -1;
        }

        Set<Integer> userIds = new HashSet<>();
        for (User item : ucUserList) {
            if (NumberUtils.toInt(item.getOfficeId(), 0) != 0) {
                userIds.add(item.getId());
            }
        }

        List<Integer> prodIds = new ArrayList<>();
        for (OrderProd item : prodList) {
            prodIds.add(item.getId());
        }

        Map<Integer, List<OrderProdUserMap>> prodUserMap = soOrderProdUserMapService.findYWYByOrderProdIds(prodIds);

        String msg = null;
        String ywyMobile = null;
        String atMobile = null;
        Set<String> ywyNames = new HashSet<>();
        for (OrderProd item : prodList) {
            List<OrderProdUserMap> prodUserMaps = (ArrayList) MapUtils.getObject(prodUserMap, item.getId(), new ArrayList<>());
            if (CollectionUtils.isEmpty(prodUserMaps)) continue;

            //User user = prodUserMaps.get(0).getPrincipal();+
            User user = new User();
            if (user == null) continue;

            Dict bdDict = bdDictService.byId(user.getOfficeId());
            if (bdDict == null) continue;

            if (StringUtils.isBlank(bdDict.getRemark()) || NumberUtils.toInt(bdDict.getRemark(), 0) == 0) continue;
            //这块读取的officeId,在Bd_dict中Remark配置的分公司主管
            User bossUser = ucUserService.byId(NumberUtils.toInt(bdDict.getRemark(), 0));

            if (bossUser == null) continue;

            if (StringUtils.isBlank(msg)) {
                msg = item.getProductName();
            } else {
                msg += "," + item.getProductName();
            }

            if (StringUtils.isBlank(ywyMobile)) {
                ywyMobile = "\"" + user.getMobilePhone() + "\",\"" + bossUser.getMobilePhone() + "\"";
                atMobile = "@" + user.getMobilePhone() + "@" + bossUser.getMobilePhone();
                ywyNames.add(user.getName());
                ywyNames.add(bossUser.getName());
            } else {
                if (atMobile.indexOf(user.getMobilePhone()) == -1) {
                    ywyMobile += "," + "\"" + user.getMobilePhone() + "\"";
                    atMobile += "@" + user.getMobilePhone();
                    ywyNames.add(user.getName());
                }

                if (atMobile.indexOf(user.getMobilePhone()) == -1) {
                    ywyMobile += "," + "\"" + user.getMobilePhone() + "\",\"" + bossUser.getMobilePhone() + "\"";
                    atMobile += "@" + user.getMobilePhone() + "@" + bossUser.getMobilePhone();
                    ywyNames.add(user.getName());
                    ywyNames.add(bossUser.getName());
                }
            }
        }

        if (StringUtils.isBlank(msg)) {
            return -5;
        }

        if (StringUtils.isBlank(ywyMobile)) {
            return -6;
        }

        NCustomer crm = (NCustomer) MapUtils.getObject(crmCustomerMap, soOrder.getAccountId(), null);

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

        String combinationStr = "客户 **" + customerName + "** 已经下单! 下单内容为：" + msg + ", 请及时跟进";

        String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"新单通知\",\"text\": \" **新单通知** " + atMobile + "\\n > " + combinationStr + "\\n > ###### " + DateUtils.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss") + " 发布 [公司宝](http://www.gongsibao.com/) \\n \"}, \"at\": {\"atMobiles\":[" + ywyMobile + "],\"isAtAll\":false}}";

        postToRobot(textMsg, token);

        String readMsg = "新单通知：" + StringUtils.join(ywyNames, ",") + "，您的客户" + customerName + "已经下单! 下单内容为：" + msg + ", 请及时跟进";


        try {
            boolean bool = redisAliyunService.put("new_order_" + orderId, readMsg, ConstantCache.TIME_ONE_DAY);
            //log.info("******************************** robot new_order=" + orderId + ", redis put=" + bool + ", reaadMsg=" + readMsg );
        } catch (Exception e) {
            e.printStackTrace();
        }

        //log.info("******************************** robot new_order=" + orderId + ", reaadMsg=" + readMsg);
        return 0;
    }

    //markdown格式
    public void postToRobot(String msg, String token) {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(token);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        StringEntity se = new StringEntity(msg, "utf-8");
        httppost.setEntity(se);

        try {
            httpclient.execute(httppost);
        } catch (Exception e) {

        }
    }

    //feedcard格式
    public void postToRobot2(String msg, String token) {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(token);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"feedCard\", \"feedCard\": {\"links\": [ " + msg + " ]}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            //httpclient.execute(httppost);
        } catch (Exception e) {

        }
    }
}
