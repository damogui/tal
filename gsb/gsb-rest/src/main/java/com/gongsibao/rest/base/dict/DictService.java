package com.gongsibao.rest.base.dict;

import com.gongsibao.rest.web.dto.dict.CityDTO;

import java.util.List;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 数据字典
 * @date 2018/4/19 10:31
 */
public interface DictService {

    /**
     * 查询省市区列表
     * @param listType 查询类型 (1:省份,2省份城市,3:全部)
     * @return List<CityDTO>
     */
    List<CityDTO> queryCityList(Integer listType);
}
