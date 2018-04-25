package com.gongsibao.rest.web.controller.v1.company;

import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.util.StringUtils;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.base.product.IProductService;
import com.gongsibao.rest.web.controller.BaseController;
import com.gongsibao.rest.web.dto.company.CompanyNameDTO;
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
@Api(1)
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
    public ResponseData lstService() {
        ResponseData data = new ResponseData();
        try {
            data.setCode(ResponseData.SUCCESS);
            data.setData(productService.findServiceList());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData.getException();
        }
        return data;
    }

    /**
     * 公司名称智能提示
     *
     * @param request
     * @return
     */
    @RequestMapping("/suggest")
    public ResponseData suggest(HttpServletRequest request) {
        try {
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
            return ResponseData.getSuccess(nameList, "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getException();
        }
    }
}
