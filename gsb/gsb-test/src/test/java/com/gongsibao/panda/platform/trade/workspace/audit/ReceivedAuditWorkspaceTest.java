package com.gongsibao.panda.platform.trade.workspace.audit;

import com.gongsibao.panda.supplier.order.workspace.audit.AuditPayPerformanceWorkspaceTest;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.AuditLog;

/**
 * @ClassName: ReceiptAuditWorkspaceTest
 * @Description:TODO 收款审核
 * @author: 韩伟
 * @date: 2017年12月7日 下午8:08:20
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
/*交易中心回款业绩审核 */
public class ReceivedAuditWorkspaceTest extends AuditPayPerformanceWorkspaceTest {

    @Before
    public void setup() {

        entity = AuditLog.class;// 实体
        urlList = "/trade/audit/received/list";// 列表的url
        //urlForm = "/trade/audit/received/form";// 弹出框的url
        listPartName = formPartName = "回款业绩审核";
        meta = MtableManager.getMtable (entity);// 获取实体元数据
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "GSB_Trade_Audit_ReceivedPer";// 菜单节点码（名称）
//
//		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
//		openWindowHeight = 400;
//		openWindowWidth = 800;
//		listFilter = "type_id=1045 AND add_user_id='{userId}' ";

		/*listPartServiceController = OrderOperationController.class.getName();
        listPartJsController = OrderOperationController.class.getName();
		listPartImportJs = "/gsb/platform/trade/js/orderoperation.list.part.js";
		listToolbarPath = "/trade/manage/order/operation/toolbar";*/
    }


}