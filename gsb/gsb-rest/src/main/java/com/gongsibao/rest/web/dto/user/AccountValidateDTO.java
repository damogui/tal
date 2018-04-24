package com.gongsibao.rest.web.dto.user;

import java.io.Serializable;

/**
 * ClassName: AccountValidateDTO
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 检验用户状态, 可做其他扩展使用
 * @date 2018/4/24 15:06
 */
public class AccountValidateDTO implements Serializable {
    private static final long serialVersionUID = -8390469320056854672L;

    private String openId;
    private String mobile;
    private String companyName;

    private Integer accountSourceClientId = 0;
    private Integer customerSourceId = 0;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCustomerSourceId() {
        return customerSourceId;
    }

    public void setCustomerSourceId(Integer customerSourceId) {
        this.customerSourceId = customerSourceId;
    }

    public Integer getAccountSourceClientId() {
        return accountSourceClientId;
    }

    public void setAccountSourceClientId(Integer accountSourceClientId) {
        this.accountSourceClientId = accountSourceClientId;
    }
}
