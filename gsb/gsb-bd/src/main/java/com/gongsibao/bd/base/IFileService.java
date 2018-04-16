package com.gongsibao.bd.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.File;

import java.util.List;

public interface IFileService extends IPersistableService<File> {

    /**
     * @Description:TODO 根据表名和formId获取集合
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/11 19:40
     */
    List<File> getByTabNameFormId(String tabName, Integer formId);


}