package com.gongsibao.account.base;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import com.gongsibao.entity.acount.AccountWxMsg;
import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.Fans;

public interface IAccountWeiXinService extends IPersistableService<AccountWeiXin> {
	Boolean bandMobile(int accountId,String openId);
	Boolean unBandUserId(int accountId);
	Account queryByOpenId(String openId);
	Fans queryFansByUserId(Integer userId);
	Fans queryFansByOpenId(String openId);
	Fans createFans(String openId);
	/**
	 * @Description:TODO 订单状态变化推送微信消息指定公众号
	 * @param   mobile 手机号   orderProductId 对应so_order_prod.`pkid`
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/18 16:47
	 */
	void pushOrderStateMsg(String originalId,String mobile,Integer orderPorudctId );
	/**
	 * @Description:TODO 订单状态变化微信推送消息
	 * @param   mobile 客户手机号  orderProductId 对应so_order_prod 表 `pkid`
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/26 15:33
	 */
	void pushOrderStateMsg(String mobile, Integer orderPorudctId);
	/**
	 * @Description:TODO 订单创建成功 审核通过后发送微信消息
	 * @param   mobile 客户手机号  orderProductId 对应so_order  表`pkid`
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/26 15:36
	 */
	void saveOrderMsg(String mobile, Integer orderPorudctId);
	void pushTextMsg(Integer accountId, String first, String keyword1, String keyword2, String date,String url, String remark, AccountWxMsg tmpId);
	void pushTextMsgByOriginalId(String originalId,Integer accountId, String first, String keyword1, String keyword2, String date,String url, String remark, AccountWxMsg tmpId);
	/**
	 * @Description:TODO
	 * @param   content 发送内容 url 跳转页面 accountId 用户id uc_account -pkid
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/27 10:15
	 */
	void sendLinkMsg(String title,String content,String url,int accountId);
	/**
	 * @Description:TODO Oauth 方式必须与公众号下域名下
	 * @param
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/27 11:03
	 */
	void pushTextMsgOauth(Integer accountId,
						  String first,
						  String keyword1,
						  String keyword2,
						  String date,
						  String url,
						  String remark,
						  AccountWxMsg tmpId);
}