package com.gongsibao.rest.netsharp;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.crm.base.ICompanyIntentionService;
import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.rest.netsharp.base.IAccountService;
import com.gongsibao.rest.web.common.constant.ConstantKey;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.util.RedisClient;
import com.gongsibao.rest.web.common.util.RegexUtils;
import com.gongsibao.rest.web.common.util.StringUtils;
import com.gongsibao.rest.web.dto.user.AccountValidateDTO;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.service.TaurusApiService;
import com.gongsibao.trade.base.ICustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.entity.Fans;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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

    @Autowired
    private RedisClient redisClient;

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
        String companyName = dto.getCompanyName();
        String mobile = dto.getMobile();
        int accountId = 0;

        // 接口兼容有openId和无openId的情况
        if (StringUtils.isNotBlank(openId)) {
            // 验证openId关联的用户信息
            AccountValidateDTO validateDTO = validAccountByOpenId(openId);

            // 当前openId是否需要手机号码
            if (StringUtils.isBlank(validateDTO.getMobile())) {
                if (StringUtils.isBlank(mobile)) {
                    return new Result<>(ResponseStatus.FAILED, "请填写手机号");
                }
                if (RegexUtils.isNotPhone(mobile)) {
                    return new Result<>(ResponseStatus.FAILED, "手机号码格式错误");
                }
            }

            // 获取粉丝
            Fans weiXin = queryFansByOpenId(openId);
            // 获取粉丝关联用户
            accountId = NumberUtils.toInt(weiXin.getUserId());
        } else {
            // 无openId时，手机号必须！
            if (StringUtils.isBlank(mobile)) {
                return new Result<>(ResponseStatus.FAILED, "请填写手机号");
            }

            if (RegexUtils.isNotPhone(mobile)) {
                return new Result<>(ResponseStatus.FAILED, "手机号码格式错误");
            }
        }

        // 公司名称必须, 如果以后公司名称，走不到这一步
        if (StringUtils.isBlank(companyName)) {
            return new Result<>(ResponseStatus.FAILED, "请填写公司名称");
        }

        // 验证公司名称是否存在于大数据
        EntRegistry entRegistry = TaurusApiService.getEntRegistry(companyName);
        if (null == entRegistry) {
            return new Result<>(ResponseStatus.FAILED, "公司[" + companyName + "]不存在");
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
                }
                // 创建Account和Customer
                saveWithCustomer(account, dto.getCustomerSourceId());// 4110218 微信
            }
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
                    accountCompany.setCompanyName(companyName);
                    accountCompany.setCrmCompanyId(crmCompany.getId());
                    accountCompany.setAccountId(account.getId());
                    accountCompany.setStatus(1);
                    accountCompany.setInUse(1);
                }

                accountCompanyService.save(accountCompany);
            }

            redisClient.set(ConstantKey.ICOMPANY_CHOOSE_KEY + openId, companyName);
        }
        Result<Account> result = new Result<>();
        result.setObj(account);
        return result;
    }

    private CompanyIntention getAndSaveCompany(AccountValidateDTO dto, String companyName) {
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
