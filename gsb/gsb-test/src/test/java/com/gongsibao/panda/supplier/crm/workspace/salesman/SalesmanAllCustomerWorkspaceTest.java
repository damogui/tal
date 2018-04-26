package com.gongsibao.panda.supplier.crm.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerALLWorkspaceTest;

public class SalesmanAllCustomerWorkspaceTest extends CustomerALLWorkspaceTest {

    @Override
    @Before
    public void setup() {

        super.setup();

        listPartName = "全部客户";
        urlList = "/crm/salesman/customer/list";
        listToolbarPath = "crm/salesman/customer/edit";
        resourceNodeCode = "CRM_SALESMAN_CUSTOMER";

        List<String> ss = new ArrayList<String>();
        ss.add("/gsb/supplier/crm/base/js/customer-base-list.part.js");
        ss.add("/gsb/supplier/crm/salesman/js/customer-all-list.part.js");
        ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
        listPartImportJs = StringManager.join("|", ss);

        listPartJsController = NCustomerAllListPart.class.getName();
        listPartServiceController = NCustomerAllListPart.class.getName();

        listFilter = "id in (select min(customer_id) from n_crm_customer_task where owner_id = '{userId}' group by customer_id)";
    }


    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = new PQueryProject();
        queryProject.toNew();
        queryProject.setResourceNode(node);
        queryProject.setColumnCount(2);
        queryProject.setName(listPartName);
        PQueryItem item;
        item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("输入客户ID、客户名称、联系方式、关联企业");
            item.setWidth(350);
        }
        item = addQueryItem(queryProject, "customerSource.name", "首次来源", ControlTypes.CUSTOM);
        {

            item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
            item.setRefFilter("type=411");
        }
        addQueryItem(queryProject, "important", "客户等级", ControlTypes.ENUM_BOX);
//		addRefrenceQueryItem(queryProject, "supplier.name", "服务商", Supplier.class.getSimpleName());
        addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
        //addQueryItem(queryProject, "company.companyName", "关联企业", ControlTypes.TEXT_BOX);
        return queryProject;
    }
}
