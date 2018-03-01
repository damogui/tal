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
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.json.DatagridResultJson;
import org.netsharp.util.StringManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchao on 2018/2/28.
 */
public class StatisticalCustomerListPart extends AdvancedListPart {

    ICustomerServiceConfigService dustomerServiceConfigService = ServiceFactory.create(ICustomerServiceConfigService.class);

    @Override
    public Object query() throws IOException {

        this.pdatagrid = this.context.getDatagrid();
        //获取参数
        getFilterMap();

        Object json = null;

        int page = NumberUtils.toInt(getRequest("page"));
        int pageSize = NumberUtils.toInt(getRequest("rows"));
        int startIndex = (page - 1) * pageSize;

        DataTable rows = dustomerServiceConfigService.executeTable(getSql(1, startIndex, pageSize, filterMap), null);

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
                result.setTotal(getqueryListCount(getSql(2, startIndex, pageSize, filterMap)));
            }

            DatagridResultJson parser = new DatagridResultJson(result, pdatagrid);
            json = parser.parse();
        }
        return json;
    }


    /*获取sql语句*/
    private String getSql(int type, int startIndex, int pageSize, Map<String, FilterParameter> filterMap) {
        StringBuffer sbSql = new StringBuffer();

        //查询数据
        if (type == 1) {

            List<String> filterWhereList = new ArrayList();
            List<String> productWhereList = new ArrayList();
            for (String key : filterMap.keySet()) {
                //日期
                if (key.equals("date")) {
                    filterWhereList.add(filterMap.get(key).getFilter());
                }
                //产品一级类别
                if (key.equals("productCategoryId1")) {
                    //productWhereList.add(filterMap.get(key).getFilter().replace("productCategoryId1", "product_category_id_1"));
                    productWhereList.add("product_category_id_1 = " + filterMap.get(key).getValue1() + " ");
                }
                //产品二级类别
                if (key.equals("productCategoryId2")) {
                    //productWhereList.add(filterMap.get(key).getFilter().replace("productCategoryId2", "product_category_id_2"));
                    productWhereList.add("product_category_id_2 = " + filterMap.get(key).getValue1() + " ");
                }
                //产品
                if (key.equals("productId")) {
                    productWhereList.add(filterMap.get(key).getFilter().replace("productId", "product_id"));
                }
            }

            if (CollectionUtils.isNotEmpty(productWhereList)) {
                String prodWhereStr = " id IN (SELECT {paramid} FROM n_crm_customer_product_map WHERE " + StringManager.join(" and ", productWhereList) + " )";
                filterWhereList.add(prodWhereStr);
            }

            //生成时间
            String createTimeWhere = CollectionUtils.isEmpty(filterWhereList) ? "" : " and " + StringManager.join(" and ", filterWhereList).replace("date", "create_time");
            //分配时间
            String lastAllocationTime = CollectionUtils.isEmpty(filterWhereList) ? "" : " and " + StringManager.join(" and ", filterWhereList).replace("date", "last_allocation_time");

            sbSql.append("SELECT em.id,em.name,(SELECT COUNT(*) FROM n_crm_customer WHERE creator_id = em.id " + createTimeWhere.replace("{paramid}", "customer_id") + " ) 'creatCustomerCount' ");//创建客户数(取创建时间-create_time)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id " + createTimeWhere.replace("{paramid}", "task_id") + ") 'creatTaskCount' ");//创建任务数(取创建时间)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer WHERE id NOT IN(SELECT DISTINCT customer_id  FROM n_crm_customer_task) AND creator_id = em.id " + createTimeWhere.replace("{paramid}", "task_id") + ") 'noTaskCustomerCount' ");//创建客户数(取创建时间)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND allocation_type = 2 " + lastAllocationTime.replace("{paramid}", "task_id") + " ) 'manualDistribution' ");//手动分配任务数(取分配时间-last_allocation_time)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND (distribut IS NULL OR distribut = 0) " + createTimeWhere.replace("{paramid}", "task_id") + " ) 'creatTaskCount' ");//未分配任务数(取创建时间)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND foolow_status = 6 " + createTimeWhere.replace("{paramid}", "task_id") + ") 'unStartTaskCount' ");//未启动任务数(取创建时间)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND foolow_status = 4 " + createTimeWhere.replace("{paramid}", "task_id") + " ) 'unSignTaskCount' ");//无法签单任务数(取创建时间)
            sbSql.append(",(SELECT COUNT(*) FROM n_crm_customer_task WHERE creator_id = em.id AND ( (owner_id IS NULL OR owner_id =0) AND (department_id IS NULL OR department_id = 0) AND (supplier_id IS NULL OR supplier_id = 0)) " + createTimeWhere.replace("{paramid}", "task_id") + " ) 'openSeasCount' ");//公海(取创建时间)
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
        DataTable rows = dustomerServiceConfigService.executeTable(getSql(2, 0, 0, null), null);
        for (Row r : rows) {
            count = NumberUtils.toInt(r.getString("rcount"));
        }
        return count;
    }
}
