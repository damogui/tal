package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.TaskAllListPart;
import com.gongsibao.crm.web.platform.PlatformTaskAllListPart;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;

/**
 * Created by zhangchao on 2018/3/3.
 */
public class TaskUnstartWorkspaceTest extends TaskOpenSeaWorkspaceTest {

    @Override
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/customer/task/unstart/list";
        listPartName = formPartName = "未启动商机";
        resourceNodeCode = "Operation_CRM_Task_UNstart";
        listPartJsController = TaskAllListPart.class.getName();
        listPartServiceController = PlatformTaskAllListPart.class.getName();
        listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listToolbarPath = "task/unstart/toolbar";
        //未分配条件
        listFilter = " foolow_status = " + CustomerFollowStatus.UNSTART.getValue() + " ";//未启动
        rowToolbaPath ="/operation/task/unstart/row/toolbar";
    }

    @Override
	public PToolbar createRowToolbar() {
		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setBasePath("panda/datagrid/row/edit");
            toolbar.setPath(rowToolbaPath);
            toolbar.setName("分配");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("allocation");
            item.setName("分配");
            item.setSeq(1000);
            item.setCommand("{controller}.allocation();");
            toolbar.getItems().add(item);
        }
        //售前-提醒操作
        item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remind");
			item.setName("提醒");
			item.setSeq(2000);
			item.setCommand("{controller}.remind();");
			toolbar.getItems().add(item);
		}
        return toolbar;
	}
}