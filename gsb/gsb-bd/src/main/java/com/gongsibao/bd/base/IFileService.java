package com.gongsibao.bd.base;

import com.gongsibao.entity.bd.File;
import org.netsharp.base.IPersistableService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IFileService extends IPersistableService<File> {

    /**
     * @Description:TODO 根据表名和formId获取集合
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/11 19:40
     */
    List<File> getByTabNameFormId(String tabName, Integer formId);

    List<File> getByTabNameFormIds(String tabName, Collection<Integer> formIds);


    Map<Integer, List<File>> getMapByFormIds(String tabName, Collection<Integer> formIds);

}