package com.gongsibao.rest.demo.v1;

import com.gongsibao.rest.common.apiversion.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/{v}/user")
@Api(4)
public class ApiController {
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public String helloV1() throws Exception {
        return"hello world v4";
    }

    @RequestMapping(value="/hello2",method=RequestMethod.GET)
    public String helloV4() throws Exception {
        return"hello world v4 hello2";
    }

}
