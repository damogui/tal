package com.gongsibao.crm.web;

import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.entity.crm.report.CustomerServiceReportEntity;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.json.DatagridResultJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/2/28.
 */
public class StatisticalCustomerListPart extends AdvancedListPart {

    ICustomerServiceConfigService dustomerServiceConfigService = ServiceFactory.create(ICustomerServiceConfigService.class);

    @Override
    public Object query() throws IOException {

        this.pdatagrid = this.context.getDatagrid();
        Object json = null;
        //TODO:获取参数，设置分页

        int page = NumberUtils.toInt(getRequest("page"));
        int pageSize = NumberUtils.toInt(getRequest("rows"));
        int startIndex = (page - 1) * pageSize;

        DataTable rows = dustomerServiceConfigService.executeTable(getSql(1, startIndex, pageSize), null);

        List<CustomerServiceReportEntity> resRows = new ArrayList();

        for (Row r : rows) {
            CustomerServiceReportEntity entity = new CustomerServiceReportEntity();
            entity.setId(r.getInteger("id"));
            entity.setName(r.getString("name"));//客服名称
            entity.setCreatCustomerCount(NumberUtils.toInt(r.getString("creatCustomerCount")));//创建客户数
            entity.setCreatTaskCount(NumberUtils.toInt(r.getString("creatTaskCount")));//创建任务数
            entity.setNoTaskCustomerCount(NumberUtils.toInt(r.getString("noTaskCustomerCount")));//无任务客户数
            entity.setManualDistribution(NumberUtils.toInt(r.getString("manualDistribution")));//手动分配任务数
            entity.setUnAllotTaskCount(NumberUtils.toInt(r.getString("unAllotTaskCount")));//未分配任务数
            entity.setUnStartTaskCount(NumberUtils.toInt(r.getString("unStartTaskCount")));//未启动任务数
            entity.setUnSignTaskCount(NumberUtils.toInt(r.getString("unSignTaskCount")));//无法签单任务数
            entity.setOpenSeasCount(NumberUtils.toInt(r.getString("openSeasCount")));//公海
            resRows.add(entity);
        }
        if (CollectionUtils.isNotEmpty(resRows)) {

            EasyuiDatagridResult result = new EasyuiDatagridResult();
            {
                result.setRows(resRows);
                result.setTotal(getqueryListCount(getSql(2, startIndex, pageSize)));
            }

            DatagridResultJson parser = new DatagridResultJson(result, pdatagrid);
            json = parser.parse();
        }
        return json;
    }


    /*获取sql语句*/
    private String getSql(int type, int startIndex, int pageSize) {
        StringBuffer sbSql = new StringBuffer();
        //查询数据
        if (type == 1) {
            sbSql.append("SELECT em.id,em.name,(SELECT COUNT(*) FROM n_crm_customer WHERE creator_id = em.id) 'creatCustomerCount' ");//创建客户数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id) 'creatTaskCount' ");//创建任务数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer WHERE id NOT IN(SELECT DISTINCT customer_id  FROM n_crm_customer_task) AND creator_id = em.id) 'noTaskCustomerCount' ");//创建客户数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND allocation_type = 2 ) 'manualDistribution' ");//手动分配任务数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND (distribut IS NULL OR distribut = 0)) 'creatTaskCount' ");//未分配任务数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND foolow_status = 6) 'unStartTaskCount' ");//未启动任务数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND foolow_status = 4) 'unSignTaskCount' ");//无法签单任务数
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND ( (owner_id IS NULL OR owner_id =0) AND (department_id IS NULL OR department_id = 0) AND (supplier_id IS NULL OR supplier_id = 0))) 'openSeasCount' ");//公海

        }
        //获取该次查询的总条数
        if (type == 2) {
            sbSql.append("SELECT COUNT(*) 'rcount' ");
        }

        sbSql.append("FROM `sys_permission_employee` em ");
        sbSql.append("JOIN crm_customer_service_config csc ON csc.employee_id = em.id ");
        sbSql.append("WHERE em.disabled = 0 ");
        if (type == 1)
            sbSql.append("LIMIT " + startIndex + ", " + pageSize + " ");

        return sbSql.toString();
    }


    // 获取查询的总条数
    protected int getqueryListCount(String sql) {
        int count = 0;
        DataTable rows = dustomerServiceConfigService.executeTable(getSql(2, 0, 0), null);
        for (Row r : rows) {
            count = NumberUtils.toInt(r.getString("rcount"));
        }
        return count;
    }
}
