package com.gongsibao.rest.web.demo.v2;

import com.netsharp.rest.common.annotation.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/{v}/user")
@Api(5)
public class ApiSeController {


    @Value("${server.port}")
    private String port;



    @RequestMapping(value="/hello2",method=RequestMethod.GET)
    public String helloV2() throws Exception {
        return"find data v5 hello2";
    }

    @RequestMapping(value="/hello3",method= RequestMethod.GET)
    public String helloV3() throws Exception {
        return"find data v3";
    }
}
