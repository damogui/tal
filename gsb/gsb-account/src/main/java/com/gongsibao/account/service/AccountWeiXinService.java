package com.gongsibao.account.service;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import com.gongsibao.entity.trade.OrderProd;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Service
public class AccountWeiXinService extends PersistableService<AccountWeiXin> implements IAccountWeiXinService {
    IFansService fansService = ServiceFactory.create(IFansService.class);
    ICustomService customService = ServiceFactory.create(ICustomService.class);
    IAccountService accountService = ServiceFactory.create(IAccountService.class);
    /**
     * 微信回调url前缀
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?from=weixin&appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
    public static final String ORDER_CHANGE_STATE_MSG = "您购买的服务\"%s\" 办理进度变更为 \"%s\" \n\r" + "<a href=\"%s\">点此查看详情>></a>";
    public static final String OID = "gh_29f5a8b8da16";

    public AccountWeiXinService() {
        super();
        this.type = AccountWeiXin.class;
    }

    /**
     * @param accountId, openId
     * @return void
     * @Description:TODO 绑定手机号
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/16 13:44
     */
    @Override
    public Boolean bandMobile(int accountId, String openId) {
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("wx_pa_fans");
            updateBuilder.set("user_id", accountId);
            updateBuilder.where("openid='" + openId + "'");
        }
        String cmdText = updateBuilder.toSQL();
        return this.pm.executeNonQuery(cmdText, null) > 0;
    }

    @Override
    public Account queryByOpenId(String openId) {
        Fans fans=this.queryFansByOpenId(openId);
        Oql oql = new Oql();
        {
            oql.setType(Account.class);
            oql.setSelects("*");
            oql.setFilter("pkid=?");
            oql.getParameters().add("pkid", fans.getUserId(), Types.INTEGER);
        }
        return accountService.queryFirst(oql);
    }

    @Override
    public Fans queryFansByOpenId(String openId) {
        Oql oql = new Oql();
        {
            oql.setType(Fans.class);
            oql.setSelects("Fans.{nickname,sex,headImgUrl,subscribeDate}");
            oql.setFilter("openId=?");
            oql.getParameters().add("openId", openId, Types.VARCHAR);
        }
        return fansService.queryFirst(oql);
    }
    /**
     * @Description:TODO 新增粉丝
     * @param  openId
     * @return org.netsharp.wx.pa.entity.Fans
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 11:43
     */
    @Override
    public Fans createFans(String openId) {
        Fans fans=new Fans();{
            fans.toNew();
            fans.setOpenId(openId);
        }
        return fansService.save(fans);
    }

    @Override
    public Fans queryFansByUserId(Integer userId) {
        Oql oql = new Oql();
        {
            oql.setType(Fans.class);
            oql.setSelects("Fans.*");
            oql.setFilter("user_id=?");
            oql.getParameters().add("user_id", userId, Types.INTEGER);
        }
        return fansService.queryFirst(oql);
    }

    /**
     * @param mobile, orderPorudctId
     * @return void
     * @Description:TODO
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/18 16:57
     */
    @Override
    public void pushOrderStateMsg(String mobile, Integer orderPorudctId) {
        String sql = "select * from so_order_prod where pkid=? ";
        QueryParameters qps = new QueryParameters();
        qps.add("@pkid", orderPorudctId, Types.INTEGER);
        ResultSet rs = this.pm.executeReader(sql, qps);
        String sqlTrace = "select * from so_order_prod_trace where order_prod_id=? order by add_time desc limit 1";
        QueryParameters qpsTrace = new QueryParameters();
        qpsTrace.add("@order_prod_id", orderPorudctId, Types.INTEGER);
        ResultSet rsTrace = this.pm.executeReader(sqlTrace, qpsTrace);
        String proName = null;
        String proTrace = null;
        try {
            if (rs.next()) {
                proName = rs.getString("product_name");
            }
            if (rsTrace.next()) {
                proTrace = rsTrace.getString("info");
            }
            if (null != proName && null != proTrace) {
                IPublicAccountService publicAccountService = ServiceFactory.create(IPublicAccountService.class);
                //取公众号配置
                PublicAccount weixinConfig = publicAccountService.byOriginalId(OID);
                //取用户信息
                Account account = accountService.byMobile(mobile);
                //取微信用户openid
                Fans accountWeiXin=this.queryFansByUserId(account.getId());
                //拼接消息内容
                String redirectUrl = UrlHelper.encode("http://" + weixinConfig.getHost() + UrlHelper.join("/index.html#/mine/order", "originalId=" + OID));
                String url = SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX;
                url = String.format(url, weixinConfig.getAppId(), redirectUrl, "snsapi_base", "123");
                String content = String.format(ORDER_CHANGE_STATE_MSG, proName, proTrace, url);
                //发送消息
                customService.sendTextMessage(content, accountWeiXin.getOpenId(), OID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}