package com.gongsibao.panda.operation.workspace.crm.report;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.StatisticalCustomerListPart;
import com.gongsibao.entity.crm.report.CustomerServiceReportEntity;
import com.gongsibao.entity.product.Product;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by zhangchao on 2018/2/28.
 */
public class StatisticalCustomerServiceWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {

        entity = CustomerServiceReportEntity.class;// 实体
        urlList = "/operation/statistical/customer/list";// 列表的url
        listPartName = formPartName = "客服统计";
        meta = MtableManager.getMtable(entity);// 获取实体元数据
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Operation_CRM_STATISTICAL_CUSTOMERSERVICE";// 菜单节点码（名称）
        listPartServiceController = StatisticalCustomerListPart.class.getName();
        listPartJsController = StatisticalCustomerListPart.class.getName();
        listPartImportJs = "/gsb/crm/js/customer.service.statistical.list.js|/gsb/gsb.custom.query.controls.js";
    }


    // 默认的grid信息的配置
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.toNew();
            datagrid.setResourceNode(node);
            datagrid.setName("客服统计");
        }

        PDatagridColumn column = null;
        column = addColumn(datagrid, "name", "客服", ControlTypes.ENUM_BOX, 100);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "creatCustomerCount", "创建客户数", ControlTypes.NUMBER_BOX, 80);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "creatTaskCount", "创建任务数", ControlTypes.NUMBER_BOX, 80);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "noTaskCustomerCount", "无任务客户数", ControlTypes.NUMBER_BOX, 80);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "manualDistribution", "手动分配任务数", ControlTypes.NUMBER_BOX, 80);
        {
            column.setVisible(false);
        }
        column = addColumn(datagrid, "unAllotTaskCount", "未分配任务数", ControlTypes.NUMBER_BOX, 80);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "unStartTaskCount", "未启动任务数", ControlTypes.NUMBER_BOX, 80);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "unSignTaskCount", "无法签单任务数", ControlTypes.NUMBER_BOX, 100);
        {
            column.setImported(true);
        }
        column = addColumn(datagrid, "openSeasCount", "公海", ControlTypes.NUMBER_BOX, 80);
        {
            column.setImported(true);
        }
        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {
        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        PQueryItem item = null;
        addQueryItem(queryProject, "date", "日期", ControlTypes.DATE_BOX);
        item = addQueryItem(queryProject, "productCategory1.name", "产品一级分类", ControlTypes.CUSTOM);
        {
            item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
            item.setRefFilter("type=201 and pid=0");
        }
        item = addQueryItem(queryProject, "productCategory2.name", "产品二级分类", ControlTypes.CUSTOM);
        {
            item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
            item.setRefFilter("type=201 and pid<>0");
        }
        addRefrenceQueryItem(queryProject, "product.name", "产品", "CRM_" + Product.class.getSimpleName());
        return queryProject;
    }

    // 默认的表单操作
    @Override
    protected void doOperation() {

        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node, OperationTypes.exportExcel);
    }
}
