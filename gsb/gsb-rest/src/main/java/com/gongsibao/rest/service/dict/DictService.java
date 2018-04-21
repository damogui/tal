package com.gongsibao.rest.service.dict;


import com.gongsibao.entity.bd.Dict;
import com.gongsibao.rest.base.dict.IDictService;
import com.gongsibao.rest.web.dto.dict.CityDTO;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/19 10:34
 */
@Service
public class DictService implements IDictService {

    private com.gongsibao.bd.base.IDictService dictService = ServiceFactory.create(com.gongsibao.bd.base.IDictService.class);


    @Override
    public List<CityDTO> queryCityList(Integer listType) {
        switch (listType) {
            case 1:
                return convertDto(dictService.byTypeParentId(101, Arrays.asList(0)));
            case 2:
                List<Dict> dicts = dictService.byTypeParentId(101, Arrays.asList(0));
                if (dicts != null) {
                    List<Integer> proIds = dicts.stream().map(dict -> {
                        return dict.getId();
                    }).collect(Collectors.toList());
                    proIds.add(0);
                    return convertDto(dictService.byTypeParentId(101, proIds));
                }
            case 3:
                return convertDto(dictService.byTypeParentId(101, null));
            default:
                return new ArrayList<>();
        }
    }

    private List<CityDTO> convertDto(List<Dict> dicts){
        return dicts.stream().filter(dict -> !dict.getName().equals("工信部")).map(dict -> {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(dict.getName());
            cityDTO.setParent(dict.getParentId());
            cityDTO.setValue(dict.getId());
            return cityDTO;
        }).collect(Collectors.toList());
    }
}
