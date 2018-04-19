package com.gongsibao.rest.web.controller.v1.dict;

import com.gongsibao.rest.base.dict.IDictService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.web.Result;
import com.gongsibao.rest.web.dto.dict.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/18 19:12
 */
@RestController
@RequestMapping("/wx/{v}/dict")
@Api(1)
public class DictController {

    @Autowired
    private IDictService dictService;

    /**
     * 根据不同类型获取相关省市区三级联动数据
     *
     * @param listType 查询类型 (1:省份,2省份城市,3:全部)
     * @return
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public Result<List<CityDTO>> cityList(@RequestParam("listType") Integer listType) {
        return Result.build(() -> {
            return dictService.queryCityList(listType);
        });
    }
}
