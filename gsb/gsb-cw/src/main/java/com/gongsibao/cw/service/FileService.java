package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IFileService;
import com.gongsibao.entity.bd.File;

@Service
public class FileService extends PersistableService<File> implements IFileService {

    public FileService() {
        super();
        this.type = File.class;
    }

    @Override
    public List<File> getByTabNameFormId(String tabName, Integer formId) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("form_id = ? and tab_name = ?");
            oql.getParameters().add("formId", formId, Types.INTEGER);
            oql.getParameters().add("tabName", tabName, Types.VARCHAR);
        }

        List<File> files = this.pm.queryList(oql);

        return files;
    }
    
    
}