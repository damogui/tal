package com.gongsibao.trade.service.action.order.transform;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.NOrderExchangeLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.NOrderExchangeLogOperationType;
import com.gongsibao.entity.trade.dic.NOrderExchangeLogType;
import com.gongsibao.trade.base.INOrderExchangeLogService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.Map;

/**
 * Created by zhangchao on 2018/3/8.
 */
public class ActionTransformRecordLog implements IAction {

    INOrderExchangeLogService orderExchangeLogService = ServiceFactory.create(INOrderExchangeLogService.class);

    IPersister<NOrderExchangeLog> changeLogPm = PersisterFactory.create();

    @Override
    public void execute(ActionContext ctx) {
        //订单
        SoOrder entity = (SoOrder) ctx.getItem();
        //获取额外参数
        Map<String, Object> statusMap = ctx.getStatus();
        //转移的目标业务员
        Salesman toUser = (Salesman) statusMap.get("toUser");
        //转移的来自业务员
        Salesman formUser = (Salesman) statusMap.get("formUser");

        NOrderExchangeLogType logType = formUser == null ? NOrderExchangeLogType.Fenpei : NOrderExchangeLogType.Zhuanyi;

        //region 先将该订单的流程日志都改为非当前的跟进记录（曾经跟进）
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("n_order_exchange_log");
            updateBuilder.set("is_current", 0);
            updateBuilder.where("order_id=?");
        }

        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", entity.getId(), Types.INTEGER);
        changeLogPm.executeNonQuery(sql, qps);
        // endregion

        //region 新增流转日志
        //内容
        String content = "【" + SessionManager.getUserName() + "】" + (logType.equals(NOrderExchangeLogType.Fenpei) ? "分配" : "转移") + "给【" + toUser.getEmployee().getName() + "】" + (logType.equals(NOrderExchangeLogType.Fenpei) ? "" : "，原跟进人为【" + formUser.getEmployee().getName() + "】");
        //新增流转日志
        NOrderExchangeLog log = new NOrderExchangeLog();
        log.toNew();
        log.setContent(content);
        log.setCurrent(true);//当前的跟进记录（正在跟进）
        log.setOrderId(entity.getId());
        log.setnOrderExchangeLogOperationType(NOrderExchangeLogOperationType.MANUAL);
        log.setnOrderExchangeLogType(logType);
        //目标业务员信息
        log.setToSupplierId(toUser.getSupplierId());
        log.setToSupplierName(toUser.getSupplier().getName());
        log.setToDepartmentId(toUser.getDepartmentId());
        log.setToDepartmentName(toUser.getDepartment().getName());
        log.setToUserId(toUser.getEmployeeId());
        log.setToUserName(toUser.getName());
        //来自业务员信息
        if (formUser != null) {
            log.setFormSupplierId(formUser.getSupplierId());
            log.setFormSupplierName(formUser.getSupplier().getName());
            log.setFormDepartmentId(formUser.getDepartmentId());
            log.setFormDepartmentName(formUser.getDepartment().getName());
            log.setFormUserId(formUser.getEmployeeId());
            log.setFormUserName(formUser.getName());
        }
        orderExchangeLogService.save(log);
        // endregion


    }
}
