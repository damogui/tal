package com.gongsibao.panda.platform.trade.workspace.audit;

import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditContractWorkspaceTest;
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
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.AuditLog;

/**
 * @ClassName: ContractAuditWorkspaceTest
 * @Description:TODO 合同审核
 * @author: 韩伟
 * @date: 2017年12月7日 下午8:08:37
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ContractAuditWorkspaceTest extends AuditContractWorkspaceTest {

    @Before
    public void setup() {
        super.setup();
        urlList = "/trade/audit/contract/list";// 列表的url
        resourceNodeCode = "GSB_Trade_Audit_Contract";// 菜单节点码（名称）
        listFilter = "type_id=" + AuditLogType.Htsq.getValue() + " AND add_user_id='{userId}' ";
    }

    @Test
    public void createRowToolbar() {

    }
}
