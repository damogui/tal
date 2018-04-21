package com.gongsibao.product.service;

import java.sql.Types;
import java.util.*;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.product.base.IOrderProdService;
import com.gongsibao.product.base.IWorkflowService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.*;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.product.base.IWorkflowNodeService;
import org.netsharp.util.StringManager;

@Service
public class WorkflowNodeService extends PersistableService<WorkflowNode> implements IWorkflowNodeService {

    IWorkflowService workflowService = ServiceFactory.create(IWorkflowService.class);

    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

    public WorkflowNodeService() {
        super();
        this.type = WorkflowNode.class;
    }

    @Override
    public Integer getWorkflowNodeMaxVersion(Integer prodId, Integer cityId) {

        List<Integer> workflowIdList = workflowService.getIdsrodIdCityId(prodId, cityId);
        Integer version = getMaxVersion(workflowIdList);
        return version;
    }


    @Override
    public List<WorkflowNode> queryWorkflowNodeList(Integer prodId, Integer cityId, Integer version) {

        String filter = "	workflow_id IN "
                + "( SELECT w.pkid FROM prod_workflow w "
                + "LEFT JOIN prod_workflow_bd_dict_map m ON w.pkid = m.workflow_id "
                + " WHERE w.is_enabled = 1 and w.product_id = ? AND m.city_id = ? )"
                + " and version=?";
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter(filter);
            oql.setOrderby("sort");

            oql.getParameters().add("prodId", prodId, Types.INTEGER);
            oql.getParameters().add("cityId", cityId, Types.INTEGER);
            oql.getParameters().add("version", version, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public WorkflowNode getById(Integer Id) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", Id, Types.INTEGER);
        }
        return this.pm.queryFirst(oql);
    }

    @Override
    public Integer getVersionByOrderProdId(Integer orderProdId) {
        WorkflowNode workflowNode = getByOrderProdId(orderProdId);
        Integer currentVersion = null;
        if (workflowNode != null) {
            currentVersion = workflowNode.getVersion();
        }
        return currentVersion;
    }

    @Override
    public WorkflowNode getByOrderProdId(Integer orderProdId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("id = (select process_status_id from so_order_prod where pkid =" + orderProdId + ") ");
        }
        WorkflowNode workflowNode = this.pm.queryFirst(oql);
        return workflowNode;
    }

    @Override
    public List<WorkflowNode> findByWorkflowIds(List<Integer> workflowIdList, Integer version) {

        if (CollectionUtils.isEmpty(workflowIdList)) {
            return null;
        }

        //流程id集合
        String workflowIds = StringManager.join(",", workflowIdList);
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("workflow_id in(" + workflowIds + ") AND version = " + version + "");
            oql.setOrderby("sort ASC");
        }
        List<WorkflowNode> workflowNodes = this.pm.queryList(oql);
        return workflowNodes;
    }

    @Override
    public List<WorkflowNode> findByIds(List<Integer> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return null;
        }
        //流程id集合
        String ids = StringManager.join(",", idList);
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("pkid in(" + ids + ")");
            oql.setOrderby("version Asc ,sort asc");
        }
        List<WorkflowNode> workflowNodes = this.pm.queryList(oql);
        return workflowNodes;
    }

    @Override
    public Integer getMaxVersion(List<Integer> workFlowIdList) {
        Integer res = null;
        List<WorkflowNode> nodeList = getListByWorkFlowIdList(workFlowIdList);
        if (CollectionUtils.isEmpty(nodeList)) {
            return res;
        }

        Collections.sort(nodeList, (o1, o2) -> {
            WorkflowNode w1 = o1;
            WorkflowNode w2 = o2;
            return w2.getVersion() - w1.getVersion();
        });

        if (CollectionUtils.isNotEmpty(nodeList)) {
            res = nodeList.get(0).getVersion();
        }
        return res;
    }

    @Override
    public List<WorkflowNode> getListByWorkFlowIdList(List<Integer> workFlowIdList) {

        if (CollectionUtils.isEmpty(workFlowIdList)) {
            return null;
        }
        //流程id集合
        String workFlowIds = StringManager.join(",", workFlowIdList);
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("workflow_id in(" + workFlowIds + ")");
            oql.setOrderby("version Asc,sort asc ");
        }
        List<WorkflowNode> workflowNodes = this.pm.queryList(oql);
        return workflowNodes;
    }

    @Override
    public Map<String, Object> getFlowIdListAndVersionByOrderProdId(Integer orderProdId) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("version", null);
        resMap.put("workflowIdList", null);
        if (NumberUtils.toInt(orderProdId) == 0) {
            return resMap;
        }
        Integer version = null;
        List<Integer> workflowIdList = new ArrayList<>();
        OrderProd orderProd = orderProdService.getById(orderProdId);
        WorkflowNode node = getByOrderProdId(orderProdId);
        if (node != null) {
            version = node.getVersion();
            workflowIdList.add(node.getWorkflowId());
        }
        if (version == null) {
            workflowIdList = workflowService.getIdsrodIdCityId(orderProd.getProductId(), orderProd.getCityId());
            version = getMaxVersion(workflowIdList);
        }
        resMap.put("version", version);
        resMap.put("workflowIdList", workflowIdList);
        return resMap;
    }

    @Override
    public List<WorkflowNode> getListByOrderProdId(Integer orderProdId) {
        /*OrderProd orderProd = orderProdService.getById(orderProdId);
        List<WorkflowNode> reslist = new ArrayList<>();
        if (orderProd == null) {
            return reslist;
        }
        WorkflowNode node = getByOrderProdId(orderProdId);
        Integer version = null;
        List<Integer> workflowIdList = new ArrayList<>();
        if (node != null) {
            version = node.getVersion();
            workflowIdList.add(node.getWorkflowId());
        }
        if (version == null) {
            workflowIdList = workflowService.getIdsrodIdCityId(orderProd.getProductId(), orderProd.getCityId());
            version = getMaxVersion(workflowIdList);
        }*/

        List<WorkflowNode> reslist;
        Map<String, Object> flowIdListAndVersionMap = getFlowIdListAndVersionByOrderProdId(orderProdId);
        Integer version = (Integer) flowIdListAndVersionMap.get("version");
        List<Integer> workflowIdList = (List<Integer>) flowIdListAndVersionMap.get("workflowIdList");
        if (version == null || CollectionUtils.isEmpty(workflowIdList)) {
            throw new BusinessException("交付流程模版未设置，请联系管理！");
        }
        reslist = findByWorkflowIds(workflowIdList, version);
        return reslist;
    }
}