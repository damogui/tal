package com.gongsibao.panda.platform.trade.workspace.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditPayWorkspaceTest;
import org.junit.Before;
import org.netsharp.core.MtableManager;

/**
 * Created by win on 2018/3/29.
 */
/*交易中心回款审核*/

public class PayAuditWorkspaceTest extends AuditPayWorkspaceTest {
    @Before
    public void setup() {

        entity = AuditLog.class;// 实体
        urlList = "/trade/audit/pay/list";// 列表的url
        //urlForm = "/trade/audit/received/form";// 弹出框的url
        listPartName = formPartName = "回款审核";
        meta = MtableManager.getMtable (entity);// 获取实体元数据
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "GSB_Trade_Audit_Received";// 菜单节点码（名称）

    }
}
