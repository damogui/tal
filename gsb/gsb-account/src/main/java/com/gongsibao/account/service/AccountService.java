package com.gongsibao.account.service;

import java.sql.Types;
import java.util.Date;
import java.util.UUID;

import com.gongsibao.account.base.IAccountWeiXinService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import org.netsharp.util.sqlbuilder.UpdateBuilder;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;

@Service
public class AccountService extends PersistableService<Account> implements IAccountService {

	ICustomService customService = ServiceFactory.create(ICustomService.class);
	IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);
	public AccountService() {
		super();
		this.type = Account.class;
	}

	@Override
	public Boolean hasMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setFilter("mobilePhone=?");
			oql.getParameters().add("@mobile", mobile, Types.VARCHAR);
		}
		return this.queryCount(oql) > 0;
	}

	@Override
	public Account byMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("Account.*,company.{id,companyName}");
			oql.setFilter("mobilePhone=?");
			oql.getParameters().add("@mobile", mobile, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}

	@Override
	public Integer updateTicket(Integer accountPkid, String ticket) {
		UpdateBuilder updateBuilder = UpdateBuilder.getInstance ();
		{
			updateBuilder.update ("uc_account");
			updateBuilder.set ("ticket", ticket);
			updateBuilder.where ("pkid=" + accountPkid);
		}
		String cmdText = updateBuilder.toSQL ();
		return this.pm.executeNonQuery (cmdText, null) ;
	}

	@Override
	public void updateAccount(String mobile, String openId) {
		Account accountOld = this.byMobile(mobile);
		Fans fans=accountWeiXinService.queryFansByOpenId(openId);
		if (null == accountOld) {
			//更新uc_account 新增一条
			Account account = new Account();
			{
				account.toNew();
				account.setPasswd("");
				account.setCreateTime(new Date());
				account.setMobilePhone(mobile);
				account.setTicket(UUID.randomUUID().toString());
				account.setEmail("");
				account.setIdentityCard("");
				account.setTelephone("");
				account.setRealName("");
				account.setCreateTime(new Date());
				account.setIsBbk("");
				account.setName("");
				if(fans!=null){
					account.setIsWeiXin("1");
					account.setFansId(fans.getId());
				}else{
					account.setIsWeiXin("0");
					account.setFansId(0);
				}
				//来源微信
				account.setSourceClientId(1036);
				account.setHeadThumbFileId(0);
			}
			Account result = this.save(account);
			//更新uc_account_weixin 表 更新 account_id
			accountWeiXinService.bandMobile(result.getId(), openId);
		} else {
			Fans oldFans=accountWeiXinService.queryFansByUserId(accountOld.getId());
			//判断是否绑定过手机号
			if(null!=oldFans&&oldFans.getUserId()!=null){
				//解绑
				oldFans.setUserId(null);
				oldFans.toPersist();
				IFansService fansService=ServiceFactory.create(IFansService.class);
				fansService.updateFans(oldFans);
			}
			if(fans!=null){
				accountOld.setIsWeiXin("1");
				accountOld.setFansId(fans.getId());
			}else{
				accountOld.setIsWeiXin("0");
				accountOld.setFansId(0);
			}
			accountOld.toPersist();
			this.save(accountOld);
			//更新uc_account_weixin 表 更新 account_id
			accountWeiXinService.bandMobile(accountOld.getId(), openId);
		}
	}
}