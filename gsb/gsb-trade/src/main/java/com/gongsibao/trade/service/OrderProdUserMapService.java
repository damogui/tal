package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.*;

import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.*;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;

@Service
public class OrderProdUserMapService extends PersistableService<OrderProdUserMap> implements IOrderProdUserMapService {

    public OrderProdUserMapService() {
        super();
        this.type = OrderProdUserMap.class;
    }

    @Override
    public int updateStatusByOrderProdId(List<Integer> orderProdIdList, Integer typeId, int newStatus, int oldStatus) {
        String orderProdIdsString = StringManager.join(",", orderProdIdList);
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order_prod_user_map ");
            updateBuilder.set("status_id", OrderProdUserMapStatus.getItem(newStatus).getValue());
            updateBuilder.where("order_prod_id in (" + orderProdIdsString + ") AND type_id = " + OrderProdUserMapType.getItem(typeId).getValue() + " AND status_id = "
                    + OrderProdUserMapStatus.getItem(oldStatus).getValue() + " ");
        }
        String sql = updateBuilder.toSQL();
        return this.pm.executeNonQuery(sql, null);
    }

    @Override
    public Map<Integer, String> getOrderUserByIds(List<Integer> pkidList, int typeId, int statusId) {
        Map<Integer, String> resMap = new HashMap<Integer, String>();
        String idString = StringManager.join(",", pkidList);
        StringBuffer sql = new StringBuffer("SELECT opum.order_prod_id 'orderProdId',u.real_name 'realName' FROM so_order_prod_user_map opum ");
        sql.append("JOIN uc_user u ON u.pkid = opum.user_id ");
        sql.append("WHERE opum.type_id=" + OrderProdUserMapType.getItem(typeId).getValue() + " AND opum.status_id=" + OrderProdUserMapStatus.getItem(statusId).getValue()
                + " AND opum.order_prod_id IN(" + idString + ") ");
        DataTable executeTable = this.pm.executeTable(sql.toString(), null);
        for (Row row : executeTable) {
            Integer orderProdId = row.getInteger("orderProdId");
            String realName = row.getString("realName");
            resMap.put(orderProdId, realName);
        }
        return resMap;
    }

    @Override
    public Map<Integer, String> getLastOperatorByOrderIdsStatusType(List<Integer> orderIdList, Integer typeId, Integer statusId) {
        StringBuffer sql = new StringBuffer();
        Map<Integer, String> resMap = new HashMap<Integer, String>();

        if (CollectionUtils.isEmpty(orderIdList)) {
            return resMap;
        }

        String orderIds = StringManager.join(",", orderIdList);

        sql.append("SELECT od.order_id,u.`real_name` FROM uc_user u ");
        sql.append("JOIN (SELECT * FROM so_order_prod_user_map WHERE pkid IN(SELECT MAX(pkid) FROM so_order_prod_user_map WHERE status_id=" + statusId + " AND type_id = " + typeId
                + " GROUP BY order_prod_id)) odum ON u.`pkid`=odum.user_id ");
        sql.append("JOIN so_order_prod od ON od.`pkid`=odum.`order_prod_id` ");
        sql.append("WHERE od.order_id IN(" + orderIds + ") ");
        sql.append("GROUP BY od.order_id ");

        DataTable executeTable = this.pm.executeTable(sql.toString(), null);
        for (Row row : executeTable) {
            resMap.put(row.getInteger("order_id"), row.getString("real_name"));
        }

        return resMap;
    }

    @Override
    public Boolean updateStatus(Integer id, OrderProdUserMapStatus newStatus, OrderProdUserMapStatus oldStatus) {

        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ");
        sql.append("so_order_prod_user_map ");
        sql.append("SET ");
        sql.append("status_id = ? ");
        sql.append("WHERE ");
        sql.append("pkid = ? ");
        sql.append("AND ");
        sql.append("status_id = ? ");
        QueryParameters qps = new QueryParameters();
        qps.add("newStatus", newStatus.getValue(), Types.INTEGER);
        qps.add("id", id, Types.INTEGER);
        qps.add("oldStatus", oldStatus.getValue(), Types.INTEGER);

        return this.pm.executeNonQuery(sql.toString(), qps) > 0;
    }

    @Override
    public List<OrderProdUserMap> queryList(Integer orderProdId, OrderProdUserMapType type) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("orderProdId = ? and type=?");
            oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
            oql.getParameters().add("type", type, Types.INTEGER);
        }
        return this.pm.queryList(oql);
    }

    @Override
    public List<OrderProdUserMap> queryProdPrincipalList(Integer orderProdId) {

        Oql oql = new Oql();
        {
            oql.setType(OrderProdUserMap.class);
            oql.setSelects("OrderProdUserMap.*,principal.{id,name,mobile}");
            oql.setFilter("orderProdId=?");
            oql.setOrderby("createTime DESC");
            oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public Boolean addPrincipal(Integer orderProdId, String principalIds, String principalNames) {
        String[] ss = principalIds.split(",");
        List<Integer> principalIdList = new ArrayList<>();
        for (String principalId : ss) {
            principalIdList.add(NumberUtils.toInt(principalId));
        }
        return addBatchPrincipal(Arrays.asList(orderProdId), principalIdList, principalNames);
    }

    @Override
    public Boolean addBatchPrincipal(List<Integer> orderProdIdList, List<Integer> principalIds, String principalNames) {
        if (CollectionUtils.isEmpty(orderProdIdList)) {
            throw new BusinessException("请先选择明细订单！");
        }

        if (CollectionUtils.isEmpty(principalIds)) {
            throw new BusinessException("请先选择操作员！");
        }

        // 1.创建负责人
        addprincipals(orderProdIdList, principalIds);

        // 2.添加跟进
        addtraces(orderProdIdList, principalNames);
        return true;
    }


    //创建负责人
    private void addprincipals(List<Integer> orderProdIdList, List<Integer> principalIds) {
        OrderProdUserMap orderProdUserMap = null;
        List<OrderProdUserMap> mapList = new ArrayList<OrderProdUserMap>();

        for (Integer orderProdId : orderProdIdList) {
            for (Integer principalId : principalIds) {
                orderProdUserMap = new OrderProdUserMap();
                {
                    orderProdUserMap.toNew();
                    orderProdUserMap.setOrderProdId(orderProdId);
                    orderProdUserMap.setPrincipalId(principalId);
                    orderProdUserMap.setType(OrderProdUserMapType.Czy);
                    orderProdUserMap.setStatus(OrderProdUserMapStatus.Zzfz);
                }
                mapList.add(orderProdUserMap);
            }
        }

        this.saves(mapList);
    }

    //添加跟进
    private void addtraces(List<Integer> orderProdIdList, String principalNames) {
        IOrderProdService prderProdService = ServiceFactory.create(IOrderProdService.class);
        Map<Integer, Integer> processStatusIdMap = prderProdService.getProcessStatusIdByOrderProdIds(orderProdIdList);
        List<OrderProdTrace> traceList = new ArrayList<>();
        for (Integer orderProdId : orderProdIdList) {
            OrderProdTrace trace = new OrderProdTrace();
            trace.toNew();
            trace.setOrderProdId(orderProdId);
            trace.setOrderProdStatusId(NumberUtils.toInt(processStatusIdMap.get(orderProdId)));
            trace.setTypeId(OrderProdTraceType.Txfzr);
            trace.setInfo("【" + SessionManager.getUserName() + "】添加【" + principalNames + "】为负责人");
            trace.setOperatorId(SessionManager.getUserId());
            traceList.add(trace);
        }
        IOrderProdTraceService traceService = ServiceFactory.create(IOrderProdTraceService.class);
        traceService.saves(traceList);
    }

}