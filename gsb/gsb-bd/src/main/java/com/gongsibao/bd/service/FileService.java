package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.*;

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

    @Override
    public List<File> getByTabNameFormIds(String tabName, Collection<Integer> formIds) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("form_id IN (" + StringManager.join(",", Arrays.asList(formIds.toArray())) +  ") and tab_name = ?");
            oql.getParameters().add("tabName", tabName, Types.VARCHAR);
        }

        return this.pm.queryList(oql);
    }

    @Override
    public Map<Integer, List<File>> getMapByFormIds(String tabName, Collection<Integer> formIds) {
        Map<Integer, List<File>> result = new HashMap<>();
        List<File> list = getByTabNameFormIds(tabName, formIds);
        if (null == list || list.isEmpty()) {
            return result;
        }

        for (File file : list) {
            Integer formId = file.getFormId();
            List<File> files = result.get(formId);
            if (null == files) {
                files = new ArrayList<>();
                result.put(formId, files);
            }
            files.add(file);
        }
        return result;
    }

}