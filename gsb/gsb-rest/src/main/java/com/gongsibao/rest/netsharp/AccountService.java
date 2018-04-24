package com.gongsibao.rest.netsharp;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.dic.ConsultWay;
import com.gongsibao.entity.crm.dic.FollowStatus;
import com.gongsibao.entity.crm.dic.Important;
import com.gongsibao.entity.crm.dic.Sex;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.rest.netsharp.base.IAccountService;
import com.gongsibao.rest.web.common.util.RegexUtils;
import com.gongsibao.rest.web.common.util.StringUtils;
import com.gongsibao.rest.web.dto.user.AccountValidateDTO;
import com.gongsibao.trade.base.ICustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.entity.Fans;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ClassName: AccountService
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/24 17:36
 */
@Service
public class AccountService extends PersistableService<Account> implements IAccountService {
    com.gongsibao.account.base.IAccountService baseAccountService = ServiceFactory.create(com.gongsibao.account.base.IAccountService.class);

    // 会员绑定公司服务
    IAccountCompanyService accountCompanyService = ServiceFactory.create(IAccountCompanyService.class);

    ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

    IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    @Override
    public AccountValidateDTO validAccountByOpenId(String openId) {
        AccountValidateDTO dto = new AccountValidateDTO();
        if (StringUtils.isBlank(openId)) {
            return dto;
        }

        dto.setOpenId(openId);
        Fans weiXin = queryFansByOpenId(openId);

        if (null != weiXin.getUserId()) {
            // 查询关联会员
            Account account = baseAccountService.getById(weiXin.getUserId());
            if (null != account) {
                dto.setMobile(account.getMobilePhone());
                // 查会员关联企业
                List<AccountCompany> companyList = accountCompanyService.findByAccount(account.getId(), 1);
                if (CollectionUtils.isNotEmpty(companyList)) {
                    // TODO 暂时取第一个公司
                    String companyName = companyList.get(0).getCompanyName();
                    dto.setCompanyName(companyName);
                }
            }
        }
        return dto;
    }

    @Override
    public Result<Account> pkLogin(AccountValidateDTO dto) {
        String openId = dto.getOpenId();
        if (StringUtils.isNotBlank(openId)) {

            AccountValidateDTO validateDTO = validAccountByOpenId(openId);
            if (StringUtils.isBlank(validateDTO.getMobile())) {
                if (StringUtils.isBlank(dto.getMobile())) {
                    return new Result<>(ResponseStatus.FAILED, "请填写手机号");
                }

                if (RegexUtils.isNotPhone(dto.getMobile())) {
                    return new Result<>(ResponseStatus.FAILED, "手机号码格式错误");
                }
            }

            if (StringUtils.isBlank(validateDTO.getCompanyName())) {

            }

            // 获取粉丝
            Fans weiXin = queryFansByOpenId(openId);
            // TODO 写了一半，回头接着写


            // 获取粉丝关联用户
            Integer userId = weiXin.getUserId();
            if (null == userId) {


                Account account = baseAccountService.byMobile(dto.getMobile());
                if (null == account) {
                    // 创建账户
                    account = new Account();
                    {
                        account.toNew();
                        account.setName("WX" + dto.getMobile());
                        account.setPasswd("");
                        account.setTicket(UUID.randomUUID().toString());
                        account.setEmail("");
                        account.setMobilePhone(dto.getMobile());
                        account.setTelephone("");
                        account.setHeadThumbFileId(0);
                        account.setRealName("");
                        account.setSourceClientId(10301); // 微信
                        account.setIdentityCard("");
                        account.setCompanyId(0);

                        saveWithCustomer(account, 4110218);
                    }

                }
            }
        } else {

        }
        return null;
    }

    @Override
    public Account saveWithCustomer(Account account, Integer customerSourceId) {
        // 保存会员
        account = save(account);

        Customer customer = new Customer();
        {
            customer.toNew();
            customer.setAccountId(account.getId());
            customer.setRealName(account.getRealName());
            customer.setMobile(account.getMobilePhone());
            customer.setEmail(account.getEmail());
            customer.setSex(Sex.SECRECY);
            customer.setTelephone("");
            customer.setQq("");
            customer.setWeixin("");

            customer.setAddr("");
            customer.setCityId(0);
            customer.setFollowUserId(0);
            customer.setFollowStatus(FollowStatus.FOLLOW_STATUS_1);
            customer.setUnvalidRemark("");

            customer.setLastFollowTime(new Date());
            customer.setBackNum(0);
            customer.setCustomerSourceId(customerSourceId);
            customer.setConsultWay(ConsultWay.CONSULT_WAY_4219);
            customer.setImportant(Important.COMMON);

            customer.setIntroducerUserId(0);
            customer.setIntroducerId(0);
            customer.setRemark("");
            customer.setCreatorId(0);
            customer.setUpdatorId(0);
        }

        // 保存客户
        customerService.save(customer);
        return account;
    }

    private Fans queryFansByOpenId(String openId) {
        Fans weiXin = accountWeiXinService.queryFansByOpenId(openId);
        if (null == weiXin) {
            // 创建微信账号
            weiXin = accountWeiXinService.createFans(openId);
        }
        return weiXin;
    }


}
