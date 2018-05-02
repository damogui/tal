package com.gongsibao.rest.base.dict;

import com.netsharp.rest.dto.dict.CityDTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 数据字典
 * @date 2018/4/19 10:31
 */
public interface IDictService {

    /**
     * 查询省市区列表
     * @param listType 查询类型 (1:省份,2省份城市,3:全部)
     * @return List<CityDTO>
     */
    List<CityDTO> queryCityList(Integer listType);

    /**
     * 查询字典名称
     * @param type 字典类型
     * @param ids pkid
     * @return
     */
    Map<Integer, String> queryDictNames(int type, Collection<Integer> ids);
}
