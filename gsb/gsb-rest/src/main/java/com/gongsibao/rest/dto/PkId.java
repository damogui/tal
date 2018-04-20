package com.gongsibao.rest.dto;

import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO （沿用之前的类）
 * @date 2018/4/17 16:27
 */
public abstract class PkId implements Serializable{
    private static final long serialVersionUID = 7447938651449258944L;
    protected String pkidStr;
    protected Integer pkid;

    public Integer getPkid() {
        if (StringUtils.isNotBlank(pkidStr)) {
            return NumberUtils.toInt(SecurityUtils.rc4Decrypt(pkidStr));
        }
        return NumberUtils.toInt(pkid);
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getPkidStr() {
        if (StringUtils.isNotBlank(pkidStr)) {
            return pkidStr;
        }
        return SecurityUtils.rc4Encrypt(getPkid());
    }

    public void setPkidStr(String pkidStr) {
        this.pkidStr = pkidStr;
    }
}
