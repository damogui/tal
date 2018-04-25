package com.gongsibao.rest.netsharp.base;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.web.dto.user.AccountValidateDTO;
import com.gongsibao.rest.web.dto.user.LoginDTO;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.wx.pa.entity.Fans;

/**
 * ClassName: IAccountService
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO rest工程内重写netsharp account服务，保证事务
 * @date 2018/4/24 17:35
 */
public interface IAccountService extends IPersistableService<Account> {

    /* *
     * @Description:TODO 验证openId关联会员、会员公司等项
     * @param  openId
     * @return com.gongsibao.rest.web.dto.user.AccountValidateDTO
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/24
     */
    AccountValidateDTO validAccountByOpenId(String openId);

    /**
     * @Description:TODO 企业PK登录
     * @param  dto
     * @return com.gongsibao.entity.Result<com.gongsibao.entity.acount.Account>
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/24
     */
    @Transaction
    Result<Account> pkLogin(LoginDTO dto);

    /**
     * @Description:TODO 同时保存会员&Crm客户
     * @param  account,customerSourceId
     * @return com.gongsibao.entity.acount.Account
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/24
     */
    @Transaction
    Account saveWithCustomer(Account account, Integer customerSourceId);

    Fans queryFansByOpenId(String openId);
}
