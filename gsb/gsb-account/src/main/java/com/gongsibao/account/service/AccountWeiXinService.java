package com.gongsibao.account.service;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;

import java.sql.Types;

@Service
public class AccountWeiXinService extends PersistableService<AccountWeiXin> implements IAccountWeiXinService{
	IFansService fansService= ServiceFactory.create(IFansService.class);
	public AccountWeiXinService() {
		super();
		this.type = AccountWeiXin.class;
	}
	/**
	 * @Description:TODO 绑定手机号
	 * @param   accountId, openId
	 * @return void
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/16 13:44
	 */
	@Override
	public Boolean bandMobile(int accountId, String openId) {
		UpdateBuilder updateBuilder = UpdateBuilder.getInstance ();
		{
			updateBuilder.update ("uc_account_wx");
			updateBuilder.set ("account_id", accountId);
			updateBuilder.where ("open_id='" + openId+"'");
		}
		String cmdText = updateBuilder.toSQL ();
		return this.pm.executeNonQuery (cmdText, null) > 0;
	}

	@Override
	public AccountWeiXin queryByOpenId(String openId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("AccountWeiXin.*");
			oql.setFilter("openId=?");
			oql.getParameters().add("openId", openId, Types.VARCHAR);
		}
		return this.queryFirst(oql);
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

	@Override
	public AccountWeiXin queryByAccountId(String accountId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("AccountWeiXin.*");
			oql.setFilter("accountId=?");
			oql.getParameters().add("accountId", accountId, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}
}