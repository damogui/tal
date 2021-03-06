package com.gongsibao.panda.platform.trade.workspace.audit;

import com.gongsibao.panda.supplier.order.workspace.audit.AuditPayPerformanceWorkspaceTest;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.AuditLog;


/*交易中心回款业绩审核 */
public class ReceivedAuditWorkspaceTest extends AuditPayPerformanceWorkspaceTest {
    private String listrowToolbarPath="/trade/audit/roworderrec/toolbar";
    @Before
    public void setup() {
        super.setup ();
        //entity = AuditLog.class;// 实体
        urlList = "/trade/audit/received/list";// 列表的url
        //urlForm = "/trade/audit/received/form";// 弹出框的url
        listPartName = formPartName = "回款业绩审核";
        meta = MtableManager.getMtable (entity);// 获取实体元数据
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "GSB_Trade_Audit_ReceivedPer";// 菜单节点码（名称）

    }
//    @Test
//    public void createRowToolbar() {
//
//        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
//        PToolbar toolbar = new PToolbar();
//        {
//            toolbar.toNew();
//            toolbar.setPath(listrowToolbarPath);
//            toolbar.setName("审核");
//            toolbar.setResourceNode(node);
//            toolbar.setToolbarType(ToolbarType.BASE);
//        }
//        PToolbarItem item = new PToolbarItem();
//        {
//            item.toNew();
//            item.setCode("audit");
//            item.setName("审核");
//            item.setSeq(1);
//            item.setCommand("{controller}.audit();");
//            toolbar.getItems().add(item);
//        }
//
//
//        toolbarService.save(toolbar);
//    }


}