package com.gongsibao.panda.platform.cw.workspace.other;

import org.junit.Before;
import org.netsharp.meta.base.WorkspaceCreationBase;

/**
 * 
* 资金调拨单 
* 项目名称：gsb-test   
* 类名称：AllocationBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class AllocationBillWorkspaceTest extends WorkspaceCreationBase {
	
    @Before
    public void setup() {

        super.setup();
        urlList = "/finance/bill/allocation/list";
        resourceNodeCode = "GSB_Finance_Manage_Allocation_Bill";
        listFilter = "";
    }
    

}
