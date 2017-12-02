package com.gongsibao.reporting;

import com.gongsibao.entity.uc.Organization;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.List;

public class OrganiztionHelper {


    //找到所有下级的组织机构Id
    public static void getOrganizationIds(List<Integer> alls, List<Integer> pids){

        alls.addAll(pids);

        IPersister<Organization> pm = PersisterFactory.create();

//        pm.executeNonQuery("alter table uc_organization add column organization_type int",null);

        Oql oql = new Oql();
        {
            String filter = "pid in ("+ StringManager.join(",",pids)+")";

            oql.setType(Organization.class);
            oql.setSelects("*");
            oql.setFilter(filter);
        }

        List<Organization> os = pm.queryList(oql);

        pids.clear();
        for(Organization o : os){
            alls.add(o.getId());
            pids.add(o.getId());

            System.out.println(o.getShortName());
        }
    }

//    public static List<Integer> getUserIds(Integer oid){
//
//    }
}
