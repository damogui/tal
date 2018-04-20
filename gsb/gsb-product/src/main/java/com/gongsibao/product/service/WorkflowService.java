package com.gongsibao.product.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Workflow;
import com.gongsibao.product.base.IWorkflowService;

@Service
public class WorkflowService extends PersistableService<Workflow> implements IWorkflowService {

    IDictService dictService = ServiceFactory.create(IDictService.class);

    public WorkflowService() {
        super();
        this.type = Workflow.class;
    }

    @Override
    public Boolean updateEnabled(Integer id, Boolean state) {

        String cmdText = "UPDATE prod_workflow set is_enabled=? where pkid=?";
        QueryParameters qps = new QueryParameters();
        qps.add("enabled", state, Types.BOOLEAN);
        qps.add("id", id, Types.INTEGER);
        return this.pm.executeNonQuery(cmdText, qps) > 0;
    }

    @Override
    public List<Integer> getIdsrodIdCityId(Integer prodId, Integer cityId) {
        List<Integer> resList = new ArrayList<>();
        int count = 0;
        //如果该地区没有流程则，一直往上一级地区找，直到找到为止
        while (count == 0) {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT distinct w.pkid 'workflowId' FROM prod_workflow w ");
            sql.append("JOIN prod_workflow_bd_dict_map wm ON w.pkid = wm.workflow_id ");
            sql.append("WHERE w.product_id = " + prodId + " AND wm.city_id = " + cityId + " AND w.is_enabled=1 ");
            DataTable rows = this.pm.executeTable(sql.toString(), null);
            for (IRow row : rows) {
                Integer workflowId = NumberUtils.toInt(row.getInteger("workflowId"));
                if (!workflowId.equals(0)) {
                    resList.add(workflowId);
                }
            }
            count = resList.size();
            Dict city = dictService.byId(cityId);
            if (NumberUtils.toInt(city.getParentId()) == 0) {
                break;
            } else {
                cityId = city.getParentId();
            }
        }
        return resList;
    }
}