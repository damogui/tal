package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.File;

import java.util.List;

public interface IFileService extends IPersistableService<File> {

    //根据表名和formId获取集合
    List<File> getByTabNameFormId(String tabName, Integer formId);

}