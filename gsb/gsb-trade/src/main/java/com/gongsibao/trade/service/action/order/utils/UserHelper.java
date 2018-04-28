package com.gongsibao.trade.service.action.order.utils;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param salesmanId
     * @author: 郭佳
     * @Description:TODO 获取业务员的相关领导的电话
     * @date: 2018/4/28 13:54
     */
    public static List<String> getSalesmanLeaders(Integer salesmanId) {

        List<String> tels = new ArrayList<>();


        SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(salesmanId);
        //1.直级领导
        if (organization.getDirectLeaderId() != null && !organization.getDirectLeaderId().equals(salesmanId)) {
            tels.add(organization.getDirectLeaderMobile());

        }
        //2.隔级领导
        if (organization.getSuperiorLeaderId() != null && !organization.getSuperiorLeaderId().equals(organization.getDirectLeaderId())) {
            tels.add(organization.getSuperiorLeaderMobile());

        }
        return tels;

    }
}
