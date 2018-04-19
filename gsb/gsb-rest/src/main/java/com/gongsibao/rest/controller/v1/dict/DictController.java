package com.gongsibao.rest.controller.v1.dict;

import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.web.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/18 19:12
 */
@RestController
@RequestMapping("/wx/{v}/dict")
@Api(1)
public class DictController {

    public Result<String> cityList(){
        return Result.build(()->{
            return null;
        });
    }
}
