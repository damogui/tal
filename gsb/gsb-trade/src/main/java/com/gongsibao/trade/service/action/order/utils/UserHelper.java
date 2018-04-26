package com.gongsibao.trade.service.action.order.utils;

import com.gongsibao.entity.bd.AuditLog;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;

/*登录用户帮助类*/
public class UserHelper {
    private static IPersister<Employee> emService = PersisterFactory.create();

    public static String getEmployeeName(int employeeId) {
        if (employeeId == 0) {
            return "";
        }

        String sql = "SELECT  NAME FROM  sys_permission_employee WHERE id=?";

        QueryParameters qps = new QueryParameters();

        qps.add("@id", employeeId, Types.INTEGER);
        Object obj = emService.executeScalar(sql, qps);

        if (obj == null) {
            return "";

        } else {
            return obj.toString();
        }


    }

    /*获取用户的电话*/
    public static String getEmployeTelById(int employeeId) {


        if (employeeId == 0) {
            return "";
        }

        String sql = "SELECT  mobile FROM  sys_permission_employee WHERE id=?";

        QueryParameters qps = new QueryParameters();

        qps.add("@id", employeeId, Types.INTEGER);
        Object obj = emService.executeScalar(sql, qps);

        if (obj == null) {
            return "";

        } else {
            return obj.toString();
        }


    }
}
