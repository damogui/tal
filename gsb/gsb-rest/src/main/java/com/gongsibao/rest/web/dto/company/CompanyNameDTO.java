package com.gongsibao.rest.web.dto.company;

import com.gongsibao.taurus.entity.CompanyNameByKey;

import java.io.Serializable;

/**
 * ClassName: CompanyNameDto
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/25 15:45
 */
public class CompanyNameDTO implements Serializable {
    private static final long serialVersionUID = 3178176147054210274L;

    // 公司名
    private String name;
    // 显示名称-带颜色标记
    private String showName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public static CompanyNameDTO getObj(CompanyNameByKey companyNameByKey) {
        if (null == companyNameByKey) {
            return null;
        }
        CompanyNameDTO name = new CompanyNameDTO();
        name.setName(companyNameByKey.getName());
        return name;
    }
}
