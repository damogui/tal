package com.gongsibao.rest.controller.v1.company;

import com.gongsibao.entity.bd.BdService;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.utils.StringUtils;
import com.netsharp.rest.base.product.IProductService;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.dto.company.CompanyNameDTO;
import com.gongsibao.taurus.entity.CompanyNameByKey;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ICompanyController
 *
 * @author bhpeng <bhpeng@gongsibao.com>
 * @Description: TODO 微信我的服务接口
 * @date 2018/4/11 13:29
 */
@RestController
@RequestMapping("/wx/{v}/icompany/company")
@ApiVersion(1)
public class CompanyController extends BaseController {

    @Autowired
    IProductService productService;

    /**
     * @return Result
     * @Description:TODO 我的服务
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/11 13:30
     */
    @RequestMapping(value = "/lstService", method = RequestMethod.GET)
    public List<BdService> lstService() {
        return productService.findServiceList();
    }

    /**
     * 公司名称智能提示
     *
     * @param request
     * @return
     */
    @RequestMapping("/suggest")
    public List<CompanyNameDTO> suggest(HttpServletRequest request) {
        String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));

        // 查大数据接口
        ResponseMessage<CompanyNameByKey> responseMessage = TaurusApiService.getCompanyNameByKey(companyName, 1, 10);

        // 封装DTO
        List<CompanyNameDTO> nameList = new ArrayList<>();
        if (null != responseMessage && CollectionUtils.isNotEmpty(responseMessage.getList())) {
            for (CompanyNameByKey companyNameByKey : responseMessage.getList()) {
                CompanyNameDTO dto = CompanyNameDTO.getObj(companyNameByKey);
                String name = StringUtils.trimToEmpty(dto.getName());
                dto.setName(name);
                dto.setShowName(name.replace(companyName, "<span>" + companyName + "</span>"));
                nameList.add(dto);
            }
        }
        return nameList;
    }
}
