package com.netsharp.rest.service.account;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.crm.base.ICompanyIntentionService;
import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.dic.CapitalType;
import com.gongsibao.entity.crm.dic.CompanyOrgType;
import com.gongsibao.entity.crm.dic.CompanyType;
import com.gongsibao.entity.crm.dic.RegisterCapitalType;
import com.netsharp.rest.base.account.IAccountService;
import com.netsharp.rest.utils.NumberUtils;
import com.netsharp.rest.utils.StringUtils;
import com.netsharp.rest.dto.user.AccountValidateDTO;
import com.netsharp.rest.dto.user.LoginDTO;
import com.gongsibao.trade.base.ICustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.entity.Fans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    ICompanyIntentionService companyIntentionService = ServiceFactory.create(ICompanyIntentionService.class);

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
    public Result<Account> pkLogin(LoginDTO dto) {
        String openId = dto.getOpenId();
        String companyName = dto.getCompanyName();
        String mobile = dto.getMobile();
        int accountId = 0;
        int fansId = 0;

        if (StringUtils.isNotBlank(openId)) {
            Fans fans = queryFansByOpenId(openId);
            accountId = NumberUtils.toInt(fans.getUserId());
            fansId = fans.getId();
        }

        // 处理Account和Customer信息
        Account account = null;
        if (accountId > 0) {
            account = baseAccountService.getById(accountId);
        } else {
            account = baseAccountService.byMobile(mobile);
            if (null == account) {
                account = new Account();
                {
                    account.toNew();
                    account.setFansId(fansId);
                    account.setName("WX" + mobile);
                    account.setPasswd("");
                    account.setTicket(UUID.randomUUID().toString());
                    account.setEmail("");
                    account.setMobilePhone(mobile);
                    account.setTelephone("");
                    account.setHeadThumbFileId(0);
                    account.setRealName("");
                    account.setSourceClientId(dto.getAccountSourceClientId()); // 微信10301
                    account.setIdentityCard("");
                    account.setCompanyId(0);
                    if (fansId > 0) {
                        account.setIsWeiXin("1");
                    }
                }
                // 创建Account和Customer
                saveWithCustomer(account, dto.getCustomerSourceId());// 4110218 微信
            }
            accountId = account.getId();
        }

        // 更新粉丝id
        if (fansId > 0 && NumberUtils.toInt(account.getFansId()) != fansId) {
            baseAccountService.updateFansId(accountId, fansId);
        }

        // 重新绑定openId关联的用户id
        if (StringUtils.isNotBlank(openId)) {
            accountWeiXinService.unBandUserId(accountId);
            accountWeiXinService.bandMobile(accountId, openId);
        }

        if (StringUtils.isNotBlank(companyName)) {
            List<AccountCompany> accountCompanyList = accountCompanyService.findByAccount(account.getId(), 1);
            Set<String> accountNames = new HashSet<>();
            if (CollectionUtils.isNotEmpty(accountCompanyList)) {
                for (AccountCompany accountCompany : accountCompanyList) {
                    accountNames.add(accountCompany.getCompanyName());
                }
            }

            if (!accountNames.contains(companyName)) {
                CompanyIntention crmCompany = getAndSaveCompany(dto, companyName);

                AccountCompany accountCompany = new AccountCompany();
                {
                    accountCompany.toNew();
                    accountCompany.setAccountId(account.getId());
                    accountCompany.setCompanyName(companyName);
                    accountCompany.setCrmCompanyId(crmCompany.getId());
                    accountCompany.setMobile(mobile);
                    accountCompany.setStatus(1);
                    accountCompany.setInUse(1);
                }

                accountCompanyService.save(accountCompany);
            }
        }
        Result<Account> result = new Result<>();
        result.setObj(account);
        return result;
    }

    private CompanyIntention getAndSaveCompany(LoginDTO dto, String companyName) {
        CompanyIntention crmCompany = companyIntentionService.getByCompanyName(companyName);
        if (null == crmCompany) {
            crmCompany = new CompanyIntention();
            {
                crmCompany.toNew();
                crmCompany.setName(companyName);
                crmCompany.setOrgType(CompanyOrgType.TYPE_44101);
                crmCompany.setOptionalName("");
                crmCompany.setFullName(companyName);
                crmCompany.setCompanyName(companyName);
                crmCompany.setCompanyType(CompanyType.CompanyType_0);
                crmCompany.setCode("");
                crmCompany.setOrderProdId(0);
                crmCompany.setOrderContactName("");
                crmCompany.setOrderContactMobile(dto.getMobile());
                crmCompany.setOrderContactEmail("");
                crmCompany.setSetupStatus(true);
                crmCompany.setCityId(0);
                crmCompany.setIsSelfAddress(1);
                crmCompany.setAddress("");
                crmCompany.setCapitalType(CapitalType.CapitalType_1);
                crmCompany.setRegisterCapital(0);
                crmCompany.setRegisterCapitalType(RegisterCapitalType.CompanyType_0);
                crmCompany.setIsSelfCapital(true);
                crmCompany.setIsExpress(false);
                crmCompany.setIsNameVerify(false);
                crmCompany.setNameVerifyFileId(0);
                crmCompany.setVerifyNo("");
                crmCompany.setBusinessTypeId(0);
                crmCompany.setOwnedBusinessType("");
                crmCompany.setBusinessScopeSupply("");
                crmCompany.setHasDirectorate(false);
                crmCompany.setStreet("");
                crmCompany.setPoliceStation("");
                crmCompany.setIsDelete(false);
                crmCompany.setArea("");
                crmCompany.setOrganizationNo("");
                crmCompany.setLegalPerson("");
                crmCompany.setSetUpDate(null);
                crmCompany.setPaidYears("");
                crmCompany.setOperatingLife("");
                crmCompany.setTelephone("");
                crmCompany.setOrderContractQq("");
                crmCompany.setOrderContractWechat("");
                crmCompany.setResidenceType(0);
                crmCompany.setHouseOwner("");
                crmCompany.setHouseSpace(0);
                crmCompany.setLogoUrl("");
                crmCompany.setNationTax("");
                crmCompany.setLocalTax("");
                crmCompany.setRemark("微信");
                crmCompany.setDescription("");
                crmCompany.setFinishTime(null);
            }

            crmCompany = companyIntentionService.save(crmCompany);
        }
        return crmCompany;
    }

    @Override
    public Account saveWithCustomer(Account account, Integer customerSourceId) {
        // 保存会员
        account = save(account);
        customerService.saveByAccount(account, customerSourceId);
        return account;
    }

    @Override
    public Fans queryFansByOpenId(String openId) {
        Fans weiXin = accountWeiXinService.queryFansByOpenId(openId);
        if (null == weiXin) {
            // 创建微信账号
            weiXin = accountWeiXinService.createFans(openId);
        }
        return weiXin;
    }
}
