package com.netsharp.rest.dto.user;

import java.io.Serializable;

/**
 * ClassName: LoginDTO
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 检验用户状态
 * @date 2018/4/24 15:06
 */
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 254122857826977937L;

    // openId
    private String openId;
    // 电话
    private String mobile;
    // 公司
    private String companyName;

    // 会员来源id
    private Integer accountSourceClientId = 0;

    // customer来源id
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
