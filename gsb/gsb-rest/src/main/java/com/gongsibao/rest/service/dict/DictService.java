package com.gongsibao.rest.service.dict;


import com.gongsibao.entity.bd.Dict;
import com.gongsibao.rest.base.dict.IDictService;
import com.netsharp.rest.dto.dict.CityDTO;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.*;
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
                return new ArrayList<>();
            case 3:
                return convertDto(dictService.byTypeParentId(101, null));
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public Map<Integer, String> queryDictNames(int type, Collection<Integer> ids) {
        Map<Integer, String> result = new HashMap<>();
        if (CollectionUtils.isEmpty(ids) || type < 0) {
            return result;
        }
        List<Dict> list = dictService.byType(type);
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        Map<Integer, Dict> dictMap = new HashMap<>();
        list.stream().forEach(dict -> {
            dictMap.put(dict.getId(),dict);
        });

        int pid = 0;
        String name = null;
        Dict dict = null;
        List<Integer> daludiqu = Arrays.asList(101900000, 101900100, 101900101);
        for (Integer id : ids) {
            if (daludiqu.contains(id)) {
                name = "大陆地区";
            } else {
                dict = dictMap.get(id);
                if (dict == null) {
                    continue;
                }
                pid = dict.getParentId();
                name = dict.getName();
                while (pid > 0) {
                    dict = dictMap.get(pid);
                    if (dict == null) {
                        break;
                    }
                    pid = dict.getParentId();
                    name = dict.getName() + "-" + name;
                }
            }
            result.put(id, name);
        }
        return result;
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
