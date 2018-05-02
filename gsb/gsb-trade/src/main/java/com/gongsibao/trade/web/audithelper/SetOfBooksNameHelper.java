package com.gongsibao.trade.web.audithelper;

import com.gongsibao.entity.bd.AuditLog;
import org.netsharp.core.DataTable;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.Row;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SetOfBooksNameHelper
 * @Description:TODO
 * @author: 郭佳
 * @date: 2018/5/2  14:41
 */
public class SetOfBooksNameHelper {
    /**
     * @author: 郭佳
     * @param orderId
     * @Description:TODO 根据订单ID获取支付账套
     * @date:   2018/5/2 14:42
     */
    public static String getSetOfBooksName(Integer orderId){

        IPersister<AuditLog> auditService = PersisterFactory.create();
        String sql = "SELECT  order_id,set_of_books_id,u.name FROM  so_pay  s LEFT JOIN    so_order_pay_map m  ON s.pkid=m.pay_id   LEFT JOIN  u8_set_of_books u ON u.id=s.set_of_books_id WHERE   order_id=?  AND  set_of_books_id>0";


        QueryParameters qps=new QueryParameters();
        qps.add("@order_id",orderId, Types.INTEGER);
        DataTable dt = auditService.executeTable(sql, qps);
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (Row item : dt
                ) {
            if (item.get("name") != null && item.get("name").toString().length() > 0) {
                if (!list.contains(item.get("name"))) {
                    list.add(item.get("name").toString());

                }


            }

        }
        for (String item:list
                ) {
            sb.append("<p>");
            sb.append(item);
            sb.append("</p>");

        }


        return sb.toString();
    }


}
