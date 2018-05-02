package com.gongsibao.rest.web.demo;

import com.netsharp.rest.common.annotation.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * API控制器入口：
 * 首先，根控制器为动态版本{v}，供前端传递；
 * 其次，在接口方法上标注@Api自定义注解；
 * 最后，访问同一个地址时，如：http://www.gongsibao.com:port/v1/wx/hello，
 * 结果返回hello world v1
 */
@RestController
@RequestMapping("/wx/{v}/user")
public class ApiDemoController {
    @Api(1)
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public String helloV1() throws Exception {
        return"hello world v1";
    }

    @RequestMapping(value="/hello",method=RequestMethod.GET)
    @Api(2)
    public String helloV2() throws Exception {
        return"hello world v2";
    }

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    @Api(3)
    public String helloV3() throws Exception {
        return "hello world v3";
    }
}
