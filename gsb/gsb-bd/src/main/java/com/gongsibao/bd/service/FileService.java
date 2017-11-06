package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;

@Service
public class FileService extends PersistableService<File> implements IFileService {

    public FileService(){
        super();
        this.type=File.class;
    }
}