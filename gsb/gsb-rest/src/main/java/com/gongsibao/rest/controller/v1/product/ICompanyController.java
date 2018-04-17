package com.gongsibao.rest.controller.v1.product;

import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class ICompanyController {

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
            data.setCode(ResponseData.FAIL);
            data.setMsg("您的网络不稳定，请稍后再试。");
        }
        return data;
    }
}
